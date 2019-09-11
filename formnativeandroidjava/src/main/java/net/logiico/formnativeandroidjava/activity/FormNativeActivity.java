package net.logiico.formnativeandroidjava.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.webkit.WebView;

import com.google.android.material.tabs.TabLayout;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import net.logiico.formnativeandroidjava.BuildConfig;
import net.logiico.formnativeandroidjava.MyApplication;
import net.logiico.formnativeandroidjava.R;
import net.logiico.formnativeandroidjava.adapter.NativeTemplateTabViewPagerAdapter;
import net.logiico.formnativeandroidjava.callback.CustomTimePickerCallback;
import net.logiico.formnativeandroidjava.callback.NativeTemplateItemCallBack;
import net.logiico.formnativeandroidjava.callback.TimePickerCallback;
import net.logiico.formnativeandroidjava.customView.SingleActionSnackBar;
import net.logiico.formnativeandroidjava.dagger.DaggerFormNativeActivityComponent;
import net.logiico.formnativeandroidjava.executor.DBThreadExecutor;
import net.logiico.formnativeandroidjava.executor.MainThreadExecutor;
import net.logiico.formnativeandroidjava.fragment.OptionDialogFragment;
import net.logiico.formnativeandroidjava.helper.FontHelper;
import net.logiico.formnativeandroidjava.helper.LogHelper;
import net.logiico.formnativeandroidjava.helper.TenthActivity;
import net.logiico.formnativeandroidjava.helper.Utils;
import net.logiico.formnativeandroidjava.model.FileModelRepository;
import net.logiico.formnativeandroidjava.model.NativeTemplateField;
import net.logiico.formnativeandroidjava.model.NativeTemplateForm;
import net.logiico.formnativeandroidjava.model.NativeTemplateItem;
import net.logiico.formnativeandroidjava.model.NativeTemplateParentForm;
import net.logiico.formnativeandroidjava.model.NativeTemplateResponsePackage;
import net.logiico.formnativeandroidjava.model.NativeTemplateResultSendModel;
import net.logiico.formnativeandroidjava.model.NativeTemplateResultSendPackage;
import net.logiico.formnativeandroidjava.model.NativeTemplateTab;
import net.logiico.formnativeandroidjava.model.RoomFileModel;
import net.logiico.formnativeandroidjava.model.TemplateRepository;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Observable;
import java.util.Observer;
import java.util.regex.Pattern;

import javax.inject.Inject;

import static net.logiico.formnativeandroidjava.model.NativeTemplateField.EnumFieldType.*;
import static net.logiico.formnativeandroidjava.model.RoomTemplateResult.EnumResultStatusInApp.NOT_SET;

public class FormNativeActivity extends TenthActivity implements OptionDialogFragment.OnItemClickListener, NativeTemplateItemCallBack, CustomTimePickerCallback {

    public static final String TEMPLATE_CONTENT = "TEMPLATE_CONTENT";
    public static final String RESULT_UID = "RESULT_UID";
    public static final String TEMPLATE_EDITABLE = "TEMPLATE_EDITABLE";
    public static final String CUSTOMER_NAME = "CUSTOMER_NAME";
    public static final String CUSTOMER_ADDRESS = "CUSTOMER_ADDRESS";
    public static final String CUSTOMER_PHONE = "CUSTOMER_PHONE";
    public static final String RESULT_ENTERING_TIME = "RESULT_ENTERING_TIME";
    public static final String RESULT_SAVED = "RESULT_SAVED";
    public static final String RESULT_STATUS_IN_APP = "RESULT_STATUS_IN_APP";
    public static final String LAT = "LAT";
    public static final String LNG = "LOC_LNG";
    public static final String LOCATION_ENTERING_DATE = "LOCATION_ENTERING_DATE";
    public static final String RESULT_SUBMITTED = "RESULT_SUBMITTED";
    public static final String RESULT_FILE_LIST_SAVED = "RESULT_FILE_LIST_SAVED";

    //    public static final int REQUEST_SELECT_FILE = 100;
//    private final static int FILECHOOSER_RESULTCODE = 1;
//    private final static int ANDROID_FILE_CHOOSER_CODE = 213;
    private final static int ANDROID_SIGNATURE = 211;
    private static final int REQUEST_CAPTURE_IMAGE = 100;
    private static final int REQUEST_CAPTURE_IMAGE_IN_FORM = 101;
    private static final String PRE_ID_NAME = "MvcDynamicField_";
    //    private static final String FILE_TYPE = "file";
    private static final String SEPARATOR = "__--__";
    public static String uId = null;
    int currentTab = -1;
    @Inject
    TemplateRepository templateRepository;
    @Inject
    FileModelRepository fileModelRepository;
    ViewPager viewPager;
    TimePickerCallback timePickerCallback;
    String TAG = "ActivityBazrasi";
    View close, submit, loading;
    ArrayList<RoomFileModel> fileModelList = new ArrayList<>();
    int resultStatusInApp;
    String template, savedResult, entering_date;
    WebView webView;
    String customerName, customerAddress, customerPhone;
    String templateContent;
    boolean editable;

    String imageFilePath;
    //    private boolean exiting = false;
    String TagMain = "templateRes";
    TabLayout tabLayout;
    List<NativeTemplateResultSendPackage> sendPackageList;
    int OnActivityResultNativeFormImageNumber;
    String OnActivityResultNativeFormImageId = null;
    String NativeFormImageValue = null;
    private NativeTemplateResponsePackage templateModel = null;

    /**
     * some times when coming back from this activity language changes (because of webView in android 7) so L corrected the language
     */
    public void setCurrentLanguageInLocal() {
        Context context = MyApplication.getInstance();
        LogHelper.d("LanguageSettings7", "setCurrentLanguageInLocal");

    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public int getLayoutResourceID() {
        return R.layout.activity_form_native_base;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // checkGpsOnCompulsary();

    }

    @Override
    public void setUpElements() {
//        AppDatabase db = AppDatabase.getAppDatabase(FormNativeActivity.this);

        DaggerFormNativeActivityComponent.builder()
                .appComponent(MyApplication.getInstance().getAppComponent())
                .build().inject(this);

        close = findViewById(R.id.activity_form_close);
        submit = findViewById(R.id.activity_form_done);
        loading = findViewById(R.id.loading);

        templateContent = getIntent().getStringExtra(TEMPLATE_CONTENT);
        uId = getIntent().getStringExtra(RESULT_UID);
        editable = getIntent().getBooleanExtra(TEMPLATE_EDITABLE, true);
        customerName = getIntent().getStringExtra(CUSTOMER_NAME);
        customerAddress = getIntent().getStringExtra(CUSTOMER_ADDRESS);
        customerPhone = getIntent().getStringExtra(CUSTOMER_PHONE);
        entering_date = getIntent().getStringExtra(RESULT_ENTERING_TIME);
        //String templateId = getIntent().getStringExtra(ActivityJobDetails.JOB_TEMPLATE_HTML);

//        template = FormHandleTemplate.getTemplatesContentById(getBaseContext(), Integer.valueOf(temp_id));// todo : make pop up
//        template = db.templateDao().getTemplateContentModelById(temp_id);
        DBThreadExecutor.getInstance().execute(new Runnable() {
            @Override
            public void run() {
//                template = templateRepository.getTemplateContentModelById(templateId);
                template = templateContent;

            }
        });

        //  Log.e("JOB_TEMPLATE_HTML",template);
        savedResult = getIntent().getStringExtra(RESULT_SAVED);
        resultStatusInApp = getIntent().getIntExtra(RESULT_STATUS_IN_APP, NOT_SET.getIntValue());
        //   attachment = (FloatingActionButton) findViewById(R.id.activity_form_web_based_attachment);

    }

    @Override
    public void setUpElementsWithData() {

    }

    @Override
    public void setUpOnClicks() {
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // finish();
                if (editable)
                    saveTemporary();
                else
                    finish();
            }
        });

        if (editable) {
            submit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // forceFinish();
                    if (validateNative()) {
//                        Gson gson = new Gson();
                        String str = new Gson().toJson(sendPackageList);
                        LogHelper.i("onCallBack  >>>>> sendPackageList setUpOnClicks >>>>>>> ", str);

                        submitNative(str);
                    } else {
                        SingleActionSnackBar.show(MyApplication.getInstance().activity.findViewById(android.R.id.content),
                                getResources().getColor(R.color.snack_bar_red_color),
                                getResources().getColor(R.color.white),
                                getString(R.string.form_mandatory_error),
                                getString(R.string.ok), new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        SingleActionSnackBar.dismiss();
                                    }
                                });
                    }
                }
            });
        } else {
            submit.setVisibility(View.GONE);
        }


    }


    void updateFieldsWithSavedResult() {
        LogHelper.e("SAVED RESULT:", savedResult);

        if (savedResult != null) {
            List<NativeTemplateResultSendPackage> sendPackageList = new Gson().fromJson(savedResult, new TypeToken<ArrayList<NativeTemplateResultSendPackage>>() {
            }.getType());

            if (sendPackageList != null && templateModel != null) {
                for (int i = 0; i < templateModel.Tabs.size(); i++) {
                    NativeTemplateTab tab = templateModel.Tabs.get(i);

                    for (int j = 0; j < tab.Forms.size(); j++) {
                        NativeTemplateParentForm parentForm = tab.Forms.get(j);
                        NativeTemplateForm form = parentForm.Form;

                        for (int k = 0; k < sendPackageList.size(); k++) {
                            NativeTemplateResultSendPackage sendPackage = sendPackageList.get(k);

                            if (form.Id == Integer.valueOf(sendPackage.FormId)) {
                                NativeTemplateResultSendPackage lastSendPackage = null;

                                if (k > 0) {
                                    lastSendPackage = sendPackageList.get(k - 1);
                                }

                                if (k > 0 && lastSendPackage.FormId.equalsIgnoreCase(sendPackage.FormId)) {
                                    tab.Forms.add(parentForm.getAnotherCopy());
                                    j++;
                                }

                                for (int l = 0; l < form.Fields.size(); l++) {
                                    NativeTemplateField field = form.Fields.get(l);

                                    for (int h = 0; h < sendPackageList.get(k).Data.size(); h++) {
                                        String fieldId = null;
                                        NativeTemplateResultSendModel fieldResult = sendPackage.Data.get(h);
                                        if (field.type == TEXTAREA_FIELD ||
                                                field.type == TEXT_FIELD ||
                                                field.type == NUMBER_FIELD) {

                                            fieldId = PRE_ID_NAME + field.name;
                                            if (fieldId.equalsIgnoreCase(fieldResult.Id)) {
                                                field.value = fieldResult.Value;
                                                break;
                                            }

                                        } else if (field.type == CHECKBOX_LIST_FIELD) {
                                            for (int t = 0; t < field.Items.size(); t++) {
                                                NativeTemplateItem item = field.Items.get(t);
                                                fieldId = PRE_ID_NAME + field.name + SEPARATOR + item.Id;
                                                if (fieldId.equalsIgnoreCase(fieldResult.Id)) {
                                                    item.selected = Boolean.valueOf(fieldResult.Value);
                                                    break;
                                                }
                                            }

                                        } else if (field.type == RADIO_BUTTON_LIST_FIELD) {
                                            fieldId = PRE_ID_NAME + field.name;
                                            if (fieldId.equalsIgnoreCase(fieldResult.Id)) {
                                                for (int t = 0; t < field.Items.size(); t++) {
                                                    if (field.Items.get(t).value.equalsIgnoreCase(fieldResult.Value)) {
                                                        field.Items.get(t).selected = true;
                                                        break;
                                                    }
                                                }
                                            }

                                        } else if (field.type == DATE_FIELD) {
                                            fieldId = PRE_ID_NAME + field.name;
                                            if (fieldId.equalsIgnoreCase(fieldResult.Id)) {
                                                field.value = fieldResult.Value;
                                                break;
                                            }

                                        } else if (field.type == SELECT_FIELD) {
                                            fieldId = PRE_ID_NAME + field.name;
                                            if (fieldId.equalsIgnoreCase(fieldResult.Id)) {
                                                field.value = fieldResult.Value;
                                                for (int t = 0; t < field.Items.size(); t++) {
                                                    NativeTemplateItem item = field.Items.get(t);
                                                    item.selected = item.value.equalsIgnoreCase(fieldResult.Value);
                                                }
                                                break;
                                            }

                                        } else if (field.type == FILE_FIELD) {
                                            fieldId = PRE_ID_NAME + field.name;

                                            if (fieldId.equalsIgnoreCase(fieldResult.Id)) {
                                                RoomFileModel dbFileModel = fileModelRepository.getByUid_Title(uId, fieldResult.Value);

                                                RoomFileModel roomFileModel = new RoomFileModel();
                                                roomFileModel.inputName = PRE_ID_NAME + field.name;
                                                roomFileModel.uId = uId;
                                                roomFileModel.status = RoomFileModel.EnumFileStatus.SAVED.getIntValue();
                                                roomFileModel.multiple = field.UseMultiple;
                                                roomFileModel.title = fieldResult.Value;

                                                if (field.fileModels == null) {
                                                    field.fileModels = new ArrayList<>();
                                                }

                                                boolean exist = false;

                                                if (dbFileModel == null) {
                                                    field.fileModels = new ArrayList<>();

                                                } else {
                                                    for (RoomFileModel current : field.fileModels) {
                                                        if (current.title.equalsIgnoreCase(fieldResult.Value)) {
                                                            exist = true;
                                                        }
                                                    }
                                                    roomFileModel.filePath = dbFileModel.filePath;
                                                }

                                                if (!exist)
                                                    field.fileModels.add(roomFileModel);
                                            }
                                        }

                                    }

                                }
                            }
                        }


                    }

                }
            }
            LogHelper.d("updateFieldsWithSavedResult/templateModelFirstTab", new Gson().toJson(templateModel.Tabs.get(0)));

        }
    }

    public void loadDataOnResume(int tab) {

        if (templateModel == null)
            return;
        DBThreadExecutor.getInstance().execute(new Runnable() {
            @Override
            public void run() {
                //TODO: Check if it meant to be temp id or mission id
//                String templateRes = templateRepository.getTemplateContentModelById(templateId);
                String templateRes = templateContent;

                LogHelper.e(TagMain, templateRes);

                try {
                    LogHelper.e(TagMain, "  templateRes :" + templateRes);

                    if (templateModel != null)
                        for (int i = 0; i < templateModel.Tabs.size(); i++) {
                            LogHelper.e(TagMain, " Field name :" + String.valueOf(i) + templateModel.Tabs.get(0).Forms.get(0).Form.Fields.get(0).name);
                            LogHelper.e(TagMain, " filed ResponseTitle:" + String.valueOf(i) + templateModel.Tabs.get(0).Forms.get(0).Form.Fields.get(0).ResponseTitle);
                        }
                } catch (Exception e) {
                    e.getStackTrace();
                }

                if (templateModel != null) {

                    /** add data to forms  */
                    updateFieldsWithSavedResult();

                    MainThreadExecutor.getInstance().execute(new Runnable() {
                        @Override
                        public void run() {

                            onCallBack();
                            // Get the ViewPager and set it's PagerAdapter so that it can display items
                            viewPager = (ViewPager) findViewById(R.id.viewpager_fn);
                            viewPager.setAdapter(new NativeTemplateTabViewPagerAdapter(templateModel.Tabs, getSupportFragmentManager(), templateModel.Tabs.size(), editable,
                                    getBaseContext()));

                            // Give the TabLayout the ViewPager
                            tabLayout = (TabLayout) findViewById(R.id.sliding_tabs_fn);
                            tabLayout.setupWithViewPager(viewPager);
                            FontHelper.overrideFonts(FormNativeActivity.this, tabLayout);
                            if (templateModel.Tabs.size() != 0) {
                                if (tab != -1 && templateModel.Tabs.size() > 1)
                                    viewPager.setCurrentItem(tab);
                                else viewPager.setCurrentItem(0);

                            }

                        }
                    });


                }
            }
        });

//        LogHelper.e(TagMain, templateRes);
//
//
//        Gson gson = new Gson();
//        try {
//            //  templateModel = gson.fromJson(templateRes, NF_A_TemplateModel.class);
//            LogHelper.e(TagMain, "  templateRes :" + templateRes);
//
//            if (templateModel != null)
//                for (int i = 0; i < templateModel.Tabs.size(); i++) {
//                    LogHelper.e(TagMain, " Field name :" + String.valueOf(i) + templateModel.Tabs.get(0).Forms.get(0).Form.Fields.get(0).name);
//                    LogHelper.e(TagMain, " filed ResponseTitle:" + String.valueOf(i) + templateModel.Tabs.get(0).Forms.get(0).Form.Fields.get(0).ResponseTitle);
//                }
//        } catch (Exception e) {
//            e.getStackTrace();
//        }
//
//        if (templateModel != null) {
//
//            /** add data to forms  */
//            setupFileAndValueFormNative();
//            onCallBack(null, null);
//            // Get the ViewPager and set it's PagerAdapter so that it can display items
//            viewPager = (ViewPager) findViewById(R.id.viewpager_fn);
//            viewPager.setAdapter(new FN_FormNative_ViewPagerAdaptor(templateModel.Tabs, getSupportFragmentManager(), templateModel.Tabs.size(), editable,
//                    getBaseContext()));
//
//            // Give the TabLayout the ViewPager
//            tabLayout = (TabLayout) findViewById(R.id.sliding_tabs_fn);
//            tabLayout.setupWithViewPager(viewPager);
//            FontHelper.overrideFonts(this, tabLayout);
//            if (templateModel.Tabs.size() != 0) {
//                if (tab != -1 && templateModel.Tabs.size() > 1)
//                    viewPager.setCurrentItem(tab);
//                else viewPager.setCurrentItem(0);
//
//            }
//
//        }

    }

    @Override
    public void loadData() {
//        String templateRes = FormHandleTemplate.getTemplatesContentById(getBaseContext(), Integer.valueOf(temp_id));// todo : make pop up

        DBThreadExecutor.getInstance().execute(new Runnable() {
            @Override
            public void run() {

//                TemplateRepository repo = new TemplateRepository();
//                String templateRes = templateRepository.getTemplateContentModelById(templateId);
                String templateRes = templateContent;

                MainThreadExecutor.getInstance().execute(new Runnable() {
                    @Override
                    public void run() {

                        LogHelper.e(TagMain, templateRes);

//                        Gson gson = new Gson();
                        try {
//                            templateModel = gson.fromJson(templateRes, NF_A_TemplateModel.class);
                            templateModel = new Gson().fromJson(templateRes, NativeTemplateResponsePackage.class);
                            LogHelper.e(TagMain, "  templateRes :" + templateRes);

                            if (templateModel != null)
                                for (int i = 0; i < templateModel.Tabs.size(); i++) {
                                    LogHelper.e(TagMain, " Field name :" + String.valueOf(i) + templateModel.Tabs.get(0).Forms.get(0).Form.Fields.get(0).name);
                                    LogHelper.e(TagMain, " filed ResponseTitle:" + String.valueOf(i) + templateModel.Tabs.get(0).Forms.get(0).Form.Fields.get(0).ResponseTitle);
                                }
                        } catch (Exception e) {
                            e.getStackTrace();
                        }

                        if (templateModel != null) {

                            loadDataOnResume(0);

                        }

                    }
                });
            }
        });



    }

    @Override
    public void onItemClicked(int position) {
        if (position == 0) {

            Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            if (cameraIntent.resolveActivity(getPackageManager()) != null) {

                openCameraIntent();

            }

        } else {
//            Intent intent = new Intent(this, ActivitySignatureForResult.class);
//            startActivityForResult(intent, ANDROID_SIGNATURE);
        }
    }

    private void openCameraIntent() {
        Intent pictureIntent = new Intent(
                MediaStore.ACTION_IMAGE_CAPTURE);
        if (pictureIntent.resolveActivity(getPackageManager()) != null) {
            //Create a file to store the image
            File photoFile = null;
            try {
                photoFile = createImageFile();

            } catch (IOException ex) {
                ex.printStackTrace();
                // Error occurred while creating the File
            }
            if (photoFile != null) {
                Uri photoURI;
// help : https://android.jlelse.eu/androids-new-image-capture-from-a-camera-using-file-provider-dd178519a954
                if (Build.VERSION.SDK_INT >= 24) {
                    photoURI = FileProvider.getUriForFile(this, BuildConfig.APPLICATION_ID + ".provider", photoFile);
                } else {
                    photoURI = Uri.fromFile(photoFile);
                }

                //  Uri photoURI = FileProvider.getUriForFile(this, BuildConfig.APPLICATION_ID + ".provider", photoFile);
                pictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(pictureIntent, REQUEST_CAPTURE_IMAGE);
            }
        }
    }

    //is used
    private void openCameraIntentForForm(int image_number, String Id, String Value) {

        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        File photoFile = null;
        try {
            photoFile = createImageFile();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        if (photoFile != null) {
            takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, FileProvider.getUriForFile(FormNativeActivity.this, /*getApplicationContext().getPackageName() +*/ ".provider", photoFile.getAbsoluteFile()));

            OnActivityResultNativeFormImageNumber = image_number;
            OnActivityResultNativeFormImageId = Id;
            NativeFormImageValue = Value;

            if (takePictureIntent.resolveActivity(getBaseContext().getPackageManager()) != null) {
                startActivityForResult(takePictureIntent, REQUEST_CAPTURE_IMAGE_IN_FORM);
            }
        }
//        if (pictureIntent.resolveActivity(getPackageManager()) != null) {
//            //Create a file to store the image
//
//            if (photoFile != null) {
//                Uri photoURI;
//// help : https://android.jlelse.eu/androids-new-image-capture-from-a-camera-using-file-provider-dd178519a954
//                if (Build.VERSION.SDK_INT >= 24) {
//                    photoURI = FileProvider.getUriForFile(this, BuildConfig.APPLICATION_ID + ".provider", photoFile);
//                } else {
//                    photoURI = Uri.fromFile(photoFile);
//                }
//                OnActivityResultNativeFormImageNumber = image_number;
//                OnActivityResultNativeFormImageId = Id;
//                NativeFormImageValue = Value;
//
//                //  Uri photoURI = FileProvider.getUriForFile(this, BuildConfig.APPLICATION_ID + ".provider", photoFile);
//                pictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
//                startActivityForResult(pictureIntent, REQUEST_CAPTURE_IMAGE_IN_FORM);
//            }
//        }
    }

    @Override
    protected void onResume() {
        loadDataOnResume(currentTab);
        super.onResume();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode,resultCode,intent);
        if (resultCode != RESULT_OK)
            return;
        LogHelper.e("CameraCamera", "--------");

        if (requestCode == REQUEST_CAPTURE_IMAGE_IN_FORM) {
            try {
                if (templateModel != null) {
                    boolean tabExists = false;

                    for (NativeTemplateTab tab : templateModel.Tabs) {
                        boolean formExists = false;

                        for (NativeTemplateParentForm parentForm : tab.Forms) {
                            for (NativeTemplateField field : parentForm.Form.Fields) {
                                if (String.valueOf(field.Id).equalsIgnoreCase(OnActivityResultNativeFormImageId)) {
                                    if (field.fileModels == null) {
                                        field.fileModels = new ArrayList<>();
                                    }

                                    RoomFileModel fileModel = new RoomFileModel();
                                    field.fileModels.add(OnActivityResultNativeFormImageNumber, fileModel);
                                    this.fileModelList.add(fileModel);

                                    fileModel.filePath = imageFilePath;
                                    fileModel.inputName = PRE_ID_NAME + field.name;
                                    fileModel.uId = uId;
                                    fileModel.status = RoomFileModel.EnumFileStatus.SAVED.getIntValue();
                                    fileModel.multiple = field.UseMultiple;
                                    String[] split = imageFilePath.split("\\.");
                                    fileModel.title = uId + fileModel.inputName + field.fileModels.size() + "." + split[split.length - 1];
                                    tabExists = true;
                                    formExists = true;
                                    break;
                                }
                            }
                            if (formExists) {
                                break;
                            }
                        }

                        if (tabExists) {
                            break;
                        }
                    }
                }

                onCallBack();

                LogHelper.e("CameraCamera : ", imageFilePath);

            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    private File createImageFile() throws IOException {
        String timeStamp =
                new SimpleDateFormat("yyyyMMdd_HHmmss",
                        Locale.getDefault()).format(new Date());
        String imageFileName = "IMG_" + timeStamp + "_";
        File mediaStorageDir = new File(MyApplication.getInstance().getExternalFilesDir(
                Environment.DIRECTORY_PICTURES), "Template");

        if (!mediaStorageDir.exists()) {
            if (!mediaStorageDir.mkdirs()) {
                return null;
            }
        }

        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                mediaStorageDir      /* directory */
        );

        imageFilePath = image.getAbsolutePath();
        return image;
    }

    @Override
    protected void onPause() {
        setCurrentLanguageInLocal();
        LogHelper.d("javascriptmbr 7>>", "onPause");

        if (viewPager != null)
            currentTab = viewPager.getCurrentItem();
        else currentTab = -1;

//        exiting = false;
        super.onPause();
    }

    @Override
    public void onBackPressed() {
        if (editable) {
            saveTemporary();
        } else {
            finish();
        }

    }

    private void saveTemporary() {
        String finalData = new Gson().toJson(sendPackageList);//submitData2(data);
        LogHelper.e("javascriptmbr 4>", "submit : " + finalData + "");
        Intent intent = new Intent();
        intent.putExtra(RESULT_SAVED, finalData);
        intent.putExtra(RESULT_SUBMITTED, false);
        intent.putExtra(RESULT_UID, uId);
//        intent.putExtra(TEMPLATE_ID, templateId);
        intent.putExtra(CUSTOMER_NAME, customerName);
        intent.putExtra(CUSTOMER_ADDRESS, customerAddress);
        intent.putExtra(CUSTOMER_PHONE, customerPhone);
        intent.putExtra(RESULT_ENTERING_TIME, entering_date);
        intent.putExtra(TEMPLATE_EDITABLE, editable);
        intent.putExtra(RESULT_STATUS_IN_APP, resultStatusInApp);

        intent.putExtra(RESULT_FILE_LIST_SAVED, fileModelList);
//        intent.putParcelableArrayListExtra(RESULT_FILE_LIST_SAVED, fileModelList);
//                    intent.putStringArrayListExtra(ActivityJobDetails.JOB_TEMPLATE_SAVED_FILE_KEY_LIST, fileKeyList);
        setResult(RESULT_OK, intent);
        webView = null;
        finish();
    }




    @Override
    public void onCallBack() {
        LogHelper.e("FILES SIZE:", this.fileModelList == null ? "null" : String.valueOf(this.fileModelList.size()));
        sendPackageList = new ArrayList<>();
        this.fileModelList = new ArrayList<>();

        if (templateModel != null) {
            for (NativeTemplateTab tab : templateModel.Tabs) {
                for (NativeTemplateParentForm parentForm : tab.Forms) {
                    NativeTemplateForm form = parentForm.Form;

                    NativeTemplateResultSendPackage sendPackage = new NativeTemplateResultSendPackage();
                    sendPackage.Data = new ArrayList<>();
                    sendPackage.FormId = String.valueOf(form.Id);

                    for (NativeTemplateField field : form.Fields) {
                        NativeTemplateResultSendModel resultSend;

                        switch (field.type) {
                            case TEXT_FIELD:
                            case TEXTAREA_FIELD:
                            case NUMBER_FIELD:
                                resultSend = new NativeTemplateResultSendModel();
                                resultSend.Id = PRE_ID_NAME + field.name;
                                resultSend.Value = field.value;
                                sendPackage.Data.add(resultSend);
                                break;

                            case CHECKBOX_LIST_FIELD:
                                for (NativeTemplateItem item : field.Items) {
                                    resultSend = new NativeTemplateResultSendModel();
                                    resultSend.Id = PRE_ID_NAME + field.name + SEPARATOR + item.Id;
                                    resultSend.Value = String.valueOf(item.selected);
                                    sendPackage.Data.add(resultSend);
                                }
                                break;

                            case RADIO_BUTTON_LIST_FIELD:
                                resultSend = new NativeTemplateResultSendModel();
                                resultSend.Id = PRE_ID_NAME + field.name;
                                resultSend.Value = field.value;
                                sendPackage.Data.add(resultSend);
                                break;

                            case DATE_FIELD:
                                resultSend = new NativeTemplateResultSendModel();
                                resultSend.Id = PRE_ID_NAME + field.name;
                                resultSend.Value = field.value;
                                sendPackage.Data.add(resultSend);
                                break;

                            case SELECT_FIELD:
                                resultSend = new NativeTemplateResultSendModel();
                                resultSend.Id = PRE_ID_NAME + field.name;
                                for (NativeTemplateItem item : field.Items) {
                                    if (item.selected) {
                                        resultSend.Value = item.value;
                                        break;
                                    }

                                    sendPackage.Data.add(resultSend);
                                }
                                break;

                            case FILE_FIELD:
                                if (field.fileModels != null && field.fileModels.size() > 0) {
                                    for (RoomFileModel fileModel : field.fileModels) {
                                        this.fileModelList.add(fileModel);
                                        resultSend = new NativeTemplateResultSendModel();
                                        resultSend.Id = PRE_ID_NAME + field.name;
                                        resultSend.Value = fileModel.title;

                                        sendPackage.Data.add(resultSend);
                                    }

                                }
                                break;
                        }
                    }

                    if (sendPackage.Data != null && sendPackage.Data.size() > 0)
                        sendPackageList.add(sendPackage);
                }

            }

        }
        LogHelper.e("FORM RESULT: ", new Gson().toJson(sendPackageList));

    }
//    @Override
//    public void onCallBack(String Id, String Value) {
//        LogHelper.e("FILES SIZE:", this.fileModelList == null ? "null" : String.valueOf(this.fileModelList.size()));
//        sendPackageList = new ArrayList<>();
//        this.fileModelList = new ArrayList<>();
//        if (templateModel != null) {
//            for (int i = 0; i < templateModel.Tabs.size(); i++) {
//                //  LogHelper.v("onCallBack 1", " Id:" + templateModel.Tabs.get(i).Id + " Names:" + templateModel.Tabs.get(i).Name);
//                for (int j = 0; j < templateModel.Tabs.get(i).Forms.size(); j++) {
//                    //  LogHelper.v("onCallBack 2","Id:"+templateModel.Tabs.get(i).Forms.get(j)+ " Names:"+ templateModel.Tabs.get(i).Name);
//                    NativeTemplateSendPackage sendPackage = new NativeTemplateSendPackage();
//                    sendPackage.Data = new ArrayList<>();
//                    sendPackage.FormId = String.valueOf(templateModel.Tabs.get(i).Forms.get(j).Form.Id);
//                    for (int k = 0; k < templateModel.Tabs.get(i).Forms.get(j).Form.Fields.size(); k++) {
//                        NativeTemplateResultSend fdm = null;
////                        NF_E_Tab_Forms_Form_Field formNativeField = templateModel.Tabs.get(i).Forms.get(j).Form.Fields.get(k);
//                        NativeTemplateField formNativeField = templateModel.Tabs.get(i).Forms.get(j).Form.Fields.get(k);
//                        if (formNativeField.type == TEXT_FIELD ||
//                                formNativeField.type == TEXTAREA_FIELD ||
//                                formNativeField.type == NUMBER_FIELD) {
//                            fdm = new NativeTemplateResultSend();
//                            fdm.Id = PRE_ID_NAME + formNativeField.name;
//                            fdm.Value = formNativeField.value;
//                            sendPackage.Data.add(fdm);
//                        } else if (formNativeField.type == CHECKBOX_LIST_FIELD) {
//                            for (int t = 0; t < formNativeField.Items.size(); t++) {
////                                NF_F_Tab_Forms_Form_Field_Items item = formNativeField.Items.get(t);
//                                NativeTemplateItem item = formNativeField.Items.get(t);
//                                fdm = new NativeTemplateResultSend();
//                                fdm.Id = PRE_ID_NAME + formNativeField.name + SEPARATOR + item.Id;
//                                fdm.Value = String.valueOf(item.selected);
//                                sendPackage.Data.add(fdm);
//                            }
//                        } else if (formNativeField.type == RADIO_BUTTON_LIST_FIELD) {
//                            fdm = new NativeTemplateResultSend();
//                            fdm.Id = PRE_ID_NAME + formNativeField.name;
//                            fdm.Value = formNativeField.value;
//                            sendPackage.Data.add(fdm);
//
//                        } else if (formNativeField.type == DATE_FIELD) {
//                            fdm = new NativeTemplateResultSend();
//                            fdm.Id = PRE_ID_NAME + formNativeField.name;
//                            fdm.Value = formNativeField.value;
//                            sendPackage.Data.add(fdm);
//
//                        } else if (formNativeField.type == SELECT_FIELD) {
//                            fdm = new NativeTemplateResultSend();
//                            fdm.Id = PRE_ID_NAME + formNativeField.name;
//                            for (int t = 0; t < formNativeField.Items.size(); t++) {
////                                NF_F_Tab_Forms_Form_Field_Items item = formNativeField.Items.get(t);
//                                NativeTemplateItem item = formNativeField.Items.get(t);
//                                if (item.selected) {
//                                    fdm.Value = item.value;
//                                    break;
//                                }
//                            }
//                            sendPackage.Data.add(fdm);
//                        } else if (formNativeField.type == FILE_FIELD) {
//                            if (formNativeField.fileModels != null && formNativeField.fileModels.size() > 0) {
//                                for (int g = 0; g < formNativeField.fileModels.size(); g++) {
//                                    this.fileModelList.add(formNativeField.fileModels.get(g));
//                                    fdm = new NativeTemplateResultSend();
//                                    fdm.Id = PRE_ID_NAME + formNativeField.name;
//                                    fdm.Value = formNativeField.fileModels.get(g).title;
//                                    sendPackage.Data.add(fdm);
//                                }
//                            }
//                        }
//
//                    }
//                    if (sendPackage != null && sendPackage.Data != null && sendPackage.Data.size() != 0)
//                        sendPackageList.add(sendPackage);
//
//                }
//
//            }
//        }
//        LogHelper.e("FORM RESULT: ", new Gson().toJson(sendPackageList));
//
//    }

    //is used
    @Override
    public void onCameraOpen(int image_number, String Id, String Value) {
        openCameraIntentForForm(image_number, Id, Value);
    }

    public boolean validateNative() {
        boolean isValid = true;

        if (templateModel != null) {
            for (NativeTemplateTab tab : templateModel.Tabs) {
                for (NativeTemplateParentForm parentForm : tab.Forms) {
                    NativeTemplateForm form = parentForm.Form;

                    for (NativeTemplateField field : form.Fields) {

                        switch (field.type) {
                            case TEXT_FIELD:
                            case TEXTAREA_FIELD:
                            case NUMBER_FIELD:
                                if (field.RegEx != null && !field.RegEx.isEmpty()) {
                                    try {
                                        Pattern sPattern = Pattern.compile(field.RegEx);

                                        if (!sPattern.matcher(field.value).matches()) {
                                            isValid = false;
                                        }
                                    } catch (Exception e) {
                                        e.getStackTrace();
                                    }
                                }

                                if (field.required && field.value.isEmpty()) {
                                    isValid = false;
                                }
                                break;

                            case DATE_FIELD:
                            case SELECT_FIELD:
                                if (field.required && field.value.isEmpty()) {
                                    isValid = false;
                                }
                                break;

                            case FILE_FIELD:
                                if (field.required && (field.fileModels == null || field.fileModels.isEmpty())) {
                                    isValid = false;
                                }
                                break;

                        }

                    }
                }
            }

        }

        return isValid;
    }

    public void submitNative(final String data) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                String finalData = data;//submitData2(data);
                LogHelper.e("javascriptmbr 4>", "submit : " + finalData + "");
                Intent intent = new Intent();
                intent.putExtra(RESULT_SAVED, finalData);
                intent.putExtra(RESULT_SUBMITTED, true);
                intent.putExtra(RESULT_UID, uId);
//                intent.putExtra(TEMPLATE_ID, templateId);
                intent.putExtra(CUSTOMER_NAME, customerName);
                intent.putExtra(CUSTOMER_ADDRESS, customerAddress);
                intent.putExtra(CUSTOMER_PHONE, customerPhone);
                intent.putExtra(RESULT_ENTERING_TIME, entering_date);
                intent.putExtra(TEMPLATE_EDITABLE, editable);
                intent.putExtra(RESULT_STATUS_IN_APP, resultStatusInApp);

                intent.putExtra(RESULT_FILE_LIST_SAVED, fileModelList);
                setResult(RESULT_OK, intent);
                webView = null;
                finish();

            }
        });
    }

    @Override
    public void onTimeSet(long timeOnly, long dateWithTime) {
        timePickerCallback.onCallBack(timeOnly);
    }

    public void setTimePickerCallback(TimePickerCallback timePickerCallback) {
        this.timePickerCallback = timePickerCallback;
    }

//    private class FormModel {
//        public String FormId;
//        public List<FormDataModel> Data;
//
//    }
//
//    private class FormDataModel {
//        public String Id;
//        public String Value;
//        public String fieldId;
//
//    }
}

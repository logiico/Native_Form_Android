package com.example.nativeformmakerandroid;

import android.Manifest;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.location.Location;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.webkit.WebView;
import android.widget.Toast;

import com.example.nativeformmakerandroid.helper.ConstString;
import com.example.nativeformmakerandroid.helper.LogHelper;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

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

import com.example.nativeformmakerandroid.Interfaces.CallBack_FN_Model_Listener;
import com.example.nativeformmakerandroid.Interfaces.Callback_FN_TimePicker_Listener;
import com.example.nativeformmakerandroid.adapter.FN_FormNative_ViewPagerAdaptor;
import com.example.nativeformmakerandroid.fragment.FileListFragment;
import com.example.nativeformmakerandroid.fragment.OptionDialogFragment;
import com.example.nativeformmakerandroid.helper.FontHelper;
/*
import com.example.nativeformmakerandroid.helper.LanguageHelper;
*/
import com.example.nativeformmakerandroid.helper.SnackarHelperByColor;
import com.example.nativeformmakerandroid.helper.ToastHelper;
/*
import com.example.nativeformmakerandroid.helper.UrlHelper;
*/
import com.example.nativeformmakerandroid.helper.Utils;
import com.example.nativeformmakerandroid.model.NativeForm.EnumDataStatusInApp;
import com.example.nativeformmakerandroid.model.NativeForm.FileModel;
import com.example.nativeformmakerandroid.model.NativeForm.FileModelJavaScript;
import com.example.nativeformmakerandroid.model.NativeForm.FormHandleTemplate;
import com.example.nativeformmakerandroid.model.NativeForm.LocDbModel;
import com.example.nativeformmakerandroid.model.NativeForm.NF_A_TemplateModel;
import com.example.nativeformmakerandroid.model.NativeForm.NF_E_Tab_Forms_Form_Field;
import com.example.nativeformmakerandroid.model.NativeForm.NF_F_Tab_Forms_Form_Field_Items;
import com.example.nativeformmakerandroid.observe.GPSObservable;
import com.example.nativeformmakerandroid.views.timePicker.CustomTimePickerCallback;

import static com.example.nativeformmakerandroid.model.NativeForm.NF_FieldTypeEnum.CHECKBOX_LIST_FIELD;
import static com.example.nativeformmakerandroid.model.NativeForm.NF_FieldTypeEnum.DATE_FIELD;
import static com.example.nativeformmakerandroid.model.NativeForm.NF_FieldTypeEnum.FILE_FIELD;
import static com.example.nativeformmakerandroid.model.NativeForm.NF_FieldTypeEnum.NUMBER_FIELD;
import static com.example.nativeformmakerandroid.model.NativeForm.NF_FieldTypeEnum.RADIO_BUTTON_LIST_FIELD;
import static com.example.nativeformmakerandroid.model.NativeForm.NF_FieldTypeEnum.SELECT_FIELD;
import static com.example.nativeformmakerandroid.model.NativeForm.NF_FieldTypeEnum.TEXTAREA_FIELD;
import static com.example.nativeformmakerandroid.model.NativeForm.NF_FieldTypeEnum.TEXT_FIELD;

public class ActivityFormNative extends TenthActivity implements FileListFragment.OnFileChangeListener, OptionDialogFragment.OnItemClickListener, Observer, CallBack_FN_Model_Listener, CustomTimePickerCallback {
    public static final int REQUEST_SELECT_FILE = 100;
    private final static int FILECHOOSER_RESULTCODE = 1;
    private final static int ANDROID_FILE_CHOOSER_CODE = 213;
    private final static int ANDROID_SIGNATURE = 211;
    private static final int REQUEST_CAPTURE_IMAGE = 100;
    private static final int REQUEST_CAPTURE_IMAGE_IN_FORM = 101;
    private static final String PRE_ID_NAME = "MvcDynamicField_";
    private static final String FILE_TYPE = "file";
    private static final String SEPERATOR = "__--__";
    int whcichTab = -1;
    ViewPager viewPager;

    Callback_FN_TimePicker_Listener timePickerCallback;

    String TAG = "ActivityBazrasi";
    LocDbModel newLocationObserver = null;
    Uri uri;
    FileListFragment fileListFragment;
    View close, submit, loading;
    //    FloatingActionButton attachment;
    ArrayList<FileModelJavaScript> FileModelsNativeForm = new ArrayList<>();
    FileModelJavaScript currentFile;
    int data_status_in_app;
    List<String> libraryKey, libraryValue;
    String template, savedData, entering_date;
    WebView webView;
    public static String u_id = null;
    String Customer_Name, Customer_Address, Customer_tell;
    int temp_id;
    boolean editable;

    String imageFilePath;
    boolean libraryAded = false;
    private boolean exiting = false;
    String TagMain = "templateRes";
    private NF_A_TemplateModel nf_a_templateModel = null;
    TabLayout tabLayout;
    List<FormModel> NativeFormDataForServer;

    int OnActivityResultNativeFormImageNumber;
    String OnActivityResultNativeFormImageId = null;
    String NativeFormImageValue = null;
    //int raw_and_file;
    int WRITE_EXST = 111;
    String template_in_json;

    /**
     * some times when coming back from this activity language changes (because of webview in android 7) so L corrected the language
     */
    public void setCurrentlanguageInLocal() {
        Context context = MyApplication.getInstance();

        LogHelper.d("LanguageSettings7", "setCurrentLanguageInLocal");

        //   String language = SharedPreferencesHelper.GetString(context, LanguageSettings.PARRENT_LOG, LanguageSettings.lANGUAGE_VALUE, LanguageSettings.FARSI_FA);

        //  LanguageHelper.setLanguage(context, language);

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

        overridePendingTransition(R.anim.slide_in_bottom, R.anim.slide_nowhere);
        GPSObservable.getInstance().addObserver(this);
        askForPermission(Manifest.permission.READ_EXTERNAL_STORAGE, WRITE_EXST);

    }

    @Override
    public void setUpElements() {

        close = findViewById(R.id.activity_form_close);
        submit = findViewById(R.id.activity_form_done);
        loading = findViewById(R.id.progressBar1);

        // getextra
        temp_id = getIntent().getIntExtra(ConstString.JOB_TEMPLATE_ID, -1);
        u_id = getIntent().getStringExtra(ConstString.JOB_DATA_U_ID);
        editable = getIntent().getBooleanExtra(ConstString.FORM_EDITABLE, true);
        Customer_Name = getIntent().getStringExtra(ConstString.JOB_DATA_CUSTOMER_NAME);
        Customer_Address = getIntent().getStringExtra(ConstString.JOB_DATA_CUSTOMER_ADDRESS);
        Customer_tell = getIntent().getStringExtra(ConstString.JOB_DATA_CUSTOMER_TELL);
        entering_date = getIntent().getStringExtra(ConstString.FORM_ENTERING_TIME);

        template = getIntent().getStringExtra(ConstString.FORM_TEMPLATE_IN_JSON_FORMAT);

        savedData = getIntent().getStringExtra(ConstString.JOB_TEMPLATE_SAVED_DATA);
        data_status_in_app = getIntent().getIntExtra(ConstString.STATUS_IN_APP, EnumDataStatusInApp.STATUS_NOT_SET_YET.getIntValue());

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
                saveTemporary();
            }
        });

        if (editable) {
            submit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // forceFinish();
                    if (validateNative()) {
                        Gson g = new Gson();
                        String str = g.toJson(NativeFormDataForServer);
                        LogHelper.i("onCallBack  >>>>> NativeFormDataForServer setUpOnClicks >>>>>>> ", str);

                        submitNative(str);
                    } else {
                        SnackarHelperByColor.show(MyApplication.getInstance().activity.findViewById(android.R.id.content),
                                getString(R.string.form_mandatory_error), MyApplication.getInstance().getString(R.string.ok),
                                MyApplication.getInstance().getResources().getColor(R.color.red_text), MyApplication.getInstance().getResources().getColor(R.color.white), new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        SnackarHelperByColor.dismiss();
                                    }
                                });
                    }
                }
            });
        } else {
            submit.setVisibility(View.GONE);
        }


    }

    void setupFileAndValueFormNative() {
        LogHelper.e("SAVED DATA:", savedData);
        if (savedData != null) {
            List<FormModel> formModels = new Gson().fromJson(savedData, new TypeToken<List<FormModel>>() {
            }.getType());
            if (formModels != null) {
                if (nf_a_templateModel != null)
                    for (int i = 0; i < nf_a_templateModel.Tabs.size(); i++) {
                        //  LogHelper.v("onCallBack 1", " Id:" + nf_a_templateModel.Tabs.get(i).Id + " Names:" + nf_a_templateModel.Tabs.get(i).Name);
                        for (int j = 0; j < nf_a_templateModel.Tabs.get(i).Forms.size(); j++) {
                            //  LogHelper.v("onCallBack 2","Id:"+nf_a_templateModel.Tabs.get(i).Forms.get(j)+ " Names:"+ nf_a_templateModel.Tabs.get(i).Name);
                            for (int k = 0; k < formModels.size(); k++) {

                                if (nf_a_templateModel.Tabs.get(i).Forms.get(j).Form.Id == Integer.valueOf(formModels.get(k).FormId)) {
                                    FormModel last = null;
                                    if (k > 0) {
                                        last = formModels.get(k - 1);
                                    }
                                    if (k > 0 && last.FormId.equalsIgnoreCase(formModels.get(k).FormId)) {
                                        nf_a_templateModel.Tabs.get(i).Forms.add(nf_a_templateModel.Tabs.get(i).Forms.get(j).getAnotherCopy());
                                        j++;
//                                        nf_a_templateModel.Tabs.get(i).Forms.get(j) =
                                    }
                                    for (int l = 0; l < nf_a_templateModel.Tabs.get(i).Forms.get(j).Form.Fields.size(); l++) {
                                        NF_E_Tab_Forms_Form_Field formFieldNative = nf_a_templateModel.Tabs.get(i).Forms.get(j).Form.Fields.get(l);
                                        for (int h = 0; h < formModels.get(k).Data.size(); h++) {
                                            String formNativeFieldId = null;
                                            FormDataModel formDataModel = formModels.get(k).Data.get(h);
                                            if (formFieldNative.type == TEXTAREA_FIELD ||
                                                    formFieldNative.type == TEXT_FIELD ||
                                                    formFieldNative.type == NUMBER_FIELD) {
                                                formNativeFieldId = PRE_ID_NAME + formFieldNative.name;
                                                if (formNativeFieldId.equalsIgnoreCase(formDataModel.Id)) {
                                                    formFieldNative.value = formDataModel.Value;
                                                    break;
                                                }
                                            } else if (formFieldNative.type == CHECKBOX_LIST_FIELD) {
                                                for (int t = 0; t < formFieldNative.Items.size(); t++) {
                                                    NF_F_Tab_Forms_Form_Field_Items item = formFieldNative.Items.get(t);
                                                    formNativeFieldId = PRE_ID_NAME + formFieldNative.name + SEPERATOR + item.Id;
                                                    if (formNativeFieldId.equalsIgnoreCase(formDataModel.Id)) {
                                                        item.selected = Boolean.valueOf(formDataModel.Value);
                                                        break;
                                                    }
                                                }
                                            } else if (formFieldNative.type == RADIO_BUTTON_LIST_FIELD) {
                                                formNativeFieldId = PRE_ID_NAME + formFieldNative.name;
                                                if (formNativeFieldId.equalsIgnoreCase(formDataModel.Id)) {
                                                    for (int t = 0; t < formFieldNative.Items.size(); t++) {
                                                        if (formFieldNative.Items.get(t).value.equalsIgnoreCase(formDataModel.Value)) {
                                                            formFieldNative.Items.get(t).selected = true;
                                                            break;
                                                        }
                                                    }
                                                }

                                            } else if (formFieldNative.type == DATE_FIELD) {
                                                formNativeFieldId = PRE_ID_NAME + formFieldNative.name;
                                                if (formNativeFieldId.equalsIgnoreCase(formDataModel.Id)) {
                                                    formFieldNative.value = formDataModel.Value;
                                                    break;
                                                }

                                            } else if (formFieldNative.type == SELECT_FIELD) {
                                                formNativeFieldId = PRE_ID_NAME + formFieldNative.name;
                                                if (formNativeFieldId.equalsIgnoreCase(formDataModel.Id)) {
                                                    formFieldNative.value = formDataModel.Value;
                                                    for (int t = 0; t < formFieldNative.Items.size(); t++) {
                                                        NF_F_Tab_Forms_Form_Field_Items item = formFieldNative.Items.get(t);
                                                        if (item.value.equalsIgnoreCase(formDataModel.Value)) {
                                                            item.selected = true;
                                                        } else {
                                                            item.selected = false;
                                                        }
                                                    }
                                                    break;
                                                }
                                            } else if (formFieldNative.type == FILE_FIELD) {
                                                // todo
                                               /* formNativeFieldId = PRE_ID_NAME + formFieldNative.name;
                                                if (formNativeFieldId.equalsIgnoreCase(formDataModel.Id)) {
                                                    List<FileModel> fileModel = FileModel.getFileModelsByTitle(formDataModel.Value, u_id);
                                                    FileModelJavaScript fileModelJavaScript = new FileModelJavaScript();
                                                    fileModelJavaScript.InputName = PRE_ID_NAME + formFieldNative.name;
                                                    fileModelJavaScript.Uid = u_id;
                                                    fileModelJavaScript.status = FileModel.FileStatus.SAVED.getIntValue();
                                                    fileModelJavaScript.Multiple = formFieldNative.UseMultiple;
                                                    fileModelJavaScript.Title = formDataModel.Value;
                                                    if (formFieldNative.fileModels == null) {
                                                        formFieldNative.fileModels = new ArrayList<>();
                                                    }
                                                    boolean exist = false;
                                                    if (fileModel == null || fileModel.size() == 0) {
                                                        formFieldNative.fileModels = new ArrayList<>();
                                                    } else {
                                                        for (FileModelJavaScript current : formFieldNative.fileModels) {
                                                            if (current.Title.equalsIgnoreCase(formDataModel.Value)) {
                                                                exist = true;
                                                            }
                                                        }
                                                        fileModelJavaScript.filePaths = new ArrayList<>();
                                                        fileModelJavaScript.filePaths.add(fileModel.get(0).filePath);
                                                    }
                                                    if (!exist)
                                                        formFieldNative.fileModels.add(fileModelJavaScript);
                                                }*/
                                            }
                                        }


                                    }
                                }
                            }


                        }

                    }
            }
        }
        LogHelper.d("callbackcallback", new Gson().toJson(nf_a_templateModel.Tabs.get(0)));
    }

    public void loadDataOnResume(int whcichTab) {
        //String templateRes = FormHandleTemplate.getTemplatesContentById(getBaseContext(), Integer.valueOf(temp_id));// todo : make pop up
       // String templateRes = FormHandleTemplate.getTemplatesContent(getBaseContext(), raw_and_file);// todo : make pop up
        String templateRes = template;

        LogHelper.e(TagMain, template);


        Gson gson = new Gson();
        try {
            //  nf_a_templateModel = gson.fromJson(templateRes, NF_A_TemplateModel.class);
            LogHelper.e(TagMain, "  templateRes :" + templateRes);

            if (nf_a_templateModel != null)
                for (int i = 0; i < nf_a_templateModel.Tabs.size(); i++) {
                    LogHelper.e(TagMain, " Field name :" + String.valueOf(i) + nf_a_templateModel.Tabs.get(0).Forms.get(0).Form.Fields.get(0).name);
                    LogHelper.e(TagMain, " filed ResponseTitle:" + String.valueOf(i) + nf_a_templateModel.Tabs.get(0).Forms.get(0).Form.Fields.get(0).ResponseTitle);
                }
        } catch (Exception e) {
            e.getStackTrace();
        }

        if (nf_a_templateModel != null) {

            /** add data to forms  */
            setupFileAndValueFormNative();
            onCallBack(null, null);
            // Get the ViewPager and set it's PagerAdapter so that it can display items
            viewPager = (ViewPager) findViewById(R.id.viewpager_fn);
            viewPager.setAdapter(new FN_FormNative_ViewPagerAdaptor(nf_a_templateModel.Tabs, getSupportFragmentManager(), nf_a_templateModel.Tabs.size(), editable,
                    getBaseContext()));

            // Give the TabLayout the ViewPager
            tabLayout = (TabLayout) findViewById(R.id.sliding_tabs_fn);
            tabLayout.setupWithViewPager(viewPager);
            FontHelper.overrideFonts(this, tabLayout);
            if (nf_a_templateModel.Tabs.size() != 0) {
                if (whcichTab != -1 && nf_a_templateModel.Tabs.size() > 1)
                    viewPager.setCurrentItem(whcichTab);
                else viewPager.setCurrentItem(0);

            }

        }
        loading.setVisibility(View.GONE);

    }

    @Override
    public void loadData() {
        // String templateRes = FormHandleTemplate.getTemplatesContentById(getBaseContext(), Integer.valueOf(temp_id));// todo : make pop up
      //  String templateRes = FormHandleTemplate.getTemplatesContent(getBaseContext(), raw_and_file);// todo : make pop up
        String templateRes = template;

        LogHelper.e(TagMain, templateRes);


        Gson gson = new Gson();
        try {
            nf_a_templateModel = gson.fromJson(templateRes, NF_A_TemplateModel.class);
            LogHelper.e(TagMain, "  templateRes :" + templateRes);

            if (nf_a_templateModel != null)
                for (int i = 0; i < nf_a_templateModel.Tabs.size(); i++) {
                    LogHelper.e(TagMain, " Field name :" + String.valueOf(i) + nf_a_templateModel.Tabs.get(0).Forms.get(0).Form.Fields.get(0).name);
                    LogHelper.e(TagMain, " filed ResponseTitle:" + String.valueOf(i) + nf_a_templateModel.Tabs.get(0).Forms.get(0).Form.Fields.get(0).ResponseTitle);
                }
        } catch (Exception e) {
            e.getStackTrace();
        }

        if (nf_a_templateModel != null) {

            // Get the ViewPager and set it's PagerAdapter so that it can display items
            viewPager = (ViewPager) findViewById(R.id.viewpager_fn);
            viewPager.setAdapter(new FN_FormNative_ViewPagerAdaptor(nf_a_templateModel.Tabs, getSupportFragmentManager(), nf_a_templateModel.Tabs.size(), editable,
                    getBaseContext()));

            // Give the TabLayout the ViewPager
            tabLayout = (TabLayout) findViewById(R.id.sliding_tabs_fn);
            tabLayout.setupWithViewPager(viewPager);

            if (nf_a_templateModel.Tabs.size() != 0) {
                viewPager.setCurrentItem(0);
            }

        }

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

    private void askForPermission(String permission, Integer requestCode) {
        if (ContextCompat.checkSelfPermission(ActivityFormNative.this, permission) != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(ActivityFormNative.this, permission)) {

                //This is called if user has denied the permission before
                //In this case I am just asking the permission again
                ActivityCompat.requestPermissions(ActivityFormNative.this, new String[]{permission}, requestCode);

            } else {

                ActivityCompat.requestPermissions(ActivityFormNative.this, new String[]{permission}, requestCode);
            }
        } else {
          //  Toast.makeText(this, "" + permission + " is already granted.", Toast.LENGTH_SHORT).show();
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
                pictureIntent.putExtra(MediaStore.EXTRA_OUTPUT,
                        photoURI);
                startActivityForResult(pictureIntent,
                        REQUEST_CAPTURE_IMAGE);
            }
        }
    }

    private void openCameraIntentForForm(int image_number, String Id, String Value) {


        Intent pictureIntent = new Intent(
                MediaStore.ACTION_IMAGE_CAPTURE);
        if (pictureIntent.resolveActivity(getPackageManager()) != null) {
            //Create a file to store the image
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
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
                OnActivityResultNativeFormImageNumber = image_number;
                OnActivityResultNativeFormImageId = Id;
                NativeFormImageValue = Value;

                //  Uri photoURI = FileProvider.getUriForFile(this, BuildConfig.APPLICATION_ID + ".provider", photoFile);
                pictureIntent.putExtra(MediaStore.EXTRA_OUTPUT,
                        photoURI);
                startActivityForResult(pictureIntent,
                        REQUEST_CAPTURE_IMAGE_IN_FORM);
            }
        }
    }

    @Override
    protected void onResume() {
        loadDataOnResume(whcichTab);
        super.onResume();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {

        if (resultCode != RESULT_OK)
            return;
        LogHelper.e("CameraCamera", "--------");

        if (requestCode == REQUEST_CAPTURE_IMAGE_IN_FORM) {
            try {
                if (nf_a_templateModel != null) {
                    boolean exist1 = false;
                    for (int i = 0; i < nf_a_templateModel.Tabs.size(); i++) {
                        //  LogHelper.v("onCallBack 1", " Id:" + nf_a_templateModel.Tabs.get(i).Id + " Names:" + nf_a_templateModel.Tabs.get(i).Name);
                        boolean exist2 = false;
                        for (int j = 0; j < nf_a_templateModel.Tabs.get(i).Forms.size(); j++) {
                            //  LogHelper.v("onCallBack 2","Id:"+nf_a_templateModel.Tabs.get(i).Forms.get(j)+ " Names:"+ nf_a_templateModel.Tabs.get(i).Name);
                            for (int k = 0; k < nf_a_templateModel.Tabs.get(i).Forms.get(j).Form.Fields.size(); k++) {
                                if (String.valueOf(nf_a_templateModel.Tabs.get(i).Forms.get(j).Form.Fields.get(k).Id).equalsIgnoreCase(OnActivityResultNativeFormImageId)) {
                                    if (nf_a_templateModel.Tabs.get(i).Forms.get(j).Form.Fields.get(k).fileModels == null) {
                                        nf_a_templateModel.Tabs.get(i).Forms.get(j).Form.Fields.get(k).fileModels = new ArrayList<>();
                                    }
                                    FileModelJavaScript g = new FileModelJavaScript();
                                    nf_a_templateModel.Tabs.get(i).Forms.get(j).Form.Fields.get(k).fileModels.add(OnActivityResultNativeFormImageNumber, g);
                                    this.FileModelsNativeForm.add(g);
                                    if (g.filePaths == null) {
                                        g.filePaths = new ArrayList<>();
                                    }
                                    g.filePaths.add(imageFilePath);
                                    g.InputName = PRE_ID_NAME + nf_a_templateModel.Tabs.get(i).Forms.get(j).Form.Fields.get(k).name;
                                    g.Uid = u_id;
                                    g.status = FileModel.FileStatus.SAVED.getIntValue();
                                    g.Multiple = nf_a_templateModel.Tabs.get(i).Forms.get(j).Form.Fields.get(k).UseMultiple;
                                    String[] splitted = imageFilePath.split("\\.");
                                    g.Title = u_id + g.InputName + nf_a_templateModel.Tabs.get(i).Forms.get(j).Form.Fields.get(k).fileModels.size() + "." + splitted[splitted.length - 1];
                                    exist1 = true;
                                    exist2 = true;
                                    break;
                                }
                            }
                            if (exist2) {
                                break;
                            }
                        }
                        if (exist1) {
                            break;
                        }
                    }
                }

                onCallBack(OnActivityResultNativeFormImageId, NativeFormImageValue);


                LogHelper.e("CameraCamera : ", imageFilePath);

            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (requestCode == REQUEST_CAPTURE_IMAGE) {
            //don't compare the data to null, it will always come as  null because we are providing a file URI, so load with the imageFilePath we obtained before opening the cameraIntent
            // Glide.with(this).load(imageFilePath).into(mImageView);
            // If you are using Glide.

            try {
                if (currentFile.filePaths == null)
                    currentFile.filePaths = new ArrayList<>();
//                    currentFile.filePaths.add(getFilePath(this, result));
                currentFile.filePaths.add(imageFilePath);

                fileListFragment = FileListFragment.newInstance(FileModelsNativeForm, editable);
                fileListFragment.show(getSupportFragmentManager(), FileListFragment.TAG);

            } catch (Exception e) {
                e.printStackTrace();
            }
            //   }
        } else if (requestCode == ANDROID_SIGNATURE) {
//            String result = intent == null || resultCode != ActivityMain.RESULT_OK ? null : intent.getStringExtra(ActivitySignatureForResult.FILE_PATH);
//            if (result != null) {
//                try {
//                    if (currentFile.filePaths == null)
//                        currentFile.filePaths = new ArrayList<>();
//                    currentFile.filePaths.add(result);
//                    fileListFragment = FileListFragment.newInstance(FileModelsNativeForm, editable);
//                    fileListFragment.show(getSupportFragmentManager(), FileListFragment.TAG);
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
        } else
            Toast.makeText(getApplicationContext(), "Failed to Upload Image", Toast.LENGTH_LONG).show();
    }

    @Override
    public void requestFile(FileModelJavaScript fileModelJavaScript) {
        if (fileModelJavaScript.Multiple || fileModelJavaScript.filePaths == null || fileModelJavaScript.filePaths.size() < 1) {

            try {
                fileListFragment.dismiss();
            } catch (Exception e) {
                e.printStackTrace();
            }
            currentFile = fileModelJavaScript;
            ArrayList<String> strings = new ArrayList<>();
            strings.add(getString(R.string.camera_form));
            //  strings.add(getString(R.string.singature_form));
            OptionDialogFragment optionDialogFragment = OptionDialogFragment.newInstance(getString(R.string.doc_type), strings);
            optionDialogFragment.show(getSupportFragmentManager(), OptionDialogFragment.TAG);
        } else {
            ToastHelper.Show(getString(R.string.only_one_file_is_allowed), this);
        }
    }

    @Override
    public void removeFile(FileModelJavaScript fileModelJavaScript) {

    }

    @Override
    public void showFile(String fileModelJavaScript) {
        MimeTypeMap myMime = MimeTypeMap.getSingleton();
        if (!fileModelJavaScript.contains("http") && !fileModelJavaScript.contains("HTTP")) {
            Intent newIntent = new Intent(Intent.ACTION_VIEW);
            String mimeType = myMime.getMimeTypeFromExtension(fileExt(fileModelJavaScript).substring(1));
            newIntent.setDataAndType(FileProvider.getUriForFile(this, getApplicationContext().getPackageName() + ".provider", new File(fileModelJavaScript)), mimeType);
            newIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            try {
                List<ResolveInfo> resInfoList = getPackageManager().queryIntentActivities(newIntent, PackageManager.MATCH_DEFAULT_ONLY);
                for (ResolveInfo resolveInfo : resInfoList) {
                    String packageName = resolveInfo.activityInfo.packageName;
                    grantUriPermission(packageName, FileProvider.getUriForFile(this, getApplicationContext().getPackageName() + ".provider", new File(fileModelJavaScript)), Intent.FLAG_GRANT_WRITE_URI_PERMISSION | Intent.FLAG_GRANT_READ_URI_PERMISSION);
                }
                startActivity(newIntent);
            } catch (ActivityNotFoundException e) {
                Toast.makeText(this, getString(R.string.No_handler_for_this_type_of_file), Toast.LENGTH_LONG).show();
            }
        } else {
            // UrlHelper.openUrl(fileModelJavaScript, this);
        }
    }

    private String fileExt(String url) {
        if (url.indexOf("?") > -1) {
            url = url.substring(0, url.indexOf("?"));
        }
        if (url.lastIndexOf(".") == -1) {
            return null;
        } else {
            String ext = url.substring(url.lastIndexOf(".") + 1);
            if (ext.indexOf("%") > -1) {
                ext = ext.substring(0, ext.indexOf("%"));
            }
            if (ext.indexOf("/") > -1) {
                ext = ext.substring(0, ext.indexOf("/"));
            }
            return ext.toLowerCase();

        }
    }

    private File createImageFile() throws IOException {
        String timeStamp =
                new SimpleDateFormat("yyyyMMdd_HHmmss",
                        Locale.getDefault()).format(new Date());
        String imageFileName = "IMG_" + timeStamp + "_";
        File storageDir =
                getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        imageFilePath = image.getAbsolutePath();
        return image;
    }

    @Override
    protected void onPause() {
        // setCurrentlanguageInLocal();
        LogHelper.d("javascriptmbr 7>>", "onPause");

        if (viewPager != null)
            whcichTab = viewPager.getCurrentItem();
        else whcichTab = -1;

        exiting = false;
        super.onPause();
    }

    @Override
    public void onBackPressed() {
        saveTemporary();
    }

    private void saveTemporary() {
        String finalData = new Gson().toJson(NativeFormDataForServer);//submitData2(data);
        LogHelper.e("javascriptmbr 4>", "submit : " + finalData + "");
        Intent intent = new Intent();
        intent.putExtra(ConstString.JOB_TEMPLATE_SAVED_DATA, finalData);
        intent.putExtra(ConstString.JOB_TEMPLATE_SAVED_SUBMITTED, false);
        intent.putExtra(ConstString.JOB_DATA_U_ID, u_id);
        intent.putExtra(ConstString.JOB_TEMPLATE_ID, temp_id);
        intent.putExtra(ConstString.JOB_DATA_CUSTOMER_NAME, Customer_Name);
        intent.putExtra(ConstString.JOB_DATA_CUSTOMER_ADDRESS, Customer_Address);
        intent.putExtra(ConstString.JOB_DATA_CUSTOMER_TELL, Customer_tell);
        intent.putExtra(ConstString.FORM_ENTERING_TIME, entering_date);
        intent.putExtra(ConstString.FORM_EDITABLE, editable);
        intent.putExtra(ConstString.STATUS_IN_APP, data_status_in_app);
        if (newLocationObserver != null) {
            intent.putExtra(ConstString.LOC_LAT, String.valueOf(newLocationObserver.Lat));
            intent.putExtra(ConstString.LOC_LNG, String.valueOf(newLocationObserver.Lng));
            intent.putExtra(ConstString.LOC_INSERT_DATE, String.valueOf(newLocationObserver.Entry_Date));
        } else {
            intent.putExtra(ConstString.LOC_LAT, "");
            intent.putExtra(ConstString.LOC_LNG, "");
            intent.putExtra(ConstString.LOC_INSERT_DATE, "");
        }

        intent.putParcelableArrayListExtra(ConstString.JOB_TEMPLATE_SAVED_FILE_LIST, FileModelsNativeForm);
//                    intent.putStringArrayListExtra(com.example.nativeformmakerandroid.helper.ConstString.JOB_TEMPLATE_SAVED_FILE_KEY_LIST, fileKeyList);
        setResult(RESULT_OK, intent);
        webView = null;
        finish();
    }


    @Override
    public void update(Observable observable, Object o) {
        try {
            LogHelper.d(TAG, "1");

            if (o instanceof Location) {
                Location location = (Location) o;

                LogHelper.d(TAG, "2");
                if (location != null) {
                    String date = String.valueOf(Utils.CurrectDateToServerDate(new Date(), getBaseContext(), ""));

                    newLocationObserver = new LocDbModel();
                    newLocationObserver.Lng = String.valueOf(location.getLongitude());
                    newLocationObserver.Lat = String.valueOf(location.getLatitude());
                    newLocationObserver.Entry_Date = date;
                    LogHelper.d(TAG, "3 date : " + date + " accu : " + location.getAccuracy() + "lat : " + location.getLatitude() + ", lng : " + location.getLongitude());
                    // LatLng  myLocationLatLng = new LatLng(location.getLatitude(), location.getLongitude());

                }
                LogHelper.d(TAG, "4");

            }
            if (o instanceof Boolean) {
                //   mySwitch.setChecked((Boolean) o);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public void onCallBack(String Id, String Value) {
        LogHelper.e("FILES SIZE:", this.FileModelsNativeForm == null ? "null" : String.valueOf(this.FileModelsNativeForm.size()));
        NativeFormDataForServer = new ArrayList<>();
        this.FileModelsNativeForm = new ArrayList<>();
        if (nf_a_templateModel != null) {
            for (int i = 0; i < nf_a_templateModel.Tabs.size(); i++) {
                //  LogHelper.v("onCallBack 1", " Id:" + nf_a_templateModel.Tabs.get(i).Id + " Names:" + nf_a_templateModel.Tabs.get(i).Name);
                for (int j = 0; j < nf_a_templateModel.Tabs.get(i).Forms.size(); j++) {
                    //  LogHelper.v("onCallBack 2","Id:"+nf_a_templateModel.Tabs.get(i).Forms.get(j)+ " Names:"+ nf_a_templateModel.Tabs.get(i).Name);
                    FormModel filemopdel = new FormModel();
                    filemopdel.Data = new ArrayList<>();
                    filemopdel.FormId = String.valueOf(nf_a_templateModel.Tabs.get(i).Forms.get(j).Form.Id);
                    for (int k = 0; k < nf_a_templateModel.Tabs.get(i).Forms.get(j).Form.Fields.size(); k++) {
                        FormDataModel fdm = null;
                        NF_E_Tab_Forms_Form_Field formNativeField = nf_a_templateModel.Tabs.get(i).Forms.get(j).Form.Fields.get(k);
                        if (formNativeField.type == TEXT_FIELD ||
                                formNativeField.type == TEXTAREA_FIELD ||
                                formNativeField.type == NUMBER_FIELD) {
                            fdm = new FormDataModel();
                            fdm.Id = PRE_ID_NAME + formNativeField.name;
                            fdm.Value = formNativeField.value;
                            filemopdel.Data.add(fdm);
                        } else if (formNativeField.type == CHECKBOX_LIST_FIELD) {
                            for (int t = 0; t < formNativeField.Items.size(); t++) {
                                NF_F_Tab_Forms_Form_Field_Items item = formNativeField.Items.get(t);
                                fdm = new FormDataModel();
                                fdm.Id = PRE_ID_NAME + formNativeField.name + SEPERATOR + item.Id;
                                fdm.Value = String.valueOf(item.selected);
                                filemopdel.Data.add(fdm);
                            }
                        } else if (formNativeField.type == RADIO_BUTTON_LIST_FIELD) {
                            fdm = new FormDataModel();
                            fdm.Id = PRE_ID_NAME + formNativeField.name;
                            fdm.Value = formNativeField.value;
                            filemopdel.Data.add(fdm);

                        } else if (formNativeField.type == DATE_FIELD) {
                            fdm = new FormDataModel();
                            fdm.Id = PRE_ID_NAME + formNativeField.name;
                            fdm.Value = formNativeField.value;
                            filemopdel.Data.add(fdm);

                        } else if (formNativeField.type == SELECT_FIELD) {
                            fdm = new FormDataModel();
                            fdm.Id = PRE_ID_NAME + formNativeField.name;
                            for (int t = 0; t < formNativeField.Items.size(); t++) {
                                NF_F_Tab_Forms_Form_Field_Items item = formNativeField.Items.get(t);
                                if (item.selected) {
                                    fdm.Value = item.value;
                                    break;
                                }
                            }
                            filemopdel.Data.add(fdm);
                        } else if (formNativeField.type == FILE_FIELD) {
                            if (formNativeField.fileModels != null && formNativeField.fileModels.size() > 0) {
                                for (int g = 0; g < formNativeField.fileModels.size(); g++) {
                                    this.FileModelsNativeForm.add(formNativeField.fileModels.get(g));
                                    fdm = new FormDataModel();
                                    fdm.Id = PRE_ID_NAME + formNativeField.name;
                                    fdm.Value = formNativeField.fileModels.get(g).Title;
                                    filemopdel.Data.add(fdm);
                                }
                            }
                        }

                    }
                    if (filemopdel != null && filemopdel.Data != null && filemopdel.Data.size() != 0)
                        NativeFormDataForServer.add(filemopdel);

                }

            }
        }
        LogHelper.e("FORM RESULT: ", new Gson().toJson(NativeFormDataForServer));

    }

    @Override
    public void onCameraOpen(int image_number, String Id, String Value) {
        openCameraIntentForForm(image_number, Id, Value);
    }

    public boolean validateNative() {
        boolean isValid = true;

        if (nf_a_templateModel != null) {
            for (int i = 0; i < nf_a_templateModel.Tabs.size(); i++) {

                for (int j = 0; j < nf_a_templateModel.Tabs.get(i).Forms.size(); j++) {

                    for (int k = 0; k < nf_a_templateModel.Tabs.get(i).Forms.get(j).Form.Fields.size(); k++) {

                        NF_E_Tab_Forms_Form_Field formNativeField = nf_a_templateModel.Tabs.get(i).Forms.get(j).Form.Fields.get(k);
                        if (formNativeField.type == TEXT_FIELD ||
                                formNativeField.type == TEXTAREA_FIELD ||
                                formNativeField.type == NUMBER_FIELD) {

                            if (formNativeField.RegEx != null && !formNativeField.RegEx.isEmpty()) {
                                try {
                                    Pattern sPattern = Pattern.compile(formNativeField.RegEx);

                                    if (!sPattern.matcher(formNativeField.value).matches()) {
                                        isValid = false;
                                    }
                                } catch (Exception e) {
                                    e.getStackTrace();
                                }
                            }

                            if (formNativeField.required && formNativeField.value.isEmpty()) {
                                isValid = false;
                            }

                        } else if (formNativeField.type == DATE_FIELD) {
                            if (formNativeField.required && formNativeField.value.isEmpty()) {
                                isValid = false;
                            }
                        } else if (formNativeField.type == SELECT_FIELD) {
                            if (formNativeField.required && formNativeField.value.isEmpty()) {
                                isValid = false;
                            }
                        } else if (formNativeField.type == FILE_FIELD) {
                            if (formNativeField.required && (formNativeField.fileModels == null || formNativeField.fileModels.isEmpty())) {
                                isValid = false;
                            }
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
                intent.putExtra(ConstString.JOB_TEMPLATE_SAVED_DATA, finalData);
                intent.putExtra(ConstString.JOB_TEMPLATE_SAVED_SUBMITTED, true);
                intent.putExtra(ConstString.JOB_DATA_U_ID, u_id);
                intent.putExtra(ConstString.JOB_TEMPLATE_ID, temp_id);
                intent.putExtra(ConstString.JOB_DATA_CUSTOMER_NAME, Customer_Name);
                intent.putExtra(ConstString.JOB_DATA_CUSTOMER_ADDRESS, Customer_Address);
                intent.putExtra(ConstString.JOB_DATA_CUSTOMER_TELL, Customer_tell);
                intent.putExtra(ConstString.FORM_ENTERING_TIME, entering_date);
                intent.putExtra(ConstString.FORM_EDITABLE, editable);
                intent.putExtra(ConstString.STATUS_IN_APP, data_status_in_app);
                if (newLocationObserver != null) {
                    intent.putExtra(ConstString.LOC_LAT, String.valueOf(newLocationObserver.Lat));
                    intent.putExtra(ConstString.LOC_LNG, String.valueOf(newLocationObserver.Lng));
                    intent.putExtra(ConstString.LOC_INSERT_DATE, String.valueOf(newLocationObserver.Entry_Date));
                } else {
                    intent.putExtra(ConstString.LOC_LAT, "");
                    intent.putExtra(ConstString.LOC_LNG, "");
                    intent.putExtra(ConstString.LOC_INSERT_DATE, "");
                }

                intent.putParcelableArrayListExtra(ConstString.JOB_TEMPLATE_SAVED_FILE_LIST, FileModelsNativeForm);
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

    public void setTimePickerCallback(Callback_FN_TimePicker_Listener timePickerCallback) {
        this.timePickerCallback = timePickerCallback;
    }

    private class FormModel {
        public String FormId;
        public List<FormDataModel> Data;

    }

    private class FormDataModel {
        public String Id;
        public String Value;
        public String fieldId;

    }
}

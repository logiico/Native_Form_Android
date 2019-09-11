package net.logiico.formnativeandroidjava.adapter;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Build;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import net.logiico.formnativeandroidjava.MyApplication;
import net.logiico.formnativeandroidjava.R;
import net.logiico.formnativeandroidjava.activity.FormNativeActivity;
import net.logiico.formnativeandroidjava.callback.NativeTemplateItemCallBack;
import net.logiico.formnativeandroidjava.callback.TimePickerCallback;
import net.logiico.formnativeandroidjava.customView.PersianEditText;
import net.logiico.formnativeandroidjava.customView.PersianTextView;
import net.logiico.formnativeandroidjava.fragment.CustomTimePickerDialog;
import net.logiico.formnativeandroidjava.helper.ConstHelper;
import net.logiico.formnativeandroidjava.helper.FontHelper;
import net.logiico.formnativeandroidjava.helper.LogHelper;
import net.logiico.formnativeandroidjava.helper.UnicodeHelper;
import net.logiico.formnativeandroidjava.model.NativeTemplateField;
import net.logiico.formnativeandroidjava.model.NativeTemplateItem;
import net.logiico.formnativeandroidjava.model.RoomFileModel;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import ir.afkar.sundatepicker.DatePickerDialog;
import ir.afkar.sundatepicker.tool.JDF;

import static net.logiico.formnativeandroidjava.model.NativeTemplateField.EnumFieldType.*;

public class NativeTemplateFieldAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements TimePickerCallback {

    Context mContext;
    int total_types;
    MediaPlayer mPlayer;
    NativeTemplateItemCallBack callBackListener;
    boolean editable;
    //    private ArrayList<NF_E_Tab_Forms_Form_Field> Fields;
    private List<NativeTemplateField> Fields;
    private boolean fabStateVolume = false;

    //    public FN_MultiViewTypeAdapter(NativeTemplateItemCallBack callBackListener, ArrayList<NF_E_Tab_Forms_Form_Field> Fields, Context context, boolean editable) {
    public NativeTemplateFieldAdapter(NativeTemplateItemCallBack callBackListener, List<NativeTemplateField> Fields, Context context, boolean editable) {
        this.Fields = Fields;
        this.mContext = context;
        this.total_types = Fields.size();
        this.callBackListener = callBackListener;
        this.editable = editable;

    }

    @Override
    public void onCallBack(long timeOnly) {

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view;
        switch (viewType) {
            case TEXT_FIELD: //  textview
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fn_text_string_type, parent, false);
                return new TextStringaViewHolder(view);
            case NUMBER_FIELD: //
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fn_text_number_type, parent, false);
                return new TextNumberFieldViewHolder(view);
            case TEXTAREA_FIELD:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fn_text_area_type, parent, false);
                return new TextAreaViewHolder(view);
            case SELECT_FIELD:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fn_selector_spinner_type, parent, false);
                return new Selected_spinner_ViewHolder(view);
            case FILE_FIELD:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fn_file_upload_type, parent, false);
                return new File_UploadViewHolder(view);
            case CHECKBOX_LIST_FIELD:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fn_check_box_type, parent, false);
                return new CheckBox_ViewHolder(view);
            case RADIO_BUTTON_LIST_FIELD:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fn_radio_button_type, parent, false);
                return new RadioButton_ViewHolder(view);
            case DATE_FIELD:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fn_date_field_type, parent, false);
                return new DateField_ViewHolder(view);
        }
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fn_text_type, parent, false);
        return new TextTypeViewHolder(view);
//        return null;
    }

    @Override
    public int getItemViewType(int position) {

        switch (Fields.get(position).type) {
            case 1:
                return TEXT_FIELD;
            case 2:
                return NUMBER_FIELD;
            case 4:
                return TEXTAREA_FIELD;
            case 5:
                return FILE_FIELD;
            case 6:
                return CHECKBOX_LIST_FIELD;
            case 7:
                return SELECT_FIELD;
            case 3:
                return DATE_FIELD;
            case 8:
                return RADIO_BUTTON_LIST_FIELD;
            default:
                return TEXT_FIELD;

            // return -1;
        }


    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int listPosition) {

//        final NF_E_Tab_Forms_Form_Field object = Fields.get(listPosition);
        final NativeTemplateField object = Fields.get(listPosition);
        if (object != null) {
            switch (object.type) {
                case TEXT_FIELD:
                    ((TextStringaViewHolder) holder).fn_field_tv_string_title.setText(object.ResponseTitle);
                    ((TextStringaViewHolder) holder).fn_field_et_string_value.setText(object.value);
                    ((TextStringaViewHolder) holder).fn_field_et_string_value.setEnabled(editable);
                    if (editable) {
                        ((TextStringaViewHolder) holder).fn_field_et_string_value.addTextChangedListener(new TextWatcher() {

                            @Override
                            public void afterTextChanged(Editable s) {

                                object.value = s.toString();

                                if (callBackListener != null)
                                    callBackListener.onCallBack();

                            }

                            @Override
                            public void beforeTextChanged(CharSequence s, int start,
                                                          int count, int after) {

                            }

                            @Override
                            public void onTextChanged(CharSequence s, int start,
                                                      int before, int count) {
//                            if (s.length() != 0) {

                            }
                            //  }
                        });

                        if (object.required)
                            ((TextStringaViewHolder) holder).mandatoryImage.setVisibility(View.VISIBLE);
                    }
                    break;
                case NUMBER_FIELD:
                    ((TextNumberFieldViewHolder) holder).fn_field_tv_number_title.setText(object.ResponseTitle);
                    ((TextNumberFieldViewHolder) holder).fn_field_et_number_value.setText(object.value);
                    ((TextNumberFieldViewHolder) holder).fn_field_et_number_value.setLines(1);
                    ((TextNumberFieldViewHolder) holder).fn_field_et_number_value.setEnabled(editable);
                    if (editable) {
                        ((TextNumberFieldViewHolder) holder).fn_field_et_number_value.addTextChangedListener(new TextWatcher() {

                            @Override
                            public void afterTextChanged(Editable s) {
                                object.value = s.toString();

                                if (callBackListener != null)
                                    callBackListener.onCallBack();

                            }

                            @Override
                            public void beforeTextChanged(CharSequence s, int start,
                                                          int count, int after) {
                            }

                            @Override
                            public void onTextChanged(CharSequence s, int start,
                                                      int before, int count) {
                                // if (s.length() != 0) {

                                //  }
                            }
                        });


                        if (object.required)
                            ((TextNumberFieldViewHolder) holder).mandatoryImage.setVisibility(View.VISIBLE);
                    }
                    break;

                case TEXTAREA_FIELD:
                    ((TextAreaViewHolder) holder).txtType.setText(object.ResponseTitle);
                    ((TextAreaViewHolder) holder).fn_field_et_value.setText(object.value);
                    ((TextAreaViewHolder) holder).fn_field_et_value.setEnabled(editable);
                    if (editable) {
                        ((TextAreaViewHolder) holder).fn_field_et_value.addTextChangedListener(new TextWatcher() {

                            @Override
                            public void afterTextChanged(Editable s) {

                                object.value = s.toString();

                                if (callBackListener != null)
                                    callBackListener.onCallBack();

                            }

                            @Override
                            public void beforeTextChanged(CharSequence s, int start,
                                                          int count, int after) {
                            }

                            @Override
                            public void onTextChanged(CharSequence s, int start,
                                                      int before, int count) {
                                //   if (s.length() != 0) {
                                if (callBackListener != null)
                                    callBackListener.onCallBack();
                                //   }
                            }
                        });


                        if (object.required)
                            ((TextAreaViewHolder) holder).mandatoryImage.setVisibility(View.VISIBLE);
                    }
                    break;

                case FILE_FIELD:
                    if (object.fileModels != null) {
                        try {
                            LogHelper.d("address_path value before   :", object.value /*+ "  size : " + String.valueOf(file_size)*/);

                            for (RoomFileModel fileModel : object.fileModels) {
                                LogHelper.d("address_pat FileModel:", fileModel.title);
                            }

                            try {
                                NativeTemplateFieldItemAdapter itemArrayAdapter = new NativeTemplateFieldItemAdapter(R.layout.fn_field_images_row_item, object.fileModels);

                                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MyApplication.getInstance());
                                ((File_UploadViewHolder) holder).fn_field_images_RecyclerView.setLayoutManager(linearLayoutManager);
                                ((File_UploadViewHolder) holder).fn_field_images_RecyclerView.setItemAnimator(new DefaultItemAnimator());
                                ((File_UploadViewHolder) holder).fn_field_images_RecyclerView.setAdapter(itemArrayAdapter);

                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    ((File_UploadViewHolder) holder).fn_file_field_title.setText(object.ResponseTitle);
                    if (editable) {
                        ((File_UploadViewHolder) holder).fn_file_field_btn.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                //    ToastHelper.Show("File_UploadViewHolder item clicked 1", MyApplication.getInstance());
                                if (callBackListener != null) {
                                    callBackListener.onCameraOpen(object.fileModels == null ? 0 : object.fileModels.size(), String.valueOf(object.Id), object.value);
                                    //       ToastHelper.Show("File_UploadViewHolder item clicked 2", MyApplication.getInstance());
                                }

                            }
                        });


                        if (object.required)
                            ((File_UploadViewHolder) holder).mandatoryImage.setVisibility(View.VISIBLE);
                    }
                    break;
                // recyclerview
                    /*
                      if (object.Images != null) {
                        try {
                            LogHelper.d("address_path value befor   :", object.value );

                fn_field_image_adaptor itemArrayAdapter = new fn_field_image_adaptor(R.layout.fn_field_images_row_item, object.Images);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MyApplication.getInstance());
                ((File_UploadViewHolder) holder).fn_field_images_RecyclerView.setLayoutManager(linearLayoutManager);
                ((File_UploadViewHolder) holder).fn_field_images_RecyclerView.setItemAnimator(new DefaultItemAnimator());
                ((File_UploadViewHolder) holder).fn_field_images_RecyclerView.setAdapter(itemArrayAdapter);


            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (object.Images != null)
            if (object.Images.size() == 1 && object.UseMultiple == false) {
                ((File_UploadViewHolder) holder).fn_file_field_btn.setVisibility(View.GONE);
            }

        ((File_UploadViewHolder) holder).fn_file_field_title.setText(object.ResponseTitle);
        ((File_UploadViewHolder) holder).fn_file_field_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //    ToastHelper.Show("File_UploadViewHolder item clicked 1", MyApplication.getInstance());
                if (callBackListener != null) {
                    callBackListener.onCameraOpen(object.Images == null ? 0 : object.Images.size(), String.valueOf(object.Id), object.value);
                    //       ToastHelper.Show("File_UploadViewHolder item clicked 2", MyApplication.getInstance());
                }

            }
        });
        break;



                   */


                case SELECT_FIELD:
                    ((Selected_spinner_ViewHolder) holder).txtType.setText(object.ResponseTitle == null ? "it is null" : object.ResponseTitle);
                    ((Selected_spinner_ViewHolder) holder).fn_selected_spinner.setAdapter(new NativeFieldSpinnerAdapter(object.Items));
                    for (int i = 0; i < object.Items.size(); i++) {
                        if (object.Items.get(i).selected) {
                            ((Selected_spinner_ViewHolder) holder).fn_selected_spinner.setSelection(i);
                        }
                    }
                    ((Selected_spinner_ViewHolder) holder).fn_selected_spinner.setEnabled(editable);
                    ((Selected_spinner_ViewHolder) holder).fn_selected_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            //  ToastHelper.Show(object.Items.get(position).name + object.Items.get(position).value, MyApplication.getInstance());
                            for (int i = 0; i < object.Items.size(); i++) {
                                object.Items.get(i).selected = false;

                            }
                            object.Items.get(position).selected = true;
                            // object.value = String.valueOf(position);

                            if (callBackListener != null)
                                callBackListener.onCallBack();
                        } // to close the onItemSelected

                        public void onNothingSelected(AdapterView<?> parent) {
                        }
                    });

                    break;

                case CHECKBOX_LIST_FIELD:
                    ((CheckBox_ViewHolder) holder).txtType.setText(object.ResponseTitle);
                    NativeFieldCheckBoxAdapter adapter = new NativeFieldCheckBoxAdapter(callBackListener, object.Items, MyApplication.getInstance(), editable);
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MyApplication.getInstance(), RecyclerView.VERTICAL, false);
                    ((CheckBox_ViewHolder) holder).recyclerView_checkbox.setLayoutManager(linearLayoutManager);
                    ((CheckBox_ViewHolder) holder).recyclerView_checkbox.setItemAnimator(new DefaultItemAnimator());
                    ((CheckBox_ViewHolder) holder).recyclerView_checkbox.setAdapter(adapter);
                    break;

                case RADIO_BUTTON_LIST_FIELD:
                    ((RadioButton_ViewHolder) holder).txtType.setText(object.ResponseTitle);
//                    for (NF_F_Tab_Forms_Form_Field_Items item :object.Items) {
                    for (NativeTemplateItem item : object.Items) {
                        RadioButton rdbtn = new RadioButton(mContext);
                        rdbtn.setText(item.name);
                        rdbtn.setTextColor(Color.BLACK);
                        rdbtn.setTag(item.Id);

                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                            ColorStateList colorStateList = new ColorStateList(
                                    new int[][]{
                                            new int[]{-android.R.attr.state_enabled}, //disabled
                                            new int[]{android.R.attr.state_enabled} //enabled
                                    },
                                    new int[]{
                                            Color.BLACK //disabled
                                            , mContext.getResources().getColor(R.color.colorPrimaryold) //enabled
                                    }
                            );
                            rdbtn.setButtonTintList(colorStateList);//set the color tint list
                            rdbtn.invalidate(); //could not be necessary
                        }

                        ((RadioButton_ViewHolder) holder).radio_group_radiobutton.addView(rdbtn);
                        if (item.selected) {
                            ((RadioButton_ViewHolder) holder).radio_group_radiobutton.check(rdbtn.getId());
                        }
                    }
                    if (editable) {
                        ((RadioButton_ViewHolder) holder).radio_group_radiobutton.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                            @Override
                            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                                for (int k = 0; k < object.Items.size(); k++) {
                                    object.Items.get(k).selected = false;
                                }

                                for (int j = 0; j < radioGroup.getChildCount(); j++) {
                                    View o = radioGroup.getChildAt(j);
                                    if (o instanceof RadioButton) {
                                        if (((RadioButton) o).isChecked()) {
                                            for (int t = 0; t < object.Items.size(); t++) {
                                                if (o.getTag().equals(object.Items.get(t).Id)) {
                                                    object.Items.get(t).selected = true;
                                                    object.value = object.Items.get(t).value;
                                                }
                                            }
                                        }
                                    }

                                }


                                if (callBackListener != null)
                                    callBackListener.onCallBack();

                            }
                        });
                    } else {
                        ((RadioButton_ViewHolder) holder).radio_group_radiobutton.setClickable(false);
                        for (int j = 0; j < ((RadioButton_ViewHolder) holder).radio_group_radiobutton.getChildCount(); j++) {
                            View o = ((RadioButton_ViewHolder) holder).radio_group_radiobutton.getChildAt(j);
                            if (o instanceof RadioButton) {
                                o.setClickable(false);
                            }

                        }
                    }
                    break;

                case DATE_FIELD:
                    ((DateField_ViewHolder) holder).txtType.setText(object.ResponseTitle);
                    if (!object.value.isEmpty()) {
                        ((DateField_ViewHolder) holder).dateValue.setText(object.value.split(" ")[0]);
                        if (object.value.split(" ").length == 2)
                            ((DateField_ViewHolder) holder).timeValue.setText(object.value.split(" ")[1]);
                    }

                    if (editable) {
                        ((DateField_ViewHolder) holder).dateValue.setClickable(true);
                        ((DateField_ViewHolder) holder).dateValue.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {

                                DatePickerDialog dp = DatePickerDialog.newInstance(
                                        new DatePickerDialog.OnDateSetListener() {
                                            @Override
                                            public void onDateSet(int id, Calendar calendar, int year, int month, int day) {
                                                date dateOne = new date(year, month, day);
                                                dateOne.setDate(year, month, day, calendar);
                                                ((DateField_ViewHolder) holder).dateValue.setText(dateOne.getDate());
                                                ((DateField_ViewHolder) holder).dateValue.clearFocus();

                                                updateDateTimeValue(dateOne.getDate(), false, object);

//                                                object.value = dateOne.getDate();
//
//                                                if (callBackListener != null)
//                                                    callBackListener.onCallBack(String.valueOf(object.Id), object.value);
//                                            formEditTextViewHolder.hint.setText(calendar.toString());
                                            }
                                        }, 2055, false);

                                dp.setTypeFace(FontHelper.get(mContext, ConstHelper.DEFAULT_FONT));

                                JDF jdf = new JDF();
                                dp.setInitialDate(jdf.getIranianYear(), jdf.getIranianMonth(), jdf.getIranianDay());
                                dp.setFutureDisabled(false);
                                dp.show(MyApplication.getInstance().activity.getSupportFragmentManager(), "");


                            }
                        });

                        ((DateField_ViewHolder) holder).timeValue.setClickable(true);
                        ((DateField_ViewHolder) holder).timeValue.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Calendar mCurrentTime = Calendar.getInstance();
                                int hour = mCurrentTime.get(Calendar.HOUR_OF_DAY);
                                int minute = mCurrentTime.get(Calendar.MINUTE);

                                CustomTimePickerDialog timePickerDialog = new CustomTimePickerDialog(mContext, hour, minute);
                                ((FormNativeActivity) mContext).setTimePickerCallback(timePickerDialog);
                                timePickerDialog.setTimeClickedListener(new TimePickerCallback() {
                                    @Override
                                    public void onCallBack(long timeOnly) {

                                        SimpleDateFormat sample = new SimpleDateFormat("HH:mm", Locale.getDefault());
                                        String time = sample.format(timeOnly);

                                        ((DateField_ViewHolder) holder).timeValue.setText(time);

                                        updateDateTimeValue(UnicodeHelper.numberToEnglish(time), true, object);

//                                        object.value =  object.value + " " + UnicodeHelper.numberToEnglish(time);
//
//                                        if (callBackListener != null)
//                                            callBackListener.onCallBack(String.valueOf(object.Id), object.value);
                                    }
                                });

                            }
                        });


                        if (object.required)
                            ((DateField_ViewHolder) holder).mandatoryImage.setVisibility(View.VISIBLE);

                    } else {
                        ((DateField_ViewHolder) holder).dateValue.setClickable(false);
                        ((DateField_ViewHolder) holder).dateValue.setFocusable(false);
                        ((DateField_ViewHolder) holder).timeValue.setClickable(false);
                        ((DateField_ViewHolder) holder).timeValue.setFocusable(false);
                    }

                    break;
            }
        }

    }

    @Override
    public int getItemCount() {
        return Fields.size();
    }

    //    private void updateDateTimeValue(String newValue, boolean changedTime, NF_E_Tab_Forms_Form_Field object){
    private void updateDateTimeValue(String newValue, boolean changedTime, NativeTemplateField object) {

        if (changedTime) {
            if (object.value.split(" ")[0].length() > 5) {
                object.value = object.value.split(" ")[0] + " " + newValue;
            } else {
                object.value = newValue;
            }

        } else {

            if (object.value.split(" ").length == 2) {
                if (object.value.split(" ")[0].length() > 5) {
                    object.value = newValue + " " + object.value.split(" ")[1];
                }

            } else {

                if (object.value.split(" ")[0].length() > 5) {
                    object.value = newValue;

                } else {
                    object.value = newValue + " " + object.value;
                }

            }

        }


        if (callBackListener != null)
            callBackListener.onCallBack();
    }

    public static class TextTypeViewHolder extends RecyclerView.ViewHolder {


        PersianTextView txtType;
        CardView cardView;

        public TextTypeViewHolder(View itemView) {
            super(itemView);

            this.txtType = (PersianTextView) itemView.findViewById(R.id.fn_field_tv_title);
            this.cardView = (CardView) itemView.findViewById(R.id.card_view);

        }

    }

    public static class File_UploadViewHolder extends RecyclerView.ViewHolder {


        PersianTextView fn_file_field_title;
        PersianTextView fn_file_field_btn;
        RecyclerView fn_field_images_RecyclerView;
        ImageView mandatoryImage;
        //   ImageView fn_file_field_image;


        public File_UploadViewHolder(View itemView) {
            super(itemView);

            this.fn_file_field_title = (PersianTextView) itemView.findViewById(R.id.fn_file_field_title);
            this.fn_file_field_btn = (PersianTextView) itemView.findViewById(R.id.fn_file_field_btn);
            this.mandatoryImage = itemView.findViewById(R.id.fn_file_field_mandatory);
//            this.fn_file_field_image = (ImageView) itemView.findViewById(R.id.fn_file_field_image);
            this.fn_field_images_RecyclerView = (RecyclerView) itemView.findViewById(R.id.fn_field_images_RecyclerView);

        }
    }

    public static class TextStringaViewHolder extends RecyclerView.ViewHolder {


        PersianTextView fn_field_tv_string_title;
        PersianEditText fn_field_et_string_value;
        ImageView mandatoryImage;
        CardView fn_text_string_card_view;

        public TextStringaViewHolder(View itemView) {
            super(itemView);

            this.fn_field_tv_string_title = (PersianTextView) itemView.findViewById(R.id.fn_field_tv_string_title);
            this.fn_field_et_string_value = (PersianEditText) itemView.findViewById(R.id.fn_field_et_string_value);
            this.mandatoryImage = itemView.findViewById(R.id.fn_field_string_mandatory);
            this.fn_text_string_card_view = (CardView) itemView.findViewById(R.id.fn_text_string_card_view);

        }
    }

    public static class TextAreaViewHolder extends RecyclerView.ViewHolder {


        PersianTextView txtType;
        PersianEditText fn_field_et_value;
        ImageView mandatoryImage;
        CardView cardView;

        public TextAreaViewHolder(View itemView) {
            super(itemView);

            this.txtType = (PersianTextView) itemView.findViewById(R.id.fn_field_tv_title);
            this.fn_field_et_value = (PersianEditText) itemView.findViewById(R.id.fn_field_et_value);
            this.mandatoryImage = itemView.findViewById(R.id.fn_field_ta_mandatory);
            this.cardView = (CardView) itemView.findViewById(R.id.card_view);

        }

    }

    public static class TextNumberFieldViewHolder extends RecyclerView.ViewHolder {


        PersianTextView fn_field_tv_number_title;
        PersianEditText fn_field_et_number_value;
        CardView fn_text_number_card_view;
        ImageView mandatoryImage;

        public TextNumberFieldViewHolder(View itemView) {
            super(itemView);

            this.fn_field_tv_number_title = (PersianTextView) itemView.findViewById(R.id.fn_field_tv_number_title);
            this.fn_field_et_number_value = (PersianEditText) itemView.findViewById(R.id.fn_field_et_number_value);
            this.mandatoryImage = itemView.findViewById(R.id.fn_field_number_mandatory);
            this.fn_text_number_card_view = (CardView) itemView.findViewById(R.id.fn_text_number_card_view);
        }
    }

    public static class Selected_spinner_ViewHolder extends RecyclerView.ViewHolder {


        PersianTextView txtType;
        CardView fn_selected_spinner_card_view;
        Spinner fn_selected_spinner;

        public Selected_spinner_ViewHolder(View itemView) {
            super(itemView);

            this.txtType = (PersianTextView) itemView.findViewById(R.id.fn_selected_spinner_field_tv_title);
            this.fn_selected_spinner = (Spinner) itemView.findViewById(R.id.fn_selected_spinner);
            this.fn_selected_spinner_card_view = (CardView) itemView.findViewById(R.id.fn_selected_spinner_card_view);

        }

    }

    public static class CheckBox_ViewHolder extends RecyclerView.ViewHolder {


        PersianTextView txtType;
        RecyclerView recyclerView_checkbox;
        CardView cardView;
        boolean editable;

        public CheckBox_ViewHolder(View itemView) {
            super(itemView);

            this.txtType = (PersianTextView) itemView.findViewById(R.id.fn_cb_field_tv_title);
            this.recyclerView_checkbox = (RecyclerView) itemView.findViewById(R.id.fn_cb_recyclerView_checkbox);
            this.cardView = (CardView) itemView.findViewById(R.id.card_view);

        }

    }

    public static class RadioButton_ViewHolder extends RecyclerView.ViewHolder {


        PersianTextView txtType;
        RadioGroup radio_group_radiobutton;
        CardView cardView;
        boolean editable;

        public RadioButton_ViewHolder(View itemView) {
            super(itemView);

            this.txtType = itemView.findViewById(R.id.fn_rb_field_tv_title);
            this.radio_group_radiobutton = itemView.findViewById(R.id.fn_rb_radio_group_radiobutton);
            this.cardView = itemView.findViewById(R.id.card_view);

        }

    }

    public static class DateField_ViewHolder extends RecyclerView.ViewHolder {

        PersianTextView txtType;
        //        DatePicker datePicker;
//        PersianEditText dateValue;
        PersianTextView dateValue;
        PersianTextView timeValue;
        ImageView mandatoryImage;
        CardView cardView;
        boolean editable;


        public DateField_ViewHolder(View itemView) {
            super(itemView);

            this.txtType = itemView.findViewById(R.id.fn_field_date_title);
            this.dateValue = itemView.findViewById(R.id.fn_field_date_value);
            this.timeValue = itemView.findViewById(R.id.fn_field_date_time_value);
            this.mandatoryImage = itemView.findViewById(R.id.fn_field_date_mandatory);
//            this.datePicker = itemView.findViewById(R.id.fn_field_date);
            this.cardView = itemView.findViewById(R.id.card_view);

        }
    }

    class date {
        int year;
        int month;
        int day;
        Calendar calendar;

        date(int year, int month, int day) {
            this.year = year;
            this.month = month;
            this.day = day;
            calendar = Calendar.getInstance();
        }

        void setDate(int year, int month, int day, Calendar calendar) {
            this.year = year;
            this.month = month;
            this.day = day;
            this.calendar = calendar;
        }

        String getDate() {
            return year + "/" + month + "/" + day;
//            return year + "/" + month + "/" + day + "  "
//                    + calendar.get(Calendar.HOUR) + ":"
//                    + calendar.get(Calendar.MINUTE);
//            return year + "/" + month + "/" + day + "  ("
//                    + calendar.get(Calendar.YEAR) + "/"
//                    + calendar.get(Calendar.MONTH) + "/"
//                    + calendar.get(Calendar.DAY_OF_MONTH) + ")";
        }
    }

}

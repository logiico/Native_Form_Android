package net.logiico.formnativeandroidjava.model;

import java.util.List;

public class NativeTemplateField {
    public int Id;
    public String ResponseTitle;
    public String Response;
    public String name;
    public String placeHolder;
    public String value;
    public int type;
    public boolean required;
    public boolean UseMultiple;
    public boolean ShowEmptyOption;
    public boolean MultipleSelection;
    public boolean EmptyOption;
    public String RegexAlert;
    public String RegEx;
    public int DisplayOrder;
    public int Size;
    public List<RoomFileModel> fileModels;
    public List<NativeTemplateItem> Items;


    public class EnumFieldType {

        public static final int TEXT_FIELD = 1; // TextView
        public static final int NUMBER_FIELD = 2; // EditText Only number
        public static final int TEXTAREA_FIELD = 4; // EditText String
        public static final int FILE_FIELD = 5; // File upload
        public static final int CHECKBOX_LIST_FIELD = 6; //CheckBox
        public static final int SELECT_FIELD = 7; // DropDown
        public static final int RADIO_BUTTON_LIST_FIELD = 8; //Radio Button
        public static final int DATE_FIELD = 3; // Date
    }
}

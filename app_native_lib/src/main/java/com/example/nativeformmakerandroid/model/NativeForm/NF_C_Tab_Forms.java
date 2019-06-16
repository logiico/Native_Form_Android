package com.example.nativeformmakerandroid.model.NativeForm;

import java.util.ArrayList;

public class NF_C_Tab_Forms {
    public NF_D_Tab_Forms_Form Form;
    public boolean Repetitive;

    public NF_C_Tab_Forms getAnotherCopy() {
        NF_C_Tab_Forms nextFormNative = new NF_C_Tab_Forms();
        nextFormNative.Form = new NF_D_Tab_Forms_Form();
        nextFormNative.Form.Id = this.Form.Id;
        nextFormNative.Form.Fields = new ArrayList<>();
        for (int j = 0; j < this.Form.Fields.size(); j++) {
            NF_E_Tab_Forms_Form_Field current = this.Form.Fields.get(j);
            NF_E_Tab_Forms_Form_Field nextFormNativeField = new NF_E_Tab_Forms_Form_Field();
            nextFormNativeField.Id = current.Id;
            nextFormNativeField.ResponseTitle = current.ResponseTitle;
            nextFormNativeField.name = current.name;
            nextFormNativeField.placeHolder = current.placeHolder;
            nextFormNativeField.value = current.value;
            nextFormNativeField.type = current.type;
            nextFormNativeField.required = current.required;
            nextFormNativeField.UseMultiple = current.UseMultiple;
            nextFormNativeField.RegexAlert = current.RegexAlert;
            nextFormNativeField.RegEx = current.RegEx;
            nextFormNativeField.Items = new ArrayList<>();
            for (int k = 0; k < current.Items.size(); k++) {
                NF_F_Tab_Forms_Form_Field_Items currentItem = current.Items.get(k);
                NF_F_Tab_Forms_Form_Field_Items nextItem = new NF_F_Tab_Forms_Form_Field_Items();
                nextItem.Id = currentItem.Id;
                nextItem.name = currentItem.name;
                nextItem.value = currentItem.value;
                nextItem.selected = currentItem.selected;
                nextFormNativeField.Items.add(nextItem);
            }
            nextFormNative.Form.Fields.add(nextFormNativeField);

        }
        return nextFormNative;
    }
}

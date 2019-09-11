package net.logiico.formnativeandroidjava.model;

import java.util.ArrayList;

public class NativeTemplateParentForm {

    public NativeTemplateForm Form;
    public boolean Repetitive;

    public NativeTemplateParentForm getAnotherCopy() {
        NativeTemplateParentForm nextFormNative = new NativeTemplateParentForm();
        nextFormNative.Form = new NativeTemplateForm();
        nextFormNative.Form.Id = this.Form.Id;
        nextFormNative.Form.Fields = new ArrayList<>();
        for (int j = 0; j < this.Form.Fields.size(); j++) {
            NativeTemplateField current = this.Form.Fields.get(j);
            NativeTemplateField nextFormNativeField = new NativeTemplateField();
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
                NativeTemplateItem currentItem = current.Items.get(k);
                NativeTemplateItem nextItem = new NativeTemplateItem();
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

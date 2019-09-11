package net.logiico.formnativeandroidjava.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import net.logiico.formnativeandroidjava.R;
import net.logiico.formnativeandroidjava.customView.PersianTextView;
import net.logiico.formnativeandroidjava.model.NativeTemplateItem;

import java.util.List;

public class NativeFieldSpinnerAdapter extends BaseAdapter {
    LayoutInflater layoutInflater;
    //    private ArrayList<NF_F_Tab_Forms_Form_Field_Items> formItemChildren;
    private List<NativeTemplateItem> formItemChildren;

    //    public FN_SpinnerTypeAdapter(ArrayList<NF_F_Tab_Forms_Form_Field_Items> formItemChildren) {
    public NativeFieldSpinnerAdapter(List<NativeTemplateItem> formItemChildren) {
        this.formItemChildren = formItemChildren;
    }

    @Override
    public int getCount() {
        return formItemChildren != null ? formItemChildren.size() : 0;
    }

    @Override
//    public NF_F_Tab_Forms_Form_Field_Items getItem(int i) {
    public NativeTemplateItem getItem(int i) {
        return formItemChildren.get(i);
    }

    @Override
    public long getItemId(int i) {
        return Integer.valueOf(getItem(i).Id);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

//        NF_F_Tab_Forms_Form_Field_Items rowItem = getItem(i);
        NativeTemplateItem rowItem = getItem(i);

        viewHolder holder;
        View rowView = view;
        if (rowView == null) {

            holder = new viewHolder();
            layoutInflater = (LayoutInflater) viewGroup.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            rowView = layoutInflater.inflate(R.layout.fn_row_spinner, null, false);

            holder.txtTitle = (PersianTextView) rowView.findViewById(R.id.fn_row_simple_text_iew);
            rowView.setTag(holder);

        } else {
            holder = (viewHolder) rowView.getTag();
        }
        //   holder.imageView.setImageResource(Integer.parseInt(rowItem.getId()));
        holder.txtTitle.setText(rowItem.name);
        return rowView;
    }

    private class viewHolder {
        PersianTextView txtTitle;
    }
}

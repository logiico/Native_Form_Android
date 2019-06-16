package com.example.nativeformmakerandroid.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import com.example.nativeformmakerandroid.R;
import com.example.nativeformmakerandroid.model.NativeForm.NF_F_Tab_Forms_Form_Field_Items;

/**
 * Created by mbr on 4/12/17.
 */

public class FN_SpinnerTypeAdapter extends BaseAdapter {
    private ArrayList<NF_F_Tab_Forms_Form_Field_Items> formItemChildren;
    LayoutInflater flater;

    public FN_SpinnerTypeAdapter(ArrayList<NF_F_Tab_Forms_Form_Field_Items> formItemChildren) {
        this.formItemChildren = formItemChildren;
    }

    @Override
    public int getCount() {
        return formItemChildren != null ? formItemChildren.size() : 0;
    }

    @Override
    public NF_F_Tab_Forms_Form_Field_Items getItem(int i) {
        return formItemChildren.get(i);
    }

    @Override
    public long getItemId(int i) {
        return Integer.valueOf(getItem(i).Id);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        NF_F_Tab_Forms_Form_Field_Items rowItem = getItem(i);

        viewHolder holder;
        View rowview = view;
        if (rowview == null) {

            holder = new viewHolder();
            flater = (LayoutInflater) viewGroup.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            rowview = flater.inflate(R.layout.fn_row_spinner, null, false);

            holder.txtTitle = (TextView) rowview.findViewById(R.id.fn_row_simple_text_iew);
            rowview.setTag(holder);
        } else {
            holder = (viewHolder) rowview.getTag();
        }
        //   holder.imageView.setImageResource(Integer.parseInt(rowItem.getId()));
        holder.txtTitle.setText(rowItem.name);
        return rowview;
    }

    private class viewHolder {
        TextView txtTitle;
    }
}

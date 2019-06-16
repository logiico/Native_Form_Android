package com.example.nativeformmakerandroid.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import java.util.ArrayList;

import com.example.nativeformmakerandroid.R;
import com.example.nativeformmakerandroid.model.NativeForm.NF_F_Tab_Forms_Form_Field_Items;

/**
 * Created by mbr on 4/11/17.
 */

public class fn_checkboxAdapter extends RecyclerView.Adapter<fn_checkboxAdapter.FormViewHolder> {

    private ArrayList<NF_F_Tab_Forms_Form_Field_Items> Items;

    public fn_checkboxAdapter(ArrayList<NF_F_Tab_Forms_Form_Field_Items> Items) {
        this.Items = Items;
    }

    @Override
    public FormViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new FormViewHolder(((LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.form_section_row, parent, false));
    }

    @Override
    public void onBindViewHolder(FormViewHolder holder, int position) {
        holder.chbox.setText(Items.get(position).name);
        holder.chbox.setSelected(Items.get(position).selected);
    }

    @Override
    public int getItemCount() {
        return Items != null ? Items.size() : 0;
    }

    class FormViewHolder extends RecyclerView.ViewHolder {
        CheckBox chbox;

        FormViewHolder(View itemView) {
            super(itemView);
        //    chbox = (CheckBox) itemView.findViewById(R.id.fn_field_checkbox_value);
        }
    }
}

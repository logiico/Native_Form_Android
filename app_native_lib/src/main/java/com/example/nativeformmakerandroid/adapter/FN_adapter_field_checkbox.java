package com.example.nativeformmakerandroid.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import java.util.ArrayList;

import com.example.nativeformmakerandroid.Interfaces.CallBack_FN_Model_Listener;
import com.example.nativeformmakerandroid.R;
import com.example.nativeformmakerandroid.helper.LogHelper;
import com.example.nativeformmakerandroid.model.NativeForm.NF_F_Tab_Forms_Form_Field_Items;

public class FN_adapter_field_checkbox extends RecyclerView.Adapter<FN_adapter_field_checkbox.ViewHolder> {
    Context mContext;
    String FILE_NAME = "FILE NAME";
    private ArrayList<NF_F_Tab_Forms_Form_Field_Items> myItems = new ArrayList<>();
    CallBack_FN_Model_Listener callBackListener;
    boolean editable;

    public FN_adapter_field_checkbox(CallBack_FN_Model_Listener callBackListener_, ArrayList<NF_F_Tab_Forms_Form_Field_Items> getItems, Context context, boolean editable) {
        try {
            this.editable = editable;
            mContext = context;
            myItems = getItems;
            callBackListener = callBackListener_;
        } catch (Exception e) {
            LogHelper.e(FILE_NAME, "51: " + e.toString());
            e.printStackTrace();
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tvContent;
        public CheckBox cbSelect;

        public ViewHolder(View v) {
            super(v);
            tvContent = (TextView) v.findViewById(R.id.tvContent);
            cbSelect = (CheckBox) v.findViewById(R.id.cbSelect);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = null;
        ViewHolder viewHolder = null;


        view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.fn_field_checkbox_item_row, viewGroup, false);
        viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final NF_F_Tab_Forms_Form_Field_Items objIncome = myItems.get(position);
        holder.tvContent.setText(objIncome.name);

        //in some cases, it will prevent unwanted situations
        holder.cbSelect.setOnCheckedChangeListener(null);
        holder.cbSelect.setEnabled(editable);

        //if true, your checkbox will be selected, else unselected
        if (objIncome.selected) {
            holder.cbSelect.setChecked(true);
        } else {
            holder.cbSelect.setChecked(false);
        }
        if (editable) {
            holder.cbSelect.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    // objIncome.value=object.Items.get(position).value;
                    objIncome.selected = isChecked;
                    if (callBackListener != null)
                        callBackListener.onCallBack(objIncome.Id, objIncome.value);
                    //set your object's last status
                    //objIncome.setSelected(isChecked);
                    // notifyDataSetChanged();
                }
            });
        }

    }

    @Override
    public int getItemCount() {
        return myItems.size();
    }
}
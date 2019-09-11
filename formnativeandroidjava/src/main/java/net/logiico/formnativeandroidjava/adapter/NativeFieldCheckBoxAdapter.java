package net.logiico.formnativeandroidjava.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import androidx.recyclerview.widget.RecyclerView;

import net.logiico.formnativeandroidjava.R;
import net.logiico.formnativeandroidjava.callback.NativeTemplateItemCallBack;
import net.logiico.formnativeandroidjava.customView.PersianTextView;
import net.logiico.formnativeandroidjava.helper.LogHelper;
import net.logiico.formnativeandroidjava.model.NativeTemplateItem;

import java.util.ArrayList;
import java.util.List;

public class NativeFieldCheckBoxAdapter extends RecyclerView.Adapter<NativeFieldCheckBoxAdapter.ViewHolder> {
    Context mContext;
    String FILE_NAME = "FILE NAME";
    //    private ArrayList<NF_F_Tab_Forms_Form_Field_Items> myItems = new ArrayList<>();
    NativeTemplateItemCallBack callBackListener;
    boolean editable;
    private List<NativeTemplateItem> myItems = new ArrayList<>();

    //    public FN_adapter_field_checkbox(NativeTemplateItemCallBack callBackListener_, ArrayList<NF_F_Tab_Forms_Form_Field_Items> getItems, Context context, boolean editable) {
    public NativeFieldCheckBoxAdapter(NativeTemplateItemCallBack callBackListener_, List<NativeTemplateItem> getItems, Context context, boolean editable) {
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
//        final NF_F_Tab_Forms_Form_Field_Items objIncome = myItems.get(position);
        final NativeTemplateItem objIncome = myItems.get(position);
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
                        callBackListener.onCallBack();
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

    public class ViewHolder extends RecyclerView.ViewHolder {
        public PersianTextView tvContent;
        public CheckBox cbSelect;

        public ViewHolder(View v) {
            super(v);
            tvContent = (PersianTextView) v.findViewById(R.id.tvContent);
            cbSelect = (CheckBox) v.findViewById(R.id.cbSelect);
        }
    }
}
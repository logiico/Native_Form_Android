package net.logiico.formnativeandroidjava.adapter;

import android.content.Context;
import android.media.MediaPlayer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import net.logiico.formnativeandroidjava.MyApplication;
import net.logiico.formnativeandroidjava.R;
import net.logiico.formnativeandroidjava.callback.NativeTemplateItemCallBack;
import net.logiico.formnativeandroidjava.model.NativeTemplateField;
import net.logiico.formnativeandroidjava.model.NativeTemplateParentForm;

import java.util.List;

public class NativeTemplateParentFormAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    Context mContext;
    int total_types;
    MediaPlayer mPlayer;
    NativeTemplateItemCallBack templateItemCallBack;
    boolean editable;
    //    private ArrayList<NF_C_Tab_Forms> Forms;
    private List<NativeTemplateParentForm> Forms;
    private boolean fabStateVolume = false;

    //    public FN_Tabs_Forms_Adapter(NativeTemplateItemCallBack callBackListener, ArrayList<NF_C_Tab_Forms> Forms, Context context, boolean editable) {
    public NativeTemplateParentFormAdapter(NativeTemplateItemCallBack templateItemCallBack, List<NativeTemplateParentForm> Forms, Context context, boolean editable) {
        this.Forms = Forms;
        this.mContext = context;
        this.total_types = Forms.size();
        this.templateItemCallBack = templateItemCallBack;
        this.editable = editable;
        String a = "";
    }

    @Override
    public Fields_ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view;

        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fn_tabs_forms_row, parent, false);
        return new Fields_ViewHolder(view);
//        return null;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int listPosition) {

//        final ArrayList<NF_E_Tab_Forms_Form_Field> Fields = Forms.get(listPosition).Form.Fields;
        final List<NativeTemplateField> Fields = Forms.get(listPosition).Form.Fields;
        if (Forms.get(listPosition).Repetitive && editable) {
            ((Fields_ViewHolder) holder).repetitive.setVisibility(View.VISIBLE);
            ((Fields_ViewHolder) holder).repetitive.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Forms.add(holder.getAdapterPosition() + 1, Forms.get(holder.getAdapterPosition()).getAnotherCopy());
                    notifyItemInserted(holder.getAdapterPosition() + 1);
                    templateItemCallBack.onCallBack();
                }
            });
        } else
            ((Fields_ViewHolder) holder).repetitive.setVisibility(View.GONE);
        if (Fields != null) {
            //  switch (object.type) {
            //  case NF_FieldTypeEnum.TEXT_FIELD:
            // todo correct it
//            FN_MultiViewTypeAdapter adapter = new FN_MultiViewTypeAdapter(callBackListener, Fields, MyApplication.getInstance(), editable);
            NativeTemplateFieldAdapter adapter = new NativeTemplateFieldAdapter(templateItemCallBack, Fields, mContext, editable);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MyApplication.getInstance(), RecyclerView.VERTICAL, false);

            //  RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.fragment_fn_view_pager);
            ((Fields_ViewHolder) holder).RC.setLayoutManager(linearLayoutManager);
//            ((Fields_ViewHolder) holder).RC.setItemAnimator(new DefaultItemAnimator());
            ((Fields_ViewHolder) holder).RC.setAdapter(adapter);
            ((Fields_ViewHolder) holder).RC.setNestedScrollingEnabled(false);

        }

    }


//    @Override
//    public int getItemViewType(int position) {
//
//        switch (Forms.get(position).type) {
//            case 1:
//                return NF_FieldTypeEnum.TEXT_FIELD;
//            case 2:
//                return NF_FieldTypeEnum.NUMBER_FIELD;
//            case 4:
//                return NF_FieldTypeEnum.TEXTAREA_FIELD;
//            case 5:
//                return NF_FieldTypeEnum.FILE_FIELD;
//            case 6:
//                return NF_FieldTypeEnum.CHECKBOX_LIST_FIELD;
//            case 7:
//                return NF_FieldTypeEnum.SELECT_FIELD;
//
//            default:
//                return NF_FieldTypeEnum.TEXT_FIELD;
//
//            // return -1;
//        }
//}

    @Override
    public int getItemCount() {
        return Forms.size();
    }

    public static class Fields_ViewHolder extends RecyclerView.ViewHolder {

        public RecyclerView RC;
        public View repetitive;

        public Fields_ViewHolder(View itemView) {
            super(itemView);

            RC = itemView.findViewById(R.id.fn_recyclerView_tabs_forms_fileds);
            repetitive = itemView.findViewById(R.id.fn_recyclerView_tabs_forms_repetitive);

        }

    }


}

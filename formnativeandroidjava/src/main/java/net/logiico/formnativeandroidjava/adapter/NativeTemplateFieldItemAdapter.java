package net.logiico.formnativeandroidjava.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import net.logiico.formnativeandroidjava.MyApplication;
import net.logiico.formnativeandroidjava.R;
import net.logiico.formnativeandroidjava.activity.ActivityDisplayImage;
import net.logiico.formnativeandroidjava.helper.LogHelper;
import net.logiico.formnativeandroidjava.model.RoomFileModel;

import java.util.List;

public class NativeTemplateFieldItemAdapter extends RecyclerView.Adapter<NativeTemplateFieldItemAdapter.ViewHolder> {

    List<RoomFileModel> itemList;
    //All methods in this adapter are required for a bare minimum recyclerview adapter
    private int listItemLayout;
//    ArrayList<FileModelJavaScript> itemList;

    // Constructor of the class
//    public fn_field_image_adaptor(int layoutId, ArrayList<FileModelJavaScript> itemList) {
    public NativeTemplateFieldItemAdapter(int layoutId, List<RoomFileModel> itemList) {
        listItemLayout = layoutId;
        this.itemList = itemList;
    }

    // get the size of the list
    @Override
    public int getItemCount() {
        return itemList == null ? 0 : itemList.size();
    }


    // specify the row layout file and click for each row
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(listItemLayout, parent, false);

        return new ViewHolder(view);
    }

    // load data in each row element
    @Override
    public void onBindViewHolder(final ViewHolder holder, final int listPosition) {

        holder.item.setText(itemList.get(listPosition).title);
        holder.item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MyApplication.getInstance(), ActivityDisplayImage.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("mode", "storage");

                String s;
                if (itemList.get(listPosition) != null)
                    s = itemList.get(listPosition).filePath.isEmpty() ? "" : itemList.get(listPosition).filePath;
                else
                    s = "";
//                String s = itemList.get(listPosition).filePaths != null ? itemList.get(listPosition).filePaths.get(0) : "";//(new File(itemList.get(listPosition).Title).getPath());
                LogHelper.d("address_path", s);
                intent.putExtra("address", s);
                MyApplication.getInstance().startActivity(intent);
            }
        });
    }

    // Static inner class to initialize the views of rows
    static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView item;

        public ViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            item = (TextView) itemView.findViewById(R.id.row_item);
        }

        @Override
        public void onClick(View view) {
            LogHelper.d("onclick", "onClick " + getLayoutPosition() + " " + item.getText());
        }
    }
}

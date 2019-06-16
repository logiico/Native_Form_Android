package com.example.nativeformmakerandroid.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.io.File;
import java.util.List;

import com.example.nativeformmakerandroid.R;

/**
 * Created by Mohammad MBR on 11/13/2017.
 */

public class FileListDialogFragmentSubAdapter extends RecyclerView.Adapter<FileListDialogFragmentSubAdapter.FileListDialogFragmentSubViewHolder> {
    private List<String> filePaths;
    private RemoveCallback removeCallback;
    private boolean editable;

    public FileListDialogFragmentSubAdapter(List<String> filePaths, boolean editable, RemoveCallback removeCallback) {
        this.filePaths = filePaths;
        this.removeCallback = removeCallback;
        this.editable = editable;
    }

    @Override
    public FileListDialogFragmentSubViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new FileListDialogFragmentSubViewHolder(parent);
    }

    @Override
    public int getItemCount() {
        return filePaths != null ? filePaths.size() : 0;
    }

    @Override
    public void onBindViewHolder(final FileListDialogFragmentSubViewHolder holder, int position) {
        String filePath = filePaths.get(position);
        holder.name.setText(new File(filePath).getName());
        holder.name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (removeCallback != null) {
                    removeCallback.showFile(filePaths.get(holder.getAdapterPosition()));
                }
            }
        });
        holder.remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (removeCallback != null) {
                    removeCallback.removeIndex(filePaths.get(holder.getAdapterPosition()));
                }
//                filePaths.remove(holder.getAdapterPosition());
                notifyItemRemoved(holder.getAdapterPosition());
            }
        });
        holder.remove.setVisibility(editable ? View.VISIBLE : View.GONE);
        holder.shadow.setVisibility(position == 0 ? View.VISIBLE : View.GONE);
    }

    interface RemoveCallback {
        void removeIndex(String filePath);

        void showFile(String filePath);
    }

    class FileListDialogFragmentSubViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        View remove, shadow;

        public FileListDialogFragmentSubViewHolder(ViewGroup parent) {
            super(LayoutInflater.from(parent.getContext()).inflate(R.layout.row_fragment_file_list_sub, parent, false));
            name = (TextView) itemView.findViewById(R.id.row_fragment_file_list_sub_name);
            remove = itemView.findViewById(R.id.row_fragment_file_list_sub_remove);
            shadow = itemView.findViewById(R.id.row_fragment_file_list_sub_shadow);

        }
    }
}

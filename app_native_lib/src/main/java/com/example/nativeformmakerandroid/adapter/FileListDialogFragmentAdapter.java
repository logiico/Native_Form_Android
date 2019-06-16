package com.example.nativeformmakerandroid.adapter;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import com.example.nativeformmakerandroid.R;
import com.example.nativeformmakerandroid.fragment.FileListFragment;
import com.example.nativeformmakerandroid.model.NativeForm.FileModelJavaScript;

/**
 * Created by Mohammad MBR on 11/13/2017.
 */

public class FileListDialogFragmentAdapter extends RecyclerView.Adapter<FileListDialogFragmentAdapter.FileListDialogFragmentViewHolder> implements FileListDialogFragmentSubAdapter.RemoveCallback {
    private List<FileModelJavaScript> fileModelJavaScripts;
    private FileListFragment.OnFileChangeListener onFileChangeListener;
    private boolean editable;

    public FileListDialogFragmentAdapter(List<FileModelJavaScript> fileModelJavaScripts, boolean editable, FileListFragment.OnFileChangeListener onFileChangeListener) {
        this.fileModelJavaScripts = fileModelJavaScripts;
        this.onFileChangeListener = onFileChangeListener;
        this.editable = editable;
    }

    @Override
    public FileListDialogFragmentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new FileListDialogFragmentViewHolder(parent);
    }

    @Override
    public void onBindViewHolder(final FileListDialogFragmentViewHolder holder, int position) {
        FileModelJavaScript fileModelJavaScript = fileModelJavaScripts.get(position);
        holder.title.setText(fileModelJavaScript.Title);
        holder.title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (holder.recyclerView.getVisibility() != View.VISIBLE) {
                    holder.recyclerView.setVisibility(View.VISIBLE);
                } else {
                    holder.recyclerView.setVisibility(View.GONE);
                }
            }
        });
        if (!editable || (fileModelJavaScript.filePaths != null && !fileModelJavaScript.Multiple && fileModelJavaScript.filePaths.size() == 1)) {
            holder.plus.setVisibility(View.GONE);
        } else {
            holder.plus.setVisibility(View.VISIBLE);
        }
        holder.plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FileModelJavaScript fileModelJavaScript1 = fileModelJavaScripts.get(holder.getAdapterPosition());
                if (onFileChangeListener != null) {
                    onFileChangeListener.requestFile(fileModelJavaScript1);
                }
            }
        });
        holder.recyclerView.setAdapter(new FileListDialogFragmentSubAdapter(fileModelJavaScript.filePaths, editable, this));
        holder.recyclerView.setLayoutManager(new LinearLayoutManager(holder.itemView.getContext()));
    }

    @Override
    public int getItemCount() {
        return fileModelJavaScripts != null ? fileModelJavaScripts.size() : 0;
    }

    @Override
    public void removeIndex(String filePath) {
        for (int i = 0; i < fileModelJavaScripts.size(); i++) {
            if (fileModelJavaScripts.get(i).filePaths != null) {
                for (int j = 0; j < fileModelJavaScripts.get(i).filePaths.size(); j++) {
                    if (filePath != null && fileModelJavaScripts.get(i).filePaths.get(j).equals(filePath)) {
                        fileModelJavaScripts.get(i).filePaths.remove(j);
                        notifyItemChanged(i);
                    }
                }
            }
        }
    }

    @Override
    public void showFile(String filePath) {
        if (onFileChangeListener != null) {
            onFileChangeListener.showFile(filePath);
        }
    }

    class FileListDialogFragmentViewHolder extends RecyclerView.ViewHolder {
        RecyclerView recyclerView;
        TextView title;
        View plus;

        FileListDialogFragmentViewHolder(ViewGroup parent) {
            super(LayoutInflater.from(parent.getContext()).inflate(R.layout.row_fragment_file_list, parent, false));
            recyclerView = (RecyclerView) itemView.findViewById(R.id.row_fragment_file_list_recycler_view);
            title = (TextView) itemView.findViewById(R.id.row_fragment_file_list_title);
            plus = itemView.findViewById(R.id.row_fragment_file_list_plus);
        }
    }


}

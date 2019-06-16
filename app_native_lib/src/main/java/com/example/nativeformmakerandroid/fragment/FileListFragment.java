package com.example.nativeformmakerandroid.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import java.util.ArrayList;

import com.example.nativeformmakerandroid.R;
import com.example.nativeformmakerandroid.adapter.FileListDialogFragmentAdapter;
import com.example.nativeformmakerandroid.helper.LogHelper;
import com.example.nativeformmakerandroid.model.NativeForm.FileModelJavaScript;


public class FileListFragment extends DialogFragment {
    public static final String TAG = "FileListFragment";
    private static final String ARG_FILE_LIST = "FileList";
    private static final String ARG_EDITABLE = "ARG_EDITABLE";
    private RecyclerView recyclerView;
    private boolean editable;

    private ArrayList<FileModelJavaScript> fileModelJavaScripts;

    private OnFileChangeListener onFileChangeListener;

    public FileListFragment() {
        // Required empty public constructor
    }

    public static FileListFragment newInstance(ArrayList<FileModelJavaScript> fileModel, boolean editable) {
        FileListFragment fragment = new FileListFragment();
        Bundle args = new Bundle();
        args.putParcelableArrayList(ARG_FILE_LIST, fileModel);
        args.putBoolean(ARG_EDITABLE, editable);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            fileModelJavaScripts = getArguments().getParcelableArrayList(ARG_FILE_LIST);
            editable = getArguments().getBoolean(ARG_EDITABLE);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        return inflater.inflate(R.layout.fragment_file_list, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = (RecyclerView) view.findViewById(R.id.fragment_file_list_recycler_view);
        recyclerView.setAdapter(new FileListDialogFragmentAdapter(fileModelJavaScripts, editable, onFileChangeListener));
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFileChangeListener) {
            onFileChangeListener = (OnFileChangeListener) context;
        } else {
            throw new IllegalStateException("parent should implement OnFileChangeListener interface");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        onFileChangeListener = null;
    }

    @Override
    public void show(FragmentManager manager, String tag) {

        try {
            FragmentTransaction ft = manager.beginTransaction();
            ft.add(this, tag);
            ft.commitAllowingStateLoss();
        } catch (IllegalStateException e) {
            LogHelper.d("ABSDIALOGFRAG", "Exception" + e.getMessage());
        }
    }

    public interface OnFileChangeListener {
        void requestFile(FileModelJavaScript fileModelJavaScript);

        void removeFile(FileModelJavaScript fileModelJavaScript);

        void showFile(String fileModelJavaScript);
    }
}

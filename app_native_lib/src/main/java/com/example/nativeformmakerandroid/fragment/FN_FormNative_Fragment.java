package com.example.nativeformmakerandroid.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import com.example.nativeformmakerandroid.Interfaces.CallBack_FN_Model_Listener;
import com.example.nativeformmakerandroid.MyApplication;
import com.example.nativeformmakerandroid.R;
import com.example.nativeformmakerandroid.adapter.FN_Tabs_Forms_Adapter;
import com.example.nativeformmakerandroid.model.NativeForm.NF_B_Tab;
/*
import com.example.nativeformmakerandroid.model.Mission;
*/


public class FN_FormNative_Fragment extends Fragment {
    View view;
    private static final String ARG_TYPE = "ARG_TYPE";
    RecyclerView recyclerView;
    View loadingView, emptyStateContainer;
    TextView emptyStateText;
    private int position;
    private int page = 1;
    private boolean loading, loadedAllItems;
    ArrayList<NF_B_Tab> Tabs;
    boolean editable;

    public void setObjectFunction(ArrayList<NF_B_Tab> Tabs, int position, boolean editable) {
        this.Tabs = Tabs;
        this.position = position;
        this.editable = editable;
    }

//    public static FN_FormNative_Fragment newInstance(int position) {
//        FN_FormNative_Fragment fragment = new FN_FormNative_Fragment();
//        Bundle args = new Bundle();
//        args.putInt(ARG_TYPE, position);
//        fragment.setArguments(args);
//        return fragment;
//    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        return inflater.inflate(R.layout.fragment_fn_form_nativet, container, false);
    }

    @Override
    public void onViewCreated(View view1, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view1, savedInstanceState);
        view = view1;
        recyclerView = view.findViewById(R.id.fragment_fn_forms);
        loadingView = view.findViewById(R.id.progressBar1);
        emptyStateContainer = view.findViewById(R.id.fragment_mission_list_empty_state_container);
        emptyStateText = view.findViewById(R.id.fragment_mission_list_empty_state_text);
    }

    @Override
    public void onResume() {
        super.onResume();
        load();

    }

    private void load() {
        loading = false;
        loadedAllItems = false;
        recyclerView.setAdapter(null);
        page = 1;
        loadData();
    }

    public void loadData() {
        loadingView.setVisibility(View.VISIBLE);

// todo correct it
//        FN_Tabs_Forms_Adapter adapter = new FN_Tabs_Forms_Adapter(callBackListener, Tabs.get(position).Forms, MyApplication.getInstance(), editable);
        FN_Tabs_Forms_Adapter adapter = new FN_Tabs_Forms_Adapter(callBackListener, Tabs.get(position).Forms, this.getActivity(), editable);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MyApplication.getInstance(), OrientationHelper.VERTICAL, false);

        RecyclerView mRecyclerView = view.findViewById(R.id.fragment_fn_forms);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(adapter);
        loadingView.setVisibility(View.GONE);

    }

    private CallBack_FN_Model_Listener callBackListener;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //getActivity() is fully created in onActivityCreated and instanceOf differentiate it between different Activities
        if (getActivity() instanceof CallBack_FN_Model_Listener)
            callBackListener = (CallBack_FN_Model_Listener) getActivity();
    }
}

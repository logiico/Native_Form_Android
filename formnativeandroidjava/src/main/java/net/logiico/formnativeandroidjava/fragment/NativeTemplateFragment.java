package net.logiico.formnativeandroidjava.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import net.logiico.formnativeandroidjava.MyApplication;
import net.logiico.formnativeandroidjava.R;
import net.logiico.formnativeandroidjava.adapter.NativeTemplateParentFormAdapter;
import net.logiico.formnativeandroidjava.callback.NativeTemplateItemCallBack;
import net.logiico.formnativeandroidjava.model.NativeTemplateTab;

import java.util.List;

public class NativeTemplateFragment extends Fragment {
    private static final String ARG_TYPE = "ARG_TYPE";
    View view;
    RecyclerView recyclerView;
    View loadingView, emptyStateContainer;
    TextView emptyStateText;
    List<NativeTemplateTab> Tabs;
    //    ArrayList<NF_B_Tab> Tabs;
    boolean editable;
    private int position;
    private int page = 1;
    private boolean loading, loadedAllItems;
    private NativeTemplateItemCallBack callBackListener;

//    public static FN_FormNative_Fragment newInstance(int position) {
//        FN_FormNative_Fragment fragment = new FN_FormNative_Fragment();
//        Bundle args = new Bundle();
//        args.putInt(ARG_TYPE, position);
//        fragment.setArguments(args);
//        return fragment;
//    }

    //    public void setObjectFunction(ArrayList<NF_B_Tab> Tabs, int position, boolean editable) {
    public void setObjectFunction(List<NativeTemplateTab> Tabs, int position, boolean editable) {
        this.Tabs = Tabs;
        this.position = position;
        this.editable = editable;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        return inflater.inflate(R.layout.fragment_native_template, container, false);
    }

    @Override
    public void onViewCreated(View view1, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view1, savedInstanceState);
        view = view1;
        recyclerView = view.findViewById(R.id.fragment_fn_forms);
        loadingView = view.findViewById(R.id.loading);
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
        NativeTemplateParentFormAdapter adapter = new NativeTemplateParentFormAdapter(callBackListener, Tabs.get(position).Forms, this.getActivity(), editable);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MyApplication.getInstance(), RecyclerView.VERTICAL, false);

        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
        loadingView.setVisibility(View.GONE);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //getActivity() is fully created in onActivityCreated and instanceOf differentiate it between different Activities
        if (getActivity() instanceof NativeTemplateItemCallBack)
            callBackListener = (NativeTemplateItemCallBack) getActivity();
    }
}

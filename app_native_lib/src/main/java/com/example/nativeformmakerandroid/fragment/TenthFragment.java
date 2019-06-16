package com.example.nativeformmakerandroid.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;


import com.example.nativeformmakerandroid.BuildConfig;
import com.example.nativeformmakerandroid.MyApplication;

// Todo tgs : google analytics for FragmentHistoryJob should be done inside the class because it is used within view pager
public abstract class TenthFragment extends Fragment {

    public TenthFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
    }

    @Override
    public void onResume() {
//      Log.e("frag_tenth_TAG", "onResume of :" + this.getClass().getSimpleName().toString());

        if (getName() != null && !BuildConfig.DEBUG) {
//            mTracker.setScreenName(this.getClass().getSimpleName());

        }

        super.onResume();
    }

    public abstract String getName();

}

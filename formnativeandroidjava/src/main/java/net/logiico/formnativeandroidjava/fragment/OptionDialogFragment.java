package net.logiico.formnativeandroidjava.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;

import net.logiico.formnativeandroidjava.R;

import java.util.ArrayList;

/**
 * A simple {@link androidx.fragment.app.Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnItemClickListener} interface
 * to handle interaction events.
 * Use the {@link OptionDialogFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class OptionDialogFragment extends DialogFragment {
    public static final String TAG = "OptionDialogFragment";
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_TITLE = "ARG_TITLE";
    private static final String ARG_STRING_LIST = "ARG_STRING_LIST";
    // TODO: Rename and change types of parameters
    private String title;
    private ArrayList<String> strings;

    private OnItemClickListener mListener;

    public OptionDialogFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param title   Parameter 1.
     * @param strings Parameter 2.
     * @return A new instance of fragment OptionDialogFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static OptionDialogFragment newInstance(String title, ArrayList<String> strings) {
        OptionDialogFragment fragment = new OptionDialogFragment();
        Bundle args = new Bundle();
        args.putString(ARG_TITLE, title);
        args.putStringArrayList(ARG_STRING_LIST, strings);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            title = getArguments().getString(ARG_TITLE);
            strings = getArguments().getStringArrayList(ARG_STRING_LIST);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_option_dialog, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TextView titleView = (TextView) view.findViewById(R.id.fragment_option_dialog_title);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.fragment_option_dialog_recycler_view);
        titleView.setText(title);
        recyclerView.setAdapter(new SimpleAdapter());
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnItemClickListener) {
            mListener = (OnItemClickListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnItemClickListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnItemClickListener {
        // TODO: Update argument type and name
        void onItemClicked(int position);
    }

    private class SimpleAdapter extends RecyclerView.Adapter<SimpleViewHolder> {

        @Override
        public SimpleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new SimpleViewHolder(parent);
        }

        @Override
        public void onBindViewHolder(SimpleViewHolder holder, int position) {
            holder.textView.setText(strings.get(position));
        }

        @Override
        public int getItemCount() {
            return strings != null ? strings.size() : 0;
        }
    }

    private class SimpleViewHolder extends RecyclerView.ViewHolder {
        TextView textView;

        public SimpleViewHolder(ViewGroup parent) {
            super(LayoutInflater.from(parent.getContext()).inflate(R.layout.row_simple, parent, false));
            textView = (TextView) itemView.findViewById(R.id.row_simple_text_iew);
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dismiss();
                    if (mListener != null) {
                        mListener.onItemClicked(getAdapterPosition());
                    }
                }
            });
        }
    }
}

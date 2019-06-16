package ir.afkar.sundatepicker.year;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import com.afkar.sundatepicker.R;

/*
 * Created by Alireza Afkar - 24/10/14
 */

@SuppressLint("ValidFragment")
public class YearFragement extends Fragment {
	int[] years;

	@SuppressLint("ValidFragment")
	public YearFragement(int[] years) {
		this.years = years;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		return inflater.inflate(R.layout.years_layout, container, false);
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		ListView list = (ListView) view.findViewById(android.R.id.list);
		list.setSelector(getResources().getDrawable(R.drawable.transparent));
		list.setAdapter(new YearAdapter(getActivity(), years));
		super.onViewCreated(view, savedInstanceState);
	}
}
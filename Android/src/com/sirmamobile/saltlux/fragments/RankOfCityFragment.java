package com.sirmamobile.saltlux.fragments;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import com.sirmamobile.base.fragments.RequestFragment;
import com.sirmamobile.saltlux.R;
import com.sirmamobile.saltlux.adapters.RankCityFragmentAdapter;
import com.sirmamobile.saltlux.adapters.RankCityFragmentAdapterListener;
import com.sirmamobile.saltlux.http.model.RankCity;
import com.sirmamobile.saltlux.http.requests.GetRankCityDataRequestFragment;

public class RankOfCityFragment extends SaltluxFragment implements
		RankCityFragmentAdapterListener {

	private static final String ARG_CITY_DATA = "ARG_CITY_DATA";
	private RankCity data;

	public static final RankOfCityFragment load() {
		RankOfCityFragment df = new RankOfCityFragment();
		Bundle b = new Bundle();
		df.setArguments(b);
		return df;
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		if (!(activity instanceof RankOfCityListener))
			throw new RuntimeException("Wrong interface type");
	}

	@Override
	protected void onCreation(boolean fromState, Bundle savedInstanceState) {
		super.onCreation(fromState, savedInstanceState);
		data = getArguments().getParcelable(ARG_CITY_DATA);
	}

	private ViewPager vp;
	private RankCityFragmentAdapter adapter;

	@Override
	protected View onViewCreation(LayoutInflater inflater, ViewGroup container,
			boolean fromState, boolean firstCall, Bundle savedInstanceState) {

		View v = inflater.inflate(R.layout.rank_city, container, false);
		Spinner spView = (Spinner) v.findViewById(R.id.spView);
		List<String> list = new ArrayList<String>();
		list.add("PLUQI");
		list.add("Education");
		list.add("Enviroment");
		list.add("Healthcare");
		list.add("Cultural Satisfaction");
		list.add("Traffic Satisfaction");
		ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, list);
		dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spView.setAdapter(dataAdapter);
		Calendar newCalendar = Calendar.getInstance();
		final SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy", Locale.US);
		final EditText edtFrom = (EditText) v.findViewById(R.id.txtFromDate);
		final EditText edtTo = (EditText) v.findViewById(R.id.txtToDate);

		final DatePickerDialog dateFromDialog = new DatePickerDialog(getActivity(), new OnDateSetListener() {
			
			@Override
			public void onDateSet(DatePicker view, int year, int monthOfYear,
					int dayOfMonth) {
				Calendar newDate = Calendar.getInstance();
	            newDate.set(year, monthOfYear, dayOfMonth);
	            edtFrom.setText(dateFormatter.format(newDate.getTime()));
				
			}
		}, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
		
		final DatePickerDialog dateToDialog = new DatePickerDialog(getActivity(), new OnDateSetListener() {
			
			@Override
			public void onDateSet(DatePicker view, int year, int monthOfYear,
					int dayOfMonth) {
				Calendar newDate = Calendar.getInstance();
	            newDate.set(year, monthOfYear, dayOfMonth);
	            edtTo.setText(dateFormatter.format(newDate.getTime()));
				
			}
		}, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
		edtFrom.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				dateFromDialog.show();
			}
		});
		
		edtTo.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				dateToDialog.show();
			}
		});
		
		
		
		vp = (ViewPager) v.findViewById(R.id.vfRanks);
		if (index >= 0)
			vp.setCurrentItem(index);
		return v;
	}

	private int index = 0;

	@Override
	public void onResume() {
		super.onResume();
		if (data == null) {
			RequestFragment.doRequest(getFragmentManager(),GetRankCityDataRequestFragment.newInstance("PLUQI"));
		} else {
			if (adapter == null)
				adapter = new RankCityFragmentAdapter(getChildFragmentManager(),data.getData());
			adapter.setListener(this);
			vp.setAdapter(adapter);
		}
	}

	public void getCityData(RankCity data) {
		this.data = data;
		getArguments().putParcelable(ARG_CITY_DATA, data);
		if (adapter == null) {
			adapter = new RankCityFragmentAdapter(getChildFragmentManager(),
					data.getData());
			adapter.setListener(this);
		}
		vp.setAdapter(adapter);
	}
}

package edu.utdallas.itsummit.fragments;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;

import edu.utdallas.itsummit.R;
import edu.utdallas.itsummit.adapters.PresenterListAdapter;
import edu.utdallas.itsummit.models.jsonmodels.session;


public class DetailSessionFragment extends Fragment {

    private final String TAG = getClass().getName();
    ListView lvPresenters;
    session session;
    SharedPreferences sharedPrefs;
    PresenterListAdapter adapter;
    String FAV_KEY;
    ImageView ivStar;

    public static DetailSessionFragment newInstance(final session session) {
        final DetailSessionFragment fragment = new DetailSessionFragment();
        fragment.session = session;
        return fragment;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        Log.d(TAG, "OnAttached");
        FAV_KEY = activity.getResources().getString(R.string.KEY_FAV);
    }

    public void refreshData() {
        if (adapter != null) {
            adapter.notifyDataSetChanged();
            Log.d(TAG, "Refreshed adapter data");
        } else {
            Log.d(TAG, "Adapter is null");
        }
    }

    public void setSession(session session) {
        this.session = session;
        adapter = new PresenterListAdapter(getActivity(), session);
        lvPresenters.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.e(TAG, "OnCreateView");
        sharedPrefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
        if (savedInstanceState != null && session == null) {
            session = savedInstanceState.getParcelable(getActivity().getResources().getString(R.string.PARCELABLE_SESSION));
        }
        View v = inflater.inflate(R.layout.session_detail_fragment, container, false);
        lvPresenters = (ListView) v.findViewById(R.id.listSession);
        if (adapter == null && session != null) {
            adapter = new PresenterListAdapter(getActivity(), session);
            lvPresenters.setAdapter(adapter);
        }
        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "OnResume");
    }


    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.e(TAG, "OnSaveInstanceState");
        outState.putParcelable(getActivity().getResources().getString(R.string.PARCELABLE_SESSION), session);
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        lvPresenters = null;
        session = null;
        adapter = null;
        ivStar = null;
    }

    @Override
    public void onInflate(Activity activity, AttributeSet attrs, Bundle savedInstanceState) {
        super.onInflate(activity, attrs, savedInstanceState);
        Log.d(TAG, "OnInflate");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.e(TAG, "OnPause");
        onSaveInstanceState(new Bundle());
    }
}

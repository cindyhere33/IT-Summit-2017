package edu.utdallas.itsummit.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.android.volley.toolbox.NetworkImageView;

import java.util.ArrayList;
import java.util.List;

import edu.utdallas.itsummit.R;
import edu.utdallas.itsummit.activities.MainActivity;
import edu.utdallas.itsummit.adapters.SessionListAdapter;
import edu.utdallas.itsummit.models.RealmString;
import edu.utdallas.itsummit.models.Schedule;
import edu.utdallas.itsummit.models.jsonmodels.presenter;
import edu.utdallas.itsummit.models.jsonmodels.session;
import edu.utdallas.itsummit.serverapi.DataGetter;


public class DetailPresenterFragment extends Fragment {

    ListView lvSchedule;
    RelativeLayout rl_back;
    NetworkImageView ivPresenter;
    presenter presenter;
    SessionListAdapter adapter;
    List<session> sessionsList = new ArrayList<>();
    private final String TAG = getClass().getName();
    MainActivity activity;


    public static DetailPresenterFragment newInstance(presenter presenter) {
        final DetailPresenterFragment fragment = new DetailPresenterFragment();
        fragment.presenter = presenter;
        return fragment;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.activity = (MainActivity) activity;
        sessionsList.clear();
        sessionsList.addAll(((Schedule) ((MainActivity) getActivity()).getDataGetter().readFromRealm(DataGetter.DataType.SESSION)).getSessions());
        if (adapter != null) adapter.notifyDataSetChanged();
    }

    private List<session> getSessions() {
        List<session> sessions = new ArrayList<>();
        for (session session : sessionsList) {
            List<RealmString> presenters = session.getPresenters();
            for (RealmString id : presenters) {
                if (id.getData().trim().equalsIgnoreCase(presenter.getID())) sessions.add(session);
            }
        }
        return sessions;
    }

    public void refreshData() {
        if (adapter != null) {
            adapter.notifyDataSetChanged();
            Log.d(TAG, "Refreshed adapter data");
        } else {
            Log.d(TAG, "Adapter is null");
        }
    }


    public void setPresenter(presenter presenter) {
        this.presenter = presenter;
        if (adapter == null)
            adapter = new SessionListAdapter(getActivity(), R.layout.session_row, presenter, getSessions());
        lvSchedule.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (savedInstanceState != null && presenter == null) {
            presenter = savedInstanceState.getParcelable(getActivity().getResources().getString(R.string.PARCELABLE_PRESENTER));
        }
        View v = inflater.inflate(R.layout.presenter_detail_fragment, container, false);
        lvSchedule = (ListView) v.findViewById(R.id.lvSchedule);
        if (adapter == null && presenter != null) {
            getSessions();
            adapter = new SessionListAdapter(getActivity(), R.layout.session_row, presenter, getSessions());
            lvSchedule.setAdapter(adapter);
        }
        lvSchedule.setOnTouchListener((v12, event) -> {
            v12.getParent().requestDisallowInterceptTouchEvent(true);
            return false;
        });
        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(getActivity().getResources().getString(R.string.PARCELABLE_PRESENTER), presenter);
    }

    @Override
    public void onPause() {
        super.onPause();
        onSaveInstanceState(new Bundle());
    }

    @Override
    public void onDetach() {
        super.onDetach();
        activity = null;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        lvSchedule = null;
        rl_back = null;
        ivPresenter = null;
        presenter = null;
        adapter = null;
    }
}

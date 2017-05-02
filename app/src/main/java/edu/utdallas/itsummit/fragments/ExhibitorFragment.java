package edu.utdallas.itsummit.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import edu.utdallas.itsummit.R;
import edu.utdallas.itsummit.activities.MainActivity;
import edu.utdallas.itsummit.adapters.ExhibitorAdapter;
import edu.utdallas.itsummit.models.Exhibitors;
import edu.utdallas.itsummit.models.jsonmodels.exhibitor;
import edu.utdallas.itsummit.serverapi.DataGetter;

/**
 * Created by sxk159231 on 7/15/2016.
 */
public class ExhibitorFragment extends Fragment {

    List<exhibitor> exhibitorList = new ArrayList<>();
    private final String TAG = getClass().getSimpleName();
    ExhibitorAdapter adapter;

    public static ExhibitorFragment newInstance() {
        return new ExhibitorFragment();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.exhibitorList.clear();
        this.exhibitorList.addAll(((Exhibitors) ((MainActivity) getActivity()).getDataGetter().readFromRealm(DataGetter.DataType.EXHIBITOR)).getExhibitors());
    }

    public void refreshExhibitors() {
        if (adapter != null) adapter.notifyDataSetChanged();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.exhibitor_fragment, container, false);
        ListView lv = (ListView) v.findViewById(R.id.lvExhibitors);
        Collections.sort(exhibitorList);
        if (adapter == null) {
            adapter = new ExhibitorAdapter(getActivity(), exhibitorList);
            lv.setAdapter(adapter);
        } else adapter.notifyDataSetChanged();
        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        adapter = null;
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}

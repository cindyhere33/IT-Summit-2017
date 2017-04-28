package edu.utdallas.itsummit2017.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import edu.utdallas.itsummit2017.R;
import edu.utdallas.itsummit2017.activities.MainActivity;
import edu.utdallas.itsummit2017.adapters.MapsAdapter;
import edu.utdallas.itsummit2017.models.Maps;
import edu.utdallas.itsummit2017.models.jsonmodels.map;
import edu.utdallas.itsummit2017.serverapi.DataGetter;


/**
 * Created by sxk159231 on 4/13/2017.
 */
public class MapFragment extends Fragment {

    ListView lvMaps;
    private final String TAG = getClass().getName();
    List<map> maps = new ArrayList<>();
    MapsAdapter adapter;

    public static MapFragment newInstance() {
        return new MapFragment();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        maps.clear();
        maps.addAll(((Maps) ((MainActivity) getActivity()).getDataGetter().readFromRealm(DataGetter.DataType.MAP)).getMaps());
    }

    public void refreshMaps() {
        if (adapter != null) adapter.notifyDataSetChanged();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.map_fragment, container, false);
        lvMaps = (ListView) v.findViewById(R.id.lvMaps);
        if (maps == null) maps = new ArrayList<>();
        adapter = new MapsAdapter(getActivity(), maps);
        lvMaps.setAdapter(adapter);
        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        adapter = null;
    }
}

package edu.utdallas.itsummit2017.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import edu.utdallas.itsummit2017.R;
import edu.utdallas.itsummit2017.activities.MainActivity;
import edu.utdallas.itsummit2017.adapters.SponsorAdapter;
import edu.utdallas.itsummit2017.models.SponsorLevels;
import edu.utdallas.itsummit2017.models.Sponsors;
import edu.utdallas.itsummit2017.models.jsonmodels.sponsor;
import edu.utdallas.itsummit2017.models.jsonmodels.sponsorLevel;
import edu.utdallas.itsummit2017.serverapi.DataGetter;

/**
 * Created by sxk159231 on 4/12/2017.
 */
public class SponsorFragment extends Fragment {

    ExpandableListView elvSponsors;
    List<sponsor> sponsorList = new ArrayList<>();
    List<String> categories = new ArrayList<>();
    HashMap<String, List<sponsor>> sponsorMap = new HashMap<>();
    List<sponsorLevel> sponsorLevels = new ArrayList<>();
    private final String TAG = getClass().getSimpleName();
    SponsorAdapter adapter;

    public static SponsorFragment newInstance() {
        return new SponsorFragment();
    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.sponsorList.clear();
        this.sponsorList.addAll(((Sponsors) ((MainActivity) getActivity()).getDataGetter().readFromRealm(DataGetter.DataType.SPONSOR)).getSponsors());
        sponsorLevels.addAll(((SponsorLevels) ((MainActivity) getActivity()).getDataGetter().readFromRealm(DataGetter.DataType.SPONSORLEVEL)).getSponsorLevels());
        sponsorMap = createMap(sponsorLevels, sponsorList);
        if (sponsorList.size() > 0 && elvSponsors != null) elvSponsors.setEnabled(true);
        if (adapter != null) adapter.notifyDataSetChanged();
    }

    private HashMap<String, List<sponsor>> createMap(List<sponsorLevel> sponsorLevels, List<sponsor> sponsors) {
        for (sponsorLevel level : sponsorLevels) {
            List<sponsor> list = new ArrayList<>();
            for (sponsor sponsor : sponsors) {
                if (sponsor.getLevel().equals(level.getID())) {
                    list.add(sponsor);
                }
            }
            sponsorMap.put(level.getLevel(), list);
        }
        return sponsorMap;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.sponsors_fragment, container, false);
        ((MainActivity) getActivity()).setHeading("Sponsors");
        elvSponsors = (ExpandableListView) v.findViewById(R.id.elvSponsors);
        if (adapter == null)
            adapter = new SponsorAdapter(getActivity(), new ArrayList<>(sponsorMap.keySet()), sponsorMap);
        else adapter.notifyDataSetChanged();
        elvSponsors.setAdapter(adapter);
        for (int i = 0; i < categories.size(); i++) {
            elvSponsors.expandGroup(i);
        }
        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        elvSponsors = null;
        adapter = null;
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

}

package edu.utdallas.itsummit2017.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import java.util.ArrayList;
import java.util.List;

import edu.utdallas.itsummit2017.R;
import edu.utdallas.itsummit2017.activities.MainActivity;
import edu.utdallas.itsummit2017.adapters.EventAdapter;
import edu.utdallas.itsummit2017.models.Events;
import edu.utdallas.itsummit2017.serverapi.DataGetter;

/**
 * Created by sxk159231 on 4/7/2017.
 */

public class WelcomeFragment extends Fragment {

    public static WelcomeFragment newInstance() {
        return new WelcomeFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.event, container, false);
        ExpandableListView elv = (ExpandableListView) view.findViewById(R.id.elvEvents);
        Events event = (Events) ((MainActivity) getActivity()).getDataGetter().readFromRealm(DataGetter.DataType.EVENT);
        if (event != null) {
            elv.setAdapter(new EventAdapter(getActivity(), getGroupHeadings(), event.getEvent()));
        }
        return view;
    }

    private List<String> getGroupHeadings() {
        List<String> headings = new ArrayList<>();
        headings.add(getActivity().getResources().getString(R.string.EVENT_GROUP_1));
        headings.add(getActivity().getResources().getString(R.string.EVENT_GROUP_2));
        headings.add(getActivity().getResources().getString(R.string.EVENT_GROUP_3));
        return headings;
    }
}

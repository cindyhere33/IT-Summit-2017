package edu.utdallas.itsummit.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import java.util.ArrayList;
import java.util.List;

import edu.utdallas.itsummit.R;
import edu.utdallas.itsummit.activities.MainActivity;
import edu.utdallas.itsummit.adapters.EventAdapter;
import edu.utdallas.itsummit.models.Events;
import edu.utdallas.itsummit.serverapi.DataGetter;

/**
 * Created by sxk159231 on 4/7/2017.
 */

public class WelcomeFragment extends Fragment {

    public static WelcomeFragment newInstance() {
        return new WelcomeFragment();
    }

    static EventAdapter adapter;
    static Events mainEvent;
    private final String TAG = getClass().getSimpleName();

    public static void updateEvents(Events event) {
        if (event != null) {
            mainEvent = event;
            adapter.notifyDataSetChanged();
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.event, container, false);
        ExpandableListView elv = (ExpandableListView) view.findViewById(R.id.elvEvents);
        mainEvent = (Events) ((MainActivity) getActivity()).getDataGetter().readFromRealm(DataGetter.DataType.EVENT);
        if (mainEvent != null) {
            adapter = new EventAdapter(getActivity(), getGroupHeadings(), mainEvent.getEvent());
            elv.setAdapter(adapter);
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

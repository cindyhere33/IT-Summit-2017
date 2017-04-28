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
import java.util.Collections;
import java.util.List;

import edu.utdallas.itsummit2017.R;
import edu.utdallas.itsummit2017.activities.MainActivity;
import edu.utdallas.itsummit2017.adapters.NewsAdapter;
import edu.utdallas.itsummit2017.models.NewsItems;
import edu.utdallas.itsummit2017.models.jsonmodels.news;
import edu.utdallas.itsummit2017.serverapi.DataGetter;


/**
 * Created by sxk159231 on 7/15/2016.
 */
public class NewsFragment extends Fragment {

    private final String TAG = getClass().getName();
    ListView lv;
    List<news> news = new ArrayList<>();
    NewsAdapter adapter;

    public static NewsFragment newInstance() {
        return new NewsFragment();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.news.clear();
        this.news.addAll(((NewsItems) ((MainActivity) getActivity()).getDataGetter().readFromRealm(DataGetter.DataType.NEWS)).getNews());
        if (news != null && news.size() > 0 && lv != null) lv.setEnabled(true);
    }

    public void refreshNews() {
        if (adapter != null) adapter.notifyDataSetChanged();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.news_fragment, container, false);
        lv = (ListView) view.findViewById(R.id.lvNews);
        Collections.sort(news);
        adapter = new NewsAdapter(getActivity(), news);
        lv.setAdapter(adapter);
        if (news == null) lv.setEnabled(false);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        adapter = null;
        lv = null;
    }
}

package edu.utdallas.itsummit2017.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import edu.utdallas.itsummit2017.R;
import edu.utdallas.itsummit2017.models.jsonmodels.news;


/**
 * Created by sxk159231 on 4/13/2017.
 */
public class DetailNewsFragment extends Fragment {
    private final String TAG = getClass().getSimpleName();
    news news;
    TextView tvTitle, tvNews, tvDesc;

    public static DetailNewsFragment newInstance(news news) {
        DetailNewsFragment fragment = new DetailNewsFragment();
        fragment.news = news;
        return fragment;
    }


    public void setNews(news news) {
        this.news = news;
        redrawView();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.news_detail_fragment, container, false);
        if (savedInstanceState != null && news == null) {
            news = savedInstanceState.getParcelable(getActivity().getResources().getString(R.string.PARCELABLE_NEWS));
        }
        tvTitle = (TextView) view.findViewById(R.id.tvTitleNews);
        tvNews = (TextView) view.findViewById(R.id.tvTimeNews);
        tvDesc = (TextView) view.findViewById(R.id.tvContentNews);
        tvTitle.setText(news.getTitle());
        tvNews.setText(news.getNews_Time());
        tvDesc.setText(news.getMessage());
        return view;
    }

    private void redrawView() {
        if (tvTitle != null) tvTitle.setText(news.getTitle());
        if (tvNews != null) tvNews.setText(news.getNews_Time());
        if (tvDesc != null) tvDesc.setText(news.getMessage());
    }


    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(getActivity().getResources().getString(R.string.PARCELABLE_NEWS), news);
    }
}

package edu.utdallas.itsummit2017.adapters;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import edu.utdallas.itsummit2017.R;
import edu.utdallas.itsummit2017.activities.MainActivity;
import edu.utdallas.itsummit2017.fragments.DetailNewsFragment;
import edu.utdallas.itsummit2017.models.jsonmodels.news;
import edu.utdallas.itsummit2017.utils.Utils;

/**
 * Created by sxk159231 on 4/13/2017.
 */
public class NewsAdapter extends ArrayAdapter<news> {

    Context context;
    private List<news> news;
    private SharedPreferences sharedPrefs;
    private final String TAG = getClass().getSimpleName();


    public NewsAdapter(Context context, List<news> newsList) {
        super(context, R.layout.news_lv, newsList);
        this.context = context;
        this.news = newsList;
        sharedPrefs = PreferenceManager.getDefaultSharedPreferences(getContext());
    }

    @Override
    public int getCount() {
        return news.size();
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        final news news = this.news.get(position);
        convertView = LayoutInflater.from(getContext()).inflate(R.layout.news_lv, parent, false);
        TextView tvNews = (TextView) convertView.findViewById(R.id.tvTitleNews);
        ImageView ivNew = (ImageView) convertView.findViewById(R.id.ivNewNews);
        ImageView ivMore = (ImageView) convertView.findViewById(R.id.ivMoreNews);
        TextView tvDesc = (TextView) convertView.findViewById(R.id.tvDescriptionNews);
        tvNews.setText(news.getTitle());
        tvDesc.setText(news.getNews_Time());
        if (!isNewsRead(news)) ivNew.setVisibility(View.VISIBLE);
        else ivNew.setVisibility(View.INVISIBLE);
        convertView.setOnClickListener(v -> {
            Utils.hideKeyboard(v);
            ((MainActivity) context).getSupportFragmentManager().beginTransaction().add(R.id.frame, DetailNewsFragment.newInstance(news), context.getResources().getString(R.string.KEY_NEWS_DETAIL)).addToBackStack(context.getResources().getString(R.string.KEY_NEWS_DETAIL)).commit();
            markAsRead(news);
        });
        ivNew.setImageDrawable(getContext().getResources().getDrawable(R.drawable.circle));
        return convertView;
    }

    private boolean isNewsRead(news news) {
        return sharedPrefs.getBoolean(context.getString(R.string.KEY_READ_NEWS) + news.getID(), false);
    }

    private void markAsRead(news news) {
        if (!isNewsRead(news)) {
            SharedPreferences.Editor editor = sharedPrefs.edit();
            editor.putBoolean(context.getString(R.string.KEY_READ_NEWS) + news.getID(), true);
            editor.commit();
        }
    }

}

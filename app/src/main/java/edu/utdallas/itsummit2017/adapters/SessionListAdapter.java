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

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

import java.util.ArrayList;
import java.util.List;

import edu.utdallas.itsummit2017.R;
import edu.utdallas.itsummit2017.activities.MainActivity;
import edu.utdallas.itsummit2017.fragments.DetailSessionFragment;
import edu.utdallas.itsummit2017.models.jsonmodels.presenter;
import edu.utdallas.itsummit2017.models.jsonmodels.session;
import edu.utdallas.itsummit2017.utils.Utils;
import edu.utdallas.itsummit2017.utils.VolleySingleton;

/**
 * Created by sxk159231 on 4/13/2017.
 */
public class SessionListAdapter extends ArrayAdapter<session> {

    Context context;
    private LayoutInflater inflater;
    private final String TAG = getClass().getSimpleName();
    ;
    private List<session> sessionList = new ArrayList<>();
    private SharedPreferences sharedPrefs;
    private SharedPreferences.Editor editor;
    private presenter presenter;
    private ImageLoader loader;

    public SessionListAdapter(Context context, int resource, presenter presenters, List<session> objects) {
        super(context, resource, objects);
        this.context = context;
        sessionList = objects;
        inflater = LayoutInflater.from(context);
        sharedPrefs = PreferenceManager.getDefaultSharedPreferences(context);
        editor = sharedPrefs.edit();
        this.presenter = presenters;
        this.loader = VolleySingleton.getInstance().getImageLoader();
    }

    @Override
    public int getCount() {
        return super.getCount() + 3;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        if (position == 0) {
            convertView = inflater.inflate(R.layout.presenter_detail_name_image, parent, false);
            TextView tvName = (TextView) convertView.findViewById(R.id.tvNamePresenter);
            TextView tvDetail = (TextView) convertView.findViewById(R.id.tvDetailPresenter);
            NetworkImageView ivPresenter = (NetworkImageView) convertView.findViewById(R.id.ivPresenterImage);
            tvName.setText(presenter.getFirst_Name() + " " + presenter.getMiddle_Name() + " " + presenter.getLast_Name());
            tvDetail.setText(presenter.getTitle() + "\n" + presenter.getCompany());
            if (presenter.getImage_URL().length() > 0)
                ivPresenter.setImageUrl(context.getString(R.string.IMAGE_BASE_URL) + presenter.getImage_URL(), loader);
            ivPresenter.setDefaultImageResId(R.drawable.ic_nopic);
            return convertView;
        } else if (position == 1) {
            convertView = inflater.inflate(R.layout.presenter_detail_bio, parent, false);
            TextView tvContent = (TextView) convertView.findViewById(R.id.tvContentPresenter);
            tvContent.setText(presenter.getBIO());
            return convertView;
        } else if (position == 2) {
            convertView = inflater.inflate(R.layout.presenter_detail_schedule_header, parent, false);
            return convertView;
        }

        convertView = inflater.inflate(R.layout.session_row, parent, false);
        TextView tvTitle = (TextView) convertView.findViewById(R.id.tvScheduleTitle);
        TextView tvTime = (TextView) convertView.findViewById(R.id.tvScheduleTime);
        ImageView ivStar = (ImageView) convertView.findViewById(R.id.ivStarScheduleList);
        final session session = sessionList.get(position - 3);
        tvTitle.setText(session.getTitle());
        String time = session.getTimeAndDate().getStartTime() + " - " + session.getTimeAndDate().getEndTime() + "; " + session.getRoomString();
        tvTime.setText(time);
        convertView.setOnClickListener(v -> {
            Utils.hideKeyboard(v);
            ((MainActivity) context).getSupportFragmentManager().beginTransaction().add(R.id.frame, DetailSessionFragment.newInstance(session), "Session Details").addToBackStack("Session Details").commit();
        });
        Utils.setupFavStar(context, session.getID(), ivStar);
        return convertView;
    }

}

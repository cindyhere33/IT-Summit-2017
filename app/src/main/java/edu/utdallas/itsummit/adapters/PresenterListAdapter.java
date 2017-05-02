package edu.utdallas.itsummit.adapters;

import android.content.Context;
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
import java.util.Collections;
import java.util.List;

import edu.utdallas.itsummit.R;
import edu.utdallas.itsummit.activities.MainActivity;
import edu.utdallas.itsummit.fragments.DetailPresenterFragment;
import edu.utdallas.itsummit.models.Presenters;
import edu.utdallas.itsummit.models.RealmString;
import edu.utdallas.itsummit.models.Rooms;
import edu.utdallas.itsummit.models.TimeAndDate;
import edu.utdallas.itsummit.models.jsonmodels.presenter;
import edu.utdallas.itsummit.models.jsonmodels.room;
import edu.utdallas.itsummit.models.jsonmodels.session;
import edu.utdallas.itsummit.serverapi.DataGetter;
import edu.utdallas.itsummit.utils.Utils;
import edu.utdallas.itsummit.utils.VolleySingleton;

import static edu.utdallas.itsummit.serverapi.DataGetter.DataType.PRESENTER;

/**
 * Created by sxk159231 on 4/13/2017.
 */

public class PresenterListAdapter extends ArrayAdapter {

    Context context;
    private List<presenter> presenters = new ArrayList<>();
    private LayoutInflater inflater;
    private final String TAG = getClass().getSimpleName();
    private ImageLoader imageLoader;
    private String image_url;
    private session session;

    public PresenterListAdapter(Context context, session session) {
        super(context, R.layout.session_detail_presenter_row);
        this.context = context;
        this.session = session;
        inflater = LayoutInflater.from(context);
        imageLoader = VolleySingleton.getInstance().getImageLoader();
        image_url = context.getResources().getString(R.string.IMAGE_BASE_URL);
        initPresenters();
    }

    private List<presenter> initPresenters() {
        presenters.clear();
        List<presenter> allPresenters = ((Presenters) ((MainActivity) context).getDataGetter().readFromRealm(PRESENTER)).getPresenters();
        for (presenter presenter : allPresenters) {
            for (RealmString id : session.getPresenters()) {
                if (id.getData().trim().equalsIgnoreCase(presenter.getID()))
                    presenters.add(presenter);
            }
        }
        Collections.sort(presenters);
        return presenters;
    }

    @Override
    public int getCount() {
        return presenters.size() + 1;
    }

    @NonNull
    @Override
    public View getView(final int position, View convertView, @NonNull ViewGroup parent) {
        if (position == 0) {
            convertView = inflater.inflate(R.layout.session_detail_heading_description, parent, false);
            TextView tvTitle = (TextView) convertView.findViewById(R.id.tvTitleSession);
            TextView tvLocation = (TextView) convertView.findViewById(R.id.tvLocationSession);
            TextView tvContent = (TextView) convertView.findViewById(R.id.tvContentSession);
            ImageView ivStar = (ImageView) convertView.findViewById(R.id.star);
            Utils.setupFavStar(context, session.getID(), session.getTitle(), ivStar);
            tvTitle.setText(session.getTitle());
            TimeAndDate date = session.getTimeAndDate();
            room room = session.getRoom(((Rooms) ((MainActivity) context).getDataGetter().readFromRealm(DataGetter.DataType.ROOM)).getRooms());
            String sessionDate = date.getWeekDay() + ", " + date.getDate() + "\n" + date.getStartTime() + " - " + date.getEndTime() + "; " + room.getName() + "; " + room.getFloor() + "; " + room.getBuilding();
            tvLocation.setText(sessionDate);
            String description = "";
            if (!session.getSession_Track().equalsIgnoreCase("Blank")) {
                description = "Track : " + session.getSession_Track();
            }
            tvContent.setText(description + "\n\n" + session.getDescription());
        } else {
            TextView tvName, tvDetail;
            NetworkImageView ivPhoto;
            convertView = inflater.inflate(R.layout.session_detail_presenter_row, parent, false);
            ivPhoto = (NetworkImageView) convertView.findViewById(R.id.ivPresenterImage);
            tvName = (TextView) convertView.findViewById(R.id.tvNamePresenter);
            tvDetail = (TextView) convertView.findViewById(R.id.tvDetailPresenter);
            String name = presenters.get(position - 1).getFirst_Name() + " " + presenters.get(position - 1).getMiddle_Name() + " " + presenters.get(position - 1).getLast_Name();
            tvName.setText(name);
            tvDetail.setText(presenters.get(position - 1).getTitle() + "\n" + presenters.get(position - 1).getCompany());
            convertView.setOnClickListener(v -> {
                Utils.hideKeyboard(v);
                Utils.reportEvent(context, "Open Presenter", presenters.get(position - 1).getTitle());
                ((MainActivity) getContext()).getSupportFragmentManager().beginTransaction().add(R.id.frame, DetailPresenterFragment.newInstance(presenters.get(position - 1)), "Presenter Details").addToBackStack("Presenter Details").commit();
            });
            ivPhoto.setDefaultImageResId(R.drawable.ic_nopic);
            if (!(presenters.get(position - 1).getImage_URL().length() == 0))
                ivPhoto.setImageUrl(image_url + presenters.get(position - 1).getImage_URL(), imageLoader);

            View line = convertView.findViewById(R.id.line);
            if (position == presenters.size()) line.setVisibility(View.GONE);
            else line.setVisibility(View.VISIBLE);
        }
        return convertView;
    }


}

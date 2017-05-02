package edu.utdallas.itsummit.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;

import edu.utdallas.itsummit.R;
import edu.utdallas.itsummit.activities.MainActivity;
import edu.utdallas.itsummit.fragments.DetailSessionFragment;
import edu.utdallas.itsummit.models.Rooms;
import edu.utdallas.itsummit.models.TimeAndDate;
import edu.utdallas.itsummit.models.jsonmodels.session;
import edu.utdallas.itsummit.serverapi.DataGetter;
import edu.utdallas.itsummit.utils.Utils;


/**
 * Created by sxk159231 on 4/13/2017.
 */
public class SessionsAdapter extends BaseExpandableListAdapter {

    private HashMap<String, List<session>> sessionsList;
    private List<String> sessionHeadings;
    Context context;
    private final String TAG = getClass().getSimpleName();

    public SessionsAdapter(Context context, HashMap<String, List<session>> timeslots, List<String> sessionHeadings) {
        this.context = context;
        sessionsList = timeslots;
        this.sessionHeadings = sessionHeadings;
    }

    @Override
    public int getGroupCount() {
        return sessionsList.keySet().size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return sessionsList.get(sessionHeadings.get(groupPosition)).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return sessionHeadings.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return sessionsList.get((String) sessionsList.keySet().toArray()[groupPosition]);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return groupPosition + childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        View v = LayoutInflater.from(context).inflate(R.layout.session_elv_group, parent, false);
        TextView tvGroup = (TextView) v.findViewById(R.id.dateGroupSession);
        tvGroup.setText(sessionHeadings.get(groupPosition));
        return v;
    }

    @Override
    public View getChildView(final int groupPosition, final int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        if (convertView == null)
            convertView = LayoutInflater.from(context).inflate(R.layout.session_elv_child, parent, false);

        final ImageView ivFav = (ImageView) convertView.findViewById(R.id.ivFav);
        View line = convertView.findViewById(R.id.v);
        if (childPosition == sessionsList.get(sessionHeadings.get(groupPosition)).size() - 1)
            line.setVisibility(View.GONE);
        else line.setVisibility(View.VISIBLE);
        final session session = sessionsList.get(sessionHeadings.get(groupPosition)).get(childPosition);
        TextView tvtime = (TextView) convertView.findViewById(R.id.tvSessionTimeAllSchedule);
        TimeAndDate date = session.getTimeAndDate();
        String sessionDate = date.getStartTime() + " - " + date.getEndTime() + "; " + session.getRoom(((Rooms) ((MainActivity) context).getDataGetter().readFromRealm(DataGetter.DataType.ROOM)).getRooms()).getName();
        tvtime.setText(sessionDate);
        TextView heading = (TextView) convertView.findViewById(R.id.tvSessionAllSchedule);
        heading.setText(session.getTitle());
        convertView.setOnClickListener(v -> {
            Utils.hideKeyboard(v);
            Utils.reportEvent(context, "Open Session", session.getTitle());
            ((MainActivity) context).getSupportFragmentManager().beginTransaction().add(R.id.frame, DetailSessionFragment.newInstance(session), context.getResources().getString(R.string.KEY_SESSION_DETAIL)).addToBackStack(context.getResources().getString(R.string.KEY_SESSION_DETAIL)).commit();
        });
        Utils.setupFavStar(context, session.getID(), session.getTitle(), ivFav);
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }


}

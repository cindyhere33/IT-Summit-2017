package edu.utdallas.itsummit.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.TextView;

import java.util.List;

import edu.utdallas.itsummit.R;
import edu.utdallas.itsummit.activities.MainActivity;
import edu.utdallas.itsummit.fragments.SponsorFragment;
import edu.utdallas.itsummit.models.jsonmodels.event;

/**
 * Created by sxk159231 on 2/1/2016.
 */
public class EventAdapter extends BaseExpandableListAdapter {

    Context context;
    private List<String> groupHeadings;
    private event event;
    private LayoutInflater inflater;
    private final String TAG = getClass().getSimpleName();

    public EventAdapter(Context context, List<String> headings, event event) {
        super();
        groupHeadings = headings;
        this.event = event;
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getGroupCount() {
        return groupHeadings.size() + 1;
    }

    public void setEvent(event event) {
        this.event = event;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        if (groupPosition == 3 || groupPosition == 0) return 0;
        return 1;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return groupHeadings.get(groupPosition - 1);
    }

    @Override
    public String getChild(int groupPosition, int childPosition) {
        if (groupHeadings.get(groupPosition).equalsIgnoreCase(context.getResources().getString(R.string.EVENT_GROUP_1)))
            return event.getDescription();
        else if (groupHeadings.get(groupPosition).equalsIgnoreCase(context.getResources().getString(R.string.EVENT_GROUP_2)))
            return event.getDetails();
        else if (groupHeadings.get(groupPosition).equalsIgnoreCase(context.getResources().getString(R.string.EVENT_GROUP_3)))
            return null;
        return null;
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
        if (groupPosition == 0) {
            convertView = inflater.inflate(R.layout.event_header, parent, false);
            TextView tvDate = (TextView) convertView.findViewById(R.id.tvDateEvents);
            TextView tvLocation = (TextView) convertView.findViewById(R.id.tvLocationEvents);
            tvDate.setText(event.getEventDate());
            tvLocation.setText(event.getLocation());
            return convertView;
        }
        convertView = inflater.inflate(R.layout.event_elv_group, parent, false);
        TextView tvHeading = (TextView) convertView.findViewById(R.id.tvGroupEventList);
        tvHeading.setText(groupHeadings.get(groupPosition - 1));
        if (groupHeadings.get(groupPosition - 1).equalsIgnoreCase("Sponsors")) {
            convertView.setOnClickListener(v -> ((MainActivity) context).getSupportFragmentManager().beginTransaction().add(R.id.frame, SponsorFragment.newInstance(), "Sponsors").addToBackStack("Sponsors").commit());
        } else
            convertView.setOnClickListener(view -> {
                ExpandableListView elv = (ExpandableListView) parent;
                if (elv.isGroupExpanded(groupPosition)) elv.collapseGroup(groupPosition);
                else elv.expandGroup(groupPosition);
            });
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        convertView = inflater.inflate(R.layout.event_elv_child, parent, false);
        TextView tvContent = (TextView) convertView.findViewById(R.id.tvContentEventList);
        if (groupPosition > 0)
            tvContent.setText(getChild(groupPosition - 1, 0));
        return convertView;
    }

    @Override
    public void onGroupExpanded(int groupPosition) {
        super.onGroupExpanded(groupPosition);
        Log.d(TAG, "Attempt to expand " + groupPosition);
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }

    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }
}

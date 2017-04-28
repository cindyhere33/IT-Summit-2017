package edu.utdallas.itsummit2017.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import edu.utdallas.itsummit2017.R;
import edu.utdallas.itsummit2017.activities.MainActivity;
import edu.utdallas.itsummit2017.fragments.DetailSponsorFragment;
import edu.utdallas.itsummit2017.models.jsonmodels.sponsor;
import edu.utdallas.itsummit2017.utils.Utils;
import edu.utdallas.itsummit2017.utils.VolleySingleton;


/**
 * Created by sxk159231 on 4/13/2017.
 */
public class SponsorAdapter extends BaseExpandableListAdapter {

    private List<String> categories = new ArrayList<>();
    private HashMap<String, List<sponsor>> sponsors = new HashMap<>();
    Context context;
    private LayoutInflater inflater;
    private ImageLoader loader;
    private final String TAG = getClass().getSimpleName();

    public SponsorAdapter(Context context, List<String> categories, HashMap<String, List<sponsor>> sponsors) {
        this.context = context;
        this.sponsors = sponsors;
        this.categories = categories;
        inflater = LayoutInflater.from(context);
        loader = VolleySingleton.getInstance().getImageLoader();
    }

    @Override
    public int getGroupCount() {
        return categories.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return sponsors.get(categories.get(groupPosition)).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return categories.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return sponsors.get(categories.get(groupPosition)).get(childPosition).getName();
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        convertView = inflater.inflate(R.layout.sponsor_elv_group, parent, false);
        TextView tvCategory = (TextView) convertView.findViewById(R.id.tvGroupSponsor);
        tvCategory.setText(categories.get(groupPosition));
        ExpandableListView mExpandableListView = (ExpandableListView) parent;
        mExpandableListView.expandGroup(groupPosition);
        return convertView;
    }

    @Override
    public View getChildView(final int groupPosition, final int childPosition, boolean isLastChild, View view, ViewGroup parent) {
        View convertView = inflater.inflate(R.layout.sponsor_elv_child, parent, false);
        final sponsor sponsor = sponsors.get(categories.get(groupPosition)).get(childPosition);
        TextView tvCompany = (TextView) convertView.findViewById(R.id.tvChildSponsor);
        NetworkImageView ivLogo = (NetworkImageView) convertView.findViewById(R.id.ivLogoSponsor);
        tvCompany.setText(sponsor.getName());
        RelativeLayout rl = (RelativeLayout) convertView.findViewById(R.id.rlSponsors);
        rl.setOnClickListener(v -> {
            Utils.hideKeyboard(v);
            ((MainActivity) context).getSupportFragmentManager().beginTransaction().add(R.id.frame, DetailSponsorFragment.newInstance(sponsor), sponsor.getName()).addToBackStack(sponsor.getName()).commit();
        });
        View v = convertView.findViewById(R.id.divider);
        if (sponsors.get(categories.get(groupPosition)).size() - 1 == childPosition && groupPosition != categories.size() - 1) {
            v.setVisibility(View.GONE);
        } else v.setVisibility(View.VISIBLE);
        ivLogo.setImageUrl(context.getResources().getString(R.string.IMAGE_BASE_URL) + sponsor.getLogo_URL(), loader);
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }

}

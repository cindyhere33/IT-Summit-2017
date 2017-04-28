package edu.utdallas.itsummit2017.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

import java.util.HashMap;
import java.util.List;

import edu.utdallas.itsummit2017.R;
import edu.utdallas.itsummit2017.activities.MainActivity;
import edu.utdallas.itsummit2017.fragments.DetailPresenterFragment;
import edu.utdallas.itsummit2017.models.jsonmodels.presenter;
import edu.utdallas.itsummit2017.utils.VolleySingleton;

/**
 * Created by sxk159231 on 4/13/2017.
 */

public class PresenterAdapter extends BaseExpandableListAdapter {

    Context context;
    private List<Character> groups;
    private HashMap<Character, List<presenter>> namesMap;
    private ImageLoader imageLoader;


    private final String TAG = getClass().getSimpleName();

    public PresenterAdapter(Context context, List<Character> headings, HashMap<Character, List<presenter>> nameMap) {
        this.context = context;
        this.groups = headings;
        this.namesMap = nameMap;
        imageLoader = VolleySingleton.getInstance().getImageLoader();
    }

    @Override
    public int getGroupCount() {
        return groups.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return namesMap.get(groups.get(groupPosition)).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return groups.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return namesMap.get(groups.get(groupPosition)).get(childPosition);
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
        convertView = LayoutInflater.from(context).inflate(R.layout.presenter_elv_group, parent, false);
        TextView tvAlphabet = (TextView) convertView.findViewById(R.id.tvAlphabetPresenters);
        ((ExpandableListView) parent).expandGroup(groupPosition);
        tvAlphabet.setText(groups.get(groupPosition).toString());
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(context).inflate(R.layout.presenter_elv_child, parent, false);
        TextView tvName = (TextView) convertView.findViewById(R.id.tvNamePresenter);
        TextView tvOccupation = (TextView) convertView.findViewById(R.id.tvDetailsPresenter);
        TextView tvCompany = (TextView) convertView.findViewById(R.id.tvDetails2Presenter);
        NetworkImageView ivPic = (NetworkImageView) convertView.findViewById(R.id.ivPicPresenter);
        View line = (View) convertView.findViewById(R.id.vPresenterChild);

        if (childPosition == namesMap.get(groups.get(groupPosition)).size() - 1) {
            line.setVisibility(View.GONE);
        } else line.setVisibility(View.VISIBLE);
        final presenter presenter = namesMap.get(groups.get(groupPosition)).get(childPosition);
        tvName.setText(presenter.getFirst_Name() + " " + presenter.getMiddle_Name() + " " + presenter.getLast_Name());
        tvCompany.setText(presenter.getCompany());
        tvOccupation.setText(presenter.getTitle());
        convertView.setOnClickListener(v -> {
            InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
            ((MainActivity) context).getSupportFragmentManager().beginTransaction().add(R.id.frame, DetailPresenterFragment.newInstance(presenter), context.getResources().getString(R.string.KEY_PRESENTER_DETAIL)).addToBackStack(context.getResources().getString(R.string.KEY_PRESENTER_DETAIL)).commit();
        });
        ivPic.setDefaultImageResId(R.drawable.ic_nopic);
        if (presenter.getImage_URL().length() == 0)
            ivPic.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_nopic));
        else
            ivPic.setImageUrl(context.getResources().getString(R.string.IMAGE_BASE_URL) + presenter.getImage_URL(), imageLoader);
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }

}

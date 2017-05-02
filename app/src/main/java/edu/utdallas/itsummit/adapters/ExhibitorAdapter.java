package edu.utdallas.itsummit.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import edu.utdallas.itsummit.R;
import edu.utdallas.itsummit.activities.MainActivity;
import edu.utdallas.itsummit.fragments.DetailExhibitorFragment;
import edu.utdallas.itsummit.models.jsonmodels.exhibitor;
import edu.utdallas.itsummit.utils.Utils;


/**
 * Created by sxk159231 on 4/14/2017.
 */
public class ExhibitorAdapter extends ArrayAdapter<exhibitor> {
    Context context;
    private final String TAG = getClass().getSimpleName();
    private List<exhibitor> exhibitorList = new ArrayList<>();
    private LayoutInflater inflater;

    public ExhibitorAdapter(Context context, List<exhibitor> objects) {
        super(context, R.layout.exhibitor_lv, objects);
        this.context = context;
        this.exhibitorList = objects;
        inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        View v = inflater.inflate(R.layout.exhibitor_lv, parent, false);
        TextView tvTitle = (TextView) v.findViewById(R.id.tvTitleExhibitors);
        TextView tvLocation = (TextView) v.findViewById(R.id.tvLocationExhibitors);
        final exhibitor exhibitor = exhibitorList.get(position);
        tvTitle.setText(exhibitor.getCompany());
        tvLocation.setText(exhibitor.getRoom());
        v.setOnClickListener(v1 -> {
            Utils.hideKeyboard(v);
            Utils.reportEvent(context, "Open Exhibitor", exhibitor.getCompany());
            ((MainActivity) context).getSupportFragmentManager().beginTransaction().add(R.id.frame, DetailExhibitorFragment.newInstance(exhibitor), context.getResources().getString(R.string.KEY_EXHIBITOR_DETAIL)).addToBackStack(context.getResources().getString(R.string.KEY_EXHIBITOR_DETAIL)).commit();
        });
        return v;
    }
}

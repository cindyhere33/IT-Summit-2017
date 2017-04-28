package edu.utdallas.itsummit2017.adapters;

import android.content.Context;
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
import edu.utdallas.itsummit2017.fragments.DetailMapFragment;
import edu.utdallas.itsummit2017.models.jsonmodels.map;
import edu.utdallas.itsummit2017.utils.Utils;


/**
 * Created by sxk159231 on 4/13/2017.
 */
public class MapsAdapter extends ArrayAdapter<map> {

    Context context;
    private List<map> headings;
    private LayoutInflater inflater;
    private final String TAG = getClass().getSimpleName();

    private static class ViewHolderMaps {
        TextView tvTitle;
        ImageView ivMore;
    }


    public MapsAdapter(Context context, List<map> strings) {
        super(context, R.layout.maps_lv, strings);
        this.context = context;
        this.headings = strings;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return headings.size();
    }

    @NonNull
    @Override
    public View getView(final int position, View convertView, @NonNull ViewGroup parent) {
        ViewHolderMaps vhm;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.maps_lv, parent, false);
            vhm = new ViewHolderMaps();
            vhm.tvTitle = (TextView) convertView.findViewById(R.id.tvTitleMaps);
            vhm.ivMore = (ImageView) convertView.findViewById(R.id.arrowMaps);
            convertView.setTag(vhm);
        } else vhm = (ViewHolderMaps) convertView.getTag();
        vhm.tvTitle.setText(headings.get(position).getTitle());
        convertView.setOnClickListener(v -> {
            Utils.hideKeyboard(v);
            ((MainActivity) context).getSupportFragmentManager().beginTransaction().add(R.id.frame, DetailMapFragment.newInstance(headings.get(position)), context.getResources().getString(R.string.KEY_MAP_DETAIL)).addToBackStack(context.getResources().getString(R.string.KEY_MAP_DETAIL)).commit();
        });
        return convertView;
    }
/*
    private static Dialog dialog;
    private static boolean showDialog = false;*/

//    private void openDialog(String url) {
//        dialog = new Dialog(context, android.R.style.Theme_DeviceDefault_Light_NoActionBar);
//        dialog.requestWindowFeature(Window.FEATURE_ACTION_BAR);
//        dialog.setContentView(R.layout.map_detail_fragment);
//        NetworkImageViewZoom iv = (NetworkImageViewZoom) dialog.findViewById(R.id.ivMapImage);
//        ImageLoader il = VolleySingleton.getInstance().getImageLoader();
//        iv.setImageUrl(context.getResources().getString(R.string.IMAGE_BASE_URL) + url, il);
//        ImageView ivClose = (ImageView) dialog.findViewById(R.id.ivCloseMap);
//        ivClose.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (dialog != null && dialog.isShowing()) {
//                    dialog.dismiss();
//                    showDialog = false;
//                }
//            }
//        });
//        if (dialog != null && !dialog.isShowing()) {
//            dialog.show();
//            showDialog = true;
//        }
//    }

}

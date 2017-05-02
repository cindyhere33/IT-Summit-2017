package edu.utdallas.itsummit.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import edu.utdallas.itsummit.R;
import edu.utdallas.itsummit.models.NetworkImageViewZoom;
import edu.utdallas.itsummit.models.jsonmodels.map;
import edu.utdallas.itsummit.utils.VolleySingleton;

/**
 * Created by sxk159231 on 4/14/2017.
 */

public class DetailMapFragment extends Fragment {


    private final String TAG = getClass().getSimpleName();
    map map = new map();

    public static DetailMapFragment newInstance(map map) {
        DetailMapFragment fragment = new DetailMapFragment();
        fragment.map = map;
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.map_detail_fragment, container, false);
        if (savedInstanceState != null) {
            map = savedInstanceState.getParcelable(getActivity().getResources().getString(R.string.PARCELABLE_MAP));
        }
        NetworkImageViewZoom iv = (NetworkImageViewZoom) view.findViewById(R.id.ivMapImage);
        iv.setImageUrl(getActivity().getResources().getString(R.string.IMAGE_BASE_URL) + map.getMap_Image_URL(), VolleySingleton.getInstance().getImageLoader());
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(getActivity().getResources().getString(R.string.PARCELABLE_MAP), map);
    }
}

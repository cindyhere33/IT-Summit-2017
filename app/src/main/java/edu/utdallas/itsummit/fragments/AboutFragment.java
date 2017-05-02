package edu.utdallas.itsummit.fragments;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import edu.utdallas.itsummit.R;


/**
 * Created by sxk159231 on 4/13/2017.
 */
public class AboutFragment extends Fragment {

    private final String TAG = getClass().getSimpleName();

    public static AboutFragment newInstance() {
        return new AboutFragment();
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (getActivity() == null) return null;
        View v = inflater.inflate(R.layout.about_fragment, container, false);
        TextView tvVersion = (TextView) v.findViewById(R.id.tvVersionAbout);
        try {
            tvVersion.setText("Version " + getActivity().getPackageManager().getPackageInfo(getActivity().getPackageName(), 0).versionName);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
    }
}

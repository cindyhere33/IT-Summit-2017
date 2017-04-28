package edu.utdallas.itsummit2017.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import edu.utdallas.itsummit2017.R;
import edu.utdallas.itsummit2017.activities.MainActivity;
import edu.utdallas.itsummit2017.models.SupportItems;
import edu.utdallas.itsummit2017.models.jsonmodels.support;
import edu.utdallas.itsummit2017.serverapi.DataGetter;


/**
 * Created by sxk159231 on 4/13/2017.
 */
public class SupportFragment extends Fragment {

    support support;
    String TAG = getClass().getName();

    public static SupportFragment newInstance() {
        return new SupportFragment();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        support = ((SupportItems) ((MainActivity) getActivity()).getDataGetter().readFromRealm(DataGetter.DataType.SUPPORT)).getSupport();
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.support_fragment, container, false);
        TextView tvSupport = (TextView) v.findViewById(R.id.tvSupport);
        if (support != null) tvSupport.setText(Html.fromHtml(support.getDescription()));
        tvSupport.setMovementMethod(LinkMovementMethod.getInstance());
        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}

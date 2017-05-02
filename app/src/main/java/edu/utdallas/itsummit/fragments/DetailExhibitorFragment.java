package edu.utdallas.itsummit.fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import edu.utdallas.itsummit.R;
import edu.utdallas.itsummit.models.jsonmodels.exhibitor;

/**
 * Created by sxk159231 on 4/14/2017.
 */
public class DetailExhibitorFragment extends Fragment {

    exhibitor exhibitor;
    private final String TAG = getClass().getSimpleName();

    public static DetailExhibitorFragment newInstance(exhibitor exhibitor) {
        final DetailExhibitorFragment fragment = new DetailExhibitorFragment();
        fragment.exhibitor = exhibitor;
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (savedInstanceState != null && exhibitor == null) {
            exhibitor = savedInstanceState.getParcelable(getActivity().getResources().getString(R.string.PARCELABLE_EXHIBITOR));
        }
        View v = inflater.inflate(R.layout.exhibitor_detail_fragment, container, false);
        TextView tvTitle = (TextView) v.findViewById(R.id.tvTitleExhibitors);
        TextView tvDesc = (TextView) v.findViewById(R.id.tvLocationExhibitors);
        TextView tvContent = (TextView) v.findViewById(R.id.tvContentExhibitors);
        TextView tvEmailValue = (TextView) v.findViewById(R.id.tvEmailValueExhibitors);
        TextView tvNameValue = (TextView) v.findViewById(R.id.tvNameValueExhibitors);
        TextView tvPhoneValue = (TextView) v.findViewById(R.id.tvPhoneValueExhibitors);
        tvTitle.setText(exhibitor.getCompany());
        tvDesc.setText(exhibitor.getRoom());
        tvContent.setText(exhibitor.getDescription());
        tvNameValue.setText(exhibitor.getContact());
        tvEmailValue.setText(exhibitor.getEmail());
        tvPhoneValue.setText(exhibitor.getPhone());
        tvEmailValue.setOnClickListener(v12 -> {
            Intent emailIntent = new Intent(Intent.ACTION_SEND);
            emailIntent.setData(Uri.parse("mailto:"));
            emailIntent.setType("text/plain");
            emailIntent.putExtra(Intent.EXTRA_EMAIL, exhibitor.getEmail());
            try {
                startActivity(Intent.createChooser(emailIntent, "Send mail..."));
                Log.i(TAG, "Email sent");
            } catch (android.content.ActivityNotFoundException ex) {
                Toast.makeText(getActivity(), "Sorry there was an error", Toast.LENGTH_SHORT).show();
            }
        });
        tvPhoneValue.setOnClickListener(v1 -> {
            Intent callIntent = new Intent(Intent.ACTION_CALL);
            callIntent.setData(Uri.parse("tel:" + exhibitor.getPhone()));
            startActivity(callIntent);
        });
        return v;
    }


    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(getActivity().getResources().getString(R.string.PARCELABLE_EXHIBITOR), exhibitor);
    }

    @Override
    public void onPause() {
        super.onPause();
        onSaveInstanceState(new Bundle());
    }
}

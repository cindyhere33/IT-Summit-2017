package edu.utdallas.itsummit2017.fragments;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import edu.utdallas.itsummit2017.R;
import edu.utdallas.itsummit2017.models.jsonmodels.sponsor;

/**
 * Created by sxk159231 on 11/21/2016.
 */

public class DetailSponsorFragment extends Fragment {

    private final String TAG = getClass().getSimpleName();
    sponsor sponsor = new sponsor();
    WebView webView;

    public static DetailSponsorFragment newInstance(sponsor sponsor) {
        DetailSponsorFragment fragment = new DetailSponsorFragment();
        fragment.sponsor = sponsor;
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.sponsors_webview_dialog, container, false);
        webView = (WebView) view.findViewById(R.id.webview);
        if (savedInstanceState != null) {
            sponsor = savedInstanceState.getParcelable(getActivity().getResources().getString(R.string.PARCELABLE_SPONSOR));
        }
        if (sponsor.getWeb_URL().length() == 0) {
            Toast.makeText(getActivity(), "Please check your connection to the Internet", Toast.LENGTH_SHORT).show();
            return view;
        }
        SwipeRefreshLayout swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipeToRefreshLayout);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient());
        if (savedInstanceState == null) {
            webView.loadUrl(sponsor.getWeb_URL());
            final ProgressDialog dialog = new ProgressDialog(getActivity());
            dialog.setCancelable(true);
            dialog.setTitle("Loading");
            dialog.setMessage("Please wait");
            dialog.setCancelable(false);
            webView.setWebChromeClient(new WebChromeClient() {
                public void onProgressChanged(WebView view, int progress) {
                    try {
                        if (progress < 90 && !dialog.isShowing()) dialog.show();
                        if (progress >= 90 && dialog.isShowing()) {
                            dialog.dismiss();
                            swipeRefreshLayout.setRefreshing(false);
                        }
                    } catch (Exception e) {
                        Log.d(TAG, "Dialog not attached to window manager exception");
                    }
                }
            });
        }
        //webView.loadUrl(sponsor.getWeb_URL());

        swipeRefreshLayout.setOnRefreshListener(() -> {
            new Handler().postDelayed(() -> {
                if (swipeRefreshLayout != null) swipeRefreshLayout.setRefreshing(false);
            }, 3000);
            webView.reload();
        });
        return view;
    }


    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        if (webView != null) webView.stopLoading();
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        webView.restoreState(savedInstanceState);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(getActivity().getResources().getString(R.string.PARCELABLE_SPONSOR), sponsor);
        webView.saveState(outState);
    }
}

package edu.utdallas.itsummit2017.fragments;

import android.app.Activity;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.ExpandableListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import edu.utdallas.itsummit2017.R;
import edu.utdallas.itsummit2017.activities.MainActivity;
import edu.utdallas.itsummit2017.adapters.PresenterAdapter;
import edu.utdallas.itsummit2017.models.Presenters;
import edu.utdallas.itsummit2017.models.jsonmodels.presenter;
import edu.utdallas.itsummit2017.serverapi.DataGetter;
import edu.utdallas.itsummit2017.utils.Utils;


public class PresenterFragment extends Fragment {

    TextView tvLast, tvFirst, tvCompany, tvCancel;
    AutoCompleteTextView tvSearch;
    PresenterAdapter adapter;
    ExpandableListView elv;
    List<Character> presenterList = new ArrayList<>();
    HashMap<Character, List<presenter>> names = new HashMap<>();
    List<presenter> presenters = new ArrayList<>();
    private final String TAG = getClass().getName();
    static String searchText = "";
    public static int index = 0, top = 0;

    public void refreshData() {
        if (adapter != null) {
            adapter.notifyDataSetChanged();
            Log.d(TAG, "Refreshed adapter data");
        } else {
            Log.d(TAG, "Adapter is null");
        }
    }


    TextWatcher watcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            searchText = s.toString();
            if (searchText.trim().equals("")) {
                sort(currentTab, presenters);
            }
            List<presenter> presList = new ArrayList<>();
            String typed = searchText.toLowerCase();
            for (presenter presenter : presenters) {
                switch (currentTab) {
                    case FIRST_NAME:
                        if (presenter.getFirst_Name().toLowerCase().contains(typed)) {
                            presList.add(presenter);
                        }
                        break;
                    case LAST_NAME:
                        if (presenter.getLast_Name().toLowerCase().contains(typed)) {
                            presList.add(presenter);
                        }
                        break;
                    case COMPANY:
                        if (presenter.getCompany().toLowerCase().contains(typed)) {
                            presList.add(presenter);
                        }
                        break;
                }
            }
            sort(currentTab, presList);
        }
    };

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        presenters.clear();
        presenters.addAll(((Presenters) ((MainActivity) getActivity()).getDataGetter().readFromRealm(DataGetter.DataType.PRESENTER)).getPresenters());
        if (adapter != null) adapter.notifyDataSetChanged();
    }

    private enum Tabs {LAST_NAME, FIRST_NAME, COMPANY}

    static Tabs currentTab;

    public static PresenterFragment newInstance() {
        return new PresenterFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.presenter_fragment, container, false);
        tvLast = (TextView) v.findViewById(R.id.tvLastNamePresenters);
        tvFirst = (TextView) v.findViewById(R.id.tvFirstNamePresenters);
        tvCompany = (TextView) v.findViewById(R.id.tvCompanyPresenters);
        tvSearch = (AutoCompleteTextView) v.findViewById(R.id.actvSearchPresenters);
        tvCancel = (TextView) v.findViewById(R.id.tvCancelPresenters);
        elv = (ExpandableListView) v.findViewById(R.id.elvPresenters);
        tvSearch.setOnClickListener(v13 -> tvCancel.setVisibility(View.VISIBLE));
        tvSearch.setImeActionLabel("Search", KeyEvent.KEYCODE_ENTER);
        tvSearch.setOnKeyListener((v16, keyCode, event) -> {
            if ((event.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)) {
                Utils.hideKeyboard(v16);
                return true;
            }
            return false;
        });
        tvCancel.setVisibility(View.GONE);
        tvCancel.setOnClickListener(v14 -> {
            tvCancel.setVisibility(View.GONE);
            searchText = "";
            tvSearch.setText(searchText);
            sort(currentTab, presenters);
            Utils.hideKeyboard(v14);
        });

        Collections.sort(presenterList);
        if (adapter == null)
            adapter = new PresenterAdapter(getActivity(), presenterList, names);
        elv.setAdapter(adapter);
        elv.setSelectionFromTop(index, top);
        if (currentTab == null) {
            currentTab = Tabs.LAST_NAME;
        }
        selectTab(currentTab);
        tvLast.setOnClickListener(v15 -> {
            if (currentTab != Tabs.LAST_NAME) {
                clearSearch(v15);
                selectTab(Tabs.LAST_NAME);
            }
        });
        tvFirst.setOnClickListener(v12 -> {
            if (currentTab != Tabs.FIRST_NAME) {
                clearSearch(v12);
                selectTab(Tabs.FIRST_NAME);
            }
        });
        tvCompany.setOnClickListener(v1 -> {
            if (currentTab != Tabs.COMPANY) {
                clearSearch(v1);
                selectTab(Tabs.COMPANY);
            }
        });
        tvSearch.addTextChangedListener(watcher);
        return v;
    }


    private void clearSearch(View v) {
        searchText = "";
        tvSearch.setText("");
        tvCancel.setVisibility(View.GONE);
        Utils.hideKeyboard(v);
    }

    private void sort(Tabs tab, List<presenter> presenters) {
        presenterList.clear();
        if (names == null) names = new HashMap<>();
        else names.clear();
        for (presenter presenter : presenters) {
            Character a = '\0';
            switch (tab) {
                case LAST_NAME:
                    a = presenter.getLast_Name().toUpperCase().charAt(0);
                    break;
                case FIRST_NAME:
                    a = presenter.getFirst_Name().toUpperCase().charAt(0);
                    break;
                case COMPANY:
                    if (presenter.getCompany().length() == 0) a = ' ';
                    else a = presenter.getCompany().toUpperCase().charAt(0);
                    break;
                default:
                    a = presenter.getLast_Name().toUpperCase().charAt(0);
                    break;
            }

            if (a != ' ') {

                if (names.keySet().size() == 0) {
                    List<presenter> pres = new ArrayList<>();
                    pres.add(presenter);
                    names.put(a, pres);
                    presenterList.add(a);
                } else {
                    int k;
                    for (k = 0; k < names.keySet().size(); k++) {
                        Character x = (Character) names.keySet().toArray()[k];
                        if (a.equals(x)) {
                            names.get(a).add(presenter);
                            break;
                        }
                    }
                    if (k == names.keySet().size()) {

                        List<presenter> pres = new ArrayList<>();
                        pres.add(presenter);
                        names.put(a, pres);
                        presenterList.add(a);
                    }
                }
            }
        }
        Collections.sort(presenterList);
        adapter.notifyDataSetChanged();
    }


    @SuppressWarnings("deprecation")
    private void selectTab(Tabs tab) {
        currentTab = tab;
        Resources res = getActivity().getResources();
        switch (tab) {
            case LAST_NAME:
                tvLast.setBackgroundDrawable(res.getDrawable(R.drawable.tab_left_orange));
                tvFirst.setBackgroundDrawable(res.getDrawable(R.drawable.tab_middle_white));
                tvCompany.setBackgroundDrawable(res.getDrawable(R.drawable.tab_right_white));
                tvLast.setTextColor(res.getColor(R.color.white));
                tvFirst.setTextColor(res.getColor(R.color.orange));
                tvCompany.setTextColor(res.getColor(R.color.orange));
                break;
            case FIRST_NAME:
                tvLast.setBackgroundDrawable(res.getDrawable(R.drawable.tab_left_white));
                tvFirst.setBackgroundDrawable(res.getDrawable(R.drawable.tab_middle_orange));
                tvCompany.setBackgroundDrawable(res.getDrawable(R.drawable.tab_right_white));
                tvLast.setTextColor(res.getColor(R.color.orange));
                tvFirst.setTextColor(res.getColor(R.color.white));
                tvCompany.setTextColor(res.getColor(R.color.orange));
                break;
            case COMPANY:
                tvLast.setBackgroundDrawable(res.getDrawable(R.drawable.tab_left_white));
                tvFirst.setBackgroundDrawable(res.getDrawable(R.drawable.tab_middle_white));
                tvCompany.setBackgroundDrawable(res.getDrawable(R.drawable.tab_right_orange));
                tvLast.setTextColor(res.getColor(R.color.orange));
                tvFirst.setTextColor(res.getColor(R.color.orange));
                tvCompany.setTextColor(res.getColor(R.color.white));
                break;
            default:
                selectTab(Tabs.LAST_NAME);
        }
        sort(tab, presenters);
    }


    @Override
    public void onResume() {
        super.onResume();
        tvSearch.setText(searchText);
        tvSearch.setSelection(tvSearch.getText().length());
        if (!searchText.equals("")) tvCancel.setVisibility(View.VISIBLE);
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(TAG, "Pausing presenter fragment");
        index = elv.getFirstVisiblePosition();
        View v = elv.getChildAt(0);
        top = (v == null) ? 0 : (v.getTop() - elv.getPaddingTop());
    }


    @Override
    public void onDetach() {
        super.onDetach();
        // activity = null;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        tvLast = null;
        tvFirst = null;
        tvCompany = null;
        tvCancel = null;
        tvSearch = null;
        adapter = null;
        elv = null;
    }
}

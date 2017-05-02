package edu.utdallas.itsummit.fragments;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AutoCompleteTextView;
import android.widget.ExpandableListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import edu.utdallas.itsummit.R;
import edu.utdallas.itsummit.activities.MainActivity;
import edu.utdallas.itsummit.adapters.SessionsAdapter;
import edu.utdallas.itsummit.models.Schedule;
import edu.utdallas.itsummit.models.jsonmodels.session;
import edu.utdallas.itsummit.serverapi.DataGetter;

public class SessionFragment extends Fragment {

    List<session> allSessionsList = new ArrayList<>();
    List<session> searchResults = new ArrayList<>();
    TextView tvAll, tvFav, tvCancel;
    AutoCompleteTextView actvSearch;
    HashMap<String, List<session>> sessionGroups = new HashMap<>();
    private final String TAG = getClass().getName();
    SessionsAdapter adapter = null;
    List<String> sessionHeadings = new ArrayList<>();
    public static int index = 0, top = 0;

    private enum Tab {All, Fav}

    static Tab currentTab = Tab.All;
    ExpandableListView elv;
    String FAV_KEY;

    public static SessionFragment newInstance() {
        return new SessionFragment();
    }

    public void refreshData() {
        if (adapter != null) {
            adapter.notifyDataSetChanged();
            Log.d(TAG, "Adapter refreshed");
        } else Log.d(TAG, "adapter is null");
    }

    private void copyToSearchList(List<session> sessionList) {
        searchResults.clear();
        for (session session : sessionList) {
            searchResults.add(session);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        Schedule sessions = (Schedule) ((MainActivity) getActivity()).getDataGetter().readFromRealm(DataGetter.DataType.SESSION);
        FAV_KEY = activity.getResources().getString(R.string.KEY_FAV);
        allSessionsList.clear();
        searchResults.clear();
        allSessionsList.addAll(sessions.getSessions());
        searchResults.addAll(sessions.getSessions());
        if (adapter != null) adapter.notifyDataSetChanged();
    }

    private HashMap<String, List<session>> getSessionHeadings(List<session> sessionsList) {
        Collections.sort(sessionsList);
        sessionGroups.clear();
        sessionHeadings.clear();
        for (session session : sessionsList) {
            String sessionGroup = session.getTimeAndDate().getSessionGroupName();
            if (checkIfExists(sessionGroups, sessionGroup)) {
                sessionGroups.get(sessionGroup).add(session);
            } else {
                List<session> newList = new ArrayList<>();
                newList.add(session);
                String key = sessionGroup;
                if (sessionHeadings.size() > 0) {
                    if (sessionGroups.get(sessionHeadings.get(sessionHeadings.size() - 1)) != null) {
                        session prevSession = sessionGroups.get(sessionHeadings.get(sessionHeadings.size() - 1)).get(0);
                        if (!prevSession.getTimeAndDate().getDate().equalsIgnoreCase(session.getTimeAndDate().getDate())) {
                            key += " - " + session.getTimeAndDate().getDate();
                        }
                    }
                } else key += " - " + session.getTimeAndDate().getDate();
                sessionGroups.put(key, newList);
                sessionHeadings.add(key);
            }
        }
        return sessionGroups;
    }

    private boolean checkIfExists(HashMap<String, List<session>> map, String key) {
        for (String mapKey : map.keySet()) {
            if (mapKey.equalsIgnoreCase(key)) return true;
        }
        return false;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.session_fragment, container, false);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Schedule");
        tvAll = (TextView) v.findViewById(R.id.tvAll);
        tvCancel = (TextView) v.findViewById(R.id.tvCancel);
        tvFav = (TextView) v.findViewById(R.id.tvFav);
        elv = (ExpandableListView) v.findViewById(R.id.elvSessions);
        actvSearch = (AutoCompleteTextView) v.findViewById(R.id.actvSearch);
        actvSearch.setImeActionLabel("Search", KeyEvent.KEYCODE_ENTER);
        actvSearch.setOnClickListener(v15 -> tvCancel.setVisibility(View.VISIBLE));
        actvSearch.setOnKeyListener((v14, keyCode, event) -> {
            if ((event.getAction() == KeyEvent.ACTION_DOWN) &&
                    (keyCode == KeyEvent.KEYCODE_ENTER)) {
                hideKeyboard(v14);
                return true;
            }
            return false;
        });
        actvSearch.addTextChangedListener(twSearch);

        tvCancel.setOnClickListener(v13 -> {
            actvSearch.setText("");
            tvCancel.setVisibility(View.GONE);
            searchResults.clear();
            copyToSearchList(allSessionsList);
            setCurrentTab(currentTab);
            hideKeyboard(v13);
        });
        tvAll.setOnClickListener(v1 -> setCurrentTab(Tab.All));
        tvFav.setOnClickListener(v12 -> setCurrentTab(Tab.Fav));
        elv.setSelectionFromTop(index, top);
        adapter = new SessionsAdapter(getActivity(), sessionGroups, sessionHeadings);
        elv.setAdapter(adapter);
        if (currentTab == null) {
            copyToSearchList(allSessionsList);
            setCurrentTab(Tab.All);
        } else setCurrentTab(currentTab);
        expandAllGroups(elv);
        return v;
    }


    private void expandAllGroups(ExpandableListView elv) {
        for (int i = 0; i < sessionHeadings.size(); i++) {
            elv.expandGroup(i);
        }
        elv.setOnGroupClickListener((parent, v, groupPosition, id) -> true);
    }

    private List<session> getFavSessions() {
        List<session> favSessionsList = new ArrayList<>();
        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
        for (session session : searchResults) {
            if (sharedPrefs.getBoolean(FAV_KEY + session.getID(), false)) {
                favSessionsList.add(session);
            }
        }
        return favSessionsList;
    }


    private void setCurrentTab(Tab tab) {
        if (currentTab != tab) tvCancel.callOnClick();
        currentTab = tab;
        Resources res = getActivity().getResources();
        switch (tab) {
            case All:
                tvAll.setTextColor(res.getColor(R.color.white));
                tvFav.setTextColor(res.getColor(R.color.orange));
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    tvAll.setBackground(res.getDrawable(R.drawable.tab_left_orange));
                    tvFav.setBackground(res.getDrawable(R.drawable.tab_right_white));
                }
                getSessionHeadings(searchResults);
                break;
            case Fav:
                tvAll.setTextColor(res.getColor(R.color.orange));
                tvFav.setTextColor(res.getColor(R.color.white));
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    tvAll.setBackground(res.getDrawable(R.drawable.tab_left_white));
                    tvFav.setBackground(res.getDrawable(R.drawable.tab_right_orange));
                }
                getSessionHeadings(getFavSessions());
                break;
        }
        if (adapter != null) adapter.notifyDataSetChanged();
        if (elv != null) expandAllGroups(elv);
    }


    TextWatcher twSearch = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            if (!s.toString().trim().equalsIgnoreCase("")) {
                search(s.toString().trim());
            } else
                copyToSearchList(allSessionsList);
            setCurrentTab(currentTab);
        }
    };

    private void search(String s) {
        searchResults.clear();
        for (session session : allSessionsList) {
            if (session.getTitle().toLowerCase().startsWith(s.toLowerCase())) {
                searchResults.add(session);
            }
        }
    }

    private void hideKeyboard(View v) {
        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
    }

    @Override
    public void onPause() {
        super.onPause();
        index = elv.getFirstVisiblePosition();
        View v = elv.getChildAt(0);
        top = (v == null) ? 0 : (v.getTop() - elv.getPaddingTop());
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        tvAll = null;
        tvFav = null;
        tvCancel = null;
        actvSearch = null;
        adapter = null;
        elv = null;
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}

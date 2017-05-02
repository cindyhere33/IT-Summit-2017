package edu.utdallas.itsummit.utils;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;

import com.google.firebase.analytics.FirebaseAnalytics;

import edu.utdallas.itsummit.R;

/**
 * Created by sxk159231 on 4/6/2017.
 */

public class Utils {

    private static final String TAG = "Utils.class";

    public static boolean isTopMostFragment(FragmentManager fragmentManager, String fragmentName) {
        return fragmentManager.getBackStackEntryCount() > 0 && (fragmentManager.getBackStackEntryAt(fragmentManager.getBackStackEntryCount() - 1).getName().equals(fragmentName));
    }

    public static void hideKeyboard(View view) {
        InputMethodManager imm = (InputMethodManager) view.getContext().getSystemService(Activity.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    public static void setupFavStar(Context context, String sessId, String sessName, ImageView ivFav) {
        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPrefs.edit();
        String FAV_KEY = context.getResources().getString(R.string.KEY_FAV);
        if (isFavorite(sharedPrefs, FAV_KEY, sessId)) {
            ivFav.setImageDrawable(context.getResources().getDrawable(R.drawable.star_filled));
        } else
            ivFav.setImageDrawable(context.getResources().getDrawable(R.drawable.star_empty));
        ivFav.setOnClickListener(v -> {
            Bundle bundle = new Bundle();
            bundle.putString(FirebaseAnalytics.Param.VALUE, sessName);

            if (isFavorite(sharedPrefs, FAV_KEY, sessId)) {
                editor.putBoolean(FAV_KEY + sessId, false);
                bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, "UNFAVORITED ");
                ivFav.setImageDrawable(context.getResources().getDrawable(R.drawable.star_empty));
            } else {
                editor.putBoolean(FAV_KEY + sessId, true);
                bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, "FAVORITED");
                ivFav.setImageDrawable(context.getResources().getDrawable(R.drawable.star_filled));
            }
            editor.commit();
            FirebaseAnalytics.getInstance(context).logEvent(FirebaseAnalytics.Event.ADD_TO_WISHLIST, bundle);
        });
    }

    public static boolean isFavorite(SharedPreferences sharedPrefs, String favKey, String sessId) {
        return (sharedPrefs.getBoolean(favKey + sessId, false));
    }


    public static int calculateInSampleSize(BitmapFactory.Options options, int reqWidth,
                                            int reqHeight) {
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;
        if (height > reqHeight || width > reqWidth) {
            final int halfHeight = height / 2;
            final int halfWidth = width / 2;
            while ((halfHeight / inSampleSize) >= reqHeight && (halfWidth / inSampleSize) >= reqWidth) {
                inSampleSize *= 2;
            }
        }
        return inSampleSize;
    }

    public static void reportEvent(Context context, String name, String value) {
        Bundle bundle = new Bundle();
        bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, name);
        bundle.putString(FirebaseAnalytics.Param.VALUE, value);
        FirebaseAnalytics.getInstance(context).logEvent(FirebaseAnalytics.Event.VIEW_ITEM, bundle);
    }

    public static Bitmap decodeSampledBitmapFromResource(Resources res, int resId,
                                                         int reqWidth, int reqHeight) {
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(res, resId, options);
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeResource(res, resId, options);
    }
}

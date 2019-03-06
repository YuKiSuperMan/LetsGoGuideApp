package com.njz.letsgoappguides.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.Nullable;

import java.util.Set;

/**
 * Created by Administrator on 2018/11/2.
 * SharedPreferences 工具类
 */

public class SPUtils {

    public static final String FIRST_OPENED = "first_open";


    public static final String SP_USER_TOKEN = "sp_user_token";
    public static final String SP_USER_MYSTORY = "sp_user_myStory";
    public static final String SP_USER_GUIDESCORE = "sp_user_guideScore";
    public static final String SP_USER_IMAGE = "sp_user_image";
    public static final String SP_USER_CARDVIABLE = "sp_user_cardViable";
    public static final String SP_USER_USERIMG = "sp_user_userImg";
    public static final String SP_USER_GENDER = "sp_user_gender";
    public static final String SP_USER_DRIVEVIABLE = "sp_user_driveViable";
    public static final String SP_USER_INTRODUCE = "sp_user_introduce";
    public static final String SP_USER_MOBILE = "sp_user_mobile";
    public static final String SP_USER_LANGUAGE = "sp_user_language";
    public static final String SP_USER_BIRTHTIME= "sp_user_birthTime";
    public static final String SP_USER_GUIDEAGE = "sp_user_guideAge";
    public static final String SP_USER_GUIDEVIABLE = "sp_user_guideViable";
    public static final String SP_USER_PASSWORD = "sp_user_password";
    public static final String SP_USER_CUSTOMSIGN = "sp_user_customSign";
    public static final String SP_USER_NAME = "sp_user_name";
    public static final String SP_USER_STARTTIME = "sp_user_startTime";
    public static final String SP_USER_ID = "sp_user_id";
    public static final String SP_USER_WORKYEAR = "sp_user_workYear";
    public static final String SP_USER_SERVICECOUNTS = "sp_user_serviceCounts";
    public static final String SP_USER_GUIDEMACROENTITYLIST = "sp_user_guideMacroEntityList";
    public static final String SP_USER_DRIVEVIABLENOPASSCAUSE = "sp_user_driveViableNoPassCause";
    public static final String SP_USER_GUIDEVIABLENOPASSCAUSE = "sp_user_guideViableNoPassCause";
    public static final String SP_USER_CARDVIABLENOPASSCAUSE = "sp_user_cardViableNoPassCause";
    public static final String SP_IM_LOGIN = "sp_im_login";


    public static final String SP_USER_LABELS = "sp_user_labels";
    public static final String SP_USER_FREE_LABELS = "sp_user_free_labels";

    public static final String SP_USER_SERVICE_IDENTIFICATION = "sp_user_service_identification";
    public static final String SP_USER_SERVICEMACROENTITYLIST = "sp_user_ServiceMacroEntityList";
    public static final String SP_USER_SERVICE_LANGAGES = "sp_user_service_langages";
    public static final String SP_USER_SERVICE_CITYS = "sp_user_service_citys";


    private static SharedPreferences mSharedPreferences;
    private static SPUtils mPreferenceUtils;
    private static SharedPreferences.Editor editor;

    public static final String PREFERENCE_NAME = "com.njz.letsgoguides_preferences";


    public static synchronized void init(Context cxt) {
        if (mPreferenceUtils == null) {
            mPreferenceUtils = new SPUtils(cxt);
        }
    }

    private SPUtils(Context cxt) {
        mSharedPreferences = cxt.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
        editor = mSharedPreferences.edit();
    }

    public static SPUtils getInstance() {
        if (mPreferenceUtils == null) {
            throw new RuntimeException("please init first!");
        }
        return mPreferenceUtils;
    }


    public boolean putString(String key, String value) {
        editor.putString(key, value);
        return editor.commit();
    }

    public String getString(String key) {
        return getString(key, null);
    }

    public String getString(String key,
                            String defaultValue) {
        return mSharedPreferences.getString(key, defaultValue);
    }

    public boolean putInt(String key, int value) {
        editor.putInt(key, value);
        return editor.commit();
    }


    public int getInt(String key) {
        return getInt(key, -1);
    }

    public int getInt(String key, int defaultValue) {
        return mSharedPreferences.getInt(key, defaultValue);
    }

    public boolean putLong(String key, long value) {
        editor.putLong(key, value);
        return editor.commit();
    }

    public long getLong(String key) {
        return getLong(key, -1);
    }


    public long getLong(String key, long defaultValue) {
        return mSharedPreferences.getLong(key, defaultValue);
    }

    public boolean putFloat(String key, float value) {
        editor.putFloat(key, value);
        return editor.commit();
    }


    public float getFloat(String key) {
        return getFloat(key, -1);
    }


    public float getFloat(String key, float defaultValue) {
        return mSharedPreferences.getFloat(key, defaultValue);
    }

    public boolean putBoolean(String key, boolean value) {
        editor.putBoolean(key, value);
        return editor.commit();
    }


    public boolean getBoolean(String key) {
        return getBoolean(key, false);
    }


    public boolean getBoolean(String key,
                              boolean defaultValue) {
        return mSharedPreferences.getBoolean(key, defaultValue);
    }


    public void put(String key, @Nullable Set<String> values) {
        editor.putStringSet(key, values).apply();
    }


    public Set<String> getStringSet(String key) {
        return getStringSet(key, null);
    }


    public Set<String> getStringSet(String key, @Nullable Set<String> defaultValue) {
        return mSharedPreferences.getStringSet(key, defaultValue);
    }


    public void remove(String key) {
        editor.remove(key).apply();
    }

    public void clearAll() {
        editor.clear().commit();
    }

    public void logoff() {
        remove(SP_USER_TOKEN);
        remove(SP_USER_ID);
        remove(SP_USER_NAME);
        remove(SP_USER_MOBILE);
        remove(SP_USER_SERVICE_IDENTIFICATION);
    }
}

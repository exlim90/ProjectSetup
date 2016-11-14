package com.projectsetup.vlad.projectsetup.util;

/**
 * Created by vladi on 11/13/2016.
 */

public interface AppPreferences {

    void setUserId(String userId);

    String getUserId();

    void clearPreferences();
}

package com.temporary.unsplashdemo.data.prefs;

public interface PreferencesHelper {

    Long getUserId();

    void setUserId(Long userId);

    String getUserName();

    void setUserName(String userName);

    String getUserEmail();

    void setUserEmail(String email);

    String getUserMobile();

    void setUserMobile(String mobileNumber);
}

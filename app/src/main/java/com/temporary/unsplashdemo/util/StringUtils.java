package com.temporary.unsplashdemo.util;

import androidx.annotation.Nullable;

public class StringUtils {

    public static int countMatches(String source, Character whatToFind) {
        return countMatches(source, whatToFind.toString());
    }

    public static int countMatches(String source, String whatToFind) {
        String temp = source.replaceAll(whatToFind, "");
        return (source.length() - temp.length()) / whatToFind.length();
    }

    public static boolean isEmpty(@Nullable CharSequence cs) {
        return cs == null || cs.length() == 0;
    }

    public static boolean notEmpty(@Nullable CharSequence cs) {
        return !StringUtils.isEmpty(cs);
    }

}

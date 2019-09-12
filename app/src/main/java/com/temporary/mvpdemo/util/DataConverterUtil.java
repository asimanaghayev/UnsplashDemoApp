package com.temporary.mvpdemo.util;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.temporary.mvpdemo.data.network.model.Photos;

import java.util.List;

public class DataConverterUtil {

    public static List<Photos> parsePhotoSuccessResponse(Object o) {
        Gson gson = new Gson();
        String res = new Gson().toJson(o);
        return gson.fromJson(res,
                new TypeToken<List<Photos>>() {
                }.getType());
    }
}

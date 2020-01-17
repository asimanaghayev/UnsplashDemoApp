package com.temporary.unsplashdemo.util;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.afollestad.materialdialogs.MaterialDialog;
import com.squareup.picasso.Picasso;
import com.temporary.unsplashdemo.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PhotoDialogBuilder {
//    private Dialog imageDialog;

    @BindView(R.id.image_view)
    ImageView imageView;

    private Context context;

    private Bitmap bitmap;

    private String url;

    private PhotoDialogBuilder() {

    }

    public static PhotoDialogBuilder newInstance(Context context) {
        PhotoDialogBuilder builder = new PhotoDialogBuilder();
        builder.context = context;
        return builder;
    }

    public PhotoDialogBuilder Image(String url) {
        this.url = url;
        return this;
    }

    public PhotoDialogBuilder Image(Bitmap bitmap) {
        bitmap= bitmap;
        return this;
    }

    public Dialog build() {
        return newInstance(context, bitmap, url);
    }

    private static Dialog newInstance(Context context, Bitmap bitmap, String url) {

        MaterialDialog dialog = new MaterialDialog.Builder(context)
                .customView(R.layout.dialog_photo, false)
                .show();

        ImageView dialogImage = (ImageView) dialog.findViewById(R.id.image_view);

        if(bitmap != null)
            dialogImage.setImageBitmap(bitmap);

        if(StringUtils.notEmpty(url))
            Picasso.get().load(url).into(dialogImage);

        return dialog;
    }
}

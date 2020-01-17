package com.temporary.unsplashdemo.util;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.squareup.picasso.Picasso;
import com.temporary.unsplashdemo.R;

import org.w3c.dom.Text;

import butterknife.BindView;

public class PhotoDialogBuilder {
//    private Dialog imageDialog;

    @BindView(R.id.image_view)
    ImageView imageView;

    private Context context;

    private Bitmap bitmap;

    private String url;

    private LinearLayout imageButtonLayout;

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
        this.bitmap = bitmap;
        return this;
    }

    public PhotoDialogBuilder ImageButton(int icon, View.OnClickListener onClickListener) {
        return ImageButton(context.getResources().getDrawable(icon), onClickListener);
    }

    public PhotoDialogBuilder ImageButton(Drawable icon, View.OnClickListener onClickListener) {
        ImageButton imageButton = new ImageButton(context, null, R.style.ImageDialogButtonStyle);

        imageButton.setImageDrawable(icon);
        imageButton.setOnClickListener(onClickListener);

        if (imageButtonLayout == null)
            imageButtonLayout = new LinearLayout(context);

        this.imageButtonLayout.addView(imageButton);
        return this;
    }

    public Dialog build() {
        return newInstance(context, bitmap, url, imageButtonLayout);
    }

    private static Dialog newInstance(Context context, Bitmap bitmap, String url, LinearLayout imageButtonLayout) {

        MaterialDialog dialog = new MaterialDialog.Builder(context)
                .customView(R.layout.dialog_photo, false)
                .show();

        ImageView dialogImage = (ImageView) dialog.findViewById(R.id.image_view);

        if (bitmap != null)
            dialogImage.setImageBitmap(bitmap);

        if (StringUtils.notEmpty(url))
            Picasso.get().load(url).into(dialogImage);

        LinearLayout imageButtons = (LinearLayout) dialog.findViewById(R.id.image_button_list);
        imageButtons.addView(imageButtonLayout);
        return dialog;
    }
}

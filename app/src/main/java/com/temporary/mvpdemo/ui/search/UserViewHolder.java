package com.temporary.mvpdemo.ui.search;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.temporary.mvpdemo.R;
import com.temporary.mvpdemo.data.network.model.User;

import butterknife.BindView;
import butterknife.ButterKnife;

public class UserViewHolder extends RecyclerView.ViewHolder {
    private View view;

    @BindView(R.id.user_image)
    ImageView userImage;

    @BindView(R.id.username)
    TextView username;

    @BindView(R.id.name)
    TextView name;

    public UserViewHolder(@NonNull View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        view = itemView;
    }

    public void bind(Context context, final User user, final ItemClickListener userItemClickListener) {
        if (user != null) {
            Picasso.get()
                    .load(user.getProfileImage().getMedium())
                    .into(userImage);

            username.setText(user.getUsername());
            name.setText(user.getName());
        }
        view.setOnClickListener(v -> userItemClickListener.onItemClick(user));
    }
}

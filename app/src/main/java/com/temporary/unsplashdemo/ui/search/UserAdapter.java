package com.temporary.unsplashdemo.ui.search;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.temporary.unsplashdemo.R;
import com.temporary.unsplashdemo.data.network.model.User;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserViewHolder> {
    private Context context;
    private List<User> users;
    private ItemClickListener userItemClickListener;

    public UserAdapter(Context context, List<User> users, ItemClickListener<User> userItemClickListener) {
        this.context = context;
        this.users = users;
        this.userItemClickListener = userItemClickListener;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.user_item, viewGroup, false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        holder.bind(context, users.get(position), userItemClickListener);
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public void clear() {
        users.clear();
    }


    public void addUsers(List<User> users) {
        this.users.addAll(users);
    }
}

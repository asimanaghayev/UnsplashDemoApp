package com.temporary.unsplashdemo.ui.main;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.OrientationHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.squareup.picasso.Picasso;
import com.temporary.unsplashdemo.R;
import com.temporary.unsplashdemo.data.network.model.Photos;
import com.temporary.unsplashdemo.ui.base.BaseActivity;
import com.temporary.unsplashdemo.ui.search.SearchActivity;
import com.temporary.unsplashdemo.util.EmptyTarget;
import com.temporary.unsplashdemo.util.PaginationListener;
import com.temporary.unsplashdemo.util.PhotoDialogBuilder;

import java.util.List;
import java.util.Random;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity implements MainContractor.View {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Inject
    MainContractor.Presenter presenter;

    PhotoDialogBuilder photoDialog;

    private PhotoAdapter photoAdapter = new PhotoAdapter();

    private StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(3, OrientationHelper.VERTICAL);

    private PaginationListener paginationListener = new PaginationListener(layoutManager) {
        @Override
        protected void loadMoreItems(int pageCount) {
            presenter.getNextPage(pageCount);
        }

        @Override
        public boolean isLoading() {
            return progressBar.getVisibility() == View.VISIBLE;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        getActivityComponent().inject(this);
        presenter.setView(this);

        photoAdapter.setItemClickListener(url -> {
            PhotoDialogBuilder.newInstance(this)
                    .Image(url)
                    .ImageButton(R.drawable.ic_info_brown, v -> {
                        Picasso.get().load(url).into(new EmptyTarget() {
                            @Override
                            public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                                super.onBitmapLoaded(bitmap, from);
                                MediaStore.Images.Media.insertImage(getContentResolver(), bitmap, "UnsplashDemo"+ new Random().toString(), "demo");
                                Toast.makeText(getApplicationContext(), "Media saved to gallery", Toast.LENGTH_SHORT).show();
                            }
                        });
                    })
                    .ImageButton(R.drawable.ic_download_brown, v -> {
                    })
                    .build();
        });

        layoutManager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_MOVE_ITEMS_BETWEEN_SPANS);
        //Objects.requireNonNull(getIntent().getExtras()).getSerializable(SEARCH_RESULT);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(photoAdapter);
        recyclerView.addOnScrollListener(paginationListener);

        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_search:
                Intent intent = new Intent(this, SearchActivity.class);
                startActivity(intent);
                return false;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(android.view.View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(android.view.View.GONE);
    }

    public void addPhotos(List<Photos> photos) {
        photoAdapter.addPhotos(photos);
        photoAdapter.notifyDataSetChanged();
//        recyclerView.setAdapter(photoAdapter);
    }
}

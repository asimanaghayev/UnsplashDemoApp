package com.temporary.mvpdemo.ui.main;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

public abstract class PaginationListener extends RecyclerView.OnScrollListener {

    public static final int PAGE_START = 1;
    private static final int PAGE_SIZE = 10;
    int pageCount = PAGE_START;

    @NonNull
    private StaggeredGridLayoutManager layoutManager;

    public PaginationListener(@NonNull StaggeredGridLayoutManager layoutManager) {
        this.layoutManager = layoutManager;
    }

    @Override
    public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);

        int visibleItemCount = layoutManager.getChildCount();
        int totalItemCount = layoutManager.getItemCount();
        int[] firstVisibleItemPosition = new int[1000];
        layoutManager.findFirstVisibleItemPositions(firstVisibleItemPosition);

        if (!isLoading() && !isLastPage()) {
            if ((visibleItemCount + firstVisibleItemPosition[0]) >= totalItemCount &&
                    firstVisibleItemPosition[0] >= 0 &&
                    totalItemCount >= PAGE_SIZE) {
                pageCount++;
                loadMoreItems(pageCount);
            }
        }
    }

    protected abstract void loadMoreItems(int pageCount);

    public abstract boolean isLastPage();

    public abstract boolean isLoading();
}


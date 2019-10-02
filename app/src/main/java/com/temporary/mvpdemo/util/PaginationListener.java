package com.temporary.mvpdemo.util;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

public abstract class PaginationListener extends RecyclerView.OnScrollListener {

    public static final int PAGE_START = 2;

    private static final int PAGE_SIZE = 10;

    private int lastPage = -1;

    private int pageCount = PAGE_START;

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
                loadMoreItems(pageCount);
                pageCount++;
            }
        }
    }

    private boolean isLastPage() {
        if (lastPage != -1)
            return lastPage < pageCount;
        return false;
    }

    public void setLastPage(int lastPage) {
        this.lastPage = lastPage;
    }

    protected abstract void loadMoreItems(int pageCount);

    public abstract boolean isLoading();
}


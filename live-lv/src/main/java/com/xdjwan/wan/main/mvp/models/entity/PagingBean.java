package com.xdjwan.wan.main.mvp.models.entity;


public final class PagingBean {
    //当前是第几页(curPage)
    private int mPageIndex = 0;
    //总数据条数(total)
    private int mTotal = 0;
    //一页显示几条数据(size)
    private int mPageSize = 0;
    //当前已经显示了几条数据(offset)
    private int mCurrentCount = 0;
    //总页数
    private int mPageCount;
    //加载延迟
    private int mDelayed = 0;

    public int getmPageCount() {
        return mPageCount;
    }

    public PagingBean setmPageCount(int mPageCount) {
        this.mPageCount = mPageCount;
        return this;
    }

    public int getPageIndex() {
        return mPageIndex;
    }

    public PagingBean setPageIndex(int mPageIndex) {
        this.mPageIndex = mPageIndex;
        return this;
    }

    public int getTotal() {
        return mTotal;
    }

    public PagingBean setTotal(int mTotal) {
        this.mTotal = mTotal;
        return this;
    }

    public int getPageSize() {
        return mPageSize;
    }

    public PagingBean setPageSize(int mPageSize) {
        this.mPageSize = mPageSize;
        return this;
    }

    public int getCurrentCount() {
        return mCurrentCount;
    }

    public PagingBean setCurrentCount(int mCurrentCount) {
        this.mCurrentCount = mCurrentCount;
        return this;
    }

    public int getDelayed() {
        return mDelayed;
    }

    public PagingBean setDelayed(int mDelayed) {
        this.mDelayed = mDelayed;
        return this;
    }

    public PagingBean addIndex() {
        mPageIndex++;
        return this;
    }
}

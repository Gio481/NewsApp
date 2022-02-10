package com.example.newsapp.ui.adapter

import android.widget.AbsListView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class OnScrollListener(
    private val getArticles: () -> Unit,
    private val pageItems: Int,
    private val layoutManager: LinearLayoutManager
) :
    RecyclerView.OnScrollListener() {

    private var isScrolling = false

    override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
        super.onScrollStateChanged(recyclerView, newState)
        if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {
            isScrolling = true
        }
    }

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)
        val firstItemPosition = layoutManager.findFirstVisibleItemPosition()
        val visibleItems = layoutManager.childCount
        val totalItems = layoutManager.itemCount

        val isAtLastItem = firstItemPosition + visibleItems >= totalItems
        val isNotAtStart = firstItemPosition >= 0
        val isTotalThanVisible = totalItems >= pageItems
        val paginate =
            isNotAtStart && isAtLastItem && isNotAtStart && isTotalThanVisible && isScrolling
        if (paginate) {
            getArticles.invoke()
            isScrolling = false
        }
    }
}
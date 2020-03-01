package com.danser.paged_list_adapter.utils.delegate_adapter

import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

class PagedDiffAdapter(
    private val delegateAdapters: List<IDelegateAdapter<*, *>>,
    callback: DiffUtil.ItemCallback<IComparableItem> = DiffUtilCallback()
) : PagedListAdapter<IComparableItem, RecyclerView.ViewHolder>(
    callback
) {

    override fun getItemViewType(position: Int): Int {
        val item = getItem(position)!!
        val viewType = delegateAdapters.indexOfFirst { adapter ->
            adapter.isForViewType(item)
        }
        if (viewType == -1) throw NullPointerException("Can not get viewType for position $position")
        return viewType
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerView.ViewHolder =
        delegateAdapters[viewType].onCreateViewHolder(parent, viewType)

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = getItem(position) ?: return
        val delegateAdapter: IDelegateAdapter<RecyclerView.ViewHolder, Any>? =
            delegateAdapters.getOrNull(getItemViewType(position)) as IDelegateAdapter<RecyclerView.ViewHolder, Any>
        if (delegateAdapter != null) {
            delegateAdapter.onBindViewHolder(holder, item)
        } else {
            throw java.lang.NullPointerException("can not find adapter for position $position")
        }
    }
}

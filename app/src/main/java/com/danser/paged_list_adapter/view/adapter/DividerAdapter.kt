package com.danser.paged_list_adapter.view.adapter

import com.danser.paged_list_adapter.R
import com.danser.paged_list_adapter.utils.delegate_adapter.IComparableItem
import com.danser.paged_list_adapter.utils.delegate_adapter.KDelegateAdapter

object DividerAdapter : KDelegateAdapter<DividerItem>() {

    override val layoutId: Int = R.layout.item_divider

    override fun isForViewType(item: Any): Boolean =
        item == DividerItem

    override fun onBind(item: DividerItem, viewHolder: KViewHolder) {
        //do nothing
    }
}

object DividerItem : IComparableItem {
    override fun id(): Any = this
    override fun content(): Any = this
}

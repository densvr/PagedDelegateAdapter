package com.danser.paged_list_adapter.utils.delegate_adapter

import androidx.recyclerview.widget.DiffUtil

class DiffUtilCallback : DiffUtil.ItemCallback<IComparableItem>() {
    override fun areItemsTheSame(
        oldItem: IComparableItem,
        newItem: IComparableItem
    ): Boolean = oldItem.id() == newItem.id()

    override fun areContentsTheSame(
        oldItem: IComparableItem,
        newItem: IComparableItem
    ): Boolean = oldItem.content() == newItem.content()
}

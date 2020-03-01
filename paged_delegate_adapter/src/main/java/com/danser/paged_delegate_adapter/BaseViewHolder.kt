package com.danser.paged_delegate_adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView.ViewHolder

open class BaseViewHolder(parent: View) : ViewHolder(parent) {
    private var listener: ItemInflateListener? = null

    fun setListener(listener: ItemInflateListener) {
        this.listener = listener
    }

    fun bind(item: Any) {
        listener?.inflated(item, itemView)
    }
}

interface ItemInflateListener {
    fun inflated(viewType: Any, view: View)
}

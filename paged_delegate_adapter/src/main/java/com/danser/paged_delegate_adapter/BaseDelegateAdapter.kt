package com.danser.paged_delegate_adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView.ViewHolder


abstract class BaseDelegateAdapter<VH : BaseViewHolder, T> :
    IDelegateAdapter<VH, T> {

    protected abstract fun onBindViewHolder(view: View, item: T, viewHolder: VH)

    @get:LayoutRes
    protected abstract val layoutId: Int

    protected abstract fun createViewHolder(parent: View): VH

    override fun onRecycled(holder: VH) {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflatedView = LayoutInflater.from(parent.context)
            .inflate(layoutId, parent, false)
        val holder = createViewHolder(inflatedView)
        holder.setListener(object : ItemInflateListener {
            override fun inflated(viewType: Any, view: View) {
                onBindViewHolder(view, viewType as T, holder)
            }
        })
        return holder
    }

    override fun onBindViewHolder(holder: VH, item: Any) {
        (holder as BaseViewHolder).bind(item)
    }
}

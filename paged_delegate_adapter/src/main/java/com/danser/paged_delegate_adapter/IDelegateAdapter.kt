package com.danser.paged_delegate_adapter


import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

interface IDelegateAdapter<VH : RecyclerView.ViewHolder, T> {
    fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder
    fun onBindViewHolder(holder: VH, item: Any)
    fun onRecycled(holder: VH)
    fun isForViewType(item: Any): Boolean
}

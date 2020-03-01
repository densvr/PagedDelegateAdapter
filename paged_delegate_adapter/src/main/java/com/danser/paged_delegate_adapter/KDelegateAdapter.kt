package com.danser.paged_delegate_adapter

import android.view.View
import kotlinx.android.extensions.LayoutContainer

abstract class KDelegateAdapter<T> : BaseDelegateAdapter<KDelegateAdapter.KViewHolder, T>() {

    open fun onCreated(view: View) = Unit

    abstract fun onBind(item: T, viewHolder: KViewHolder)

    final override fun onBindViewHolder(view: View, item: T, viewHolder: KViewHolder) {
        onBind(item, viewHolder)
    }

    override fun createViewHolder(parent: View): KViewHolder = KViewHolder(parent, ::onCreated)

    class KViewHolder(
        override val containerView: View,
        onCreated: (View) -> Unit
    ) : BaseViewHolder(containerView), LayoutContainer {

        init {
            onCreated(containerView)
        }
    }
}

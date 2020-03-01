package com.danser.paged_list_adapter.view.adapter

import com.danser.paged_list_adapter.R
import com.danser.paged_list_adapter.domain.FeedItem
import com.danser.paged_delegate_adapter.IComparableItem
import com.danser.paged_delegate_adapter.KDelegateAdapter
import kotlinx.android.synthetic.main.item_advert.*

class AdvertAdapter(
    private val onHideClick: (FeedItem.Advert) -> Unit
) : KDelegateAdapter<AdvertViewModel>() {

    override val layoutId: Int = R.layout.item_advert

    override fun onBind(item: AdvertViewModel, viewHolder: KViewHolder) = with(viewHolder) {
        bHide.setOnClickListener { onHideClick(item.payload) }
    }

    override fun isForViewType(item: Any): Boolean =
        item is AdvertViewModel
}

data class AdvertViewModel(
    val payload: FeedItem.Advert
) : IComparableItem {

    override fun id(): Any = AdvertViewModel::javaClass

    override fun content(): Any = this
}

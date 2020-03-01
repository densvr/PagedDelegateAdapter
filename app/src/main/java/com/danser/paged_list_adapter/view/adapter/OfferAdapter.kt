package com.danser.paged_list_adapter.view.adapter

import com.danser.paged_list_adapter.R
import com.danser.paged_list_adapter.domain.FeedItem
import com.danser.paged_delegate_adapter.IComparableItem
import com.danser.paged_delegate_adapter.KDelegateAdapter
import kotlinx.android.synthetic.main.item_offer.*

class OfferAdapter(
    private val onClick: (payload: FeedItem.Offer) -> Unit
) : KDelegateAdapter<OfferViewModel>() {

    override val layoutId: Int = R.layout.item_offer

    override fun onBind(item: OfferViewModel, viewHolder: KViewHolder) = with(viewHolder) {
        tvTitle.text = item.title
        tvText.text = item.text
        itemView.setOnClickListener { onClick(item.payload) }
    }

    override fun isForViewType(item: Any): Boolean = item is OfferViewModel
}

data class OfferViewModel(
    val title: String,
    val price: String,
    val text: String,
    val payload: FeedItem.Offer
) : IComparableItem {

    override fun id(): Any = title

    override fun content(): Any = this
}

package com.danser.paged_list_adapter.presentation

import com.danser.paged_list_adapter.domain.FeedItem
import com.danser.paged_list_adapter.utils.delegate_adapter.IComparableItem
import com.danser.paged_list_adapter.view.adapter.AdvertViewModel
import com.danser.paged_list_adapter.view.adapter.DividerItem
import com.danser.paged_list_adapter.view.adapter.OfferViewModel
import java.text.DecimalFormat

class FeedVMFactory {

    private val decimalFormat: DecimalFormat = DecimalFormat("###,###.#")

    fun toViewModel(items: List<FeedItem>): List<IComparableItem> {
        val items = items.map { item ->
            when(item) {
                is FeedItem.Offer -> item.toViewModel()
                is FeedItem.Advert -> item.toViewModel()
            }
        }
        return items
            .map { item -> listOf(item, DividerItem) }
            .flatten()
    }

    private fun FeedItem.Offer.toViewModel(): OfferViewModel = OfferViewModel(
        title = title,
        price = decimalFormat.format(price) + " Р",
        text = text,
        payload = this
    )

    private fun FeedItem.Advert.toViewModel(): AdvertViewModel = AdvertViewModel(
        payload = this
    )
}

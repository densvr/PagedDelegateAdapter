package com.danser.paged_list_adapter.domain

sealed class FeedItem(open val id: String) {

    data class Offer(
        override val id: String,
        val title: String,
        val price: Int,
        val text: String
    ) : FeedItem(id)

    data class Advert(
        override val id: String
    ) : FeedItem(id)
}

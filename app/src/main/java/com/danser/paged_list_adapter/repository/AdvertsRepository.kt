package com.danser.paged_list_adapter.repository

import com.danser.paged_list_adapter.domain.FeedItem

interface IAdvertsRepository {
    fun getItems(count: Int): List<FeedItem.Advert>
}

class AdvertsRepository : IAdvertsRepository {

    private var id = 0
    
    override fun getItems(count: Int): List<FeedItem.Advert> = (0 until count).map {
        FeedItem.Advert(
            id = "${id++}"
        )
    }
}

package com.danser.paged_list_adapter.domain

import com.danser.paged_list_adapter.repository.AdvertsRepository
import com.danser.paged_list_adapter.repository.IAdvertsRepository
import com.danser.paged_list_adapter.repository.IOffersRepository
import com.danser.paged_list_adapter.repository.OffersRepository

class FeedInteractor(
    private val offersRepo: IOffersRepository = OffersRepository(),
    private val advertsRepo: IAdvertsRepository = AdvertsRepository()
) {
    fun getFeedItems(startPosition: Int, pageSize: Int): List<FeedItem> = offersRepo.getItems(10)
        .mapIndexed { idx, item ->
            when {
                idx % 4  == 3 -> listOf(item) + advertsRepo.getItems(1)
                else -> listOf(item)
            }
        }
        .flatten().apply { Thread.sleep(300) }
}

package com.danser.paged_list_adapter.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.danser.paged_list_adapter.domain.FeedInteractor
import com.danser.paged_list_adapter.domain.FeedItem
import com.danser.paged_list_adapter.repository.MyPositionalDataSource
import com.danser.paged_list_adapter.utils.delegate_adapter.IComparableItem
import com.danser.paged_list_adapter.utils.delegate_adapter.MainThreadExecutor
import java.util.concurrent.Executors

class FeedPresentationModel(
    private val interactor: FeedInteractor = FeedInteractor(),
    private val feedVMFactory: FeedVMFactory = FeedVMFactory()
) : ViewModel() {

    val modelLiveData by lazy {
        MutableLiveData<FeedViewModel>()
    }

    private var model: Model


    init {
        model = Model(getPagedList(::getItems))
        update()
    }

    fun onOfferClicked(offer: FeedItem.Offer) {
        //TODO
    }

    fun onHideAdvertClicked(advert: FeedItem.Advert) {
        //remove advert from the list
        /*update {
            copy(
                items = items.filterNot { item ->
                    item is FeedItem.Advert && item.id == advert.id
                }
            )
        }*/
    }

    private fun getItems(startPosition: Int, pageSize: Int): List<IComparableItem> {
        val items = interactor.getFeedItems(startPosition, pageSize)
        return feedVMFactory.toViewModel(items)
    }

    private fun update(mapper: Model.() -> Model = { this }) {
        val newModel = model.mapper()
        modelLiveData.value = FeedViewModel(newModel.items)
    }

    private fun getPagedList(itemsProvider: (Int, Int) -> List<IComparableItem>): PagedList<IComparableItem> {
        val dataSource = MyPositionalDataSource(itemsProvider)

        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setPageSize(10)
            .build()

        return PagedList.Builder<Int, IComparableItem>(dataSource, config)
            .setNotifyExecutor(MainThreadExecutor())
            .setFetchExecutor(Executors.newSingleThreadExecutor())
            .build()
    }

    data class Model(
        val items: PagedList<IComparableItem>
    )
}

data class FeedViewModel(
    val pagedList: PagedList<IComparableItem>
)

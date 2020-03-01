package com.danser.paged_list_adapter.repository

import androidx.paging.PositionalDataSource
import com.danser.paged_list_adapter.utils.delegate_adapter.IComparableItem

internal class MyPositionalDataSource(
    private val itemsProvider: (Int, Int) -> List<IComparableItem>
) : PositionalDataSource<IComparableItem>() {

    override fun loadInitial(params: LoadInitialParams, callback: LoadInitialCallback<IComparableItem?>) {
        val result: List<IComparableItem> = itemsProvider(params.requestedStartPosition, params.requestedLoadSize)

        callback.onResult(result, 0)
    }

    override fun loadRange(params: LoadRangeParams, callback: LoadRangeCallback<IComparableItem>) {
        val result: List<IComparableItem> = itemsProvider(params.startPosition, params.loadSize)
        callback.onResult(result)
    }
}

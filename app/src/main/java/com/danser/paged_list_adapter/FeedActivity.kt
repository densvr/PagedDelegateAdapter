package com.danser.paged_list_adapter

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.danser.paged_list_adapter.presentation.FeedPresentationModel
import com.danser.paged_list_adapter.presentation.FeedViewModel
import com.danser.paged_list_adapter.utils.delegate_adapter.PagedDiffAdapter
import com.danser.paged_list_adapter.view.adapter.AdvertAdapter
import com.danser.paged_list_adapter.view.adapter.DividerAdapter
import com.danser.paged_list_adapter.view.adapter.OfferAdapter
import kotlinx.android.synthetic.main.activity_feed.*

class FeedActivity : AppCompatActivity() {

    private lateinit var presentation: FeedPresentationModel

    private val adapter: PagedDiffAdapter by lazy { getDiffAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_feed)

        initPresentationModel()
        bindRecycler()
    }

    private fun initPresentationModel() {
        presentation = ViewModelProviders.of(this)[FeedPresentationModel::class.java]

        presentation.modelLiveData.observe(this, Observer { model: FeedViewModel ->
            update(model)
        })
    }

    private fun bindRecycler() {
        rvList.adapter = adapter
        rvList.layoutManager = LinearLayoutManager(this)
    }

    private fun update(model: FeedViewModel) {
        adapter.submitList(model.pagedList)
    }

    private fun getDiffAdapter(): PagedDiffAdapter = PagedDiffAdapter(
        listOf(
            OfferAdapter(presentation::onOfferClicked),
            AdvertAdapter(presentation::onHideAdvertClicked),
            DividerAdapter
        )
    )

}

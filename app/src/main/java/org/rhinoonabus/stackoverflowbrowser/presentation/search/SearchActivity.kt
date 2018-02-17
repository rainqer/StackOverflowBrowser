package org.rhinoonabus.stackoverflowbrowser.presentation.search

import android.content.Context
import android.content.Intent
import com.infullmobile.android.infullmvp.InFullMvpActivity
import org.rhinoonabus.stackoverflowbrowser.presentation.search.di.SearchGraph
import javax.inject.Inject

class SearchActivity : InFullMvpActivity<SearchPresenter, SearchView>() {

    @Inject
    lateinit var searchPresenter: SearchPresenter
    @Inject
    lateinit var searchView: SearchView
    val searchGraph = SearchGraph()

    override val presenter: SearchPresenter get() = searchPresenter
    override val presentedView: SearchView get() = searchView

    override fun injectIntoGraph() {
        searchGraph.inject(this)
    }

    companion object {
        fun getIntent(context: Context) = Intent(context, SearchActivity::class.java)
    }
}

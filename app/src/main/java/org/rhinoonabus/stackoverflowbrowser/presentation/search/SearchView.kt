package org.rhinoonabus.stackoverflowbrowser.presentation.search

import android.support.annotation.LayoutRes
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.SearchView
import android.widget.Toast
import com.infullmobile.android.infullmvp.PresentedActivityView
import com.jakewharton.rxbinding2.support.v7.widget.queryTextChanges
import io.reactivex.Observable
import io.reactivex.Scheduler
import org.rhinoonabus.stackoverflowbrowser.R
import org.rhinoonabus.stackoverflowbrowser.domain.SearchResultItem
import java.util.concurrent.TimeUnit

open class SearchView(
        private val searchResultsAdapter: SearchResultsAdapter,
        private val queryTextChangedScheduler: Scheduler
) : PresentedActivityView<SearchPresenter>() {

    @LayoutRes
    override val layoutResId = R.layout.activity_search
    val searchView: SearchView by bindView(R.id.searchView)
    val listOfResults: RecyclerView by bindView(R.id.listOfResults)
    val somethingWentWrongText: String by bindString(R.string.general_error)

    open val queryTextChanges: Observable<String>
        get() = searchView
                .queryTextChanges()
                .skipInitialValue()
                .debounce(QUERY_DELAY_TIME_IN_SECONDS, TimeUnit.SECONDS, queryTextChangedScheduler)
                .map { it.toString() }

    override fun onViewsBound() {
        listOfResults.adapter = searchResultsAdapter
    }

    open fun displayResultsForPhrase(results: List<SearchResultItem>) = searchResultsAdapter.setData(results)

    open fun displayError(error: Throwable) =
            Toast.makeText(context, somethingWentWrongText, Toast.LENGTH_LONG).show()

    companion object {
        const val QUERY_DELAY_TIME_IN_SECONDS = 1L
    }
}

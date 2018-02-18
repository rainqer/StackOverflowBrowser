package org.rhinoonabus.stackoverflowbrowser.presentation.search

import android.support.annotation.LayoutRes
import android.support.v7.widget.SearchView
import com.infullmobile.android.infullmvp.PresentedActivityView
import com.jakewharton.rxbinding2.support.v7.widget.queryTextChanges
import io.reactivex.Observable
import org.rhinoonabus.stackoverflowbrowser.R
import org.rhinoonabus.stackoverflowbrowser.domain.CodeRepository
import java.util.concurrent.TimeUnit

open class SearchView : PresentedActivityView<SearchPresenter>() {

    @LayoutRes
    override val layoutResId = R.layout.activity_search

    val searchView: SearchView by bindView(R.id.searchView)

    open val queryTextChanges: Observable<String>
        get() = searchView
                .queryTextChanges()
                .skipInitialValue()
                .debounce(QUERY_DELAY_TIME_IN_SECONDS, TimeUnit.SECONDS)
                .map { it.toString() }

    override fun onViewsBound() {
        // NO-OP
    }

    open fun displayResultsForPhrase(results: List<CodeRepository>) {
        // NO-OP
    }

    open fun displayError(error: Throwable) {
        // NO-OP
    }

    companion object {
        const val QUERY_DELAY_TIME_IN_SECONDS = 2L
    }
}

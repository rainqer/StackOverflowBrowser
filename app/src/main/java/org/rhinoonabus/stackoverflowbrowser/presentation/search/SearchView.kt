package org.rhinoonabus.stackoverflowbrowser.presentation.search

import android.support.annotation.LayoutRes
import android.support.v7.widget.SearchView
import com.infullmobile.android.infullmvp.PresentedActivityView
import com.jakewharton.rxbinding2.support.v7.widget.queryTextChanges
import io.reactivex.Observable
import org.rhinoonabus.stackoverflowbrowser.R
import java.util.concurrent.TimeUnit

open class SearchView : PresentedActivityView<SearchPresenter>() {

    @LayoutRes
    override val layoutResId = R.layout.activity_search

    val searchView: SearchView by bindView(R.id.searchView)

    open val queryTextChanges: Observable<CharSequence>
        get() = searchView
                .queryTextChanges()
                .skipInitialValue()
                .debounce(QUERY_DELAY_TIME_IN_SECONDS, TimeUnit.SECONDS)

    override fun onViewsBound() {
        // NO-OP
    }

    companion object {
        const val QUERY_DELAY_TIME_IN_SECONDS = 2L
    }
}

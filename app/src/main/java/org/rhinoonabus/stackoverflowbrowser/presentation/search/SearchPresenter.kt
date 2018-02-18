package org.rhinoonabus.stackoverflowbrowser.presentation.search

import android.net.Uri
import android.os.Bundle
import com.infullmobile.android.infullmvp.Presenter
import org.rhinoonabus.stackoverflowbrowser.presentation.search.di.SearchModel

open class SearchPresenter(
        view: SearchView,
        private val model: SearchModel
) : Presenter<SearchView>(view) {

    override fun bind(intentBundle: Bundle, savedInstanceState: Bundle, intentData: Uri?) {
        presentedView.queryTextChanges
                .flatMapSingle { phrase -> model.queryGitHubForPhrase(phrase) }
                .subscribe { results -> presentedView.displayGitHubResultsForPhrase(results) }
    }
}

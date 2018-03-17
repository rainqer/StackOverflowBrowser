package org.rhinoonabus.stackoverflowbrowser.presentation.search

import android.net.Uri
import android.os.Bundle
import org.rhinoonabus.stackoverflowbrowser.presentation.RxPresenter

open class SearchPresenter(
        view: SearchView,
        private val model: SearchModel
) : RxPresenter<SearchView>(view) {

    override fun bind(intentBundle: Bundle, savedInstanceState: Bundle, intentData: Uri?) {
        presentedView.queryTextChanges
                .flatMapSingle { phrase -> model.queryForPhrase(phrase) }
                .subscribe (
                        { results -> presentedView.displayResultsForPhrase(results) },
                        { error -> presentedView.displayError(error) }
                ).register()
    }
}

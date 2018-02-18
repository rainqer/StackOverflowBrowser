package org.rhinoonabus.stackoverflowbrowser.presentation.search

import android.net.Uri
import android.os.Bundle
import com.infullmobile.android.infullmvp.Presenter

open class SearchPresenter(
        view: SearchView,
        private val model: SearchModel
) : Presenter<SearchView>(view) {

    override fun bind(intentBundle: Bundle, savedInstanceState: Bundle, intentData: Uri?) {
        presentedView.queryTextChanges
                .flatMapSingle { phrase -> model.queryForPhrase(phrase) }
                .subscribe (
                        { results -> presentedView.displayResultsForPhrase(results) },
                        { error -> presentedView.displayError(error) }
                )
    }
}

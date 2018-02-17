package org.rhinoonabus.stackoverflowbrowser.presentation.search

import android.net.Uri
import android.os.Bundle
import com.infullmobile.android.infullmvp.Presenter

open class SearchPresenter(
        view: SearchView
) : Presenter<SearchView>(view) {

    override fun bind(intentBundle: Bundle, savedInstanceState: Bundle, intentData: Uri?) {
        // NO-OP
    }
}

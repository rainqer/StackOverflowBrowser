package org.rhinoonabus.stackoverflowbrowser.presentation.search

import io.reactivex.Single

open class SearchModel {

    open fun queryForPhrase(phrase: String) = Single.fromCallable { listOf("exampleResult") }
}

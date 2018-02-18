package org.rhinoonabus.stackoverflowbrowser.presentation.search.di

import io.reactivex.Single

open class SearchModel {

    open fun queryGitHubForPhrase(phrase: String) = Single.fromCallable{ listOf("exampleResult") }
}

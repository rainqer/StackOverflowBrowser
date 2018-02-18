package org.rhinoonabus.stackoverflowbrowser.presentation.search

import org.rhinoonabus.stackoverflowbrowser.domain.SearchForRepositoriesWithPhraseUseCase

open class SearchModel(private val searchForRepositoriesWithPhraseUseCase: SearchForRepositoriesWithPhraseUseCase) {

    open fun queryForPhrase(phrase: String) = searchForRepositoriesWithPhraseUseCase.searchFor(phrase)
}

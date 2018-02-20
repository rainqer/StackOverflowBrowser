package org.rhinoonabus.stackoverflowbrowser.presentation.search

import org.rhinoonabus.stackoverflowbrowser.domain.SearchForRepositoriesOrUsersWithPhraseUseCase

open class SearchModel(private val searchForRepositoriesWithPhraseUseCase: SearchForRepositoriesOrUsersWithPhraseUseCase) {

    open fun queryForPhrase(phrase: String) = searchForRepositoriesWithPhraseUseCase.searchFor(phrase)
}

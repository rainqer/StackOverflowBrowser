package org.rhinoonabus.stackoverflowbrowser.presentation.search.di

import dagger.Module
import dagger.Provides
import org.rhinoonabus.stackoverflowbrowser.domain.SearchForRepositoriesWithPhraseUseCase
import org.rhinoonabus.stackoverflowbrowser.presentation.search.SearchModel
import org.rhinoonabus.stackoverflowbrowser.presentation.search.SearchPresenter
import org.rhinoonabus.stackoverflowbrowser.presentation.search.SearchResultsAdapter
import org.rhinoonabus.stackoverflowbrowser.presentation.search.SearchView

@Module
open class SearchModule {

    @SearchScope
    @Provides
    open fun providesSearchResultsAdapter() = SearchResultsAdapter()

    @SearchScope
    @Provides
    open fun providesSearchView(searchResultsAdapter: SearchResultsAdapter) = SearchView(searchResultsAdapter)

    @SearchScope
    @Provides
    open fun providesSearchModel(
            searchForRepositoriesWithPhraseUseCase: SearchForRepositoriesWithPhraseUseCase
    ) = SearchModel(searchForRepositoriesWithPhraseUseCase)

    @SearchScope
    @Provides
    open fun providesSearchPresenter(
            view: SearchView,
            model: SearchModel
    ) = SearchPresenter(view, model)
}

package org.rhinoonabus.stackoverflowbrowser.presentation.search.di

import dagger.Module
import dagger.Provides
import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers
import org.rhinoonabus.stackoverflowbrowser.domain.SearchForRepositoriesOrUsersWithPhraseUseCase
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
    open fun providesOnQueryTextChangedScheduler(): Scheduler = Schedulers.single()

    @SearchScope
    @Provides
    open fun providesSearchView(
            searchResultsAdapter: SearchResultsAdapter,
            onQueryTextChangedScheduler: Scheduler
    ) = SearchView(searchResultsAdapter, onQueryTextChangedScheduler)

    @SearchScope
    @Provides
    open fun providesSearchModel(
            searchForRepositoriesWithPhraseUseCase: SearchForRepositoriesOrUsersWithPhraseUseCase
    ) = SearchModel(searchForRepositoriesWithPhraseUseCase)

    @SearchScope
    @Provides
    open fun providesSearchPresenter(
            view: SearchView,
            model: SearchModel
    ) = SearchPresenter(view, model)
}

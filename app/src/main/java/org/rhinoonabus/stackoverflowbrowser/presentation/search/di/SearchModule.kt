package org.rhinoonabus.stackoverflowbrowser.presentation.search.di

import dagger.Module
import dagger.Provides
import org.rhinoonabus.stackoverflowbrowser.presentation.search.SearchPresenter
import org.rhinoonabus.stackoverflowbrowser.presentation.search.SearchView

@Module
class SearchModule() {

    @SearchScope
    @Provides
    internal fun providesSearchView() = SearchView()

    @SearchScope
    @Provides
    fun providesSearchPresenter(
            view: SearchView
    ) = SearchPresenter(view)
}

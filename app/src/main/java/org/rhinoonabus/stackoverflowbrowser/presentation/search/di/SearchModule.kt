package org.rhinoonabus.stackoverflowbrowser.presentation.search.di

import dagger.Module
import dagger.Provides
import org.rhinoonabus.stackoverflowbrowser.presentation.search.SearchPresenter
import org.rhinoonabus.stackoverflowbrowser.presentation.search.SearchView

@Module
open class SearchModule() {

    @SearchScope
    @Provides
    open fun providesSearchView() = SearchView()

    @SearchScope
    @Provides
    open fun providesSearchPresenter(
            view: SearchView
    ) = SearchPresenter(view)
}

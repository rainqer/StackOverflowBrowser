package org.rhinoonabus.stackoverflowbrowser.presentation.search.di

import dagger.Module
import dagger.Provides
import org.rhinoonabus.stackoverflowbrowser.presentation.search.SearchModel
import org.rhinoonabus.stackoverflowbrowser.presentation.search.SearchPresenter
import org.rhinoonabus.stackoverflowbrowser.presentation.search.SearchView

@Module
open class SearchModule {

    @SearchScope
    @Provides
    open fun providesSearchView() = SearchView()

    @SearchScope
    @Provides
    open fun providesSearchModel() = SearchModel()

    @SearchScope
    @Provides
    open fun providesSearchPresenter(
            view: SearchView,
            model: SearchModel
    ) = SearchPresenter(view, model)
}

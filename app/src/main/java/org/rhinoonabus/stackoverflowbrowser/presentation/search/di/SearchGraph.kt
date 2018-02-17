package org.rhinoonabus.stackoverflowbrowser.presentation.search.di

import android.support.annotation.VisibleForTesting
import dagger.Component
import org.rhinoonabus.stackoverflowbrowser.presentation.search.SearchActivity

class SearchGraph {

    private val builder: DaggerSearchGraph_SearchComponent.Builder

    init {
        this.builder = DaggerSearchGraph_SearchComponent
                .builder()
                .searchModule(SearchModule())
    }

    fun inject(activity: SearchActivity) {
        builder
                .build()
                .inject(activity)
    }

    @VisibleForTesting
    fun setSearchModule(module: SearchModule) {
        builder.searchModule(module)
    }

    @SearchScope
    @Component(modules = [SearchModule::class])
    interface SearchComponent {
        fun inject(activity: SearchActivity)
    }
}

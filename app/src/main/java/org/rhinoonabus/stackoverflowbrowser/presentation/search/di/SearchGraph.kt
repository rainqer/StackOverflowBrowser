package org.rhinoonabus.stackoverflowbrowser.presentation.search.di

import android.support.annotation.VisibleForTesting
import dagger.Component
import org.rhinoonabus.stackoverflowbrowser.presentation.application.Components
import org.rhinoonabus.stackoverflowbrowser.presentation.application.StackOverflowBrowserApplicationComponent
import org.rhinoonabus.stackoverflowbrowser.presentation.search.SearchActivity

class SearchGraph() {

    private val builder = DaggerSearchGraph_SearchComponent
            .builder()
            .searchModule(SearchModule())

    fun inject(activity: SearchActivity) {
        builder
                .stackOverflowBrowserApplicationComponent(Components.from(activity.application))
                .build()
                .inject(activity)
    }

    @VisibleForTesting
    fun setSearchModule(module: SearchModule) {
        builder.searchModule(module)
    }

    @SearchScope
    @Component(dependencies = [StackOverflowBrowserApplicationComponent::class], modules = [SearchModule::class])
    interface SearchComponent {
        fun inject(activity: SearchActivity)
    }
}

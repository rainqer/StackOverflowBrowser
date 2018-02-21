package org.rhinoonabus.stackoverflowbrowser.presentation.user_details.di

import android.support.annotation.VisibleForTesting
import dagger.Component
import org.rhinoonabus.stackoverflowbrowser.presentation.application.Components
import org.rhinoonabus.stackoverflowbrowser.presentation.application.StackOverflowBrowserApplicationComponent
import org.rhinoonabus.stackoverflowbrowser.presentation.user_details.UserDetailsActivity

class UserDetailsGraph {

    private val builder: DaggerUserDetailsGraph_UserDetailsComponent.Builder

    init {
        this.builder = DaggerUserDetailsGraph_UserDetailsComponent
                .builder()
                .userDetailsModule(UserDetailsModule())
    }

    fun inject(activity: UserDetailsActivity) {
        builder
                .stackOverflowBrowserApplicationComponent(Components.from(activity.application))
                .build()
                .inject(activity)
    }

    @VisibleForTesting
    fun setUserDetailsModule(module: UserDetailsModule) {
        builder.userDetailsModule(module)
    }

    @UserDetailsScope
    @Component(
            dependencies = [StackOverflowBrowserApplicationComponent::class],
            modules = [UserDetailsModule::class])
    interface UserDetailsComponent {
        fun inject(activity: UserDetailsActivity)
    }
}

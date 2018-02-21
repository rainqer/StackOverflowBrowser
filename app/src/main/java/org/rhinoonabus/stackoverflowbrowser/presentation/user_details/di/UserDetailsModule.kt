package org.rhinoonabus.stackoverflowbrowser.presentation.user_details.di

import dagger.Module
import dagger.Provides
import org.rhinoonabus.stackoverflowbrowser.presentation.user_details.UserDetailsPresenter
import org.rhinoonabus.stackoverflowbrowser.presentation.user_details.UserDetailsView

@Module
class UserDetailsModule {

    @UserDetailsScope
    @Provides
    internal fun providesUserDetailsView() = UserDetailsView()

    @UserDetailsScope
    @Provides
    fun providesUserDetailsPresenter(view: UserDetailsView) = UserDetailsPresenter(view)
}

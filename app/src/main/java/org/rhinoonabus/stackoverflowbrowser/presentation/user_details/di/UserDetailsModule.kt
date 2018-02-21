package org.rhinoonabus.stackoverflowbrowser.presentation.user_details.di

import dagger.Module
import dagger.Provides
import org.rhinoonabus.stackoverflowbrowser.domain.GetDetailsForUserWithLoginUseCase
import org.rhinoonabus.stackoverflowbrowser.presentation.user_details.UserDetailsModel
import org.rhinoonabus.stackoverflowbrowser.presentation.user_details.UserDetailsPresenter
import org.rhinoonabus.stackoverflowbrowser.presentation.user_details.UserDetailsView

@Module
class UserDetailsModule {

    @UserDetailsScope
    @Provides
    internal fun providesUserDetailsView() = UserDetailsView()

    @UserDetailsScope
    @Provides
    internal fun providesUserDetailsModel(
            getDetailsForUserWithLoginUseCase: GetDetailsForUserWithLoginUseCase
    ) = UserDetailsModel(getDetailsForUserWithLoginUseCase)

    @UserDetailsScope
    @Provides
    fun providesUserDetailsPresenter(view: UserDetailsView, model: UserDetailsModel) = UserDetailsPresenter(view, model)
}

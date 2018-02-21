package org.rhinoonabus.stackoverflowbrowser.presentation.user_details.di

import dagger.Module
import dagger.Provides
import org.rhinoonabus.stackoverflowbrowser.domain.GetDetailsForUserWithLoginUseCase
import org.rhinoonabus.stackoverflowbrowser.presentation.user_details.UserDetailsModel
import org.rhinoonabus.stackoverflowbrowser.presentation.user_details.UserDetailsPresenter
import org.rhinoonabus.stackoverflowbrowser.presentation.user_details.UserDetailsView

@Module
open class UserDetailsModule {

    @UserDetailsScope
    @Provides
    open fun providesUserDetailsView() = UserDetailsView()

    @UserDetailsScope
    @Provides
    open fun providesUserDetailsModel(
            getDetailsForUserWithLoginUseCase: GetDetailsForUserWithLoginUseCase
    ) = UserDetailsModel(getDetailsForUserWithLoginUseCase)

    @UserDetailsScope
    @Provides
    open fun providesUserDetailsPresenter(view: UserDetailsView, model: UserDetailsModel) =
            UserDetailsPresenter(view, model)
}

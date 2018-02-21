package org.rhinoonabus.stackoverflowbrowser.presentation.user_details

import org.rhinoonabus.stackoverflowbrowser.domain.GetDetailsForUserWithLoginUseCase

open class UserDetailsModel(
        private val getDetailsForUserWithLoginUseCase: GetDetailsForUserWithLoginUseCase
) {

    open fun getDetailsForUser(userLogin: String) = getDetailsForUserWithLoginUseCase.getUserWithLogin(userLogin)
}

package org.rhinoonabus.stackoverflowbrowser.presentation.user_details

import android.net.Uri
import android.os.Bundle
import org.rhinoonabus.stackoverflowbrowser.presentation.RxPresenter

open class UserDetailsPresenter(
        view: UserDetailsView,
        private val model: UserDetailsModel
) : RxPresenter<UserDetailsView>(view) {

    override fun bind(intentBundle: Bundle, savedInstanceState: Bundle, intentData: Uri?) {
        model.getDetailsForUser(extractUserIdFromBundle(intentBundle))
                .subscribe { userDetails -> presentedView.displayUserDetails(userDetails) }
                .register()
    }

    private fun extractUserIdFromBundle(intentBundle: Bundle) =
            intentBundle.getString(USER_LOGIN_KEY)
                    ?: throw IllegalStateException("Cannot display details for a user id")

    companion object {
        const val USER_LOGIN_KEY = "userLoginKey"
    }
}

package org.rhinoonabus.stackoverflowbrowser.presentation.user_details

import android.net.Uri
import android.os.Bundle
import com.infullmobile.android.infullmvp.Presenter

open class UserDetailsPresenter(
        view: UserDetailsView,
        private val model: UserDetailsModel
) : Presenter<UserDetailsView>(view) {

    override fun bind(intentBundle: Bundle, savedInstanceState: Bundle, intentData: Uri?) {
        model.getDetailsForUser(extractUserIdFromBundle(intentBundle))
                .subscribe { userDetails -> presentedView.displayUserDetails(userDetails) }
    }

    private fun extractUserIdFromBundle(intentBundle: Bundle) =
            intentBundle.getString(USER_LOGIN_KEY)
                    ?: throw IllegalStateException("Cannot display details for a user id")

    companion object {
        const val USER_LOGIN_KEY = "userLoginKey"
    }
}

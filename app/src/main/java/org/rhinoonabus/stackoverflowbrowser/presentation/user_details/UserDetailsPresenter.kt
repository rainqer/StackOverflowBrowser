package org.rhinoonabus.stackoverflowbrowser.presentation.user_details

import android.net.Uri
import android.os.Bundle
import com.infullmobile.android.infullmvp.Presenter

open class UserDetailsPresenter(
        view: UserDetailsView,
        private val model: UserDetailsModel
) : Presenter<UserDetailsView>(view) {

    override fun bind(intentBundle: Bundle, savedInstanceState: Bundle, intentData: Uri?) {
        model.getDetailsForUser("android")
                .subscribe { userDetails -> presentedView.displayUserDetails(userDetails) }
    }
}

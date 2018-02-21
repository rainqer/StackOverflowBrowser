package org.rhinoonabus.stackoverflowbrowser.presentation.user_details

import android.support.annotation.LayoutRes
import android.widget.Toast
import com.infullmobile.android.infullmvp.PresentedActivityView
import org.rhinoonabus.stackoverflowbrowser.R
import org.rhinoonabus.stackoverflowbrowser.domain.CodeRepositoryUserDetails

open class UserDetailsView : PresentedActivityView<UserDetailsPresenter>() {

    @LayoutRes
    override val layoutResId = R.layout.activity_user_details

    override fun onViewsBound() {
        // NO-OP
    }

    open fun displayUserDetails(userDetails: CodeRepositoryUserDetails) =
            Toast.makeText(context, userDetails.login, Toast.LENGTH_LONG).show()
}

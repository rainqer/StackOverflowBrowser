package org.rhinoonabus.stackoverflowbrowser.presentation.user_details

import android.support.annotation.LayoutRes
import com.infullmobile.android.infullmvp.PresentedActivityView
import org.rhinoonabus.stackoverflowbrowser.R

open class UserDetailsView: PresentedActivityView<UserDetailsPresenter>() {

    @LayoutRes
    override val layoutResId = R.layout.activity_user_details

    override fun onViewsBound() {
        // NO-OP
    }
}

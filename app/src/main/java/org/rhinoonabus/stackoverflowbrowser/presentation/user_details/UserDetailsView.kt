package org.rhinoonabus.stackoverflowbrowser.presentation.user_details

import android.support.annotation.LayoutRes
import android.widget.ImageView
import android.widget.TextView
import com.infullmobile.android.infullmvp.PresentedActivityView
import com.squareup.picasso.Picasso
import org.rhinoonabus.stackoverflowbrowser.R
import org.rhinoonabus.stackoverflowbrowser.domain.CodeRepositoryUserDetails

open class UserDetailsView : PresentedActivityView<UserDetailsPresenter>() {

    @LayoutRes
    override val layoutResId = R.layout.activity_user_details

    val userLogin: TextView by bindView(R.id.userLogin)
    val numberOfFollowers: TextView by bindView(R.id.numberOfFollowers)
    val userAvatar: ImageView by bindView(R.id.userAvatar)

    override fun onViewsBound() {
        // NO-OP
    }

    open fun displayUserDetails(userDetails: CodeRepositoryUserDetails) {
        userLogin.text = userDetails.login
        numberOfFollowers.text = userDetails.numberOfFollowers.toString()
        Picasso.with(context).load(userDetails.avatarUrl).into(userAvatar)
    }
}

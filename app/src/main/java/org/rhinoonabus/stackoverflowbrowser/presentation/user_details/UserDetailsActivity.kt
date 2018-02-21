package org.rhinoonabus.stackoverflowbrowser.presentation.user_details

import android.content.Context
import android.content.Intent
import com.infullmobile.android.infullmvp.InFullMvpActivity
import org.rhinoonabus.stackoverflowbrowser.presentation.user_details.di.UserDetailsGraph
import javax.inject.Inject

class UserDetailsActivity : InFullMvpActivity<UserDetailsPresenter, UserDetailsView>() {

    @Inject
    lateinit var userDetailsPresenter: UserDetailsPresenter
    @Inject
    lateinit var userDetailsView: UserDetailsView
    val userDetailsGraph = UserDetailsGraph()

    override val presenter: UserDetailsPresenter get() = userDetailsPresenter
    override val presentedView: UserDetailsView get() = userDetailsView

    override fun injectIntoGraph() = userDetailsGraph.inject(this)

    companion object {
        fun getIntent(context: Context) = Intent(context, UserDetailsActivity::class.java)
    }
}

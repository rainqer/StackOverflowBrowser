package org.rhinoonabus.stackoverflowbrowser.presentation.search

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.rhinoonabus.stackoverflowbrowser.R
import org.rhinoonabus.stackoverflowbrowser.domain.CodeRepositoryFactory.REPOSITORY_A
import org.rhinoonabus.stackoverflowbrowser.domain.CodeRepositoryUserFactory.USER_A
import org.rhinoonabus.stackoverflowbrowser.presentation.user_details.UserDetailsActivity
import org.rhinoonabus.stackoverflowbrowser.presentation.user_details.UserDetailsPresenter
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import org.robolectric.Shadows

@RunWith(RobolectricTestRunner::class)
class SearchResultViewHolderTest {

    private val activity = Robolectric.setupActivity(Activity::class.java)

    @Test
    fun shouldBindCodeRepositoryDataToFields() {
        // given
        val codeRepositoryToBind = REPOSITORY_A
        val viewHolder = buildViewHolder()

        // when
        viewHolder.bind(codeRepositoryToBind)

        // then
        assertThat(viewHolder.itemType.text).isEqualTo(getString(R.string.codeRepositoryTypeName))
        assertThat(viewHolder.itemName.text).isEqualTo(codeRepositoryToBind.name)
        assertThat(viewHolder.itemUrl.text).isEqualTo(codeRepositoryToBind.url)
    }

    @Test
    fun shouldBindCodeRepositoryUserDataToFields() {
        // given
        val codeRepositoryUserToBind = USER_A
        val viewHolder = buildViewHolder()

        // when
        viewHolder.bind(codeRepositoryUserToBind)

        // then
        assertThat(viewHolder.itemType.text).isEqualTo(getString(R.string.codeRepositoryUserTypeName))
        assertThat(viewHolder.itemName.text).isEqualTo(codeRepositoryUserToBind.name)
        assertThat(viewHolder.itemUrl.text).isEqualTo(codeRepositoryUserToBind.url)
    }

    @Test
    fun shouldStartUserDetailsActivityForUserNameIfViewHolderBoundToUser() {
        // given
        val codeRepositoryUserToBind = USER_A
        val viewHolder = buildViewHolder()
        viewHolder.bind(codeRepositoryUserToBind)

        // when
        viewHolder.itemView.performClick()

        // then
        val startedIntent = checkIfActivityStarted(UserDetailsActivity::class.java)
        assertThat(startedIntent.getStringExtra(UserDetailsPresenter.USER_LOGIN_KEY)).isEqualTo(USER_A.name)
    }

    @Test
    fun shouldNotStartAnyActivityIfViewHolderBoundToRepository() {
        // given
        val codeRepositoryUserToBind = REPOSITORY_A
        val viewHolder = buildViewHolder()
        viewHolder.bind(codeRepositoryUserToBind)

        // when
        viewHolder.itemView.performClick()

        // then
        assertThat(Shadows.shadowOf(activity).nextStartedActivity).isNull()
    }

    private fun buildViewHolder() = SearchResultViewHolder(inflateViewForViewHolder())

    private fun inflateViewForViewHolder() =
            LayoutInflater.from(activity).inflate(R.layout.search_result_view_holder, null, false)

    private fun getString(stringRes: Int) = RuntimeEnvironment.application.getString(stringRes)

    private fun checkIfActivityStarted(activityClass: Class<out Activity>): Intent {
        val intent = Shadows.shadowOf(activity).nextStartedActivity
        assertThat(intent.component.className).isEqualTo(activityClass.name)
        return intent
    }
}
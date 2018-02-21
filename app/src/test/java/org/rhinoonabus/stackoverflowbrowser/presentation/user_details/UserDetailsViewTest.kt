package org.rhinoonabus.stackoverflowbrowser.presentation.user_details

import com.infullmobile.android.infullmvp.basetest.InFullMvpActivityBaseTest
import com.nhaarman.mockito_kotlin.mock
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.rhinoonabus.stackoverflowbrowser.domain.CodeRepositoryUserDetailsFactory
import org.rhinoonabus.stackoverflowbrowser.domain.GetDetailsForUserWithLoginUseCase
import org.rhinoonabus.stackoverflowbrowser.presentation.user_details.di.UserDetailsModule
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class UserDetailsViewTest : InFullMvpActivityBaseTest<UserDetailsActivity, UserDetailsPresenter, UserDetailsView>() {

    val mockedUserDetailsPresenter = mock<UserDetailsPresenter>()
    val mockedUserDetailsModel = mock<UserDetailsModel>()

    @Test
    fun shouldDisplayUserData() {
        // given
        val numberOfStarredRepositories = 12
        val testUserDetails = CodeRepositoryUserDetailsFactory.A_USER_DETAILS
                .copy(numberOfStarredRepositories = numberOfStarredRepositories)

        // when
        testedView.displayUserDetails(testUserDetails)

        // then
        assertThat(testedView.userLogin.text).isEqualTo(testUserDetails.login)
        assertThat(testedView.numberOfFollowers.text).isEqualTo(testUserDetails.numberOfFollowers.toString())
        assertThat(testedView.numberOfStarredRepositories.text).isEqualTo(numberOfStarredRepositories.toString())
    }

    override val testActivityClass = UserDetailsActivity::class.java

    override fun substituteModules(activity: UserDetailsActivity) =
            activity.userDetailsGraph.setUserDetailsModule(TestUserDetailsModule())

    inner class TestUserDetailsModule : UserDetailsModule() {

        override fun providesUserDetailsModel(
                getDetailsForUserWithLoginUseCase: GetDetailsForUserWithLoginUseCase
        ) = mockedUserDetailsModel

        override fun providesUserDetailsPresenter(
                view: UserDetailsView,
                model: UserDetailsModel
        ) = mockedUserDetailsPresenter
    }
}
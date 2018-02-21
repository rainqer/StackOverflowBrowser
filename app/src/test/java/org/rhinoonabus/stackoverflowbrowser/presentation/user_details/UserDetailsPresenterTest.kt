package org.rhinoonabus.stackoverflowbrowser.presentation.user_details

import android.os.Bundle
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Single
import org.junit.Test
import org.junit.runner.RunWith
import org.rhinoonabus.stackoverflowbrowser.domain.CodeRepositoryUserDetailsFactory
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class UserDetailsPresenterTest {

    val mockedUserDetailsView = mock<UserDetailsView>()
    val mockedUserDetailsModel = mock<UserDetailsModel>()
    val presenter = UserDetailsPresenter(mockedUserDetailsView, mockedUserDetailsModel)

    @Test
    fun shouldDownloadUserDetailsAndDisplayDelegateThemToView() {
        // given
        val downloadedUser = CodeRepositoryUserDetailsFactory.A_USER_DETAILS
        val userLogin = "userLogin"
        whenever(mockedUserDetailsModel.getDetailsForUser(userLogin)).thenReturn(Single.just(downloadedUser))

        // when
        presenter.bind(getBundleWithUserLogin(userLogin), Bundle(), null)

        // then
        verify(mockedUserDetailsView).displayUserDetails(downloadedUser)
    }

    @Test(expected = IllegalStateException::class)
    fun shouldThrowErrorWhenTryingToBindPresenterWithoutProvidingProperUserLogin() {
        // given
        val intentBundleWithoutUserLogin = Bundle()

        // when
        presenter.bind(intentBundleWithoutUserLogin, Bundle(), null)
    }

    private fun getBundleWithUserLogin(userLogin: String) =
            Bundle().apply { putString(UserDetailsPresenter.USER_LOGIN_KEY, userLogin) }
}
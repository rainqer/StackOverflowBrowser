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
        whenever(mockedUserDetailsModel.getDetailsForUser("android")).thenReturn(Single.just(downloadedUser))

        // when
        presenter.bind(Bundle(), Bundle(), null)

        // then
        verify(mockedUserDetailsView).displayUserDetails(downloadedUser)
    }
}
package org.rhinoonabus.stackoverflowbrowser.domain

import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Single
import io.reactivex.schedulers.TestScheduler
import org.junit.Test
import org.rhinoonabus.stackoverflowbrowser.domain.CodeRepositoryUserDetailsFactory.A_USER_DETAILS

class GetDetailsForUserWithLoginUseCaseTest {

    val testScheduler = TestScheduler()
    val mockedSourceCodeManagementRepository = mock<SourceCodeManagementRepository>()
    val expectedUserDetails = A_USER_DETAILS
    val expectedNumberOfStarredRepositories = 123
    val getDetailsForUserWithLoginUseCase = GetDetailsForUserWithLoginUseCase(
            testScheduler,
            testScheduler,
            mockedSourceCodeManagementRepository
    )

    @Test
    fun shouldGetUserDetailsWithNumberOfStarredRepositoriesFromSourceCodeManagementRepository() {
        // given
        val userLogin = "testUserLogin"
        whenever(mockedSourceCodeManagementRepository.getUserDetails(userLogin)).thenReturn(Single.just(expectedUserDetails))
        whenever(mockedSourceCodeManagementRepository.getUserNumberOfRepositoriesStarredByUser(userLogin))
                .thenReturn(Single.just(expectedNumberOfStarredRepositories))

        // when
        val testState = getDetailsForUserWithLoginUseCase.getUserWithLogin(userLogin).test()

        // then
        testScheduler.triggerActions()
        testState.assertComplete()
        testState.assertValue { result ->
            result.login == expectedUserDetails.login
            && result.id == expectedUserDetails.id
            && result.avatarUrl == expectedUserDetails.avatarUrl
            && result.numberOfFollowers == expectedUserDetails.numberOfFollowers
            && result.numberOfStarredRepositories == expectedNumberOfStarredRepositories
        }
    }

    @Test
    fun shouldReturnErrorWhenGettingUserDetailsFromSourceCodeManagementRepositoryReturnsError() {
        // given
        val userLogin = "testUserLogin"
        val error = IllegalStateException("testError")
        whenever(mockedSourceCodeManagementRepository.getUserDetails(userLogin)).thenReturn(Single.error(error))
        whenever(mockedSourceCodeManagementRepository.getUserNumberOfRepositoriesStarredByUser(userLogin))
                .thenReturn(Single.just(expectedNumberOfStarredRepositories))

        // when
        val testState = getDetailsForUserWithLoginUseCase.getUserWithLogin(userLogin).test()

        // then
        testScheduler.triggerActions()
        testState.assertError(error)
    }
}
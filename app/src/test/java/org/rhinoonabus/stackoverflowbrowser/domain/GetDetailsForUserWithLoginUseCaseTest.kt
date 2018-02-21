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
    val getDetailsForUserWithLoginUseCase = GetDetailsForUserWithLoginUseCase(
            testScheduler,
            testScheduler,
            mockedSourceCodeManagementRepository
    )

    @Test
    fun shouldGetUserDetailsFromSourceCodeManagementRepository() {
        // given
        val userLogin = "testUserLogin"
        val expectedResult = A_USER_DETAILS
        whenever(mockedSourceCodeManagementRepository.getUserDetails(userLogin)).thenReturn(Single.just(expectedResult))

        // when
        val testState = getDetailsForUserWithLoginUseCase.getUserWithLogin(userLogin).test()

        // then
        testScheduler.triggerActions()
        testState.assertComplete()
        testState.assertValue(expectedResult)
    }

    @Test
    fun shouldReturnErrorWhenGettingUserDetailsFromSourceCodeManagementRepositoryReturnsError() {
        // given
        val userLogin = "testUserLogin"
        val error = IllegalStateException("testError")
        whenever(mockedSourceCodeManagementRepository.getUserDetails(userLogin)).thenReturn(Single.error(error))

        // when
        val testState = getDetailsForUserWithLoginUseCase.getUserWithLogin(userLogin).test()

        // then
        testScheduler.triggerActions()
        testState.assertError(error)
    }
}
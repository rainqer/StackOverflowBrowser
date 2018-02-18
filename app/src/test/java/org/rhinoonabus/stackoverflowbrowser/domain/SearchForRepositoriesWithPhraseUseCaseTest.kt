package org.rhinoonabus.stackoverflowbrowser.domain

import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Single
import io.reactivex.schedulers.TestScheduler
import org.junit.Test
import org.junit.runner.RunWith
import org.rhinoonabus.stackoverflowbrowser.domain.CodeRepositoryFactory.REPOSITORY_A
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class SearchForRepositoriesWithPhraseUseCaseTest {

    val testScheduler = TestScheduler()
    val mockedSourceCodeManagementRepository = mock<SourceCodeManagementRepository>()
    private val useCase = SearchForRepositoriesWithPhraseUseCase(
            testScheduler,
            testScheduler,
            mockedSourceCodeManagementRepository
    )

    @Test
    fun shouldSearchForPhraseInTheSourceCodeManagementRepository() {
        // given
        val testSearchPhrase = "testPhrase"
        val expectedResult = listOf(REPOSITORY_A)
        whenever(mockedSourceCodeManagementRepository.searchForCodeRepositories(testSearchPhrase))
                .thenReturn(Single.just(expectedResult))

        // when
        val testedState = useCase.searchFor(testSearchPhrase).test()

        // then
        testScheduler.triggerActions()
        testedState.awaitTerminalEvent()
        testedState.assertValue(expectedResult)
    }

    @Test
    fun shouldReturnErrorWhenSearchingForPhraseInTheSourceCodeManagementRepositoryReturnsError() {
        // given
        val testSearchPhrase = "testPhrase"
        val error = IllegalStateException("testError")
        whenever(mockedSourceCodeManagementRepository.searchForCodeRepositories(testSearchPhrase))
                .thenReturn(Single.error(error))

        // when
        val testedState = useCase.searchFor(testSearchPhrase).test()

        // then
        testScheduler.triggerActions()
        testedState.assertError(error)
    }
}
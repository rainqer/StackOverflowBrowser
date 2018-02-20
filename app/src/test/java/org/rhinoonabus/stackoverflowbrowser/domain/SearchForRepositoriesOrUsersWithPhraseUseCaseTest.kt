package org.rhinoonabus.stackoverflowbrowser.domain

import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Single
import io.reactivex.schedulers.TestScheduler
import org.junit.Test
import org.junit.runner.RunWith
import org.rhinoonabus.stackoverflowbrowser.domain.CodeRepositoryFactory.REPOSITORY_A
import org.rhinoonabus.stackoverflowbrowser.domain.CodeRepositoryFactory.REPOSITORY_B
import org.rhinoonabus.stackoverflowbrowser.domain.CodeRepositoryUserFactory.USER_A
import org.rhinoonabus.stackoverflowbrowser.domain.CodeRepositoryUserFactory.USER_B
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class SearchForRepositoriesOrUsersWithPhraseUseCaseTest {

    val testScheduler = TestScheduler()
    val correctSetOfRepositories = listOf(REPOSITORY_A, REPOSITORY_B)
    val correctSetOfUsers = listOf(USER_A, USER_B)
    val mockedSourceCodeManagementRepository = mock<SourceCodeManagementRepository>()
    private val useCase = SearchForRepositoriesOrUsersWithPhraseUseCase(
            testScheduler,
            testScheduler,
            mockedSourceCodeManagementRepository
    )

    @Test
    fun shouldSearchForPhraseInTheSourceCodeManagementRepository() {
        // given
        val testSearchPhrase = "testPhrase"
        whenever(mockedSourceCodeManagementRepository.searchForCodeRepositories(testSearchPhrase))
                .thenReturn(Single.just(correctSetOfRepositories))
        whenever(mockedSourceCodeManagementRepository.searchUsers(testSearchPhrase))
                .thenReturn(Single.just(correctSetOfUsers))

        // when
        val testedState = useCase.searchFor(testSearchPhrase).test()

        // then
        testScheduler.triggerActions()
        testedState.awaitTerminalEvent()
        testedState.assertValue { result ->
            result.containsAll(correctSetOfRepositories) && result.containsAll(correctSetOfUsers)
        }
    }

    @Test
    fun shouldGiveTheSearchResultsSortedByIdInAscendingOrder() {
        // given
        val listOfMixedRepositories =
                listOf(REPOSITORY_A.copy(id = 2), REPOSITORY_A.copy(id = 3), REPOSITORY_A.copy(id = 1))
        val listOfMixedUsers =
                listOf(USER_A.copy(id = 12), USER_A.copy(id = 13), USER_A.copy(id = 11))

        val testSearchPhrase = "testPhrase"
        whenever(mockedSourceCodeManagementRepository.searchForCodeRepositories(testSearchPhrase))
                .thenReturn(Single.just(listOfMixedRepositories))
        whenever(mockedSourceCodeManagementRepository.searchUsers(testSearchPhrase))
                .thenReturn(Single.just(listOfMixedUsers))

        // when
        val testedState = useCase.searchFor(testSearchPhrase).test()

        // then
        testScheduler.triggerActions()
        testedState.awaitTerminalEvent()
        testedState.assertValue { result ->
            result.containsAll(listOfMixedRepositories) && result.containsAll(listOfMixedUsers)
            && result[0].id == 1L
            && result[1].id == 2L
            && result[2].id == 3L
            && result[3].id == 11L
            && result[4].id == 12L
            && result[5].id == 13L
        }
    }

    @Test
    fun shouldReturnOnlyUsersWhenSearchingForRepositoryByPhraseInTheSourceCodeManagementRepositoryReturnsError() {
        // given
        val testSearchPhrase = "testPhrase"
        val error = IllegalStateException("testError")
        whenever(mockedSourceCodeManagementRepository.searchForCodeRepositories(testSearchPhrase))
                .thenReturn(Single.error(error))
        whenever(mockedSourceCodeManagementRepository.searchUsers(testSearchPhrase))
                .thenReturn(Single.just(correctSetOfUsers))

        // when
        val testedState = useCase.searchFor(testSearchPhrase).test()

        // then
        testScheduler.triggerActions()
        testedState.assertValue { result -> result.containsAll(correctSetOfUsers)}
    }

    @Test
    fun shouldReturnOnlyRepositoriesWhenSearchingForUserByPhraseInTheSourceCodeManagementRepositoryReturnsError() {
        // given
        val testSearchPhrase = "testPhrase"
        val error = IllegalStateException("testError")
        whenever(mockedSourceCodeManagementRepository.searchForCodeRepositories(testSearchPhrase))
                .thenReturn(Single.just(correctSetOfRepositories))
        whenever(mockedSourceCodeManagementRepository.searchUsers(testSearchPhrase))
                .thenReturn(Single.error(error))

        // when
        val testedState = useCase.searchFor(testSearchPhrase).test()

        // then
        testScheduler.triggerActions()
        testedState.assertValue { result -> result.containsAll(correctSetOfRepositories)}
    }

    @Test
    fun shouldReturnEmptyListWhenBothSearchingForRepositoriesAndUsersReturnsError() {
        // given
        val testSearchPhrase = "testPhrase"
        val error = IllegalStateException("testError")
        whenever(mockedSourceCodeManagementRepository.searchForCodeRepositories(testSearchPhrase))
                .thenReturn(Single.error(error))
        whenever(mockedSourceCodeManagementRepository.searchUsers(testSearchPhrase))
                .thenReturn(Single.error(error))

        // when
        val testedState = useCase.searchFor(testSearchPhrase).test()

        // then
        testScheduler.triggerActions()
        testedState.assertComplete()
        testedState.assertValue { result -> result.isEmpty()}
    }
}
package org.rhinoonabus.stackoverflowbrowser.repository

import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Single
import org.junit.Test
import org.rhinoonabus.stackoverflowbrowser.repository.GitHubSearchForRepositoriesResponseEntityFactory.SEARCH_FOR_REPO_RESPONSE_A
import org.rhinoonabus.stackoverflowbrowser.repository.GitHubSearchForRepositoryUsersResponseEntityFactory.SEARCH_FOR_USER_RESPONSE_A
import org.rhinoonabus.stackoverflowbrowser.repository.GitHubSourceCodeManagementRepository.Companion.RESULTS_PER_PAGE

class GitHubSourceCodeManagementRepositoryTest {

    val mockedGitHubClient = mock<GitHubApiClient>()
    val gitHubSourceCodeManagementRepository = GitHubSourceCodeManagementRepository(mockedGitHubClient)

    @Test
    fun shouldReturnListOfMappedCodeRepositories() {
        // given
        val testPhrase = "testPhrase"
        val expectedResultEntity = SEARCH_FOR_REPO_RESPONSE_A
        whenever(mockedGitHubClient.searchForRepositories(testPhrase, RESULTS_PER_PAGE))
                .thenReturn(Single.just(expectedResultEntity))

        // when
        val testedState = gitHubSourceCodeManagementRepository.searchForCodeRepositories(testPhrase).test()

        // then
        testedState.assertComplete()
        val expectedEntity = expectedResultEntity.items?.get(0)
        testedState.assertValue { resultListOfRepositories ->
            resultListOfRepositories[0].id == expectedEntity?.id
            && resultListOfRepositories[0].name == expectedEntity.name
            && resultListOfRepositories[0].description == expectedEntity.description
            && resultListOfRepositories[0].url == expectedEntity.url
        }
    }

    @Test
    fun shouldReturnErrorIfGitHubClientReturnsErrorWheSearchingForRepositories() {
        // given
        val testPhrase = "testPhrase"
        val error = IllegalStateException("testError")
        whenever(mockedGitHubClient.searchForRepositories(testPhrase, RESULTS_PER_PAGE))
                .thenReturn(Single.error(error))

        // when
        val testedState = gitHubSourceCodeManagementRepository.searchForCodeRepositories(testPhrase).test()

        // then
        testedState.assertError(error)

    }@Test
    fun shouldReturnListOfMappedCodeRepositoryUsers() {
        // given
        val testPhrase = "testPhrase"
        val expectedResultEntity = SEARCH_FOR_USER_RESPONSE_A
        whenever(mockedGitHubClient.searchForUsers(testPhrase, RESULTS_PER_PAGE))
                .thenReturn(Single.just(expectedResultEntity))

        // when
        val testedState = gitHubSourceCodeManagementRepository.searchUsers(testPhrase).test()

        // then
        testedState.assertComplete()
        val expectedEntity = expectedResultEntity.items?.get(0)
        testedState.assertValue { resultListOfRepositoryUsers ->
            resultListOfRepositoryUsers[0].id == expectedEntity?.id
            && resultListOfRepositoryUsers[0].name == expectedEntity.name
            && resultListOfRepositoryUsers[0].url == expectedEntity.url
        }
    }

    @Test
    fun shouldReturnErrorIfGitHubClientReturnsErrorWhenSearchingForUsers() {
        // given
        val testPhrase = "testPhrase"
        val error = IllegalStateException("testError")
        whenever(mockedGitHubClient.searchForUsers(testPhrase, RESULTS_PER_PAGE))
                .thenReturn(Single.error(error))

        // when
        val testedState = gitHubSourceCodeManagementRepository.searchUsers(testPhrase).test()

        // then
        testedState.assertError(error)
    }
}
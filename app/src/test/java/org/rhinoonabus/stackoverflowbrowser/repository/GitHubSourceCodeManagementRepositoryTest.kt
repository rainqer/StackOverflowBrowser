package org.rhinoonabus.stackoverflowbrowser.repository

import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Single
import org.junit.Test
import org.rhinoonabus.stackoverflowbrowser.repository.GitHubRepositoryEntityFactory.REPOSITORY_ENTITY_A
import org.rhinoonabus.stackoverflowbrowser.repository.GitHubSourceCodeManagementRepository.Companion.RESULTS_PER_PAGE

class GitHubSourceCodeManagementRepositoryTest {

    val mockedGitHubClient = mock<GitHubApiClient>()
    val gitHubSourceCodeManagementRepository = GitHubSourceCodeManagementRepository(mockedGitHubClient)

    @Test
    fun shouldReturnListOfMappedCodeRepositories() {
        // given
        val testPhrase = "testPhrase"
        val expectedResultEntity = REPOSITORY_ENTITY_A
        whenever(mockedGitHubClient.searchForRepositories(testPhrase, RESULTS_PER_PAGE))
                .thenReturn(Single.just(listOf(expectedResultEntity)))

        // when
        val testedState = gitHubSourceCodeManagementRepository.searchForCodeRepositories(testPhrase).test()

        // then
        testedState.assertComplete()
        testedState.assertValue { resultListOfRepositories ->
            resultListOfRepositories[0].id == expectedResultEntity.id
            && resultListOfRepositories[0].name == expectedResultEntity.name
            && resultListOfRepositories[0].description == expectedResultEntity.description
            && resultListOfRepositories[0].url == expectedResultEntity.url
        }
    }

    @Test
    fun shouldReturnErrorIfHitHubClientReturnsError() {
        // given
        val testPhrase = "testPhrase"
        val error = IllegalStateException("testError")
        whenever(mockedGitHubClient.searchForRepositories(testPhrase, RESULTS_PER_PAGE))
                .thenReturn(Single.error(error))

        // when
        val testedState = gitHubSourceCodeManagementRepository.searchForCodeRepositories(testPhrase).test()

        // then
        testedState.assertError(error)
    }
}
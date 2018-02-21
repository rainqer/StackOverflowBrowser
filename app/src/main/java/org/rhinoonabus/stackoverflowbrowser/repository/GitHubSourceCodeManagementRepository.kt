package org.rhinoonabus.stackoverflowbrowser.repository

import io.reactivex.Observable
import io.reactivex.Single
import org.rhinoonabus.stackoverflowbrowser.domain.CodeRepository
import org.rhinoonabus.stackoverflowbrowser.domain.CodeRepositoryUser
import org.rhinoonabus.stackoverflowbrowser.domain.CodeRepositoryUserDetails
import org.rhinoonabus.stackoverflowbrowser.domain.SourceCodeManagementRepository

class GitHubSourceCodeManagementRepository(
        private val gitHubClient: GitHubApiClient
) : SourceCodeManagementRepository {

    override fun searchForCodeRepositories(searchPhrase: String): Single<List<CodeRepository>> =
            gitHubClient.searchForRepositories(searchPhrase, RESULTS_PER_PAGE)
                    .flatMapObservable { Observable.fromIterable(it.items) }
                    .map { CodeRepository(it) }
                    .toList()

    override fun searchUsers(searchPhrase: String): Single<List<CodeRepositoryUser>> =
            gitHubClient.searchForUsers(searchPhrase, RESULTS_PER_PAGE)
                    .flatMapObservable { Observable.fromIterable(it.items) }
                    .map { CodeRepositoryUser(it) }
                    .toList()

    override fun getUserDetails(userLogin: String): Single<CodeRepositoryUserDetails> =
            gitHubClient.getUserDetails(userLogin)
                    .map { CodeRepositoryUserDetails(it) }

    companion object {
        const val RESULTS_PER_PAGE = 10
    }
}

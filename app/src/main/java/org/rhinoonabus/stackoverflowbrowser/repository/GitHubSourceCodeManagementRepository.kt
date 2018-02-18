package org.rhinoonabus.stackoverflowbrowser.repository

import io.reactivex.Observable
import io.reactivex.Single
import org.rhinoonabus.stackoverflowbrowser.domain.CodeRepository
import org.rhinoonabus.stackoverflowbrowser.domain.SourceCodeManagementRepository

class GitHubSourceCodeManagementRepository(
        private val gitHubClient: GitHubApiClient
) : SourceCodeManagementRepository {

    override fun searchForCodeRepositories(searchPhrase: String): Single<List<CodeRepository>> =
            gitHubClient.searchForRepositories(searchPhrase, RESULTS_PER_PAGE)
                    .flatMapObservable { Observable.fromIterable(it) }
                    .map { CodeRepository(it) }
                    .toList()

    companion object {
        const val RESULTS_PER_PAGE = 10
    }

}

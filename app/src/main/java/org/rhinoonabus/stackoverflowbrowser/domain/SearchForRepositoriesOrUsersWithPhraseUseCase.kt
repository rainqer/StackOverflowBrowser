package org.rhinoonabus.stackoverflowbrowser.domain

import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.functions.BiFunction

open class SearchForRepositoriesOrUsersWithPhraseUseCase(
        private val executionScheduler: Scheduler,
        private val redirectionScheduler: Scheduler,
        private val sourceCodeManagementRepository: SourceCodeManagementRepository
) {
    open fun searchFor(phrase: String): Single<List<SearchResultItem>> =
            sourceCodeManagementRepository.searchForCodeRepositories(phrase)
                    .zipWith(sourceCodeManagementRepository.searchUsers(phrase), combineRepositoriesWithUsers())
            .subscribeOn(executionScheduler)
            .observeOn(redirectionScheduler)

    private fun combineRepositoriesWithUsers() =
            BiFunction<List<CodeRepository>, List<CodeRepositoryUser>, List<SearchResultItem>>
            { listOfRepositories, listOfUsers -> (listOfRepositories as List<SearchResultItem>).plus(listOfUsers) }
}

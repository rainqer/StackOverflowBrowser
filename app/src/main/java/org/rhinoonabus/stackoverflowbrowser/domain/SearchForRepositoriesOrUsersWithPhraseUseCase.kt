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
            getItemsForNonEmptyPhrase(phrase)
            .subscribeOn(executionScheduler)
            .observeOn(redirectionScheduler)

    private fun getItemsForNonEmptyPhrase(phrase: String) =
            if (phrase.isEmpty()) Single.just(emptyList<SearchResultItem>())
            else searchForCodeRepositoriesOrReturnEmptyList(phrase).zipWith(searchForUsersOrReturnEmptyList(phrase), combineRepositoriesWithUsers())

    private fun searchForCodeRepositoriesOrReturnEmptyList(phrase: String) =
            sourceCodeManagementRepository.searchForCodeRepositories(phrase).onErrorReturnItem(emptyList())

    private fun searchForUsersOrReturnEmptyList(phrase: String) =
            sourceCodeManagementRepository.searchUsers(phrase).onErrorReturnItem(emptyList())

    private fun combineRepositoriesWithUsers() =
            BiFunction<List<CodeRepository>, List<CodeRepositoryUser>, List<SearchResultItem>>
            { listOfRepositories, listOfUsers ->
                (listOfRepositories as List<SearchResultItem>)
                        .plus(listOfUsers)
                        .sortedBy { it.id }
            }
}

package org.rhinoonabus.stackoverflowbrowser.domain

import io.reactivex.Scheduler
import io.reactivex.Single

open class SearchForRepositoriesWithPhraseUseCase(
        private val executionScheduler: Scheduler,
        private val redirectionScheduler: Scheduler,
        private val sourceCodeManagementRepository: SourceCodeManagementRepository
) {
    open fun searchFor(phrase: String): Single<List<CodeRepository>> = sourceCodeManagementRepository
            .searchForCodeRepositories(phrase)
            .subscribeOn(executionScheduler)
            .observeOn(redirectionScheduler)
}

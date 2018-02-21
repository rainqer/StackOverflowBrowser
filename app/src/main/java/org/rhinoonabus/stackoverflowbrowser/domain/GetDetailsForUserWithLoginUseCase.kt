package org.rhinoonabus.stackoverflowbrowser.domain

import io.reactivex.Scheduler
import io.reactivex.Single

open class GetDetailsForUserWithLoginUseCase(
        private val executionScheduler: Scheduler,
        private val redirectionScheduler: Scheduler,
        private val sourceCodeManagementRepository: SourceCodeManagementRepository
) {
    open fun getUserWithLogin(userLogin: String): Single<CodeRepositoryUserDetails> =
            sourceCodeManagementRepository.getUserDetails(userLogin)
                    .subscribeOn(executionScheduler)
                    .observeOn(redirectionScheduler)
}
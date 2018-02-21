package org.rhinoonabus.stackoverflowbrowser.domain

import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.functions.BiFunction

open class GetDetailsForUserWithLoginUseCase(
        private val executionScheduler: Scheduler,
        private val redirectionScheduler: Scheduler,
        private val sourceCodeManagementRepository: SourceCodeManagementRepository
) {
    open fun getUserWithLogin(userLogin: String): Single<CodeRepositoryUserDetails> =
            sourceCodeManagementRepository.getUserDetails(userLogin)
                    .zipWith(
                            sourceCodeManagementRepository.getUserNumberOfRepositoriesStarredByUser(userLogin),
                            combineRepositoriesWithUsers()
                    )
                    .subscribeOn(executionScheduler)
                    .observeOn(redirectionScheduler)

    private fun combineRepositoriesWithUsers() =
            BiFunction<CodeRepositoryUserDetails, Int, CodeRepositoryUserDetails>
            { userDetails, numberOfStarredRepositories ->
                userDetails.copy(numberOfStarredRepositories = numberOfStarredRepositories)
            }
}
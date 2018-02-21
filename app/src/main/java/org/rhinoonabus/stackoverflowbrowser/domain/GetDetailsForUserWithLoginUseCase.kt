package org.rhinoonabus.stackoverflowbrowser.domain

import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.functions.BiFunction
import org.rhinoonabus.stackoverflowbrowser.domain.CodeRepositoryUserDetails.Companion.DEFAULT_NUMBER_OF_STARRED_REPOSITORIES

open class GetDetailsForUserWithLoginUseCase(
        private val executionScheduler: Scheduler,
        private val redirectionScheduler: Scheduler,
        private val sourceCodeManagementRepository: SourceCodeManagementRepository
) {
    open fun getUserWithLogin(userLogin: String): Single<CodeRepositoryUserDetails> =
            sourceCodeManagementRepository.getUserDetails(userLogin)
                    .zipWith(getNumberOfStarredRepositoriesOrDefault(userLogin), combineRepositoriesWithUsers())
                    .subscribeOn(executionScheduler)
                    .observeOn(redirectionScheduler)

    private fun getNumberOfStarredRepositoriesOrDefault(userLogin: String) =
            sourceCodeManagementRepository
                    .getUserNumberOfRepositoriesStarredByUser(userLogin)
                    .onErrorReturnItem(DEFAULT_NUMBER_OF_STARRED_REPOSITORIES)

    private fun combineRepositoriesWithUsers() =
            BiFunction<CodeRepositoryUserDetails, Int, CodeRepositoryUserDetails>
            { userDetails, numberOfStarredRepositories ->
                userDetails.copy(numberOfStarredRepositories = numberOfStarredRepositories)
            }
}
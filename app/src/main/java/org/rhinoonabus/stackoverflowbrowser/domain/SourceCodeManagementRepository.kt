package org.rhinoonabus.stackoverflowbrowser.domain

import io.reactivex.Single

interface SourceCodeManagementRepository {

    fun searchForCodeRepositories(searchPhrase: String): Single<List<CodeRepository>>
    fun searchUsers(searchPhrase: String): Single<List<CodeRepositoryUser>>
    fun getUserDetails(userLogin: String): Single<CodeRepositoryUserDetails>
}
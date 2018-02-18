package org.rhinoonabus.stackoverflowbrowser.domain

import io.reactivex.Single

interface SourceCodeManagementRepository {

    fun searchForCodeRepositories(searchPhrase: String): Single<List<CodeRepository>>
}
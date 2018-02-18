package org.rhinoonabus.stackoverflowbrowser.domain

import org.rhinoonabus.stackoverflowbrowser.repository.GitHubRepositoryEntity

data class CodeRepository(
        val id: Long,
        val name: String,
        val description: String,
        val url: String
) {

    constructor(gitHubCodeRepositoryEntity: GitHubRepositoryEntity) : this(
            gitHubCodeRepositoryEntity.id ?: throw IllegalStateException("Every repository must have an id"),
            gitHubCodeRepositoryEntity.name ?: "",
            gitHubCodeRepositoryEntity.description ?: "",
            gitHubCodeRepositoryEntity.url ?: ""
    )
}

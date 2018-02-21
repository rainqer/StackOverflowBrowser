package org.rhinoonabus.stackoverflowbrowser.domain

import org.rhinoonabus.stackoverflowbrowser.R
import org.rhinoonabus.stackoverflowbrowser.repository.GitHubRepositoryEntity

data class CodeRepository(
        override val id: Long,
        override val name: String,
        override val url: String,
        val description: String,
        override val typeNameRes: Int = R.string.code_repository_type_name
) : SearchResultItem {

    constructor(gitHubCodeRepositoryEntity: GitHubRepositoryEntity) : this(
            gitHubCodeRepositoryEntity.id ?: throw IllegalStateException("Every repository must have an id"),
            gitHubCodeRepositoryEntity.name ?: "",
            gitHubCodeRepositoryEntity.url ?: "",
            gitHubCodeRepositoryEntity.description ?: ""
    )
}

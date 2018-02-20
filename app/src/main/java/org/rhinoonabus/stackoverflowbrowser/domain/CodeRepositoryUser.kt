package org.rhinoonabus.stackoverflowbrowser.domain

import org.rhinoonabus.stackoverflowbrowser.repository.GitHubUserEntity

data class CodeRepositoryUser(
        val id: Long,
        val name: String,
        val url: String
) {

    constructor(gitHubCodeUserEntity: GitHubUserEntity) : this(
            gitHubCodeUserEntity.id ?: throw IllegalStateException("Every user must have an id"),
            gitHubCodeUserEntity.name ?: "",
            gitHubCodeUserEntity.url ?: ""
    )
}

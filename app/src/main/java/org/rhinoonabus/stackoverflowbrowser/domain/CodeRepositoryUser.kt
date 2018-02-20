package org.rhinoonabus.stackoverflowbrowser.domain

import org.rhinoonabus.stackoverflowbrowser.R
import org.rhinoonabus.stackoverflowbrowser.repository.GitHubUserEntity

data class CodeRepositoryUser(
        override val id: Long,
        override val name: String,
        override val url: String,
        override val typeNameRes: Int = R.string.codeRepositoryUserTypeName

) : SearchResultItem {

    constructor(gitHubCodeUserEntity: GitHubUserEntity) : this(
            gitHubCodeUserEntity.id ?: throw IllegalStateException("Every user must have an id"),
            gitHubCodeUserEntity.name ?: "",
            gitHubCodeUserEntity.url ?: ""
    )
}

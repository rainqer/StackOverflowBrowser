package org.rhinoonabus.stackoverflowbrowser.domain

import org.rhinoonabus.stackoverflowbrowser.repository.GitHubUserDetailsResponseEntity

data class CodeRepositoryUserDetails(
        val id: Long,
        val login: String,
        val avatarUrl: String,
        val numberOfFollowers: Int
) {

    constructor(userDetailsEntity: GitHubUserDetailsResponseEntity) : this(
            userDetailsEntity.id ?: throw IllegalStateException("Every user must have an id"),
            userDetailsEntity.login ?: "",
            userDetailsEntity.avatarUrl ?: "",
            userDetailsEntity.followers ?: 0
    )
}

package org.rhinoonabus.stackoverflowbrowser.domain

import org.rhinoonabus.stackoverflowbrowser.repository.GitHubRepositoryUserEntityFactory

object CodeRepositoryUserFactory {

    val USER_A
        get() = CodeRepositoryUser(GitHubRepositoryUserEntityFactory.USER_ENTITY_A)

    val USER_B
        get() = CodeRepositoryUser(GitHubRepositoryUserEntityFactory.USER_ENTITY_B)
}
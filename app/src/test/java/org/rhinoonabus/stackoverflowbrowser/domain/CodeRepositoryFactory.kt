package org.rhinoonabus.stackoverflowbrowser.domain

import org.rhinoonabus.stackoverflowbrowser.repository.GitHubRepositoryEntityFactory

object CodeRepositoryFactory {

    val REPOSITORY_A
        get() = CodeRepository(GitHubRepositoryEntityFactory.REPOSITORY_ENTITY_A)
}
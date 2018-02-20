package org.rhinoonabus.stackoverflowbrowser.repository

object GitHubSearchForRepositoriesResponseEntityFactory {

    val SEARCH_FOR_REPO_RESPONSE_A = GitHubSearchForRepositoriesResponseEntity(
            1,
            listOf(GitHubRepositoryEntityFactory.REPOSITORY_ENTITY_A)
    )
}
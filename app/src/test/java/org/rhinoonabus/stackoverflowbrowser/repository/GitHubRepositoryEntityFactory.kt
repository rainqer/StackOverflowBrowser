package org.rhinoonabus.stackoverflowbrowser.repository

object GitHubRepositoryEntityFactory {

    const val A_ID = 11L
    const val A_NAME = "testNameA"
    const val A_DESCRIPTION = "testDescriptionA"
    const val A_URL = "testUrlA"

    val REPOSITORY_ENTITY_A
        get() = GitHubRepositoryEntity(A_ID, A_NAME, A_DESCRIPTION, A_URL)
}
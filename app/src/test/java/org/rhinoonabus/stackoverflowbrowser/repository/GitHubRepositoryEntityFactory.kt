package org.rhinoonabus.stackoverflowbrowser.repository

object GitHubRepositoryEntityFactory {

    const val A_ID = 11L
    const val A_NAME = "testNameA"
    const val A_DESCRIPTION = "testDescriptionA"
    const val A_URL = "testUrlA"

    const val B_ID = 12L
    const val B_NAME = "testNameB"
    const val B_DESCRIPTION = "testDescriptionB"
    const val B_URL = "testUrlB"

    val REPOSITORY_ENTITY_A
        get() = GitHubRepositoryEntity(A_ID, A_NAME, A_DESCRIPTION, A_URL)

    val REPOSITORY_ENTITY_B
        get() = GitHubRepositoryEntity(B_ID, B_NAME, B_DESCRIPTION, B_URL)
}
package org.rhinoonabus.stackoverflowbrowser.repository

object GitHubRepositoryUserEntityFactory {

    const val A_ID = 11L
    const val A_NAME = "testUserNameA"
    const val A_URL = "testUserUrlA"

    const val B_ID = 12L
    const val B_NAME = "testUserNameB"
    const val B_URL = "testUserUrlB"

    val USER_ENTITY_A
        get() = GitHubUserEntity(A_ID, A_NAME, A_URL)

    val USER_ENTITY_B
        get() = GitHubUserEntity(B_ID, B_NAME, B_URL)
}
package org.rhinoonabus.stackoverflowbrowser.repository

object GitHubUserDetailsResponseEntityFactory {

    const val A_ID = 21L
    const val A_LOGIN = "loginA"
    const val A_AVATAR_URL = "avatarUrlA"
    const val A_NUMBER_OF_FOLLOWERS = 13

    val A_RESPONSE = GitHubUserDetailsResponseEntity(A_ID, A_LOGIN, A_AVATAR_URL, A_NUMBER_OF_FOLLOWERS)
}
package org.rhinoonabus.stackoverflowbrowser.repository

import com.google.gson.annotations.SerializedName

data class GitHubUserDetailsResponseEntity(
        @SerializedName("id") val id: Long?,
        @SerializedName("login") val login: String?,
        @SerializedName("avatar_url") val avatarUrl: String?,
        @SerializedName("followers") val followers: Int?
)

package org.rhinoonabus.stackoverflowbrowser.repository

import com.google.gson.annotations.SerializedName

data class GitHubSearchForRepositoryUsersResponseEntity(
        @SerializedName("total_count") val totalCount: Int?,
        @SerializedName("items") val items: List<GitHubUserEntity>?
)

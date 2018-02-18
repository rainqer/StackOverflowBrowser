package org.rhinoonabus.stackoverflowbrowser.repository

import com.google.gson.annotations.SerializedName

data class GitHubSearchForRepositoriesResponseEntity(
        @SerializedName("total_count") val totalCount: Int?,
        @SerializedName("items") val items: List<GitHubRepositoryEntity>?
)

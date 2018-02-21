package org.rhinoonabus.stackoverflowbrowser.repository

import com.google.gson.annotations.SerializedName

data class GitHubUserEntity(
        @SerializedName("id") val id: Long?,
        @SerializedName("login") val userLogin: String?,
        @SerializedName("html_url") val url: String?
)

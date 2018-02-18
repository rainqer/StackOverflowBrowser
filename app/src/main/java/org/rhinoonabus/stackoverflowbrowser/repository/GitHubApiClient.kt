package org.rhinoonabus.stackoverflowbrowser.repository

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface GitHubApiClient {

    @Headers("Accept: application/vnd.github.v3+json", "Content-Type: application/json")
    @GET("search/repositories")
    fun searchForRepositories(
            @Query("q") queryPhrase: String,
            @Query("per_page") resultsOnPage: Int
    ): Single<List<GitHubRepositoryEntity>>
}
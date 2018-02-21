package org.rhinoonabus.stackoverflowbrowser.repository

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface GitHubApiClient {

    @Headers("Accept: application/vnd.github.v3+json", "Content-Type: application/json")
    @GET("search/repositories")
    fun searchForRepositories(
            @Query("q") queryPhrase: String,
            @Query("per_page") resultsOnPage: Int
    ): Single<GitHubSearchForRepositoriesResponseEntity>

    @Headers("Accept: application/vnd.github.v3+json", "Content-Type: application/json")
    @GET("search/users")
    fun searchForUsers(
            @Query("q") queryPhrase: String,
            @Query("per_page") resultsOnPage: Int
    ): Single<GitHubSearchForRepositoryUsersResponseEntity>

    @Headers("Accept: application/vnd.github.v3+json", "Content-Type: application/json")
    @GET("users/{login}")
    fun getUserDetails(@Path("login") userLogin: String): Single<GitHubUserDetailsResponseEntity>

    @Headers("Accept: application/vnd.github.v3+json", "Content-Type: application/json")
    @GET("users/{login}/starred")
    fun getUserStarredRepositories(@Path("login") userLogin: String): Single<List<GitHubRepositoryEntity>>
}
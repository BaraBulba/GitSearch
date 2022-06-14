package android.example.githubapplication.github.api

import android.example.githubapplication.github.model.DetailUserResponse
import android.example.githubapplication.github.model.Repos
import android.example.githubapplication.github.model.User
import android.example.githubapplication.github.model.UserResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface Api {
    @GET("search/users")
    @Headers("Authorization: token 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 ")
    fun getSearchUsers(
        @Query("q") query: String
    ): Call<UserResponse>

    @GET("users/{username}")
    @Headers("Authorization: token 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 ")
    fun getUserDetails(
        @Path("username") username: String
    ): Call<DetailUserResponse>


    @GET("users/{username}/followers")
    @Headers("Authorization: token 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 ")
    fun getFollowers(
        @Path("username") username: String
    ): Call<ArrayList<User>>

    @GET("users/{username}/following")
    @Headers("Authorization: token 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 ")
    fun getFollowing(
        @Path("username") username: String
    ): Call<ArrayList<User>>

    @GET("users/{username}/repos")
    @Headers("Authorization: token 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 ")
    fun getRepos(
        @Path("username") username: String
    ): Call<ArrayList<Repos>>
}
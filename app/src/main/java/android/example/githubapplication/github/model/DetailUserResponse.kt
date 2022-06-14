package android.example.githubapplication.github.model

import com.google.gson.annotations.SerializedName

data class DetailUserResponse(
    val login: String,
    val id: Int,
    @SerializedName("avatar_url")
    val avatarUrl: String,
    @SerializedName("followers_url")
    val followersUrl: String,
    @SerializedName("following_url")
    val followingUrl: String,
    @SerializedName("repos_url")
    val reposUrl: String,
    val followers: Int,
    val following: Int,
    val name: String,
    @SerializedName("public_repos")
    val publicRepos: Int,
)

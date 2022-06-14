package android.example.githubapplication.github.ui.favorite

import android.app.Application
import android.example.githubapplication.github.data.local.FavoriteUser
import android.example.githubapplication.github.data.local.FavoriteUserDao
import android.example.githubapplication.github.data.local.UserDatabase
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData

class FavoriteViewModel(application: Application): AndroidViewModel(application) {

    private var userDao: FavoriteUserDao?
    private var userDb: UserDatabase?

    init {
        userDb = UserDatabase.getDatabase(application)
        userDao = userDb?.favoriteUserDao()
    }

    fun getFavoriteUser(): LiveData<List<FavoriteUser>>?{
        return userDao?.getFavoriteUser()
    }

}
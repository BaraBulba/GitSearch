package android.example.githubapplication.github.ui.main.details

import android.example.githubapplication.github.api.RetrofitClient
import android.example.githubapplication.github.model.DetailUserResponse
import android.example.githubapplication.github.model.Repos
import android.example.githubapplication.github.model.User
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ReposViewModel: ViewModel() {

    val listRepos = MutableLiveData<ArrayList<Repos>>()

    fun setListRepos(username: String){
        RetrofitClient.apiInstance
            .getRepos(username)
            .enqueue(object : Callback<ArrayList<Repos>>{
                override fun onResponse(
                    call: Call<ArrayList<Repos>>,
                    response: Response<ArrayList<Repos>>
                ) {
                    if (response.isSuccessful){
                        listRepos.postValue(response.body())
                    }

                }

                override fun onFailure(call: Call<ArrayList<Repos>>, t: Throwable) {
                    Log.d("Failure", t.message.toString())
                }

            })
    }

    fun getListRepos(): LiveData<ArrayList<Repos>> {
        return listRepos
    }
}
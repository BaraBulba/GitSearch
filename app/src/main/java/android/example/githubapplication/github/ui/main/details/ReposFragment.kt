package android.example.githubapplication.github.ui.main.details

import android.content.Intent
import android.example.githubapplication.R
import android.example.githubapplication.databinding.FragmentFollowBinding
import android.example.githubapplication.databinding.FragmentReposBinding
import android.example.githubapplication.github.model.Repos
import android.example.githubapplication.github.model.User
import android.example.githubapplication.github.ui.main.ReposAdapter
import android.example.githubapplication.github.ui.main.UserAdapter
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager

class ReposFragment: Fragment(R.layout.fragment_repos) {

    private var _binding: FragmentReposBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: ReposViewModel
    private lateinit var adapter: ReposAdapter
    private lateinit var username: String

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val args  = arguments
        username = args?.getString(DetailUserActivity.EXTRA_USERNAME).toString()
        _binding = FragmentReposBinding.bind(view)

        adapter = ReposAdapter()
        adapter.notifyDataSetChanged()


        adapter.setOnItemClickCallback(object : ReposAdapter.OnItemClickCallback{
            override fun onItemClicked(data: Repos) {
                Intent(Intent.ACTION_VIEW, Uri.parse(data.htmlUrl)).also {
                    startActivity(it)
                }
            }

        })

        binding.apply {
            rvRepos.setHasFixedSize(true)
            rvRepos.layoutManager = LinearLayoutManager(activity)
            rvRepos.adapter = adapter
        }
        showLoading(true)
        viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())
            .get(ReposViewModel::class.java)
        viewModel.setListRepos(username)
        viewModel.getListRepos().observe(viewLifecycleOwner, {
            if (it != null){
                adapter.setList(it)
                showLoading(false)
            }
        })
    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun showLoading(state: Boolean){
        if (state){
            binding.pbLoading.visibility = View.VISIBLE
        }
        else{
            binding.pbLoading.visibility = View.GONE
        }
    }
}
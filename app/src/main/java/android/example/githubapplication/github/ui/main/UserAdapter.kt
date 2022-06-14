package android.example.githubapplication.github.ui.main

import android.example.githubapplication.R
import android.example.githubapplication.databinding.ItemUserBinding
import android.example.githubapplication.github.model.User
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class UserAdapter: RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    private val list = ArrayList<User>()
    private var onItemClickCallback: OnItemClickCallback? = null

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback){
        this.onItemClickCallback = onItemClickCallback
    }

    fun setList (users: ArrayList<User>){
        list.clear()
        list.addAll(users)
        notifyDataSetChanged()
    }

    inner class UserViewHolder(val binding: ItemUserBinding): RecyclerView.ViewHolder(binding.root){
                fun bind(user: User){
                    binding.root.setOnClickListener {
                        onItemClickCallback?.onItemClicked(user)
                    }
                    binding.apply {
                        Glide.with(itemView)
                            .load(user.avatarUrl)
                            .placeholder(R.drawable.default_ava)
                            .dontAnimate()
                            .centerCrop()
                            .into(ivAvatar)
                        tvUsername.text = user.login
                     }
                }
            }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view = ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserViewHolder(view)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int = list.size

    interface OnItemClickCallback{
        fun onItemClicked(data: User)
    }
}
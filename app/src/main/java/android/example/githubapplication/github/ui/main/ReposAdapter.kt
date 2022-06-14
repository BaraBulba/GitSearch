package android.example.githubapplication.github.ui.main

import android.example.githubapplication.databinding.ItemRepositoriesBinding
import android.example.githubapplication.github.model.Repos
import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.time.format.DateTimeFormatter
import java.util.*
import kotlin.collections.ArrayList


class ReposAdapter: RecyclerView.Adapter<ReposAdapter.ReposViewHolder>() {


    private val list = ArrayList<Repos>()
    private var onItemClickCallback: OnItemClickCallback? = null

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback){
        this.onItemClickCallback = onItemClickCallback
    }

    fun setList (repos: ArrayList<Repos>){
        list.clear()
        list.addAll(repos)
        notifyDataSetChanged()
    }

    inner class ReposViewHolder(val binding: ItemRepositoriesBinding): RecyclerView.ViewHolder(binding.root){
        @RequiresApi(Build.VERSION_CODES.O)
        fun bind(repos: Repos){
            binding.root.setOnClickListener {
                onItemClickCallback?.onItemClicked(repos)
            }
            binding.apply {
                tvRepoName.text = repos.name
                tvRepoLanguage.text = repos.language
                tvRepoUpdate.text = repos.updatedAt
                    .replace("Z", "\n")
                    .replace("T", "\n")
                    .removeRange(10..19)

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReposViewHolder {
        val view = ItemRepositoriesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ReposViewHolder(view)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: ReposViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int = list.size

    interface OnItemClickCallback{
        fun onItemClicked(data: Repos)
    }
}

//    companion object{
//        const val API_DATE_TIME_FORMAT = "yyyy-MM-ddTHH:mm:ss.SSSSSSSZ"
//        const val UI_DATE_FORMAT = "MM/dd/yyyy"
//        const val UI_TIME_FORMAT = "hh:mm:ss a"
//    }

//    fun getFormattedDate(apiFormatDateTime: String): String {
//        return try{
//            val parser = SimpleDateFormat(API_DATE_TIME_FORMAT, Locale.getDefault())
//            parser.timeZone = TimeZone.getTimeZone("UTC")
//            val formatter = SimpleDateFormat(UI_DATE_FORMAT, Locale.getDefault())
//            val date = parser.parse(apiFormatDateTime)!!
//            formatter.timeZone = TimeZone.getDefault()
//            formatter.format(date)
//        }catch (ex : Exception){
//            apiFormatDateTime
//        }
//    }
//
//    /**
//     * Function for getting time from api datetime string
//     * @return formatted time
//     */
//    fun getFormattedTime(apiFormatDateTime: String): String {
//        return try{
//            val parser = SimpleDateFormat(API_DATE_TIME_FORMAT, Locale.getDefault())
//            parser.timeZone = TimeZone.getTimeZone("UTC")
//            val formatter = SimpleDateFormat(UI_TIME_FORMAT, Locale.getDefault())
//            formatter.timeZone = TimeZone.getDefault()
//            formatter.format(parser.parse(apiFormatDateTime)!!)
//        }catch (ex : Exception){
//            apiFormatDateTime
//        }
//    }
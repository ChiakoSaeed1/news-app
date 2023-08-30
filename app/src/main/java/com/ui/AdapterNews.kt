package com.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.data.model.ResponseNews
import com.data.model.ResponseNews.Article
import com.example.newsapp.databinding.SampleRecBinding
import javax.inject.Inject

class AdapterNews @Inject constructor():RecyclerView.Adapter<AdapterNews.ViewHolder>() {

    private var binding:SampleRecBinding?=null
    private var news= emptyList<Article>()
    inner class ViewHolder():RecyclerView.ViewHolder(binding!!.root){
        fun bind(item:Article){
            binding?.apply {
                imgNews.load(item.urlToImage)
                txtNews.text=item.title
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding=SampleRecBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder()
    }

    override fun getItemCount(): Int {
        return news.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(news[position])
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    fun setData(data:List<Article>){
        val diffutils =Diffutils(news,data)
        val diffUtil =DiffUtil.calculateDiff(diffutils)
        news=data
        diffUtil.dispatchUpdatesTo(this)
    }

    class Diffutils(private val oldItem:List<Article>,private val newItem:List<Article>):DiffUtil.Callback(){
        override fun getOldListSize(): Int {
            return oldItem.size
        }

        override fun getNewListSize(): Int {
           return newItem.size
        }

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldItem[oldItemPosition]===newItem[newItemPosition]
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldItem[oldItemPosition]===newItem[newItemPosition]
        }

    }
}
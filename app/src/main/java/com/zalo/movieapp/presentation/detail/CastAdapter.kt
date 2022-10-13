package com.zalo.movieapp.presentation.detail


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.zalo.movieapp.databinding.CastItemViewBinding
import com.zalo.movieapp.domain.model.Cast
import com.zalo.movieapp.util.Constants

class CastAdapter : ListAdapter<Cast, RecyclerView.ViewHolder>(diffUtil) {
    class CastViewHolder(private val binding: CastItemViewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(cast: Cast) {
            binding.castNameTextView.text = cast.name
            val url = Constants.IMAGE_URL + cast.profile_path
            cast.profile_path?.let {
                Picasso.get().load(url)
                    .into(binding.castImageView)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CastViewHolder {
        val binding =
            CastItemViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CastViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = getItem(position)
        val itemHolder = holder as CastViewHolder
        itemHolder.bind(item)

    }

}

val diffUtil = object : DiffUtil.ItemCallback<Cast>() {
    override fun areItemsTheSame(oldItem: Cast, newItem: Cast): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Cast, newItem: Cast): Boolean {
        return oldItem == newItem
    }
}

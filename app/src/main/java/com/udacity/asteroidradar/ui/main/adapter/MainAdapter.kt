package com.udacity.asteroidradar.ui.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.udacity.asteroidradar.databinding.MainListItemBinding
import com.udacity.asteroidradar.domain.model.Asteroid
import com.udacity.asteroidradar.ui.main.util.DiffCallback

class MainAdapter :
    ListAdapter<Asteroid, MainAdapter.ViewHolder>(DiffCallback) {

    inner class ViewHolder(private val binding: MainListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Asteroid?) {
            item.let { asteroid ->
                binding.asteroid = asteroid
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            MainListItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}
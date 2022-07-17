package com.udacity.asteroidradar.ui.main.util

import androidx.recyclerview.widget.DiffUtil
import com.udacity.asteroidradar.domain.model.Asteroid

object DiffCallback : DiffUtil.ItemCallback<Asteroid>() {
    override fun areItemsTheSame(oldItem: Asteroid, newItem: Asteroid): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Asteroid, newItem: Asteroid): Boolean {
        return oldItem == newItem
    }
}
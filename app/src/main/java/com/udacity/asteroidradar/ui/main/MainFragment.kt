package com.udacity.asteroidradar.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import coil.load
import com.udacity.asteroidradar.R
import com.udacity.asteroidradar.databinding.FragmentMainBinding
import com.udacity.asteroidradar.ui.main.adapter.MainAdapter
import com.udacity.asteroidradar.ui.main.util.fragmentMainMenuProvider
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainFragment : Fragment() {

    private val binding: FragmentMainBinding by lazy {
        FragmentMainBinding.inflate(layoutInflater)
    }
    private val viewModel: MainViewModel by viewModels()
    private val adapter by lazy {
        MainAdapter { asteroid ->
            findNavController().navigate(MainFragmentDirections.actionShowDetail(asteroid))
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = binding.root


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = this

        binding.viewModel = viewModel

        requireActivity().addMenuProvider(fragmentMainMenuProvider)
        binding.tryAgainButton.setOnClickListener { viewModel.getAsteroids() }

        collectImageOfDay()
        collectAsteroids()
    }

    private fun collectImageOfDay() = lifecycleScope.launch {
        viewModel.pictureOfDay.collectLatest {
            binding.activityMainImageOfTheDay.load(it.url) {
                crossfade(CROSSFADE_DURATION)
                placeholder(R.drawable.placeholder_picture_of_day)
                error(R.drawable.placeholder_picture_of_day)
            }
        }
    }

    private fun collectAsteroids() = lifecycleScope.launch {
        viewModel.mainState.collectLatest { state ->
            when {
                state.loading -> {
                    setVisibility(loading = true)
                }
                state.failed -> {
                    setVisibility(error = true)
                }
                state.asteroids.isNotEmpty() -> {
                    setVisibility()
                    adapter.submitList(state.asteroids)
                    binding.asteroidRecycler.adapter = adapter
                }
            }
        }
    }

    private fun setVisibility(loading: Boolean = false, error: Boolean = false) {
        binding.statusLoadingWheel.visibility = if (loading) View.VISIBLE else View.GONE
        binding.someErrorTv.visibility = if (error) View.VISIBLE else View.GONE
        binding.tryAgainButton.visibility = if (error) View.VISIBLE else View.GONE
    }

    private companion object {
        const val CROSSFADE_DURATION = 400
    }
}

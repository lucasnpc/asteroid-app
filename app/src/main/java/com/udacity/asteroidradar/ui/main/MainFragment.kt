package com.udacity.asteroidradar.ui.main

import android.os.Bundle
import android.view.*
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import coil.load
import com.udacity.asteroidradar.R
import com.udacity.asteroidradar.databinding.FragmentMainBinding
import com.udacity.asteroidradar.ui.main.adapter.MainAdapter
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

    private val mainMenuListener = object :
        MenuProvider {

        override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
            menuInflater.inflate(R.menu.main_overflow_menu, menu)
        }

        override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
            when (menuItem.itemId) {
                R.id.show_all_menu -> {
                    viewModel.getAsteroids()
                }
                R.id.show_rent_menu -> {
                    viewModel.getTodayAsteroids()
                }
                R.id.show_buy_menu -> {
                    viewModel.getSavedAsteroids()
                }
            }
            return true
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

        requireActivity().addMenuProvider(mainMenuListener, viewLifecycleOwner)
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
                    adapter.submitList(listOf())
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

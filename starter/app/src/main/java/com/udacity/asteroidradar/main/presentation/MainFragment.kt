package com.udacity.asteroidradar.main.presentation

import android.os.Bundle
import android.view.*
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.udacity.asteroidradar.R
import com.udacity.asteroidradar.common.domain.model.Asteroid
import com.udacity.asteroidradar.common.presentation.model.Event
import com.udacity.asteroidradar.common.utils.setImage
import com.udacity.asteroidradar.databinding.FragmentMainBinding
import com.udacity.asteroidradar.main.presentation.adapter.AsteroidAdapter
import com.udacity.asteroidradar.main.presentation.adapter.AsteroidClickListener
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : Fragment() {

    private val viewModel: MainViewModel by viewModels()

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMainBinding.inflate(inflater)
        binding.lifecycleOwner = this
        setHasOptionsMenu(true)
        setupUI()

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main_overflow_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return true
    }


    private fun setupUI() {
        val adapter = createAdapter()
        setupRecyclerView(adapter)
        observeViewStateUpdates(adapter)
    }

    private fun observeViewStateUpdates(adapter: AsteroidAdapter) {
        viewModel.state.observe(viewLifecycleOwner) {
            updateScreenState(it, adapter)

        }
    }

    private fun updateScreenState(state: MainViewState, adapter: AsteroidAdapter) {

        with(binding) {

            statusLoadingWheel.isVisible = state.loading
            activityMainImageOfTheDay.setImage(state.imageOfDayUrl)
        }
        adapter.submitList(state.asteroids)
        handleFailures(state.failure)
    }


    private fun handleFailures(failure: Event<Throwable>?) {
        val unhandledFailure = failure?.getContentIfNotHandled() ?: return

        val fallbackMessage = getString(R.string.an_error_occurred)

        val snackbarMessage = if (unhandledFailure.message.isNullOrEmpty()) {
            fallbackMessage
        } else {
            unhandledFailure.message!!
        }
        if (snackbarMessage.isNotEmpty()) {
            Snackbar.make(requireView(), snackbarMessage, Snackbar.LENGTH_SHORT).show()
        }
    }

    private fun setupRecyclerView(asteroidAdapter : AsteroidAdapter) {
        binding.asteroidRecycler.apply {
            adapter = asteroidAdapter
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
        }
    }

    private fun createAdapter(): AsteroidAdapter {
        return AsteroidAdapter().apply {
          asteroidClickListener = object : AsteroidClickListener {
              override fun onClick(asteroid: Asteroid) {
                  val action = MainFragmentDirections.actionShowDetail(asteroid)
                  findNavController().navigate(action)

              }
          }
        }
    }


}

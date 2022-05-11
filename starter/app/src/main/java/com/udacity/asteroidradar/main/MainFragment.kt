package com.udacity.asteroidradar.main

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import com.udacity.asteroidradar.Asteroid
import com.udacity.asteroidradar.R
import com.udacity.asteroidradar.databinding.FragmentMainBinding

class MainFragment : Fragment() {

    private val viewModel: MainViewModel by lazy {
        ViewModelProvider(this).get(MainViewModel::class.java)
    }
    lateinit var binding: FragmentMainBinding
    lateinit var adapter: MainAsteroidAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainBinding.inflate(inflater)
        with(binding) {
            lifecycleOwner = this@MainFragment
            viewModel = this@MainFragment.viewModel
        }

        setupRecyclerViewAdapter()
        setupObservers()
        setHasOptionsMenu(true)

        return binding.root
    }

    private fun setupRecyclerViewAdapter() {
        adapter = MainAsteroidAdapter(MainAsteroidAdapter.AsteroidListener {
            findNavController().navigate(MainFragmentDirections.actionShowDetail(it))
        })
        binding.asteroidRecycler.adapter = adapter
    }


    private fun setupObservers() {
        viewModel.asteroids.observe(viewLifecycleOwner) { asteroids ->
            if (asteroids != null) {
                adapter.submitList(asteroids)
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main_overflow_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.show_all_menu -> viewModel.onViewWeekAsteroidsClicked()
            R.id.show_rent_menu -> viewModel.onTodayAsteroidsClicked()
            R.id.show_buy_menu -> viewModel.onSavedAsteroidsClicked()
        }
        return true
    }
}

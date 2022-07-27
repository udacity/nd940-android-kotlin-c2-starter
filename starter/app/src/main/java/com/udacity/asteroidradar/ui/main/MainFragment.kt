package com.udacity.asteroidradar.ui.main

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.udacity.asteroidradar.R
import com.udacity.asteroidradar.databinding.FragmentMainBinding
import com.udacity.asteroidradar.ui.main.adapter.AsteroidClickListener
import com.udacity.asteroidradar.ui.main.adapter.AsteroidsRecyclerViewAdapter


class MainFragment : Fragment() {

    private val binding by lazy { FragmentMainBinding.inflate(layoutInflater) }
    private val viewModel by viewModels<MainViewModel>()

    private val adapter by lazy {
        val adapterClickListener by lazy { AsteroidClickListener { asteroid ->
                findNavController().navigate(MainFragmentDirections.actionShowDetail(asteroid)) } }
        AsteroidsRecyclerViewAdapter(adapterClickListener)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        setHasOptionsMenu(true)
        with(binding) {
            lifecycleOwner = this@MainFragment
            bViewModel = viewModel
            bAdapter = adapter

            viewModel.asteroids.observe(viewLifecycleOwner) {
                Log.d(this::class.simpleName, "onCreateView: $it")
                adapter.submitList(it)
            }
        }

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main_overflow_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return true
    }
}

package com.udacity.asteroidradar.ui.main

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.udacity.asteroidradar.R
import com.udacity.asteroidradar.databinding.FragmentMainBinding
import com.udacity.asteroidradar.db.AsteroidDatabase
import com.udacity.asteroidradar.repository.AsteroidRepository


class MainFragment : Fragment() {


    private val binding by lazy { FragmentMainBinding.inflate(layoutInflater) }

    private val viewModel by viewModels<MainViewModel>{
        val database = AsteroidDatabase.getDatabase(requireContext().applicationContext)
        val repo = AsteroidRepository(database)
        MainViewModelFactory(repo)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        setHasOptionsMenu(true)
        with(binding) {
            lifecycleOwner = this@MainFragment
            bViewModel = viewModel

            viewModel.imageOfTheDay.observe(viewLifecycleOwner) {
                Log.d(this::class.simpleName, "onCreateView: $it")
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

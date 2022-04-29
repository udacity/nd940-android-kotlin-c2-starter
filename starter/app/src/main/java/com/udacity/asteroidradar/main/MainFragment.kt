package com.udacity.asteroidradar.main

import android.os.Bundle
import android.view.*
import androidx.core.net.toUri
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import com.udacity.asteroidradar.R
import com.udacity.asteroidradar.databinding.FragmentMainBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions


class MainFragment : Fragment() {

    private val viewModel: MainViewModel by lazy {
        ViewModelProvider(this).get(MainViewModel::class.java)
    }

    lateinit var binding : FragmentMainBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_main, container, false)

        binding.lifecycleOwner = this

        binding.viewModel = viewModel

        setHasOptionsMenu(true)

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main_overflow_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return true
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Call the getImageOfTheDay function defined in the viewModel
        viewModel.getImageOfTheDay()
        // Call the getAsteroidsList function defined in the viewModel
        // todo        viewModel.getAsteroidsList()

        // set an observer to see if the value of asteroidImage changes
        viewModel._asteroidImage.observe(viewLifecycleOwner){
            val imgUri = it.url.toUri().buildUpon()?.scheme("https")?.build()
            Glide.with(requireContext())
                .load(imgUri)
                .apply(
                    RequestOptions()
                        .placeholder(R.drawable.placeholder_picture_of_day)
                        .error(R.drawable.ic_help_circle)
                )
                .into(binding.activityMainImageOfTheDay)
        }

    }
}

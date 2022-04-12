package com.udacity.asteroidradar.main

import android.os.Bundle
import android.view.*
import androidx.core.net.toUri
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.udacity.asteroidradar.PictureOfDay
import com.udacity.asteroidradar.R
import com.udacity.asteroidradar.databinding.FragmentMainBinding

class MainFragment : Fragment() {

    private val viewModel: MainViewModel by lazy {
        ViewModelProvider(this).get(MainViewModel::class.java)
    }
    lateinit var binding : FragmentMainBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_main, container, false)
        // Allows Data Binding to Observe LiveData with the lifecycle of this Fragment
        binding.lifecycleOwner = this

        // Giving the binding access to the MainViewModel
        binding.viewModel = viewModel

        // Sets the adapter of the FragmentMain RecyclerView with clickHandler lambda that
        // tells the viewModel when our asteroid is clicked
        binding.asteroidRecycler.adapter = AsteroidGridAdapter()

        viewModel.navigateToSelectedAsteroid.observe(viewLifecycleOwner, Observer {
            if ( null != it) {
                this.findNavController().navigate(MainFragmentDirections.actionShowDetail(it))
                viewModel.displayAsteroidDetailsComplete()
            }
        })

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
        viewModel.getImageOfTheDay()
        viewModel.getAsteroidsList()
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

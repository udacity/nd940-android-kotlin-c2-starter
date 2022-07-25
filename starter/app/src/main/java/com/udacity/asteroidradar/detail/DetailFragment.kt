package com.udacity.asteroidradar.detail


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.udacity.asteroidradar.R
import com.udacity.asteroidradar.databinding.FragmentDetailBinding

class DetailFragment : Fragment() {

    private val binding by lazy { FragmentDetailBinding.inflate(layoutInflater) }
    private val args by navArgs<DetailFragmentArgs>()
    private val asteroid by lazy { args.selectedAsteroid }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        with(binding) {
            lifecycleOwner = this@DetailFragment
            bAsteroid = asteroid

            helpButton.setOnClickListener {
                displayAstronomicalUnitExplanationDialog()
            }
        }


        return binding.root
    }

    private fun displayAstronomicalUnitExplanationDialog() {
        val builder = AlertDialog.Builder(requireActivity())
            .setMessage(getString(R.string.astronomica_unit_explanation))
            .setPositiveButton(android.R.string.ok, null)
        builder.create().show()
    }
}

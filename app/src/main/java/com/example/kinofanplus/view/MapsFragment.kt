package com.example.kinofanplus.view

import android.app.AlertDialog
import android.location.Address
import android.location.Geocoder
import androidx.fragment.app.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.kinofanplus.R
import com.example.kinofanplus.databinding.FragmentMapsBinding
import com.example.kinofanplus.databinding.FragmentMovieDetailBinding
import com.example.kinofanplus.model.movie_list_gson.MovieDTO
import com.example.kinofanplus.viewmodel.AppStateGetMovieList

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.IOException

class MapsFragment : Fragment() {

    private var _binding: FragmentMapsBinding? = null
    private val binding get() = _binding!!
    private lateinit var map: GoogleMap

    private val callback = OnMapReadyCallback { googleMap ->
        map = googleMap
        val sydney = LatLng(52.52000659999999, 13.404953999999975)
        map.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))
        map.moveCamera(CameraUpdateFactory.newLatLng(sydney))
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMapsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(callback)
        initSearchByAddress()

    }

    private fun initSearchByAddress() {
        binding.buttonSearch.setOnClickListener {
            val geoCoder = Geocoder(context)
            Thread {
                try {
                    val addresses = geoCoder.getFromLocationName(
                        binding.searchAddress.text.toString(),
                        1
                    )
                        goToAddress(addresses)
                } catch (e: IOException) {
                    e.printStackTrace()
                }
            }.start()
        }
    }

    private fun goToAddress(address: List<Address>) {
        val location = LatLng(
            address[0].latitude,
            address[0].longitude
        )
        binding.buttonSearch.post {
            map.addMarker(
                MarkerOptions().position(location).title("${binding.searchAddress.toString()}")
            )
            map.moveCamera(CameraUpdateFactory.newLatLngZoom(location, 15f))
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
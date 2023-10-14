package com.ggr3ml1n.moretech50

import android.Manifest
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.ggr3ml1n.moretech50.databinding.FragmentMapBinding
import com.google.android.gms.location.LocationServices
import com.yandex.mapkit.Animation
import com.yandex.mapkit.MapKitFactory
import com.yandex.mapkit.geometry.Point
import com.yandex.mapkit.map.CameraPosition
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class MapFragment : Fragment() {

    private var _binding: FragmentMapBinding? = null
    private val binding get() = _binding!!

    //private val viewModel: MapFragmentViewModel by viewModels()
    private val serviceScope = CoroutineScope(SupervisorJob() + Dispatchers.IO)

    private lateinit var locationClient: LocationClient

    //временные значения, нужно понять что тут поставить
//    private val latitudeMap = arguments?.latitude ?: 59.0
//    private val longitudeMap = arguments?.longitude ?: 30.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MapKitFactory.setApiKey(BuildConfig.MAPKIT_API_KEY)
        locationClient = DefaultLocationClient(
            activity?.applicationContext!!,
            LocationServices.getFusedLocationProviderClient(activity?.applicationContext!!)
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMapBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        ActivityCompat.requestPermissions(
            requireActivity(),
            arrayOf(
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION
            ),
            0
        )

        val zoomMap = arguments?.zoom ?: 8.0f

        var lat = 0.0
        var long = 0.0

        locationClient
            .getLocationsUpdate(10000L)
            .catch { e ->
                e.printStackTrace()
            }
            .onEach { location ->
                lat = location.latitude
                long = location.longitude
                Log.d("Location", "Location: ($lat, $long)")
            }
            .launchIn(serviceScope)

        with(binding) {

            mapVTB.map.move( //пермещение карты
                CameraPosition(
                    Point(lat, long), zoomMap, 0.0f, 0.0f
                ), Animation(
                    Animation.Type.SMOOTH, 5f
                ), null
            )

            filter.setOnClickListener {//фильтр по услугам
                //TODO
            }

            whereAmI.setOnClickListener {//показывает, где пользователь
                Log.d("Service", "Service started")
                Intent(requireActivity().applicationContext, LocationService::class.java).apply {
                    action = LocationService.ACTION_START
                    activity?.startService(this@apply)
                }
            }
        }
    }

    override fun onStart() {
        super.onStart()
        MapKitFactory.getInstance().onStart()
        binding.mapVTB.onStart()
    }

    override fun onStop() {
        binding.mapVTB.onStop()
        MapKitFactory.getInstance().onStop()
        super.onStop()
    }


    companion object {
        @JvmStatic
        fun newInstance() = MapFragment()

        var Bundle.latitude: Double
            set(value) = putDouble("PointLat", value)
            get() = getDouble("PointLat")
        var Bundle.longitude: Double
            set(value) = putDouble("PointLong", value)
            get() = getDouble("PointLong")
        var Bundle.idPoint: Int
            set(value) = putInt("PointId", value)
            get() = getInt("PointId")
        var Bundle.zoom: Float
            set(value) = putFloat("PointZoom", value)
            get() = getFloat("PointZoom")
    }
}
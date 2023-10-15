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
import com.ggr3ml1n.moretech50.databinding.FragmentMapBinding
import com.yandex.mapkit.Animation
import com.yandex.mapkit.MapKitFactory
import com.yandex.mapkit.geometry.Point
import com.yandex.mapkit.layers.ObjectEvent
import com.yandex.mapkit.map.CameraPosition
import com.yandex.mapkit.user_location.UserLocationLayer
import com.yandex.mapkit.user_location.UserLocationObjectListener
import com.yandex.mapkit.user_location.UserLocationView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob

class MapFragment : Fragment(), UserLocationObjectListener {

    private var _binding: FragmentMapBinding? = null
    private val binding get() = _binding!!

    //private val viewModel: MapFragmentViewModel by viewModels()
    private val serviceScope = CoroutineScope(SupervisorJob() + Dispatchers.IO)
    private val defaultLocation = Point(55.7522, 37.6156)

    private lateinit var userLocationLayer: UserLocationLayer
    private var userLocation = defaultLocation

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        MapKitFactory.setApiKey(BuildConfig.MAPKIT_API_KEY)
        MapKitFactory.initialize(context)
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

        userLocationLayer = MapKitFactory.getInstance().createUserLocationLayer(binding.mapVTB.mapWindow)
        userLocationLayer.isVisible = true
        userLocationLayer.isHeadingEnabled = true
        userLocationLayer.setObjectListener(this@MapFragment)

        updateCamera()

        binding.whereAmI.setOnClickListener {//показывает, где пользователь
            Log.d("Service", "Service started")
            Intent(requireActivity().applicationContext, LocationService::class.java).apply {
                updateCamera()
            }
        }

        binding.filter.setOnClickListener {//фильтр по услугам
            //TODO
        }
    }

    private fun updateCamera() {
        if (userLocationLayer.cameraPosition() != null) {
            userLocation = userLocationLayer.cameraPosition()!!.target
            binding.mapVTB.map.move(
                CameraPosition(userLocation, 14f, 0f, 0f), Animation(Animation.Type.SMOOTH, 1f), null
            )
        } else {
            binding.mapVTB.map.move(CameraPosition(defaultLocation, 14f, 0f, 0f))
        }
    }

    override fun onObjectAdded(p0: UserLocationView) {
        updateCamera()
    }

    override fun onObjectRemoved(p0: UserLocationView) { }

    override fun onObjectUpdated(p0: UserLocationView, p1: ObjectEvent) {
        updateCamera()
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
package com.ggr3ml1n.moretech50

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.yandex.mapkit.Animation
import com.ggr3ml1n.moretech50.databinding.FragmentMapBinding
import com.yandex.mapkit.geometry.Point
import com.yandex.mapkit.map.CameraPosition

class MapFragment : Fragment() {

    private var _binding: FragmentMapBinding? = null
                                                    //временные значения, нужно понять что тут поставить
    private val latitudeMap = arguments?.latitude ?: 59.0
    private val longitudeMap = arguments?.longitude ?: 30.0
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMapBinding.inflate(inflater, container, false)
        val zoomMap = arguments?.zoom?:8.0f

        with(binding) {
            mapVTB.map.move( //пермещение карты
                CameraPosition(
                    Point(latitudeMap, longitudeMap), zoomMap, 0.0f, 0.0f
                ), Animation(
                    Animation.Type.SMOOTH, 5f
                ), null
            )

            filter.setOnClickListener {
                //TODO
            }

            whereAmI.setOnClickListener {
                //TODO
            }
        }

        return binding.root
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
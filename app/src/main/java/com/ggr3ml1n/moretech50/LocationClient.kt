package com.ggr3ml1n.moretech50

import android.location.Location
import kotlinx.coroutines.flow.Flow

interface LocationClient {
    fun getLocationsUpdate(intervals: Long): Flow<Location>

    class LocationException(message: String): Exception()
}
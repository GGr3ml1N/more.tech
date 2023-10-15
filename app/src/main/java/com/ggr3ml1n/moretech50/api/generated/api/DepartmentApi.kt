package com.ggr3ml1n.moretech50.api.generated.api

import com.ggr3ml1n.moretech50.api.infrastructure.CollectionFormats.*
import retrofit2.http.*
import retrofit2.Response
import okhttp3.RequestBody
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

import com.ggr3ml1n.moretech50.api.generated.model.FilterDto
import com.ggr3ml1n.moretech50.api.generated.model.GetDepartmentList200Response
import com.ggr3ml1n.moretech50.api.generated.model.GetNearestDepartments200Response
import com.ggr3ml1n.moretech50.api.generated.model.UserDto

interface DepartmentApi {

    /**
    * enum for parameter sortDir
    */
    enum class SortDir_getDepartmentList(val value: kotlin.String) {
        @SerialName(value = "ASC") aSC("ASC"),
        @SerialName(value = "DESC") dESC("DESC")
    }

    /**
     * 
     * List all Departments
     * Responses:
     *  - 200: List of OrderDto with pagination info
     *
     * @param filter Filter field (optional)
     * @param pageNumber Page number (optional, default to 0)
     * @param pageSize Page size (optional, default to 10)
     * @param sortBy Sorting field (optional, default to "id")
     * @param sortDir Sorting direction (optional, default to ASC)
     * @return [GetDepartmentList200Response]
     */
    @GET("departments")
    suspend fun getDepartmentList(@Query("filter") filter: FilterDto? = null, @Query("pageNumber") pageNumber: kotlin.Int? = 0, @Query("pageSize") pageSize: kotlin.Int? = 10, @Query("sortBy") sortBy: kotlin.String? = "id", @Query("sortDir") sortDir: SortDir_getDepartmentList? = SortDir_getDepartmentList.aSC): Response<GetDepartmentList200Response>

    /**
     * 
     * Get the nearest departments
     * Responses:
     *  - 200: List of OrderDto with pagination info
     *
     * @param userDto  (optional)
     * @return [GetNearestDepartments200Response]
     */
    @POST("departments/nearest")
    suspend fun getNearestDepartments(@Body userDto: UserDto? = null): Response<GetNearestDepartments200Response>

}

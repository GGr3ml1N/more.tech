/**
 *
 * Please note:
 * This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * Do not edit this file manually.
 *
 */

@file:Suppress(
    "ArrayInDataClass",
    "EnumEntryName",
    "RemoveRedundantQualifierName",
    "UnusedImport"
)

package com.ggr3ml1n.moretech50.api.generated.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * 
 *
 * @param page Current page
 * @param totalPages Total page
 * @param pageSize Quantity of records per page
 * @param filter 
 * @param totalRows total numbers of rows for the request
 * @param sortField sorting field
 * @param sortDir sorting direction
 */
@Serializable

data class GetATMList200Response (

    /* Current page */
    @SerialName(value = "page")
    val page: kotlin.Int,

    /* Total page */
    @SerialName(value = "totalPages")
    val totalPages: kotlin.Int,

    /* Quantity of records per page */
    @SerialName(value = "pageSize")
    val pageSize: kotlin.Int,

    @SerialName(value = "filter")
    val filter: FilterDto? = null,

    /* total numbers of rows for the request */
    @SerialName(value = "totalRows")
    val totalRows: kotlin.Long? = null,

    /* sorting field */
    @SerialName(value = "sortField")
    val sortField: kotlin.String? = null,

    /* sorting direction */
    @SerialName(value = "sortDir")
    val sortDir: GetATMList200Response.SortDir? = null

) {

    /**
     * sorting direction
     *
     * Values: aSC,dESC
     */
    @Serializable
    enum class SortDir(val value: kotlin.String) {
        @SerialName(value = "ASC") aSC("ASC"),
        @SerialName(value = "DESC") dESC("DESC");
    }
}


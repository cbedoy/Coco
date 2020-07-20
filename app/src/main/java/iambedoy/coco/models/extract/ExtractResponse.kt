package iambedoy.coco.models.extract

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Coco
 *
 * Created by bedoy on 19/07/20.
 */
@JsonClass(generateAdapter = true)
data class ExtractResponse(
    val title: String = "",
    val description: String = "",
    val url: String = "",
    val type: String = "",
    @Json(name = "provider_name") val providerName: String = "",
    @Json(name = "favicon_url") val faviconUrl: String = "",
    @Json(name = "original_url") val originalUrl: String = "",
    @Json(name = "images") val images: List<ExtractImageResponse> = emptyList(),
    val media: ExtractMediaResponse = ExtractMediaResponse()
)
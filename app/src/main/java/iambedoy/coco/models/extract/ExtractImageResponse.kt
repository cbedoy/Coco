package iambedoy.coco.models.extract

import com.squareup.moshi.JsonClass

/**
 * Coco
 *
 * Created by bedoy on 19/07/20.
 */
@JsonClass(generateAdapter = true)
data class ExtractImageResponse(
    val url: String = "",
    val width: Int = 0,
    val height: Int = 0
)
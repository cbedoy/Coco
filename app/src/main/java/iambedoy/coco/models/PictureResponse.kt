package iambedoy.coco.models

import com.squareup.moshi.JsonClass

/**
 * Coco
 *
 * Created by bedoy on 07/07/20.
 */
@JsonClass(generateAdapter = true)
data class PictureResponse(
    val large: String = "",
    val medium: String = "",
    val thumbnail: String = ""
)
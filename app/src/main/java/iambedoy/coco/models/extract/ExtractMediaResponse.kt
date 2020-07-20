package iambedoy.coco.models.extract

import com.squareup.moshi.JsonClass


/**
 * Coco
 *
 * Created by bedoy on 19/07/20.
 */
@JsonClass(generateAdapter = true)
data class ExtractMediaResponse(
    val type: String = "",
    val html: String = "",
    val height: Int = 0,
    val width: Int = 0
)
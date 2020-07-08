package iambedoy.coco.models

import com.squareup.moshi.JsonClass

/**
 * Coco
 *
 * Created by bedoy on 07/07/20.
 */
@JsonClass(generateAdapter = true)
data class NameResponse(
    val title: String = "",
    val first: String = "",
    val last: String = ""
)
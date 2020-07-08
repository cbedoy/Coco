package iambedoy.coco.models

import com.squareup.moshi.JsonClass

/**
 * Coco
 *
 * Created by bedoy on 07/07/20.
 */
@JsonClass(generateAdapter = true)
data class LoginResponse(
    val uuid: String = "",
    val username: String = ""
)
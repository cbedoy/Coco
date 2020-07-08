package iambedoy.coco.models

import com.squareup.moshi.JsonClass

/**
 * Coco
 *
 * Created by bedoy on 07/07/20.
 */
@JsonClass(generateAdapter = true)
data class RandomUserResultResponse(
    val gender: String = "",
    val name: NameResponse = NameResponse(),
    val email: String = "",
    val login: LoginResponse = LoginResponse(),
    val picture: PictureResponse = PictureResponse()
)


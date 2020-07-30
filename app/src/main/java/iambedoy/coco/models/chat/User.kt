package iambedoy.coco.models.chat

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * Coco
 *
 * Created by bedoy on 19/07/20.
 */
@Parcelize
data class User(
    val uuid: String = "",
    val nickname: String = "",
    val avatar: String = ""
) : Parcelable
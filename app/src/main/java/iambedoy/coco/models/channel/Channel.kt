package iambedoy.coco.models.channel

import android.os.Parcelable
import iambedoy.coco.models.chat.Message
import kotlinx.android.parcel.Parcelize

/**
 * Coco
 *
 * Created by bedoy on 20/07/20.
 */
@Parcelize
data class Channel(
    val uuid: String = "",
    val name: String = "",
    val avatar: String = "",
    val lastMessage: Message.ChatMessage = Message.ChatMessage(text = "No messages")
) : Parcelable
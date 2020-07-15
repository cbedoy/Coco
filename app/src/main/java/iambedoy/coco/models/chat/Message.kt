package iambedoy.coco.models.chat

/**
 * Coco
 *
 * Created by bedoy on 08/07/20.
 */
sealed class Message{

    data class ChatMessage(
        val text: String = "",
        val uuid: String = "",
        val metadata: MetadataMessage = MetadataMessage()
    ): Message()

    data class ErrorMessage(
        val error: String = ""
    ) : Message()
}


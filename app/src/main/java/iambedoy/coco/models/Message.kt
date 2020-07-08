package iambedoy.coco.models

/**
 * Coco
 *
 * Created by bedoy on 08/07/20.
 */
data class Message(
    val text: String = "",
    val uuid: String = "",
    val metadata: MetadataMessage = MetadataMessage()
)
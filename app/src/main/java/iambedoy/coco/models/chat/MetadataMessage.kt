package iambedoy.coco.models.chat

/**
 * Coco
 *
 * Created by bedoy on 08/07/20.
 */
data class MetadataMessage(
    val thumbnail: String = "",
    val title: String = "",
    val description: String = "",
    val service: String = "",
    val playable: Boolean = false,
    val playableContent: String = "",
    val type: String = "",
    val favicon: String = "",
    val originalUrl: String = "",
    val url: String = ""
)
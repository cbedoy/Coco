package iambedoy.coco.chat

import com.haroldadmin.cnradapter.NetworkResponse
import iambedoy.coco.models.chat.MetadataMessage
import iambedoy.coco.services.ExtractService

/**
 * Coco
 *
 * Created by bedoy on 08/07/20.
 */
class ChatRepository(
    private val noEmbedService: ExtractService
){
    suspend fun getLinkMetadata(url: String) : MetadataMessage {
        return when(val call = noEmbedService.getMetadataLink(url, "e33332a0146045eea14b082e9f39e90e")){
            is NetworkResponse.Success -> {
                call.body.let {
                    MetadataMessage(
                        thumbnail = it.images.firstOrNull()?.url?:"",
                        title = it.title,
                        description = it.description,
                        type = it.type,
                        favicon = it.faviconUrl,
                        service = it.providerName,
                        url = it.url,
                        originalUrl = it.originalUrl,
                        playable = it.media.html.isNotEmpty(),
                        playableContent = it.media.html
                    )
                }
            }
            else -> {
                MetadataMessage()
            }
        }
    }
}
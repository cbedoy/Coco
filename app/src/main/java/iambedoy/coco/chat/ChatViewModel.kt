package iambedoy.coco.chat

/**
 * Coco
 *
 * Created by bedoy on 08/07/20.
 */
class ChatViewModel (
    private val repository: ChatRepository
){
    val messages = repository.randomMessages
}
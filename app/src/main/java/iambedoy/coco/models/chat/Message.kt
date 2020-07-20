package iambedoy.coco.models.chat

/**
 * Coco
 *
 * Created by bedoy on 08/07/20.
 */
sealed class Message{

    data class ChatMessage(
        val user: User = User("test-user", "Test user", "https://avatars0.githubusercontent.com/u/5570799?s=460&u=99501c68964dae1777f459bd9fed40137d8a6a64&v=4"),
        val text: String = "",
        val uuid: String = "",
        val metadata: MetadataMessage = MetadataMessage()
    ): Message()

    data class ErrorMessage(
        val error: String = ""
    ) : Message()
}


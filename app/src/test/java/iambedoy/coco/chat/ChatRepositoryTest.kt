package iambedoy.coco.chat

import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test


/**
 * Coco
 *
 *
 * Created by bedoy on 19/07/20.
 */
class ChatRepositoryTest{

    private var chatRepository : ChatRepository? = null

    @Before
    fun before(){
        chatRepository = ChatRepository()
    }

    @Test
    fun someFun() {
        runBlocking {
            val linkMetadata =
                chatRepository?.getLinkMetadata("https://www.amazon.com/")
        }
    }

}
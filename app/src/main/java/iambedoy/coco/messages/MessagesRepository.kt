package iambedoy.coco.messages

import com.haroldadmin.cnradapter.NetworkResponse
import iambedoy.coco.models.RandomUserResultResponse
import iambedoy.coco.services.RandomUserService

/**
 * Coco
 *
 * Created by bedoy on 07/07/20.
 */
class MessagesRepository (
    private val randomUserService: RandomUserService
){
    suspend fun requestUserList(count: Int = 10): List<RandomUserResultResponse> {
        return when(val randomUsers = randomUserService.getRandomUsers()){
            is NetworkResponse.Success -> {
                val body = randomUsers.body
                body.results
            }
            else -> {
                emptyList()
            }
        }
    }
}
package iambedoy.coco.services

import com.haroldadmin.cnradapter.NetworkResponse
import iambedoy.coco.models.RandomUserResponse
import retrofit2.http.GET

/**
 * Coco
 *
 * Created by bedoy on 07/07/20.
 */
interface RandomUserService {
    @GET("?results=100")
    suspend fun getRandomUsers() : NetworkResponse<RandomUserResponse, Void>
}
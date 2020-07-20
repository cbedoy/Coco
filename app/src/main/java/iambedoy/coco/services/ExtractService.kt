package iambedoy.coco.services

import com.haroldadmin.cnradapter.NetworkResponse
import iambedoy.coco.models.extract.ExtractResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Coco
 *
 * Created by bedoy on 19/07/20.
 */
interface ExtractService {
    @GET("1/extract")
    suspend fun getMetadataLink(
        @Query("url") url: String,
        @Query("key") key: String
    ) : NetworkResponse<ExtractResponse, Void>
}
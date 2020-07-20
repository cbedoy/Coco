package iambedoy.coco

import com.haroldadmin.cnradapter.NetworkResponseAdapterFactory
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import iambedoy.coco.chat.ChatFragment
import iambedoy.coco.chat.ChatRepository
import iambedoy.coco.chat.ChatViewModel
import iambedoy.coco.explorer.ExplorerFragment
import iambedoy.coco.messages.MessagesFragment
import iambedoy.coco.messages.MessagesRepository
import iambedoy.coco.messages.MessagesViewModel
import iambedoy.coco.pubnub.PubNubRepository
import iambedoy.coco.pubnub.PubNubService
import iambedoy.coco.services.ExtractService
import iambedoy.coco.services.RandomUserService
import iambedoy.coco.settings.SettingsFragment
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

/**
 * Coco
 *
 * Created by bedoy on 07/07/20.
 */

private fun Retrofit.Builder.createInstanceFor(baseUrl: String): Retrofit {


    val client = OkHttpClient.Builder().addInterceptor(HttpLoggingInterceptor().apply {
        HttpLoggingInterceptor.Level.BASIC
    }).build()

    return baseUrl(baseUrl)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .addCallAdapterFactory(NetworkResponseAdapterFactory())
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .client(client)
        .build()
}

val moshi: Moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()


val retrofitRandomService = Retrofit.Builder().createInstanceFor("https://randomuser.me/api/")
val retrofitEmbedLyService = Retrofit.Builder().createInstanceFor("http://api.embed.ly/")



val viewModel = module {
    factory {
        MessagesViewModel(get())
    }
    factory {
        ChatViewModel(get(), get())
    }
}

val fragment = module {
    factory {
        MessagesFragment()
    }
    factory {
        ExplorerFragment()
    }
    factory {
        SettingsFragment()
    }
    factory {
        ChatFragment()
    }
}

val repository = module {
    single {
        MessagesRepository(get())
    }
    single{
        ChatRepository(get())
    }
    single {
        PubNubRepository(get())
    }
}

val service = module {
    single {
        retrofitRandomService.create(RandomUserService::class.java)
    }
    single {
        retrofitEmbedLyService.create(ExtractService::class.java)
    }
    single {
        PubNubService()
    }
}


val appModule = viewModel + fragment + repository + service
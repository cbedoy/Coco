package iambedoy.coco

import com.haroldadmin.cnradapter.NetworkResponseAdapterFactory
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import iambedoy.coco.explorer.ExplorerFragment
import iambedoy.coco.messages.MessagesFragment
import iambedoy.coco.messages.MessagesRepository
import iambedoy.coco.messages.MessagesViewModel
import iambedoy.coco.services.RandomUserService
import iambedoy.coco.settings.SettingsFragment
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

/**
 * Coco
 *
 * Created by bedoy on 07/07/20.
 */

val moshi: Moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

val retrofit: Retrofit = Retrofit.Builder()
    .baseUrl("https://randomuser.me/api/")
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .addCallAdapterFactory(NetworkResponseAdapterFactory())
    .addCallAdapterFactory(CoroutineCallAdapterFactory())
    .build()

val viewModel = module {
    factory {
        MessagesViewModel(get())
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
}

val repository = module {
    single {
        MessagesRepository(get())
    }
}

val service = module {
    single {
        retrofit.create(RandomUserService::class.java)
    }
}


val appModule = viewModel + fragment + repository + service
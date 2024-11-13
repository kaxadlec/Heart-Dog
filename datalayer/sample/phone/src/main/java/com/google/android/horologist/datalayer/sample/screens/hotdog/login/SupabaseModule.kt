package com.google.android.horologist.datalayer.sample.screens.hotdog.login

import com.google.android.horologist.datalayer.sample.BuildConfig
import com.google.android.horologist.datalayer.sample.screens.hotdog.login.repository.AuthenticationRepository
import com.google.android.horologist.datalayer.sample.screens.hotdog.login.repository.AuthenticationRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.auth.Auth
import io.github.jan.supabase.auth.FlowType
import io.github.jan.supabase.auth.auth
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.postgrest.postgrest
import io.github.jan.supabase.serializer.KotlinXSerializer
import kotlinx.serialization.json.Json
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object SupabaseModule {

    @Provides
    @Singleton
    fun provideSupabaseClient(): SupabaseClient {
        return createSupabaseClient(
            supabaseUrl = BuildConfig.SUPABASE_URL,
            supabaseKey = BuildConfig.SUPABASE_ANON_KEY
        ) {
            install(Postgrest) {
                serializer = KotlinXSerializer(
                    Json {
                        ignoreUnknownKeys = true
                        isLenient = true
                        coerceInputValues = true // null 값을 기본값으로 대체
                    }
                )
            }
            install(Auth) {
                flowType = FlowType.PKCE
                scheme = "app"
                host = "supabase.com"
            }
        }
    }

    @Provides
    @Singleton
    fun provideSupabaseDatabase(client: SupabaseClient): Postgrest {
        return client.postgrest
    }

    @Provides
    @Singleton
    fun provideSupabaseAuth(client: SupabaseClient): Auth {
        return client.auth
    }

//    @Provides
//    @Singleton
//    fun provideSupabaseStorage(client: SupabaseClient): Storage {
//        return client.storage
//    }

//    @Provides
//    @Singleton
//    fun provideAuthenticationRepository(
//        auth: Auth
//    ) : AuthenticationRepository {
//        return AuthenticationRepositoryImpl(auth = auth)
//    }

    @Provides
    @Singleton
    fun provideAuthenticationRepository(
        auth: Auth,
        supabaseClient: SupabaseClient  // SupabaseClient 추가
    ) : AuthenticationRepository {
        return AuthenticationRepositoryImpl(
            auth = auth,
            supabaseClient = supabaseClient  // SupabaseClient 전달
        )
    }
}
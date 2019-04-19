package app.frantic.koinstarter.network

import app.frantic.koinstarter.model.Flower
import app.frantic.koinstarter.network.API_URL.BASEURL
import app.frantic.koinstarter.network.API_URL.GETFLOWERS
import com.itkacher.okhttpprofiler.OkHttpProfilerInterceptor
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kotlinx.coroutines.Deferred
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import java.util.concurrent.TimeUnit

class RestApi{
    companion object {

        operator fun invoke(): ApiServices {
            val loggingInterceptor = HttpLoggingInterceptor()
            loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

            val client = OkHttpClient().newBuilder()
                .connectTimeout(5, TimeUnit.MINUTES)
                .readTimeout(5000, TimeUnit.SECONDS)
                .writeTimeout(5000, TimeUnit.SECONDS)
                .addInterceptor(OkHttpProfilerInterceptor())
                .addInterceptor(loggingInterceptor)


            return Retrofit.Builder()
                .baseUrl(BASEURL)
                .client(client.build())
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(ApiServices::class.java)
        }
    }

    interface ApiServices{

        @GET(GETFLOWERS)
        fun getFlowers(): Deferred<Response<List<Flower>>>
    }
}
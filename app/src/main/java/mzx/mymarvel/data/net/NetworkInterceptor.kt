package mzx.mymarvel.data.net

import mzx.mymarvel.BuildConfig
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import okio.IOException
import java.math.BigInteger
import java.security.MessageDigest
import javax.inject.Inject


class NetworkInterceptor @Inject constructor() : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val currentRequest = chain.request()

        val builder = currentRequest.newBuilder()
            .url(addApyKeyToUrl(currentRequest, System.currentTimeMillis().toString()))
        val newRequest = builder.build()

        return chain.proceed(newRequest)
    }

    private fun addApyKeyToUrl(
        currentRequest: Request,
        currentTime: String
    ) = currentRequest.url.newBuilder()
        .addQueryParameter("ts", currentTime)
        .addQueryParameter("apikey", BuildConfig.PUB_API_KEY)
        .addQueryParameter("hash", hashValue(currentTime).md5()).build()

    private fun hashValue(ts: String): String =
        "$ts${BuildConfig.PRIV_API_KEY}${BuildConfig.PUB_API_KEY}"

    private fun String.md5(): String =
        BigInteger(1, MessageDigest.getInstance("MD5").digest(toByteArray())).toString(16)
            .padStart(32, '0')


}
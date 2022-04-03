package mzx.mymarvel.data.net

import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import okio.IOException
import javax.inject.Inject


class NetworkInterceptor @Inject constructor(private val networkParamFactory: NetworkParamFactory) :
    Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val param = networkParamFactory.getParam()
        val currentRequest = chain.request()


        val builder = currentRequest.newBuilder()
            .url(addApyKeyParam(currentRequest, param))
        val newRequest = builder.build()

        return chain.proceed(newRequest)
    }

    private fun addApyKeyParam(
        currentRequest: Request,
        currentParam: List<Pair<String, String>>
    ) = currentRequest.url.newBuilder().apply {
        currentParam.forEach { addQueryParameter(it.first, it.second) }
    }.build()

}
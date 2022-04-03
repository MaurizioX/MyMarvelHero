package mzx.mymarvel.data.net

import mzx.mymarvel.BuildConfig
import java.math.BigInteger
import java.security.MessageDigest
import javax.inject.Inject

class NetworkParamFactoryImpl @Inject constructor() : NetworkParamFactory {
    override fun getParam(): List<Pair<String, String>> {
        val time = System.currentTimeMillis().toString()
        return listOf(
            "ts" to time,
            "apikey" to BuildConfig.PUB_API_KEY,
            "hash" to hashValue(time).md5()
        )
    }

    private fun hashValue(ts: String): String =
        "$ts${BuildConfig.PRIV_API_KEY}${BuildConfig.PUB_API_KEY}"

    private fun String.md5(): String =
        BigInteger(1, MessageDigest.getInstance("MD5").digest(toByteArray())).toString(16)
            .padStart(32, '0')
}
package mzx.mymarvel.data.net

interface NetworkParamFactory {
    fun getParam(): List<Pair<String,String>>
}

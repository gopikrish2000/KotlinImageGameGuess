package sample.gopi.com.fastapplication.imagegameguess.networklayer

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class GameApiAdapter {

    private var gameApiObj: GameApi? = null

    companion object {
        private var curObj: GameApiAdapter? = null

        fun getInstance(): GameApiAdapter {
            return curObj ?: GameApiAdapter()
        }
    }

    fun getGameApi(): GameApi? {
        if (gameApiObj != null) return gameApiObj
        val retrofit = Retrofit.Builder().baseUrl("https://api.flickr.com").addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create()).build()
        gameApiObj = retrofit.create(GameApi::class.java)  // weird syntax for getting class GameApi::class.java => GameApi.class in java code
        return gameApiObj
    }
}
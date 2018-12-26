package sample.gopi.com.fastapplication.imagegameguess.repositoryLayer

import com.google.gson.Gson
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import sample.gopi.com.fastapplication.imagegameguess.networklayer.GameApiAdapter
import sample.gopi.com.fastapplication.imagegameguess.pojos.Game
import sample.gopi.com.fastapplication.imagegameguess.pojos.GameItemWrapper
import sample.gopi.com.fastapplication.imagegameguess.utils.CommonUtils

class ImageGameRepository {

    fun getRandomImages(): Observable<List<Game>?>? {
        val paramMap = hashMapOf("format" to "json")  // key to value
        return GameApiAdapter.getInstance().getGameApi()?.getRandomGameImages(paramMap)?.map(CommonUtils.Companion::convertResponseToString)
                ?.map { s -> CommonUtils.Companion.convertJsonPToJson(s) }
                ?.map { s -> s.toString() }?.map { s: String -> Gson().fromJson(s, GameItemWrapper::class.java) }?.map { wrapper: GameItemWrapper? -> wrapper?.items }?.observeOn(AndroidSchedulers.mainThread())?.subscribeOn(Schedulers.io())
    }
}
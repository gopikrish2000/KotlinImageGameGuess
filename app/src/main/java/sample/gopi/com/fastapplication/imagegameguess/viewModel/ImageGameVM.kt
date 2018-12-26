package sample.gopi.com.fastapplication.imagegameguess.viewModel

import android.arch.lifecycle.MutableLiveData
import sample.gopi.com.fastapplication.imagegameguess.pojos.Game
import sample.gopi.com.fastapplication.imagegameguess.repositoryLayer.ImageGameRepository

data class ImageGameVM(var groupVisibility: MutableLiveData<Boolean>, var countDown: MutableLiveData<Int>) {
    var gameList: MutableList<GameItem> = mutableListOf()
    var networkGameList: MutableLiveData<MutableList<Game>> = MutableLiveData()
    var textData: MutableLiveData<String> = MutableLiveData()
    var sampleList: MutableList<String> = mutableListOf("a", "b", "c", "d")
    var repository: ImageGameRepository = ImageGameRepository()

    init {
        groupVisibility.value = true
        textData.value = "Game starts in count down of "
        countDown.value = 15
    }

    fun makeNetworkCallProcessing() {
        repository.getRandomImages()?.subscribe({
            it?.let {
                var mutableList: MutableList<Game> = mutableListOf()
                var count: Int = 0
                it.forEach { gamObj ->
                    if ((gamObj.media?.m ?: "").length > 0 && count < 9){
                        mutableList.add(gamObj)
                        count++
                    }
                }
                networkGameList.value = mutableList
            }
        }) {
            print("exception happened with ${it.localizedMessage}")
        }
    }
}

data class GameItem(var name: String = "", var url: String?, var id: Int, var state: Int = 0)
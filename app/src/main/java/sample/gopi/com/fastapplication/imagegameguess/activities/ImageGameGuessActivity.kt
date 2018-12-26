package sample.gopi.com.fastapplication.imagegameguess.activities

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Observer
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import com.android.databinding.library.baseAdapters.BR
import sample.gopi.com.fastapplication.R
import sample.gopi.com.fastapplication.databinding.ActivityImageGameGuessBinding
import sample.gopi.com.fastapplication.imagegameguess.viewModel.ImageGameVM
import sample.gopi.com.fastapplication.imagegameguess.adapter.GameGuessAdapter

class ImageGameGuessActivity : AppCompatActivity() {
    lateinit var dataBinding: ActivityImageGameGuessBinding
    lateinit var imageGameVM: ImageGameVM
    lateinit var handler: Handler
    lateinit var gameGuessAdapter: GameGuessAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_image_game_guess)
        imageGameVM = ImageGameVM(MutableLiveData<Boolean>(), MutableLiveData())
        dataBinding.setVariable(BR.imageGameVM, imageGameVM)
        dataBinding.setVariable(BR.stringlist, imageGameVM.sampleList)
        dataBinding.setLifecycleOwner(this)
        handler = Handler(Looper.getMainLooper())

        initViews()
        initLogic()
    }

    fun initViews(){
        gameGuessAdapter = GameGuessAdapter(mutableListOf(), handler)
        dataBinding.gameRv.layoutManager = GridLayoutManager(this,3)
        dataBinding.gameRv.adapter = gameGuessAdapter
    }

    fun initLogic() {
        val runnable: Runnable = object : Runnable {   // if u want to use this keyword inside subclass then u have to use object.
            override fun run() {
                if (imageGameVM.countDown.value ?: 0 <= 0) {
                    imageGameVM.groupVisibility.setValue(false)
//                    imageGameVM.textData.value = " ZERO "
                    gameGuessAdapter.flipAllItemsToGrey()
                    return
                }
//                imageGameVM.textData.value = "Not fifteen "
                imageGameVM.countDown.setValue((imageGameVM.countDown.value ?: 0) - 1)
                handler.postDelayed(this, 1000)
            }
        }
        handler.postDelayed(runnable, 1000)
        imageGameVM.makeNetworkCallProcessing()
        imageGameVM.networkGameList.observe(this, Observer { list ->
            val updatedList = list?.filter { (it.media?.m).isNullOrEmpty().not() } ?: mutableListOf()
            gameGuessAdapter.updateItemList(updatedList)
        })


    }

    override fun onDestroy() {
        super.onDestroy()
        handler.removeCallbacksAndMessages(null)
    }
}

 /*var runnable1: Runnable = Runnable{
             if (imageGameVM.countDown.get() <= 0) {
//                 dataBinding.topTvGroup.visibility = View.GONE
                 imageGameVM.groupVisibility.value = false
             }
             imageGameVM.countDown.set(imageGameVM.countDown.get() -1)
//             handler.postDelayed(this, 1000)      It errors out at THIS keyword as u have to use SAM conversions to do it ( https://kotlinlang.org/docs/reference/java-interop.html#sam-conversions )
         }*/

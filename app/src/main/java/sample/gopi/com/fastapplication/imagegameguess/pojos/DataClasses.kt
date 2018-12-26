package sample.gopi.com.fastapplication.imagegameguess.pojos

data class GameItemWrapper(var items: List<Game>)

data class Game(val title: String?, val link: String?, val media: FlickerMedia?, val author: String?, var showState: Boolean = true)

data class FlickerMedia(val m: String?)
package sample.gopi.com.fastapplication.imagegameguess.utils

import android.widget.Toast
import okhttp3.ResponseBody
import sample.gopi.com.fastapplication.imagegameguess.activities.GameApplication
import java.io.BufferedReader
import java.io.InputStreamReader


class CommonUtils {

    companion object {

        fun convertResponseToString(responseBody: ResponseBody?): String {
            try {
                val reader: BufferedReader? = BufferedReader(InputStreamReader(responseBody?.byteStream()))
                val out = StringBuilder()
                val newLine = System.getProperty("line.separator")
                var line: String?
                while (true) {
                    line = reader?.readLine()
                    if(line == null) break
                    out.append(line)
                    out.append(newLine)
                }
                println(out) // Prints the correct String representation of body.
                return out.toString()
            } catch (e: Exception) {
                e.printStackTrace()
            }

            return ""
        }

        fun convertJsonPToJson(jsonp: String?): String {
            /*return jsonp?.let {
                // if nonnull then only it gets executed.
                return@let if (!jsonp.contains("(") || !jsonp.contains(")")) {
                    jsonp
                } else jsonp.substring(jsonp.indexOf("(") + 1, jsonp.lastIndexOf(")"))
            } ?: ""*/

//            if ((jsonp == null)) ""
            var jsonp = jsonp!!
            return if (!jsonp.contains("(") || !jsonp.contains(")")) {
                jsonp
            } else {
                jsonp = jsonp.substring(jsonp.indexOf("(") + 1, jsonp.lastIndexOf(")"))
                jsonp
            }
        }

        fun showToast(msg: String) {
            Toast.makeText(GameApplication.getAppContext(),msg, Toast.LENGTH_LONG).show()
        }
    }

}
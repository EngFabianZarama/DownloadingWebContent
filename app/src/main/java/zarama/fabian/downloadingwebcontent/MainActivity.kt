package zarama.fabian.downloadingwebcontent

import android.os.AsyncTask
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

class MainActivity : AppCompatActivity() {

    // Pass in a string, pass out String
    open class DownloadTask() : AsyncTask<String,Void,String>(){
        protected override fun doInBackground(vararg urls: String?): String { // is like an array
            var result: String = ""

            try {


                val url = URL(urls.get(0))
                val urlConnection = url.openConnection() as HttpURLConnection
                val input = urlConnection.inputStream
                val reader = InputStreamReader(input)
                var data = reader.read()

                while (data != -1) {
                    var current = data.toChar()
                    result += current
                    data = reader.read()
                }


                //Log.i("URL1", urls.get(0))
                //Log.i("URL2", urls.get(1))

            }catch (e: Exception){
                Log.i("Download Error", e.localizedMessage)
            }
            return result
        }


    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var task = DownloadTask()
        var result : String = ""

        try {
            result = task.execute("www.google.com/").get()

            Log.i("Result",result)

        }catch (e: Exception){
            Log.i("Puta", e.printStackTrace().toString())
        }

    }
}

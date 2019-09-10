package fi.metropolia.fetchimage

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.ConnectivityManager
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

import android.view.View
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*
import java.net.URL

class MainActivity : AppCompatActivity(), View.OnClickListener {

    inner class LoadImageTask : AsyncTask<Any, Unit, Bitmap>() {
        private val sampleImgUrl = "https://i.imgur.com/LIEH6y7.png"

        override fun doInBackground(vararg params: Any?): Bitmap? {
            var bmp: Bitmap? = null
            try {
                val raw = URL(sampleImgUrl).readBytes()
                bmp = BitmapFactory.decodeByteArray(raw, 0, raw.size)
            } catch (e: Exception) {
                Log.e("LoadImageTask", "Error loading image: $e")
            }
            return bmp
        }

        override fun onPostExecute(result: Bitmap?) {
            if (result != null) imageView.setImageBitmap(result)
            else super.onPostExecute(result)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        downloadButton.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        if (isNetworkAvailable()) {
            showSnackWithMessage("Downloading image...")
            LoadImageTask().execute()
        } else {
            showSnackWithMessage("No internet connection.")
        }
    }

    private fun showSnackWithMessage(msg: String) {
        val snack = Snackbar.make(
            rootLayout,
            msg,
            Snackbar.LENGTH_SHORT
        )
        snack.view.background = resources.getDrawable(R.drawable.snackbar_background, this.theme)
        snack.show()
    }

    private fun isNetworkAvailable(): Boolean {
        val connectivityManager = this.getSystemService(
            Context.CONNECTIVITY_SERVICE
        ) as ConnectivityManager
        return connectivityManager.activeNetworkInfo?.isConnected == true
    }
}

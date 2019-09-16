package fi.metropolia.fileapp

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.view.View
import android.widget.Toast
import androidx.fragment.app.FragmentTransaction
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_file_input.*
import kotlinx.android.synthetic.main.fragment_view_file.*
import org.jetbrains.anko.toast
import java.io.File

class MainActivity : AppCompatActivity(), OnFragmentInteractionListener {

    companion object {
        const val TXTFILE = "fileapp_txtfile.txt"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onFragmentInteraction(v: View?) {
        when (v) {
            viewButton -> navigateToViewFragment()
            writeButton -> writeNewFile()
            else -> onBackPressed()
        }
    }

    private fun writeNewFile() {
        if (Environment.getExternalStorageState() == Environment.MEDIA_MOUNTED) {
            val dir = applicationContext.getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS)
            val file = File(dir, TXTFILE)
            val newText = textInputTv.text
            file.writeText("$newText")
            textInputTv.text.clear()
            toast("Write successful!")
        } else {
            toast("Failed to write file.")
        }
    }

    private fun navigateToViewFragment() {
        supportFragmentManager
            .beginTransaction()
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            .addToBackStack(null)
            .add(R.id.fragmentContainer, ViewFileFragment.newInstance(), "view_file_fragment")
            .commit()
    }
}

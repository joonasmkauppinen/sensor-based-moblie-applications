package fi.metropolia.helloworld

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var isHelloWorld = true

    companion object {
        private const val TOGGLE_STATE_KEY = "TOGGLE_STATE_KEY"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        toggleTextButton.setOnClickListener {
            isHelloWorld = !isHelloWorld
            updateTitle(isHelloWorld)
        }

        if (savedInstanceState != null) {
            isHelloWorld = savedInstanceState.getBoolean(TOGGLE_STATE_KEY)
            updateTitle(isHelloWorld)
        }
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        outState?.putBoolean(TOGGLE_STATE_KEY, isHelloWorld)
    }

    private fun updateTitle (toggleState: Boolean) {
        when (toggleState) {
            true  -> textTitle.text = getString(R.string.title_main)
            false -> textTitle.text = getString(R.string.title_secondary)
        }
    }
}

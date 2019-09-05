package fi.metropolia.helloworld

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var isHello = true

    private val clickListener = View.OnClickListener {
        isHello = !isHello

        when (isHello) {
            true  -> textTitle.text = getString(R.string.title_main)
            false -> textTitle.text = getString(R.string.title_secondary)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        toggleTextButton.setOnClickListener(clickListener)
    }
}

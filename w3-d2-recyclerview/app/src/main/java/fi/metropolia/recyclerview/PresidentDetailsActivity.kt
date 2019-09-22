package fi.metropolia.recyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_president_details.*

class PresidentDetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_president_details)
        val president = Presidents.list[intent.getIntExtra("PRESIDENT_INDEX", 0)]
        name.text = president.fullName
        duty.text = "${president.startDuty} - ${president.endDuty}"
        description.text = "${president.description}"
    }
}

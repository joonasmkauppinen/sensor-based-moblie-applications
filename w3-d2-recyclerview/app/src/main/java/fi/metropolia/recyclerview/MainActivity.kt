package fi.metropolia.recyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val presidentsList = ArrayList(Presidents.list)

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = PresidentsAdapter(presidentsList)
    }
}

package fi.metropolia.mood

import android.os.Bundle
import android.view.View
import androidx.fragment.app.FragmentTransaction
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

private const val MOOD_GOOD = "GOOD"
private const val MOOD_BAD = "BAD"

class MainActivity : AppCompatActivity(), View.OnClickListener, MoodFragment.OnFragmentInteractionListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        setContentView(R.layout.activity_main)
        goodMoodButton.setOnClickListener(this)
        badMoodButton.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v) {
            goodMoodButton -> navigateToMoodFragment(MOOD_GOOD)
            badMoodButton  -> navigateToMoodFragment(MOOD_BAD)
        }
    }

    private fun navigateToMoodFragment(mood: String) {
        val fragment = MoodFragment.newInstance(mood)
        val transaction = supportFragmentManager.beginTransaction()
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
        transaction.addToBackStack(null)
        transaction.add(R.id.fragmentContainer, fragment, "MOOD_FRAGMENT").commit()
        supportFragmentManager.executePendingTransactions()
    }

    override fun onFragmentInteraction() {
        onBackPressed()
    }
}

package fi.metropolia.mood

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.view.LayoutInflater
import android.content.Context
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_mood.view.*

private const val USER_MOOD = "USER_MOOD"
private const val MOOD_GOOD = "GOOD"

class MoodFragment : Fragment() {
    private var userMood: String? = null
    private var listener: OnFragmentInteractionListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            userMood = it.getString(USER_MOOD)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_mood, container, false)

        val isGoodMood = (userMood == MOOD_GOOD)

        val text = if (isGoodMood) getString(R.string.mood_good) else getString(R.string.mood_bad)

        val color = if (isGoodMood) resources.getColor(R.color.yellow, activity?.theme)
        else resources.getColor(R.color.blue, activity?.theme)

        val secondaryColor = if (isGoodMood) resources.getColor(R.color.yellowDark, activity?.theme)
        else resources.getColor(R.color.blueDark, activity?.theme)

        val buttonBg = if (isGoodMood) R.drawable.button_good_goback else R.drawable.button_bad_goback

        val imageSrc = if (isGoodMood) R.mipmap.good_face_ic else R.mipmap.bad_face_ic

        view.moodImage.setImageResource(imageSrc)
        view.moodFragment.setBackgroundColor(color)
        view.moodText.text = text
        view.moodText.setTextColor(secondaryColor)
        view.goBackButton.setBackgroundResource(buttonBg)
        view.goBackButton.setTextColor(secondaryColor)
        view.goBackButton.setOnClickListener{ onGoBackButtonPressed() }

        return view
    }

    private fun onGoBackButtonPressed() {
        listener?.onFragmentInteraction()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException("$context must implement OnFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    interface OnFragmentInteractionListener {
        fun onFragmentInteraction()
    }

    companion object {
        fun newInstance(userMood: String) =
            MoodFragment().apply {
                arguments = Bundle().apply {
                    putString(USER_MOOD, userMood)
                }
            }
    }
}

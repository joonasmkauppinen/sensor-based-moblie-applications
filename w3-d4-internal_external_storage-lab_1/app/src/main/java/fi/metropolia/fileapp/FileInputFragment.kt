package fi.metropolia.fileapp


import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_file_input.view.*

class FileInputFragment : Fragment() {

    private var listener: OnFragmentInteractionListener? = null

//    companion object {
//        fun newInstance(): FileInputFragment {
//            return FileInputFragment()
//        }
//    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException("$context must implement OnFragmentInteractionListener")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_file_input, container, false)
        view.viewButton.setOnClickListener { onNavigationButtonPress(it) }
        view.writeButton.setOnClickListener { onNavigationButtonPress(it) }
        return view
    }

    private fun onNavigationButtonPress(v: View) {
        listener?.onFragmentInteraction(v)
    }

}

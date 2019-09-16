package fi.metropolia.fileapp


import android.content.Context
import android.os.Bundle
import android.os.Environment
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_view_file.*
import kotlinx.android.synthetic.main.fragment_view_file.view.*
import org.jetbrains.anko.support.v4.toast
import java.io.File

class ViewFileFragment : Fragment() {

    private var listener: OnFragmentInteractionListener? = null

    companion object {
        const val TXTFILE = "fileapp_txtfile.txt"
        fun newInstance(): ViewFileFragment {
            return ViewFileFragment()
        }
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException("$context must implement OnFragmentInteractionListener")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_view_file, container, false)
        view.goBackButton.setOnClickListener{ onGoBackPress(it) }
        return view
    }

    override fun onStart() {
        super.onStart()
        if (Environment.getExternalStorageState() == Environment.MEDIA_MOUNTED) {
            val dir = context?.getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS)
            val file = File(dir, TXTFILE)
            fileContentTv.text = file.readText()
        } else {
            toast("Failed to read file.")
        }
    }

    private fun onGoBackPress(v: View?) {
        listener?.onFragmentInteraction(v)
    }

}

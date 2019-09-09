package fi.metropolia.databinding


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import fi.metropolia.databinding.databinding.FragmentAddressBinding

/**
 * A simple [Fragment] subclass.
 */
class AddressFragment : Fragment() {

    lateinit var bindingClass: FragmentAddressBinding
    val addresses = Model()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        bindingClass = DataBindingUtil.inflate(inflater, R.layout.fragment_address, container, false)
        bindingClass.addresses = addresses
        return bindingClass.root
    }


}

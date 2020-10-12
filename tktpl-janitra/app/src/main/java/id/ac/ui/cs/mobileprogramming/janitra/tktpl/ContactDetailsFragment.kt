package id.ac.ui.cs.mobileprogramming.janitra.tktpl

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import id.ac.ui.cs.mobileprogramming.janitra.tktpl.data.Contacts
import id.ac.ui.cs.mobileprogramming.janitra.tktpl.databinding.FragmentContactDetailsBinding

class ContactDetailsFragment (val data: Contacts, val position: Int): Fragment() {
    private lateinit var mView: View

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentContactDetailsBinding =
            FragmentContactDetailsBinding.inflate(inflater, container, false)
        this.mView = binding.getRoot()

        binding.viewName.setText(data.name)
        binding.viewPhone.setText(data.phone.toString())
        binding.viewAddress.setText(data.address)
        binding.viewCompany.setText(data.company)
        binding.viewWebsite.setText(data.website)
        return mView
    }
}

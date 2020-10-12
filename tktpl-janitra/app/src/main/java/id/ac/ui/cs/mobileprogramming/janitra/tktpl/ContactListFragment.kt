package id.ac.ui.cs.mobileprogramming.janitra.tktpl

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import id.ac.ui.cs.mobileprogramming.janitra.tktpl.data.Contacts
import id.ac.ui.cs.mobileprogramming.janitra.tktpl.vm.ContactsViewModel
import id.ac.ui.cs.mobileprogramming.janitra.tktpl.util.InjectorUtils
import id.ac.ui.cs.mobileprogramming.janitra.tktpl.databinding.FragmentListContactBinding

class ContactListFragment: Fragment() {
    private lateinit var recyclerView: RecyclerView
    val ADD_CONTACTS_REQUEST = 1
    private lateinit var mView: View
    private lateinit var contactsViewModel: ContactsViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding: FragmentListContactBinding = FragmentListContactBinding.inflate(inflater, container, false)
        this.mView = binding.root
        this.recyclerView = mView.findViewById(R.id.recycler_view)

        val buttonAddContacts = mView.findViewById(R.id.button_add) as ImageButton
        buttonAddContacts.setOnClickListener {
            val intent: Intent = Intent(mView.context, AddContactActivity::class.java)
            startActivityForResult(intent, ADD_CONTACTS_REQUEST)
        }

        showRecyclerList()
        return mView
    }

    fun goToSelectedContacts(data: Contacts, position: Int) {
        val fragment = ContactDetailsFragment(data, position)
        val fragmentTransaction: FragmentTransaction? = activity?.supportFragmentManager?.beginTransaction()
        fragmentTransaction?.replace(R.id.fragment_list_contact, fragment, fragment.toString())
        fragmentTransaction?.addToBackStack(fragment.toString())
        fragmentTransaction?.commit()
    }

    private fun showRecyclerList() {
        recyclerView.setLayoutManager(LinearLayoutManager(mView.context))
        recyclerView.setHasFixedSize(true)

        val adapter = ContactsAdapter()
        recyclerView.setAdapter(adapter)

        val factory = InjectorUtils.provideQuotesViewModelFactory(mView.context)

        contactsViewModel = ViewModelProviders.of(this, factory)
            .get(ContactsViewModel::class.java)
        contactsViewModel.getAllContacts().observe(this, Observer { contacts ->
            adapter.setContacts(contacts)
        })

        adapter.setOnItemClickCallback(object: ContactsAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Contacts, position: Int) {
                goToSelectedContacts(data, position)
            }
        })
    }

    @kotlinx.coroutines.ObsoleteCoroutinesApi
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == ADD_CONTACTS_REQUEST && resultCode == Activity.RESULT_OK) {
            if (data != null) {
                val name: String = data.getStringExtra(AddContactActivity.EXTRA_NAME)?: ""
                val phone: Int = data.getIntExtra(AddContactActivity.EXTRA_PHONE,1234)
                val address: String = data.getStringExtra(AddContactActivity.EXTRA_ADDRESS)?: ""
                val company: String = data.getStringExtra(AddContactActivity.EXTRA_COMPANY)?: ""
                val website: String = data.getStringExtra(AddContactActivity.EXTRA_WEBSITE)?: ""

                val contacts = Contacts(name, phone, address, company, website)
                contactsViewModel.insert(contacts)

                Toast.makeText(mView.context, "Contact Saved", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
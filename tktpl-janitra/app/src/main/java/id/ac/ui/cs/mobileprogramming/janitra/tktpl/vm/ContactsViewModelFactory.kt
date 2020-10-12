package id.ac.ui.cs.mobileprogramming.janitra.tktpl.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import id.ac.ui.cs.mobileprogramming.janitra.tktpl.repository.ContactsRepository

class ContactsViewModelFactory (private val contactsRepository: ContactsRepository)
    : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T: ViewModel?> create(modelClass: Class<T>): T {
        return ContactsViewModel(contactsRepository) as T
    }
}
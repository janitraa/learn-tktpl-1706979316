package id.ac.ui.cs.mobileprogramming.janitra.tktpl.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import id.ac.ui.cs.mobileprogramming.janitra.tktpl.data.Contacts
import id.ac.ui.cs.mobileprogramming.janitra.tktpl.repository.ContactsRepository

class ContactsViewModel (
    private val contactsRepository: ContactsRepository
): ViewModel() {
    private val allContacts: LiveData<List<Contacts>> = contactsRepository.getAllContacts()

    @kotlinx.coroutines.ObsoleteCoroutinesApi
    fun insert(contacts: Contacts) {
        contactsRepository.insertContacts(contacts)
    }

    @kotlinx.coroutines.ObsoleteCoroutinesApi
    fun update(contacts: Contacts) {
        contactsRepository.updatecontacts(contacts)
    }

    @kotlinx.coroutines.ObsoleteCoroutinesApi
    fun delete(contacts: Contacts) {
        contactsRepository.deleteContacts(contacts)
    }

    fun getAllContacts(): LiveData<List<Contacts>> {
        return allContacts
    }
}
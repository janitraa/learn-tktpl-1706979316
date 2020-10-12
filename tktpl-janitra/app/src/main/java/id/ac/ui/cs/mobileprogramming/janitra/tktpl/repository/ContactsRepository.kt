package id.ac.ui.cs.mobileprogramming.janitra.tktpl.repository

import androidx.lifecycle.LiveData
import id.ac.ui.cs.mobileprogramming.janitra.tktpl.data.Contacts
import id.ac.ui.cs.mobileprogramming.janitra.tktpl.data.ContactsDao
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.newSingleThreadContext

class ContactsRepository private constructor(
    private val contactsDao: ContactsDao
) {
    private val allContacts: LiveData<List<Contacts>> = contactsDao.getAll()

    @kotlinx.coroutines.ObsoleteCoroutinesApi
    var thread = newSingleThreadContext("contactsRepository") as CoroutineDispatcher

    @kotlinx.coroutines.ObsoleteCoroutinesApi
    fun insertContacts(contacts: Contacts) = GlobalScope.launch(thread) {
        contactsDao.insert(contacts)
    }

    fun getAllContacts(): LiveData<List<Contacts>> {
        return allContacts
    }

    @kotlinx.coroutines.ObsoleteCoroutinesApi
    fun deleteContacts(contacts: Contacts) = GlobalScope.launch(thread) {
        contactsDao.delete(contacts)
    }

    @kotlinx.coroutines.ObsoleteCoroutinesApi
    fun updatecontacts(contacts: Contacts) = GlobalScope.launch(thread) {
        contactsDao.update(contacts)
    }

    companion object {
        @Volatile private var instance: ContactsRepository? = null

        fun getInstance(contactsDao: ContactsDao) =
            instance ?: synchronized(this) {
                instance ?: ContactsRepository(contactsDao).also { instance = it }
            }
    }
}
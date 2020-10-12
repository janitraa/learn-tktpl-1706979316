package id.ac.ui.cs.mobileprogramming.janitra.tktpl.util

import android.content.Context
import id.ac.ui.cs.mobileprogramming.janitra.tktpl.data.ContactsDb
import id.ac.ui.cs.mobileprogramming.janitra.tktpl.repository.ContactsRepository
import id.ac.ui.cs.mobileprogramming.janitra.tktpl.vm.ContactsViewModelFactory

object InjectorUtils {
    fun provideQuotesViewModelFactory(context: Context): ContactsViewModelFactory {

        val contactsRepository = ContactsRepository.getInstance(ContactsDb.getInstance(context).contactsDao())
        return ContactsViewModelFactory(contactsRepository)
    }
}
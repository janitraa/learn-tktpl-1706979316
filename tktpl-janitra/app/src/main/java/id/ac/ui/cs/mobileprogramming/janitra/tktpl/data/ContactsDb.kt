package id.ac.ui.cs.mobileprogramming.janitra.tktpl.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Contacts::class], version = 2, exportSchema = false)
abstract class ContactsDb: RoomDatabase() {

    abstract fun contactsDao(): ContactsDao

    companion object {

        @Volatile
        private var instance: ContactsDb? = null

        fun getInstance(context: Context) = instance ?: synchronized(this) {
                instance ?: Room.databaseBuilder(
                    context.getApplicationContext(),
                    ContactsDb::class.java,
                    "contacts_db"
                )
                    .fallbackToDestructiveMigration()
                    .build()
        }
    }
}
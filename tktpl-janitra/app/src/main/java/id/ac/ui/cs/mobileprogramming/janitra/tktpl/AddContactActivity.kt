package id.ac.ui.cs.mobileprogramming.janitra.tktpl

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlin.reflect.jvm.internal.impl.resolve.constants.IntValue

class AddContactActivity : AppCompatActivity() {

    private lateinit var editName: EditText
    private lateinit var editPhone: EditText
    private lateinit var editAddress: EditText
    private lateinit var editCompany: EditText
    private lateinit var editWebsite: EditText

    companion object {
        val EXTRA_NAME = "id.ac.ui.cs.mobileprogrramming.janitra.tktpl.EXTRA_NAME"
        val EXTRA_PHONE = "id.ac.ui.cs.mobileprogrramming.janitra.tktpl.EXTRA_PHONE"
        val EXTRA_ADDRESS = "id.ac.ui.cs.mobileprogrramming.janitra.tktpl.EXTRA_ADDRESS"
        val EXTRA_COMPANY = "id.ac.ui.cs.mobileprogrramming.janitra.tktpl.EXTRA_COMPANY"
        val EXTRA_WEBSITE = "id.ac.ui.cs.mobileprogrramming.janitra.tktpl.EXTRA_WEBSITE"
    }

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_contact)

        editName = findViewById(R.id.edit_name)
        editPhone = findViewById(R.id.edit_phone)
        editAddress = findViewById(R.id.edit_address)
        editCompany = findViewById(R.id.edit_company)
        editWebsite = findViewById(R.id.edit_website)

        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_noun_close)
        setTitle("Add Contact")
    }

    fun saveContact() {
        val name: String = editName.text.toString()
        val phone: String = editPhone.text.toString()
        val address: String = editAddress.text.toString()
        val company: String = editCompany.text.toString()
        val website: String = editWebsite.text.toString()

        if (name.trim().isEmpty() || phone.trim().isEmpty()) {
            Toast.makeText(this, "Please insert name and phone number", Toast.LENGTH_SHORT).show()
            return
        }

        val data: Intent = Intent()
        data.putExtra(EXTRA_NAME, name)
        data.putExtra(EXTRA_PHONE, phone)
        data.putExtra(EXTRA_ADDRESS, address)
        data.putExtra(EXTRA_COMPANY, company)
        data.putExtra(EXTRA_WEBSITE, website)

        setResult(RESULT_OK, data)
        finish()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.save_contact, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.save_contact -> {
                saveContact()
                return true
            }

            else -> {
                return super.onOptionsItemSelected(item)
            }
        }
    }
}
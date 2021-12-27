package com.example.kinofanplus.viewmodel

import android.Manifest
import android.annotation.SuppressLint
import android.app.AlertDialog
import android.database.Cursor
import android.os.Bundle
import android.provider.ContactsContract
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.kinofanplus.R
import com.example.kinofanplus.databinding.ActivityMainBinding
import com.example.kinofanplus.view.ADULT_KEY
import com.example.kinofanplus.view.isAdultDefault
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val permissionResult =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { result ->
            when {
                result -> getContact()
                //узнать, что юзер поставил галочку "не показывать"
                shouldShowRequestPermissionRationale(Manifest.permission.READ_CONTACTS) -> {
                    Toast.makeText(this, "Permission denied allow", Toast.LENGTH_SHORT).show()
                }
                else -> Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT).show()
            }
        }

    private val binding: ActivityMainBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    private val buttonNavigationPanel: BottomNavigationView by lazy { binding.buttonNavigationPanel }

    private val navigationController by lazy { findNavController(R.id.navigation_fragment_container) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setSupportActionBar(custom_toolbar)

        buttonNavigationPanel.setupWithNavController(navigationController)

        setDefaultSettings()
    }

    private fun setDefaultSettings() {
        this.getPreferences(MODE_PRIVATE)?.getBoolean(ADULT_KEY, isAdultDefault)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.settings -> {
                navigationController.navigate(R.id.settingsFragment)
                true
            }
            R.id.contacts -> {
                permissionResult.launch(Manifest.permission.READ_CONTACTS)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    @SuppressLint("Range")
    private fun getContact() {
        val cursor: Cursor? = contentResolver.query(
            ContactsContract.Contacts.CONTENT_URI,
            null,
            null,
            null,
            ContactsContract.Contacts.DISPLAY_NAME + " ASC"
        )

        val contacts = mutableListOf<String>()
        cursor?.let {
            for (i in 0..cursor.count) {
                if (cursor.moveToPosition(i)) {
                    val name =
                        cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME))
                    contacts.add(name)
                }
            }
            it.close()
        }
        AlertDialog.Builder(this)
            .setItems(contacts.toTypedArray()) { _, whitch ->
                Toast.makeText(this, "Звоню контакту -> ${contacts[whitch]}", Toast.LENGTH_SHORT)
                    .show()
            }
            .setCancelable(true)
            .show()
    }
}
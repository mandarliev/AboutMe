package com.example.android.aboutme

import android.content.Context
import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.example.android.aboutme.databinding.ActivityMainBinding
import org.w3c.dom.Text

class MainActivity : AppCompatActivity() {

    // Create a variable for the binding object
    private lateinit var binding: ActivityMainBinding

    // Instance of MyName data class.
    private val myName: MyName = MyName("Dimitar Mandarliev")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Setting the content view using DataBindingUtil creates an instance of
        // ActivityMainBinding from the supplied activity and the supplied layout. This object
        // contains mappings between the activity and layout,
        // and functionality to interact with them.
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        // Set the value of the myName variable that is declared and used in the layout fule.
        binding.myName = myName

        // Click listener for the Done button.
        binding.done.setOnClickListener { addNickname(it) }
    }

    // Click handler for the Done button.
    // Demonstrates how data binding can be used to make code much more readable
    // by eliminating calls to findViewById and changing data in the binding object.
    private fun addNickname(view: View) {
        binding.apply {
            // Set the text for nicknameText to the value in nicknameEdit.
            myName?.nickname = nicknameEdit.text.toString()
            // Invalidate all binding expressions and request a new rebind to refresh UI
            invalidateAll()
            // Change which views are visible
            // Remove the EditText and the Button.
            // With GONE they are invisible and do not occupy space.
            nicknameEdit.visibility = View.GONE
            done.visibility = View.GONE
            
            // Make the TextView with the nickname visible.
            nicknameText.visibility = View.VISIBLE
        }

        // Hide the keyboard.
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }
}

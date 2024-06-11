package com.example.exp4

/*
import android.os.Bundle
import android.telephony.SmsManager
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
class MainActivity : AppCompatActivity() {
    private lateinit var phoneNumber: EditText
    private lateinit var message: EditText
    private lateinit var send: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        send = findViewById(R.id.button)
        phoneNumber = findViewById(R.id.editText)
        message = findViewById(R.id.editText2)
        send.setOnClickListener {
            val number = phoneNumber.text.toString()
            val msg = message.text.toString()
            try {
                val smsManager = SmsManager.getDefault()
                smsManager.sendTextMessage(number, null, msg, null, null)
                Toast.makeText(applicationContext, "Message Sent", Toast.LENGTH_LONG).show()
            } catch (e: Exception) {
                Toast.makeText(applicationContext, "Some fields are empty ${e}",
                    Toast.LENGTH_LONG).show()
            }
        }
    }
}


 */
//if not above code try below comment above

import android.os.Bundle
import android.telephony.SmsManager
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import android.Manifest
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
class MainActivity : AppCompatActivity() {
    private lateinit var phoneNumber: EditText
    private lateinit var message: EditText
    private lateinit var send: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        send = findViewById(R.id.button)
        phoneNumber = findViewById(R.id.editText)
        message = findViewById(R.id.editText2)
        send.setOnClickListener {
            val number = phoneNumber.text.toString()
            val msg = message.text.toString()
            if (number.isNotEmpty() && msg.isNotEmpty()) {
                if (ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS) ==
                    PackageManager.PERMISSION_GRANTED) {
                    sendSMS(number, msg)
                } else {
                    ActivityCompat.requestPermissions(this,
                        arrayOf(Manifest.permission.SEND_SMS), SMS_PERMISSION_CODE)
                }
            } else {
                Toast.makeText(this, "Please enter both phone number and message",
                    Toast.LENGTH_SHORT).show()
            }
        }
    }
    private fun sendSMS(phone: String, message: String) {
        try {
            val smsManager = SmsManager.getDefault()
            smsManager.sendTextMessage(phone, null, message, null, null)
            Toast.makeText(applicationContext, "Message Sent", Toast.LENGTH_LONG).show()
        } catch (e: Exception) {
            Toast.makeText(applicationContext, "Failed to send message",
                Toast.LENGTH_LONG).show()
        }
    }
    companion object {
        private const val SMS_PERMISSION_CODE = 101
    }
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>,
                                            grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == SMS_PERMISSION_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] ==
                PackageManager.PERMISSION_GRANTED) {
                val number = phoneNumber.text.toString()
                val msg = message.text.toString()
                sendSMS(number, msg)
            } else {
                Toast.makeText(this, "SMS permission denied", Toast.LENGTH_SHORT).show()
            }
        }
    }
}

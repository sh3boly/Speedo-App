package com.example.speedoapp.ui.help

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.lifecycle.ViewModel

class HelpViewModel : ViewModel() {
    private fun navigateToDial(context: Context) {
        val intent = Intent(Intent.ACTION_DIAL).apply {
            data = Uri.parse("tel:19888")
        }
        context.startActivity(intent)
    }

    @SuppressLint("QueryPermissionsNeeded")
    private fun navigateToEmail(context: Context) {
        val intent = Intent(Intent.ACTION_SENDTO).apply {
            data = Uri.parse("mailto:BM19888@banquemisr.com")
        }
        context.startActivity(intent)
    }

    fun help(identifier: Int, context: Context) {
        if (identifier == 0) {
            navigateToDial(context)
        } else
            navigateToEmail(context)
    }

}
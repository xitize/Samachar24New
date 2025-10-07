package com.kshitiz.samachar24.ui.screen

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Switch
import android.widget.TextView
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import com.kshitiz.samachar24.R


class ProfileFrag : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.profile_frag, container, false)
        val mSwitch = view.findViewById<Switch>(R.id.switcher)
        val share = view.findViewById<TextView>(R.id.text_share)
        val rate_us = view.findViewById<TextView>(R.id.text_rate_us)
        val feedback = view.findViewById<TextView>(R.id.text_feedback)
        feedback.setOnClickListener(View.OnClickListener { v: View? ->
            val emailIntent = Intent(Intent.ACTION_SEND)
            emailIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            emailIntent.setType("vnd.android.cursor.item/email")
            emailIntent.putExtra(Intent.EXTRA_EMAIL, arrayOf<String>("xitize@zoho.com"))
            emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Feedback on Samachar24 App")
            emailIntent.putExtra(Intent.EXTRA_TEXT, "App is ...")
            startActivity(Intent.createChooser(emailIntent, "Send mail using..."))
        })

        share.setOnClickListener {
            val appPackageName = activity?.packageName
            val sendIntent = Intent()
            sendIntent.setAction(Intent.ACTION_SEND)
            sendIntent.putExtra(
                Intent.EXTRA_TEXT,
                "Check out the App at: https://play.google.com/store/apps/details?id=$appPackageName"
            )
            sendIntent.setType("text/plain")
            activity?.startActivity(sendIntent)
        }

        rate_us.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.setData(("https://play.google.com/store/apps/details?id=" + activity?.packageName).toUri())
            startActivity(intent)
        }
        return view
    }
}

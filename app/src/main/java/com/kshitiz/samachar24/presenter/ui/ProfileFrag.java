package com.kshitiz.samachar24.presenter.ui;


import android.app.Fragment;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;


import androidx.annotation.NonNull;

import com.kshitiz.samachar24.R;

import io.paperdb.Paper;


/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFrag extends Fragment {


    public ProfileFrag() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        final Switch mSwitch = view.findViewById(R.id.switcher);
        TextView share = view.findViewById(R.id.text_share);
        TextView rate_us = view.findViewById(R.id.text_rate_us);
        TextView feedback = view.findViewById(R.id.text_feedback);
        feedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent emailIntent = new Intent(Intent.ACTION_SEND);
                emailIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                emailIntent.setType("vnd.android.cursor.item/email");
                emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{"xitize@zoho.com"});
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Feedback on Samachar24 App");
                emailIntent.putExtra(Intent.EXTRA_TEXT, "App is ...");
                startActivity(Intent.createChooser(emailIntent, "Send mail using..."));
            }
        });

        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String appPackageName = getActivity().getPackageName();
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, "Check out the App at: https://play.google.com/store/apps/details?id=" + appPackageName);
                sendIntent.setType("text/plain");
                getActivity().startActivity(sendIntent);
            }
        });

        rate_us.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("https://play.google.com/store/apps/details?id=" + getActivity().getPackageName()));
                startActivity(intent);
            }
        });

        if (Paper.book().exist("IMAGE_LOADING_FLAG"))
            if (Paper.book().read("IMAGE_LOADING_FLAG")) {
                mSwitch.setChecked(true);
            } else {
                mSwitch.setChecked(false);
            }

        mSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    // ToastMsg.shortMsg(getContext(), "Checked");
                    Paper.book().write("IMAGE_LOADING_FLAG", true);

                } else {
                    Paper.book().write("IMAGE_LOADING_FLAG", false);


                }
            }
        });
        return view;
    }

}

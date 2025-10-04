package com.kshitiz.samachar24.presenter.ui;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.kshitiz.samachar24.R;
import com.kshitiz.samachar24.model.ItemItem;
import com.kshitiz.samachar24.util.Parser;

import java.net.URI;
import java.net.URISyntaxException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Objects;

import io.paperdb.Paper;


public class DetailFeed extends AppCompatActivity {
    private static final String TAG = DetailFeed.class.getSimpleName();
    private ItemItem entry;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);

        setContentView(R.layout.activity_detail_feed);
        entry = (ItemItem) getIntent().getSerializableExtra("ENTRY");
        Toolbar toolbar = findViewById(R.id.toolbar_detail_view);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


        TextView title = findViewById(R.id.title_detail);
        TextView date = findViewById(R.id.date_detail);
        TextView content = findViewById(R.id.content_detail);
        ImageView imageView = findViewById(R.id.detailImageView);
        TextView author = findViewById(R.id.authorTv);

        Log.d("DETAIL FEEDS", "Entry : " + entry.getTitle());
        title.setText(entry.getTitle());

        // date.setText(String.format("%s", Parser.convertLongDateToAgoString(Date.parse(entry.getPubDate()), System.currentTimeMillis())));
        Log.i(TAG, "" + entry.getEncoded());

        if (entry.getEncoded() != null)
            content.setText(Html.fromHtml(entry.getEncoded(), Parser.EMPTY_IMAGE_GETTER, null));
        else if (entry.getDescription() != null) {
            content.setText(Html.fromHtml(entry.getDescription(), Parser.EMPTY_IMAGE_GETTER, null));
        }

        try {
            if (entry.getLink() != null)
                author.setText(getDomainName(entry.getLink()));
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        if (Paper.book().exist("IMAGE_LOADING_FLAG")) {
            if (Paper.book().read("IMAGE_LOADING_FLAG")) {
                Glide.with(getApplicationContext())
                        .load(Parser.parseImg(entry.getEncoded())).apply(new RequestOptions().placeholder(R.drawable.ic_img_placeholder).error(R.drawable.ic_img_placeholder))
                        .into(imageView);

            } else {
                //dont load image
            }
        } else
            Glide.with(getApplicationContext())
                    .load(Parser.parseImg(entry.getEncoded())).apply(new RequestOptions().placeholder(R.drawable.ic_img_placeholder).error(R.drawable.ic_img_placeholder))
                    .into(imageView);


        @SuppressLint("SimpleDateFormat") DateFormat d1 = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss zzz");
        @SuppressLint("SimpleDateFormat") DateFormat d2 = new SimpleDateFormat("dd MMM yyyy");
        try {
            date.setText(d2.format(Objects.requireNonNull(d1.parse(entry.getPubDate()))));
        } catch (ParseException e) {
            e.printStackTrace();
        }


    }


    public static String getDomainName(String url) throws URISyntaxException {
        URI uri = new URI(url);
        String domain = uri.getHost();
        return domain.startsWith("www.") ? domain.substring(4) : domain;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.detail_feed_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menu_detail_share) {
            Intent shareIntent = new Intent();
            shareIntent.setAction(Intent.ACTION_SEND);
            shareIntent.setType("text/plain");
            shareIntent.putExtra(Intent.EXTRA_SUBJECT, entry.getTitle());
            shareIntent.putExtra(Intent.EXTRA_TEXT, entry.getLink());
            startActivity(Intent.createChooser(shareIntent, getResources().getText(R.string.send_to)));
            return true;
        } else if (item.getItemId() == R.id.menu_detail_open_in_web) {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse(entry.getLink()));
            Intent chooser = Intent.createChooser(intent, "Select a browse");
// Verify the original intent will resolve to at least one activity
            if (intent.resolveActivity(getPackageManager()) != null) {
                startActivity(chooser);
            }
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}

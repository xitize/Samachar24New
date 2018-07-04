package com.kshitiz.samachar24.presenter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.kshitiz.samachar24.R;
import com.kshitiz.samachar24.model.ItemItem;
import com.kshitiz.samachar24.presenter.ui.DetailFeed;
import com.kshitiz.samachar24.util.Parser;


import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;
import java.util.List;

import io.paperdb.Paper;

public class CVAdapter extends RecyclerView.Adapter<CVAdapter.ViewHolder> {
    private final Context context;
    private final List<ItemItem> itemItemList;

    public CVAdapter(Context context, List<ItemItem> itemItemsList) {
        this.context = context;
        this.itemItemList = itemItemsList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_card_view, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        final ItemItem itemItem = itemItemList.get(position);
        String url = Parser.parseImg(itemItem.getEncoded());
        holder.title.setText(itemItem.getTitle());

        try {
            holder.source.setText(Parser.capitalize(String.format("%s", Parser.getSource(itemItem.getLink()))));
        } catch (MalformedURLException e1) {
            e1.printStackTrace();
        }

        holder.date.setText(String.format("%s", Parser.convertLongDateToAgoString(Date.parse(itemItem.getPubDate()), System.currentTimeMillis()))); //to set date time in '3 minutes ago' like
        holder.date.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);


        if (Paper.book().exist("IMAGE_LOADING_FLAG")) {
            if (Paper.book().read("IMAGE_LOADING_FLAG")) {
                Glide.with(context)
                        .load(url).apply(new RequestOptions().error(R.drawable.ic_img_placeholder).placeholder(R.drawable.ic_img_placeholder))
                        .into(holder.imageView);

            } else {
                //dont load image
            }
        } else
            Glide.with(context)
                    .load(url).apply(new RequestOptions().error(R.drawable.ic_img_placeholder).placeholder(R.drawable.ic_img_placeholder))
                    .into(holder.imageView);


        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DetailFeed.class);
                intent.putExtra("ENTRY", itemItem);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return itemItemList.size();
    }

    private String convertImgUrl(String url) throws MalformedURLException {
        if (url != null && url.startsWith("http://") || url != null && url.startsWith("https://")) {
            if (url.toLowerCase().contains(".png".toLowerCase())) {
                URL url1 = new URL(url);
                String tempUrl = url1.getHost() + ".rsz.io" + url1.getPath() + "?format=jpg";
                url = "http://images.weserv.nl/?url=" + tempUrl + "&q=80";
                Log.i("PNG TAG", "" + url);
            } else {
                url = "http://images.weserv.nl/?url=" + url.replace("http://", "") + "&q=80";
                Log.i("TAG", " String to be shows : " + url);
            }
        } else Log.i("TAG", " String is null");
        return url;
    }


    class ViewHolder extends RecyclerView.ViewHolder {
        final TextView title;
        final TextView date;
        final CardView cardView;
        final ImageView imageView;
        final TextView source;

        ViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            date = itemView.findViewById(R.id.date);
            imageView = itemView.findViewById(R.id.image_view);
            source = itemView.findViewById(R.id.source);
            cardView = itemView.findViewById(R.id.cardView);
        }
    }
}
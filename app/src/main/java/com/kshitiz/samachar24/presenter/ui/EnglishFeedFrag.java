package com.kshitiz.samachar24.presenter.ui;


import static io.paperdb.Paper.book;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.gson.Gson;
import com.kshitiz.samachar24.FeedsUrl;
import com.kshitiz.samachar24.R;
import com.kshitiz.samachar24.model.ItemItem;
import com.kshitiz.samachar24.model.ResponseFeed;
import com.kshitiz.samachar24.presenter.CVAdapter;
import com.kshitiz.samachar24.util.CheckInternet;
import com.kshitiz.samachar24.util.Parser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class EnglishFeedFrag extends Fragment {

    private SwipeRefreshLayout swipeRefreshLayout;
    CVAdapter cvAdapter;
    private final ArrayList<String> rss = FeedsUrl.getEnglishFeedsUrl();
    static final String TAG = MainActivity.class.getSimpleName();
    List<ItemItem> tempItems = new ArrayList<>();


    public EnglishFeedFrag() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_english_feed, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        swipeRefreshLayout = view.findViewById(R.id.swipeRefresh_layout);
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(llm);
        cvAdapter = new CVAdapter(getActivity(), tempItems);
        recyclerView.setAdapter(cvAdapter);
        tempItems.clear();


        if (book().exist("CACHE_OFFLINE_FEED_ENGLISH")) {
            pullFromOffline();
        } else {
            //first run
            swipeRefreshLayout.post(this::fetchFeeds);
        }

        swipeRefreshLayout.setOnRefreshListener(() -> {
            if (CheckInternet.isNetworkAvailable(getActivity()))
                fetchFeeds();
            else {
                pullFromOffline();
                swipeRefreshLayout.setRefreshing(false);
            }
        });


        return view;
    }


    private void pullFromOffline() {
        tempItems.addAll(book().read("CACHE_OFFLINE_FEED_ENGLISH"));
        cvAdapter.notifyDataSetChanged();
    }

    private void fetchFeeds() {
        final OkHttpClient okHttpClient = new OkHttpClient();
        okHttpClient.dispatcher().setMaxRequests(1);
        swipeRefreshLayout.setRefreshing(true);
        Observable<List<ItemItem>> myObservable = Observable.create(emitter -> {
            final List<ItemItem> tempList = new ArrayList<>();
            for (int i = 0; i < rss.size(); i++) {
                Request request = new Request.Builder().url(rss.get(i))
                        .build();

                final int finalI = i;
                okHttpClient.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(@NonNull Call call, @NonNull IOException e) {
                       /* emitter.onNext(Parser.polishFeed(tempList));  //single all List<Item>
                        emitter.onComplete();*/
                    }

                    @Override
                    public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                        Log.d("TAG", "feeds pulled is " + finalI);
                        Gson gson = new Gson();
                        assert response.body() != null;
                        ResponseFeed responseFeed = gson.fromJson(response.body().string(), ResponseFeed.class);
                        if (responseFeed.getQuery().getResults() != null)
                            tempList.addAll(responseFeed.getQuery().getResults().getRss().getChannel().getItem());

                        Log.d(TAG, "Feeds in a url  number  " + finalI + " is " + responseFeed.getQuery().getResults().getRss().getChannel().getItem().size());

                        /*check the condition if disconnects in middle of downloading*/
                        if (finalI == rss.size() - 1) {
                            emitter.onNext(Parser.polishFeedWithEnglish(tempList));  //single all List<Item>
                            emitter.onComplete();
                        }
                    }
                });

            }

        });

        myObservable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new DisposableObserver<List<ItemItem>>() {

            @Override
            public void onNext(List<ItemItem> itemItems) {
                Log.d(TAG, "Items Size : " + itemItems.size());
                tempItems.clear();
                tempItems.addAll(itemItems);
                cvAdapter.notifyDataSetChanged();
                book().write("CACHE_OFFLINE_FEED_ENGLISH", tempItems);

            }

            @Override
            public void onError(Throwable e) {
                Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_SHORT).show();
                swipeRefreshLayout.setRefreshing(false);

            }

            @Override
            public void onComplete() {
                swipeRefreshLayout.setRefreshing(false);


            }
        });
    }


}

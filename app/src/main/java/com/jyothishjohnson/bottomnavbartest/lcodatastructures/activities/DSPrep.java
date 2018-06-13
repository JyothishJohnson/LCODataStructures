package com.jyothishjohnson.bottomnavbartest.lcodatastructures.activities;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.jyothishjohnson.bottomnavbartest.lcodatastructures.R;
import com.jyothishjohnson.bottomnavbartest.lcodatastructures.data.CategoryDisplayAdapter;
import com.jyothishjohnson.bottomnavbartest.lcodatastructures.data.Model;
import com.jyothishjohnson.bottomnavbartest.lcodatastructures.data.QuestionLoader;
import com.yarolegovich.discretescrollview.DSVOrientation;
import com.yarolegovich.discretescrollview.DiscreteScrollView;
import com.yarolegovich.discretescrollview.InfiniteScrollAdapter;
import com.yarolegovich.discretescrollview.transform.ScaleTransformer;

import java.util.ArrayList;
import java.util.List;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class DSPrep extends AppCompatActivity implements
        DiscreteScrollView.OnItemChangedListener,View.OnClickListener,
        LoaderManager.LoaderCallbacks<List<Model>>{
    private InfiniteScrollAdapter infiniteScrollAdapter;
    private DiscreteScrollView discreteScrollView;
    private ArrayList<Model> modelArrayList;
    private static final int NEWS_LOADER_ID = 1;
    private CardView bannerAd;


    private static final String jsonUrl = "https://learncodeonline.in/api/android/datastructure.json";

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dsprep);
        bannerAd = findViewById(R.id.bannerAdPrep);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



        initViews();
        modelArrayList = new ArrayList<>();
        discreteScrollView.addOnItemChangedListener(this);

        ConnectivityManager connMgr = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isConnected()) {

            LoaderManager loaderManager = getSupportLoaderManager();
            loaderManager.initLoader(NEWS_LOADER_ID, null,this);

        } else {

            Snackbar.make(findViewById(android.R.id.content),"No Network Connectivity",
                    2000).show();
        }
        bannerAd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goToMarket = new Intent(Intent.ACTION_VIEW, Uri.parse("https://courses.learncodeonline.in/"));
                // To count with Play market backstack, After pressing back button,
                // to taken back to our application, we need to add following flags to intent.
                goToMarket.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY |
                        Intent.FLAG_ACTIVITY_NEW_DOCUMENT |
                        Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
                try {
                    startActivity(goToMarket);
                } catch (ActivityNotFoundException e) {
                    startActivity(new Intent(Intent.ACTION_VIEW,
                            Uri.parse("https://courses.learncodeonline.in/")));
                }
            }
        });
    }


    private void initViews() {
        discreteScrollView = findViewById(R.id.recycler_viewDS);
        discreteScrollView.setHasFixedSize(true);
        discreteScrollView.setOrientation(DSVOrientation.VERTICAL);
    }


    @Override
    public void onClick(View view) {

    }

    @Override
    public void onCurrentItemChanged(@Nullable RecyclerView.ViewHolder viewHolder, int adapterPosition) {

    }

    @NonNull
    @Override
    public android.support.v4.content.Loader<List<Model>> onCreateLoader(int id, @Nullable Bundle args) {
        return new QuestionLoader(getApplicationContext(), jsonUrl);
    }

    @Override
    public void onLoadFinished(@NonNull android.support.v4.content.Loader<List<Model>> loader, List<Model> data) {

        modelArrayList.clear();
        if(data!=null && !data.isEmpty()){
            modelArrayList.addAll(data);


            infiniteScrollAdapter = InfiniteScrollAdapter.wrap(new CategoryDisplayAdapter(modelArrayList));
            discreteScrollView.setAdapter(infiniteScrollAdapter);
            discreteScrollView.setItemTransformer(new ScaleTransformer.Builder()
                    .setMinScale(0.8f)
                    .build());


        }
        else {
            if(data.isEmpty()){
                Toast.makeText(getApplicationContext(),"Empty",Toast.LENGTH_SHORT).show();

            }
            Toast.makeText(getApplicationContext(),"NULL",Toast.LENGTH_SHORT).show();

        }

    }

    @Override
    public void onLoaderReset(@NonNull Loader<List<Model>> loader) {
        modelArrayList.clear();
    }
}

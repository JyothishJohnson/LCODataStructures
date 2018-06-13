package com.jyothishjohnson.bottomnavbartest.lcodatastructures.activities;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.jyothishjohnson.bottomnavbartest.lcodatastructures.R;
import com.jyothishjohnson.bottomnavbartest.lcodatastructures.data.CoursesModel;
import com.jyothishjohnson.bottomnavbartest.lcodatastructures.data.MyAdapter;
import com.yarolegovich.discretescrollview.DSVOrientation;
import com.yarolegovich.discretescrollview.DiscreteScrollView;
import com.yarolegovich.discretescrollview.InfiniteScrollAdapter;
import com.yarolegovich.discretescrollview.transform.ScaleTransformer;

import java.util.ArrayList;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, DiscreteScrollView.OnItemChangedListener, View.OnClickListener {

    private InfiniteScrollAdapter infiniteScrollAdapter;
    private DiscreteScrollView discreteScrollView;
    private ArrayList<CoursesModel> mArrayList;
    private CardView dataStructuresPrep,bannerAd;

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar =  findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        dataStructuresPrep = findViewById(R.id.dataStructuresPrep);
        bannerAd = findViewById(R.id.bannerAd);


        DrawerLayout drawer =  findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView =  findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        initViews();
        discreteScrollView.addOnItemChangedListener(this);

        ConnectivityManager connMgr = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isConnected()) {

        } else {

            Snackbar.make(findViewById(android.R.id.content),"No Network Connectivity",
                    2000).show();
        }
        dataStructuresPrep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),DSPrep.class);
                startActivity(i);
            }
        });
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
        loadData();
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_website) {

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
                        Uri.parse("https://courses.learncodeonline.in")));
            }

            // Handle the camera action
        } else if (id == R.id.nav_rate) {

            Intent goToMarket = new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=in.learncodeonline.lco"));
            // To count with Play market backstack, After pressing back button,
            // to taken back to our application, we need to add following flags to intent.
            goToMarket.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY |
                    Intent.FLAG_ACTIVITY_NEW_DOCUMENT |
                    Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
            try {
                startActivity(goToMarket);
            } catch (ActivityNotFoundException e) {
                startActivity(new Intent(Intent.ACTION_VIEW,
                        Uri.parse("https://play.google.com/store/apps/details?id=in.learncodeonline.lco")));
            }

        }else if(id== R.id.nav_youtube){

            Intent goToMarket = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/user/hiteshitube"));
            // To count with Play market backstack, After pressing back button,
            // to taken back to our application, we need to add following flags to intent.
            goToMarket.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY |
                    Intent.FLAG_ACTIVITY_NEW_DOCUMENT |
                    Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
            try {
                startActivity(goToMarket);
            } catch (ActivityNotFoundException e) {
                startActivity(new Intent(Intent.ACTION_VIEW,
                        Uri.parse("https://www.youtube.com/user/hiteshitube")));
            }


        }
        else if (id == R.id.nav_share) {

            Intent sharingIntent = new Intent(Intent.ACTION_SEND);
            sharingIntent.setType("text/plain");
            StringBuilder sb = new StringBuilder();
            sb.append("https://play.google.com/store/apps/details?id=in.learncodeonline.lco");
            // sharingIntent.addFlags(activityFlags.ClearWhenTaskReset);
            sharingIntent.putExtra(Intent.EXTRA_SUBJECT, "Test");
            sharingIntent.putExtra(Intent.EXTRA_TEXT, sb.toString());
            startActivity(Intent.createChooser(sharingIntent, "Test"));

        } else if (id == R.id.nav_email) {

            Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto","info@learncodeonline.in", null));
            startActivity(Intent.createChooser(emailIntent, "Send email..."));

        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public void onCurrentItemChanged(@Nullable RecyclerView.ViewHolder viewHolder, int adapterPosition) {

    }

    private void initViews(){
        discreteScrollView= findViewById(R.id.recycler_viewCourses);
        discreteScrollView.setHasFixedSize(true);
        discreteScrollView.setOrientation(DSVOrientation.HORIZONTAL);

    }

    private void loadData(){
        mArrayList = new ArrayList<>();

        mArrayList.add(new CoursesModel("Javascript-for-2018-developer",getResources().getString(R.string.c1),
                "Free","49 Lessons",getResources().getString(R.string.u1)));
        mArrayList.add(new CoursesModel("Sketch-Fun with icons",getResources().getString(R.string.c2),
                "Free","7 Lessons",getResources().getString(R.string.u2)));
        mArrayList.add(new CoursesModel("Machine-Learning-Bootcamp",getResources().getString(R.string.c3),
                "2400 INR","87 Lessons",getResources().getString(R.string.u3)));
        mArrayList.add(new CoursesModel("BackEnd-Web-Development-with-Django",getResources().getString(R.string.c4),
                "799 INR","43 Lessons",getResources().getString(R.string.u4)));
        mArrayList.add(new CoursesModel("Complete-Front-End-Development",getResources().getString(R.string.c5),
                "1199 INR","128 Lessons",getResources().getString(R.string.u5)));
        mArrayList.add(new CoursesModel("React-Native-Design",getResources().getString(R.string.c6),
                "799 INR","47 Lessons",getResources().getString(R.string.u6)));
        mArrayList.add(new CoursesModel("Firebase ChapApp-Android",getResources().getString(R.string.c7),
                "899 INR","40 Lessons",getResources().getString(R.string.u7)));
        mArrayList.add(new CoursesModel("Ethical-Hacking-Master-Course",getResources().getString(R.string.c8),
                "799 INR","66 Lessons",getResources().getString(R.string.u8)));

        infiniteScrollAdapter = InfiniteScrollAdapter.wrap(new MyAdapter(mArrayList,getApplicationContext()));
        discreteScrollView.setAdapter(infiniteScrollAdapter);
        discreteScrollView.setItemTransformer(new ScaleTransformer.Builder()
                .setMinScale(0.8f)
                .build());

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {

            final AlertDialog.Builder builder= new AlertDialog.Builder(MainActivity.this);
            builder.setMessage("Do You Want To Exit?");
            builder.setCancelable(true);
            builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.cancel();
                }
            });

            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    finish();
                }
            });
            AlertDialog dialog = builder.create();
            dialog.show();
        }

    }

}

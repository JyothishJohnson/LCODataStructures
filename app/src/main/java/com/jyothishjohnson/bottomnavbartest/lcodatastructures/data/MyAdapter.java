package com.jyothishjohnson.bottomnavbartest.lcodatastructures.data;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jyothishjohnson.bottomnavbartest.lcodatastructures.R;

import java.util.ArrayList;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;


public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private ArrayList<CoursesModel> mArrayList;
    private Context context;

    public MyAdapter(ArrayList<CoursesModel> mArrayList, Context context) {
        this.mArrayList = mArrayList;
        this.context =context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.listitem,
                parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {

        holder.courseNameView.setText(mArrayList.get(position).getCourseName());
        holder.courseLessonCountView.setText(mArrayList.get(position).getCourseLessons());
        holder.courseFeesView.setText(mArrayList.get(position).getCourseFees());
        holder.cardViewSingleCourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goToMarket = new Intent(Intent.ACTION_VIEW, Uri.parse(mArrayList.get(position).getCourseUrl()));
                // To count with Play market backstack, After pressing back button,
                // to taken back to our application, we need to add following flags to intent.
                goToMarket.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY |
                        Intent.FLAG_ACTIVITY_NEW_DOCUMENT |
                        Intent.FLAG_ACTIVITY_MULTIPLE_TASK |FLAG_ACTIVITY_NEW_TASK);
                try {
                    context.startActivity(goToMarket);
                } catch (ActivityNotFoundException e) {
                    context.startActivity(new Intent(Intent.ACTION_VIEW,
                            Uri.parse(mArrayList.get(position).getCourseUrl())));
                }
            }
        });

        Glide.with(context).load(mArrayList.get(position).getCourseImgUrl()).into(holder.courseImageView);

    }


    @Override
    public int getItemCount() {
        return mArrayList.size();
    }


    class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView courseNameView,courseFeesView,courseLessonCountView;
        private CardView cardViewSingleCourse;
        private ImageView courseImageView;
        MyViewHolder(View view) {
            super(view);
            courseLessonCountView = view.findViewById(R.id.description);
            courseNameView = view.findViewById(R.id.courseName);
            cardViewSingleCourse = view.findViewById(R.id.cardViewSingleCourse);
            courseFeesView = view.findViewById(R.id.courseCost);
            courseImageView = view.findViewById(R.id.courseImage);

        }
    }


}

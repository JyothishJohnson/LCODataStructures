package com.jyothishjohnson.bottomnavbartest.lcodatastructures.data;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jyothishjohnson.bottomnavbartest.lcodatastructures.Demo;
import com.jyothishjohnson.bottomnavbartest.lcodatastructures.R;

import java.util.ArrayList;

/**
 * Created by jyothish on 18/5/18.
 */

public class CategoryDisplayAdapter extends
        RecyclerView.Adapter<CategoryDisplayAdapter.CategoryDisplayHolder> {

    private ArrayList<Model> mArrayList;


    public CategoryDisplayAdapter(ArrayList<Model> mArrayList) {
        this.mArrayList = mArrayList;
    }

    @NonNull
    @Override
    public CategoryDisplayHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.listitemquestions, parent, false);
        return new CategoryDisplayHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final CategoryDisplayHolder holder, int position) {

        holder.questions.setText(mArrayList.get(position).getQuestion());
        holder.answers.setText(mArrayList.get(position).getAnswer());
        holder.questions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(view.getContext(), Demo.class);
                view.getContext().startActivity(i);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mArrayList.size();
    }

    class CategoryDisplayHolder extends RecyclerView.ViewHolder{
        private TextView questions,answers;

        CategoryDisplayHolder(View view) {
            super(view);
            questions = view.findViewById(R.id.dsQuestion);
            answers = view.findViewById(R.id.dsAnswer);
        }
    }
}

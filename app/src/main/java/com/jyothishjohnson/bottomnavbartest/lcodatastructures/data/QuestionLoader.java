package com.jyothishjohnson.bottomnavbartest.lcodatastructures.data;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

import java.util.List;

/**
 * Created by jyothish on 5/6/18.
 */

public class QuestionLoader extends AsyncTaskLoader<List<Model>> {

    private String mUrl;

    public QuestionLoader(Context context, String url) {
        super(context);
        mUrl = url;
    }

    @Override
    public List<Model> loadInBackground() {
        if (mUrl == null) {
            return null;
        }

        List<Model> responses = FetchData.fetchQuestions(mUrl);
        return  responses;

    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }
}

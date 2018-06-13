package com.jyothishjohnson.bottomnavbartest.lcodatastructures.data;

import android.text.TextUtils;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jyothish on 5/6/18.
 */

public class FetchData {

    private static final String LOG_TAG = FetchData.class.getSimpleName();



    public FetchData() {
    }

    private static List<Model> extractFromJson(String newsJson) {

        ArrayList<Model> responses= new ArrayList<>();

        // If the JSON string is empty or null, then return early.
        if (TextUtils.isEmpty(newsJson)) {
            return null;
        }

        try {
            JSONObject baseJsonResponse = new JSONObject(newsJson);

            JSONArray featureArray = baseJsonResponse.getJSONArray("questions");
            // If there are results in the features array
            if (featureArray.length() > 0) {

                for(int i=0;i<featureArray.length();i++) {
                    JSONObject firstFeature = featureArray.getJSONObject(i);


                    String question = firstFeature.getString("question");
                    String answer = firstFeature.getString("Answer");

                    Model response= new Model(question,answer);

                    responses.add(response);

                }
            }
        } catch (JSONException e) {

            e.printStackTrace();
        }
        return responses;
    }

    private static URL createUrl(String stringUrl) {
        URL url = null;
        try {
            url = new URL(stringUrl);
        } catch (MalformedURLException e) {
               Log.e(LOG_TAG, "Problem building the URL ", e);
        }
        return url;
    }

    public static List<Model> fetchQuestions(String requestUrl) {
        // Create URL object
        URL url = createUrl(requestUrl);

        // Perform HTTP request to the URL and receive a JSON response back
        String jsonResponse = null;
        try {
            jsonResponse = makeHttpRequest(url);
        } catch (IOException e) {
            Log.e(LOG_TAG, "Problem making the HTTP request.", e);
        }
        List<Model> questions = extractFromJson(jsonResponse);

        return questions;
    }

    private static String readFromStream(InputStream inputStream) throws IOException {
        StringBuilder output = new StringBuilder();
        if (inputStream != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while (line != null) {
                output.append(line);
                line = reader.readLine();
            }
        }
        return output.toString();
    }

    private  static String makeHttpRequest(URL url) throws IOException {
        String jsonResponse ="";
        if(url==null){
            return jsonResponse;
        }

        HttpURLConnection urlConnection= null;
        InputStream inputStream=null;

        try {

            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setReadTimeout(10000 /* milliseconds */);
            urlConnection.setConnectTimeout(15000 /* milliseconds */);
            urlConnection.connect();


            if (urlConnection.getResponseCode() == 200) {
                inputStream = urlConnection.getInputStream();
                jsonResponse = readFromStream(inputStream);
            } else {
                Log.e(LOG_TAG, "Error response code: " + urlConnection.getResponseCode());
            }
        }
        catch (Exception e){
            Log.e(LOG_TAG, "Problem retrieving the earthquake JSON results.", e);
            e.printStackTrace();
        }
        finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (inputStream != null) {
                // function must handle java.io.IOException here
                inputStream.close();
            }
        }
        return jsonResponse;
    }

}

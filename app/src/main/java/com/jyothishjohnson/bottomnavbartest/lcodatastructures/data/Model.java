package com.jyothishjohnson.bottomnavbartest.lcodatastructures.data;

/**
 * Created by jyothish on 12/6/18.
 */

public class Model {

    private String question,answer;

    public Model(String question, String answer) {
        this.question = question;
        this.answer = answer;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}

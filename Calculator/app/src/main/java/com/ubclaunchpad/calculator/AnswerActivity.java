package com.ubclaunchpad.calculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class AnswerActivity extends AppCompatActivity {
    private TextView answerText;
    private Button goBackButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answer);

        // initialize fields
        answerText = (TextView) findViewById(R.id.answer);
        goBackButton = (Button) findViewById(R.id.goBack);

        // set answer
        double answer = getIntent().getDoubleExtra("answer", 0);
        String answerString = "" + answer;
        answerText.setText(answerString);
    }

    public void goBackToCalculator(View view) {
        finish();
    }
}

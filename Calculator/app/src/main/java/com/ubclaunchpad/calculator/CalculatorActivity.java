package com.ubclaunchpad.calculator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class CalculatorActivity extends AppCompatActivity implements View.OnClickListener{
    private final static String TAG = CalculatorActivity.class.getSimpleName();
    private EditText firstInputText;
    private EditText secondInputText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);

        firstInputText = (EditText) findViewById(R.id.firstInput);
        secondInputText = (EditText) findViewById(R.id.secondInput);

    }

    /**
     * This implementation of the click listener is for the view found in res/layout/activity_calculator
     * The functions in general should grab the appropriate inputs, and if they are valid, computes the answer.
     * The answer should be displayed in a second activity labelled "AnswerActivity"
     * @param v
     */
    @Override
    public void onClick(View v) {
        Boolean validFirstInput = isValidFirstInput(v);
        Boolean validSecondInput = isValidSecondInput(v);

        if(validFirstInput && validSecondInput) {
            double firstInput = getInput(firstInputText);
            double secondInput = getInput(secondInputText);

            switch (v.getId())
            {
                case R.id.operation_add:
                {
                    double answer = firstInput + secondInput;
                    goToAnswerActivity(answer);
                    break;
                }
                case R.id.operation_sub:
                {
                    double answer = firstInput - secondInput;
                    goToAnswerActivity(answer);
                    break;
                }
                case R.id.operation_multi:
                {
                    double answer = firstInput * secondInput;
                    goToAnswerActivity(answer);
                    break;
                }
                case R.id.operation_div:
                {
                    double answer = firstInput / secondInput;
                    goToAnswerActivity(answer);
                    break;
                }
                case R.id.power: // first input to the power of the second input
                {
                    double answer = Math.pow(firstInput, secondInput);
                    goToAnswerActivity(answer);
                }
                case R.id.root: // calculate the second input'th root of the first input
                {
                    double power = 1/secondInput;
                    double answer = Math.pow(firstInput, power);
                    goToAnswerActivity(answer);
                }
                default:
                {
                    Toast.makeText(this, "Click not implmented yet", Toast.LENGTH_LONG).show();
                    Log.e(TAG, "View id: " + v.getId() + " click not implemented yet");
                    break;
                }
            }
        } else if (!validFirstInput && !validSecondInput) {
            Toast.makeText(this, "Both inputs are not valid", Toast.LENGTH_SHORT).show();
        } else if (!validFirstInput && validSecondInput) {
            Toast.makeText(this, "First input is not valid", Toast.LENGTH_SHORT).show();
        } else if (validFirstInput && !validSecondInput) {
            Toast.makeText(this, "Second input is not valid", Toast.LENGTH_SHORT).show();
        }


    }

    private void goToAnswerActivity(double answer) {
        Intent intent = new Intent(this, AnswerActivity.class);
        intent.putExtra("answer", answer);
        startActivity(intent);
    }

    private Boolean isValidFirstInput(View v) {
        try {
            getInput(firstInputText);
            return true;
        } catch (Exception e) {
            return false;
        }

    }

    private Boolean isValidSecondInput(View v) {
        try {
            getInput(secondInputText);
            return true;
        } catch (Exception e) {
            return false;
        }

    }

    private Double getInput(EditText input) {
        Editable inputEditable = input.getText();
        String inputString = inputEditable.toString();
        Double inputNum = Double.parseDouble(inputString);

        return inputNum;
    }
}

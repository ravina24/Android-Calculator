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
                    Intent intent = new Intent(this, AnswerActivity.class);
                    intent.putExtra("answer", answer);
                    startActivity(intent);
                    break;
                }
                case R.id.operation_sub:
                {
                    //TODO subtract function
                    break;
                }
                case R.id.operation_multi:
                {
                    //TODO multiply function
                    break;
                }
                case R.id.operation_div:
                {
                    //TODO divide function
                    break;
                }
                //TODO any extra implmentations (optional)
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

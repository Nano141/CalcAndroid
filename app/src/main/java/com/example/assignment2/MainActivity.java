package com.example.assignment2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Configuration;
import android.os.Bundle;
import android.renderscript.Sampler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements GridFragment.InputManager {

    static DecimalFormat df = new DecimalFormat("#.####");
    static String historyOr;
    static String outputOr;
    static boolean firstOp = true;
    static Double value1 = 0.0;
    static Double value2 = 0.0;
    static Double result = 0.0;
    static String op;

    //TODO: Add clear history button and edit display
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView hist = findViewById(R.id.historyText);
        TextView out = findViewById(R.id.resultText);
        int orientation = getResources().getConfiguration().orientation;
        if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            // In landscape
            hist.setText(historyOr);
            out.setText(outputOr);
            hist.setTextSize(24f);
            out.setTextSize(24f);
        } else {
            // In portrait
            hist.setText(historyOr);
            out.setText(outputOr);
        }
    }


    @Override
    public void getInput(String text) {
        TextView txt = findViewById(R.id.resultText);
        TextView history = findViewById(R.id.historyText);
        history.setSelected(true);
        checkValue(text, txt, history);
    }

    public void checkValue(String input, TextView output, TextView history) {
        if (input.equals("Ca")) {
            Toast.makeText(this, "Clear All", Toast.LENGTH_SHORT).show();
            output.setText("");
            history.setText("");
            value1 = 0.0;
            value2 = 0.0;
            firstOp = true;

        } else if (input.equals("0") && output.getText().toString().equals("0")) {
            return;
        } else if (input.equals("P/N")) {

            if (output.getText().toString().equals("0")) {
                return;
            } else if (output.getText().toString().equals("")) {
                return;
            } else if (output.getText().toString().startsWith("-")) {
                output.setText(output.getText().toString().replace("-", ""));
                return;
            } else {
                output.setText("-" + output.getText().toString());
                return;
            }

        } else if (input.equals("Back") && !output.getText().toString().equals("")) {
            output.setText(output.getText().toString().substring(0, output.getText().toString().length() - 1));
        } else if (output.getText().toString().equals("") && input.equals("-")) {
            Toast.makeText(this, "Please Input number", Toast.LENGTH_SHORT).show();
            return;
        } else if ((output.getText().toString().equals("") || output.getText().toString().equals("-"))
                && (input.equals("+") || input.equals("*") || input.equals("/") || input.equals("-"))) {
            Toast.makeText(this, "Please Input number", Toast.LENGTH_SHORT).show();
            return;
        } else if (output.getText().toString().equals("0.") && (input.equals("+") || input.equals("*") || input.equals("/") ||
                input.equals("-"))) {
            Toast.makeText(this, "Please Input number", Toast.LENGTH_SHORT).show();
            return;
        } else if ((output.getText().toString().equals("") || output.getText().toString().endsWith("+") || output.getText().toString().endsWith("-") ||
                output.getText().toString().endsWith("*") || output.getText().toString().endsWith("/")) && input.equals(".")) {
            output.append("0.");
            outputOr = output.getText().toString();
            return;

        } else if (input.equals(".") && output.getText().toString().contains(".")) {
            Toast.makeText(this, "Can't put two decimal dots", Toast.LENGTH_SHORT).show();
            return;
        } else if (input.equals(".")) {
            output.append(input);
            outputOr = output.getText().toString();
            return;
        } else if (input.equals("C")) {
            output.setText("");
            outputOr = output.getText().toString();
            Toast.makeText(this, "Clear", Toast.LENGTH_SHORT).show();
            return;
        } else if (!output.getText().toString().equals("") && input.equals("0")) {
            output.append(input);
            outputOr = output.getText().toString();
            return;
        } else if (input.equals("=") && !firstOp && !output.getText().toString().equals("") && !output.getText().toString().equals("-")) {

            value2 = Double.valueOf(output.getText().toString());

            switch (op) {
                case "+": {
                    result = value1 + value2;
                    break;
                }
                case "-": {
                    result = value1 - value2;
                    break;
                }
                case "*": {
                    result = value1 * value2;
                    break;
                }
                case "/": {
                    if (value2 == 0) {
                        Toast.makeText(this, "Can't divide by ZERO Please input number", Toast.LENGTH_SHORT).show();
                        output.setText("");
                        history.setText("");
                        value1 = 0.0;
                        firstOp = true;
                        return;
                    }
                    result = value1 / value2;
                    break;
                }
            }
            history.setText(df.format(value1) + op + df.format(value2) + "=" + df.format(result));
            historyOr = history.getText().toString();
            output.setText("");
            outputOr = output.getText().toString();
            firstOp = true;
        } else if (input.equals("0") || input.equals("1") || input.equals("2") || input.equals("3") || input.equals("4") || input.equals("5") ||
                input.equals("6") || input.equals("7") || input.equals("8") || input.equals("9")) {
            output.append(input);
            outputOr = output.getText().toString();
            return;
        } else if ((!output.getText().toString().equals("")) && (input.equals("+") || input.equals("-") || input.contains("*") || input.contains("/"))) {

            if (firstOp) {
                value1 = Double.valueOf(output.getText().toString());
                op = input;
                history.setText(df.format(value1) + input);
                historyOr = history.getText().toString();
                output.setText("");
                outputOr = output.getText().toString();
                firstOp = false;
                return;
            } else {
                if (input.equals("/") && Double.valueOf(output.getText().toString()) == 0) {
                    Toast.makeText(this, "Can't divide by ZERO Please input number", Toast.LENGTH_SHORT).show();
                    output.setText("");
                    history.setText("");
                    value1 = 0.0;
                    firstOp = true;
                    return;
                }
                value2 = Double.valueOf(output.getText().toString());
                switch (op) {
                    case "+": {
                        result = value1 + value2;
                        break;
                    }
                    case "-": {
                        result = value1 - value2;
                        break;
                    }
                    case "*": {
                        result = value1 * value2;
                        break;
                    }
                    case "/": {
                        if (value2 == 0) {
                            Toast.makeText(this, "Can't divide by ZERO Please input number", Toast.LENGTH_SHORT).show();
                            output.setText("");
                            history.setText("");
                            value1 = 0.0;
                            firstOp = true;
                            return;
                        }
                        result = value1 / value2;
                        break;
                    }
                }
                history.setText(df.format(value1) + op + df.format(value2) + "=" + df.format(result) + input);
                historyOr = history.getText().toString();
                output.setText("");
                outputOr = output.getText().toString();
                op = input;
                value1 = result;

            }

        }


    }

}
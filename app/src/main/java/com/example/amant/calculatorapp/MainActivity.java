package com.example.amant.calculatorapp;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.text.Spanned;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.math.BigDecimal;
import java.text.DecimalFormat;


public class MainActivity extends AppCompatActivity {

    //Varibles for holding operands and operators
    private BigDecimal number = null; //USED BIGDECIMAL FOR BETTER PRECISION
    private String operator = "=";

    /*
    EditText for displaying numbers.
    1.) I used EditText instead of TextView because inputType works better with it and
        EditText gives more freedom to work with text.
    2.) I have disabled the focusable and enable so that its not clickable or editable using
        system keyboard.
     */
    private EditText firstNum;
    private EditText secondNum;

    /*Declaring Button variables for basic functions.*/
    private Button btnEqualTo;
    private Button btnDiv;
    private Button btnMul;
    private Button btnSub;
    private Button btnAdd;
    /*Declaring Button variables for numbers.*/
    private Button btnZero;
    private Button btnOne;
    private Button btnTwo;
    private Button btnThree;
    private Button btnFour;
    private Button btnFive;
    private Button btnSix;
    private Button btnSeven;
    private Button btnEight;
    private Button btnNine;
    /*Declaring Button variables for Advance functions.*/
    private Button btnDecimal;
    private Button btnSqrt;
    private Button btnNegative;
    private Button btnAllClear;
    private Button btnClear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*Assigning Button variables.*/
        firstNum = findViewById(R.id.firstNum);
        secondNum = findViewById(R.id.secondNum);
        btnEqualTo = findViewById(R.id.btn_result);
        btnDiv = findViewById(R.id.btn_div);
        btnMul = findViewById(R.id.btn_mul);
        btnSub = findViewById(R.id.btn_sub);
        btnAdd = findViewById(R.id.btn_add);
        btnZero = findViewById(R.id.btn_zero);
        btnOne = findViewById(R.id.btn_num_1);
        btnTwo = findViewById(R.id.btn_num_2);
        btnThree = findViewById(R.id.btn_num_3);
        btnFour = findViewById(R.id.btn_num_4);
        btnFive = findViewById(R.id.btn_num_5);
        btnSix = findViewById(R.id.btn_num_6);
        btnSeven = findViewById(R.id.btn_num_7);
        btnEight = findViewById(R.id.btn_num_8);
        btnNine = findViewById(R.id.btn_num_9);
        btnDecimal = findViewById(R.id.btn_dec);
        btnSqrt = findViewById(R.id.btn_sqrt);
        btnNegative = findViewById(R.id.btn_sign);
        btnAllClear = findViewById(R.id.btn_ac);
        btnClear = findViewById(R.id.btn_clear);

        firstNum.setText("");
        secondNum.setText("");

        /*Callback for Numbers*/
        View.OnClickListener numListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Button btn = (Button) view;
                secondNum.append(btn.getText().toString());
            }
        };

        /*Callback for Operators*/
        View.OnClickListener opListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Button btn = (Button) view;
                String op = btn.getText().toString();
                String value = secondNum.getText().toString();
                secondNum.setHint(op);
                //This done to check if the converted value is a valid numeric type.
                try {
                    BigDecimal decimalValue = new BigDecimal(value);
                    basicOperations(decimalValue, op);
                } catch (NumberFormatException e) {
                    secondNum.setText("");
                }
                operator = op;
            }
        };

        btnZero.setOnClickListener(numListener);
        btnOne.setOnClickListener(numListener);
        btnTwo.setOnClickListener(numListener);
        btnThree.setOnClickListener(numListener);
        btnFour.setOnClickListener(numListener);
        btnFive.setOnClickListener(numListener);
        btnSix.setOnClickListener(numListener);
        btnSeven.setOnClickListener(numListener);
        btnEight.setOnClickListener(numListener);
        btnNine.setOnClickListener(numListener);
        btnDecimal.setOnClickListener(numListener);
        btnEqualTo.setOnClickListener(opListener);
        btnDiv.setOnClickListener(opListener);
        btnMul.setOnClickListener(opListener);
        btnSub.setOnClickListener(opListener);
        btnAdd.setOnClickListener(opListener);
        btnSqrt.setOnClickListener(opListener);

        /*Callback function for sign (+/-)*/
        btnNegative.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String value = secondNum.getText().toString();
                if (value.length() == 0) {
                    secondNum.setText("-");
                } else if (value.length() > 10) {
                    return;
                } else {
                    //This done to check if the converted value is a valid numeric type.
                    try {
                        BigDecimal decimalValue = new BigDecimal(value);
                        BigDecimal negative = new BigDecimal(-1);
                        decimalValue = decimalValue.multiply(negative);
                        secondNum.setText(decimalValue.toString());
                    } catch (NumberFormatException e) {
                        secondNum.setText("");
                    }
                }
            }
        });
        /*Callback function for square root(√)*/
        btnSqrt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String value = secondNum.getText().toString();
                //This done to check if the converted value is a valid numeric type.
                try {
                    Double sqrt = Double.valueOf(value);
                    sqrt = Math.sqrt(sqrt);
                    secondNum.setText(sqrt.toString());
                } catch (NumberFormatException e) {
                    secondNum.setText("");
                }
            }
        });

        /*Callback function for Clear button
            Referred for callback of clear button: https://www.youtube.com/watch?v=782_MBAZRuw*/
        btnAllClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                number = null;
                firstNum.setText("");
                secondNum.setText("");
                secondNum.setHint("0");
            }
        });

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                secondNum.setText("");
                secondNum.setHint("0");
            }
        });
    }

    /*Dialog box explaining features of the calculator to the new user.*/
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.info, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_help:
                /* Referred For Custom Dialog:
                https://www.mkyong.com/android/android-custom-dialog-example/
                */
                final Dialog dialog = new Dialog(MainActivity.this);
                dialog.setContentView(R.layout.info_dialog);
                TextView infoText = dialog.findViewById(R.id.info);
                Spanned text = Html.fromHtml(getString(R.string.dialog_text));
                infoText.setText(text);

                Button dialogButtonBack = dialog.findViewById(R.id.dialogButtonBack);
                dialogButtonBack.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

                dialog.show();

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    /*Function for performing basic mathematical operations.*/
    private void basicOperations(BigDecimal value, String operation) {
        BigDecimal zero = new BigDecimal(0);
        if (number == null) {
            number = value;
        } else {
            if (operator.equals("=")) {
                operator = operation;
            }
            switch (operator) {
                case "=":
                    number = value;
                    secondNum.setText(null);
                    break;
                case "/":
                    /*Referred For setting up scale:
                    https://stackoverflow.com/questions/33889019/bigdecimal-divide-with-a-large-number-of-decimal-places*/
                    if (value.compareTo(zero) == 0) {
                        number = null;
                    } else {
                        number = number.divide(value, 9, BigDecimal.ROUND_HALF_UP).stripTrailingZeros();
                    }
                    break;
                case "*":
                    number = number.multiply(value);
                    break;
                case "-":
                    number = number.subtract(value);
                    break;
                case "+":
                    number = number.add(value);
                    break;
            }
        }
        /*Validation to check DividebyZero error and giving error and Warning to user.*/
        if (number == null) {
            firstNum.setText(R.string.error);
            Toast toast = Toast.makeText(getApplicationContext(),R.string.divide_by_zero, Toast.LENGTH_SHORT);
            toast.show();
        } else {
            Double result = number.doubleValue();
            /*Setting decimal format to avoid scientific notation.*/
            DecimalFormat decimal = new DecimalFormat("0.#########");
            firstNum.setText(decimal.format(result).toString());
            secondNum.setText("");
        }
    }
}
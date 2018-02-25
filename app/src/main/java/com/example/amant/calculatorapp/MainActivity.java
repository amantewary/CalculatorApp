package com.example.amant.calculatorapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//Using BigDecimal for better accuracy.
        private BigDecimal number = null;
        private String operator = "=";
        private EditText firstNum;
        private EditText secondNum;

        private Button btnEqualTo;
        private Button btnDiv;
        private Button btnMul;
        private Button btnSub;
        private Button btnAdd;

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

        private Button btnDecimal;
        private Button btnSqrt;
        private Button btnNegative;
        private Button btnAllClear;
        private Button btnClear;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

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

            View.OnClickListener listener = new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Button btn = (Button) view;
                    secondNum.append(btn.getText().toString());
                }
            };

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
                        Operation(decimalValue, op);
                    } catch (NumberFormatException e) {
                        secondNum.setText("");
                    }
                    operator = op;
                }
            };

            btnZero.setOnClickListener(listener);
            btnOne.setOnClickListener(listener);
            btnTwo.setOnClickListener(listener);
            btnThree.setOnClickListener(listener);
            btnFour.setOnClickListener(listener);
            btnFive.setOnClickListener(listener);
            btnSix.setOnClickListener(listener);
            btnSeven.setOnClickListener(listener);
            btnEight.setOnClickListener(listener);
            btnNine.setOnClickListener(listener);
            btnDecimal.setOnClickListener(listener);
            btnEqualTo.setOnClickListener(opListener);
            btnDiv.setOnClickListener(opListener);
            btnMul.setOnClickListener(opListener);
            btnSub.setOnClickListener(opListener);
            btnAdd.setOnClickListener(opListener);
            btnSqrt.setOnClickListener(opListener);

    }




}
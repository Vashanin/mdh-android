package com.example.vashanin.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;

import android.util.Pair;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.HashMap;
import java.util.Map;


public class MainActivity extends AppCompatActivity {
    public static Map<Pair<String, String>, Float> currencyPrices = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText firstCurrencyValue = findViewById(R.id.first_currency_input);

        firstCurrencyValue.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                EditText finalField = findViewById(R.id.second_currency_input);
                EditText firstCurrencyValue = findViewById(R.id.first_currency_input);

                String stringCurrencyValue = firstCurrencyValue.getText().toString();

                String finalText = "";
                if (stringCurrencyValue.length() > 0) {
                    double currencyValue = Double.parseDouble(firstCurrencyValue.getText().toString());
                    finalText = Double.toString(currencyValue * 2.5);
                }

                Spinner firstCurrency = findViewById(R.id.first_currency);
                String firstCurrencyName = firstCurrency.getSelectedItem().toString();

                Spinner secondCurrency = findViewById(R.id.second_currency);
                String secondCurrencyName = secondCurrency.getSelectedItem().toString();

                finalField.setText(finalText);
            }
        });
    }
}

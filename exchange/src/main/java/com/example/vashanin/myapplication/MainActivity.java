package com.example.vashanin.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;

import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.HashMap;
import java.util.Map;


public class MainActivity extends AppCompatActivity {
    public static Map<String, Double> currencyPrices = new HashMap<>();

    static {
        currencyPrices.put("USD", 1.0);
        currencyPrices.put("EUR", 0.88);
        currencyPrices.put("SEK", 0.11);
        currencyPrices.put("KRW", 1128.00);
        currencyPrices.put("UAH", 28.00);
        currencyPrices.put("GBP", 0.78);
        currencyPrices.put("JPY", 113.31);
        currencyPrices.put("CNY", 6.94);
    }

    public void convertAll() {
        try {
            EditText finalField = findViewById(R.id.second_currency_input);
            EditText firstCurrencyValue = findViewById(R.id.first_currency_input);

            String stringCurrencyValue = firstCurrencyValue.getText().toString();

            Spinner firstCurrency = findViewById(R.id.first_currency);
            String firstCurrencyName = firstCurrency.getSelectedItem().toString();

            Spinner secondCurrency = findViewById(R.id.second_currency);
            String secondCurrencyName = secondCurrency.getSelectedItem().toString();

            String finalText = "";
            if (stringCurrencyValue.length() > 0) {
                double currencyValue = Double.parseDouble(firstCurrencyValue.getText().toString());
                Double firstCurrencyRate = currencyPrices.get(firstCurrencyName);
                Double secondCurrencyRate = currencyPrices.get(secondCurrencyName);
                Double actualValue = Math.round(currencyValue / firstCurrencyRate * secondCurrencyRate * 100.0) / 100.0;
                finalText = Double.toString(actualValue);
            }

            finalField.setText(finalText);

        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText firstCurrencyValue = findViewById(R.id.first_currency_input);
        Spinner firstCurrencySpinner = findViewById(R.id.first_currency);
        Spinner secondCurrencySpinner = findViewById(R.id.second_currency);

        firstCurrencySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                convertAll();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });

        secondCurrencySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                convertAll();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });

        firstCurrencyValue.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) { convertAll(); }
        });


    }
}

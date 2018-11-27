package com.example.vashanin.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;

import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.Map;


public class MainActivity extends AppCompatActivity {
    public static Map<String, Double> currencyPrices = new HashMap<>();

    static {
        currencyPrices.put("USD", 1.0);
        currencyPrices.put("EUR", 0.88);
        currencyPrices.put("SEK", 9.08);
        currencyPrices.put("KRW", 1128.00);
        currencyPrices.put("UAH", 28.00);
        currencyPrices.put("GBP", 0.78);
        currencyPrices.put("JPY", 113.31);
        currencyPrices.put("CNY", 6.94);
    }

    public void convertAll() {
        try {
            TextView finalField = findViewById(R.id.second_currency_input);
            EditText firstCurrencyValue = findViewById(R.id.first_currency_input);

            String stringCurrencyValue = firstCurrencyValue.getText().toString();

            Spinner firstCurrency = findViewById(R.id.first_currency);
            String firstCurrencyName = firstCurrency.getSelectedItem().toString();

            Spinner secondCurrency = findViewById(R.id.second_currency);
            String secondCurrencyName = secondCurrency.getSelectedItem().toString();

            String finalText = "";
            if (stringCurrencyValue.length() > 0) {
                String postfix = "";

                double currencyValue = Double.parseDouble(firstCurrencyValue.getText().toString());

                Double firstCurrencyRate = currencyPrices.get(firstCurrencyName);
                Double secondCurrencyRate = currencyPrices.get(secondCurrencyName);

                Double actualValue = currencyValue / firstCurrencyRate * secondCurrencyRate;

                if (actualValue > 1e24) {
                    postfix = "Septillion";
                    actualValue /= 1e24;
                } else if (actualValue > 1e21) {
                    postfix = "Sextillion";
                    actualValue /= 1e21;
                } else if (actualValue > 1e18) {
                    postfix = "Quintillion";
                    actualValue /= 1e18;
                } else if (actualValue > 1e15) {
                    postfix = "Quadrillion";
                    actualValue /= 1e15;
                } else if (actualValue > 1e12) {
                    postfix = "Trillion";
                    actualValue /= 1e12;
                } else if (actualValue > 1e9) {
                    postfix = "Billion";
                    actualValue /= 1e9;
                } else if (actualValue > 1e6) {
                    postfix = "Million";
                    actualValue /= 1e6;
                }
                actualValue = Math.round(actualValue * 1000.0) / 1000.0;

                finalText = Double.toString(actualValue) + " " + postfix;
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

    /** Called when the user taps the button */
    public void seeAllCurrencies(View view) {
        Intent intent = new Intent(this, SecondPageActivity.class);
//        String message = "Hello";
//        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }

    public static void hideSoftKeyboard(Activity activity) {
        InputMethodManager inputMethodManager =
                (InputMethodManager) activity.getSystemService(
                        Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(
                activity.getCurrentFocus().getWindowToken(), 0);
    }
}

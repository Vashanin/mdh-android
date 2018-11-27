package com.example.vashanin.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SecondPageActivity extends AppCompatActivity {
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_page);

        Spinner mySpinner = findViewById(R.id.currency_to_display_spinner);

        mySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                Spinner mySpinner = findViewById(R.id.currency_to_display_spinner);

                String currencyType = mySpinner.getSelectedItem().toString();

                TextView[] viewsList = new TextView[] {
                    findViewById(R.id.textViewCurrency1),
                    findViewById(R.id.textViewCurrency2),
                    findViewById(R.id.textViewCurrency3),
                    findViewById(R.id.textViewCurrency4),
                    findViewById(R.id.textViewCurrency5),
                    findViewById(R.id.textViewCurrency6),
                    findViewById(R.id.textViewCurrency7)
                };

                int i = 0;
                for (Map.Entry<String, Double> entry : currencyPrices.entrySet())
                {
                    if (!entry.getKey().equals(currencyType)) {
                        Double value = Math.round(currencyPrices.get(currencyType)
                                        / entry.getValue()
                                        * 10000.0) / 10000.0;
                        viewsList[i].setText(entry.getKey()
                                + "/"
                                + currencyType
                                + "    "
                                + String.format("%.6f", value));
                        i++;
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) { }

        });
    }
}
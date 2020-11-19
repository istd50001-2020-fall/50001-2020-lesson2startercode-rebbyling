package com.example.norman_lee.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.text.BreakIterator;

public class SubActivity extends AppCompatActivity {

    Button buttonBackToCalculator;
    EditText editTextSubValueOfHome;
    EditText editTextSubValueOfForeign;
    public final static String INTENT_EXCH_RATE = "Exchange Rate";
    private SharedPreferences mPreferences;
    private String sharedPrefFile = "com.example.android.subsharedprefs";
    public final static String HOME_KEY = "HOME_KEY";
    public final static String FOREIGN_KEY = "FOREIGN_KEY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);
        final Intent intent = getIntent();

        //TODO 4.9 Implement saving to shared preferences for the contents of the EditText widget
        mPreferences = getSharedPreferences(sharedPrefFile, MODE_PRIVATE);
        String defaultA = getResources().getString(R.string.enter_units_of_a);
        String defaultB = getResources().getString(R.string.enter_units_of_b);
        String a = mPreferences.getString(INTENT_EXCH_RATE, defaultA);
        String b = mPreferences.getString(INTENT_EXCH_RATE, defaultB);
        //TODO 3.5 Get references to the editText widgets
        editTextSubValueOfHome = findViewById(R.id.editTextSubValueA);
        editTextSubValueOfForeign = findViewById(R.id.editTextSubValueB);
        buttonBackToCalculator = findViewById(R.id.buttonBackToCalculator);
        //TODO 3.6 Get a reference to the Back To Calculator Button
        //TODO 3.7 Set up setOnClickListener
        buttonBackToCalculator.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                try {
                    //TODO 3.8 Obtain the values stored in the editTextWidgets
                    String home = editTextSubValueOfHome.getText().toString();
                    String foreign = editTextSubValueOfForeign.getText().toString();
                    //TODO 3.9 Check that these values are valid --> See the Utils class
                    Utils.checkValidString(home);
                    Utils.checkValidString(foreign);
                    //TODO 3.10 Set up an explicit intent and pass the exchange rate back to MainActivity
                    Intent intent = new Intent(SubActivity.this, MainActivity.class);
                    intent.putExtra(HOME_KEY, home);
                    intent.putExtra(FOREIGN_KEY, foreign);
                    startActivity(intent);
                //TODO 3.11 Decide how you are going to handle a divide-by-zero situation or when negative numbers are entered
                //TODO 3.12 Decide how you are going to handle a situation when the editText widgets are empty
                } catch (NumberFormatException ex) {
                    Toast.makeText(SubActivity.this, "pleasse enter a value", Toast.LENGTH_LONG).show();
                } catch (IllegalArgumentException ex) {
                    Toast.makeText(SubActivity.this,ex.getMessage(), Toast.LENGTH_LONG).show();
                }

            }
        });




    }

    //TODO 4.10 Don't forget to override onPause()

    @Override
    protected void onPause() {
        super.onPause();
        SharedPreferences.Editor editor = mPreferences.edit();
        editor.putString(INTENT_EXCH_RATE, String.valueOf(R.id.editTextSubValueA));
        editor.apply();
    }
}

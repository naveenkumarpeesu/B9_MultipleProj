package com.example.naveenkumar.radiobuttonpractice;

import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.TextWatcher;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    RadioButton piratesBtn;
    RadioButton titanicBtn;
    Snackbar snack;
    EditText expiryDateField;
    EditText phoneNumberField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        piratesBtn = (RadioButton) findViewById(R.id.pirates);
        titanicBtn = (RadioButton) findViewById(R.id.titanic);
        expiryDateField = (EditText) findViewById(R.id.expiryDate);
        phoneNumberField=(EditText)findViewById(R.id.phoneNumber);
        phoneNumberField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {



            @Override
            public void afterTextChanged(Editable editable) {
                Validator.isPhoneNumber(phoneNumberField, false);
            }
        });
        expiryDateField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
//                expiryDateField.getEditableText().setSpan(this, start,
//                        ((start - before + count) < start) ? start : start - before + count,
//                        android.text.Spanned.SPAN_MARK_MARK);



                String working = charSequence.toString();
                boolean isValid = true;
                if (working.length()==2 && before ==0) {
                    if (Integer.parseInt(working) < 1 || Integer.parseInt(working)>12) {
                        isValid = false;
                    } else {
                        working+="/";
                        expiryDateField.setText(working);
                        expiryDateField.setSelection(working.length());
                    }
                }
                else if (working.length()==7 && before ==0) {
                    String enteredYear = working.substring(3);
                    int currentYear = Calendar.getInstance().get(Calendar.YEAR);
                    if (Integer.parseInt(enteredYear) < currentYear) {
                        isValid = false;
                    }
                } else if (working.length()!=7) {
                    isValid = false;
                }

                if (!isValid) {
                    expiryDateField.setError("Enter a valid date: MM/YYYY");
                } else {
                    expiryDateField.setError(null);
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {
                //expiryDateField.removeTextChangedListener(this);

            }
        });
        piratesBtn.setOnClickListener(clickListener);
        titanicBtn.setOnClickListener(clickListener);
    }

    View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Boolean isChecked = (((RadioButton) view).isChecked());
            switch (view.getId()) {
                case R.id.pirates:
                    if (isChecked) {
                        //piratesBtn.setChecked(true);
                        Snackbar snackbar = Snackbar
                                .make(findViewById(android.R.id.content), "Message is deleted", Snackbar.LENGTH_LONG)
                                .setAction("UNDO", new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        piratesBtn.setChecked(false);
                                        titanicBtn.setChecked(true);
                                    }
                                });

                        snackbar.show();
                    }
                    break;
                case R.id.titanic:
                    if (isChecked)
                        Toast.makeText(MainActivity.this, "titanic was clicked", Toast.LENGTH_LONG).show();
                    break;
            }
        }
    };
}

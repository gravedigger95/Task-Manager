package com.example.masa.taskmanager;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import android.widget.ViewFlipper;

import java.util.Calendar;


public class NoviZadatak extends AppCompatActivity implements View.OnClickListener{
    Button btnDodaj,btnOtkazi,btnCrvena,btnZelena,btnZuta,btnVreme,btnDalje,btnPocetna;
    EditText imeZadatka,opisZadatka;
    Intent pocetna;

    TextView txtDatum,txtVreme;

    // Variable for storing current date and time
    private int mYear, mMonth, mDay, mHour, mMinute;
    boolean btnChosen=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_novi_zadatak);

        txtDatum=(TextView)findViewById(R.id.tvDatum);
        txtVreme=(TextView)findViewById(R.id.tvVreme);

        btnCrvena=(Button)findViewById(R.id.btnCrvena);
        btnCrvena.setOnClickListener(this);
        btnZuta=(Button)findViewById(R.id.btnZuta);
        btnZuta.setOnClickListener(this);
        btnZelena=(Button)findViewById(R.id.btnZelena);
        btnZelena.setOnClickListener(this);
        btnDodaj=(Button)findViewById(R.id.btnDodaj);
        btnDodaj.setOnClickListener(this);
        btnOtkazi=(Button)findViewById(R.id.btnOtkazi);
        btnOtkazi.setOnClickListener(this);

        btnVreme=(Button)findViewById(R.id.btnVreme);
        btnVreme.setOnClickListener(this);


        pocetna=new Intent(NoviZadatak.this,Pocetna.class);

        imeZadatka=(EditText) findViewById(R.id.etImeZad);
        imeZadatka.setInputType(InputType.TYPE_CLASS_TEXT);


        opisZadatka=(EditText) findViewById(R.id.etOpis);
        //opisZadatka.setInputType(InputType.TYPE_CLASS_TEXT);

        imeZadatka.addTextChangedListener(mTextWatcher);
        opisZadatka.addTextChangedListener(mTextWatcher);

        // run once to disable if empty
        checkFieldsForEmptyValues();
    }

    private TextWatcher mTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {

        }

        @Override
        public void afterTextChanged(Editable editable) {
            // check Fields For Empty Values
            checkFieldsForEmptyValues();
        }
    };


    void checkFieldsForEmptyValues(){
        btnOtkazi = (Button) findViewById(R.id.btnOtkazi);
        btnDodaj = (Button) findViewById(R.id.btnDodaj);

        String s1 = imeZadatka.getText().toString();
        String s2 = opisZadatka.getText().toString();
        String s3 = txtDatum.getText().toString();
        String s4 = txtVreme.getText().toString();
        if(s1.isEmpty() || s2.isEmpty() || btnChosen==false || s3.isEmpty() || s4.isEmpty()){
            btnDodaj.setEnabled(false);
            btnOtkazi.setEnabled(false);
        } else {
            btnOtkazi.setEnabled(true);
            btnDodaj.setEnabled(true);
        }
    }

    @Override
    public void onClick(View v){
        switch(v.getId()){
            case R.id.btnCrvena:
                btnChosen=true;
                checkFieldsForEmptyValues();
                btnZuta.setEnabled(false);
                btnZelena.setEnabled(false);
                break;
            case R.id.btnZuta:
                btnChosen=true;
                checkFieldsForEmptyValues();
                btnCrvena.setEnabled(false);
                btnZelena.setEnabled(false);
                break;
            case R.id.btnZelena:
                btnChosen=true;
                checkFieldsForEmptyValues();
                btnCrvena.setEnabled(false);
                btnZuta.setEnabled(false);
                break;
            case R.id.btnDodaj:
                startActivity(pocetna);
                break;
            case R.id.btnOtkazi:
                startActivity(pocetna);
                break;
            case R.id.btnVreme:
                final Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dpd = new DatePickerDialog(this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                txtDatum.setText(dayOfMonth + "-"
                                        + (monthOfYear + 1) + "-" + year);
                                checkFieldsForEmptyValues();


                            }
                        }, mYear, mMonth, mDay);
                dpd.show();

                final Calendar k = Calendar.getInstance();
                mHour = k.get(Calendar.HOUR_OF_DAY);
                mMinute = k.get(Calendar.MINUTE);

                // Launch Time Picker Dialog
                TimePickerDialog tpd = new TimePickerDialog(this,
                        new TimePickerDialog.OnTimeSetListener() {

                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay,
                                                  int minute) {
                                // Display Selected time in textbox
                                txtVreme.setText(hourOfDay + ":" + minute);
                                checkFieldsForEmptyValues();
                            }
                        }, mHour, mMinute, false);
                tpd.show();
                break;
            default:
                break;
        }
    }


}

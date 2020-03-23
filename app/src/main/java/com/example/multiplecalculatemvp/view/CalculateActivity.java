package com.example.multiplecalculatemvp.view;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.example.multiplecalculatemvp.CalculateInterface;
import com.example.multiplecalculatemvp.R;
import com.example.multiplecalculatemvp.presenter.CalculatePresenter;

public class CalculateActivity extends AppCompatActivity implements CalculateInterface.View {

    EditText edtNumberMultiplied, edtMultiplier;
    Button btnCalculate;
    TextView tvResult;

    private CalculatePresenter calculatePresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculate);
        mapping();
        initPresenter();
        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!edtMultiplier.getText().toString().equals("") && !edtNumberMultiplied.getText().toString().equals("")){
                    long multiplier = Long.parseLong(edtMultiplier.getText().toString());
                    long numberMultiplied = Long.parseLong(edtNumberMultiplied.getText().toString());
                    calculatePresenter.calculateMultiple(numberMultiplied,multiplier);
                }
            }
        });
    }

    private void initPresenter() {
        calculatePresenter = new CalculatePresenter(this);
    }

    private void mapping() {
        edtMultiplier = findViewById(R.id.edt_Multiplier);
        edtNumberMultiplied = findViewById(R.id.edt_NumberMultiplied);
        tvResult = findViewById(R.id.textView_Result);
        btnCalculate = findViewById(R.id.btn_Calculate);
    }

    public void setResultTextView(String result){
        tvResult.setText(String.valueOf(result));
    }
}

package com.example.guiassignmentprototype;

import android.os.Bundle;
import android.widget.TextView;
import android.content.Intent;

public class CheckoutActivity extends LoginActivity{
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.checkout);
        
        //receive incoming money
        
        Intent i = getIntent();
        String money = i.getStringExtra("money");
        
        //test values for screen 3
        Float testTotal = Float.parseFloat(money);
        Float Vat = testTotal*0.21f;
        Float Final = testTotal + Vat;
        
        //create text views to hold values
        TextView txtTotal = (TextView) findViewById(R.id.total);
        TextView txtVat = (TextView) findViewById(R.id.vat);
        TextView txtFinal = (TextView) findViewById(R.id.finalTotal);
        
        //display amounts
        txtTotal.setText(testTotal.toString());
        txtVat.setText(Vat.toString());
        txtFinal.setText(Final.toString());
    }
}

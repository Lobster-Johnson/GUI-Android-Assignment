package com.example.guiassignmentprototype;

import android.os.Bundle;
import android.widget.TextView;

public class CheckoutActivity extends ListActivity{
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        //test values for screen 3
        Float testTotal = 100.00f;
        Float Vat = testTotal/0.25f;
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

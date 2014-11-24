package com.example.guiassignmentprototype;



import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
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
        Float testTotal = i.getFloatExtra("money", 0);
        String[] items = i.getStringArrayExtra("items");
        
        //test values for screen 3
        //Float testTotal = Float.parseFloat(money);//Global.total;
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
        
        //display the shopping cart
        Spinner cart = (Spinner) findViewById(R.id.cart);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,items);
        cart.setAdapter(adapter);
        
        
        
        
    }
}

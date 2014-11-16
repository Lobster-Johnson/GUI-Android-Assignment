package com.example.guiassignmentprototype;



import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class ListActivity extends LoginActivity {
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        
        ListView lv = (ListView)findViewById(R.id.shopping_list);
        
        //contents of the list by title
        String[] items = {"potato", "drink", "meat", "veg", "fruit"};
        Float[] prices = {};
        
        
        
        //receive incoming data
        Intent i = getIntent();
        String name = i.getStringExtra("name");
        String money = i.getStringExtra("amount");
        
      //turn money string into a float to be calculated with
        Float dosh = Float.valueOf(money);
        
        //test code to make sure items are received
        final TextView txtName = (TextView) findViewById(R.id.textView1);
        TextView txtMoney = (TextView) findViewById(R.id.textView2);
        
        //display the name and budget of the user
        txtName.setText(name);
        txtMoney.setText(money);
        
        //create list
        ArrayAdapter<String> shoppinglist = new ArrayAdapter<String>(this, R.layout.item_row, R.id.text1,items);

        lv.setAdapter(shoppinglist);
        
        
        //------------------------------------------------------------------------------------
        //action listener for next screen
        //action listener for getting to the checkout screen
        Button submit = (Button) findViewById(R.id.checkout);
        submit.setOnClickListener(new View.OnClickListener() 
        {
        	//when the user clicks submit, they're put in the next screen
        	//the name and budget of the user are passed
        	public void onClick(View v) 
        	{
        		Intent finalScreen = new Intent(getApplicationContext(), CheckoutActivity.class);
        		
        		//be sure to remove this
        		finalScreen.putExtra("name", txtName.getText().toString());
        		
        		startActivity(finalScreen);
        		finish();
        	}
        });
 
    }


}

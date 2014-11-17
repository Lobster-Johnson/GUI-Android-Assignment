package com.example.guiassignmentprototype;





import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
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
        final String[] items = getResources().getStringArray(R.array.items);
        final String[] prices = getResources().getStringArray(R.array.prices);
        
        
        
      //custom adapter code goes here--------------------------------------------------------------------------------------
        class MyCustomAdapter extends ArrayAdapter
    	{ 
    	// Constructor for MyCustomAdapter will call super constructor (of Array    Adapter)
    	public MyCustomAdapter(Context context, int textViewResourceId, String[] objects) 
    	 {
    		 super(context, textViewResourceId, objects);
    			 
    	 }
    	 // Then.. Override the getView() method – which is used EACH time that a row is inflated on the list
    	public View getView(int position, View convertView, ViewGroup parent) 
    	{
    			//Get a layout inflater object:
    			 LayoutInflater inflater=getLayoutInflater();
    			 View row=inflater.inflate(R.layout.item_row, parent, false);
    			 //take current position of array and populate row
    			 
    			 TextView words = (TextView) row.findViewById(R.id.text1);
    			 TextView words2 = (TextView) row.findViewById(R.id.text2);
    			 words.setText(items[position]);
    			 words2.setText(prices[position]);
    			 
    			return row;
    	      
    	   } // end of custom adapter----------------------------------------------------------------------------
    	}
        
        
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
        //ArrayAdapter<String> shoppinglist = new ArrayAdapter<String>(this, R.layout.item_row, R.id.text1,items);
        MyCustomAdapter shoppinglist = new MyCustomAdapter(this, R.layout.item_row, items);

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
        		
        		//still an error preventing me from getting to last screen
        		startActivity(finalScreen);
        	}
        });
        
        
    }
    
    
    


}

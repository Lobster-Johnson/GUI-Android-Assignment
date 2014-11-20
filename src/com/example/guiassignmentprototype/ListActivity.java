package com.example.guiassignmentprototype;





import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class ListActivity extends LoginActivity implements AdapterView.OnItemClickListener{
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) 
    {
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
    			 
    			 final TextView words = (TextView) row.findViewById(R.id.text1);
    			 final TextView words2 = (TextView) row.findViewById(R.id.text2);
    			 final TextView quantity = (TextView) row.findViewById(R.id.quantity);
    			 words.setText(items[position]);
    			 words2.setText(prices[position]);
    			 
    			 //button to add things to the shopping cart
    			 Button add = (Button)row.findViewById(R.id.add);
    			 add.setOnClickListener(new View.OnClickListener() 
    		        {
    		        	//when the user clicks submit, they're put in the next screen
    		        	//the name and budget of the user are passed
    		        	public void onClick(View v) 
    		        	{
    		        		//show what it was
    		        		//Toast toast = Toast.makeText(getApplicationContext(), words.getText().toString(), Toast.LENGTH_SHORT);
    	        			//toast.show();
    	        			
    	        			//find total cost
    		        		Float cost = Float.valueOf(words2.getText().toString())*Float.valueOf(quantity.getText().toString());
    		        		Toast toast = Toast.makeText(getApplicationContext(), "item: " + words.getText().toString() + " x " + quantity.getText().toString() + " Price: " + cost.toString(), Toast.LENGTH_SHORT);
    	        			toast.show();
    	        			
    	        			//add to total
    	        			
    	        			//check if not over budget
    	        			
    	        			//add to cart
    		        	}
    		        });
    		        
    			 
    			return row;
    	      
    	   } // end of custom adapter----------------------------------------------------------------------------
    	}
        
        
        //receive incoming data
        Intent i = getIntent();
        String name = i.getStringExtra("name");
        String money = i.getStringExtra("amount");
        
      //turn money string into a float to be calculated with
        Float dosh = Float.valueOf(money);
        Float total = 0.0f;
        
        //test code to make sure items are received
        final TextView txtName = (TextView) findViewById(R.id.textView1);
        final TextView txtMoney = (TextView) findViewById(R.id.textView2);
        
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
        		finalScreen.putExtra("money", txtMoney.getText().toString());
        		
        		//still an error preventing me from getting to last screen
        		startActivity(finalScreen);
        	}
        });
        
        /*lv.setOnClickListener(new View.OnClickListener() 
        {

			

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
			    }
				
		});*/
        	
    }

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		// TODO Auto-generated method stub
		
	}
    

	/*@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		// TODO Auto-generated method stub
		TextView temp = (TextView) view;
    	Toast.makeText(this, "SHOPPING", Toast.LENGTH_SHORT).show();
		
	}
    
    /*@Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l)
    {
    	TextView temp = (TextView) view;
    	Toast.makeText(this, "SHOPPING", Toast.LENGTH_SHORT).show();
    	
    }*/
    
    


}

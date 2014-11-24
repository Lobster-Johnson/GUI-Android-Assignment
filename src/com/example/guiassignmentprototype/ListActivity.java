package com.example.guiassignmentprototype;





import java.util.Currency;


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
        
        
        Global.total = 0.0f;
        
      //receive incoming data
        Intent i = getIntent();
        String name = i.getStringExtra("name");
        String money = i.getStringExtra("amount");
        
      //turn money string into a float to be calculated with
        final Float budget = Float.valueOf(money);
        
        //consider adding this to a different class
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
    			 //get a description here
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
    	        			
    	        			//find total cost
    		        		String name = words.getText().toString();
    		        		Float price = Float.valueOf(words2.getText().toString());
    		        		int quant = Integer.valueOf(quantity.getText().toString());
    		        		Float cost = price * quant;
    		        		
    		        		//test to make sure the user gave a quantity
    		        		if(quant == 0)
    		        		{
    		        			Toast toast = Toast.makeText(getApplicationContext(), "You can't have 0 of something", Toast.LENGTH_SHORT);
        	        			toast.show();
    		        		}
    		        		
    		        		//check to see if it doesn't go over budget
    		        		else if (Global.total + cost > budget)
    		        		{
    		        			Toast toast = Toast.makeText(getApplicationContext(), "Not enough cash", Toast.LENGTH_SHORT);
        	        			toast.show();
    		        			
    		        		}
    		        		
    		        		else
    		        		{
    		        			//begin adding the new item to the cart
	    		        		String newitem = name + " x " + quantity.getText().toString() + ": €" + cost.toString();
	    		        		
	    		        		//show the user what they added to the cart
	    		        		Toast toast = Toast.makeText(getApplicationContext(), newitem , Toast.LENGTH_SHORT);
	    	        			toast.show();
	    	        			
	    	        			//total increases
	    	        			Global.total = Global.total + cost;
	    	        			boolean duplicate = false;
	    	        			
	    	        			
	    	        			//item gets added to cart
	    	        			//add a function to detect if an item is already in the cart
	    	        			int x = (Global.shopping).length;
	    	        			for(int z = 0; z < x; z++)
	    	        			{
	    	        				if(((Global.shopping[z]).name).equals(name))
	    	        				{
	    	        					duplicate = true;
	    	        					(Global.shopping[z]).quantity = (Global.shopping[z]).quantity + quant;
	    	        					(Global.shopping[z]).total = (Global.shopping[z]).total + cost;
	    	        				}
	    	        			}
	    	        			
	    	        			if(duplicate == false)
	    	        			{
	    	        			
		    	        			//temp array to hold contents of array
		    	        			ListItem[] temp = new ListItem[x];
		    	        			
		    	        			for(int y = 0; y < x; y++)
		    	        			{
		    	        				temp[y] = new ListItem();
		    	        				temp[y].name = Global.shopping[y].name;
		    	        				temp[y].quantity = Global.shopping[y].quantity;
		    	        				temp[y].total = Global.shopping[y].total;
		    	        			}
		    	        			
		    	        			//add new item to the end of the shopping list
		    	        			//then put old items back in
		    	        			x++;
		    	        			Global.shopping = new ListItem[x];
		    	        			
		    	        			//according to debugging, error lies here
		    	        			Global.shopping[x-1] = new ListItem();
		    	        			Global.shopping[x-1].name = name;
		    	        			Global.shopping[x-1].quantity = quant;
		    	        			Global.shopping[x-1].total = cost;
		    	        			
		    	        			for(int y = 0; y < temp.length; y++)
		    	        			{
		    	        				Global.shopping[y] = new ListItem();
		    	        				Global.shopping[y].name = temp[y].name;
		    	        				Global.shopping[y].quantity = temp[y].quantity;
		    	        				Global.shopping[y].total = temp[y].total;
		    	        			}
	    	        			}
    		        		}//item should now be in the cart
    	        			
    		        	}
    		        	
    		        	

    		        });
    		        
    			 
    			return row;
    	      
    	   } // end of custom adapter----------------------------------------------------------------------------
    	}
        
        
        
        
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
        		//Float safety = Global.total;
        		
        		//emergency code
        		String[] ToCart = new String[(Global.shopping).length];
        		
        		for(int i = 0; i < ToCart.length; i++)
        		{
        			ToCart[i] = (Global.shopping[i]).name + " x " + Integer.toString((Global.shopping[i]).quantity) + ": €" + Float.toString((Global.shopping[i]).total); 
        		}
        		//pass total cash and array of items
        		finalScreen.putExtra("money", Global.total);
        		finalScreen.putExtra("items", ToCart);
        		
        		startActivity(finalScreen);
        	}
        });
        	
    }

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		// TODO Auto-generated method stub
		
	}
	
    
    
    


}

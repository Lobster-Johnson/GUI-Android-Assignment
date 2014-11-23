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
    		        		Float price = Float.valueOf(words2.getText().toString());
    		        		Float quant = Float.valueOf(quantity.getText().toString());
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
	    		        		String newitem = (words.getText().toString()) + " x " + quantity.getText().toString() + ": €" + cost.toString();
	    		        		
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
	    	        				String olditem = null;
	    	        				olditem = Global.shopping[z];
	    	        				String[] test = olditem.split(" ");
	    	        				String[] test2 = newitem.split(" ");
	    	        				if(test[0].equals(test2[0]))
	    	        				{
	    	        					Toast war = Toast.makeText(getApplicationContext(), "YUP, got a duplicate here", Toast.LENGTH_SHORT);
	    	    	        			war.show();
	    	        					duplicate = true;
	    	        					//Global.shopping[z] = addToPreExisting(newitem, olditem);
	    	        				}
	    	        			}
	    	        			
	    	        			if(duplicate == false)
	    	        			{
		    	        			//temp array to hold contents of array
		    	        			String[] temp = new String[x];
		    	        			
		    	        			for(int y = 0; y < x; y++)
		    	        			{
		    	        				temp[y] = Global.shopping[y];
		    	        			}
		    	        			
		    	        			//add new item to the end of the shopping list
		    	        			//then put old items back in
		    	        			x++;
		    	        			Global.shopping = new String[x];
		    	        			
		    	        			Global.shopping[x-1] = newitem;
		    	        			for(int y = 0; y < temp.length; y++)
		    	        			{
		    	        				Global.shopping[y] = temp[y];
		    	        			}
	    	        			}
    		        		}
    	        			
    		        	}
    		        	
    		        	//method for adding to an already existing item
						private String addToPreExisting(String newitem, String olditem) 
						{
							// split string apart
							String[] newArray = newitem.split(" |€|:");
							String[] oldArray = olditem.split(" |€|:");	
							
							//reminder:
							//0 is name
							//1 is x
							//2 is quantity
							//3 is total
							
							//add quantities and totals
							Float q1 = Float.valueOf(newArray[2]);
							Float q2 = Float.valueOf(oldArray[2]);
							Float q3 = q1 + q2;
							
							Float t1 = Float.valueOf(newArray[3]);
							Float t2 = Float.valueOf(newArray[3]);
							Float t3 = t1 + t2;
							
							//rebuild
							String Finish = newArray[0] + " x " + q3.toString() + ": €" + t3.toString();
							
							//return
							return Finish;
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
        		
        		/*//emergency code
        		String[] ToCart = new String[(Global.shopping).length];
        		
        		//create array to be passed in
        		for(int x = 0; x < (Global.shopping).length; x++)
        		{
        			ToCart[x] =  Global.shopping[x];
        		}*/
        		
        		//pass total cash and array of items
        		finalScreen.putExtra("money", Global.total);
        		finalScreen.putExtra("items", Global.shopping);
        		
        		//still an error preventing me from getting to last screen
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

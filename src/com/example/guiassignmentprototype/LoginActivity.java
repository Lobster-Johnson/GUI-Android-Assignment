package com.example.guiassignmentprototype;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


public class LoginActivity extends Activity 
{

    @Override
    protected void onCreate(Bundle savedInstanceState) 
    {
    	//create and set to login screen
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        
        //create objects to be used on this screen
        Spinner spinner = (Spinner) findViewById(R.id.jobselecter);
        final TextView txtName = (TextView) findViewById(R.id.username);
        final TextView totalAmount = (TextView) findViewById(R.id.money);
        Button submit = (Button) findViewById(R.id.submit_button);
        
        final String error = "Please enter a username and a budget";
        
        //create and populate spinner
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.jobtitles, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        
        //action listener for submitting user details
        submit.setOnClickListener(new View.OnClickListener() 
        {
        	//when the user clicks submit, they're put in the next screen
        	//the name and budget of the user are passed
        	public void onClick(View v) 
        	{
        		if((txtName.getText().toString()).matches("") || (totalAmount.getText().toString()).matches(""))
        		{
        			//Note: need to implement warning if these crucial fields haven't been filled out
        			return;
        		
        		}
        		else
        		{
        			//not my code
	        		Intent listScreen = new Intent(getApplicationContext(), ListActivity.class);
	        		listScreen.putExtra("name", txtName.getText().toString());
	        		listScreen.putExtra("amount", totalAmount.getText().toString());
	        		
	        		startActivity(listScreen);
	        		finish();
	        		//end of not my code
        		}
        	}
        });
    }

}

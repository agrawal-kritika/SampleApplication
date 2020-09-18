package com.example.sampleapp;



import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginPage extends Activity {
	
	EditText username ,password;
	Button login;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login_page);
		
		username = (EditText)findViewById(R.id.editText1);
		password = (EditText)findViewById(R.id.editText2);
		login = (Button)findViewById(R.id.button1);
		
        login.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				
				String suser = username.getText().toString();
				String spass = password.getText().toString();
				if(suser.equals("admin") && (spass.equals("admin")))
				{
					Toast.makeText(getApplicationContext(), "Login Successful", Toast.LENGTH_LONG).show();
					new Handler().postDelayed(new Runnable() {
						
						@Override
						public void run() {
							// TODO Auto-generated method stub
							
							Intent in = new Intent(getApplicationContext(), WeChat.class);
							startActivity(in);
							finish();
						}
					},2500);
					
				}
				else 
				{
					Toast.makeText(getApplicationContext(), "Invalid username or password", Toast.LENGTH_LONG).show();
				}
			}
		});			
	}
			

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.login_page, menu);
		return true;
	}

}

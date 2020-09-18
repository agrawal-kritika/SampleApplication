package com.example.sampleapp;




import java.util.Locale;



import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.speech.tts.TextToSpeech;
import android.telephony.SmsManager;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class WeChat extends Activity {
	
	EditText phonenumber , message;
	Button send, speech ,close;
	TextToSpeech txt;
	AlertDialog.Builder build;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_we_chat);
		

        phonenumber = (EditText)findViewById(R.id.editText1);
        message = (EditText)findViewById(R.id.editText2);
        send = (Button)findViewById(R.id.button1);
        speech = (Button)findViewById(R.id.button2);
        close = (Button)findViewById(R.id.button3);
        
        txt = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
			
			@Override
			public void onInit(int status) {
				// TODO Auto-generated method stub
				

				if(status != TextToSpeech.ERROR)
				{
					txt.setLanguage(Locale.US);
				}
				
			}
		});
        
        speech.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				String smessage = message.getText().toString();
				txt.speak(smessage, TextToSpeech.QUEUE_FLUSH, null);
				//if this is successful we display a toast message
				Toast.makeText(getApplicationContext(), smessage, Toast.LENGTH_LONG).show();
				
			}
		});
        
        build = new AlertDialog.Builder(this);
        close.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				
				build.setMessage("Are you sure you want to exit?");
				build.setCancelable(false);
				build.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface arg0, int arg1) {
						// TODO Auto-generated method stub
						
						finish();
						Toast.makeText(getApplicationContext(), "You pressed Yes button", Toast.LENGTH_LONG).show();
						
					}
				});
				
				build.setNegativeButton("No", new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface st, int arg1) {
						// TODO Auto-generated method stub
						
						st.cancel();
						Toast.makeText(getApplicationContext(), "You pressed No button", Toast.LENGTH_LONG).show();
						
					}
				});
				
				AlertDialog ad = build.create();
				ad.setTitle("Warning");
				ad.show();
			}
		});
	
        
        send.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				
				String sphonenumber = phonenumber.getText().toString();
				String smessage = message.getText().toString();
				if(sphonenumber.equals("") && (smessage.equals("")))
				{
					Toast.makeText(getApplicationContext(), "The fields cannot be empty", Toast.LENGTH_LONG).show();
					
				}
				else
				{
					try
					{
						SmsManager sms = SmsManager.getDefault();
						sms.sendTextMessage(sphonenumber, null, smessage, null, null);
						Toast.makeText(getApplicationContext(), "SMS sent successfully", Toast.LENGTH_LONG).show();
						
					}
					catch(Exception e)
					{
						e.printStackTrace();
						Toast.makeText(getApplicationContext(), "Failed to send the SMS", Toast.LENGTH_LONG).show();
						
					}
				}
				
				
			}
		});
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.we_chat, menu);
		return true;
	}

}

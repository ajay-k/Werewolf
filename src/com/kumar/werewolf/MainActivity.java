package com.kumar.werewolf;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
	public static final int PICK_CONTACT = 1;
	public TextView txtContacts;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Toast.makeText(getApplicationContext(),
				"Press menu button to explore the Application",	Toast.LENGTH_LONG).show();
	}

	@Override
	public boolean onCreateOptionsMenu(android.view.Menu menu) {
		// TODO Auto-generated method stub
		super.onCreateOptionsMenu(menu);
		MenuInflater blowUp = getMenuInflater();
		blowUp.inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub

		switch (item.getItemId()) {
		// Menu instructions button
		case R.id.instruct:
			Intent i = new Intent("android.intent.action.INSTRUCT");
			startActivity(i);
			break;
		// Menu Add Players button
		case R.id.addPlayers:
			Intent intent = new Intent(Intent.ACTION_PICK,
					ContactsContract.Contacts.CONTENT_URI);
			startActivityForResult(intent, PICK_CONTACT);
			break;
		// Menu Settings button
		case R.id.settings:
			Intent p = new Intent("android.intent.action.SETTINGS");
			startActivity(p);
			break;
		case R.id.exit:
			finish();
			break;
		}
		return false;
	}
	

	@Override
	// Get Contacts from Contact/Address book
	public void onActivityResult(int reqCode, int resultCode, Intent data) {
		super.onActivityResult(reqCode, resultCode, data);
		switch (reqCode) {
		case (PICK_CONTACT):
			if (resultCode == Activity.RESULT_OK) {
				Uri contactData = data.getData();
				Cursor c = getContentResolver().query(contactData, null, null,
						null, null);
				int Name = c
						.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME);

				if (c.moveToFirst()) {

					txtContacts = (TextView) findViewById(R.id.txt_contacts);
					String sName = c.getString(Name);
					Toast.makeText(getApplicationContext(),
							"Player " + sName + " added!", Toast.LENGTH_LONG)	.show();
		
			}
			break;
		}
		
	}
}
	
}

package com.fglp.futeba;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import adapter.GroupMemberAdapter;
import adapter.PersonAdapter;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ListView;

import com.appspot.patota_hackathon.api.model.Group;
import com.appspot.patota_hackathon.api.model.GroupMember;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.plus.Plus;
import com.google.android.gms.plus.People.LoadPeopleResult;
import com.google.android.gms.plus.model.people.Person;
import com.google.android.gms.plus.model.people.PersonBuffer;

public class AddGroupActivity extends Activity implements ConnectionCallbacks, OnConnectionFailedListener, ResultCallback<LoadPeopleResult>{
	private List<Person> people = new ArrayList<Person>();
	private PersonAdapter pAdapter; 
	private ListView listPeople;
	private GoogleApiClient mGoogleApiClient;
	private EditText groupName;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_addgroup);
		mGoogleApiClient = new GoogleApiClient.Builder(this)
								.addConnectionCallbacks(this)
								.addOnConnectionFailedListener(this).addApi(Plus.API, null)
								.addScope(Plus.SCOPE_PLUS_LOGIN).build();
		
		listPeople = (ListView) findViewById(R.id.peopleList);
				
	}
	
	@Override
	protected void onStart() {
		super.onStart();
		mGoogleApiClient.connect();
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
	    // Inflate the menu items for use in the action bar
	    MenuInflater inflater = getMenuInflater();
	    inflater.inflate(R.menu.activity_addgroup, menu);
	    return super.onCreateOptionsMenu(menu);
	}

	@Override
	public void onConnectionFailed(ConnectionResult result) {
	}

	@Override
	public void onResult(LoadPeopleResult result) {
		PersonBuffer personBuffer = result.getPersonBuffer();
		for (int i = 0, tam = personBuffer.getCount(); i < tam; i++) {
			Log.d("Nome: ", personBuffer.get(i).getDisplayName());
			people.add(personBuffer.get(i));
		}
		if (people != null) {
			pAdapter = new PersonAdapter(this, people);
			
			listPeople.setAdapter(pAdapter);
		}
	}

	@Override
	public void onConnected(Bundle arg0) {
		Plus.PeopleApi.loadVisible(mGoogleApiClient, null).setResultCallback(this);
	}

	@Override
	public void onConnectionSuspended(int arg0) {
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    // Handle item selection
	    switch (item.getItemId()) {
	        case R.id.menu_ok:
	        	salvarGrupo();
	        	return true;
	        case R.id.menu_cancel:
	        	return true;    
	        default:
	            return super.onOptionsItemSelected(item);
	    }
	}
	
	public void salvarGrupo() {
		groupName = (EditText) findViewById(R.id.groupName);
		
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				try {
					Group g = PatotaAPIFactory.getService().insertGroup(groupName.getText().toString()).execute();
					List<String> selecteds = pAdapter.getSelectedPeopleId();
					for(int i=0, tam=selecteds.size(); i<tam; i++) {
						GroupMember gM = new GroupMember();
						gM.setAbilityLevel(1);
						gM.setConfirmed(true);
						gM.setKeeper(false);
						gM.setName(selecteds.get(i));
						PatotaAPIFactory.getService().addMember(g.getId(), gM);
					}
					
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}).start();
	}
}

package com.fglp.futeba;

import helper.ChallengeFinder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import adapter.GroupsAdapter;
import adapter.IChallengeClickedListener;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;

import com.appspot.patota_hackathon.api.model.Group;

public class GroupsActivity extends ActionBarActivity {
	private List<Group> allGroups = new ArrayList<Group>();
	private GroupsAdapter gAdapter; 
	private ListView listGroup;
	
	//FUCKING WORKAROUND
	public static Group groupSelected;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_groups);

		new Thread(new Runnable() {
			
			@Override
			public void run() {
				try {
					PatotaAPIFactory.getService().insertGroup("teste").execute();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				try {
					allGroups = PatotaAPIFactory.getService().allGroups().execute().getGroups();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}).start();
		
		gAdapter = new GroupsAdapter(this, allGroups);
		gAdapter.setChallengeClickedListenter(new IChallengeClickedListener() {
			
			@Override
			public void groupClicked(Group group) {
				groupSelected = group;
				startActivity(new Intent(GroupsActivity.this, ChallengeFinder.class));
				
			}
		});
		listGroup = (ListView) findViewById(R.id.listGroup);
		listGroup.setAdapter(gAdapter);		
	}
	
	@Override
	protected void onStart() {
		super.onStart();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
	    // Inflate the menu items for use in the action bar
	    MenuInflater inflater = getMenuInflater();
	    inflater.inflate(R.menu.activity_groups, menu);
	    return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    // Handle item selection
	    switch (item.getItemId()) {
	        case R.id.menu_addgroup:
	        	startActivity(new Intent(GroupsActivity.this, AddGroupActivity.class));
	        	return true;    
	        default:
	            return super.onOptionsItemSelected(item);
	    }
	}

}

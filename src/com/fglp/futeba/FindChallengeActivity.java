package com.fglp.futeba;

import helper.ChallengeFinder;

import java.io.IOException;
import java.util.LinkedList;

import adapter.GroupChallengeAdapter;
import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;

import com.appspot.patota_hackathon.api.model.Challenge;

public class FindChallengeActivity extends Activity implements OnClickListener,
		LocationListener {

	int km = 10;

	LinkedList<ChallengeGroup> challenges = new LinkedList<>();

	private GroupChallengeAdapter gAdapter;
	private ListView listGroup;
	Location location;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.find_challenge);

		location = getLocation();
		new Thread(new Runnable() {

			@Override
			public void run() {
				challenges = ChallengeFinder.find(GroupsActivity.groupSelected.getId().toString(), location.getLatitude(),
						location.getLongitude(), km);

			}
		}).start();

		SeekBar sb = (SeekBar) findViewById(R.id.seekBar1);
		sb.setProgress(30);
		sb.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {

			@Override
			public void onStopTrackingTouch(final SeekBar arg0) {
				new Thread(new Runnable() {

					@Override
					public void run() {
						challenges = ChallengeFinder.find(GroupsActivity.groupSelected.getId().toString(),
								location.getLatitude(),
								location.getLongitude(), arg0.getProgress());
						gAdapter.clear();
						gAdapter.addAll(challenges);
					}
				}).start();

			}

			@Override
			public void onStartTrackingTouch(SeekBar arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onProgressChanged(SeekBar arg0, int arg1, boolean arg2) {
				// TODO Auto-generated method stub

			}
		});

		gAdapter = new GroupChallengeAdapter(this, challenges);
		listGroup = (ListView) findViewById(R.id.listView1);
		listGroup.setAdapter(gAdapter);
	}

	public void createChallenge() {
		new Thread(new Runnable() {

			@Override
			public void run() {
				Challenge c = new Challenge();
				c.setGroupId(GroupsActivity.groupSelected.getId().toString());
				c.setLat((float) location.getLatitude());
				c.setLng((float) location.getLongitude());
				try {
					PatotaAPIFactory.getService().addChallenge(c);
				} catch (IOException e) {
					throw new IllegalStateException("Error creating Challenge",
							e);
				}
			}
		}).start();
	}

	@Override
	public void onClick(View v) {

	}

	private Location getLocation() {
		LocationManager locManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
		locManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0,
				this);

		// get the current location (last known location) from the location
		// manager
		Location location = locManager
				.getLastKnownLocation(LocationManager.GPS_PROVIDER);
		if (location == null) {
			location = locManager
					.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
			if (location == null) {
				location = locManager
						.getLastKnownLocation(LocationManager.PASSIVE_PROVIDER);
			}
		}

		// if location found display as a toast the current latitude and
		// longitude
		return location;
	}

	@Override
	public void onLocationChanged(Location arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onProviderDisabled(String arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onProviderEnabled(String arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onStatusChanged(String arg0, int arg1, Bundle arg2) {
		// TODO Auto-generated method stub

	}
}
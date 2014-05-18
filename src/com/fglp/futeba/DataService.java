package com.fglp.futeba;

import android.content.Context;

import com.appspot.patota_hackathon.api.Api;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.googleapis.extensions.android.gms.auth.GoogleAccountCredential;
import com.google.api.client.json.gson.GsonFactory;

public class DataService {

	private static Api service;

	public static Api getService(Context context) {
		if (service == null) {
		GoogleAccountCredential credential = GoogleAccountCredential
				.usingAudience(
						context,
						"server:client_id:236724444260-rt5t990c4esli29fft2iqjg8hcbo94lr.apps.googleusercontent.com");
		Api.Builder builder = new Api.Builder(
				AndroidHttp.newCompatibleTransport(), new GsonFactory(),
				credential);
		service = builder.build();
		}
		return service;
	}

}

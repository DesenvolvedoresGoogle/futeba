package com.fglp.futeba;

import com.appspot.patota_hackathon.api.Api;
import com.appspot.patota_hackathon.api.Api.PatotaAPI;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.json.gson.GsonFactory;

public class PatotaAPIFactory {

	private static PatotaAPI instance;

	public static PatotaAPI getService() {
		if (instance == null) {
			Api.Builder builder = new Api.Builder(
					AndroidHttp.newCompatibleTransport(), new GsonFactory(),
					null);
			Api service = builder.build();
			instance = service.patotaAPI();
		}
		return instance;
	}
}
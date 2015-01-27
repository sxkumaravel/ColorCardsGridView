package com.colorcards;

import org.json.JSONObject;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class MyCardCLicked extends Activity {

	private AppVolleyApiManager apiManager;
	TextView textView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.jsontext_view);
		textView = (TextView) findViewById(R.id.txt_view);
		apiManager = AppVolleyApiManager.instance();
		apiManager.initVolley(getApplicationContext());
		loadData("http://headers.jsontest.com", 1);
		loadData("http://ip.jsontest.com", 2);
	}

	private void loadData(String API, final int x) {
		apiManager.getJsonResponse(getApplicationContext(), API,
				new OnNetWorkResponse() {

					public void onSuccessResponse(JSONObject responseObject) {

						if (x == 1) {//for the first http link

							String acceptLanguage = responseObject.optString(
									"Accept-Language", "No Language");
							String host = responseObject.optString("Host",
									"No Host");
							String userAgent = responseObject.optString(
									"User-Agent", "No User Agent");
							String accept = responseObject.optString("Accept",
									"No Accepting");

							textView.append("Accept-Language :"
									+ acceptLanguage + "\n");
							textView.append("Host :" + host + "\n");
							textView.append("User-Agent :" + userAgent + "\n");
							textView.append("Accept :" + accept + "\n");

						}
						if (x == 2) {//for the second http link
							String ip = responseObject.optString("ip",
									"No IP Details");
							textView.append("" + "\n");
							textView.append("IP :" + ip);
						}
					}

					public void onSuccessResponse(String xmlStreamSource) {

					}

					public void onError(String error) {
						Log.v("", "User Info error : " + error.toString());

					}
				});

	}
}

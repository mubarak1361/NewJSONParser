package com.opendesk.asynctask;

import org.json.JSONException;
import org.json.JSONObject;

import com.opendesk.helper.CommonAsyncTask;
import com.opendesk.helper.MakeAPICall;
import com.opendesk.helper.OnCommonAsyncTaskListener;
import com.opendesk.helper.RequestType;

import android.app.Activity;
import android.os.Bundle;

public class MainActivity extends Activity {

	private String url ="https://expensetracker-opendesk.rhcloud.com/users/login.json";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		JSONObject jsonObject =  new JSONObject();
		try {
			jsonObject.putOpt("username", "mubarak");
			jsonObject.putOpt("password", "Simple123");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		/*new CommonAsyncTask(url, jsonObject, RequestType.POST, new OnCommonAsyncTaskListener() {
			
			@Override
			public void onTaskCompleted(JSONObject jsonObject) {
				new CommonAsyncTask("https://expensetracker-opendesk.rhcloud.com/expensecategories/index.json", null, RequestType.GET,
						new OnCommonAsyncTaskListener() {
							
							@Override
							public void onTaskCompleted(JSONObject jsonObject) {
								// TODO Auto-generated method stub
								
							}
						}).execute();
				
			}
		}).execute();*/

		new MakeAPICall.Builder()
				.setEndPoint("https://expensetracker-opendesk.rhcloud.com/users/login.json")
				.setRequestType(RequestType.POST)
				.setPostData(jsonObject)
				.getResponse(new OnCommonAsyncTaskListener() {
					@Override
					public void onTaskCompleted(JSONObject jsonObject) {

					}
				}).build();
	}
	
}

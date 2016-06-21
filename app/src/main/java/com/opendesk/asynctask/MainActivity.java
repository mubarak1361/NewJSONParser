package com.opendesk.asynctask;

import android.app.Activity;
import android.os.Bundle;

import com.opendesk.helper.MakeAPICall;
import com.opendesk.helper.OnResponseListener;
import com.opendesk.helper.RequestType;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends Activity implements  OnResponseListener{

	private final String USER_LOGIN ="https://expensetracker-opendesk.rhcloud.com/users/login.json";
	private final String USER_CATEGORY ="https://expensetracker-opendesk.rhcloud.com/expensecategories/index.json";

	private final int LOGIN =1;
	private final int CATEGORY = 2;

	private MakeAPICall.Builder categoryBuilder;
	
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

		new MakeAPICall.Builder()
				.setEndPoint(USER_LOGIN)
				.setRequestType(RequestType.POST)
				.setId(LOGIN)
				.setPostData(jsonObject)
				.getResponse(this).build();

		categoryBuilder = new MakeAPICall.Builder()
				.setEndPoint(USER_CATEGORY)
				.setId(CATEGORY)
				.setRequestType(RequestType.GET)
				.getResponse(this);
	}

	@Override
	public void onSuccess(int id, JSONObject jsonObject) {
		switch (id){
			case LOGIN:
				categoryBuilder.build();
				break;
			case CATEGORY:
				break;
		}
	}

	@Override
	public void OnFailure(int id, String info) {
		switch (id){
			case LOGIN:
				break;
			case CATEGORY:
				break;
		}
	}
}

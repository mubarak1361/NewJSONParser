package com.opendesk.helper;

import org.json.JSONObject;

import android.os.AsyncTask;

public class CommonAsyncTask extends AsyncTask<Void, Void, JSONObject>{
	
	private String url;
	private JSONObject jsonObject;
	private RequestType requestType;
	private OnCommonAsyncTaskListener onCommonAsyncTask;
	
	public CommonAsyncTask(String url,JSONObject jsonObject,RequestType requestType,OnCommonAsyncTaskListener onCommonAsyncTask) {
		this.url = url;
		this.jsonObject = jsonObject;
		this.requestType = requestType;
		this.onCommonAsyncTask = onCommonAsyncTask;
	}
	
	@Override
	protected void onPreExecute() {
		super.onPreExecute();
		WebServiceHelper.setEnableSession(true);
	}
	

	@Override
	protected JSONObject doInBackground(Void... params) {
		return WebServiceHelper.runService(url, jsonObject, requestType);
	}

	@Override
	protected void onPostExecute(JSONObject result) {
		super.onPostExecute(result);
		onCommonAsyncTask.onTaskCompleted(result);
	}


}

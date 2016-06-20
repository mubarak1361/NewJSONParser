package com.opendesk.helper;

import org.json.JSONObject;

import android.os.AsyncTask;
import android.os.Build;

public class MakeAPICall extends AsyncTask<Void, Void, JSONObject>{

	private String url;
	private JSONObject jsonObject;
	private RequestType requestType;
	private OnCommonAsyncTaskListener onCommonAsyncTask;

	private MakeAPICall(){
		super();
	}

	private MakeAPICall(String url,JSONObject jsonObject,RequestType requestType,OnCommonAsyncTaskListener onCommonAsyncTask) {
		super();
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

	public static class Builder implements APIBuilder<Builder> {

		private RequestType requestType;
		private String url;
		private JSONObject jsonPostObject;
		private OnCommonAsyncTaskListener onCommonAsyncTask;

		public Builder(){
			resetData();
		}

		@Override
		public Builder setRequestType(RequestType requestType) {
			this.requestType = requestType;
			return this;
		}

		@Override
		public Builder setEndPoint(String url) {
			this.url = url;
			return this;
		}

		@Override
		public Builder setPostData(JSONObject jsonPostObject) {
			this.jsonPostObject = jsonPostObject;
			return this;
		}

		@Override
		public Builder getResponse(OnCommonAsyncTaskListener onCommonAsyncTask) {
			this.onCommonAsyncTask = onCommonAsyncTask;
			return this;
		}

		@Override
		public void build() {
			new MakeAPICall(url, jsonPostObject, requestType, onCommonAsyncTask).execute();
		}

		private void resetData() {
			requestType = null;
			url = null;
			jsonPostObject = null;
			onCommonAsyncTask = null;
		}

	}


}

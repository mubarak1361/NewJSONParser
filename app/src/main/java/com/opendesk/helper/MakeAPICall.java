package com.opendesk.helper;

import org.json.JSONObject;

import android.os.AsyncTask;

public class MakeAPICall extends AsyncTask<Void, Void, JSONObject>{

	private String url;
	private JSONObject jsonObject;
	private RequestType requestType;
	private OnResponseListener onCommonAsyncTask;
	private int id;

	private MakeAPICall(){
		super();
	}

	private MakeAPICall(String url,JSONObject jsonObject,RequestType requestType,OnResponseListener onCommonAsyncTask,int id) {
		super();
		this.url = url;
		this.jsonObject = jsonObject;
		this.requestType = requestType;
		this.onCommonAsyncTask = onCommonAsyncTask;
		this.id = id;
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
		onCommonAsyncTask.onSuccess(id,result);
	}

	public static class Builder implements APIBuilder<Builder> {

		private RequestType requestType;
		private String url;
		private JSONObject jsonPostObject;
		private OnResponseListener onCommonAsyncTask;
		private int id;

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
		public Builder getResponse(OnResponseListener onCommonAsyncTask) {
			this.onCommonAsyncTask = onCommonAsyncTask;
			return this;
		}

		@Override
		public Builder setId(int id) {
			this.id = id;
			return this;
		}

		@Override
		public void build() {
			new MakeAPICall(url, jsonPostObject, requestType, onCommonAsyncTask,id).execute();
		}

		private void resetData() {
			requestType = null;
			url = null;
			jsonPostObject = null;
			onCommonAsyncTask = null;
		}

	}


}

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

	public static class Create implements APIExecuter<Create>{

		private String endPoint;
		private RequestType requestType;
		private String urlPath;
		private JSONObject jsonPostObject;
		private OnResponseListener onCommonAsyncTask;
		private int tag;


		private Create(){

		}

		private Create(String endPoint){
			this.endPoint = endPoint;
		}

		@Override
		public Create setRequestType(RequestType requestType) {
			this.requestType = requestType;
			return this;
		}

		@Override
		public Create setURLPath(String urlPath) {
			this.urlPath = endPoint+urlPath;
			return this;
		}

		@Override
		public Create setPostData(JSONObject jsonPostObject) {
			this.jsonPostObject = jsonPostObject;
			return this;
		}

		@Override
		public Create getResponse(OnResponseListener onCommonAsyncTask) {
			this.onCommonAsyncTask = onCommonAsyncTask;
			return this;
		}

		@Override
		public Create setTag(int tag) {
			this.tag = tag;
			return this;
		}

		@Override
		public void run() {
			new MakeAPICall(urlPath, jsonPostObject, requestType, onCommonAsyncTask,tag).execute();
		}

	}

	public static class Builder implements APIBuilder<Builder,Create> {

		private String url;

		public Builder(){
		}

		@Override
		public Builder setEndPoint(String url) {
			this.url = url;
			return this;
		}

		@Override
		public Create build() {
			return new Create(url);
		}
	}


}

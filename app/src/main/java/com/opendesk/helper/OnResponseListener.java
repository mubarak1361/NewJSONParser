package com.opendesk.helper;

import org.json.JSONObject;

public interface OnResponseListener {
	public void onSuccess(int id, JSONObject jsonObject);
	public void OnFailure(int id,String info);
}

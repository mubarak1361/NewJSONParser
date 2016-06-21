package com.opendesk.helper;

import org.json.JSONObject;

/**
 * Created by CIPL0224 on 6/20/2016.
 */
public interface APIBuilder<T> {
    public T setRequestType(RequestType requestType);
    public T setEndPoint(String url);
    public T setPostData(JSONObject jsonObject);
    public T getResponse(OnResponseListener onCommonAsyncTask);
    public T setId(int id);
    public void build();
}

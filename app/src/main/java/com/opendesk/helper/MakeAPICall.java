package com.opendesk.helper;

import org.json.JSONObject;

/**
 * Created by CIPL0224 on 6/20/2016.
 */
public class MakeAPICall {


    private MakeAPICall() {

    }


    public static class Builder implements APIBuilder<Builder> {

        private static MakeAPICall makeAPICall;
        private RequestType requestType;
        private String url;
        private JSONObject jsonPostObject;
        private OnCommonAsyncTaskListener onCommonAsyncTask;

        public MakeAPICall Builder() {
            if (makeAPICall != null)
                makeAPICall = new MakeAPICall();
            resetData();
            return makeAPICall;
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
            new CommonAsyncTask(url, jsonPostObject, requestType, onCommonAsyncTask).execute();
        }

        private void resetData() {
            makeAPICall = null;
            requestType = null;
            url = null;
            jsonPostObject = null;
            onCommonAsyncTask = null;
        }

    }


}

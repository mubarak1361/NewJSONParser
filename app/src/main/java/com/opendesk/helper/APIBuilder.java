package com.opendesk.helper;

import org.json.JSONObject;

/**
 * Created by CIPL0224 on 6/20/2016.
 */
public interface APIBuilder<T,C> {
    public T setEndPoint(String url);
    public C build();
}

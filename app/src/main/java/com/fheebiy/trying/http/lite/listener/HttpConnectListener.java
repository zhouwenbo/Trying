package com.fheebiy.trying.http.lite.listener;

import com.fheebiy.trying.http.lite.request.Request;

/**
 * @author MaTianyu
 * @date 2014-11-06
 */
public interface HttpConnectListener {
    public void onPreConnect(Request req);
    public void onAfterConnect(Request req);
}

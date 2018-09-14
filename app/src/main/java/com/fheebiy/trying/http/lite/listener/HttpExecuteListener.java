package com.fheebiy.trying.http.lite.listener;

import com.fheebiy.trying.http.lite.exception.HttpException;
import com.fheebiy.trying.http.lite.request.Request;
import com.fheebiy.trying.http.lite.response.Response;

/**
 * @author MaTianyu
 * @date 2014-11-06
 */
public interface HttpExecuteListener {
    public void onStart(Request req) throws HttpException;

    public void onEnd(Response res);

    public void onRetry(Request req, int max, int now);

    public void onRedirect(Request req);
}

package com.fheebiy.trying.http.lite.HttpHelper;

import com.fheebiy.trying.http.lite.request.Request;
import com.fheebiy.trying.http.urlconfig.UrlConfig;

/**
 * Created by Lenovo on 14-12-29.
 */
public class HttpHelper {



    public static Request getNewsReq(String sy_c){
        return new Request(UrlConfig.HOMEPAGE_NEWS).addUrlParam("sy_c",sy_c).addUrlParam("vc","4.0.6");
    }


}

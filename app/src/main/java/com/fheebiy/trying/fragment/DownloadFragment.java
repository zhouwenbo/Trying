package com.fheebiy.trying.fragment;

import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;
import com.fheebiy.trying.R;
import com.fheebiy.trying.http.Http;
import org.apache.commons.httpclient.util.HttpURLConnection;
import org.apache.http.NameValuePair;
import org.json.JSONObject;

import java.io.*;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by bob zhou on 14-9-3.
 */
public class DownloadFragment extends Fragment {

    private File cacheDir;

    private WebView myWebView;

    String html = "";

    private int count;

    private int successCount;

    private String body;

    private String content = "";

    private Button btn;

    private Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 100) {
                successCount++;
                body += content;
                if (successCount == count) {
                    html += body;
                    myWebView.loadDataWithBaseURL(null, html, "text/html", "utf-8", null);
                }
            }
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.download, container, false);
        myWebView = (WebView) view.findViewById(R.id.my_web_view);
        btn = (Button) view.findViewById(R.id.start_btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getHtml();

            }
        });
        return view;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    private String getHtml() {
        cacheDir = getActivity().getExternalCacheDir();
        String downUrl = "http://kanboxshare.com/interface/down.php?key=eqCHS3IqgPGLYZHVSUHZ1EPnHG2OSLlQ3tVzJnGq2&uid=966440028&djangoId=0F49C72AD68BE8B095EB47A8053E8B44293FFA1E&gcid=0F49C72AD68BE8B095EB47A8053E8B44293FFA1E&filename=template.txt&_randomstr=1409717063167";
        String cachePath = cacheDir.getPath();
        for (int i = 0; i < 7; i++) {
            String fileName = "srp_" + i + "_time" + new Date().getTime() + ".txt";
            String filePath = cachePath + "/" + fileName;
            File templateFile = new File(filePath);
            if (i % 2 == 0) {
                try {
                    templateFile.createNewFile();
                    // String content = downloadFile(downUrl, templateFile);
                    new MyThread(downUrl, templateFile).start();
                    html += content;
                    count++;
                } catch (IOException e) {
                    e.printStackTrace();
                }

            } else {
                html += "我像风一样自由";
            }
        }

        return html;
    }


    private String downloadFile(String url, File file) {
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        String contents = Http.postData(url, params).toString();
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(file);
            OutputStreamWriter osw = new OutputStreamWriter(fos, "utf-8");

            BufferedWriter bw = new BufferedWriter(osw);
            bw.write(contents);
            bw.flush();
            bw.close();
            osw.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        content = contents;
        handler.sendEmptyMessage(100);
        return contents;
    }

  /*  */

    /**
     * 根据URL下载文件,前提是这个文件当中的内容是文本,函数的返回值就是文本当中的内容
     * 1.创建一个URL对象
     * 2.通过URL对象,创建一个HttpURLConnection对象
     * 3.得到InputStream
     * 4.从InputStream当中读取数据
     *
     * @param
     * @return
     *//*
    public String download(String urlStr) {
        StringBuffer sb = new StringBuffer();
        String line = null;
        BufferedReader buffer = null;
        try {
            URL url = new URL(urlStr);
            HttpURLConnection urlConn = (HttpURLConnection) url.openConnection();
            buffer = new BufferedReader(new InputStreamReader(urlConn.getInputStream()));
            while ((line = buffer.readLine()) != null) {
                sb.append(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
           *//* try {

            } catch (IOException e) {
                e.printStackTrace();
            }*//*
        }
        return sb.toString();
    }*/


    class MyThread extends Thread {

        private String downUrl;

        private File templateFile;


        MyThread(String downUrl, File templateFile) {
            this.downUrl = downUrl;
            this.templateFile = templateFile;
        }

        @Override
        public void run() {
            downloadFile(this.downUrl, this.templateFile);
        }
    }

}
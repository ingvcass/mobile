package com.example.lab13;

import android.app.Activity;
import android.util.Log;

import java.io.BufferedInputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.function.Consumer;

public class HttpRequest {
    private final Activity activity;
    private final Consumer<String> successCallback;
    private final Consumer<String> errorCallback;

    public HttpRequest(Activity activity, Consumer<String> successCallback, Consumer<String> errorCallback) {
        this.activity = activity;
        this.successCallback = successCallback;
        this.errorCallback = errorCallback;
    }

    class Worker implements Runnable {
        String target;

        public Worker(String target) {
            this.target = target;
        }

        @Override
        public void run() {
            try {
                URL url = new URL(target);
                HttpURLConnection connection = (HttpURLConnection)url.openConnection();
                connection.setRequestMethod("GET");
                connection.setReadTimeout(2000);
                connection.connect();
                BufferedInputStream inputStream = new BufferedInputStream(connection.getInputStream());

                byte[] buff = new byte[100];
                StringBuilder res = new StringBuilder();

                while(true) {
                    int n = inputStream.read(buff);
                    if (n <= 0) break;
                    res.append(new String(buff, 0, n));
                }

                connection.disconnect();
                activity.runOnUiThread(() -> successCallback.accept(res.toString()));

            }
            catch (Exception e) {
                activity.runOnUiThread(() ->errorCallback.accept(e.getMessage()));
            }
        }
    }

    public void makeRequest(String url) {
        Thread thread = new Thread(new Worker(url));

        thread.start();
    }
}
package com.threads;

import com.data.DataObjectImp;
import com.data.DataObject;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;

public class ConfirmIPThread extends Thread {
    private DataObjectImp data ;
    private LinkedBlockingDeque<DataObject> queue ;



    public ConfirmIPThread(DataObjectImp data, LinkedBlockingDeque<DataObject> queue){
        this.data = data;
        this.queue = queue;
    }

    @Override
    public void run(){
        if(data==null) throw new NullPointerException("未设置DataObjectImp");
        Proxy.Type type = data.getProtocols().equals("HTTP") ? Proxy.Type.HTTP : Proxy.Type.SOCKS;
        String hostname = data.getIP();
        int port = data.getPort();
        //构造client，设置超时以及代理
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(3*1000, TimeUnit.SECONDS)
                .proxy(new Proxy(type,new InetSocketAddress(hostname,port)))
                .build();
        //构造请求，设置URL
        Request r  = new Request.Builder()
                .url("https://www.amoshuang.com")
                .build();
        try{
            Response response = null;
            try{
                long start = System.currentTimeMillis();
                response = client.newCall(r).execute();
                long cost = System.currentTimeMillis() - start;
                data.setResponseTime((int)cost/1000);
            }catch (IOException e ){
                e.printStackTrace();
            }
            if(response != null && response.isSuccessful() && response.body() != null){
                String result = response.body().string();
                if(result.equals(hostname)){
                    try {
                        queue.put(data);
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }
                }
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}

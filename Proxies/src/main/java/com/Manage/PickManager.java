package com.Manage;

import com.data.DataObject;

import java.util.HashMap;
import java.util.concurrent.LinkedBlockingDeque;

public class PickManager {
    private  LinkedBlockingDeque<DataObject> queue;


    public PickManager(LinkedBlockingDeque<DataObject> queue){
        this.queue = queue;
    }

    public String pickOne(){
        try {
            DataObject data = queue.take();
            return data.toString();
        }catch (InterruptedException e) {
            return "";
        }
    }

    public HashMap<String,String> pickOneMap(){
        HashMap<String,String> object = new HashMap<>();
        try{
            DataObject data = queue.take();
            object.put("protocols",data.getProtocols());
            object.put("ip",data.getIP());
            object.put("port",String.valueOf(data.getPort()));
            object.put("locate",data.getLocate());
            object.put("responseTime",String.valueOf(data.getResponseTime()));
        }catch (InterruptedException e) {
            e.printStackTrace();
        }
        return object;
    }
}

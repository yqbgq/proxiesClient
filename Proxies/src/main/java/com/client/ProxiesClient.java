package com.client;


import com.Manage.ConfirmManage;
import com.Manage.GetManage;
import com.Manage.PickManager;
import com.data.DataObject;

import java.util.HashMap;
import java.util.concurrent.LinkedBlockingDeque;

public class ProxiesClient {
    private static boolean start = false;
    private static boolean work = false;
    private static int confirmThreadsSize = 2;

    private static int getThreadsSize = 1;

    public int getConfirmThreadsSize() {
        return confirmThreadsSize;
    }

    public int getGetThreadsSize() {
        return getThreadsSize;
    }

    public static void setConfirmThreadsSize(int size) {
        confirmThreadsSize = size;
    }

    public static void setGetThreadsSize(int size) {
        getThreadsSize = size;
    }

    //用于生产者和消费者模式的队列，可以在多线程下进行无锁阻塞
    private static LinkedBlockingDeque<DataObject> queue = new LinkedBlockingDeque<>();

    //已经验证过IP的代理
    private static LinkedBlockingDeque<DataObject> confirmedQueue = new LinkedBlockingDeque<>();


    private static PickManager pm =new PickManager(confirmedQueue);

    private static GetManage gm = new GetManage(queue,getThreadsSize);

    private static ConfirmManage cm = new ConfirmManage(queue,confirmedQueue);


    public static String pickOne(){
        if(start){
            return pm.pickOne();
        }else{
            throw new NullPointerException("请首先调用client的start()启动client！");
        }
    }

    public static HashMap<String, String> pickOneMap(){
        if(start){
            return pm.pickOneMap();
        }else{
            throw new NullPointerException("请首先调用client的start()启动client！");
        }
    }


    public static void main(String[] args){
        gm.start();

        try {
            Thread.sleep(5*1000);
            gm.changeFlag();
            System.out.println(gm.isAlive());
            System.out.println(queue.take());
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void stop(){
        gm.changeFlag();
        cm.changeFlag();
        work = false;
    }

    public static void start(){
        if(!start){
            gm.start();
            cm.start();
            start = true;
            work = true;
        }else{
            throw new NullPointerException("client已经启动了，如需重启请调用restart()方法");
        }
    }
}

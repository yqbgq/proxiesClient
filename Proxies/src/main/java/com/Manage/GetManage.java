package com.Manage;

import com.data.DataObject;
import com.threads.GetThread;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;

public class GetManage extends Thread {
    private volatile boolean flag = true;
    private volatile boolean start = false;
    private LinkedBlockingDeque<DataObject> queue ;


    private int size;

    public GetManage(LinkedBlockingDeque<DataObject> queue, int size){
        this.queue = queue;
        this.size = size;
    }

    @Override
    public void run(){
        if(queue==null) throw new NullPointerException();
        start = true;
        ExecutorService exe = Executors.newFixedThreadPool(size);
        while(flag){
            GetThread gt = new GetThread();
            gt.setArgs(queue);
            exe.execute(gt);
            try{
                Thread.sleep(3*1000);
            }catch (Exception e){
                e.printStackTrace();
            }

        }
    }

    public void changeFlag(){
        if(start) {
            flag = false;
            try {
                this.join();
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }else{
            throw new NullPointerException("请首先调用client的start()启动client！");
        }
    }

}

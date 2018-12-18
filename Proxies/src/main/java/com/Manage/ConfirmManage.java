package com.Manage;

import com.data.DataObject;
import com.data.DataObjectImp;
import com.threads.ConfirmIPThread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;

public class ConfirmManage extends Thread {
    private LinkedBlockingDeque<DataObject> beforeQueue;
    private LinkedBlockingDeque<DataObject> afterQueue;
    private volatile boolean start = false;
    private volatile boolean flag = true;
    //创建线程池exe用于执行任务
    private ExecutorService exe = Executors.newFixedThreadPool(10);

    public ConfirmManage(LinkedBlockingDeque<DataObject> queue1,LinkedBlockingDeque<DataObject> queue2){
        this.beforeQueue = queue1;
        this.afterQueue = queue2;
    }

    @Override
    public void run(){
        if(beforeQueue==null) throw new NullPointerException();
        if(afterQueue==null) throw new NullPointerException();
        while(flag){
                try {
                    DataObjectImp data = (DataObjectImp)beforeQueue.take();
                    exe.execute(new ConfirmIPThread(data,afterQueue));
                }catch (InterruptedException e){
                    e.printStackTrace();
                }

        }
    }

    public void changeFlag(){
        if(start) {
            flag = false;
        }else{
            throw new NullPointerException("请首先调用client的start()启动client！");
        }
    }
}

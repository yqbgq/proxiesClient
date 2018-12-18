package com.threads;

import com.data.DataObjectImp;
import com.data.DataObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.*;
import java.util.Properties;
import java.util.Random;
import java.util.concurrent.LinkedBlockingDeque;

public class GetThread extends Thread {

    private LinkedBlockingDeque<DataObject> queue = null;


    private static Properties props = new Properties();


    public void setArgs(LinkedBlockingDeque<DataObject> queue){
        this.queue = queue;
    }

    @Override
    public void run(){
        Random rand = new Random();
        if(queue == null) throw new NullPointerException("为设置阻塞队列");
        try{
            FileInputStream in = new FileInputStream("config.properties");
            props.load(in);
        }catch (Exception e){
            e.printStackTrace();
        }
        Document doc;
        String[] result;
        String baseUrl = props.getProperty("flag").equals("true") ? props.getProperty("anonymous") : props.getProperty("normal");
        String url = baseUrl + (rand.nextInt(20)+1)+"/";
        try{
            doc = Jsoup.connect(url).header("User-Agent", props.getProperty("User-Agent"))
                    .header("Cookie", props.getProperty("Cookie"))
                    .header("Referer", props.getProperty("Referer"))
                    .header("Host",props.getProperty("Host"))
                    .header("Upgrade-Insecure-Requests",props.getProperty("Upgrade-Insecure-Requests"))
                    .get();
            Elements elements = doc.getElementsByTag("tr");
            for(int i=1;i<elements.size();i++){
                result = elements.get(i).text().split(" ");
                DataObjectImp data = new DataObjectImp(result[0],result[4],Integer.parseInt(result[1]),result[2]);
                try{
                    queue.put(data);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}

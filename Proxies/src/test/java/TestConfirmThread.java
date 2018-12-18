import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.junit.Test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.concurrent.TimeUnit;

public class TestConfirmThread {

    @Test
    public void confirm(){
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(10*1000, TimeUnit.SECONDS)
                .proxy(new Proxy(Proxy.Type.HTTP,new InetSocketAddress("221.7.255.167",80)))
                .build();
        Request r  = new Request.Builder()
                .url("https://api.ipify.org/")
                .build();
        try{
            Response response = client.newCall(r).execute();
            if(response.body()!= null) {
                String result = response.body().string();
                if(result.equals("221.7.255.167")){
                    System.out.println(result);
                }
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}

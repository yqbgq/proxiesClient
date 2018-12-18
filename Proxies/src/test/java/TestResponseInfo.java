import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

public class TestResponseInfo {

    @Test
    public void test() throws Exception{
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(10*1000, TimeUnit.SECONDS)
                .build();
        Request r  = new Request.Builder()
                .url("https://www.baidu.com")
                .build();

        Response response = client.newCall(r).execute();
        System.out.println(response.headers());
        System.out.println(r);
    }
}

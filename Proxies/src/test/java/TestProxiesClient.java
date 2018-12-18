import com.client.ProxiesClient;
import org.junit.Test;

public class TestProxiesClient {
    @Test
    public void test(){
        ProxiesClient.start();
        System.out.println(ProxiesClient.pickOne());
    }
}

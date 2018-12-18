import org.junit.Test;

import java.io.FileInputStream;
import java.util.Properties;

public class TestProperties {



    @Test
    public void test(){
        Properties props = new Properties();
        try{
            FileInputStream in = new FileInputStream("config.properties");
            props.load(in);
        }catch (Exception e){
            e.printStackTrace();
        }
        System.out.println(props.getProperty("User-Agent"));
    }
}

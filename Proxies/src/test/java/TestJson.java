import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.junit.Test;

public class TestJson {

    @Test
    public void test(){
        JsonObject object=new JsonObject();
        object.addProperty("ip","fuck");
        System.out.println(object.toString());
    }
}

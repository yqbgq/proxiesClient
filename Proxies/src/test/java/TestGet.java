import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.junit.Test;

import java.io.IOException;

public class TestGet {

    @Test
    public void test() throws IOException {
        Document doc = Jsoup.connect("https://www.xicidaili.com/nt/2").get();
        Elements elements = doc.getElementsByTag("tr");
        for(int i= 1;i<elements.size();i++){
            String[] result = elements.get(i).text().split(" ");
            System.out.println(result[0] + "   " +result[1] + "   " + result[3]);
        }
    }
}

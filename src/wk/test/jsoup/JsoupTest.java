package wk.test.jsoup;

import java.io.File;
import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class JsoupTest {
    static String url="http://www.cnblogs.com/zyw-205520/archive/2012/12/20/2826402.html";
    /**
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        
    	File input = new File("d:\\��Ͽˮ��(600116)����ժҪ_���˲ƾ�_������.html");
    	Document doc = Jsoup.parse(input, "GBK", "");
    	
    	Element content = doc.getElementById("con02-4");
    	Elements a = content.getElementsByTag("td");
    	for (Element link : a) {
    		 
    		  String linkText = link.text();
    		  System.out.println(linkText);
    		}


    	

    }

    /**
     * ��ȡָ��HTML �ĵ�ָ����body
     * @throws IOException
     */
    private static void BolgBody() throws IOException {
        // ֱ�Ӵ��ַ��������� HTML �ĵ�
        String html = "<html><head><title> ��Դ�й����� </title></head>"
                + "<body><p> ������ jsoup ��Ŀ��������� </p></body></html>";
        Document doc = Jsoup.parse(html);
        System.out.println(doc.body());
        
        
        // �� URL ֱ�Ӽ��� HTML �ĵ�
        Document doc2 = Jsoup.connect(url).get();
        String title = doc2.body().toString();
        System.out.println(title);
    }

    /**
     * ��ȡ�����ϵ����±��������
     */
    public static void article() {
        Document doc;
        try {
            doc = Jsoup.connect("http://www.cnblogs.com/zyw-205520/").get();
            Elements ListDiv = doc.getElementsByAttributeValue("class","postTitle");
            for (Element element :ListDiv) {
                Elements links = element.getElementsByTag("a");
                for (Element link : links) {
                    String linkHref = link.attr("href");
                    String linkText = link.text().trim();
                    System.out.println(linkHref);
                    System.out.println(linkText);
                }
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
    /**
     * ��ȡָ���������µ�����
     */
    public static void Blog() {
        Document doc;
        try {
            doc = Jsoup.connect("http://www.cnblogs.com/zyw-205520/archive/2012/12/20/2826402.html").get();
            Elements ListDiv = doc.getElementsByAttributeValue("class","postBody");
            for (Element element :ListDiv) {
                System.out.println(element.html());
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    }

}
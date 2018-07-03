package crawler.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

public class NetUtil {
	
	public static String getHtmlSourceByUrl(String url, int num) {
		String html = "";
		
		if(url == null || url.equals("")) {
			LogUtil.downFunPrice.info("url获取异常,取消下载");
			return  html ;
		}
		
		
		
		for(int i = 0; i != num; i++) {
			html = NetUtil.getHtmlSourceByUrl(url);

			if(html == null || html.equals("")) {
				if(i == num-1) {
					LogUtil.downFunPrice.info("from:" + url + num+"次下载均失败");
					return html;
				}

				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			} else {

				LogUtil.downFunPrice.info("from:" + url + "第" + i + "下载成功");
				break;
			}
		}

		return html;
	}
	
	/**
	 * 根据网址，获取网页源码
	 * @param url 网址
	 * @return
	 */
	public static String getHtmlSourceByUrl(String url) {
		String htmlSource = new String();
		String linesep, htmlLine;
		linesep = System.getProperty("line.separator");
		htmlSource = "";
		try {
			java.net.URL source = new URL(url);
			InputStream in = new BufferedInputStream(source.openStream());
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			while ((htmlLine = br.readLine()) != null) {
				//htmlSource = htmlSource + htmlLine + linesep;
				htmlSource = htmlSource + htmlLine;
			}
			br.close();
			in.close();
		} catch (MalformedURLException muex) {

		} catch (Exception e) {

		}
		return htmlSource;

	}
	
	public static ArrayList<String> getListByUrl(String url) {
		ArrayList<String> rlt = new ArrayList<String>();
		String htmlLine = "";
		try {
			java.net.URL source = new URL(url);
			InputStream in = new BufferedInputStream(source.openStream());
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			while ((htmlLine = br.readLine()) != null) {
				rlt.add(htmlLine);
			}
			br.close();
			in.close();
		} catch (java.net.MalformedURLException muex) {

		} catch (Exception e) {

		}
		return rlt;

	}

	/**
	 * 根据图片网址，获取图片
	 * @param URLName 图片网址
	 * @param target 图片另存名
	 * @throws Exception
	 */
	public static void getUrlImg(String URLName, String target)
			throws Exception {
		int HttpResult = 0;
		URL url = new URL(URLName);
		URLConnection urlconn = url.openConnection();
		HttpURLConnection httpconn = (HttpURLConnection) urlconn;
		HttpResult = httpconn.getResponseCode();
		//System.out.println(HttpResult);
		if (HttpResult != HttpURLConnection.HTTP_OK) {
		} else {

			int filesize = urlconn.getContentLength();
			BufferedInputStream bis = new BufferedInputStream(urlconn
					.getInputStream());
			BufferedOutputStream bos = new BufferedOutputStream(
					new FileOutputStream(target));
			byte[] buffer = new byte[1024];
			int num = -1;
			while (true) {
				num = bis.read(buffer);
				if (num == -1) {
					bos.flush();
					break;
				}
				bos.flush();
				bos.write(buffer, 0, num);
			}
			bos.close();
			bis.close();
		}
	}
	
	public static Result downFile(String url, String filePath) {
		String html = getHtmlSourceByUrl(url);
		File f = new File(filePath);
		if(f.exists()) {
			return new Result(ResultFlag.Success,"文件存在");
		} else if(html==null || html.equals("")) {
			return new Result(ResultFlag.Fail,"文件下载为空");
		} else {
			return FileUtil.writeToFile(html, filePath, false);
		}
		

	}
}

package wk.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class FileUtil {
	
	/**
	 * 将数据写入文件
	 * @param source 写入文件的内容
	 * @param filePath 文件路径
	 * @param isAdd 是否追加 true追加
	 */
	public static Result writeToFile(String source, String filePath, boolean isAdd) {
		try {
			File targetFile = new File(filePath);
			if(!targetFile.exists()) {
				targetFile.createNewFile();
			}
			File file = new File(filePath);
			if (!file.exists()) {
				file.createNewFile();
			}
			FileWriter fw = new FileWriter(file, isAdd);
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(source);
			bw.close();
			fw.close();
			return new Result(ResultFlag.Success,filePath+"文件下载成功");
		} catch (FileNotFoundException e) {
			//e.printStackTrace();
			return new Result(ResultFlag.Fail,e+"/n"+filePath+"待写入文件不存在");
		} catch (IOException e) {
			//e.printStackTrace();
			return new Result(ResultFlag.Fail,e+"/n"+filePath+"文件下载失败");
		}
	}
	
	/**
	 * 从文件中读取数据
	 * @param filePath 文件路径
	 * @return
	 */
	public static String getFromFile(String filePath) {
		File file = new File(filePath);
		String htmlSource = "";
		try {
			FileReader fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);
			String line = "";
			while((line = br.readLine()) != null) {
				htmlSource = htmlSource + line;

			}
			br.close();
			fr.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return htmlSource;
	}
	
	public static ArrayList<String> getListFromFile(String filePath) {
		File file = new File(filePath);
		ArrayList<String> lines = new ArrayList<String>();
		try {
			FileReader fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);
			String line = "";
			while((line = br.readLine()) != null) {
				lines.add(line);
			}
			br.close();
			fr.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lines;
	}
	
	public static ArrayList<String> getListFromFile(File file) {
		ArrayList<String> lines = new ArrayList<String>();
		try {
			FileReader fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);
			String line = "";
			while((line = br.readLine()) != null) {
				lines.add(line);
			}
			br.close();
			fr.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lines;
	}

	
}

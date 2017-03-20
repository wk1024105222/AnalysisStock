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
	 * ������д���ļ�
	 * @param source д���ļ�������
	 * @param filePath �ļ�·��
	 * @param isAdd �Ƿ�׷�� true׷��
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
			return new Result(ResultFlag.Success,filePath+"�ļ����سɹ�");
		} catch (FileNotFoundException e) {
			//e.printStackTrace();
			return new Result(ResultFlag.Fail,e+"/n"+filePath+"��д���ļ�������");
		} catch (IOException e) {
			//e.printStackTrace();
			return new Result(ResultFlag.Fail,e+"/n"+filePath+"�ļ�����ʧ��");
		}
	}
	
	/**
	 * ���ļ��ж�ȡ����
	 * @param filePath �ļ�·��
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

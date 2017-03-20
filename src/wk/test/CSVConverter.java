package wk.test;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.Locale;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.WorkbookSettings;

public class CSVConverter {
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			OutputStream os = new FileOutputStream(new File("d:\\input.csv"));
			OutputStreamWriter osw = new OutputStreamWriter(os, "UTF8");
			BufferedWriter bw = new BufferedWriter(osw);
			// ����Excel�ļ�
			WorkbookSettings ws = new WorkbookSettings();
			ws.setLocale(new Locale("en", "EN"));
			Workbook wk = Workbook.getWorkbook(new File("��Ͽˮ��(600116)_�ʲ���ծ��.xls"), ws);
			// �ӹ�����(workbook)ȡ��ÿҳ(sheets)
			for (int sheet = 0; sheet < wk.getNumberOfSheets(); sheet++) {
				Sheet s = wk.getSheet(sheet);
				bw.write(s.getName());
				bw.newLine();
				Cell[] row = null;
				// ��ÿҳ(sheet)ȡ��ÿ������(Cell)
				for (int i = 0; i < s.getRows(); i++) {
					row = s.getRow(i);
					if(row.length > 0) {
						bw.write(row[0].getContents());
						for (int j = 1; j < row.length; j++) {
							bw.write(',');
							bw.write(row[j].getContents());
						}
					}
					bw.newLine();
				}
			}
			bw.flush();
			bw.close();

		} catch (Exception e) {
			System.err.println(e.toString());
			e.printStackTrace();
		}
	}
}
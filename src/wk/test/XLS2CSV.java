package wk.test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class XLS2CSV {

	public static void readXls(String path) {

		InputStream is;
		HSSFWorkbook hssfWorkbook;
		try {
			is = new FileInputStream(path);
			hssfWorkbook = new HSSFWorkbook(is);
			HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(0);

			int rownum = hssfSheet.getLastRowNum();
			int colnum = hssfSheet.getRow(0).getLastCellNum();

			for (int j = 0; j < colnum-1; j++) {
				for (int i = 0; i <= rownum; i++) {

					try {

						HSSFCell cell = hssfSheet.getRow(i).getCell(j);
						if(cell != null) {
							System.out.print(XLS2CSV.getValue(cell) + ";");

						} else {
							System.out.print(";");
						}

					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();

						continue;
					}
				}
				System.out.println();
			}
			is.close();
			hssfWorkbook.close();

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@SuppressWarnings("static-access")
	private static String getValue(HSSFCell hssfCell) {
		DecimalFormat decimalFormat = new DecimalFormat("0");// 格式化设置
		if(hssfCell.getCellType() == hssfCell.CELL_TYPE_BOOLEAN) {
			return String.valueOf(hssfCell.getBooleanCellValue());
		} else if(hssfCell.getCellType() == hssfCell.CELL_TYPE_NUMERIC) {
			return decimalFormat.format(hssfCell.getNumericCellValue());
		} else {
			return String.valueOf(hssfCell.getStringCellValue());
		}
	}

	public static void main(String[] args) throws Exception {
		XLS2CSV.readXls("d:\\三峡水利(600116)_利润表.xls");
	}
}
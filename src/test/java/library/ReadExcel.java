package library;

import java.io.FileInputStream;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class ReadExcel {
	public HSSFWorkbook workbook;
	public HSSFSheet sheet;
	public HSSFCell cell;
	String data;

	public String excelRead(int row,int column){

		try {
			FileInputStream fis=new FileInputStream("..//TestAutomationDVLA//src//test//java//testdata//dvla.xls");
			workbook =new HSSFWorkbook(fis);
			sheet=workbook.getSheet("Sheet1");
			cell=sheet.getRow(row).getCell(column);
			data=cell.getStringCellValue();
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		return data;
	}
}

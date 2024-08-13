package api.genericUtility;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelUtility {
	public String getDataFromExcel(String sheet_name,int row_num,int col_num) throws EncryptedDocumentException, IOException {
		FileInputStream  fis= new FileInputStream("./testdata/ORGANIZATION.xlsx");
		Workbook book=WorkbookFactory.create(fis);
		String data=book.getSheet("sheet_name").getRow(row_num).getCell(col_num).getStringCellValue();
		book.close();
		return data;
	}
}

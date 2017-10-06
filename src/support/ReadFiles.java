package support;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadFiles {
	private static XSSFWorkbook wordBook;
	private static XSSFSheet sheet;
	private static XSSFRow Row;
	private static XSSFCell cell;

	/***
	 * Description: Method use to read excel file form row 2sd and columns 2sd
	 * number columns equal number parameter
	 * 
	 * @param fileName
	 * @param sheetName
	 * @return String[][] Array
	 * @throws Exception
	 */
	public Object[][] getExcel(String filePath, String sheetName) throws Exception {
		String[][] Array = null;
		try {
			//get path
			FileInputStream file = new FileInputStream(filePath);
			// read file
			wordBook = new XSSFWorkbook(file);
			sheet = wordBook.getSheet(sheetName);
			
			int totalRows = sheet.getLastRowNum() - sheet.getFirstRowNum();
			Row = sheet.getRow(totalRows);
			int totalColumns = Row.getLastCellNum();
			// create array list
			Array = new String[totalRows][totalColumns-1];
			
			for (int i = 0; i < totalRows; i++) {
				for (int j = 0; j < totalColumns - 1; j++) {
					// get data from excel file 
					cell = sheet.getRow(i+1).getCell(j+1);
					// check code here
					if(cell == null) {
						Array[i][j] = "";
					} else {
						Array[i][j] = cell.getStringCellValue();	
					}
				}
			}
		} catch (FileNotFoundException e) {
			System.out.println("Could not read the Excel sheet");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Could not read the Excel sheet");
			e.printStackTrace();
		}
		return (Array);
	}
	
	/***
	 * Description: Method use to read excel file form row 2sd and columns 2sd
	 * number columns equal number parameter
	 * 
	 * @param fileName
	 * @param sheetName
	 * @return String[][] Array
	 * @throws Exception
	 */
	public Object[][] getExcel(String filePath, String sheetName, int startRow, int startColumn) throws Exception {
		String[][] Array = null;
		try {
			//get path
			FileInputStream file = new FileInputStream(filePath);
			// read file
			wordBook = new XSSFWorkbook(file);
			sheet = wordBook.getSheet(sheetName);
			
			int totalRows = sheet.getLastRowNum() - sheet.getFirstRowNum();
			Row = sheet.getRow(totalRows);
			int totalColumns = Row.getLastCellNum();
			// create array list
			Array = new String[totalRows][totalColumns];
			// *** check total > start
			// Co khi can reset lai so dong so cot
			for (int i = startRow; i < totalRows + 1; i++) {
				for (int j = startColumn; j < totalColumns; j++) {
					// get data from excel file 
					cell = sheet.getRow(i+1).getCell(j);
					// check code here
					if(cell == null) {
						Array[i][j] = "";
					} else {
						Array[i][j] = cell.getStringCellValue();	
					}
				}
			}
		} catch (FileNotFoundException e) {
			System.out.println("Could not read the Excel sheet");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Could not read the Excel sheet");
			e.printStackTrace();
		}
		return (Array);
	}
	
	
}

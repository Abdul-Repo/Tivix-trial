package myUtils;

import java.awt.AWTException;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.imageio.ImageIO;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import basePage.BasePage;

public class MyUtils extends BasePage {


	/*
	 * To get all options from Dropdown
	 */
	public static List<String> getAllOptionInSelect(WebElement element){
		Select select = new Select(element);
		List<WebElement> options = select.getOptions();
		List<String> list= new ArrayList<String>();
		for (int i=0; i<options.size(); i++){
			list.add(options.get(i).getText());
		}
		return list;
	}

	/*
	 * To select option from Dropdown by VisbleText
	 */
	public static void dropdownSelect(WebElement element, String value,  WebDriver driver){
		Select select = new Select(element);
		select.selectByVisibleText(value);
	}

	
	public static void takeScreenShot(String filename){
		Robot robot=null;
		try {robot = new Robot();} catch (AWTException e1) {e1.printStackTrace();}
		Dimension screenSize=Toolkit.getDefaultToolkit().getScreenSize();
		Rectangle rectangle = new Rectangle(screenSize);
		BufferedImage src = robot.createScreenCapture(rectangle);
		File dest = new File(prop.getProperty("screenshot")+filename+".png");
		try {ImageIO.write(src, "png", dest);} catch (IOException e) {e.printStackTrace();}
	}


	public static List<String> readExcel(String pathName){
		List<String> data= new ArrayList<String>();
		FileInputStream excel = null;
		try {excel = new FileInputStream(pathName);} catch (FileNotFoundException e1) {e1.printStackTrace();}		
		Workbook workbook = null;
		try {workbook = new XSSFWorkbook(excel);} catch (IOException e) {e.printStackTrace();}		
		Sheet sheet = workbook.getSheetAt(0);
		Iterator<Row> rowItrator = sheet.iterator();			
		while(rowItrator.hasNext()){
			Row rowValue=rowItrator.next();	
			Iterator<Cell> columnItrator= rowValue.iterator();	
			while(columnItrator.hasNext()){	
				Cell ColumnValue=columnItrator.next();
				data.add(ColumnValue.toString());
			}
		} 				return data;

	}



}

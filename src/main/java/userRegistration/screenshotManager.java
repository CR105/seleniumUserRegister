package userRegistration;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class screenshotManager {
	
	private boolean flagDebuggin;
	private int step;
	private String savePath;
	
	/**
	 * Method to instance class
	 * @param nameTest String Name of the directory test
	 * @param debuggin True don't take screenshot, False take screenshot.
	 */
	public screenshotManager(String nameTest, boolean debuggin) {
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat dateOnly = new SimpleDateFormat("yyyyMMdd");
		SimpleDateFormat timeOnly = new SimpleDateFormat("HHmmss");
		File file = createDir("Test" + "_" + evalStr(nameTest) + "_" + dateOnly.format(cal.getTime()) + "." + timeOnly.format(cal.getTime()));
		this.step = 0;
		this.savePath = file.toString();
		this.flagDebuggin = debuggin;
	}
	
	/**
	 * Method to take screenshot
	 * @param driver WebDriver Selenium WebDriver.
	 * @param nameStep String name of step or evidence.
	 * @return integer 1 successfully take screenshot, -1 some error. 
	 */
	public int takeScreen(WebDriver driver, String nameStep) {
		if (flagDebuggin){
			return 0;
		}
		else {
			nameStep = evalStr(nameStep);
			if (TakeScreen(driver,  savePath + "/" + String.format("%03d", step) + " - " + nameStep +".png")) {
				++step;
				return 1;
			}
			else {
				return -1;
			}
		}
	}
	
	/**
	 * 
	 * @param driver WebDriver Selenium WebDriver.
	 * @param intStep Integer Step
	 * @param nameStep String name of step or evidence.
	 * @return integer 1 successfully take screenshot, -1 some error. 
	 */
	public int takeScreen(WebDriver driver, int intStep, String nameStep) {
		if (flagDebuggin){
			return 0;
		}
		else {
			nameStep = evalStr(nameStep);
			if (TakeScreen(driver,  savePath + "/" + String.format("%03d", intStep) + " - " + nameStep +".png")) {
				return 1;
			}
			else {
				return -1;
			}
		}
	}
	
	/**
	 * 
	 * @param driver WebDriver Selenium WebDriver.
	 * @param strStep String customer Step.
	 * @param nameStep String name of step or evidence.
	 * @return integer 1 successfully take screenshot, -1 some error.
	 */
	public int takeScreen(WebDriver driver, String strStep, String nameStep) {
		if (flagDebuggin){
			return 0;
		}
		else {
			nameStep = evalStr(nameStep);
			if (TakeScreen(driver,  savePath + "/" + strStep + " - " + nameStep +".png")) {
				return 1;
			}
			else {
				return -1;
			}
		}
	}
	
	/**
	 * Method to screen shot with full name like parameter
	 * @param driver WebDriver Selenium WebDriver.
	 * @param fileName full name of screenshot
	 * @return boolean
	 */
	private boolean TakeScreen(WebDriver driver, String fileName) {
		try {
			TakesScreenshot scrShot =((TakesScreenshot)driver);
			File SrcFile=scrShot.getScreenshotAs(OutputType.FILE);
			File DestFile=new File(fileName);
			FileUtils.copyFile(SrcFile, DestFile);
			return true;
		} catch (Exception e) {
			System.out.println("Exception in save Screenshot: " + e.toString());
			return false;
		}
	}
	
	/**
	 * Method evaluate parameter String an return to upper case.
	 * @param nameStep
	 * @return
	 */
	private String evalStr(String strValue) {
		String newStrValue = strValue != "" ? strValue.replace(" ", "_").toUpperCase() : "NOTHING"; 
		return newStrValue;
	}
	
	/**
	 * Method to create test directory.
	 * @param newDir name folder of test directory.
	 * @return File full name of directory.
	 */
	private File createDir(String newDir){
		File directory = null;
	    String basePath = new File("src/test/resources").getAbsolutePath();
	    
	    try{
	    	directory = new File(basePath + "/" + newDir);
		    if (! directory.exists()){
		        directory.mkdir();
		    }
	    }
	    catch (Exception e){
	        e.printStackTrace();
	    }
	    return directory;
	}

}

package userRegistration;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class pom_userRegistration {
	public WebDriver driver;
	
	/**
   * This method is used to set up driver.
   * @param timeOut This is parameter to define explicit wait (in seconds).
   * @param kindBrowser  This is a parameter to select kind of browser (FIREFOX, EDGE, IE, OPERA, CHROME).
   * @param window This is a parameter to select size of window ("", MAX or FULL).
   * @return boolean True is was passed, False is was failed.
   */
	public boolean setDriver(int timeOut, String kindBrowser, String window) {
		String driverPath = "";
		String driverPropertie = "";
		File rfile =new File("");
		
        try {
        	switch(kindBrowser.toUpperCase()) {
    		case "FIREFOX":
    			rfile = new File("src/main/resources/geckodriver");
    			driverPath = rfile.getAbsolutePath();
    			driverPropertie = "webdriver.gecko.driver";
    			System.setProperty(driverPropertie, driverPath);
    			driver = new FirefoxDriver();
    		    break;
    		case "EDGE":
    			driverPath = "";
    			driverPropertie = "webdriver.edge.driver";
    			break;
    		case "IE":
    			driverPath = "";
    			driverPropertie = "webdriver.ie.driver";
    			break;
    		case "OPERA":
    			driverPath = "";
    			driverPropertie = "webdriver.opera.driver";
    			break;
    		case "CHROME":
    			rfile = new File("src/main/resources/chromedriver");
    			driverPath = rfile.getAbsolutePath();
    			driverPropertie = "webdriver.chrome.driver";
    			System.setProperty(driverPropertie, driverPath);
    			driver = new ChromeDriver();
    			break;
    		default:
    			rfile = new File("src/main/resources/chromedriver");
    			driverPath = rfile.getAbsolutePath();
    			driverPropertie = "webdriver.chrome.driver";
    			System.setProperty(driverPropertie, driverPath);
    			driver = new ChromeDriver();
    		}
            
            driver.manage().deleteAllCookies();
            
            // Set implicitly wait (timeout)
            if (timeOut !=0) {
            	driver.manage().timeouts().implicitlyWait(timeOut, TimeUnit.SECONDS);
            }
            
            // Set window size
            if (window.toUpperCase() == "MAX"){
            	driver.manage().window().maximize();
            }
            else if (window.toUpperCase() == "FULL") {
            	driver.manage().window().fullscreen();
            }
        	return true;
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Exception in set driver: " + e.toString());
			return false;
		}
	}
	
	/**
	 * This method is used to set URL.
	 * @param baseUrl This is a parameter to set URL.
	 * @return boolean True is was passed, False is was failed.  
	 */
	public boolean setURL(String baseUrl){
				
        try {
        	driver.get(baseUrl);
		  
        	WebDriverWait wait = new WebDriverWait(driver, 20);
        	wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("loginBaseLogo")));
		
        	return true;
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Exception in set URL: " + e.toString());
			return false;
		}
	}

	/**
	 * Method to login in a Catalyst page.
	 * @param user String with user name.
	 * @param pass String with password.
	 * @return boolean True is was passed, False is was failed.
	 */
	public boolean login(String user, String pass){
		
        try {
        	// Type User name
    		driver.findElement(By.xpath("//*[@id=\'idToken1\']")).sendKeys(user);
    		// Type Password
            driver.findElement(By.xpath("//*[@id=\'idToken2\']")).sendKeys(pass);
            
            // Click login button
            driver.findElement(By.xpath("//*[@id=\'loginButton_0\']")).click();
            
            WebDriverWait wait = new WebDriverWait(driver, 20);
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#appContentWrapper > div > div > div > div > p > button")));
			
        	return true;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Exception in login: " + e.toString());
			return false;
		}
	}

	/**
	 * Method to select module Administrator.
	 * @return boolean True is was passed, False is was failed.
	 */
	public boolean admin(){
		
        try {
        	driver.findElement(By.id("__BVID__13__BV_button_")).click();
            driver.findElement(By.xpath("//*[@id=\'__BVID__13\']/div/a[2]")).click();
            
            WebDriverWait wait = new WebDriverWait(driver, 20);
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#content > div:nth-child(1) > div > h1")));
			
        	return true;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Exception in select admin: " + e.toString());
			return false;
		}
	}
	
	/**
	 * Method to select Manage Users section.
	 * @return boolean True is was passed, false is was failed.
	 */
	public boolean manageUsers() {
		
        try {
        	driver.findElement(By.xpath("//*[@id=\'dashboardWidgets\']/div/div/div[2]/div/div/div[7]/div/div/div/a")).click();
    		
    		WebDriverWait wait = new WebDriverWait(driver, 20);
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#managedViewTable_container > div.page-header > h1")));
        	return true;
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Exception in manage users:" + e.toString());
			return false;
		}
	}
	
	/**
	 * Method to use for add new users in Catalyst.
	 * @param userName String user name of new user(It's recommended use part of email) 
	 * @param firstName String first name of new user
	 * @param fatherLastName String father last name of new user
	 * @param motherLastName String mother last name of new user
	 * @param email String email of new user
	 * @param passWord String password of new user
	 * @return Nothing
	 */
	public boolean addNewUser(String userName, String firstName, String fatherLastName, String motherLastName, String email, String passWord) {
		
		try {
			//Click New User 
			driver.findElement(By.xpath("//*[@id=\'managedViewTable_container\']/div[2]/div[1]/a/button")).click(); 
			
			//Set User name
			driver.findElement(By.id("0-root-userName")).sendKeys(userName);

			// Set FirtsName				
			driver.findElement(By.id("0-root-givenName")).sendKeys(firstName);
			
			// Set  LastName
			driver.findElement(By.id("0-root-sn")).sendKeys(fatherLastName + " " + motherLastName);
			
			// Set Email Address
			driver.findElement(By.id("0-root-mail")).sendKeys(email);
			driver.findElement(By.id("0-root-mail")).click();
			
			// Set password
			driver.findElement(By.id("input-password")).sendKeys(passWord);
			driver.findElement(By.id("input-password")).click();
			
			// Confirm password
			driver.findElement(By.id("input-confirmPassword")).sendKeys(passWord);
			driver.findElement(By.id("input-confirmPassword")).click();
			
			driver.findElement(By.id("input-password")).sendKeys(Keys.TAB);
			driver.findElement(By.id("input-confirmPassword")).sendKeys(Keys.TAB);
			
			// Send Data
			driver.findElement(By.id("passwordSaveBtn")).click();
			
			WebDriverWait wait = new WebDriverWait(driver, 20);
	        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("root[telephoneNumber]")));
			
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Exception in add new user: " + e.toString());
			return false;
		}		
       
	}
	
	/**
	 * Method to use to return user lists
	 * @return boolean
	 */
	public boolean backListUser(){
		
		try {
			// Back users list
			WebDriverWait wait = new WebDriverWait(driver, 20);
			
			driver.get("https://sabadell.catalyst.forgerock.org/admin/#resource/managed/user/list/");
			
	        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#managedViewTable_container > div.page-header > h1")));
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Exception in back to list users: " + e.toString());
			return false;
		}
		
	}
	
	/**
	 * Method to search existing user
	 * @param username
	 * @return boolean 
	 */
	public boolean searchUser(String username){
		
		try {
			driver.findElement(By.xpath("//*[@id=\"managedViewTable\"]/table/thead/tr/th[2]/form/input")).click();
			driver.findElement(By.xpath("//*[@id=\"managedViewTable\"]/table/thead/tr/th[2]/form/input")).sendKeys(username);
			driver.findElement(By.xpath("//*[@id=\"managedViewTable\"]/table/thead/tr/th[2]/form/input")).sendKeys(Keys.ENTER);
			
			WebDriverWait wait = new WebDriverWait(driver, 20);
			wait.until(ExpectedConditions.numberOfElementsToBe(By.cssSelector("#managedViewTable > table > tbody > tr > td.select-row-cell.renderable"), 1));
			
			driver.findElement(By.cssSelector("#managedViewTable > table > tbody > tr > td:nth-child(2)")).click();
			
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#content > div > div:nth-child(2) > div > div > div.media-body.media-top > h1")));
			
			return true;
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Exception in search User: " + e.toString());
			return false;
		}
		
	}

	/**
	 * 
	 * @param phoneNumber
	 * @return boolean
	 */
	public boolean addPhoneNumber(String phoneNumber){
		
		try {
			driver.findElement(By.name("root[telephoneNumber]")).clear();
			driver.findElement(By.name("root[telephoneNumber]")).sendKeys(phoneNumber);	
		
			driver.findElement(By.xpath("//*[@id=\"resource-details\"]/div[2]/div[2]/a[2]")).click();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Exception in addPhoneNumber: " + e.toString());
			return false;
		}

	}

	/**
	 * Method to logout
	 * @return boolean
	 */
	public boolean logout() {
		
		try {
			driver.findElement(By.cssSelector("#navbarBrand > a > img")).click();
			
			driver.findElement(By.cssSelector("#loginContent > li:nth-child(2) > a")).click();
			driver.findElement(By.cssSelector("#logout_link")).click();

			WebDriverWait wait = new WebDriverWait(driver, 20);
	        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#loginBaseLogo > img")));
	        
	        driver.close();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Exception in logout: " + e.toString());
			return false;
		}
		
	}
	
	/**
	 * Method to change phone number.
	 * @param newpass
	 * @return
	 */
	public boolean changePass(String newpass) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, 20);
			driver.findElement(By.cssSelector("#tabHeader_password > a")).click();
			
			driver.findElement(By.id("input-password")).sendKeys(newpass);
			driver.findElement(By.id("input-confirmPassword")).sendKeys(newpass);
			
			driver.findElement(By.id("input-password")).sendKeys(Keys.TAB);
			driver.findElement(By.id("input-confirmPassword")).sendKeys(Keys.TAB);

			wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#password > div:nth-child(2) > div:nth-child(1) > strong:nth-child(2)")));
			
			driver.findElement(By.id("passwordSaveBtn")).click();
			
			wait.until(ExpectedConditions.elementToBeClickable(By.id("0-root-userName")));
			
			return true;
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Exception in add new user: " + e.toString());
			return false;
		}
	}
	
	/**
	 * 
	 * @return boolean
	 */
	public boolean close() {
		try {
			driver.close();
			return true;
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Exception in close: " + e.toString());
			return false;
		}
		
	}

}

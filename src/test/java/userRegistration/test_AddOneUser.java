package userRegistration;


import java.io.File;
import java.io.FileReader;
import java.util.Arrays;
import java.util.List;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class test_AddOneUser {
	private final pom_userRegistration CatObj;
	private final String browser;
	private final String URL;
	private final String user;
	private final String passw;
	private final String userName, firstName, fatherLastName, motherLastName, email, passWord, phoneNumber;
	
	public test_AddOneUser(String browser, String URL, String user, String passw, String userName, String firstName,
			String fatherLastName, String motherLastName, String email, String passWord, String phoneNumber) {
		this.CatObj = new pom_userRegistration();
		this.browser = browser;
		this.URL = URL;
		this.user = user;
		this.passw = passw;
		this.userName = userName;
		this.firstName = firstName;
		this.fatherLastName = fatherLastName;
		this.motherLastName = motherLastName;
		this.email = email;
		this.passWord = passWord;
		this.phoneNumber = phoneNumber;
	}
	
	@Parameters
	public static List<Object []> data(){
		JSONObject jsonObject;
		try {
			
			FileReader reader = new FileReader(new File("src/test/resources/dataOneUserRegistration.json").getAbsolutePath());
		    JSONParser jsonParser = new JSONParser();
			jsonObject = (JSONObject) jsonParser.parse(reader);
		} catch (Exception e) {
			System.out.println("Exeception in read Json File: " + e.toString());
			return null;
		}
		return Arrays.asList(new Object[][] {
            { jsonObject.get("browser"), jsonObject.get("url"), jsonObject.get("user"), jsonObject.get("password"), 
            	jsonObject.get("userName"), jsonObject.get("firstName"), jsonObject.get("fatherLastName"),
            	jsonObject.get("motherLastName"), jsonObject.get("email"), jsonObject.get("passWord"), jsonObject.get("phoneNumber")}
		});
	}
	
	@Test
	public void test_addUser() {
		screenshotManager tsObj = new screenshotManager("Add One User", false);
		
		Assert.assertEquals(true, CatObj.setDriver(20, browser, ""));
		tsObj.takeScreen(CatObj.driver, "");
		
    	Assert.assertEquals(true, CatObj.setURL(URL));
    	tsObj.takeScreen(CatObj.driver, "Set URL");
    	
        Assert.assertEquals(true, CatObj.login(user, passw));
        tsObj.takeScreen(CatObj.driver, "Login");
        
        Assert.assertEquals(true, CatObj.admin());
        tsObj.takeScreen(CatObj.driver, "Admin");
        
        Assert.assertEquals(true, CatObj.manageUsers());
        tsObj.takeScreen(CatObj.driver, "Manager Users");
        
		Assert.assertEquals(true, CatObj.addNewUser(userName, firstName, fatherLastName, motherLastName, email, passWord));
		tsObj.takeScreen(CatObj.driver, "Add New User");
		
		Assert.assertEquals(true, CatObj.addPhoneNumber(phoneNumber));
		tsObj.takeScreen(CatObj.driver, "Add Phone Number");
		
		Assert.assertEquals(true, CatObj.backListUser());
		tsObj.takeScreen(CatObj.driver, "Back to List");
		
		Assert.assertEquals(true, CatObj.close());
	}

}

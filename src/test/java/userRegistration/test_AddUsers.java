package userRegistration;


import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class test_AddUsers {
	private final pom_userRegistration CatObj;
	private final String browser;
	private final String URL;
	private final String user;
	private final String passw;
	private final String userName, firstName, fatherLastName, motherLastName, email, passWord, phoneNumber;
	
	public test_AddUsers(String browser, String URL, String user, String passw, String userName, String firstName,
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
	
	@SuppressWarnings("resource")
	@Parameters
	public static List<String []> data(){
		String[][] csvdata = new String[2][11];
		String line = "";
		String splitBy = ",";
		int indx = 0;
		String pathFile = "/userRegistration/src/test/resources/dataAddUsers.csv";
		try {
			BufferedReader br = new BufferedReader(new FileReader(pathFile));
	        while ((line = br.readLine()) != null)
	        {  
	        	String[] newUser = line.split(splitBy);
	        	if (! newUser[0].matches("BROWSER")) {
	        		System.out.println("Browser = " + newUser[0] + 
		            		", URL = " + newUser[1] + 
		            		", User = " + newUser[2] + 
		            		", Pass = " + newUser[3] + 
		            		", username = " + newUser[4] + 
		            		", name = " + newUser[5] + 
		            		", Father Last Name = " + newUser[6] +
		            		", Mother Last Name = " + newUser[7] +
		            		", Email = " + newUser[8] +
		            		", Pass = " + newUser[9] +
		            		", Phone = " + newUser[10]);
	        		
	        		csvdata[indx][0] = newUser[0];
	        		csvdata[indx][1] = newUser[1];
	        		csvdata[indx][2] = newUser[2];
	        		csvdata[indx][3] = newUser[3];
	        		csvdata[indx][4] = newUser[4];
	        		csvdata[indx][5] = newUser[5];
	        		csvdata[indx][6] = newUser[6];
	        		csvdata[indx][7] = newUser[7];
	        		csvdata[indx][8] = newUser[8];
	        		csvdata[indx][9] = newUser[9];
	        		csvdata[indx][10] = newUser[10];
	        		++indx;
	        	}
	        }
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Exeception in read Data File: " + e.toString());
			return null;
		}
		return Arrays.asList(csvdata);
	}
	
	@Test
	public void test_addUser() {
		screenshotManager tsObj = new screenshotManager("Add Users", false);
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

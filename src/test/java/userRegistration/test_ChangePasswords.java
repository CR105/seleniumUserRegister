package userRegistration;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class test_ChangePasswords {
	private final pom_userRegistration CatObj;
	private final String browser;
	private final String URL;
	private final String user;
	private final String passw;
	private final String userSearching, newpass;
	
	public test_ChangePasswords(String browser, String URL, String user, String passw, String userSearching, String newpass) {
		this.CatObj = new pom_userRegistration();
		this.browser = browser;
		this.URL = URL;
		this.user = user;
		this.passw = passw;
		this.userSearching = userSearching;
		this.newpass = newpass;
	}
	
	@SuppressWarnings("resource")
	@Parameters
	public static List<String []> data(){
		int numData= 6;
		int numReg = 3;
		String[][] csvdata = new String[numReg][numData];
		String line = "";
		String splitBy = ",";
		int indx = 0;
		String pathFile = "/userRegistration/src/test/resources/dataChangePasswords.csv";
		try {
			BufferedReader br = new BufferedReader(new FileReader(pathFile));
	        while ((line = br.readLine()) != null)
	        {  
	        	String[] newUser = line.split(splitBy);
	        	if (! newUser[0].contains("BROWSER")) {
	        		System.out.println(indx + " - Browser = " + newUser[0] + 
		            		", URL = " + newUser[1]);
	        		System.out.println(indx + " - User = " + newUser[2] + 
		            		", Pass = " + newUser[3] + 
		            		", userSearching = " + newUser[4] + 
		            		", newpass = " + newUser[5]);
	        		
	        		csvdata[indx][0] = newUser[0];
	        		csvdata[indx][1] = newUser[1];
	        		csvdata[indx][2] = newUser[2];
	        		csvdata[indx][3] = newUser[3];
	        		csvdata[indx][4] = newUser[4];
	        		csvdata[indx][5] = newUser[5];
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
	
	@After
	public void finalize() {
		Assert.assertEquals(true, CatObj.close());
	}
	
	@Test
    public void test_ChangePassword(){
		System.out.println("User: " + userSearching);
    	screenshotManager tsObj = new screenshotManager("SearchUser", true);
    	
    	Assert.assertEquals(true, CatObj.setDriver(30, browser, ""));
    	tsObj.takeScreen(CatObj.driver, "");
    	
    	Assert.assertEquals(true, CatObj.setURL(URL));
    	tsObj.takeScreen(CatObj.driver, "Set URL");
    	
        Assert.assertEquals(true, CatObj.login(user, passw));
        tsObj.takeScreen(CatObj.driver, "Login");
        
        Assert.assertEquals(true, CatObj.admin());
        tsObj.takeScreen(CatObj.driver, "Go to Admin");
        
        Assert.assertEquals(true, CatObj.manageUsers());
        tsObj.takeScreen(CatObj.driver, "Select Manager User");
        
        Assert.assertEquals(true, CatObj.searchUser(userSearching));
        tsObj.takeScreen(CatObj.driver, "Search user");
        
        Assert.assertEquals(true, CatObj.changePass(newpass));
        tsObj.takeScreen(CatObj.driver, "Search user");
        
        Assert.assertEquals(true, CatObj.backListUser());
        tsObj.takeScreen(CatObj.driver, "Back to list users");
    }

}

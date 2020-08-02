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
import org.junit.runners.Parameterized.Parameters;
import org.junit.runners.Parameterized;

/**
 * 
 * @author carlos.rios.rios
 * Unit Test to Catalyst Application.
 */
@RunWith(Parameterized.class)
public class test_SearchUser {
	
	@Parameters
	public static List<Object []> data(){
		JSONObject jsonObject;
		try {
			File rfile = new File("src/test/resources/dataSearchUser.json");
			String afile = rfile.getAbsolutePath();
			System.out.println("File:" + afile);
			FileReader reader = new FileReader(afile);
		    JSONParser jsonParser = new JSONParser();
			jsonObject = (JSONObject) jsonParser.parse(reader);
		} catch (Exception e) {
			System.out.println("Exeception in read Json File: " + e.toString());
			return null;
		}
		return Arrays.asList(new Object[][] {
            { jsonObject.get("browser"), 
            	jsonObject.get("url"), 
            	jsonObject.get("user"), 
            	jsonObject.get("password"), 
            	jsonObject.get("userToSearch")}
    });
	}
	
	private final pom_userRegistration CatObj;
	private final String browser;
	private final String URL;
	private final String user;
	private final String passw;
	private final String userSearching;
	
	public test_SearchUser(String browser, String URL, String user, String passw, String userSearching) {
		this.CatObj = new pom_userRegistration();
		this.browser = browser;
		this.URL = URL;
		this.user = user;
		this.passw = passw;	
		this.userSearching = userSearching;
	}

    @Test
    public void searchUser(){
    	screenshotManager tsObj = new screenshotManager("SearchUser", false);
    	
    	Assert.assertEquals(true, CatObj.setDriver(20, browser, ""));
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
        
//        Assert.assertEquals(true, CatObj.backListUser());
//        tsObj.takeScreen(CatObj.driver, 11, "Back to list users");
//        
//        Assert.assertEquals(true, CatObj.close());
    }

}

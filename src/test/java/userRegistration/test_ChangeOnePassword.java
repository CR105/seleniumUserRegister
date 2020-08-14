package userRegistration;

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
public class test_ChangeOnePassword {
	@Parameters
	public static List<Object []> data(){
		JSONObject jsonObject;
		try {
			FileReader reader = new FileReader("src/test/resources/dataChangeOnePassword.json");
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
            	jsonObject.get("userToSearch"),
            	jsonObject.get("newpass")}
    });
	}
	
	private final pom_userRegistration CatObj;
	private final String browser;
	private final String URL;
	private final String user;
	private final String passw;
	private final String userSearching;
	private final String newpass;
	
	public test_ChangeOnePassword(String browser, String URL, String user, String passw, String userSearching, String newpass) {
		this.CatObj = new pom_userRegistration();
		this.browser = browser;
		this.URL = URL;
		this.user = user;
		this.passw = passw;	
		this.userSearching = userSearching;
		this.newpass = newpass;
	}

    @Test
    public void test_ChangePassword(){
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
        tsObj.takeScreen(CatObj.driver, 11, "Back to list users");
        
        Assert.assertEquals(true, CatObj.close());
    }
}

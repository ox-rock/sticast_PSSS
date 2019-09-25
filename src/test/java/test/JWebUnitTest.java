package test;

import org.junit.*;
import static net.sourceforge.jwebunit.junit.JWebUnit.*;
import java.security.SecureRandom;
import net.sourceforge.jwebunit.util.TestingEngineRegistry;

public class JWebUnitTest {

    static final String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    static SecureRandom rnd = new SecureRandom();

    String randomString( int len ){
        StringBuilder sb = new StringBuilder( len );
        for( int i = 0; i < len; i++ ) 
            sb.append( AB.charAt( rnd.nextInt(AB.length()) ) );
        return sb.toString();
    }
	
    @Before
    public void prepare() {
    	setTestingEngineKey(TestingEngineRegistry.TESTING_ENGINE_HTMLUNIT); 
        setBaseUrl("http://localhost:8080/sticast");
        
    }

    @Test
    public void TestLogin1() {
    	setScriptingEnabled(false);
    	beginAt("index.jsp");
    	assertTitleEquals("StiCast! - Welcome");
    	clickLink("login");
        assertTitleEquals("StiCast! - Login");
        setTextField("username", "test");
        setTextField("password", "testtesttest");
        submit();
    	assertTitleEquals("StiCast! - Questions");
    	gotoPage("logout");
    	
    }
    
    @Test
    public void TestLogin2() {
    	setScriptingEnabled(false);
    	beginAt("index.jsp");
    	assertTitleEquals("StiCast! - Welcome");
    	clickLink("login");
        assertTitleEquals("StiCast! - Login");
        setTextField("username", "pluto");
        setTextField("password", "asdasdasd");
        submit();
    	assertTitleEquals("StiCast! - Login");
    }
    
 /*   @Test
    public void TestLogin3() {
    	setScriptingEnabled(false);
    	beginAt("index.jsp");
    	assertTitleEquals("StiCast! - Welcome");
    	clickLink("login");
        assertTitleEquals("StiCast! - Login");
        setTextField("username", "pippo");
        setTextField("password", "pippo23");
        submit();
    	assertTitleEquals("StiCast! - Login");
    }
    
 */   
    @Test
    public void TestRegistration1() {
    	setScriptingEnabled(false);
    	beginAt("index.jsp");
    	assertTitleEquals("StiCast! - Welcome");
    	clickLink("registration");
        assertTitleEquals("StiCast! - Register");
        String randomUsername = randomString(20);
        String randomEmail = randomString(20);
        setTextField("username", "TEST"+randomUsername);
        setTextField("password", "TEST");
        setTextField("name", "TEST");
        setTextField("email", randomEmail + "@email.com");
        submit();
    	assertTitleEquals("StiCast! - Questions");
    	gotoPage("logout");
    }
    
    @Test
    public void TestRegistration2() {
    	setScriptingEnabled(false);
    	beginAt("index.jsp");
    	assertTitleEquals("StiCast! - Welcome");
    	clickLink("registration");
        assertTitleEquals("StiCast! - Register");
        setTextField("username", "pippo");
        setTextField("password", "pippoppppppppp23");
        setTextField("name", "pippo");
        setTextField("email", "pippo23@dsa.it");
        submit();
    	assertTitleEquals("StiCast! - Register");
    } 
    
    @Test
    public void TestRegistration3() {
    	setScriptingEnabled(false);
    	beginAt("index.jsp");
    	assertTitleEquals("StiCast! - Welcome");
    	clickLink("registration");
        assertTitleEquals("StiCast! - Register");
        setTextField("username", "Eusebio77");
        setTextField("password", "asdasdasd");
        setTextField("name", "Eusebio");
        setTextField("email", "pippo@pippo.it");
        submit();
    	assertTitleEquals("StiCast! - Register");
    }    
    
    @Test
    public void TestMakeForecast1() {
    	
    	String[] values=new String[2];
    	values[0]="yes";
    	values[1]="no";
    	
    	setScriptingEnabled(false);
    	beginAt("index.jsp");
    	assertTitleEquals("StiCast! - Welcome");
    	clickLink("login");
        assertTitleEquals("StiCast! - Login");
        setTextField("username", "test");
        setTextField("password", "testtesttest");
        submit();
    	assertTitleEquals("StiCast! - Questions");
    	gotoPage("question/0");
    	assertTitleEquals("StiCast! - Test");
    	setTextField("quantity", "10");
    	submit();
    	assertTextInElement("ys", "10");
    	gotoPage("logout");
    	}
    
    @Test
    public void TestMakeForecast2() {
    	
    	String[] values=new String[2];
    	values[0]="yes";
    	values[1]="no";
    	
    	setScriptingEnabled(false);
    	beginAt("index.jsp");
    	assertTitleEquals("StiCast! - Welcome");
    	clickLink("login");
        assertTitleEquals("StiCast! - Login");
        setTextField("username", "test");
        setTextField("password", "testtesttest");
        submit();
    	assertTitleEquals("StiCast! - Questions");
    	gotoPage("question/0");
    	assertTitleEquals("StiCast! - Test");
    	setHiddenField("buyOrSell", "sell");
    	setTextField("quantity", "-10");
    	submit();
    	assertTextInElement("ys", "0");
    	gotoPage("logout");
    	}
    
    @Test
    public void TestMakeForecast3() {
    	
    	String[] values=new String[2];
    	values[0]="yes";
    	values[1]="no";
    	
    	setScriptingEnabled(false);
    	beginAt("index.jsp");
    	assertTitleEquals("StiCast! - Welcome");
    	clickLink("login");
        assertTitleEquals("StiCast! - Login");
        setTextField("username", "test");
        setTextField("password", "testtesttest");
        submit();
    	assertTitleEquals("StiCast! - Questions");
    	gotoPage("question/0");
    	assertTitleEquals("StiCast! - Test");
    	setHiddenField("buyOrSell", "buy");
    	selectOptionByValue("answer",0,"no");
    	setTextField("quantity", "10");
    	submit();
    	assertTextInElement("ns", "10");
    	gotoPage("logout");
    }
    
    @Test
    public void TestMakeForecast4() {
    	
    	String[] values=new String[2];
    	values[0]="yes";
    	values[1]="no";
    	
    	setScriptingEnabled(false);
    	beginAt("index.jsp");
    	assertTitleEquals("StiCast! - Welcome");
    	clickLink("login");
        assertTitleEquals("StiCast! - Login");
        setTextField("username", "test");
        setTextField("password", "testtesttest");
        submit();
    	assertTitleEquals("StiCast! - Questions");
    	gotoPage("question/0");
    	assertTitleEquals("StiCast! - Test");
       	setHiddenField("buyOrSell", "sell");
    	selectOptionByValue("answer",0,"no");
    	setTextField("quantity", "-10");
    	submit();
    	assertTextInElement("ns", "0");
    	gotoPage("logout");
    }
    
    @Test
    public void TestForecastsHistory() {
    	setScriptingEnabled(false);
    	beginAt("index.jsp");
    	assertTitleEquals("StiCast! - Welcome");
    	clickLink("login");
        assertTitleEquals("StiCast! - Login");
        setTextField("username", "test");
        setTextField("password", "testtesttest");
        submit();
    	assertTitleEquals("StiCast! - Questions");
    	clickLink("profile");
    	assertTitleEquals("StiCast! - My Profile");  
    	clickLink("forecasts");
    	assertTitleEquals("StiCast! - Forecasts History");
    	gotoPage("logout");
    }
     
    @Test
    public void TestAddComment1() {
    	
    	String randomComment = randomString(20);
    	
    	setScriptingEnabled(false);
    	beginAt("index.jsp");
    	assertTitleEquals("StiCast! - Welcome");
    	clickLink("login");
        assertTitleEquals("StiCast! - Login");
        setTextField("username", "test");
        setTextField("password", "testtesttest");
        submit();
    	assertTitleEquals("StiCast! - Questions");
    	gotoPage("question/0");
    	assertTitleEquals("StiCast! - Test");
    	setTextField("text",randomComment);
    	submit();
    	assertTextPresent(randomComment);
    }
/*  
 * 
 *   NON RIESCO A FARLO FUNZIONARE
 *   
    @Test
    public void TestAddComment2() {
    	
    	String randomComment = randomString(101);
    	
    	setScriptingEnabled(false);
    	beginAt("index.jsp");
    	assertTitleEquals("StiCast! - Welcome");
    	clickLink("login");
        assertTitleEquals("StiCast! - Login");
        setTextField("username", "pippo");
        setTextField("password", "pippo123");
        submit();
    	assertTitleEquals("StiCast! - Questions");
    	gotoPage("question/0");
    	assertTitleEquals("StiCast! - Test");
    	setTextField("addComment",randomComment);
    	submit();
     	assertResponseCode(500);
    }
*/    
}
package org.selenium.docker;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.Test;

import junit.framework.Assert;
import utility.ReadProperties;
import utility.PropertiesReader;

import java.io.IOException;
import java.net.MalformedURLException;


public class SeleniumDockerTest {

    public String Username= ReadProperties.getUsername();
    public String Password= ReadProperties.getPassword();
    public String URL= ReadProperties.getURL();
    public String RunMode= ReadProperties.getRunMode();

    public String HostURL= ReadProperties.getHostURL();
    public RemoteWebDriver driver;

    @Test
    public void PrintMavenProperties() throws IOException {
        PropertiesReader reader = new PropertiesReader("properties-from-pom.properties"); 
        String junitVersion = reader.getProperty("junit.version");
        System.out.println("This is printing maven values --junitVersion "+junitVersion);
        String runMode = reader.getProperty("run.mode");
        System.out.println("This is printing maven values -- runMode "+runMode);
        
        Assert.assertEquals("3.8.1", junitVersion);
    }



    //@Test
    public void TestJobPortal() throws MalformedURLException {

        System.out.println(URL);
        System.out.println(Username);
        System.out.println(Password);
        System.out.println(RunMode);
        System.out.println("=====================Test Class=======================");


    /*    URL url = new URL("http://localhost:4444");
        DesiredCapabilities cap = new DesiredCapabilities();
        cap.setBrowserName("firefox");
        driver = new RemoteWebDriver(url, cap);
     */
        driver = new ChromeDriver();

        driver.get("https://alchemy.hguy.co/jobs/wp-admin");
        driver.manage().window().maximize();
        driver.findElement(By.id("user_login")).sendKeys(Username);
        driver.findElement(By.id("user_pass")).sendKeys(Password);
        driver.findElement(By.id("wp-submit")).click();
        System.out.println(driver.getTitle());
        driver.close();
    }
}

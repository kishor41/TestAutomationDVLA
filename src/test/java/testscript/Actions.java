package testscript;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Actions {
	public static WebDriver driver=null;
	/**
	 * 
	 */
	public static void openBrowser(){
		driver=new FirefoxDriver();
	}	
	/**
	 * 
	 * @param url
	 */
	public static void openApplication(String url){
		driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
		driver.get(url);
	}
	/**
	 * 
	 * @param vehRegNum
	 */
	public static void enterRegNumber(String vehRegNum){
		driver.findElement(By.id("Vrm")).sendKeys(vehRegNum);
	}
	/**
	 * 
	 * @throws Exception
	 */
	public static void clickContinueButton() throws Exception{
		driver.findElement(By.xpath("(//button[contains(text(),'Continue')])")).click();
		Thread.sleep(2000);
	}
	/**
	 * 
	 * @return
	 */
	public static PageResult retrievePageResult(){
		PageResult pgRes = new PageResult();
		WebElement pageVehicleReg = driver.findElement(By.xpath("//li[@class=\"list-summary-item\"]/span[@class='reg-mark']"));
		pgRes.setRegNumber(pageVehicleReg.getText());
		WebElement pageVehcileMake = driver.findElement(By.xpath("//li[@class=\"list-summary-item\"]/span[2]/strong"));
		pgRes.setMake(pageVehcileMake.getText());
		return pgRes;
	}
	/**
	 * 
	 */
	public static void tearDown(){
		driver.quit();
	}
}

package automationTests;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.AfterTest;
import utilities.configPropertyReader;

public class TestInitiator {

  public static WebDriver driver;
  public ExtentHtmlReporter htmlreporter;
  public static ExtentReports extent;
  public static ExtentTest logger;
  String baseResourcesPath = System.getProperty("user.dir") + File.separator + "src" + File.separator + "test" + File.separator + "resources" + File.separator;
  String extentReportPath = baseResourcesPath + "reports" + File.separator;
  String screenshotsPath = baseResourcesPath + "screenshots" + File.separator;
  private static String configPropertiesFilename = "config.properties";

  
  @BeforeTest
  public void beforeTest() {
	  htmlreporter = new ExtentHtmlReporter(extentReportPath + "TestAutomationReport.html");
	  htmlreporter.config().setEncoding("utf-8");
	  htmlreporter.config().setDocumentTitle("Test Automation Report");
	  htmlreporter.config().setReportName("Automation Test Results");
	  htmlreporter.config().setTheme(Theme.DARK);
	  extent = new ExtentReports();
	  extent.attachReporter(htmlreporter);
	  extent.setSystemInfo("Automation Engineer", "Vipul Gaur");
  }
  
  @BeforeMethod
  public void beforeMethod(Method testMethod) throws IOException {
	  logger = extent.createTest(testMethod.getName());
	  setupDriver(configPropertyReader.readAndGetProperty("browserName", configPropertiesFilename).toString());
	  driver.manage().window().maximize();
	  driver.manage().timeouts().implicitlyWait(Integer.parseInt(configPropertyReader.readAndGetProperty("timeout", configPropertiesFilename)), TimeUnit.SECONDS);
	  driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
	  driver.manage().deleteAllCookies();
	  driver.get(configPropertyReader.readAndGetProperty("url", configPropertiesFilename));
  }
  
  @AfterTest
  public void afterTest() {
	  extent.flush();
  }

  @AfterMethod
  public void afterMethod(ITestResult result) throws IOException {
	  if(result.getStatus() == ITestResult.SUCCESS)
	  {
		  String methodName = result.getMethod().getMethodName();
		  String logText = "TestCase "+ methodName + " Passed";
		  Markup m = MarkupHelper.createLabel(logText, ExtentColor.GREEN);
		  logger.log(Status.PASS, m);
	  }
	  else if (result.getStatus() == ITestResult.FAILURE)
	  {
		  String methodName = result.getMethod().getMethodName();
		  String logText = "TestCase "+ methodName + " Failed";
		  Markup m = MarkupHelper.createLabel(logText, ExtentColor.RED);
		  logger.log(Status.FAIL, m);
		  String screenShotPath = getScreenshot(result);
		  logger.log(Status.FAIL, "Failure Screenshot below: "+logger.addScreenCaptureFromPath(screenShotPath));
	  }
	  else if (result.getStatus() == ITestResult.SKIP)
	  {
		  String methodName = result.getMethod().getMethodName();
		  String logText = "TestCase "+ methodName + " Skipped";
		  Markup m = MarkupHelper.createLabel(logText, ExtentColor.YELLOW);
		  logger.log(Status.SKIP, m);
	  }
	  logger.info("Closing all the Browsers");
	  driver.quit();
  }

public void setupDriver(String browserName)
  {
	  if(browserName.equalsIgnoreCase("chrome"))
	  {
		  System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + File.separator + "src" + File.separator + "test" + File.separator + "resources"+ File.separator + "drivers" + File.separator + "chromedriver93.exe");
		  driver = new ChromeDriver();
	  }else if (browserName.equalsIgnoreCase("firefox"))
	  {
		  System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + File.separator + "src" + File.separator + "test" + File.separator + "resources"+ File.separator + "drivers" + File.separator + "geckodriver.exe");
		  FirefoxProfile profile = new FirefoxProfile();
		  profile.setPreference("browser.cache.disk.enable", false);
		  FirefoxOptions options = new FirefoxOptions();
		  options.setProfile(profile);
		  driver = new FirefoxDriver(options);
	  }
	  else if (browserName.equalsIgnoreCase("ie"))
	  {
		  System.setProperty("webdriver.ie.driver", System.getProperty("user.dir") + File.separator + "src" + File.separator + "test" + File.separator + "resources"+ File.separator + "drivers" + File.separator + "IEDriverServer32.exe");
		  InternetExplorerOptions options = new InternetExplorerOptions();
		  options.enablePersistentHovering();
		  options.ignoreZoomSettings();
		  options.introduceFlakinessByIgnoringSecurityDomains();
		  options.setUnhandledPromptBehaviour(UnexpectedAlertBehaviour.ACCEPT);
		  options.enableNativeEvents();
		  driver = new InternetExplorerDriver(options);
	  }
	  else
	  {
		  System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + File.separator + "src" + File.separator + "test" + File.separator + "resources"+ File.separator + "drivers" + File.separator + "chromedriver.exe");
		  driver = new ChromeDriver();
	  }
  }
  
  public String getScreenshot(ITestResult result)
  {
	    String date = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		String filename = screenshotsPath + result.getMethod().getMethodName() + "-" + date;
		String destinationPath = filename+".png";
		File f = ((TakesScreenshot)TestInitiator.driver).getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(f, new File(destinationPath));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return destinationPath;
  }
  
  @AfterSuite
  public void moveResultsToCurrentTestResults() throws IOException
  {
	  String date = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
	  String destination_dirpath = baseResourcesPath + File.separator + "CurrentTestResults" + File.separator;
	  File dir  = new File(destination_dirpath + File.separator + "TestResult-" +date);
	  File source_html_dir = new File(extentReportPath);
	  File[] myreports = source_html_dir.listFiles();
	  for(int i = 0; i < myreports.length; i++)
          {
              File file1 = myreports[i];
              FileUtils.copyFile(new File(source_html_dir + File.separator + file1.getName()), new File(dir + File.separator + file1.getName()));
          }
	  File source_screenshots_dir = new File(screenshotsPath);
	  File[] myscreenshots = source_screenshots_dir.listFiles();
	  for(int i = 0; i < myscreenshots.length; i++)
          {
              File file1 = myscreenshots[i];
              FileUtils.copyFile(new File(source_screenshots_dir + File.separator + file1.getName()), new File(dir + File.separator + file1.getName()));
          }
	  }
  
  @BeforeSuite
  public void ArchiveResultsAtStart() throws IOException
  {
	  String destination_directory = baseResourcesPath + File.separator + "ArchivedTestResults";
	  String source_directory = baseResourcesPath + File.separator + "CurrentTestResults";
	  File dir  = new File(source_directory);
	  File[] source_results = dir.listFiles();
	  for(int i = 0; i < source_results.length; i++)
      {
          File file1 = source_results[i];
          FileUtils.copyDirectoryToDirectory(new File(source_directory + File.separator + file1.getName()), new File(destination_directory + File.separator + file1.getName()));
          FileUtils.deleteDirectory(new File(source_directory + File.separator + file1.getName()));
      }
	  File source_screenshots_dir = new File(screenshotsPath);
	  File[] myscreenshots = source_screenshots_dir.listFiles();
	  for(int i = 0; i < myscreenshots.length; i++)
          {		  	  	
              File file1 = myscreenshots[i];
              FileUtils.forceDelete(file1);
          }	  
   }
}

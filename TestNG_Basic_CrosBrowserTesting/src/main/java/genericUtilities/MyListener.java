package genericUtilities;

import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class MyListener implements ITestListener {

    @Override
    public void onTestSuccess(ITestResult result) {
        captureScreenshot(result, "PASSED");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        captureScreenshot(result, "FAILED");
    }

    private void captureScreenshot(ITestResult result, String status) {
        // Access the driver from the test instance
        Object currentClass = result.getInstance();
        WebDriver driver = ((BaseClass) currentClass).driver;

        if (driver != null) {
            String methodName = result.getName();
            File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            
            // Saves in a folder called 'Screenshots' in your project root
            String filePath = "./Screenshots/" + status + "_" + methodName + ".png";
            try {
                FileUtils.copyFile(srcFile, new File(filePath));
                System.out.println("Screenshot captured: " + filePath);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
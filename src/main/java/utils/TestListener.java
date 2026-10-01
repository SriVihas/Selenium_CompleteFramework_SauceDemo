package utils;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
public class TestListener implements ITestListener{
    private static final ExtentReports report = ExtentReportManager.getInstance();
    private static final ThreadLocal<ExtentTest> testThread = new ThreadLocal<>();
    @Override
    public void onTestStart(ITestResult result) {
        String testName = result.getMethod().getMethodName();
        String description = result.getMethod().getDescription();

        ExtentTest currentTest = report.createTest(
                description != null ? description : testName
        );
        testThread.set(currentTest);
    }
    @Override
    public void onTestSuccess(ITestResult result) {
        testThread.get().log(Status.PASS, "Test Executed Successfully");
    }
    @Override
    public void onTestFailure(ITestResult result) {
        testThread.get().log(Status.FAIL, "Test Execution Failed");
        testThread.get().fail(result.getThrowable());
    }
    @Override
    public void onTestSkipped(ITestResult result) {
        testThread.get().log(Status.SKIP, "Test Execution Skipped");
        if (result.getThrowable() != null) {
            testThread.get().skip(result.getThrowable());
        }
    }
    @Override
    public void onFinish(ITestContext context) {
        if (report != null) {
            report.flush();
        }
    }
}

package StepDefinitions;

import Utilities.BaseDriver;
import Utilities.ExcelUtility;
import io.cucumber.java.After;
import io.cucumber.java.Scenario;
import io.cucumber.java.Status;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.apache.commons.io.FileUtils;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class Hooks {


    @After  // HER SENARYODAN SONRA ÇALIŞACAK BÖLÜM
    public void after(Scenario scenario) { // SENARYO FAİL İSE PARANTEZİN İÇİNE TANIMLARIZ VE İF KOŞULU İLE EKRAN GÖRÜNTÜSÜ İSTEYEBİLİRİZ
        System.out.println("Test scenario ended");
        System.out.println("Test scenario result =" + scenario.getStatus());
        System.out.println("Scenario is failed ?=" + scenario.isFailed());

        LocalDateTime date = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yy HH:ss");
        DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("dd.MM.yy");

        ExcelUtility.writeExcel("src/test/java/ExcelReport/ScenarioStatus.xlsx", scenario, BaseDriver.threadBrowserName.get(), date.format(formatter));


        //EXTEND REPORTTA ÇALIŞAN KISMI KAPATTIK EXCEL RAPORUNU ALMAK İÇİN
        if (scenario.isFailed() || scenario.getStatus() == Status.PASSED) { // Senaryo fail yada passed olsa bile görüntü al
            TakesScreenshot ts = (TakesScreenshot) BaseDriver.getDriver();
            File hafizadakiHali = ts.getScreenshotAs(OutputType.FILE);
            try {
                FileUtils.copyFile(hafizadakiHali,
                        new File("target/ScreenShotsGoogleMaps/" + scenario.getId() + "_" + date.format(formatter1) + ".png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        BaseDriver.quitDriver();

    }
    public String GoogleMapsScreenShot() {
        return ((TakesScreenshot) BaseDriver.getDriver()).getScreenshotAs(OutputType.BASE64);
        // Base64 veri türü ekran görüntüsünün raporlamada kullanılması için elverişli

    }
}

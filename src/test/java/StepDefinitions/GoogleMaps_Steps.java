package StepDefinitions;

import Pages.DialogContent;
import Utilities.BaseDriver;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.poi.ss.formula.functions.T;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.FileOutputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;


public class GoogleMaps_Steps {

    DialogContent dc = new DialogContent();

    WebDriverWait wait = new WebDriverWait(BaseDriver.getDriver(), Duration.ofSeconds(30));


    @Given("User goes to the website")
    public void userGoesToTheWebsite() {
        BaseDriver.getDriver().get("https://www.google.com/maps/");
        BaseDriver.getDriver().manage().window().maximize();


    }

    @When("User clicks the directions button")
    public void userClicksTheDirectionsButton() {
        dc.clickFunc(dc.directions);
    }

    @And("User determines the starting and destination")
    public void userDeterminesTheStartingAndDestination() {
        dc.sendKeysFunc(dc.startingPoint, "İstanbul");
        dc.sendKeysFunc(dc.destinationPoint, "Silivri");
    }

    @And("User click on search button")
    public void userClickOnSearchButton() {
        dc.clickFunc(dc.searchBtn);

    }

    @And("User creates route by car")
    public void userCreatesRouteByCar() {
        dc.clickFunc(dc.routeCar);

    }

    @And("User displays estimated time and distance")
    public void userDisplaysEstimatedTimeAndDistance() throws InterruptedException {

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[class='MespJc']")));

        List<WebElement> routeCarInfoList = BaseDriver.getDriver().findElements(By.cssSelector("[class='MespJc']"));

        // WebElement listesini konsolda yazdırın
        //   for (WebElement routeCarInfo : routeCarInfoList) {
        //       System.out.println(routeCarInfo.getText());
        //   }


        // Excel dosyasını oluşturun
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("CarInfo");

        // Başlık hücresini ekleyin
        Row headerRow = sheet.createRow(0);
        Cell headerCell = headerRow.createCell(0);
        headerCell.setCellValue("ARABA KULLANIMI İÇİN ROTA BİLGİLERİ "); // Başlık metni


        // WebElement liste döngüsü
        int rowNum = 1;
        for (WebElement routeCarInfo : routeCarInfoList) {
            Row row = sheet.createRow(rowNum++);
            Cell cell = row.createCell(0);

            // WebElement'ten metni al Excel hücresine yaz
            String carInfoText = routeCarInfo.getText();
            cell.setCellValue(carInfoText);
        }

        // Excel dosyasını kayded
        try (FileOutputStream outputStream = new FileOutputStream("CarRoute_info.xlsx")) {
            workbook.write(outputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @And("User creates route with public transport")
    public void userCreatesRouteWithPublicTransport() {
        dc.clickFunc(dc.routePublicTransport);


    }

    @And("User displays public transport route info")
    public void userDisplaysPublicTransportRouteInfo() {

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[class='MespJc']")));
        List<WebElement> routePublicTransportInfoList = BaseDriver.getDriver().findElements(By.cssSelector("[class='MespJc']"));



        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("PublicTransportInfo");



        Row headerRow = sheet.createRow(0);
        Cell headerCell = headerRow.createCell(0);
        headerCell.setCellValue("TOPLU TAŞIMA KULLANIMI İÇİN ROTA BİLGİLERİ ");



        int rowNum = 1;
        for (WebElement routePublicTransport : routePublicTransportInfoList) {
            Row row = sheet.createRow(rowNum++);
            Cell cell = row.createCell(0);


            String PubTransInfoText = routePublicTransport.getText();
            cell.setCellValue(PubTransInfoText);
        }


        try (FileOutputStream outputStream = new FileOutputStream("PublicTransport_info.xlsx")) {
            workbook.write(outputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    @And("User determines walking route to destination")
    public void userDeterminesWalkingRouteToDestination() {
        dc.clickFunc(dc.routePedestrian);


    }

    @And("User lists the results")
    public void userListsTheResults() {


        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[class='MespJc']")));
        List<WebElement> routePublicTransportInfoList = BaseDriver.getDriver().findElements(By.cssSelector("[class='MespJc']"));



        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("PedestrianRoute_Info");



        Row headerRow = sheet.createRow(0);
        Cell headerCell = headerRow.createCell(0);
        headerCell.setCellValue("YAYA OLARAK ROTA BİLGİLERİ "); // Başlık metnini burada belirleyin



        int rowNum = 1;
        for (WebElement routePedestrianInfo : routePublicTransportInfoList) {
            Row row = sheet.createRow(rowNum++);
            Cell cell = row.createCell(0);


            String PedestInfoText = routePedestrianInfo.getText();
            cell.setCellValue(PedestInfoText);
        }


        try (FileOutputStream outputStream = new FileOutputStream("PedestrianRoute_info.xlsx")) {
            workbook.write(outputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    @And("User searches for a new route")
    public void userSearchesForANewRoute() throws InterruptedException {


        dc.clickFunc(dc.directionsClose);
        dc.sendKeysFunc(dc.searchInput, "Fatih");
        dc.clickFunc(dc.searchBtn2);
        dc.clickFunc(dc.nearBy);
        dc.sendKeysFunc(dc.searchInput,"Restoranlar");
        dc.clickFunc(dc.searchBtn2);

    }

    @And("User lists restorants close to the route")
    public void userListsRestorantsCloseToTheRoute() throws InterruptedException {

        JavascriptExecutor js = (JavascriptExecutor) BaseDriver.getDriver();


        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Restoranlar_Info");


        Row headerRow = sheet.createRow(0);
        Cell headerCell = headerRow.createCell(0);
        headerCell.setCellValue("BÖLGEDE YER ALAN RESTORANLARIN LİSTESİ");


        int rowNum = 1;
        int previousRestCount = 0;

        while (true) {

            Thread.sleep(5000);

            WebElement firstElement = BaseDriver.getDriver().findElement(By.xpath("(//*[@aria-label='Kömür Türk Mutfağı'])[1]"));//ilk elementle sayfayı aldım
            wait.until(ExpectedConditions.visibilityOf(firstElement));


            List<WebElement> restList = BaseDriver.getDriver().findElements(By.xpath("//*[@aria-label='Restoranlar için sonuçlar']/child::div"));
            if (restList.size() == previousRestCount) {
                break;
            }

            int batchSize = 20;
            for (int i = 0; i < restList.size(); i += batchSize) {
                List<WebElement> batch = restList.subList(i, Math.min(i + batchSize, restList.size()));


                for (WebElement ms : batch) {
                    Row row = sheet.createRow(rowNum++);
                    Cell cell = row.createCell(0);


                    String restInfoText = ms.getText();
                    cell.setCellValue(restInfoText);
                }
            }

            js.executeScript("window.scrollTo(0, document.body.scrollHeight)");


            Thread.sleep(2000);


            List<WebElement> newMuseumsList = BaseDriver.getDriver().findElements(By.xpath("//*[@aria-label='Restoranlar için sonuçlar']/child::div"));
            if (newMuseumsList.size() == previousRestCount) {
                break;
            }
            previousRestCount = newMuseumsList.size();
        }


        try (FileOutputStream outputStream = new FileOutputStream("Restoranlar_info.xlsx")) {
            workbook.write(outputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @And("User closes the map window and exits the page.")
    public void userClosesTheMapWindowAndExitsThePage() {

        for (int i = 0; i < 7; i++) {
            BaseDriver.getDriver().navigate().back();
        }

    }

    @Then("User verifies that they have logged out successfully")
    public void userVerifiesThatTheyHaveLoggedOutSuccessfully() {
        String lastPageURL = BaseDriver.getDriver().getCurrentUrl();

        // URL'yi yazdırın veya işlem yapın
        System.out.println("Son sayfanın URL'si: " + lastPageURL);




    }



}

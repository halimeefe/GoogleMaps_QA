package Pages;

import Utilities.BaseDriver;
import org.checkerframework.checker.units.qual.C;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class DialogContent  extends Parent {


    public DialogContent() {
        PageFactory.initElements(BaseDriver.getDriver(), this);


    }

    @FindBy (className = "lSDxNd")
    public WebElement directions;

    @FindBy (xpath = "(//*[@class='tactile-searchbox-input'])[1]")
    public WebElement startingPoint;

    @FindBy (xpath = "(//*[@class='tactile-searchbox-input'])[2]")
    public WebElement destinationPoint;


    @FindBy (xpath = "(//*[@data-tooltip='Arayın'])[2]")
    public WebElement searchBtn;

    @FindBy (css = "[data-tooltip='Araba']")
    public WebElement routeCar;

    @FindBy (css = "[data-tooltip='Toplu Taşıma']")
    public WebElement routePublicTransport;

    @FindBy (css = "[data-tooltip='Yaya']")
    public WebElement routePedestrian;

    @FindBy (className = "InF6Wc")
    public WebElement directionsClose;

    @FindBy (id = "searchboxinput")
    public WebElement searchInput;

    @FindBy (xpath = "(//*[@aria-label='Arayın'])[1]")
    public WebElement searchBtn2;


    @FindBy (xpath = "(//*[@class='DVeyrd '])[3]")
    public WebElement nearBy;

















}


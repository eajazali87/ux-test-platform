package elementsSDK.styles.stylesPageObjects;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;

/**
 * Created by udhadpa on 9/22/16.
 */
public class PresentationStrategiesPageObjects {

    public WebDriver driver = null;
    public AppiumDriver appium = null;

    public PresentationStrategiesPageObjects(){
    }

    // Default Strategy
    public By singleTemplate = By.id("template-single-default");
    public By defaultStrategy = By.id("default-strategy");

    // Centered Strategy
    public By centeredStrategy = By.id("centered-strategy");
    public By centeredElement1 = By.id("centered-element1");
    public By centeredElement2 = By.id("centered-element2");

    //Left Right Strategy
    public By leftrightStrategy = By.id("leftright-strategy");
    public By leftElement = By.id("left-element");
    public By rightElement = By.id("right-element");

    //Default  - Spacing
    public By defaultCenteredGap1 = By.id("centered-default1");
    public By defaultCenteredGap2 = By.id("centered-default2");
    public By defaultCenteredGap3 = By.id("centered-default3");

    //Small Gap - Spacing
    public By smallCenteredGap1 = By.id("centered-smallgap1");
    public By smallCenteredGap2 = By.id("centered-smallgap2");
    public By smallCenteredGap3 = By.id("centered-smallgap3");

    //Large Gap - Spacing
    public By largeCenteredGap1 = By.id("centered-largegap1");
    public By largeCenteredGap2 = By.id("centered-largegap2");
    public By largeCenteredGap3 = By.id("centered-largegap3");

    // Basic Grid - Small
    public By gridSmallCol2 = By.id("basic-grid-small-2col");
    public By gridSmallCol3 = By.id("basic-grid-small-3col");
    public By gridSmallCol4 = By.id("basic-grid-small-4col");

    // Basic Grid - Large
    public By gridLargeCol2 = By.id("basic-grid-large-2col");
    public By gridLargeCol3 = By.id("basic-grid-large-3col");
    public By gridLargeCol4 = By.id("basic-grid-large-4col");
}
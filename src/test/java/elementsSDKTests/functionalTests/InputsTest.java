package elementsSDKTests.functionalTests;

import com.google.gson.JsonObject;
import elementsSDK.functional.functionalPageObjects.FunctionalInputsPageObjects;
import elementsSDK.styles.stylesPageObjects.InputsPageObjects;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.*;
import utilities.BaseClass;
import utilities.RetryAnalyzer;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.*;

/**
 * Created by umahaea on 3/15/17.
 */
public class InputsTest extends BaseClass {

    private final String inputsUrl = "http://localhost:8000/src/main/java/elementsSDK/functional/fixtures/inputs.html";
    private final String absInputsJSFilePath = new File("elementsSDK/functional/jsfiles/inputs/inputs.js").getAbsolutePath();
    private final String inputsJSFilePath = constructPath(absInputsJSFilePath);
    private final String absTempJSFilePath = new File("elementsSDK/functional/jsfiles/inputs/temp.js").getAbsolutePath();
    private final String tempJSFilePath = constructPath(absTempJSFilePath);

    private static String browser = "", lBrowser = "", setPlatform = "", setAppium = "", setMobile;
    private String browserLogs = "", macChromeFontFamily = "\"Open Sans\", Calibri, Tahoma, sans-serif", ffFontFamily = "\"Open Sans\",Calibri,Tahoma,sans-serif", safariFontFamily = "'Open Sans', Calibri, Tahoma, sans-serif", ieFontFamily = "open sans,calibri,tahoma,sans-serif", edgeFontFamily = "Open Sans,Calibri,Tahoma,sans-serif", cssPropertyType = "", outlineStyle = "", backgroundColor = "", unroundedTransValue = "", display = "", height = "", transitionDelay = "", transitionDuration = "", transitionProp = "", trainsitionTimingFunc = "", testConfig = "", fileContentsInAString = "", postFixConfig = "", preFixConfig = "", bgColor = "", marginTop = "", paddingRight = "", paddingLeft = "", fontSize = "", lineHeight = "", borderRadius = "", borderWidth = "", borderStyle = "", borderColor = "", color = "", boxShadow = "", basicInputBorder = "", basicInputValueColor = "", labelFontSize = "", labelColor = "", paddingTop = "", beforeFinalFormat = "", finalFormat = "", finalConfig = "", labelLineHeight = "", actIconClass = "", showBtnColor = "", showBtnFloat = "", textDecoration = "", paddingBottom = "", borderBottom = "", borderBottomColor = "", borderBottomStyle = "", width = "", marginRight = "", marginBottom = "", radioBtnSelectedColor = "", radioBtnPadding = "", fontFamily = "", padding = "", testName = "", position = "", minHeight = "", leftProperty = "", topProperty = "";
    int indexOfFirstOpenBrace = 0, indexOfLastCloseBrace = 0, roundedTransValue = 0, len = 0, lastIndexOf = 0, indexOfFirstCloseBrace = 0;
    private boolean isCSSProperty = false, isOutlineStyle = false, isBackgroundColor = false, isDisplay = false, isHeight = false, isTransitionDelay = false, isTransitionDuration = false, isTransitionProp = false, isTransitionTimingFunc = false, result = false, isBorderRadius = false, isMarginTop = false, isPaddingRight = false, isPaddingLeft = false, isFontSize = false, isLineHeight = false, isBorderWidth = false, isBorderStyle = false, isBorderColor = false, isColor = false, isLabelFor = false, isBoxShadow = false, isBasicInputBorder = false, isBasicInputValueColor = false, isLabelFontSize = false, isLabelColor = false, isPaddingTop = false, isAriaDescByContains = false, islabelLineHeight = false, isIconClass = false, isBgColor = false, isShowBtnColor = false, isShowBtnFloat = false, isTextDecoration = false, isPaddingBottom = false, isBorderBottom = false, isBorderBottomColor = false, isBorderBottomStyle = false, isMarginRight = false, isRadioBtnPadding = false, isMarginBottom = false, isWidth = false, isRadioBtnSelectedColor = false, isFontFamily = false, isPadding = false, isMinHeight = false, isPosition = false, isTop = false, isLeft = false;
    private final String incorrectElementIdErrorMsg = "Target container is not a DOM element";
    private final String incorrectComponentNameErrorMsg = "type is invalid";

    JavascriptExecutor js = null;
    WebElement webElement = null;

    JsonObject jsonDetailObject = null, jsonDetailPropertiesObject = null, jsonPropsObject = null, jsonPropsPropertiesObject = null, jsonPropsOptionObject = null, jsonPropsOptionsPropertiesObject = null;
    Map<String, String> detailProperties = null;
    Map<String, String> propsProperties = null;
    Map<String, JsonObject> propsConfigMap = null;


    String[] borderTops = {"border-top-width", "border-top-style", "border-top-color"};
    String[] borderBottoms = {"border-bottom-width", "border-bottom-style", "border-bottom-color"};
    String[] borderRights = {"border-right-width", "border-right-style", "border-right-color"};
    String[] borderLefts = {"border-left-width", "border-left-style", "border-left-color"};
    String[] paddings = {"padding-top", "padding-bottom", "padding-right", "padding-left"};

    String[] borderWidths = {"border-top-width", "border-right-width", "border-bottom-width", "border-left-width"};
    String[] borderStyles = {"border-top-style", "border-bottom-style", "border-right-style", "border-left-style"};
    String[] borderColors = {"border-top-color", "border-right-color", "border-bottom-color", "border-left-color"};
    String[] borderRadii = {"border-top-left-radius", "border-top-right-radius", "border-bottom-right-radius", "border-bottom-left-radius"};

    private String infoMsgClassName = "pe-input--info_message";
    private String errorMsgClassName = "pe-input--error_message";
    private String preConfigStr1 = "function init() {";
    private String preConfigStr2 = "document.body.dispatchEvent(new CustomEvent('o.InitComponents', ";
    private String postConfigStr1 = "));}window.onload = init;";

    final static Logger log = Logger.getLogger(InputsTest.class.getName());
    FunctionalInputsPageObjects compInputsPgObj = null;
    InputsPageObjects inputsPgObj = null;

    @BeforeClass(alwaysRun = true)
    private void InputsTestBeforeClass() {
        compInputsPgObj = new FunctionalInputsPageObjects();
        inputsPgObj = new InputsPageObjects();
        browser = BaseClass.sauceBrowser;
        lBrowser = BaseClass.localBrowser;
        setMobile = BaseClass.mobile;
        setPlatform = BaseClass.platform;
        setAppium = BaseClass.appiumDriver;
    }

    /****************
     * DESKTOP TESTS
     ***************/

    //Fancy - Text Input
    @DataProvider(name = "Single Line Text Input Wi/Wo Values Test Data")
    public Object[][] getSingleLineTextInputWiWoValuesTestData() {
        return new Object[][]{
                {"fancy-text-input-with-value", inputsPgObj.slTextInput, new String[]{commonUtils.hex2Rgb("#252525"), commonUtils.hex2RgbWithoutTransparency("#252525")}, new String[]{"14px"}},
                {"basic-text-input-without-value", inputsPgObj.inputBasicSingleLineWithoutValue, new String[]{commonUtils.hex2Rgb("#252525"), commonUtils.hex2RgbWithoutTransparency("#252525")}, new String[]{"14px"}},
        };
    }

    @Test(testName = "Verify Single Line Text Input Wi/Wo Values", dataProvider = "Single Line Text Input Wi/Wo Values Test Data", groups = "bug-desktop-regression")
    //will enable this test when jason adds the 'value' prop
    private void singleLineTextInputWiWoValuesTest(String type, By element, String[] expColor, String[] expFontSize) throws Exception {
        String[] detailsPropertiesList = new String[]{"elementId", "text-input-target", "componentName", "TextInput"};
        String[] propsPropertiesList = new String[]{"id", "sl-text-input", "labelText", "Last Name", "inputType", "default", "fancy", "true", "placeholder", "Last Name"};
        setConfigAndLaunch(detailsPropertiesList, propsPropertiesList);

        fontSize = commonUtils.getCSSValue(element, "font-size");
        isFontSize = commonUtils.assertCSSProperties("font-size", fontSize, expFontSize);
        if (!isFontSize) {
            log.info("font-size for " + type + " is not as per the spec, actual: " + fontSize);
        }
        color = commonUtils.getCSSValue(element, "color");
        isColor = commonUtils.assertCSSProperties("color", color, expColor);
        if (!isColor) {
            log.info("color for " + type + " is not as per the spec, actual: " + color);
        }
        Assert.assertTrue(isFontSize && isColor);
    }

    @DataProvider(name = "Fancy - Single Line Text Input Default/Error/ReadOnly Test Data")
    public Object[][] getFancySingleLineTextInputDefaultErrorReadOnlyTestData() {
        return new Object[][]{
                {"default","sl-text-input", inputsPgObj.slTextInput, new String[]{"1px", "solid", "#6A7070"}, new String[]{"14px"}, new String[]{commonUtils.hex2Rgb("#252525"), commonUtils.hex2RgbWithoutTransparency("#252525")}, new String[]{"none", "solid", "none", "none"}, new String[]{"8px", "10px", "0px", "0px"}, new String[]{macChromeFontFamily, ffFontFamily, safariFontFamily, ieFontFamily}},
                {"error","sl-text-input-error", inputsPgObj.slTextInputErrored, new String[]{"1px", "solid", "#DB0020"}, new String[]{"14px"}, new String[]{commonUtils.hex2Rgb("#252525"), commonUtils.hex2RgbWithoutTransparency("#252525")}, new String[]{"none", "solid", "none", "none"}, new String[]{"8px", "10px", "0px", "0px"}, new String[]{macChromeFontFamily, ffFontFamily, safariFontFamily, ieFontFamily}},
                {"readOnly", "sl-text-input-readonly", inputsPgObj.slTextInputReadOnly, new String[]{"0px", "none", "#252525"}, new String[]{"14px"}, new String[]{commonUtils.hex2Rgb("#252525"), commonUtils.hex2RgbWithoutTransparency("#252525")},  new String[]{"none", "none", "none", "none"}, new String[]{"8px", "10px", "0px", "0px"}, new String[]{macChromeFontFamily, ffFontFamily, safariFontFamily, ieFontFamily}}};
    }

    //Fancy - Inputs (Single line)
    @Test(testName = "Fancy - Verify Single Line Text Input - Default/Error/ReadOnly", dataProvider = "Fancy - Single Line Text Input Default/Error/ReadOnly Test Data", groups = {"desktop-ci", "desktop-regression"})
    private void fancySingleLineTextInputDefaultErrorReadOnlyTest(String type, String id, By element, String[] expBorderBottoms, String[] expFontSize, String[] expColor, String[] expBorderStyle, String[] expPadding, String[] expFontFamily) throws Exception {
        String[] detailsPropertiesList = new String[]{"elementId", "text-input-target", "componentName", "TextInput"};
        String[] propsPropertiesList = new String[]{"id", id, "labelText", "Last Name", "inputState", type, "fancy", "true", "placeholder", "Last Name", "changeHandler", "function () {}", "infoMessage", "This is an info message", "errorMessage", "This is an error message'"};
        setConfigAndLaunch(detailsPropertiesList, propsPropertiesList);

        fontSize = commonUtils.getCSSValue(element, "font-size");
        color = commonUtils.getCSSValue(element, "color");
        fontFamily = commonUtils.getCSSValue(element, "font-family");

        isFontSize = commonUtils.assertCSSProperties("font-size", fontSize, expFontSize);
        isColor = commonUtils.assertCSSProperties("color", color, expColor);
        isFontFamily = commonUtils.assertCSSProperties("font-family", fontFamily, expFontFamily);

        if (!isFontSize) {
            log.info("font-size for Fancy - Single Line Text Input - " + type + " is not as per the spec, actual: " + fontSize);
        }
        if (!isColor) {
            log.info("color for Fancy - Single Line Text Input - " + type + " is not as per the spec, actual: " + color);
        }
        if (!isFontFamily) {
            log.info("font-family for Fancy - Single Line Text Input - " + type + " is not as per the spec, actual: " + fontFamily);
        }

        Assert.assertTrue(isFontSize && isColor && isFontFamily);

        for (int i = 0; i < borderStyles.length; i++) {
            borderStyle = commonUtils.getCSSValue(element, borderStyles[i]);
            isBorderStyle = commonUtils.assertValue(borderStyle, expBorderStyle[i], borderStyles[i] + " for fancy - Single Line Text - " + type + "is not as per the spec");
            Assert.assertTrue(isBorderStyle);
        }

        for (int i = 0; i < paddings.length; i++) {
            padding = commonUtils.getCSSValue(element, paddings[i]);
            isPadding = commonUtils.assertValue(padding, expPadding[i], paddings[i] + " for fancy - Single Line Text - " + type + "is not as per the spec");
            Assert.assertTrue(isPadding);
        }
        testBorders("Fancy - Single Line Text Input - "+type+" ", borderBottom, borderBottoms, expBorderBottoms, element);
    }

    @DataProvider(name = "Fancy - Single Line Text Input Disabled Test Data")
    public Object[][] getFancySingleLineInputDisabledTestData() {
        return new Object[][]{
                {inputsPgObj.slTextInputDisabled, new String[]{"4px", "solid", "#C7C7C7"}, new String[]{commonUtils.hex2Rgb("#C7C7C7"), commonUtils.hex2RgbWithoutTransparency("#C7C7C7")}, new String[]{commonUtils.hex2Rgb("#FFFFFF"), commonUtils.hex2RgbWithoutTransparency("#FFFFFF")}, new String[]{macChromeFontFamily, ffFontFamily, safariFontFamily, ieFontFamily}}
        };
    }

    //Fancy - Inputs (single line - disabled)
    @Test(testName = "Fancy - Verify Single Line Text Input - Disabled", dataProvider = "Fancy - Single Line Text Input Disabled Test Data", groups = "desktop-regression")
    private void fancySingleLineTextInputDisabledTest(By element, String[] expBorderBottoms, String[] expColor, String[] expBackgroundColor, String[] expFontFamily) throws Exception {
        String[] detailsPropertiesList = new String[]{"elementId", "text-input-target", "componentName", "TextInput"};
        String[] propsPropertiesList = new String[]{"id", "sl-text-input-disabled", "labelText", "Last Name", "inputState", "disabled", "fancy", "true", "placeholder", "Last Name", "changeHandler", "function () {}", "infoMessage", "This is an info message", "errorMessage", "This is an error message'"};
        setConfigAndLaunch(detailsPropertiesList, propsPropertiesList);

        color = commonUtils.getCSSValue(element, "color");
        backgroundColor = commonUtils.getCSSValue(element, "background-color");
        fontFamily = commonUtils.getCSSValue(element, "font-family");

        isColor = commonUtils.assertCSSProperties("color", color, expColor);
        isBackgroundColor = commonUtils.assertCSSProperties("background-color", backgroundColor, expBackgroundColor);
        isFontFamily = commonUtils.assertCSSProperties("font-family", fontFamily, expFontFamily);

        if (!isColor) {
            log.info("color for Fancy - Single Line Text Input Disabled is not as per the spec, actual: " + color);
        }
        if (!isBackgroundColor) {
            log.info("background-color for Fancy - Single Line Text Input Disabled is not as per the spec, actual: " + backgroundColor);
        }
        if (!isFontFamily) {
            log.info("font-family for Fancy - Single Line Text Input Disabled is not as per the spec, actual: " + fontFamily);
        }

        Assert.assertTrue(isColor && isBackgroundColor && isFontFamily);
        testBorders("Fancy - Single Line Text Input - Disabled", borderBottom, borderBottoms, expBorderBottoms, element);
    }

    @DataProvider(name = "Fancy - Single Line Text Input ReadOnly Test Data")
    public Object[][] getFancySingleLineInputReadOnlyTestData() {
        return new Object[][]{
                {inputsPgObj.slTextInputReadOnly, new String[]{"0px", "none", "#252525"}, new String[]{commonUtils.hex2Rgb("#252525"), commonUtils.hex2RgbWithoutTransparency("#252525")}, new String[]{"14px"}, new String[]{"none", "none", "none", "none"}, new String[]{"8px", "10px", "0px", "0px"}, new String[]{macChromeFontFamily, ffFontFamily, safariFontFamily, ieFontFamily}}
        };
    }

    @DataProvider(name = "Fancy - Single Line Text Input - Focus state Test Data")
    public Object[][] getSingleLineTextInputFocusStateTestData() {
        return new Object[][]{
                {"default", "sl-text-input", inputsPgObj.slTextInput, "sl-text-input", "none", "input-underline", inputsPgObj.textInputUnderLineClass, new String[]{commonUtils.hex2Rgb("#047A9C"), commonUtils.hex2RgbWithoutTransparency("#047A9C")}, "block", new String[]{"4px"}, new String[]{"0s"}, new String[]{"0.2s"}, "all", new String[]{"ease", "cubic-bezier(0.25, 0.1, 0.25, 1)"}},
                {"error", "sl-text-input-error", inputsPgObj.slTextInputErrored, "sl-text-input-error", "none", "input-underline-error", inputsPgObj.textInputUnderLineErrorClass, new String[]{commonUtils.hex2Rgb("#DB0020"), commonUtils.hex2RgbWithoutTransparency("#DB0020")}, "block", new String[]{"4px"}, new String[]{"0s"}, new String[]{"0.2s"}, "all", new String[]{"ease", "cubic-bezier(0.25, 0.1, 0.25, 1)"}},
                {"readOnly", "sl-text-label-input-readonly", inputsPgObj.slTextLableInputReadOnly, "sl-text-label-input-readonly", "none", "", By.xpath(""), new String[]{""}, "", new String[]{""}, new String[]{""}, new String[]{""}, "", new String[]{""}}
        };
    }

    //Fancy Inputs - States
    @Test(testName = "Fancy - Verify Single Line Text Input - Focus state", dataProvider = "Fancy - Single Line Text Input - Focus state Test Data", groups = {"desktop-regression"})
    private void fancySingleLineTextInputFocusStateTest(String inputState, String type, By element, String id, String expOutlineStyle, String underlineElementType, By underlineElement, String[] expUnderlineBackgroundColor, String expDisplay, String[] expUnderlineHeight, String[] expUnderlineTrasitionDelay, String[] expUnderlineTrasitionDuration, String expUnderlineTransitionProp, String[] expUnderlineTransitionTimingFunc) throws Exception {
        if ((browser.equals("firefox")) || browser.equals("safari") || browser.equals("ie") || lBrowser.equals("firefox")) {
            throw new SkipException("Focus operation not yet supported in firefox/safari browser drivers");
        }
        String[] detailsPropertiesList = new String[]{"elementId", "text-input-target", "componentName", "TextInput"};
        String[] propsPropertiesList = new String[]{"id", id, "labelText", "First Name", "inputState", inputState, "fancy", "true", "placeholder", "First Name", "changeHandler", "function () {}", "infoMessage", "This is an info message", "errorMessage", "This is an error message'"};
        setConfigAndLaunch(detailsPropertiesList, propsPropertiesList);

        commonUtils.focusOnElementById(id);
        outlineStyle = commonUtils.getCSSValue(element, "outline-style");
        isOutlineStyle = commonUtils.assertValue(outlineStyle, expOutlineStyle, "'" + type + "' :for Single Line Input - Focus state is not as per the spec");

        if (!(type.equals("sl-text-label-input-readonly"))) {
            backgroundColor = commonUtils.getCSSValue(underlineElement, "background-color");
            len = backgroundColor.length();
            lastIndexOf = backgroundColor.lastIndexOf(',');
            unroundedTransValue = backgroundColor.substring(lastIndexOf + 1, len - 1);
            try {
                roundedTransValue = Math.round(Float.valueOf(Float.valueOf(String.valueOf(unroundedTransValue))));
            } catch (NumberFormatException e) {
                log.info("number format exception for background color");
            }
            backgroundColor = backgroundColor.substring(0, backgroundColor.lastIndexOf(',')) + ", " + roundedTransValue + ")";
            isBackgroundColor = commonUtils.assertCSSProperties("background-color", backgroundColor, expUnderlineBackgroundColor);
            if (!isBackgroundColor) {
                log.info("background-color for " + underlineElementType + " is not as per the spec, actual: " + backgroundColor);
            }
            display = commonUtils.getCSSValue(underlineElement, "display");
            isDisplay = commonUtils.assertValue(display, expDisplay, "underline color for '" + underlineElementType + "' is not as per the spec");
            height = commonUtils.getCSSValue(underlineElement, "height");
            isHeight = commonUtils.assertCSSProperties("height", height, expUnderlineHeight);
            if (!isHeight) {
                log.info("height for " + underlineElementType + " is not as per the spec, actual: " + height);
            }
            transitionDelay = commonUtils.getCSSValue(underlineElement, "transition-delay");
            isTransitionDelay = commonUtils.assertCSSProperties("transitionDelay", transitionDelay, expUnderlineTrasitionDelay);
            if (!isTransitionDelay) {
                log.info("transitionDelay for " + underlineElementType + " is not as per the spec, actual: " + transitionDelay);
            }
            transitionDuration = commonUtils.getCSSValue(underlineElement, "transition-duration");
            isTransitionDuration = commonUtils.assertCSSProperties("transitionDuration", transitionDuration, expUnderlineTrasitionDuration);
            if (!isTransitionDuration) {
                log.info("transitionDuration for " + underlineElementType + " is not as per the spec, actual: " + transitionDuration);
            }
            transitionProp = commonUtils.getCSSValue(underlineElement, "transition-property");
            isTransitionProp = commonUtils.assertValue(transitionProp, expUnderlineTransitionProp, "'" + underlineElementType + "' :for Single Line Input - Focus state is not as per the spec");
            trainsitionTimingFunc = commonUtils.getCSSValue(underlineElement, "transition-timing-function");
            isTransitionTimingFunc = commonUtils.assertCSSProperties("transition-timing-function", trainsitionTimingFunc, expUnderlineTransitionTimingFunc);
            if (!isTransitionTimingFunc) {
                log.info("" + "'" + underlineElementType + "' :for Single Line Input - Focus state is not as per the spec, actual: " + expUnderlineTransitionTimingFunc);
            }
            Assert.assertTrue(isOutlineStyle && isBackgroundColor && isDisplay && isHeight && isTransitionDelay && isTransitionDuration && isTransitionProp && isTransitionTimingFunc);
        }
    }

    @DataProvider(name = "Fancy - Single Line Text Input - underline Test Data")
    public Object[][] getSingleLineTextInputUnderlineTestData() {
        return new Object[][]{
                {"default", "input-underline", inputsPgObj.textInputUnderLineClass, new String[]{commonUtils.hex2Rgb("#047a9c"), commonUtils.hex2RgbWithoutTransparency("#047a9c")}, "block", new String[]{"4px"}, new String[]{"0s"}, new String[]{"0.2s"}, "all", "ease"},
                {"error", "input-underline-error", inputsPgObj.textInputUnderLineErrorClass, new String[]{commonUtils.hex2Rgb("#DB0020"), commonUtils.hex2RgbWithoutTransparency("#DB0020")}, "block", new String[]{"4px"}, new String[]{"0s"}, new String[]{"0.2s"}, "all", "ease"},
        };
    }

    @Test(testName = "Fancy - Verify Single Line Text Input - underline", dataProvider = "Fancy - Single Line Text Input - underline Test Data", groups = {"desktop-regression"})
    private void singleLineTextInputUnderlineTest(String inputState, String underlineElementType, By underlineElement, String[] expUnderlineBackgroundColor, String expDisplay, String[] expUnderlineHeight, String[] expUnderlineTrasitionDelay, String[] expUnderlineTrasitionDuration, String expUnderlineTransitionProp, String expUnderlineTransitionTimingFunc) throws Exception {
        String[] detailsPropertiesList = new String[]{"elementId", "text-input-target", "componentName", "TextInput"};
        String[] propsPropertiesList = new String[]{"id", underlineElementType, "labelText", "First Name", "inputState", inputState, "fancy", "true", "placeholder", "First Name", "changeHandler", "function () {}", "infoMessage", "This is an info message", "errorMessage", "This is an error message'"};
        setConfigAndLaunch(detailsPropertiesList, propsPropertiesList);

        backgroundColor = commonUtils.getCSSValue(underlineElement, "background-color");
        isBackgroundColor = commonUtils.assertCSSProperties("background-color", backgroundColor, expUnderlineBackgroundColor);
        if (!isBackgroundColor) {
            log.info("background-color for " + underlineElementType + " is not as per the spec, actual: " + backgroundColor);
        }
        display = commonUtils.getCSSValue(underlineElement, "display");
        isDisplay = commonUtils.assertValue(display, expDisplay, "underline color for '" + underlineElementType + "' is not as per the spec");
        height = commonUtils.getCSSValue(underlineElement, "height");
        isHeight = commonUtils.assertCSSProperties("height", height, expUnderlineHeight);
        if (!isHeight) {
            log.info("height for " + underlineElementType + " is not as per the spec, actual: " + height);
        }
        transitionDelay = commonUtils.getCSSValue(underlineElement, "transition-delay");
        isTransitionDelay = commonUtils.assertCSSProperties("transitionDelay", transitionDelay, expUnderlineTrasitionDelay);
        if (!isTransitionDelay) {
            log.info("transitionDelay for " + underlineElementType + " is not as per the spec, actual: " + transitionDelay);
        }
        transitionDuration = commonUtils.getCSSValue(underlineElement, "transition-duration");
        isTransitionDuration = commonUtils.assertCSSProperties("transitionDuration", transitionDuration, expUnderlineTrasitionDuration);
        if (!isTransitionDuration) {
            log.info("transitionDuration for " + underlineElementType + " is not as per the spec, actual: " + transitionDuration);
        }
        transitionProp = commonUtils.getCSSValue(underlineElement, "transition-property");
        isTransitionProp = commonUtils.assertValue(transitionProp, expUnderlineTransitionProp, "'" + underlineElementType + "' :for Single Line Input - Focus state is not as per the spec");
        trainsitionTimingFunc = commonUtils.getCSSValue(underlineElement, "transition-timing-function");
        isTransitionTimingFunc = commonUtils.assertValue(trainsitionTimingFunc, expUnderlineTransitionTimingFunc, "'" + underlineElementType + "' :for Single Line Input - Focus state is not as per the spec");
        Assert.assertTrue(isBackgroundColor && isDisplay && isHeight && isTransitionDelay && isTransitionDuration && isTransitionProp && isTransitionTimingFunc);
    }

    //Text Label Input
    @DataProvider(name = "Single Line Text Input - Text Label Test Data")
    public Object[][] getSingleLineTextInputLabelTestData() {
        return new Object[][]{
                {"default", "sl-text-label-input", inputsPgObj.textLabelInputClass, "true", new String[]{"12px"}, new String[]{commonUtils.hex2Rgb("#6A7070"), commonUtils.hex2RgbWithoutTransparency("#6A7070")}},
                {"error", "text-label-input-errored", inputsPgObj.textLabelInputErrorClass, "false", new String[]{"12px"}, new String[]{commonUtils.hex2Rgb("#DB0020"), commonUtils.hex2RgbWithoutTransparency("#DB0020")}},
                {"disabled", "text-label-input-disabled", inputsPgObj.textLabelInputDisabledClass, "true", new String[]{"12px"}, new String[]{commonUtils.hex2Rgb("#6A7070"), commonUtils.hex2RgbWithoutTransparency("#6A7070")}},
                {"readOnly", "text-label-input-readonly", inputsPgObj.textLabelInputClass, "false", new String[]{"12px"}, new String[]{commonUtils.hex2Rgb("#6A7070"), commonUtils.hex2RgbWithoutTransparency("#6A7070")}}
        };
    }

    @Test(testName = "Verify Single Line Text Input - Text Label", dataProvider = "Single Line Text Input - Text Label Test Data", groups = {"desktop-ci", "desktop-regression"})
    private void singleLineTextInputInputTest(String inputState, String id, By element, String type, String[] expFontSize, String[] expColor) throws Exception {
        String[] detailsPropertiesList = new String[]{"elementId", "text-input-target", "componentName", "TextInput"};
        String[] propsPropertiesList = new String[]{"id", id, "labelText", "Last Name", "inputState", inputState, "fancy", type, "placeholder", "Last Name", "changeHandler", "function () {}", "infoMessage", "This is an info message", "errorMessage", "This is an error message'"};
        setConfigAndLaunch(detailsPropertiesList, propsPropertiesList);

        fontSize = commonUtils.getCSSValue(element, "font-size");
        isFontSize = commonUtils.assertCSSProperties("font-size", fontSize, expFontSize);
        if (!isFontSize) {
            log.info("font-size for " + inputState + " is not as per the spec, actual: " + fontSize);
        }
        color = commonUtils.getCSSValue(element, "color");
        isColor = commonUtils.assertCSSProperties("color", color, expColor);
        if (!isColor) {
            log.info("color for " + inputState + " is not as per the spec, actual: " + color);
        }
        Assert.assertTrue(isFontSize && isColor);
    }

    //Basic - Text Input
    @DataProvider(name = "Basic - Single Line Text Input - Active/Error/Disabled Test Data")
    public Object[][] getBasicSingleLineTextInputTestData() {
        return new Object[][]{
                {"Active", "default", "z", inputsPgObj.inputBasicSingleLine, new String[]{commonUtils.hex2Rgb("#FFFFFF"), commonUtils.hex2RgbWithoutTransparency("#FFFFFF")}, "6px", "14px", "14px", new String[]{"36px", "34px"}, "3px", new String[]{"14px", "13.93px", "18.66px"}, "18px"},
                {"Error", "error", "y", inputsPgObj.inputBasicError, new String[]{commonUtils.hex2Rgb("#FFFFFF"), commonUtils.hex2RgbWithoutTransparency("#FFFFFF")}, "6px", "14px", "14px", new String[]{"36px", "34px"}, "3px", new String[]{"14px", "13.93px", "18.66px"}, "18px"},
                {"Disabled", "disabled", "x", inputsPgObj.inputBasicDisabled, new String[]{commonUtils.hex2Rgb("#E9E9E9"), commonUtils.hex2RgbWithoutTransparency("#E9E9E9")}, "6px", "14px", "14px", new String[]{"36px", "34px"}, "3px", new String[]{"14px", "13.93px", "18.66px"}, "18px"}
        };
    }

    @Test(testName = "Basic - Verify Single Line Text Input - Active/Error/Disabled", dataProvider = "Basic - Single Line Text Input - Active/Error/Disabled Test Data", groups = "desktop-regression")
    private void basicSingleLineTextInputTest(String type, String inputState, String id, By element, String[] expBgColor, String expMarginTop, String expPaddingRight, String expPaddingLeft, String[] expHeight, String expBorderRad, String[] expFontSize, String expLineHt) throws Exception {
        String[] detailsPropertiesList = new String[]{"elementId", "text-input-target", "componentName", "TextInput"};
        String[] propsPropertiesList = new String[]{"id", id, "labelText", "Last Name", "inputState", inputState, "fancy", "false", "placeholder", "Last Name", "changeHandler", "function () {}", "infoMessage", "This is an info message", "errorMessage", "This is an error message'"};
        setConfigAndLaunch(detailsPropertiesList, propsPropertiesList);

        bgColor = commonUtils.getCSSValue(element, "background-color");
        marginTop = commonUtils.getCSSValue(element, "margin-top");
        paddingRight = commonUtils.getCSSValue(element, "padding-right");
        paddingLeft = commonUtils.getCSSValue(element, "padding-left");
        height = commonUtils.getCSSValue(element, "height");
        fontSize = commonUtils.getCSSValue(element, "font-size");
        lineHeight = commonUtils.getCSSValue(element, "line-height");
        for (String cssProperty : borderRadii) {
            borderRadius = commonUtils.getCSSValue(element, cssProperty);
            isBorderRadius = commonUtils.assertValue(borderRadius, expBorderRad, "Border radius " + cssProperty + " of Input-Basic Single Line (" + type + ") field is not as per spec ");
            Assert.assertTrue(isBorderRadius);
        }

        isBackgroundColor = commonUtils.assertCSSProperties("background-color", bgColor, expBgColor);
        if (!isBackgroundColor) {
            log.info("Background color of Input-Basic Single Line (" + type + ") field is not as per spec exp, actual " + bgColor);
        }
        isMarginTop = commonUtils.assertValue(marginTop, expMarginTop, "margin-top of Input-Basic Single Line (Active) field is not as per spec");
        isPaddingRight = commonUtils.assertValue(paddingRight, expPaddingRight, "Padding right of Input-Basic Single Line (Active) field is not as per spec");
        isPaddingLeft = commonUtils.assertValue(paddingLeft, expPaddingLeft, "Padding left of Input-Basic Single Line (Active) field is not as per spec");
        isHeight = commonUtils.assertCSSProperties("height", height, expHeight);
        if (!isHeight) {
            log.info("Box height of Input-Basic Single Line (" + type + ") field is not as per spec, actual " + height);
        }
        isFontSize = commonUtils.assertCSSProperties("font-size", fontSize, expFontSize);
        if (!isFontSize) {
            log.info("Font Size of Input-Basic Single Line (" + type + ") field is not as per spec, actual " + fontSize);
        }
        isLineHeight = commonUtils.assertValue(lineHeight, expLineHt, "Line height of Input-Basic Single Line (" + type + ") field is not as per spec");

        Assert.assertTrue(isBackgroundColor && isMarginTop && isPaddingRight && isPaddingLeft && isHeight && isFontSize && isLineHeight);
    }

    @DataProvider(name = "Basic - Single Line Text Input - Active/Error/Disabled - Borders Test Data")
    public Object[][] getBasicSingleLineTextInputBordersTestData() {
        return new Object[][]{
                {"Active", "default", "z", inputsPgObj.inputBasicSingleLine, "1px", "solid", new String[]{commonUtils.hex2Rgb("#C7C7C7"), commonUtils.hex2RgbWithoutTransparency("#C7C7C7")}},
                {"Error", "error", "y", inputsPgObj.inputBasicError, "1px", "solid", new String[]{commonUtils.hex2Rgb("#DB0020"), commonUtils.hex2RgbWithoutTransparency("#DB0020")}},
                {"Disabled", "disabled", "x", inputsPgObj.inputBasicDisabled, "1px", "solid", new String[]{commonUtils.hex2Rgb("#C7C7C7"), commonUtils.hex2RgbWithoutTransparency("#C7C7C7")}}
        };
    }

    @Test(testName = "Basic - Verify Single Line Text Input - Active/Error/Disabled Borders", dataProvider = "Basic - Single Line Text Input - Active/Error/Disabled - Borders Test Data", groups = "desktop-regression")
    private void basicSingleLineTextInputBordersTest(String type, String inputState, String id, By element, String expBorderWidth, String expBorderStyle, String[] expBorderColor) throws Exception {
        String[] detailsPropertiesList = new String[]{"elementId", "text-input-target", "componentName", "TextInput"};
        String[] propsPropertiesList = new String[]{"id", id, "labelText", "Last Name", "inputState", inputState, "fancy", "false", "placeholder", "Last Name", "changeHandler", "function () {}", "infoMessage", "This is an info message", "errorMessage", "This is an error message'"};
        setConfigAndLaunch(detailsPropertiesList, propsPropertiesList);

        for (String cssProperty : borderWidths) {
            borderWidth = commonUtils.getCSSValue(element, cssProperty);
            isBorderWidth = commonUtils.assertValue(borderWidth, expBorderWidth, "Border width " + cssProperty + " of Input-Basic Single Line (" + type + ") field is not as per spec");
            Assert.assertTrue(isBorderWidth);
        }
        for (String cssProperty : borderStyles) {
            borderStyle = commonUtils.getCSSValue(element, cssProperty);
            isBorderStyle = commonUtils.assertValue(borderStyle, expBorderStyle, "Border style " + cssProperty + " of Input-Basic Single Line (" + type + ") field is not as per spec");
            Assert.assertTrue(isBorderStyle);
        }
        for (String cssProperty : borderColors) {
            borderColor = commonUtils.getCSSValue(element, cssProperty);
            isBorderColor = commonUtils.assertCSSProperties(cssProperty, borderColor, expBorderColor);
            if (!isBorderColor) {
                log.info("Border color " + cssProperty + " of Input-Basic Single Line (" + type + ") field is not as per spec, actual: " + borderColor);
            }
            Assert.assertTrue(isBorderColor);
        }
    }

    @DataProvider(name = "Basic - Single Line Text Input - Active/Error/Disabled - Label Test Data")
    public Object[][] getBasicSingleLineTextInputLabelTestData() {
        return new Object[][]{
                {"Active", "default", "basic-sl-label", inputsPgObj.textLabelInputClass, "z", inputsPgObj.inputBasicSingleLine, new String[]{commonUtils.hex2Rgb("#6A7070"), commonUtils.hex2RgbWithoutTransparency("#6A7070")}, "12px"},
                {"Error", "error", "basic-error-label", inputsPgObj.textLabelInputErrorClass, "y", inputsPgObj.inputBasicError, new String[]{commonUtils.hex2Rgb("#DB0020"), commonUtils.hex2RgbWithoutTransparency("#DB0020")}, "12px"},
                {"Disabled", "disabled", "basic-disabled-label", inputsPgObj.textLabelInputDisabledClass, "x", inputsPgObj.inputBasicDisabled, new String[]{commonUtils.hex2Rgb("#6A7070"), commonUtils.hex2RgbWithoutTransparency("#6A7070")}, "12px"}
        };
    }

    @Test(testName = "Basic - Verify Single Line Text Input - Active/Error/Disabled Label", dataProvider = "Basic - Single Line Text Input - Active/Error/Disabled - Label Test Data", groups = "desktop-regression")
    private void basicSingleLineTextInputLabelTest(String type, String inputState, String labelId, By elementForLabel, String id, By element, String[] expLabelColor, String expLabelFontSize) throws Exception {
        String[] detailsPropertiesList = new String[]{"elementId", "text-input-target", "componentName", "TextInput"};
        String[] propsPropertiesList = new String[]{"id", id, "labelText", "Last Name", "inputState", inputState, "fancy", "false", "placeholder", "Last Name", "changeHandler", "function () {}", "infoMessage", "This is an info message", "errorMessage", "This is an error message'"};
        setConfigAndLaunch(detailsPropertiesList, propsPropertiesList);

        js = (JavascriptExecutor) driver;
        webElement = driver.findElement(elementForLabel);
        js.executeScript("arguments[0].setAttribute('id', '" + labelId + "')", webElement);

        color = commonUtils.getCSSValue(elementForLabel, "color");
        fontSize = commonUtils.getCSSValue(elementForLabel, "font-size");
        lineHeight = commonUtils.getCSSValue(elementForLabel, "line-height");
        isFontSize = commonUtils.assertValue(fontSize, expLabelFontSize, "Label font size of Input-Basic Single Line (" + type + ") label is not as per spec");
        isColor = commonUtils.assertCSSProperties("color", color, expLabelColor);
        if (!isColor) {
            log.info("Label color of Input-Basic Single Line (" + type + ") is not as per spec, actual:" + color);
        }
        isLineHeight = commonUtils.assertValue(lineHeight, "16px", "Line-height of Input-Basic Single Line (" + type + ") label is not as per spec");
        isLabelFor = commonUtils.checkLabelForVal(elementForLabel, element);
        Assert.assertTrue(isColor && isFontSize && isLabelFor);
    }

    @DataProvider(name = "Basic - Single Line Text Input - Active/Error - Focus Test Data")
    public Object[][] getBasicSingleLineTextInputFocusTestData() {
        return new Object[][]{
                {"default", "z", inputsPgObj.inputBasicSingleLabel, "basic-sl-label", inputsPgObj.inputBasicSingleLine, new String[]{"rgb(4, 122, 156) 0px 0px 5px 0px"}, new String[]{"1px solid rgb(4, 122, 156)"}, "3px"},
                {"error", "y", inputsPgObj.inputBasicErrorLabel, "basic-error-label", inputsPgObj.inputBasicError, new String[]{"rgb(219, 0, 32) 0px 0px 4px 0px"}, new String[]{"1px solid rgb(219, 0, 32)"}, "3px"}
        };
    }

    @Test(testName = "Basic - Verify Single Line Text Input - Active/Error - Focus", dataProvider = "Basic - Single Line Text Input - Active/Error - Focus Test Data", groups = "desktop-regression")
    private void basicSingleLineTextInputFocusTest(String inputState, String id, By labelElement, String labelId, By element, String[] expBoxShadow, String[] expBorder, String expBorderRad) throws Exception {
        if (browser.equals("firefox") || browser.equals("safari") || browser.equals("ie") || browser.equals("edge") || lBrowser.equals("firefox")) {
            throw new SkipException("the focus operation is not supported on firefox/safari/ie drivers");
        }

        String[] detailsPropertiesList = new String[]{"elementId", "text-input-target", "componentName", "TextInput"};
        String[] propsPropertiesList = new String[]{"id", id, "labelText", "Last Name", "inputState", inputState, "fancy", "false", "placeholder", "Last Name", "changeHandler", "function () {}", "infoMessage", "This is an info message", "errorMessage", "This is an error message'"};
        setConfigAndLaunch(detailsPropertiesList, propsPropertiesList);

        commonUtils.focusOnElementById(id);
        boxShadow = commonUtils.getCSSValue(element, "box-shadow");
        basicInputBorder = commonUtils.getCSSValue(element, "border");
        borderRadius = commonUtils.getCSSValue(element, "border-radius");

        isBoxShadow = commonUtils.assertCSSProperties("box-shadow", boxShadow, expBoxShadow);
        if (!isBoxShadow) {
            log.info("Box shadow of Input-Basic Single Line (Focus) field is not as per spec exp, actual " + boxShadow);
        }
        isBasicInputBorder = commonUtils.assertCSSProperties("border", basicInputBorder, expBorder);
        if (!isBasicInputBorder) {
            log.info("Border of Input-Basic Single Line (Focus) field is not as per spec exp, actual " + basicInputBorder);
        }
        isBorderRadius = commonUtils.assertValue(borderRadius, expBorderRad, "Border radius of Input-Basic Single Line (Focus) field is not as per spec");

        js = (JavascriptExecutor) driver;
        webElement = driver.findElement(By.xpath("//label"));
        js.executeScript("arguments[0].setAttribute('id', '" + labelId + "')", webElement);

        isLabelFor = commonUtils.checkLabelForVal(labelElement, element);
        Assert.assertTrue(isBoxShadow && isBasicInputBorder && isBorderRadius && isLabelFor);
    }

    //input--error_message
    @DataProvider(name = "Single Line Text Input Info/Error Message Test Data")
    public Object[][] getSingleLineTextInputErrorMessageTestData() {
        return new Object[][]{
                {"fancy", "error", "pe-input--error_message-fancy", inputsPgObj.textInputErrorMessageClass, "true", new String[]{"3px"}, new String[]{commonUtils.hex2Rgb("#DB0020"), commonUtils.hex2RgbWithoutTransparency("#DB0020")}, new String[]{"12px"}, new String[]{"16px"}},
                {"basic", "error", "pe-input--error_message-basic", inputsPgObj.textInputErrorMessageClass, "false", new String[]{"3px"}, new String[]{commonUtils.hex2Rgb("#DB0020"), commonUtils.hex2RgbWithoutTransparency("#DB0020")}, new String[]{"12px"}, new String[]{"16px"}}
        };
    }

    @Test(testName = "Verify Single Line Text Input Info/Error Message Test", dataProvider = "Single Line Text Input Info/Error Message Test Data", groups = "desktop-regression")
    private void singleLineTextInputErrorMessageTest(String inputType, String inputState, String id, By element, String inputTypeValue, String[] expPaddingTop, String[] expColor, String[] expFontSize, String[] expLineHeight) throws Exception {
        String[] detailsPropertiesList = new String[]{"elementId", "text-input-target", "componentName", "TextInput"};
        String[] propsPropertiesList = new String[]{"id", id, "labelText", "Password", "inputState", inputState, "placeholder", "Password", "fancy", inputTypeValue, "changeHandler", "function () {}", "infoMessage", "This is an info message", "errorMessage", "This is an error message'"};
        setConfigAndLaunch(detailsPropertiesList, propsPropertiesList);

        js = (JavascriptExecutor) driver;
        webElement = driver.findElement(element);
        js.executeScript("arguments[0].setAttribute('id', '" + id + "')", webElement);

        paddingTop = commonUtils.getCSSValue(element, "padding-top");
        isPaddingTop = commonUtils.assertCSSProperties("padding-top", paddingTop, expPaddingTop);
        if (!isPaddingTop) {
            log.info("padding-top for " + inputType + " is not as per the spec, actual: " + paddingTop);
        }
        color = commonUtils.getCSSValue(element, "color");
        isColor = commonUtils.assertCSSProperties("color", color, expColor);
        if (!isColor) {
            log.info("color for " + inputType + " is not as per the spec, actual: " + color);
        }
        fontSize = commonUtils.getCSSValue(element, "font-size");
        isFontSize = commonUtils.assertCSSProperties("font-size", fontSize, expFontSize);
        if (!isFontSize) {
            log.info("font-size for " + inputType + " is not as per the spec, actual: " + fontSize);
        }
        lineHeight = commonUtils.getCSSValue(element, "line-height");
        isLineHeight = commonUtils.assertCSSProperties("line-height", lineHeight, expLineHeight);
        if (!isLineHeight) {
            log.info("line-height for " + inputType + " is not as per the spec, actual: " + lineHeight);
        }
        Assert.assertTrue(isPaddingTop && isColor && isFontSize && isLineHeight);
    }

    //Fancy - Password Input
    @DataProvider(name = "Fancy - Password Input - Label,and Msg Test Data")
    private Object[][] getFancyPasswordInputOtherFieldsTestData() {
        return new Object[][]{
                {"default", "password-fancy", "password-input-fancy", inputsPgObj.passwordFancyField, inputsPgObj.textLabelInputClass, "password-input-fancy-label", inputsPgObj.passwordFancyLabel, "password-input-fancy-info", inputsPgObj.passwordFancyInfoMsg, "password-input-fancy-errorMsg", inputsPgObj.passwordFancyErrorMsg, "12px", new String[]{commonUtils.hex2Rgb("#6A7070"), commonUtils.hex2RgbWithoutTransparency("#6A7070")}},
                {"error", "password-fancy-error", "password-input-fancy-error", inputsPgObj.passwordFancyErrorField, inputsPgObj.textLabelInputErrorClass, "password-input-fancy-error-label", inputsPgObj.passwordFancyErrorLabel, "password-input-fancy-error-info", inputsPgObj.passwordFancyErrorInfoMsg, "password-input-fancy-error-errorMsg", inputsPgObj.passwordFancyErrorErrorMsg, "12px", new String[]{commonUtils.hex2Rgb("#DB0020"), commonUtils.hex2RgbWithoutTransparency("#DB0020")}},
                {"disabled", "password-fancy-disabled", "password-input-fancy-disabled", inputsPgObj.passwordFancyDisabledField, inputsPgObj.textLabelInputDisabledClass, "password-input-fancy-disabled-label", inputsPgObj.passwordFancyDisabledLabel, "password-input-fancy-disabled-info", inputsPgObj.passwordFancyDisabledInfoMsg, "password-input-fancy-disabled-errorMsg", inputsPgObj.passwordFancyDisabledErrorMsg, "12px", new String[]{commonUtils.hex2Rgb("#6A7070"), commonUtils.hex2RgbWithoutTransparency("#6A7070")}},
                {"readOnly", "password-fancy-readOnly", "password-input-fancy-readOnly", inputsPgObj.passwordFancyReadOnlyField, inputsPgObj.textLabelInputClass, "password-input-fancy-readOnly-label", inputsPgObj.passwordFancyReadOnlyLabel, "password-input-fancy-readOnly-info", inputsPgObj.passwordFancyReadOnlyInfoMsg, "password-input-fancy-readOnly-errorMsg", inputsPgObj.passwordFancyReadOnlyErrorMsg, "12px", new String[]{commonUtils.hex2Rgb("#6A7070"), commonUtils.hex2RgbWithoutTransparency("#6A7070,")}},
        };
    }

    @Test(testName = "Fancy - Verify Password Input - Label,and Msg", dataProvider = "Fancy - Password Input - Label,and Msg Test Data", groups = {"desktop-ci", "desktop-regression"})
    private void fancyPasswordInputOtherFieldsShowTest(String inputState, String type, String elemId, By elem, By labelClass, String labelId, By label, String infoMsgId, By infoMsg, String errorMsgId, By errorMsg, String expLabelFontSize, String[] expLabelFontColor) throws Exception {
        String[] detailsPropertiesList = new String[]{"elementId", "password-input-target", "componentName", "TextInput"};
        String[] propsPropertiesList = new String[]{"password", "true", "fancy", "true", "id", elemId, "labelText", "Password", "inputState", inputState, "placeholder", "Password", "changeHandler", "function () {}", "showText", "show", "hideText", "hide", "isNotVisibleMsg", "Password is hidden", "isVisibleMsg", "Password is visible", "infoMessage", "This is an info message", "errorMessage", "This is an error message'"};
        setConfigAndLaunch(detailsPropertiesList, propsPropertiesList);

        js = (JavascriptExecutor) driver;
        webElement = driver.findElement(labelClass);
        js.executeScript("arguments[0].setAttribute('id','" + labelId + "')", webElement);

        webElement = driver.findElement(inputsPgObj.textInputInfoMessageClass);
        js.executeScript("arguments[0].setAttribute('id','" + infoMsgId + "')", webElement);

        labelFontSize = commonUtils.getCSSValue(label, "font-size");
        labelColor = commonUtils.getCSSValue(label, "color");
        isLabelFontSize = commonUtils.assertValue(labelFontSize, expLabelFontSize, "The font size of " + type + " Label is not as per spec");
        isLabelColor = commonUtils.assertCSSProperties("color", labelColor, expLabelFontColor);
        if (!isLabelColor) {
            log.info("label color of " + inputState + type + " is not as per spec,actual" + labelColor);
        }
        isLabelFor = commonUtils.checkLabelForVal(label, elem);
        if (!isLabelFor) {
            log.info("the password label is not mapped correctly to the password field  of " + type);
        }

        fontSize = commonUtils.getCSSValue(infoMsg, "font-size");
        isFontSize = commonUtils.assertValue(fontSize, "12px", "Info Msg font size of " + type + " is not as per spec");
        paddingTop = commonUtils.getCSSValue(infoMsg, "padding-top");
        isPaddingTop = commonUtils.assertValue(paddingTop, "3px", "Info Msg padding top of " + type + " is not as per spec");
        color = commonUtils.getCSSValue(infoMsg, "color");
        isColor = commonUtils.assertCSSProperties("color", color, new String[]{commonUtils.hex2Rgb("#6A7070"), commonUtils.hex2RgbWithoutTransparency("#6A7070")});
        if (!isColor) {
            log.info("Font Color of Info Msg of " + type + " is not as per spec, actual " + color);
        }
        Assert.assertTrue(isFontSize && isColor && isPaddingTop);

        if (inputState.equals("error")) {
            webElement = driver.findElement(inputsPgObj.textInputErrorMessageClass);
            js.executeScript("arguments[0].setAttribute('id','" + errorMsgId + "')", webElement);
            Thread.sleep(500);
            fontSize = commonUtils.getCSSValue(errorMsg, "font-size");
            isFontSize = commonUtils.assertValue(fontSize, "12px", "error Msg font size of " + type + " is not as per spec");
            paddingTop = commonUtils.getCSSValue(infoMsg, "padding-top");
            isPaddingTop = commonUtils.assertValue(paddingTop, "3px", "Error Msg padding top of " + type + " is not as per spec");
            color = commonUtils.getCSSValue(errorMsg, "color");
            isColor = commonUtils.assertCSSProperties("color", color, new String[]{commonUtils.hex2Rgb("#DB0020"), commonUtils.hex2RgbWithoutTransparency("#DB0020")});
            if (!isColor) {
                log.info("Font Color of error Msg of " + type + " is not as per spec, actual " + color);
            }
            Assert.assertTrue(isFontSize && isColor && isPaddingTop);
        }
        Assert.assertTrue(isLabelFontSize && isLabelColor && isLabelFor);
    }

    @DataProvider(name = "Fancy - Password Input - Show Button Test data")
    private Object[][] getFancyPasswordShowButtonTestData() {
        return new Object[][]{
                {"default", "password-input-fancy-showbutton", inputsPgObj.passwordFancyShowbuttonClass, new String[]{commonUtils.hex2Rgb("#047A9C"), commonUtils.hex2RgbWithoutTransparency("#047A9C")}, "-37px"},
                {"error", "password-input-fancy-error-showbutton", inputsPgObj.passwordFancyShowbuttonErrorClass, new String[]{commonUtils.hex2Rgb("#047A9C"), commonUtils.hex2RgbWithoutTransparency("#047A9C")}, "-37px"},
                {"disabled", "password-input-fancy-disabled-showbutton", inputsPgObj.passwordFancyShowbuttonDisabledClass, new String[]{commonUtils.hex2Rgb("#C7C7C7"), commonUtils.hex2RgbWithoutTransparency("#C7C7C7")}, "-35px"},
                {"readOnly", "password-input-fancy-readOnly-showbutton", inputsPgObj.passwordBasicShowButtonReadOnlyClass, new String[]{commonUtils.hex2Rgb("#047A9C"), commonUtils.hex2RgbWithoutTransparency("#047A9C")}, "-37px"},
        };
    }

    @Test(testName = "Fancy - Verify Password Input - Show Button Test", dataProvider = "Fancy - Password Input - Show Button Test data", groups = "desktop-regression")
    private void fancyPasswordInputShowBtnTest(String inputState, String id, By showbutton, String[] expShowBtnColor, String expMarginTop) throws Exception {
        String[] detailsPropertiesList = new String[]{"elementId", "password-input-target", "componentName", "TextInput"};
        String[] propsPropertiesList = new String[]{"password", "true", "fancy", "true", "id", id, "labelText", "Password", "inputState", inputState, "placeholder", "Password", "changeHandler", "function () {}", "showText", "show", "hideText", "hide", "isNotVisibleMsg", "Password is hidden", "isVisibleMsg", "Password is visible", "infoMessage", "This is an info message", "errorMessage", "This is an error message'"};
        setConfigAndLaunch(detailsPropertiesList, propsPropertiesList);

        Thread.sleep(500);
        showBtnColor = commonUtils.getCSSValue(showbutton, "color");
        marginTop = commonUtils.getCSSValue(showbutton, "margin-top");
        showBtnFloat = commonUtils.getCSSValue(showbutton, "float");

        isShowBtnColor = commonUtils.assertCSSProperties("color", showBtnColor, expShowBtnColor);
        if (!isShowBtnColor) {
            log.info("Show Button color  of " + id + " is not as per spec,actual " + showBtnColor);
        }
        isMarginTop = commonUtils.assertValue(marginTop, expMarginTop, "The top margin value  of " + id + "  show btn is not as per specs");
        isShowBtnFloat = commonUtils.assertValue(showBtnFloat, "right", "The show btn  of " + id + " is not on aligned on the right side");
        for (String cssProperty : paddings) {
            String cssPropertyType = cssProperty;
            cssProperty = commonUtils.getCSSValue(showbutton, cssProperty);
            isCSSProperty = commonUtils.assertValue(cssProperty, "2px", cssPropertyType + " of " + id + " is not as per spec");
            Assert.assertTrue(isCSSProperty);
        }
        Assert.assertTrue(isShowBtnColor && isMarginTop && isShowBtnFloat);
    }

    @DataProvider(name = "Fancy - Password Input - Show Button Focus Test data")
    private Object[][] getFancyPasswordShowButtonFocusTestData() {
        return new Object[][]{
                {"default", "password-input-fancy-showbutton", "showbutton-password-input-fancy-showbutton", inputsPgObj.passwordFancyShowbuttonClass},
                {"error", "password-input-fancy-error-showbutton", "showbutton-password-input-fancy-error-showbutton", inputsPgObj.passwordFancyShowbuttonErrorClass},
                {"disabled", "password-input-fancy-disabled-showbutton", "showbutton-password-input-fancy-disabled-showbutton", inputsPgObj.passwordFancyShowbuttonDisabledClass},
                {"readOnly", "password-input-fancy-readOnly-showbutton", "showbutton-password-input-fancy-readOnly-showbutton", inputsPgObj.passwordFancyShowbuttonClass},
        };
    }

    @Test(testName = "Fancy - Verify Password Input - Show Button Focus Test", dataProvider = "Fancy - Password Input - Show Button Focus Test data", groups = "desktop-regression")
    private void fancyPasswordInputFancyShowBtnFocusTest(String inputState, String id, String showButtonId, By showbutton) throws Exception {
        if ((browser.equals("firefox")) || browser.equals("safari") || browser.equals("ie") || lBrowser.equals("firefox")) {
            throw new SkipException("Focus operation not yet supported in firefox/safari browser drivers");
        }
        String[] detailsPropertiesList = new String[]{"elementId", "password-input-target", "componentName", "TextInput"};
        String[] propsPropertiesList = new String[]{"password", "true", "fancy", "true", "id", id, "labelText", "Password", "inputState", inputState, "placeholder", "Password", "changeHandler", "function () {}", "showText", "show", "hideText", "hide", "isNotVisibleMsg", "Password is hidden", "isVisibleMsg", "Password is visible", "infoMessage", "This is an info message", "errorMessage", "This is an error message'"};
        setConfigAndLaunch(detailsPropertiesList, propsPropertiesList);

        commonUtils.focusOnElementById(showButtonId);
        Thread.sleep(2000);
        textDecoration = commonUtils.getCSSValue(showbutton, "text-decoration-line");
        if (inputState.equals("disabled")) {
            isTextDecoration = commonUtils.assertValue(textDecoration, "none", "text-decoration for Show Password Button for " + id + " is not as per the spec");
        } else {
            isTextDecoration = commonUtils.assertValue(textDecoration, "underline", "text-decoration for Show Password Button for " + id + " is not as per the spec");
        }
        Assert.assertTrue(isTextDecoration);
    }

    @DataProvider(name = "Fancy - Password Input - Input Box Test Data")
    private Object[][] getFancyPasswordInputBoxTestData() {
        return new Object[][]{
                {"default", "password-input-fancy", inputsPgObj.passwordFancyField, "10px", "8px", "1px", new String[]{commonUtils.hex2Rgb("#6A7070"), commonUtils.hex2RgbWithoutTransparency("#6A7070")}, "solid"},
                {"error", "password-input-fancy-error", inputsPgObj.passwordFancyErrorField, "10px", "8px", "1px", new String[]{commonUtils.hex2Rgb("#DB0020"), commonUtils.hex2RgbWithoutTransparency("#DB0020")}, "solid"},
                {"disabled", "password-input-fancy-disabled", inputsPgObj.passwordFancyDisabledField, "10px", "8px", "4px", new String[]{commonUtils.hex2Rgb("#C7C7C7"), commonUtils.hex2RgbWithoutTransparency("#C7C7C7")}, "solid"},
                {"readOnly", "password-input-fancy-readOnly", inputsPgObj.passwordFancyReadOnlyField, "10px", "8px", "0px", new String[]{""}, "none"},
        };
    }

    @Test(testName = "Fancy - Verify Password Input - Input Box Test", dataProvider = "Fancy - Password Input - Input Box Test Data", groups = "desktop-regression")
    private void fancyPasswordInputBoxTest(String inputState, String id, By elem, String expPaddingBottom, String expPaddingTop, String expBorderBottom, String[] expBorderBtmColor, String expBorderBtmStyle) throws Exception {
        String[] detailsPropertiesList = new String[]{"elementId", "password-input-target", "componentName", "TextInput"};
        String[] propsPropertiesList = new String[]{"password", "true", "fancy", "true", "id", id, "labelText", "Password", "inputState", inputState, "placeholder", "Password", "changeHandler", "function () {}", "showText", "show", "hideText", "hide", "isNotVisibleMsg", "Password is hidden", "isVisibleMsg", "Password is visible", "infoMessage", "This is an info message", "errorMessage", "This is an error message'"};
        setConfigAndLaunch(detailsPropertiesList, propsPropertiesList);

        paddingBottom = commonUtils.getCSSValue(elem, "padding-bottom");
        paddingTop = commonUtils.getCSSValue(elem, "padding-top");
        isPaddingBottom = commonUtils.assertValue(paddingBottom, expPaddingBottom, "The padding-bottom of " + id + " is not as per spec");
        isPaddingTop = commonUtils.assertValue(paddingTop, expPaddingTop, "The padding-top of " + id + " is not as per spec");

        borderBottom = commonUtils.getCSSValue(elem, "border-bottom-width");
        borderBottomColor = commonUtils.getCSSValue(elem, "border-bottom-color");
        borderBottomStyle = commonUtils.getCSSValue(elem, "border-bottom-style");
        isBorderBottom = commonUtils.assertValue(borderBottom, expBorderBottom, "The bottom border width of " + id + "  is not as per spec");
        if (!id.equals("password-input-fancy-readOnly")) {
            isBorderBottomColor = commonUtils.assertCSSProperties("border-bottom-color", borderBottomColor, expBorderBtmColor);
            if (!isBorderBottomColor) {
                log.info("Bottom border color of " + id + " is not as per spec,actual " + borderBottomColor);
            }
        }
        isBorderBottomStyle = commonUtils.assertValue(borderBottomStyle, expBorderBtmStyle, "The bottom border style of " + id + " is not as per spec");
        Assert.assertTrue(isPaddingBottom && isPaddingTop && isBorderBottom && isBorderBottomColor && isBorderBottomStyle);
    }

    @DataProvider(name = "Fancy - Password Input - Underline Test Data")
    private Object[][] getFancyPasswordInputBoxUnderlineTestData() {
        return new Object[][]{
                {"default", "password-input-fancy", inputsPgObj.passwordFancyField, "password-input-fancy-underline", inputsPgObj.textInputUnderLineClass, inputsPgObj.passwordFancyUnderLine, "4px", new String[]{commonUtils.hex2Rgb("#047A9C"), commonUtils.hex2RgbWithoutTransparency("#047A9C")}},
                {"error", "password-input-fancy-error", inputsPgObj.passwordFancyErrorField, "password-input-fancy-error-underline", inputsPgObj.textInputUnderLineErrorClass, inputsPgObj.passwordFancyErrorUnderLine, "4px", new String[]{commonUtils.hex2Rgb("#DB0020"), commonUtils.hex2RgbWithoutTransparency("#DB0020")}}
        };
    }

    @Test(testName = "Fancy - Verify Password Input - Underline", dataProvider = "Fancy - Password Input - Underline Test Data", groups = "desktop-regression")
    private void fancyPasswordInputUnderlineTest(String inputState, String id, By elem, String underlineElemId, By underlineElementClass, By underlineElement, String expUnderlineHeight, String[] expUnderlineColor) throws Exception {
        String[] detailsPropertiesList = new String[]{"elementId", "password-input-target", "componentName", "TextInput"};
        String[] propsPropertiesList = new String[]{"password", "true", "fancy", "true", "id", id, "labelText", "Password", "inputState", inputState, "placeholder", "Password", "changeHandler", "function () {}", "showText", "show", "hideText", "hide", "isNotVisibleMsg", "Password is hidden", "isVisibleMsg", "Password is visible", "infoMessage", "This is an info message", "errorMessage", "This is an error message'"};
        setConfigAndLaunch(detailsPropertiesList, propsPropertiesList);

        commonUtils.click(elem);
        js = (JavascriptExecutor) driver;
        webElement = driver.findElement(underlineElementClass);
        js.executeScript("arguments[0].setAttribute('id','" + underlineElemId + "')", webElement);

        lineHeight = commonUtils.getCSSValue(underlineElement, "height");
        color = commonUtils.getCSSValue(underlineElement, "background-color");

        isHeight = commonUtils.assertValue(lineHeight, expUnderlineHeight, "The underline height of " + id + " is not as per spec");
        isColor = commonUtils.assertCSSProperties("background-color", color, expUnderlineColor);
        if (!isColor) {
            log.info("Underline color  of " + id + " is not as per spec,actual " + color + " at width " + width);
        }
        Assert.assertTrue(isHeight && isColor);
    }

    //Basic - Password Input
    @DataProvider(name = "Basic - Password Input - Label,and Msg Test Data")
    private Object[][] getBasicPasswordInputOtherFieldsTestData() {
        return new Object[][]{
                {"default", "password-basic", "password-input-basic", inputsPgObj.passwordBasicField, inputsPgObj.textLabelInputClass, "password-input-basic-label", inputsPgObj.passwordBasicLabel, "password-input-basic-info", inputsPgObj.passwordBasicInfoMsg, "password-input-basic-errorMsg", inputsPgObj.passwordBasicErrorMsg, "12px", new String[]{commonUtils.hex2Rgb("#6A7070"), commonUtils.hex2RgbWithoutTransparency("#6A7070")}},
                {"error", "password-basic-error", "password-input-basic-error", inputsPgObj.passwordBasicErrorField, inputsPgObj.textLabelInputErrorClass, "password-input-basic-error-label", inputsPgObj.passwordBasicErrorLabel, "password-input-basic-error-info", inputsPgObj.passwordBasicErrorInfoMsg, "password-input-basic-error-errorMsg", inputsPgObj.passwordBasicErrorErrorMsg, "12px", new String[]{commonUtils.hex2Rgb("#DB0020"), commonUtils.hex2RgbWithoutTransparency("#DB0020")}},
                {"disabled", "password-basic-disabled", "password-input-basic-disabled", inputsPgObj.passwordBasicDisabledField, inputsPgObj.textLabelInputDisabledClass, "password-input-basic-disabled-label", inputsPgObj.passwordBasicDisabledLabel, "password-input-basic-disabled-info", inputsPgObj.passwordBasicDisabledrInfoMsg, "password-input-basic-disabled-errorMsg", inputsPgObj.passwordBasicDisabledErrorMsg, "12px", new String[]{commonUtils.hex2Rgb("#6A7070"), commonUtils.hex2RgbWithoutTransparency("#6A7070")}},
                {"readOnly", "password-basic-readOnly", "password-input-basic-readOnly", inputsPgObj.passwordBasicReadOnlyField, inputsPgObj.textLabelInputClass, "password-input-basic-readOnly-label", inputsPgObj.passwordBasicReadOnlyLabel, "password-input-basic-readOnly-info", inputsPgObj.passwordBasicReadOnlyInfoMsg, "password-input-basic-readOnly-errorMsg", inputsPgObj.passwordBasicReadOnlyErrorMsg, "12px", new String[]{commonUtils.hex2Rgb("#6A7070"), commonUtils.hex2RgbWithoutTransparency("#6A7070")}},
        };
    }

    @Test(testName = "Basic - Verify Password Input - Label,and Msg", dataProvider = "Basic - Password Input - Label,and Msg Test Data", groups = "desktop-regression")
    private void basicPasswordInputOtherFieldsShowTest(String inputState, String type, String elemId, By elem, By labelClass, String labelId, By label, String infoMsgId, By infoMsg, String errorMsgId, By errorMsg, String expLabelFontSize, String[] expLabelFontColor) throws Exception {
        String[] detailsPropertiesList = new String[]{"elementId", "password-input-target", "componentName", "TextInput"};
        String[] propsPropertiesList = new String[]{"password", "true", "fancy", "false", "id", elemId, "labelText", "Password", "inputState", inputState, "placeholder", "Password", "changeHandler", "function () {}", "showText", "show", "hideText", "hide", "infoMessage", "This is an info message", "errorMessage", "This is an error message", "isNotVisibleMsg", "Password is hidden", "isVisibleMsg", "Password is visible'"};
        setConfigAndLaunch(detailsPropertiesList, propsPropertiesList);

        js = (JavascriptExecutor) driver;
        webElement = driver.findElement(labelClass);
        js.executeScript("arguments[0].setAttribute('id','" + labelId + "')", webElement);

        webElement = driver.findElement(inputsPgObj.textInputInfoMessageClass);
        js.executeScript("arguments[0].setAttribute('id','" + infoMsgId + "')", webElement);

        labelFontSize = commonUtils.getCSSValue(label, "font-size");
        labelColor = commonUtils.getCSSValue(label, "color");
        isLabelFontSize = commonUtils.assertValue(labelFontSize, expLabelFontSize, "The font size of " + type + " Label is not as per spec");
        isLabelColor = commonUtils.assertCSSProperties("color", labelColor, expLabelFontColor);
        if (!isLabelColor) {
            log.info("label color of " + type + " is not as per spec,actual" + labelColor);
        }
        isLabelFor = commonUtils.checkLabelForVal(label, elem);
        if (!isLabelFor) {
            log.info("the password label is not mapped correctly to the password field  of " + type);
        }

        fontSize = commonUtils.getCSSValue(infoMsg, "font-size");
        isFontSize = commonUtils.assertValue(fontSize, "12px", "Info Msg font size of " + type + " is not as per spec");
        color = commonUtils.getCSSValue(infoMsg, "color");
        isColor = commonUtils.assertCSSProperties("color", color, new String[]{commonUtils.hex2Rgb("#6A7070"), commonUtils.hex2RgbWithoutTransparency("#6A7070")});
        if (!isColor) {
            log.info("Font Color of Info Msg of " + type + " is not as per spec, actual " + color);
        }
        Assert.assertTrue(isFontSize && isColor);

        if (inputState.equals("error")) {
            webElement = driver.findElement(inputsPgObj.textInputErrorMessageClass);
            js.executeScript("arguments[0].setAttribute('id','" + errorMsgId + "')", webElement);
            Thread.sleep(500);
            fontSize = commonUtils.getCSSValue(errorMsg, "font-size");
            isFontSize = commonUtils.assertValue(fontSize, "12px", "error Msg font size of " + type + " is not as per spec");
            color = commonUtils.getCSSValue(errorMsg, "color");
            isColor = commonUtils.assertCSSProperties("color", color, new String[]{commonUtils.hex2Rgb("#DB0020"), commonUtils.hex2RgbWithoutTransparency("#DB0020")});
            if (!isColor) {
                log.info("Font Color of error Msg of " + type + " is not as per spec, actual " + color);
            }
            Assert.assertTrue(isFontSize && isColor);
        }

        Assert.assertTrue(isLabelFontSize && isLabelColor && isLabelFor);
    }

    @DataProvider(name = "Basic - Password Input - Show Button Test data")
    private Object[][] getBasicPasswordInputShowButtonTestData() {
        return new Object[][]{
                {"default", "password-input-basic-showbutton", inputsPgObj.passwordBasicShowbuttonClass, new String[]{commonUtils.hex2Rgb("#047A9C"), commonUtils.hex2RgbWithoutTransparency("#047A9C")}, "-28px", "14px"},
                {"error", "password-input-basic-error-showbutton", inputsPgObj.passwordBasicShowbuttonClass, new String[]{commonUtils.hex2Rgb("#047A9C"), commonUtils.hex2RgbWithoutTransparency("#047A9C")}, "-28px", "14px"},
                {"disabled", "password-input-basic-disabled-showbutton", inputsPgObj.passwordBasicShowbuttonDisabledClass, new String[]{commonUtils.hex2Rgb("#C7C7C7"), commonUtils.hex2RgbWithoutTransparency("#C7C7C7")}, "-28px", "14px"},
                {"readOnly", "password-input-basic-readOnly-showbutton", inputsPgObj.passwordBasicShowButtonReadOnlyClass, new String[]{commonUtils.hex2Rgb("#047A9C"), commonUtils.hex2RgbWithoutTransparency("#047A9C")}, "-37px", "0px"},
        };
    }

    @Test(testName = "Basic - Verify Password Input - Show Button", dataProvider = "Basic - Password Input - Show Button Test data", groups = "desktop-regression")
    private void basicPasswordInputShowBtnTest(String inputState, String id, By showbutton, String[] expShowBtnColor, String expMarginTop, String expMarginRight) throws Exception {
        String[] detailsPropertiesList = new String[]{"elementId", "password-input-target", "componentName", "TextInput"};
        String[] propsPropertiesList = new String[]{"password", "true", "fancy", "false", "id", id, "labelText", "Password", "inputState", inputState, "placeholder", "Password", "changeHandler", "function () {}", "showText", "show", "hideText", "hide", "infoMessage", "This is an info message", "errorMessage", "This is an error message", "isNotVisibleMsg", "Password is hidden", "isVisibleMsg", "Password is visible'"};
        setConfigAndLaunch(detailsPropertiesList, propsPropertiesList);

        Thread.sleep(500);
        showBtnColor = commonUtils.getCSSValue(showbutton, "color");
        marginTop = commonUtils.getCSSValue(showbutton, "margin-top");
        marginRight = commonUtils.getCSSValue(showbutton, "margin-right");
        showBtnFloat = commonUtils.getCSSValue(showbutton, "float");

        isShowBtnColor = commonUtils.assertCSSProperties("color", showBtnColor, expShowBtnColor);
        if (!isShowBtnColor) {
            log.info("Show Button color  of " + id + " is not as per spec,actual " + showBtnColor);
        }
        isMarginTop = commonUtils.assertValue(marginTop, expMarginTop, "The top margin value  of " + id + "  show btn is not as per specs");
        isMarginRight = commonUtils.assertValue(marginRight, expMarginRight, "The margin-right value  of " + id + "  show btn is not as per specs");
        isShowBtnFloat = commonUtils.assertValue(showBtnFloat, "right", "The show btn  of " + id + " is not on aligned on the right side");
        for (String cssProperty : paddings) {
            String cssPropertyType = cssProperty;
            cssProperty = commonUtils.getCSSValue(showbutton, cssProperty);
            isCSSProperty = commonUtils.assertValue(cssProperty, "2px", "'" + cssPropertyType + "' of " + id + " is not as per spec");
            Assert.assertTrue(isCSSProperty);
        }
        Assert.assertTrue(isShowBtnColor && isMarginTop && isMarginRight && isShowBtnFloat);
    }

    @DataProvider(name = "Basic - Password Input - Show Button Focus Test data")
    private Object[][] getPasswordBasicShowButtonFocusTestData() {
        return new Object[][]{
                {"default", "password-input-basic-showbutton", "showbutton-password-input-basic-showbutton", inputsPgObj.passwordBasicShowbuttonClass},
                {"error", "password-input-basic-error-showbutton", "showbutton-password-input-basic-error-showbutton", inputsPgObj.passwordBasicShowbuttonClass},
                //{"error", "password-input-basic-error-showbutton", "showbutton-password-input-basic-error-showbutton", inputsPgObj.passwordBasicShowbuttonErrorClass},
                {"disabled", "password-input-basic-disabled-showbutton", "showbutton-password-input-basic-disabled-showbutton", inputsPgObj.passwordBasicShowbuttonDisabledClass},
                {"readOnly", "password-input-basic-readOnly-showbutton", "showbutton-password-input-basic-readOnly-showbutton", inputsPgObj.passwordBasicShowButtonReadOnlyClass},
        };
    }

    @Test(testName = "Basic - Verify Password Input - Show Button Focus Test", dataProvider = "Basic - Password Input - Show Button Focus Test data", groups = "desktop-regression")
    private void basicPasswordInputShowBtnFocusTest(String inputState, String id, String showButtonId, By showbutton) throws Exception {
        if ((browser.equals("firefox")) || browser.equals("safari") || browser.equals("ie") || lBrowser.equals("firefox")) {
            throw new SkipException("Focus operation not yet supported in firefox/safari browser drivers");
        }
        String[] detailsPropertiesList = new String[]{"elementId", "password-input-target", "componentName", "TextInput"};
        String[] propsPropertiesList = new String[]{"password", "true", "fancy", "false", "id", id, "labelText", "Password", "inputState", inputState, "placeholder", "Password", "changeHandler", "function () {}", "showText", "show", "hideText", "hide", "infoMessage", "This is an info message", "errorMessage", "This is an error message", "isNotVisibleMsg", "Password is hidden", "isVisibleMsg", "Password is visible'"};
        setConfigAndLaunch(detailsPropertiesList, propsPropertiesList);

        commonUtils.focusOnElementById(showButtonId);
        Thread.sleep(1000);
        textDecoration = commonUtils.getCSSValue(showbutton, "text-decoration-line");
        if (inputState.equals("disabled")) {
            isTextDecoration = commonUtils.assertValue(textDecoration, "none", "text-decoration for Show Password Button for " + id + " is not as per the spec");
        } else {
            isTextDecoration = commonUtils.assertValue(textDecoration, "underline", "text-decoration for Show Password Button for " + id + " is not as per the spec");
        }
        Assert.assertTrue(isTextDecoration);
    }

    @DataProvider(name = "Basic - Password Input - Input Box Test Data")
    private Object[][] getBasicPasswordInputBoxTestData() {
        return new Object[][]{
                {"default", "password-input-basic", inputsPgObj.passwordBasicField, "14px", new String[]{"36px", "34px"}, "14px", new String[]{commonUtils.hex2Rgb("#FFFFFF"), commonUtils.hex2RgbWithoutTransparency("#FFFFFF")}, "6px"},
                {"error", "password-input-basic-error", inputsPgObj.passwordBasicErrorField, "14px", new String[]{"36px", "34px"}, "14px", new String[]{commonUtils.hex2Rgb("#FFFFFF"), commonUtils.hex2RgbWithoutTransparency("#FFFFFF")}, "6px"},
                {"disabled", "password-input-basic-disabled", inputsPgObj.passwordBasicDisabledField, "14px", new String[]{"36px", "34px"}, "14px", new String[]{commonUtils.hex2Rgb("#E9E9E9"), commonUtils.hex2RgbWithoutTransparency("#E9E9E9")}, "6px"},
        };
    }

    @Test(testName = "Basic - Verify Password Input - Input Box", dataProvider = "Basic - Password Input - Input Box Test Data", groups = "desktop-regression")
    private void basicPasswordInputBoxTest(String inputState, String id, By elem, String expPaddingLeftRight, String[] expHeight, String expFontSize, String[] expBgColor, String expMarginTop) throws Exception {
        String[] detailsPropertiesList = new String[]{"elementId", "password-input-target", "componentName", "TextInput"};
        String[] propsPropertiesList = new String[]{"password", "true", "fancy", "false", "id", id, "labelText", "Password", "inputState", inputState, "placeholder", "Password", "changeHandler", "function () {}", "showText", "show", "hideText", "hide", "infoMessage", "This is an info message", "errorMessage", "This is an error message", "isNotVisibleMsg", "Password is hidden", "isVisibleMsg", "Password is visible'"};
        setConfigAndLaunch(detailsPropertiesList, propsPropertiesList);

        paddingLeft = commonUtils.getCSSValue(elem, "padding-left");
        paddingRight = commonUtils.getCSSValue(elem, "padding-right");
        height = commonUtils.getCSSValue(elem, "height");
        fontSize = commonUtils.getCSSValue(elem, "font-size");
        bgColor = commonUtils.getCSSValue(elem, "background-color");
        marginTop = commonUtils.getCSSValue(elem, "margin-top");

        isPaddingLeft = commonUtils.assertValue(paddingLeft, expPaddingLeftRight, "The padding-left of " + id + " is not as per spec");
        isPaddingRight = commonUtils.assertValue(paddingRight, expPaddingLeftRight, "The padding-right of " + id + " is not as per spec");
        isHeight = commonUtils.assertCSSProperties("height", height, expHeight);
        if (!isHeight) {
            log.info("The height of " + id + " is not as per spec, actual " + height);
        }
        isFontSize = commonUtils.assertValue(fontSize, expFontSize, "The font-size of " + id + " is not as per spec");
        isBgColor = commonUtils.assertCSSProperties("color", bgColor, expBgColor);
        if (!isBgColor) {
            log.info("BackGround Color of " + id + " is not as per spec, actual " + bgColor);
        }
        isMarginTop = commonUtils.assertValue(marginTop, expMarginTop, "The margin-top of " + id + " is not as per spec");

        Assert.assertTrue(isPaddingLeft && isPaddingRight && isHeight && isFontSize && isBgColor && isMarginTop);
    }

    //Multiline Text
    @DataProvider(name = "MultiLine Input Active Test Data")
    public Object[][] getMultiLineInputData() {
        return new Object[][]{
                {inputsPgObj.multiLineTextInput, new String[]{"14px"}, new String[]{"18px"}, new String[]{"6px"}, new String[]{commonUtils.hex2Rgb("#252525"), commonUtils.hex2RgbWithoutTransparency("#252525")}, new String[]{commonUtils.hex2Rgb("#FFFFFF"), commonUtils.hex2RgbWithoutTransparency("#FFFFFF")}, new String[]{"9px", "9px", "14px", "14px"}, new String[]{"1px", "1px", "1px", "1px"}, new String[]{"solid", "solid", "solid", "solid"}, new String[]{commonUtils.hex2Rgb("#C7C7C7"), commonUtils.hex2RgbWithoutTransparency("#C7C7C7")}}
        };
    }

    @Test(testName = "MultiLine Input - Active States Test", dataProvider = "MultiLine Input Active Test Data", groups = {"desktop-ci", "desktop-regression"})
    private void multiLineInputBoxTest(By element, String[] expFontSize, String[] expLineHeight, String[] expMarginTop, String[] expColor, String[] expBackgroundColor, String[] expPadding, String[] expBorderWidth, String[] expBorderStyle, String[] expBorderColor) throws Exception {
        String[] detailsPropertiesList = new String[]{"elementId", "multilinetext-target", "componentName", "MultiLineText"};
        String[] propsPropertiesList = new String[]{"id", "multiLine-text-input", "labelText", "Multi-line label", "inputState", "default", "placeholder", "Multilinetext placeholder", "changeHandler", "function () {}", "infoMessage", "This is an info message", "errorMessage", "This is an error message'"};
        setConfigAndLaunch(detailsPropertiesList, propsPropertiesList);

        fontSize = commonUtils.getCSSValue(element, "font-size");
        lineHeight = commonUtils.getCSSValue(element, "line-height");
        marginTop = commonUtils.getCSSValue(element, "margin-top");
        color = commonUtils.getCSSValue(element, "color");
        backgroundColor = commonUtils.getCSSValue(element, "background-color");

        isFontSize = commonUtils.assertCSSProperties("font-size", fontSize, expFontSize);
        isLineHeight = commonUtils.assertCSSProperties("line-height", lineHeight, expLineHeight);
        isMarginTop = commonUtils.assertCSSProperties("margin-top", marginTop, expMarginTop);
        isColor = commonUtils.assertCSSProperties("color", color, expColor);
        isBackgroundColor = commonUtils.assertCSSProperties("bg-color", backgroundColor, expBackgroundColor);

        if (!isFontSize) {
            log.info("font size for MultiLine Input is not as per the spec, actual: " + fontSize);
        }
        if (!isLineHeight) {
            log.info("Line Height for MultiLine Input is not as per the spec, actual: " + lineHeight);
        }
        if (!isMarginTop) {
            log.info("margin-top for MultiLine Input is not as per the spec, actual: " + marginTop);
        }
        if (!isColor) {
            log.info("color for for MultiLine Input is not as per the spec, actual: " + color);
        }
        if (!isBackgroundColor) {
            log.info("background-color for for MultiLine Input is not as per the spec, actual: " + backgroundColor);
        }
        Assert.assertTrue(isFontSize && isLineHeight && isMarginTop && isColor && isBackgroundColor);

        for (int i = 0; i < paddings.length; i++) {
            padding = commonUtils.getCSSValue(element, paddings[i]);
            isPadding = commonUtils.assertValue(padding, expPadding[i], paddings[i] + " for MultiLine Text is not as per the spec");
            Assert.assertTrue(isPadding);
        }

        for (int i = 0; i < borderStyles.length; i++) {
            borderStyle = commonUtils.getCSSValue(element, borderStyles[i]);
            isBorderStyle = commonUtils.assertValue(borderStyle, expBorderStyle[i], borderStyles[i] + " for MultiLine Text is not as per the spec");
            Assert.assertTrue(isBorderStyle);
        }

        for (int i = 0; i < borderWidths.length; i++) {
            borderWidth = commonUtils.getCSSValue(element, borderWidths[i]);
            isBorderWidth = commonUtils.assertValue(borderWidth, expBorderWidth[i], borderWidths[i] + " for MultiLine Text is not as per the spec");
            Assert.assertTrue(isBorderWidth);
        }

        for (int i = 0; i < borderRadii.length; i++) {
            borderRadius = commonUtils.getCSSValue(element, borderRadii[i]);
            isBorderRadius = commonUtils.assertValue(borderRadius, "3px", borderRadii[i] + " for MultiLine Text is not as per the spec");
            Assert.assertTrue(isBorderRadius);
        }

        for (String cssProperty : borderColors) {
            borderColor = commonUtils.getCSSValue(element, cssProperty);
            isBorderColor = commonUtils.assertCSSProperties(cssProperty, borderColor, expBorderColor);
            if (!isBorderColor) {
                log.info("Border color " + cssProperty + " for MultiLine Text is not as per spec, actual " + borderColor);
            }
            Assert.assertTrue(isBorderColor);
        }
    }

    @DataProvider(name = "MultiLine Input Error Test Data")
    public Object[][] getMultiLineInputErrorData() {
        return new Object[][]{
                {inputsPgObj.multiLineTextErrorInput, new String[]{commonUtils.hex2Rgb("#FFFFFF"), commonUtils.hex2RgbWithoutTransparency("#FFFFFF")}, new String[]{commonUtils.hex2Rgb("#DB0020"), commonUtils.hex2RgbWithoutTransparency("#DB0020")}}
        };
    }

    @Test(testName = "MultiLine Input - Error States Test", dataProvider = "MultiLine Input Error Test Data", groups = {"desktop-regression"})
    private void multiLineInputBoxErrorTest(By element, String[] expBackgroundColor, String[] expBorderColor) throws Exception {
        String[] detailsPropertiesList = new String[]{"elementId", "multilinetext-target", "componentName", "MultiLineText"};
        String[] propsPropertiesList = new String[]{"id", "multiLine-text-input-error", "labelText", "Multi-line label", "inputState", "error", "placeholder", "Multilinetext placeholder", "changeHandler", "function () {}", "infoMessage", "This is an info message", "errorMessage", "This is an error message'"};
        setConfigAndLaunch(detailsPropertiesList, propsPropertiesList);

        backgroundColor = commonUtils.getCSSValue(element, "background-color");
        isBackgroundColor = commonUtils.assertCSSProperties("background-color", backgroundColor, expBackgroundColor);
        if (!isBackgroundColor) {
            log.info("background-color for for MultiLine Input Error is not as per the spec, actual: " + backgroundColor);
        }
        Assert.assertTrue(isBackgroundColor);

        for (String cssProperty : borderColors) {
            borderColor = commonUtils.getCSSValue(element, cssProperty);
            isBorderColor = commonUtils.assertCSSProperties(cssProperty, borderColor, expBorderColor);
            if (!isBorderColor) {
                log.info("Border color " + cssProperty + " for MultiLine Input Error is not as per spec, actual " + borderColor);
            }
            Assert.assertTrue(isBorderColor);
        }
    }

    @DataProvider(name = "MultiLine Input Disabled Test Data")
    public Object[][] getMultiLineInputDisabledData() {
        return new Object[][]{
                {inputsPgObj.multiLineTextDisabledInput, new String[]{commonUtils.hex2Rgb("#E9E9E9"), commonUtils.hex2RgbWithoutTransparency("#E9E9E9"), commonUtils.hex2Rgb("#c7c7c7"), commonUtils.hex2RgbWithoutTransparency("#c7c7c7")}, new String[]{commonUtils.hex2Rgb("#E9E9E9"), commonUtils.hex2RgbWithoutTransparency("#E9E9E9")}, new String[]{commonUtils.hex2Rgb("#C7C7C7"), commonUtils.hex2RgbWithoutTransparency("#C7C7C7")}, new String[]{"none"}}};
    }

    @Test(testName = "MultiLine Input - Disabled States Test", dataProvider = "MultiLine Input Disabled Test Data", groups = {"desktop-regression"})
    private void multiLineInputBoxDisabledTest(By element, String[] expColor, String[] expBackgroundColor, String[] expBorderColor, String[] expOutlineStyle) throws Exception {
        String[] detailsPropertiesList = new String[]{"elementId", "multilinetext-target", "componentName", "MultiLineText"};
        String[] propsPropertiesList = new String[]{"id", "multiLine-text-input-disabled", "labelText", "Multi-line label", "inputState", "disabled", "placeholder", "Multilinetext placeholder", "changeHandler", "function () {}", "infoMessage", "This is an info message", "errorMessage", "This is an error message'"};
        setConfigAndLaunch(detailsPropertiesList, propsPropertiesList);

        color = commonUtils.getCSSValue(element, "color");
        backgroundColor = commonUtils.getCSSValue(element, "background-color");
        outlineStyle = commonUtils.getCSSValue(element, "outline-style");

        isColor = commonUtils.assertCSSProperties("color", color, expColor);
        isBackgroundColor = commonUtils.assertCSSProperties("background-color", backgroundColor, expBackgroundColor);
        isOutlineStyle = commonUtils.assertCSSProperties("outline-style", outlineStyle, expOutlineStyle);
        if (!isColor) {
            log.info("color for MultiLine Input Error is not as per the spec, actual: " + color);
        }
        if (!isBackgroundColor) {
            log.info("background-color for MultiLine Input Error is not as per the spec, actual: " + backgroundColor);
        }
        if (!isOutlineStyle) {
            log.info("outline-style for MultiLine Input Error is not as per the spec, actual: " + outlineStyle);
        }
        Assert.assertTrue(isColor && isBackgroundColor && isOutlineStyle);

        for (String cssProperty : borderColors) {
            borderColor = commonUtils.getCSSValue(element, cssProperty);
            isBorderColor = commonUtils.assertCSSProperties(cssProperty, borderColor, expBorderColor);
            if (!isBorderColor) {
                log.info("Border color " + cssProperty + " for MultiLine Input Disabled is not as per spec, actual " + borderColor);
            }
            Assert.assertTrue(isBorderColor);
        }
    }

    @DataProvider(name = "MultiLine Input ReadOnly Test Data")
    public Object[][] getMultiLineInputReadOnlyData() {
        return new Object[][]{
                {inputsPgObj.multiLineTextReadOnlyInput, new String[]{commonUtils.hex2Rgb("#FFFFFF"), commonUtils.hex2RgbWithoutTransparency("#FFFFFF")}, new String[]{"none", "none", "none", "none"}}
        };
    }

    @Test(testName = "MultiLine Input - ReadOnly States Test", dataProvider = "MultiLine Input ReadOnly Test Data", groups = "desktop-regression")
    private void multiLineInputBoxReadOnlyTest(By element, String[] expBackgroundColor, String[] expBorderStyle) throws Exception {
        String[] detailsPropertiesList = new String[]{"elementId", "multilinetext-target", "componentName", "MultiLineText"};
        String[] propsPropertiesList = new String[]{"id", "multiLine-text-input-readOnly", "labelText", "Multi-line label", "inputState", "readOnly", "placeholder", "Multilinetext placeholder", "changeHandler", "function () {}", "infoMessage", "This is an info message", "errorMessage", "This is an error message'"};
        setConfigAndLaunch(detailsPropertiesList, propsPropertiesList);

        backgroundColor = commonUtils.getCSSValue(element, "background-color");
        isBackgroundColor = commonUtils.assertCSSProperties("background-color", backgroundColor, expBackgroundColor);
        if (!isBackgroundColor) {
            log.info("background-color for for MultiLine Input ReadOnly is not as per the spec, actual: " + backgroundColor);
        }
        Assert.assertTrue(isBackgroundColor);

        for (int i = 0; i < borderStyles.length; i++) {
            borderStyle = commonUtils.getCSSValue(element, borderStyles[i]);
            isBorderStyle = commonUtils.assertValue(borderStyle, expBorderStyle[i], borderStyles[i] + " for MultiLine Text ReadOnly is not as per the spec");
            Assert.assertTrue(isBorderStyle);
        }
    }

    @DataProvider(name = "MultiLine Input Label Test Data")
    public Object[][] getMultiTextInputLabelData() {
        return new Object[][]{
                {"default", "multitext-Input-Label", inputsPgObj.textLabelInputClass, inputsPgObj.multiLineTextLabel, "12px", "16px", new String[]{commonUtils.hex2Rgb("#6A7070"), commonUtils.hex2RgbWithoutTransparency("#6A7070")}},
                {"error", "multitext-input-label-error", inputsPgObj.textLabelInputErrorClass, inputsPgObj.multiLineTextErrorLabel, "12px", "16px", new String[]{commonUtils.hex2Rgb("#DB0020"), commonUtils.hex2RgbWithoutTransparency("#DB0020")}},
                {"disabled", "multitext-input-label-disabled", inputsPgObj.textLabelInputClass, inputsPgObj.multiLineTextDisabledLabel, "12px", "16px", new String[]{commonUtils.hex2Rgb("#6A7070"), commonUtils.hex2RgbWithoutTransparency("#6A7070")}},
                {"readOnly", "multitext-input-label-readOnly", inputsPgObj.textLabelInputClass, inputsPgObj.multiLineTextReadOnlyLabel, "12px", "16px", new String[]{commonUtils.hex2Rgb("#6A7070"), commonUtils.hex2RgbWithoutTransparency("#6A7070")}},
        };
    }

    @Test(testName = "MultiLine Input Label Test", dataProvider = "MultiLine Input Label Test Data", groups = {"desktop-regression"})
    private void multiTextInputLabelTest(String inputState, String labelTextType, By labelClass, By label, String expLabelFontSize, String expLabelLineHt, String[] expLabelFontColor) throws Exception {
        String[] detailsPropertiesList = new String[]{"elementId", "multilinetext-target", "componentName", "MultiLineText"};
        String[] propsPropertiesList = new String[]{"id", "multiLine-text-input", "labelText", labelTextType, "inputState", inputState, "placeholder", "Multilinetext placeholder", "changeHandler", "function () {}", "infoMessage", "This is an info message", "errorMessage", "This is an error message'"};
        setConfigAndLaunch(detailsPropertiesList, propsPropertiesList);

        js = (JavascriptExecutor) driver;
        webElement = driver.findElement(labelClass);
        js.executeScript("arguments[0].setAttribute('id', '" + labelTextType + "')", webElement);

        labelFontSize = commonUtils.getCSSValue(label, "font-size");
        labelLineHeight = commonUtils.getCSSValue(label, "line-height");
        labelColor = commonUtils.getCSSValue(label, "color");

        isLabelFontSize = commonUtils.assertValue(labelFontSize, expLabelFontSize, "Font-size of " + labelTextType + " Label is not as per spec");
        islabelLineHeight = commonUtils.assertValue(labelLineHeight, expLabelLineHt, "Line-height of " + labelTextType + " Label is not as per spec");
        isLabelColor = commonUtils.assertCSSProperties("color", labelColor, expLabelFontColor);
        if (!isLabelColor) {
            log.info("Font-color of " + labelTextType + " Label is not as per spec, actual " + labelColor);
        }
        Assert.assertTrue(isLabelFontSize && islabelLineHeight && isLabelColor);
    }

    @DataProvider(name = "MultiLine Input (Focus) Test Data")
    public Object[][] getMultiTextInputFocusData() {
        return new Object[][]{
                {"default", "multiLine-text-input", inputsPgObj.multiLineTextInput, "1px", "solid", new String[]{commonUtils.hex2Rgb("#047A9C"), commonUtils.hex2RgbWithoutTransparency("#047A9C")}, new String[]{"rgb(4, 122, 156) 0px 0px 5px 0px", "0px 0px 5px 0px #047a9c"}},
                {"error", "multiLine-text-input-error", inputsPgObj.multiLineTextErrorInput, "1px", "solid", new String[]{commonUtils.hex2Rgb("#DB0020"), commonUtils.hex2RgbWithoutTransparency("#DB0020")}, new String[]{"rgb(219, 0, 32) 0px 0px 4px 0px", "0px 0px 4px 0px #DB0020", "0px 0px 4px 0px #db0020"}}
        };
    }

    @Test(testName = "MultiLine Input Text Data Focus", dataProvider = "MultiLine Input (Focus) Test Data", groups = {"desktop-regression"})
    private void multiTextInputFocusTest(String inputState, String type, By elem, String expBorderWidth, String expBorderStyle, String[] expBorderColor, String[] expBoxShadow) throws Exception {
        if (browser.equals("firefox") || browser.equals("safari") || browser.equals("ie") || lBrowser.equals("firefox")) {
            throw new SkipException("the focus operation is not supported on firefox/safari drivers");
        }

        String[] detailsPropertiesList = new String[]{"elementId", "multilinetext-target", "componentName", "MultiLineText"};
        String[] propsPropertiesList = new String[]{"id", type, "labelText", "Multi-line label", "inputState", inputState, "placeholder", "Multilinetext placeholder", "changeHandler", "function () {}", "infoMessage", "This is an info message", "errorMessage", "This is an error message'"};
        setConfigAndLaunch(detailsPropertiesList, propsPropertiesList);

        commonUtils.focusOnElementById(type);
        for (String cssProperty : borderWidths) {
            borderWidth = commonUtils.getCSSValue(elem, cssProperty);
            isBorderWidth = commonUtils.assertValue(borderWidth, expBorderWidth, "Border width " + cssProperty + " of " + type + " (Focus) field is not as per spec");
            Assert.assertTrue(isBorderWidth);
        }
        for (String cssProperty : borderStyles) {
            borderStyle = commonUtils.getCSSValue(elem, cssProperty);
            isBorderStyle = commonUtils.assertValue(borderStyle, expBorderStyle, "Border style " + cssProperty + " of " + type + " (Focus) field is not as per spec");
            Assert.assertTrue(isBorderStyle);
        }
        for (String cssProperty : borderColors) {
            borderColor = commonUtils.getCSSValue(elem, cssProperty);
            isBorderColor = commonUtils.assertCSSProperties(cssProperty, borderColor, expBorderColor);
            if (!isBorderColor) {
                log.info("Border color " + cssProperty + " of " + type + " (Focus) field is not as per spec, actual " + borderColor);
            }
            Assert.assertTrue(isBorderColor);
        }
        boxShadow = commonUtils.getCSSValue(elem, "box-shadow");
        isBoxShadow = commonUtils.assertCSSProperties("box-shadow", boxShadow, expBoxShadow);
        if (!isBoxShadow) {
            log.info("Box-shadow  of " + type + " (Focus) is not as per spec, actual " + boxShadow);
        }
        Assert.assertTrue(isBoxShadow);
    }

    //Fancy - Select Input
    @DataProvider(name = "Fancy Select Input All States Test Data")
    public Object[][] getFancySelectInputAllStatesTestData() {
        return new Object[][]{
                {"Fancy Select Input", "select-input-fancy", "pe-select-container--fancy", "select-input-fancy-div", "default", inputsPgObj.fancySelectInput, inputsPgObj.fancySelectInputContainer, new String[]{"36px", "34px"}, new String[]{"14px"}, new String[]{"18px", "20px", "17px", "19px"}, new String[]{"8px", "10px", "0px", "0px"}, new String[]{commonUtils.hex2Rgb("#252525"), commonUtils.hex2RgbWithoutTransparency("#252525")}, new String[]{"1px", "solid", "#6A7070"}, new String[]{"3px", "3px", "3px", "3px"}},
                {"Fancy Select Input Error", "select-input-fancy-error", "pe-select-container-fancy-error", "select-input-fancy-error-div", "error", inputsPgObj.fancySelectInputError, inputsPgObj.fancySelectInputErrorContainer, new String[]{"36px", "34px"}, new String[]{"14px"}, new String[]{"18px", "20px", "17px", "19px"}, new String[]{"8px", "10px", "0px", "0px"}, new String[]{commonUtils.hex2Rgb("#252525"), commonUtils.hex2RgbWithoutTransparency("#252525")}, new String[]{"1px", "solid", "#DB0020"}, new String[]{"3px", "3px", "3px", "3px"}},
                {"Fancy Select Input ReadOnly", "select-input-fancy-readOnly", "pe-select-container-fancy-readonly", "select-input-fancy-readOnly-div", "readOnly", inputsPgObj.fancySelectInputReadOnly, inputsPgObj.fancySelectInputReadOnlyContainer, new String[]{"36px", "34px"}, new String[]{"14px"}, new String[]{"18px", "20px", "17px", "19px"}, new String[]{"8px", "10px", "0px", "0px"}, new String[]{commonUtils.hex2Rgb("#252525"), commonUtils.hex2RgbWithoutTransparency("#252525")}, new String[]{"0px", "none", "#252525"}, new String[]{"3px", "3px", "3px", "3px"}},
                {"Fancy Select Input Disabled", "select-input-fancy-disabled", "pe-select-container-fancy-disabled", "select-input-fancy-disabled-div", "disabled", inputsPgObj.fancySelectInputDisabled, inputsPgObj.fancySelectInputDisabledContainer, new String[]{"36px", "34px"}, new String[]{"14px"}, new String[]{"18px", "20px", "17px", "19px"}, new String[]{"8px", "10px", "0px", "0px"}, new String[]{commonUtils.hex2Rgb("#252525"), commonUtils.hex2RgbWithoutTransparency("#252525")}, new String[]{"4px", "solid", "#C7C7C7"}, new String[]{"3px", "3px", "3px", "3px"}}
        };
    }

    @Test(testName = "Fancy Select Input Box All States Test", dataProvider = "Fancy Select Input All States Test Data", groups = {"desktop-ci", "desktop-regression"})
    private void fancySelectInputBoxAllStatesTest(String type, String id, String className, String divElement, String state, By element, By elementContainer, String[] expHeight, String[] expFontSize, String[] expLineHeight, String[] expPadding, String[] expColor, String[] expBorderBottoms, String[] expBorderRad) throws Exception {
        String[] detailsPropertiesList = new String[]{"elementId", "select-target", "componentName", "Select"};
        String[] propsPropertiesList = new String[]{"id", id, "labelText", "Select Label", "options", "['ipad', 'mac','iphone']", "fancy", "true", "inputState", state, "changeHandler", "function () {}", "infoMessage", "This is an info message", "errorMessage", "This is an error message'"};

        setConfigAndLaunch(detailsPropertiesList, propsPropertiesList);

        js = (JavascriptExecutor) driver;
        webElement = driver.findElement(By.className(className));
        js.executeScript("arguments[0].setAttribute('id','" + divElement + "')", webElement);

        height = commonUtils.getCSSValue(elementContainer, "height");
        fontSize = commonUtils.getCSSValue(elementContainer, "font-size");
        lineHeight = commonUtils.getCSSValue(elementContainer, "line-height");
        color = commonUtils.getCSSValue(elementContainer, "color");

        isHeight = commonUtils.assertCSSProperties("height", height, expHeight);
        isFontSize = commonUtils.assertCSSProperties("font-size", fontSize, expFontSize);
        isLineHeight = commonUtils.assertCSSProperties("line-height", lineHeight, expLineHeight);
        isColor = commonUtils.assertCSSProperties("color", color, expColor);
        if (!isHeight) {
            log.info("Height for " + type + " is not as per the spec, actual: " + height);
        }
        if (!isFontSize) {
            log.info("font size for " + type + " is not as per the spec, actual: " + fontSize);
        }
        if (!isLineHeight) {
            log.info("Line Height for " + type + " is not as per the spec, actual: " + lineHeight);
        }
        if (!isColor) {
            log.info("color for " + type + " is not as per the spec, actual: " + color);
        }
        Assert.assertTrue(isHeight && isFontSize && isLineHeight && isColor);

        for (int i = 0; i < paddings.length; i++) {
            padding = commonUtils.getCSSValue(element, paddings[i]);
            isPadding = commonUtils.assertValue(padding, expPadding[i], paddings[i] + " for " + type + " is not as per the spec");
            Assert.assertTrue(isPadding);
        }

        testBorders(type, borderBottom, borderBottoms, expBorderBottoms, element);
        int i = 0;
        for (i = 0; i < borderRadii.length; i++) {
            borderRadius = commonUtils.getCSSValue(elementContainer, borderRadii[i]);
            isBorderRadius = commonUtils.assertValue(borderRadius, expBorderRad[i], "Border radius " + borderRadii[i] + " of " + type + " field is not as per spec");
            Assert.assertTrue(isBorderRadius);
        }
    }

    @DataProvider(name = "Fancy Select Input Label Test Data")
    public Object[][] getFancySelectInputLabelTestData() {
        return new Object[][]{
                {"select-input-fancy", "default", inputsPgObj.fancySelectInput, compInputsPgObj.label, "12px", "16px", new String[]{commonUtils.hex2Rgb("#6A7070"), commonUtils.hex2RgbWithoutTransparency("#6A7070")}},
                {"select-input-fancy-error", "error", inputsPgObj.fancySelectInputError, compInputsPgObj.label, "12px", "16px", new String[]{commonUtils.hex2Rgb("#DB0020"), commonUtils.hex2RgbWithoutTransparency("#DB0020")}},
                {"select-input-fancy-disabled", "disabled", inputsPgObj.fancySelectInputDisabled, compInputsPgObj.label, "12px", "16px", new String[]{commonUtils.hex2Rgb("#6A7070"), commonUtils.hex2RgbWithoutTransparency("#6A7070")}},
                {"select-input-fancy-readOnly", "readOnly", inputsPgObj.fancySelectInputReadOnly, compInputsPgObj.label, "12px", "16px", new String[]{commonUtils.hex2Rgb("#6A7070"), commonUtils.hex2RgbWithoutTransparency("#6A7070")}},
        };
    }

    @Test(testName = "Fancy Select Input Label Test", dataProvider = "Fancy Select Input Label Test Data", groups = {"desktop-regression"})
    private void fancySelectInputBoxLabelTest(String type, String state, By elem, By label, String expLabelFontSize, String expLabelLineHt, String[] expLabelColor) throws Exception {
        String[] detailsPropertiesList = new String[]{"elementId", "select-target", "componentName", "Select"};
        String[] propsPropertiesList = new String[]{"id", type, "labelText", "Select Label", "options", "['ipad', 'mac','iphone']", "fancy", "true", "inputState", state, "changeHandler", "function () {}", "infoMessage", "This is an info message", "errorMessage", "This is an error message'"};
        setConfigAndLaunch(detailsPropertiesList, propsPropertiesList);

        labelColor = commonUtils.getCSSValue(label, "color");
        labelFontSize = commonUtils.getCSSValue(label, "font-size");
        labelLineHeight = commonUtils.getCSSValue(label, "line-height");

        isLabelColor = commonUtils.assertCSSProperties("color", labelColor, expLabelColor);
        if (!isLabelColor) {
            log.info("Label color of " + type + " is not as per spec, actual " + labelColor);
        }
        isLabelFontSize = commonUtils.assertValue(labelFontSize, expLabelFontSize, "Font-size of " + type + " Label is not as per spec");
        islabelLineHeight = commonUtils.assertValue(labelLineHeight, expLabelLineHt, "Line-height of " + type + " Label is not as per spec");
        isLabelFor = commonUtils.checkLabelForVal(label, elem);
        if (!isLabelFor) {
            log.info("Label for " + type + " is not tagged to the appropriate input");
        }
        Assert.assertTrue(isLabelColor && isLabelFontSize && islabelLineHeight && isLabelFor);
    }

    @DataProvider(name = "Fancy Select Input Msg Test Data")
    public Object[][] getFancySelectInputMsgData() {
        return new Object[][]{
                {"select-input-fancy", "default", "select-input-fancy-info", infoMsgClassName, inputsPgObj.fancySelectInputInfoMsg, "12px", "3px", new String[]{commonUtils.hex2Rgb("#6A7070"), commonUtils.hex2RgbWithoutTransparency("#6A7070")}},

                {"select-input-fancy-error", "error", "select-input-fancy-error-info", infoMsgClassName, inputsPgObj.fancySelectInputErrorInfoMsg, "12px", "3px", new String[]{commonUtils.hex2Rgb("#6A7070"), commonUtils.hex2RgbWithoutTransparency("#6A7070")}},
                {"select-input-fancy-error", "error", "select-input-fancy-error-errorMsg", errorMsgClassName, inputsPgObj.fancySelectInputErrorErrorMsg, "12px", "3px", new String[]{commonUtils.hex2Rgb("#DB0020"), commonUtils.hex2RgbWithoutTransparency("#DB0020")}},

                {"select-input-fancy-disabled", "disabled", "select-input-fancy-disabled-info", infoMsgClassName, inputsPgObj.fancySelectInputDisabledInfoMsg, "12px", "3px", new String[]{commonUtils.hex2Rgb("#6A7070"), commonUtils.hex2RgbWithoutTransparency("#6A7070")}},

                {"select-input-fancy-readOnly", "readOnly", "select-input-fancy-readOnly-info", infoMsgClassName, inputsPgObj.fancySelectInputReadOnlyInfoMsg, "12px", "3px", new String[]{commonUtils.hex2Rgb("#6A7070"), commonUtils.hex2RgbWithoutTransparency("#6A7070")}},
        };
    }

    @Test(testName = "Fancy Select Input Msg Test", dataProvider = "Fancy Select Input Msg Test Data", groups = {"desktop-regression"})
    private void fancySelectInputBoxMsgTest(String type, String state, String msgType, String className, By msg, String expFontSize, String expPaddingTop, String[] expColor) throws Exception {
        String[] detailsPropertiesList = new String[]{"elementId", "select-target", "componentName", "Select"};
        String[] propsPropertiesList = new String[]{"id", type, "labelText", "Select Label", "options", "['ipad', 'mac','iphone']", "fancy", "true", "inputState", state, "changeHandler", "function () {}", "infoMessage", "This is an info message", "errorMessage", "This is an error message'"};
        setConfigAndLaunch(detailsPropertiesList, propsPropertiesList);

        js = (JavascriptExecutor) driver;
        webElement = driver.findElement(By.className(className));
        js.executeScript("arguments[0].setAttribute('id','" + msgType + "')", webElement);
        fontSize = commonUtils.getCSSValue(msg, "font-size");
        isFontSize = commonUtils.assertValue(fontSize, expFontSize, "font size of " + type + " is not as per spec");
        paddingTop = commonUtils.getCSSValue(msg, "padding-top");
        isPaddingTop = commonUtils.assertValue(paddingTop, expPaddingTop, "padding top of " + type + " is not as per spec");
        color = commonUtils.getCSSValue(msg, "color");
        isColor = commonUtils.assertCSSProperties("color", color, expColor);
        if (!isColor) {
            log.info("Font Color of " + type + " is not as per spec, actual " + color);
        }
        Assert.assertTrue(isFontSize && isColor && isPaddingTop);
    }

    @DataProvider(name = "Fancy Select Input(Focus) Test Data")
    public Object[][] getFancySelectInputFocusData() {
        return new Object[][]{
                {"select-input-fancy", "pe-input_underline", "select-input-fancy-underline", "default", inputsPgObj.fancySelectInputUnderline, new String[]{commonUtils.hex2Rgb("#047A9C"), commonUtils.hex2RgbWithoutTransparency("#047A9C")}, "4px", "-3px"},
                {"select-input-basic-error", "pe-inputError_underline", "select-input-fancy-error-underline", "error", inputsPgObj.fancySelectInputErrorUnderline, new String[]{commonUtils.hex2Rgb("#DB0020"), commonUtils.hex2RgbWithoutTransparency("#DB0020")}, "4px", "-3px"},
        };
    }

    @Test(testName = "Fancy Select Input in Focus Mode Test", dataProvider = "Fancy Select Input(Focus) Test Data", groups = {"desktop-regression"})
    private void fancySelectInputFocusTest(String type, String className, String id, String state, By underLine, String[] expBgColor, String expHeight, String expMarginTop) throws Exception {
        if (browser.equals("firefox") || browser.equals("safari") || lBrowser.equals("firefox")) {
            throw new SkipException("the focus operation is not supported on firefox/safari/ie drivers");
        }
        String[] detailsPropertiesList = new String[]{"elementId", "select-target", "componentName", "Select"};
        String[] propsPropertiesList = new String[]{"id", type, "labelText", "Select Label", "options", "['ipad', 'mac','iphone']", "fancy", "true", "inputState", state, "changeHandler", "function () {}", "infoMessage", "This is an info message", "errorMessage", "This is an error message'"};
        setConfigAndLaunch(detailsPropertiesList, propsPropertiesList);

        js = (JavascriptExecutor) driver;
        webElement = driver.findElement(By.className(className));
        js.executeScript("arguments[0].setAttribute('id','" + id + "')", webElement);
        commonUtils.focusOnElementById(type);
        bgColor = commonUtils.getCSSValue(underLine, "background-color");
        height = commonUtils.getCSSValue(underLine, "height");
        marginTop = commonUtils.getCSSValue(underLine, "margin-top");

        isBgColor = commonUtils.assertCSSProperties("background-color", bgColor, expBgColor);
        if (!isBgColor) {
            log.info("The underline background-color of " + type + " is not as per spec, actual " + bgColor);
        }
        isHeight = commonUtils.assertValue(height, expHeight, "height of underline for " + type + " is not as per spec");
        isMarginTop = commonUtils.assertValue(marginTop, expMarginTop, "margin Top of underline for " + type + " is not as per spec");

        Assert.assertTrue(isBgColor && isHeight && isMarginTop);
    }

    //Basic - Select Input
    @DataProvider(name = "Basic Select Input All States Test Data")
    public Object[][] getBasicSelectInputAllStatesData() {
        return new Object[][]{
                {"Basic Select Input", "select-input-basic", "pe-select-container", "select-input-div", "default", inputsPgObj.basicSelectInput, inputsPgObj.basicSelectInputContainer, new String[]{"36px", "34px"}, new String[]{"14px"}, new String[]{"14px", "4.2px"}, new String[]{"6px"}, new String[]{"14px"}, new String[]{"18px", "20px", "17px", "19px"}, new String[]{commonUtils.hex2Rgb("#252525"), commonUtils.hex2RgbWithoutTransparency("#252525")}, new String[]{commonUtils.hex2Rgb("#FFFFFF"), commonUtils.hex2RgbWithoutTransparency("#FFFFFF")}},
                {"Basic Select Input Error", "select-input-basic-error", "pe-select-container-error", "select-input-error-div", "error", inputsPgObj.basicSelectInputError, inputsPgObj.basicSelectInputErrorContainer, new String[]{"36px", "34px"}, new String[]{"14px"}, new String[]{"4.2px", "4.199999809265137px"}, new String[]{"6px"}, new String[]{"14px"}, new String[]{"18px", "20px", "17px", "19px"}, new String[]{commonUtils.hex2Rgb("#252525"), commonUtils.hex2RgbWithoutTransparency("#252525")}, new String[]{commonUtils.hex2Rgb("#FFFFFF"), commonUtils.hex2RgbWithoutTransparency("#FFFFFF")}},
                {"Basic Select Input Disabled", "select-input-basic-disabled", "pe-select-container-disabled", "select-input-disabled-div", "disabled", inputsPgObj.basicSelectInputDisabled, inputsPgObj.basicSelectInputDisabledContainer, new String[]{"36px", "34px"}, new String[]{"14px"}, new String[]{"14px"}, new String[]{"6px"}, new String[]{"14px"}, new String[]{"18px", "20px", "17px", "19px"}, new String[]{commonUtils.hex2Rgb("#C7C7C7"), commonUtils.hex2RgbWithoutTransparency("#C7C7C7")}, new String[]{commonUtils.hex2Rgb("#E9E9E9"), commonUtils.hex2RgbWithoutTransparency("#E9E9E9")}},
                {"Basic Select Input ReadOnly", "select-input-basic-readOnly", "pe-select-container-readonly", "select-input-readOnly-div", "readOnly", inputsPgObj.basicSelectInputReadOnly, inputsPgObj.basicSelectInputReadOnlyContainer, new String[]{"36px", "34px"}, new String[]{"0px"}, new String[]{"0px"}, new String[]{"6px"}, new String[]{"14px"}, new String[]{"18px", "20px", "17px", "19px"}, new String[]{commonUtils.hex2Rgb("#252525"), commonUtils.hex2RgbWithoutTransparency("#252525")}, new String[]{commonUtils.hex2Rgb("#FFFFFF"), commonUtils.hex2RgbWithoutTransparency("#FFFFFF")}}};
    }

    @Test(testName = "Basic Select Input All States Test", dataProvider = "Basic Select Input All States Test Data", groups = "desktop-regression")
    private void basicSelectInputAllStatesTest(String type, String id, String className, String divElement, String state, By element, By elementContainer, String[] expHeight, String[] expPaddingLeft, String[] expPaddingRight, String[] expMarginTop, String[] expFontSize, String[] expLineHeight, String[] expColor, String[] expBackgroundColor) throws Exception {
        String[] detailsPropertiesList = new String[]{"elementId", "select-target", "componentName", "Select"};
        String[] propsPropertiesList = new String[]{"id", id, "labelText", "Select Label", "options", "['ipad', 'mac','iphone']", "fancy", "false", "inputState", state, "changeHandler", "function () {}", "infoMessage", "This is an info message", "errorMessage", "This is an error message'"};
        setConfigAndLaunch(detailsPropertiesList, propsPropertiesList);

        js = (JavascriptExecutor) driver;
        webElement = driver.findElement(By.className(className));
        js.executeScript("arguments[0].setAttribute('id','" + divElement + "')", webElement);
        height = commonUtils.getCSSValue(elementContainer, "height");
        paddingLeft = commonUtils.getCSSValue(element, "padding-left");
        paddingRight = commonUtils.getCSSValue(element, "padding-right");
        marginTop = commonUtils.getCSSValue(elementContainer, "margin-top");
        fontSize = commonUtils.getCSSValue(elementContainer, "font-size");
        lineHeight = commonUtils.getCSSValue(elementContainer, "line-height");
        color = commonUtils.getCSSValue(elementContainer, "color");
        backgroundColor = commonUtils.getCSSValue(elementContainer, "background-color");

        isHeight = commonUtils.assertCSSProperties("height", height, expHeight);
        isPaddingLeft = commonUtils.assertCSSProperties("padding-left", paddingLeft, expPaddingLeft);
        isPaddingRight = commonUtils.assertCSSProperties("padding-right", paddingRight, expPaddingRight);
        isMarginTop = commonUtils.assertCSSProperties("margin-top", marginTop, expMarginTop);

        isFontSize = commonUtils.assertCSSProperties("font-size", fontSize, expFontSize);
        isLineHeight = commonUtils.assertCSSProperties("line-height", lineHeight, expLineHeight);
        isColor = commonUtils.assertCSSProperties("color", color, expColor);
        isBackgroundColor = commonUtils.assertCSSProperties("background-color", backgroundColor, expBackgroundColor);

        if (!isHeight) {
            log.info("Height for " + type + " is not as per the spec, actual: " + height);
        }
        if (!isPaddingLeft) {
            log.info("padding-left for " + type + " is not as per the spec, actual: " + paddingLeft);
        }
        if (!isPaddingRight) {
            log.info("padding-right for " + type + " is not as per the spec, actual: " + paddingRight);
        }
        if (!isMarginTop) {
            log.info("margin-top for " + type + " is not as per the spec, actual: " + marginTop);
        }
        if (!isFontSize) {
            log.info("font size for " + type + " is not as per the spec, actual: " + fontSize);
        }
        if (!isLineHeight) {
            log.info("Line Height for " + type + " is not as per the spec, actual: " + lineHeight);
        }
        if (!isColor) {
            log.info("color for " + type + " is not as per the spec, actual: " + color);
        }
        if (!isBackgroundColor) {
            log.info("bg-color for " + type + " is not as per the spec, actual: " + backgroundColor);
        }
        Assert.assertTrue(isHeight && isPaddingLeft && isPaddingRight && isMarginTop && isFontSize && isLineHeight && isColor && isBackgroundColor);
    }

    @DataProvider(name = "Basic Select Input Border Test Data")
    public Object[][] getBasicSelectInputBorderData() {
        return new Object[][]{
                {"select-input-basic", "default", "pe-select-container", "select-input-div", inputsPgObj.basicSelectInputContainer, "3px", "1px", "solid", new String[]{commonUtils.hex2Rgb("#C7C7C7"), commonUtils.hex2RgbWithoutTransparency("#C7C7C7")}},
                {"select-input-basic-error", "error", "pe-select-container-error", "select-input-error-div", inputsPgObj.basicSelectInputErrorContainer, "3px", "1px", "solid", new String[]{commonUtils.hex2Rgb("#DB0020"), commonUtils.hex2RgbWithoutTransparency("#DB0020")}},
                {"select-input-basic-disabled", "disabled", "pe-select-container-disabled", "select-input-disabled-div", inputsPgObj.basicSelectInputDisabled, "3px", "1px", "solid", new String[]{commonUtils.hex2Rgb("#C7C7C7"), commonUtils.hex2RgbWithoutTransparency("#C7C7C7")}},
                {"select-input-basic-readOnly", "readOnly", "pe-select-container-readonly", "select-input-readOnly-div", inputsPgObj.basicSelectInputReadOnlyContainer, "3px", "0px", "none", new String[]{}},
        };
    }

    @Test(testName = "Basic Select Input Box- Border Test", dataProvider = "Basic Select Input Border Test Data", groups = {"desktop-regression"})
    private void basicSelectInputBoxBorderTest(String type, String state, String className, String id, By elem, String expBorderRadius, String expBorderWidth, String expBorderStyle, String[] expBorderColor) throws Exception {
        String[] detailsPropertiesList = new String[]{"elementId", "select-target", "componentName", "Select"};
        String[] propsPropertiesList = new String[]{"id", type, "labelText", "Select Label", "options", "['ipad', 'mac','iphone']", "fancy", "false", "inputState", state, "changeHandler", "function () {}", "infoMessage", "This is an info message", "errorMessage", "This is an error message'"};
        setConfigAndLaunch(detailsPropertiesList, propsPropertiesList);

        js = (JavascriptExecutor) driver;
        webElement = driver.findElement(By.className(className));
        js.executeScript("arguments[0].setAttribute('id','" + id + "')", webElement);
        Thread.sleep(500);
        for (String cssProperty : borderRadii) {
            borderRadius = commonUtils.getCSSValue(elem, cssProperty);
            isBorderRadius = commonUtils.assertValue(borderRadius, expBorderRadius, "Border radius  " + cssProperty + " of " + type + " is not as per spec");
            Assert.assertTrue(isBorderRadius);
        }
        for (String cssProperty : borderWidths) {
            borderWidth = commonUtils.getCSSValue(elem, cssProperty);
            isBorderWidth = commonUtils.assertValue(borderWidth, expBorderWidth, "Border width " + cssProperty + " of " + type + " is not as per spec");
            Assert.assertTrue(isBorderWidth);
        }
        for (String cssProperty : borderStyles) {
            borderStyle = commonUtils.getCSSValue(elem, cssProperty);
            isBorderStyle = commonUtils.assertValue(borderStyle, expBorderStyle, "Border style " + cssProperty + " of " + type + " is not as per spec");
            Assert.assertTrue(isBorderStyle);
        }
        if (!type.equals("select-input-basic-readOnly")) {
            for (String cssProperty : borderColors) {
                borderColor = commonUtils.getCSSValue(elem, cssProperty);
                isBorderColor = commonUtils.assertCSSProperties(cssProperty, borderColor, expBorderColor);
                if (!isBorderColor) {
                    log.info("Border color " + cssProperty + " of " + type + " is not as per spec, actual " + borderColor);
                }
                Assert.assertTrue(isBorderColor);
            }
        }
    }

    @DataProvider(name = "Basic Select Input Label and Icon Test Data")
    public Object[][] getBasicSelectInputLabelIconData() {
        return new Object[][]{
                {"select-input-basic", "default", inputsPgObj.basicSelectInput, compInputsPgObj.label, compInputsPgObj.icon, new String[]{commonUtils.hex2Rgb("#6A7070"), commonUtils.hex2RgbWithoutTransparency("#6A7070")}, "12px", "16px"},
                {"select-input-basic-error", "error", inputsPgObj.basicSelectInputError, compInputsPgObj.label, compInputsPgObj.icon, new String[]{commonUtils.hex2Rgb("#DB0020"), commonUtils.hex2RgbWithoutTransparency("#DB0020")}, "12px", "16px"},
                {"select-input-basic-disabled", "disabled", inputsPgObj.basicSelectInputDisabled, compInputsPgObj.label, compInputsPgObj.icon, new String[]{commonUtils.hex2Rgb("#6A7070"), commonUtils.hex2RgbWithoutTransparency("#6A7070")}, "12px", "16px"},
                {"select-input-basic-readOnly", "readOnly", inputsPgObj.basicSelectInputReadOnly, compInputsPgObj.label, compInputsPgObj.icon, new String[]{commonUtils.hex2Rgb("#6A7070"), commonUtils.hex2RgbWithoutTransparency("#6A7070")}, "12px", "16px"},
        };
    }

    @Test(testName = "Basic Select Input Label Test", dataProvider = "Basic Select Input Label and Icon Test Data", groups = {"desktop-regression"}, retryAnalyzer = RetryAnalyzer.class)
    private void basicSelectInputBoxLabelTest(String type, String state, By elem, By label, By icon, String[] expLabelColor, String expLabelFontSize, String expLabelLineHt) throws Exception {
        // Select Input Label
        String[] detailsPropertiesList = new String[]{"elementId", "select-target", "componentName", "Select"};
        String[] propsPropertiesList = new String[]{"id", type, "labelText", "Select Label", "options", "['ipad', 'mac','iphone']", "fancy", "false", "inputState", state, "changeHandler", "function () {}", "infoMessage", "This is an info message", "errorMessage", "This is an error message'"};
        setConfigAndLaunch(detailsPropertiesList, propsPropertiesList);

        labelColor = commonUtils.getCSSValue(label, "color");
        labelFontSize = commonUtils.getCSSValue(label, "font-size");
        labelLineHeight = commonUtils.getCSSValue(label, "line-height");

        isLabelColor = commonUtils.assertCSSProperties("color", labelColor, expLabelColor);
        if (!isLabelColor) {
            log.info("Label color of " + type + " is not as per spec, actual " + labelColor);
        }
        isLabelFontSize = commonUtils.assertValue(labelFontSize, expLabelFontSize, "Font-size of " + type + " Label is not as per spec");
        islabelLineHeight = commonUtils.assertValue(labelLineHeight, expLabelLineHt, "Line-height of " + type + " Label is not as per spec");
        isLabelFor = commonUtils.checkLabelForVal(label, elem);
        if (!isLabelFor) {
            log.info("Label for " + type + " is not tagged to the appropriate input");
        }
        // icon
        actIconClass = commonUtils.getAttributeValue(icon, "class");
        isIconClass = commonUtils.assertValue(actIconClass, "pe-icon--dropdown-open-sm-24", "Dropdown icon does not comply to the \"pe-icon--dropdown-open-sm-24\"");
        Assert.assertTrue(isLabelColor && isLabelFontSize && islabelLineHeight && isLabelFor && isIconClass);
    }

    @DataProvider(name = "Basic Select Input(Focus) Test Data")
    public Object[][] getBasicSelectInputFocusData() {
        return new Object[][]{
                {"select-input-basic", "default", inputsPgObj.basicSelectInput, "1px", "solid", new String[]{commonUtils.hex2Rgb("#047A9C"), commonUtils.hex2RgbWithoutTransparency("#047A9C")}, new String[]{"rgb(4, 122, 156) 0px 0px 5px 0px", "0px 0px 5px 0px #047a9c"}},
                {"select-input-basic-error", "error", inputsPgObj.basicSelectInputError, "1px", "solid", new String[]{commonUtils.hex2Rgb("#DB0020"), commonUtils.hex2RgbWithoutTransparency("#DB0020")}, new String[]{"rgb(219, 0, 32) 0px 0px 4px 0px", "0px 0px 4px 0px #db0020"}},
        };
    }

    @Test(testName = "Basic Select Input and Error Test Focus", dataProvider = "Basic Select Input(Focus) Test Data", groups = {"desktop-regression"})
    private void basicSelectInputFocusTest(String type, String state, By elem, String expBorderWidth, String expBorderStyle, String[] expBorderColor, String[] expBoxShadow) throws Exception {
        if (browser.equals("firefox") || browser.equals("safari") || lBrowser.equals("firefox")) {
            throw new SkipException("the focus operation is not supported on firefox/safari/ie drivers");
        }
        String[] detailsPropertiesList = new String[]{"elementId", "select-target", "componentName", "Select"};
        String[] propsPropertiesList = new String[]{"id", type, "labelText", "Select Label", "options", "['ipad', 'mac','iphone']", "fancy", "false", "inputState", state, "changeHandler", "function () {}", "infoMessage", "This is an info message", "errorMessage", "This is an error message'"};
        setConfigAndLaunch(detailsPropertiesList, propsPropertiesList);

        commonUtils.focusOnElementById(type);
        Thread.sleep(1000);
        for (String cssProperty : borderWidths) {
            borderWidth = commonUtils.getCSSValue(elem, cssProperty);
            isBorderWidth = commonUtils.assertValue(borderWidth, expBorderWidth, "Border width " + cssProperty + " of " + type + " (Focus) field is not as per spec");
            Assert.assertTrue(isBorderWidth);
        }
        for (String cssProperty : borderStyles) {
            borderStyle = commonUtils.getCSSValue(elem, cssProperty);
            isBorderStyle = commonUtils.assertValue(borderStyle, expBorderStyle, "Border style " + cssProperty + " of " + type + " (Focus) field is not as per spec");
            Assert.assertTrue(isBorderStyle);
        }
        Thread.sleep(1000);
        for (String cssProperty : borderColors) {
            borderColor = commonUtils.getCSSValue(elem, cssProperty);
            isBorderColor = commonUtils.assertCSSProperties(cssProperty, borderColor, expBorderColor);
            if (!isBorderColor) {
                log.info("Border color " + cssProperty + " of " + type + " (Focus) field is not as per spec, actual " + borderColor);
            }
            Assert.assertTrue(isBorderColor);
        }
        boxShadow = commonUtils.getCSSValue(elem, "box-shadow");
        isBoxShadow = commonUtils.assertCSSProperties("box-shadow", boxShadow, expBoxShadow);
        if (!isBoxShadow) {
            log.info("Box-shadow of " + type + " (Focus) is not as per spec, actual " + boxShadow);
        }
        Assert.assertTrue(isBoxShadow);
    }

    //Check Box
    @DataProvider(name = "Check Box Input - Test Data")
    public Object[][] getCheckBoxTestData() {
        return new Object[][]{
                {inputsPgObj.checkBoxClass, new String[]{"relative"}, new String[]{"16px"}, new String[]{"14px"}}
        };
    }

    @Test(testName = "Verify Checkbox Input", dataProvider = "Check Box Input - Test Data", groups = {"desktop-ci", "desktop-regression"})
    private void checkboxTest(By element, String[] expPosition, String[] expMinHeight, String[] expMarginBottom) throws Exception {
        String[] detailsPropertiesList = new String[]{"elementId", "checkbox-target", "componentName", "RadioCheckGroup"};
        String[] propsPropertiesList = new String[]{"id", "checkboxInput", "inputType", "checkbox", "legendText", "checkbox1", "options", "[{value: 0, label: 'coffee', checked: true}, {value: 1, label: 'tea'}, {value: 2, label: 'hot cocoa'}]", "changeHandler", "function () {}"};
        setConfigAndLaunch(detailsPropertiesList, propsPropertiesList);

        js = (JavascriptExecutor) driver;
        webElement = driver.findElement(element);
        js.executeScript("arguments[0].setAttribute('id', 'checkboxInput')", webElement);

        position = commonUtils.getCSSValue(element, "position");
        minHeight = commonUtils.getCSSValue(element, "min-height");
        marginBottom = commonUtils.getCSSValue(element, "margin-bottom");

        isPosition = commonUtils.assertCSSProperties("position", position, expPosition);
        isMinHeight = commonUtils.assertCSSProperties("min-height", minHeight, expMinHeight);
        isMarginBottom = commonUtils.assertCSSProperties("marginBottom", marginBottom, expMarginBottom);

        if (!isPosition) {
            log.info("position for checkbox input is not as per the spec, actual: " + position);
        }
        if (!isMinHeight) {
            log.info("min-height for checkbox input is not as per the spec, actual: " + minHeight);
        }
        if (!isMarginBottom) {
            log.info("margin-bottom for checkbox input is not as per the spec, actual: " + marginBottom);
        }
        Assert.assertTrue(isPosition && isMinHeight && isMarginBottom);
    }

    @DataProvider(name = "Check Box - Normal State Test Data")
    public Object[][] getCheckBoxNormalStateTestData() {
        return new Object[][]{
                {inputsPgObj.checkBoxState, new String[]{"absolute"}, new String[]{"0px"}, new String[]{"0px"}, new String[]{"16px", "14px"}, new String[]{"16px", "14px"}, "1px", "solid", new String[]{commonUtils.hex2Rgb("#C7C7C7"), commonUtils.hex2RgbWithoutTransparency("#C7C7C7")}, "2px"}
        };
    }

    @Test(testName = "Verify Checkbox - Normal State", dataProvider = "Check Box - Normal State Test Data", groups = {"desktop-ci", "desktop-regression"})
    private void checkboxNormalStateTest(By element, String[] expPosition, String[] expTop, String[] expLeft, String[] expHeight, String[] expWidth, String expBorderWidth, String expBorderStyle, String[] expBorderColor, String expBorderRadius) throws Exception {
        String[] detailsPropertiesList = new String[]{"elementId", "checkbox-target", "componentName", "RadioCheckGroup"};
        String[] propsPropertiesList = new String[]{"id", "checkbox-state", "inputType", "checkbox", "legendText", "checkbox1", "options", "[{value: 0, label: 'coffee', checked: true}, {value: 1, label: 'tea'}, {value: 2, label: 'hot cocoa'}]", "changeHandler", "function () {}"};
        setConfigAndLaunch(detailsPropertiesList, propsPropertiesList);

        js = (JavascriptExecutor) driver;
        webElement = driver.findElement(By.xpath("//span"));
        js.executeScript("arguments[0].setAttribute('id', 'checkbox-state')", webElement);

        position = commonUtils.getCSSValue(element, "position");
        topProperty = commonUtils.getCSSValue(element, "top");
        leftProperty = commonUtils.getCSSValue(element, "left");
        height = commonUtils.getCSSValue(element, "height");
        width = commonUtils.getCSSValue(element, "width");

        isPosition = commonUtils.assertCSSProperties("position", position, expPosition);
        isTop = commonUtils.assertCSSProperties("top", topProperty, expTop);
        isLeft = commonUtils.assertCSSProperties("left", leftProperty, expLeft);
        isHeight = commonUtils.assertCSSProperties("height", height, expHeight);
        isWidth = commonUtils.assertCSSProperties("width", width, expWidth);

        if (!isPosition) {
            log.info("position for checkbox input is not as per the spec, actual" + position);
        }
        if (!isTop) {
            log.info("top for checkbox input is not as per the spec, actual" + topProperty);
        }
        if (!isLeft) {
            log.info("left for checkbox input is not as per the spec, actual" + leftProperty);
        }
        if (!isHeight) {
            log.info("height for checkbox input is not as per the spec, actual" + height);
        }
        if (!isWidth) {
            log.info("width for checkbox input is not as per the spec, actual" + width);
        }
        Assert.assertTrue(isPosition && isTop && isLeft && isHeight && isWidth);

        for (String cssProperty : borderWidths) {
            borderWidth = commonUtils.getCSSValue(element, cssProperty);
            isBorderWidth = commonUtils.assertValue(borderWidth, expBorderWidth, "Border width '" + cssProperty + "' of check box is not as per spec");
            Assert.assertTrue(isBorderWidth);
        }
        for (String cssProperty : borderStyles) {
            borderStyle = commonUtils.getCSSValue(element, cssProperty);
            isBorderStyle = commonUtils.assertValue(borderStyle, expBorderStyle, "Border style '" + cssProperty + "' of check box is not as per spec");
            Assert.assertTrue(isBorderStyle);
        }
        for (String cssProperty : borderColors) {
            borderColor = commonUtils.getCSSValue(element, cssProperty);
            isBorderColor = commonUtils.assertCSSProperties(cssProperty, borderColor, expBorderColor);
            if (!isBorderColor) {
                log.info("Border color '" + cssProperty + "' of check box is not as per spec, actual: " + borderColor);
            }
            Assert.assertTrue(isBorderColor);
        }
        for (String cssProperty : borderRadii) {
            borderRadius = commonUtils.getCSSValue(element, cssProperty);
            isBorderRadius = commonUtils.assertValue(borderRadius, expBorderRadius, "Border radius '" + cssProperty + "' of check box is not as per spec");
            Assert.assertTrue(isBorderRadius);
        }
    }

    @DataProvider(name = "Check Box - All States Test Data")
    public Object[][] getCheckBoxAllStatesTestData() {
        return new Object[][]{
                {"focus", "checkbox-state", By.xpath("//span"), inputsPgObj.checkBoxState, new String[]{commonUtils.hex2Rgb("#047A9C"), commonUtils.hex2RgbWithoutTransparency("#047A9C")}},
                {"disabled", "checkbox-checked-disabled", By.xpath("//div[1]/span"), inputsPgObj.checkBoxCheckedDisabled, new String[]{commonUtils.hex2Rgb("#C7C7C7"), commonUtils.hex2RgbWithoutTransparency("#C7C7C7")}}
        };
    }

    @Test(testName = "Verify Checkbox - All States Test", dataProvider = "Check Box - All States Test Data", groups = "desktop-regression")
    private void checkboxAllStatesTest(String state, String id, By xpathElement, By element, String[] expBorderColor) throws Exception {
        if (state.equals("focus") && (browser.equals("firefox") || browser.equals("safari") || browser.equals("ie") || lBrowser.equals("firefox"))) {
            throw new SkipException("the focus operation is not supported on firefox/safari/ie drivers");
        }
        String[] detailsPropertiesList = new String[]{"elementId", "checkbox-target", "componentName", "RadioCheckGroup"};
        String[] propsPropertiesList = {};
        if (state.equals("focus")) {
            propsPropertiesList = new String[]{"id", id, "inputType", "checkbox", "legendText", "checkbox1", "options", "[{value: 0, label: 'coffee', checked: true}, {value: 1, label: 'tea'}, {value: 2, label: 'hot cocoa'}]", "changeHandler", "function () {}"};
        } else {
            propsPropertiesList = new String[]{"id", id, "inputType", "checkbox", "legendText", "checkbox1", "options", "[{value: 0, label: 'coffee', checked: true, disabled: true}, {value: 1, label: 'tea'}, {value: 2, label: 'hot cocoa'}]", "changeHandler", "function () {}"};
        }
        setConfigAndLaunch(detailsPropertiesList, propsPropertiesList);

        js = (JavascriptExecutor) driver;
        webElement = driver.findElement(xpathElement);
        js.executeScript("arguments[0].setAttribute('id', '" + id + "')", webElement);

        if (state.equals("focus")) {
            commonUtils.focusOnElementById("radiocheck-undefined- 0-0");
            commonUtils.focusOnElementById("checkbox-state");
        } else {
            backgroundColor = commonUtils.getCSSValue(element, "background-color");
            isBackgroundColor = commonUtils.assertCSSProperties("bg-color", backgroundColor, new String[]{commonUtils.hex2Rgb("#E9E9E9"), commonUtils.hex2RgbWithoutTransparency("#E9E9E9")});
            if (!isBackgroundColor) {
                log.info("'bg-color of check box in " + state + " state is not as per spec, actual: " + backgroundColor);
            }
            Assert.assertTrue(isBackgroundColor);
        }
        for (String cssProperty : borderColors) {
            borderColor = commonUtils.getCSSValue(element, cssProperty);
            isBorderColor = commonUtils.assertCSSProperties(cssProperty, borderColor, expBorderColor);
            if (!isBorderColor) {
                log.info("Border color '" + cssProperty + "' of check box in " + state + " state is not as per spec");
            }
            Assert.assertTrue(isBorderColor);
        }
    }

    @DataProvider(name = "Check Box - Label Test Data")
    public Object[][] getCheckBoxLabelTestData() {
        return new Object[][]{
                {"checkbox-unchecked", "false", "false", "//div[1]/label", inputsPgObj.checkBoxUnchecked, new String[]{"28px"}, "inline-block"},
                {"checkbox-unchecked-focus", "false", "false", "//div[1]/label", inputsPgObj.checkBoxUnCheckedFocus, new String[]{"28px"}, "inline-block"},
                {"checkbox-checked", "true", "false", "//div[1]/label", inputsPgObj.checkBoxChecked, new String[]{"28px"}, "inline-block"},
                {"checkbox-checked-focus", "true", "false", "//div[1]/label", inputsPgObj.checkBoxCheckedFocus, new String[]{"28px"}, "inline-block"},
                {"checkbox-unchecked-disabled", "false", "true", "//div[1]/label", inputsPgObj.checkBoxUnCheckedDisabled, new String[]{"28px"}, "inline-block"},
                {"checkbox-checked-disabled", "true", "true", "//div[1]/label", inputsPgObj.checkBoxCheckedDisabled, new String[]{"28px"}, "inline-block"}
        };
    }

    @Test(testName = "Verify Check Box - Label", dataProvider = "Check Box - Label Test Data", groups = "desktop-regression")
    private void labelForCheckBoxTest(String id, String checkedValue, String disabledValue, String loc, By element, String[] expPaddingLeft, String expDisplay) throws Exception {
        String[] detailsPropertiesList = new String[]{"elementId", "checkbox-target", "componentName", "RadioCheckGroup"};
        String[] propsPropertiesList = new String[]{"id", id, "inputType", "checkbox", "legendText", "checkbox1", "options", "[{value: 0, label: 'coffee', checked: " + checkedValue + ", disabled: " + disabledValue + "}, {value: 1, label: 'tea'}, {value: 2, label: 'hot cocoa'}]", "changeHandler", "function () {}"};
        setConfigAndLaunch(detailsPropertiesList, propsPropertiesList);

        js = (JavascriptExecutor) driver;
        webElement = driver.findElement(By.xpath(loc));
        js.executeScript("arguments[0].setAttribute('id', '" + id + "')", webElement);

        if (id.contains("focus")) {
            commonUtils.focusOnElementById(id);
        }

        paddingLeft = commonUtils.getCSSValue(element, "padding-left");
        isPaddingLeft = commonUtils.assertCSSProperties("padding-left", paddingLeft, expPaddingLeft);
        if (!isPaddingLeft) {
            log.info("padding-left for checkbox label of " + id + " type is not as per the spec, actual: " + paddingLeft);
        }
        display = commonUtils.getCSSValue(element, "display");
        isDisplay = commonUtils.assertValue(display, expDisplay, "'display' for checkbox label of '" + id + "' type is not as per the spec");

        Assert.assertTrue(isPaddingLeft && isDisplay);
    }

    //Radios
    @DataProvider(name = "Radio Buttons Unselected Selected And Disabled States - SVG Test Data")
    public Object[][] radioButtonsUnselectedSelectedDisabledSVGTestData() {
        return new Object[][]{
                {"unselected", "false", new String[]{commonUtils.hex2Rgb("#C7C7C7"), commonUtils.hex2RgbWithoutTransparency("#C7C7C7")}, new String[]{commonUtils.hex2Rgb("#6A7070"), commonUtils.hex2RgbWithoutTransparency("#6A7070")}, "//div[1]/span", "unselected-span", inputsPgObj.unselectedRadioBtnSpan, "//div[1]/span/*[local-name() = 'svg']", "unselected-svg", inputsPgObj.unselectedRadioBtnSvg},
                {"selected", "true", new String[]{commonUtils.hex2Rgb("#C7C7C7"), commonUtils.hex2RgbWithoutTransparency("#C7C7C7")}, new String[]{commonUtils.hex2Rgb("#6A7070"), commonUtils.hex2RgbWithoutTransparency("#6A7070")}, "//div[1]/span", "selected-span", inputsPgObj.selectedRadioBtnSpan, "//div[1]/span/*[local-name() = 'svg']", "selected-svg", inputsPgObj.selectedRadioBtnSpanSvg},
        };
    }

    @Test(testName = "Radio Buttons Unselected Selected And Disabled States - SVG Test", dataProvider = "Radio Buttons Unselected Selected And Disabled States - SVG Test Data", groups = {"desktop-regression"})
    private void radioButtonsUnselectedSelectedDisabledSVGTest(String elemType, String checkedValue, String[] expBorderColor, String[] expSvgColor, String spanXpath, String spanId, By span, String svgXpath, String svgId, By svg) throws Exception {
        String[] detailsPropertiesList = new String[]{"elementId", "radio-target", "componentName", "RadioCheckGroup"};
        String[] propsPropertiesList = new String[]{"id", elemType, "inputType", "radio", "legendText", "radio1", "options", "[{value: 0, label: 'coffee',checked: " + checkedValue + ",disabled: false},{value: 1, label: 'tea'}]", "changeHandler", "function () {}"};

        setConfigAndLaunch(detailsPropertiesList, propsPropertiesList);

        js = (JavascriptExecutor) driver;
        webElement = driver.findElement(By.xpath(spanXpath));
        js.executeScript("arguments[0].setAttribute('id', '" + spanId + "')", webElement);

        webElement = driver.findElement(By.xpath(svgXpath));
        js.executeScript("arguments[0].setAttribute('id', '" + svgId + "')", webElement);

        height = commonUtils.getCSSValue(span, "height");
        width = commonUtils.getCSSValue(span, "width");
        color = commonUtils.getCSSValue(span, "color");
        radioBtnSelectedColor = commonUtils.getCSSValue(svg, "color");

        for (String cssProperty : borderColors) {
            borderColor = commonUtils.getCSSValue(span, cssProperty);
            isBorderColor = commonUtils.assertCSSProperties(cssProperty, borderColor, expBorderColor);
            if (!isBorderColor) {
                log.info("Border-color of " + elemType + " is not as per spec, actual " + borderColor);
            }
            Assert.assertTrue(isBorderColor);
        }
        for (String cssProperty : borderStyles) {
            borderStyle = commonUtils.getCSSValue(span, cssProperty);
            isBorderStyle = commonUtils.assertValue(borderStyle, "solid", "'" + cssProperty + "' of " + elemType + " is not as per spec");
            Assert.assertTrue(isBorderStyle);
        }
        for (String cssProperty : borderWidths) {
            borderWidth = commonUtils.getCSSValue(span, cssProperty);
            isBorderWidth = commonUtils.assertValue(borderWidth, "1px", "'" + cssProperty + "' of " + elemType + " is not as per spec");
            Assert.assertTrue(isBorderWidth);
        }
        for (String cssProperty : borderRadii) {
            borderRadius = commonUtils.getCSSValue(span, cssProperty);
            isBorderRadius = commonUtils.assertCSSProperties(cssProperty, borderRadius, new String[]{"50%", "8px"});
            if (!isBorderRadius) {
                log.info("'" + cssProperty + "' of " + elemType + " is not as per spec, actual " + borderRadius);
            }
            Assert.assertTrue(isBorderRadius);
        }
        for (String cssProperty : paddings) {
            radioBtnPadding = commonUtils.getCSSValue(span, cssProperty);
            isRadioBtnPadding = commonUtils.assertCSSProperties(cssProperty, radioBtnPadding, new String[]{"3px", "6px"});
            if (!isRadioBtnPadding) {
                log.info("'" + cssProperty + "' of " + elemType + " is not as per spec, actual " + radioBtnPadding);
            }
            Assert.assertTrue(isRadioBtnPadding);
        }
        if (elemType.contains("disabled")) {
            bgColor = commonUtils.getCSSValue(span, "background-color");
            isBgColor = commonUtils.assertCSSProperties("background-color", bgColor, new String[]{commonUtils.hex2Rgb("#E9E9E9"), commonUtils.hex2RgbWithoutTransparency("#E9E9E9")});
            Assert.assertTrue(isBgColor);
        }
        isRadioBtnSelectedColor = commonUtils.assertCSSProperties("color", radioBtnSelectedColor, expSvgColor);
        if (!isRadioBtnSelectedColor) {
            log.info("Svg-icon color when selected of " + elemType + " is not as per spec, actual " + radioBtnSelectedColor);
        }
        Assert.assertTrue(isRadioBtnSelectedColor);
    }

    @DataProvider(name = "Radio Buttons Unselected Selected And Disabled States - Label Test Data")
    public Object[][] radioButtonsUnselectedSelectedDisabledLabelTestData() {
        return new Object[][]{
                {"unselected", "false", "false", "28px", new String[]{"14px", "13.93px"}, "//div[1]/label", "label-unselected", inputsPgObj.unselectedRadioBtnLabel},
                {"selected", "true", "false", "28px", new String[]{"14px", "13.93px"}, "//div[1]/label", "label-selected", inputsPgObj.selectedRadioBtnLabel},
                {"disabled-unselected", "false", "true", "28px", new String[]{"14px", "13.93px"}, "//div[1]/label", "label-disabled-unselected", inputsPgObj.disabledUnselectedRadioBtnLabel},
                {"disabled-selected", "true", "true", "28px", new String[]{"14px", "13.93px"}, "//div[1]/label", "label-disabled-selected", inputsPgObj.disabledSelectedRadioBtnLabel}
        };
    }

    @Test(testName = "Radio Buttons Unselected Selected And Disabled States LabelTest", dataProvider = "Radio Buttons Unselected Selected And Disabled States - Label Test Data", groups = {"desktop-regression"})
    private void radioButtonsUnselectedSelectedDisabledLabelTest(String elemType, String checkedValue, String disabledValue, String expPaddingLeft, String[] expLabelfontSize, String labelXpath, String labelId, By label) throws Exception {
        String[] detailsPropertiesList = new String[]{"elementId", "radio-target", "componentName", "RadioCheckGroup"};
        String[] propsPropertiesList = new String[]{"id", elemType, "inputType", "radio", "legendText", "radio1", "options", "[{value: 0, label: 'coffee',checked: " + checkedValue + ",disabled: " + disabledValue + "},{value: 1, label: 'tea'}]", "changeHandler", "function () {}"};
        setConfigAndLaunch(detailsPropertiesList, propsPropertiesList);

        js = (JavascriptExecutor) driver;
        webElement = driver.findElement(By.xpath(labelXpath));
        js.executeScript("arguments[0].setAttribute('id', '" + labelId + "')", webElement);

        paddingLeft = commonUtils.getCSSValue(label, "padding-left");
        labelFontSize = commonUtils.getCSSValue(label, "font-size");
        labelLineHeight = commonUtils.getCSSValue(label, "line-height");
        isPaddingLeft = commonUtils.assertValue(paddingLeft, expPaddingLeft, "Padding-left between " + elemType + " and its label is not as per spec");
        isLabelFontSize = commonUtils.assertCSSProperties("font-size", labelFontSize, expLabelfontSize);
        if (!isLabelFontSize) {
            log.info("Label font size of " + elemType + "is not as per spec, actual " + labelFontSize);
        }
        islabelLineHeight = commonUtils.assertValue(labelLineHeight, "18px", "Label line height of " + elemType + "is not as per spec");

        Assert.assertTrue(isPaddingLeft && isLabelFontSize && islabelLineHeight);
    }

    @DataProvider(name = "Radio Buttons Unselected Selected And Disabled States - Div Test Data")
    public Object[][] radioButtonsUnselectedSelectedDisabledStatesTestData() {
        return new Object[][]{
                {"selected", "true", "false", "0px", "//div[1]", "selected-radio-div", inputsPgObj.selectedRadioBtn},
                {"disabled-unselected", "false", "true", "0px", "//div[1]", "disabled-unselected-radio-div", inputsPgObj.disabledUnselectedRadioBtn},
                {"unselected", "false", "false", "0px", "//div[1]", "unselected-radio-div", inputsPgObj.unselectedRadioBtn},
                {"disabled-selected", "true", "true", "0px", "//div[1]", "disabled-selected-radio-div", inputsPgObj.disabledSelectedRadioBtn}
        };
    }

    @Test(testName = "Radio Buttons Unselected Selected And Disabled States - Div Test", dataProvider = "Radio Buttons Unselected Selected And Disabled States - Div Test Data", groups = {"desktop-regression"})
    private void radioButtonsUnselectedSelectedDisabledRadioDivTest(String elemType, String checkedValue, String disabledValue, String expMarginBottom, String divXpath, String divId, By elem) throws Exception {
        String[] detailsPropertiesList = new String[]{"elementId", "radio-target", "componentName", "RadioCheckGroup"};
        String[] propsPropertiesList = new String[]{"id", elemType, "inputType", "radio", "legendText", "radio1", "options", "[{value: 0, label: 'coffee',checked: " + checkedValue + ",disabled: " + disabledValue + "},{value: 1, label: 'tea'}]", "changeHandler", "function () {}"};
        setConfigAndLaunch(detailsPropertiesList, propsPropertiesList);

        js = (JavascriptExecutor) driver;
        webElement = driver.findElement(By.xpath(divXpath));
        js.executeScript("arguments[0].setAttribute('id', '" + divId + "')", webElement);

        marginBottom = commonUtils.getCSSValue(elem, "margin-bottom");
        isMarginBottom = commonUtils.assertValue(marginBottom, expMarginBottom, "Margin-Bottom of " + elemType + " is not as per spec");
        Assert.assertTrue(isMarginBottom);
    }

    /***************
     * Mobile Tests
     ***************/
    //Fancy - Text Input
    @Test(testName = "Mobile: Verify Single Line Text Input Wi/Wo Values", dataProvider = "Single Line Text Input Wi/Wo Values Test Data", groups = "bug-mobile-regression")
    //will enable this test when jason adds the 'value' prop
    private void singleLineTextInputWiWoValuesMobileTest(String type, By element, String[] expColor, String[] expFontSize) throws Exception {
        String[] detailsPropertiesList = new String[]{"elementId", "text-input-target", "componentName", "TextInput"};
        String[] propsPropertiesList = new String[]{"id", "sl-text-input", "labelText", "Last Name", "inputType", "default", "fancy", "true", "placeholder", "Last Name"};
        setConfigAndLaunch(detailsPropertiesList, propsPropertiesList, "mobile");

        fontSize = commonUtils.getCSSValue(element, "font-size", "mobile");
        isFontSize = commonUtils.assertCSSProperties("font-size", fontSize, expFontSize);
        if (!isFontSize) {
            log.info("font-size for " + type + " is not as per the spec, actual: " + fontSize);
        }
        color = commonUtils.getCSSValue(element, "color", "mobile");
        isColor = commonUtils.assertCSSProperties("color", color, expColor);
        if (!isColor) {
            log.info("color for " + type + " is not as per the spec, actual: " + color);
        }
        Assert.assertTrue(isFontSize && isColor);
    }

    //Fancy - Inputs (Single line)
    @Test(testName = "Mobile: Fancy - Verify Single Line Text Input - Default/Error/ReadOnly", dataProvider = "Fancy - Single Line Text Input Default/Error/ReadOnly Test Data", groups = "mobile-regression")
    private void fancySingleLineTextInputDefaultErrorReadOnlyMobileTest(String type, String id, By element, String[] expBorderBottoms, String[] expFontSize, String[] expColor, String[] expBorderStyle, String[] expPadding, String[] expFontFamily) throws Exception {
        String[] detailsPropertiesList = new String[]{"elementId", "text-input-target", "componentName", "TextInput"};
        String[] propsPropertiesList = new String[]{"id", id, "labelText", "Last Name", "inputState", type, "fancy", "true", "placeholder", "Last Name", "changeHandler", "function () {}", "infoMessage", "This is an info message", "errorMessage", "This is an error message'"};
        setConfigAndLaunch(detailsPropertiesList, propsPropertiesList, "mobile");

        fontSize = commonUtils.getCSSValue(element, "font-size", "mobile");
        color = commonUtils.getCSSValue(element, "color", "mobile");
        fontFamily = commonUtils.getCSSValue(element, "font-family", "mobile");

        isFontSize = commonUtils.assertCSSProperties("font-size", fontSize, expFontSize);
        isColor = commonUtils.assertCSSProperties("color", color, expColor);
        isFontFamily = commonUtils.assertCSSProperties("font-family", fontFamily, expFontFamily);

        if (!isFontSize) {
            log.info("font-size for Fancy - Single Line Text Input - " + type + " is not as per the spec, actual: " + fontSize);
        }
        if (!isColor) {
            log.info("color for Fancy - Single Line Text Input - " + type + " is not as per the spec, actual: " + color);
        }
        if (!isFontFamily) {
            log.info("font-family for Fancy - Single Line Text Input - " + type + " is not as per the spec, actual: " + fontFamily);
        }

        Assert.assertTrue(isFontSize && isColor && isFontFamily);

        for (int i = 0; i < borderStyles.length; i++) {
            borderStyle = commonUtils.getCSSValue(element, borderStyles[i], "mobile");
            isBorderStyle = commonUtils.assertValue(borderStyle, expBorderStyle[i], borderStyles[i] + " for fancy - Single Line Text - " + type + "is not as per the spec");
            Assert.assertTrue(isBorderStyle);
        }

        for (int i = 0; i < paddings.length; i++) {
            padding = commonUtils.getCSSValue(element, paddings[i], "mobile");
            isPadding = commonUtils.assertValue(padding, expPadding[i], paddings[i] + " for fancy - Single Line Text - " + type + "is not as per the spec");
            Assert.assertTrue(isPadding);
        }
        testBorders("Fancy - Single Line Text Input - "+type+" ", borderBottom, borderBottoms, expBorderBottoms, element, "mobile");
    }

    //Fancy - Inputs (single line - disabled)
    @Test(testName = "Mobile: Fancy - Verify Single Line Text Input - Disabled", dataProvider = "Fancy - Single Line Text Input Disabled Test Data", groups = "mobile-regression")
    private void fancySingleLineTextInputDisabledMobileTest(By element, String[] expBorderBottoms, String[] expColor, String[] expBackgroundColor, String[] expFontFamily) throws Exception {
        String[] detailsPropertiesList = new String[]{"elementId", "text-input-target", "componentName", "TextInput"};
        String[] propsPropertiesList = new String[]{"id", "sl-text-input-disabled", "labelText", "Last Name", "inputState", "disabled", "fancy", "true", "placeholder", "Last Name", "changeHandler", "function () {}", "infoMessage", "This is an info message", "errorMessage", "This is an error message'"};
        setConfigAndLaunch(detailsPropertiesList, propsPropertiesList, "mobile");

        color = commonUtils.getCSSValue(element, "color", "mobile");
        backgroundColor = commonUtils.getCSSValue(element, "background-color", "mobile");
        fontFamily = commonUtils.getCSSValue(element, "font-family", "mobile");

        isColor = commonUtils.assertCSSProperties("color", color, expColor);
        isBackgroundColor = commonUtils.assertCSSProperties("background-color", backgroundColor, expBackgroundColor);
        isFontFamily = commonUtils.assertCSSProperties("font-family", fontFamily, expFontFamily);

        if (!isColor) {
            log.info("color for Fancy - Single Line Text Input Disabled is not as per the spec, actual: " + color);
        }
        if (!isBackgroundColor) {
            log.info("background-color for Fancy - Single Line Text Input Disabled is not as per the spec, actual: " + backgroundColor);
        }
        if (!isFontFamily) {
            log.info("font-family for Fancy - Single Line Text Input Disabled is not as per the spec, actual: " + fontFamily);
        }

        Assert.assertTrue(isColor && isBackgroundColor && isFontFamily);
        testBorders("Fancy - Single Line Text Input - Disabled", borderBottom, borderBottoms, expBorderBottoms, element, "mobile");
    }

    @Test(testName = "Mobile: Verify Single Line Text Input - Text Label", dataProvider = "Single Line Text Input - Text Label Test Data", groups = {"mobile-regression"})
    private void singleLineTextInputInputMobileTest(String inputState, String id, By element, String type, String[] expFontSize, String[] expColor) throws Exception {
        String[] detailsPropertiesList = new String[]{"elementId", "text-input-target", "componentName", "TextInput"};
        String[] propsPropertiesList = new String[]{"id", id, "labelText", "Last Name", "inputState", inputState, "fancy", type, "placeholder", "Last Name", "changeHandler", "function () {}", "infoMessage", "This is an info message", "errorMessage", "This is an error message'"};
        setConfigAndLaunch(detailsPropertiesList, propsPropertiesList, "mobile");

        fontSize = commonUtils.getCSSValue(element, "font-size", "mobile");
        isFontSize = commonUtils.assertCSSProperties("font-size", fontSize, expFontSize);
        if (!isFontSize) {
            log.info("font-size for " + inputState + " is not as per the spec, actual: " + fontSize);
        }
        color = commonUtils.getCSSValue(element, "color", "mobile");
        isColor = commonUtils.assertCSSProperties("color", color, expColor);
        if (!isColor) {
            log.info("color for " + inputState + " is not as per the spec, actual: " + color);
        }
        Assert.assertTrue(isFontSize && isColor);
    }

    @Test(testName = "Mobile: Fancy - Verify Single Line Text Input - underline", dataProvider = "Fancy - Single Line Text Input - underline Test Data", groups = {"mobile-regression"})
    private void singleLineTextInputUnderlineMobileTest(String inputState, String underlineElementType, By underlineElement, String[] expUnderlineBackgroundColor, String expDisplay, String[] expUnderlineHeight, String[] expUnderlineTrasitionDelay, String[] expUnderlineTrasitionDuration, String expUnderlineTransitionProp, String expUnderlineTransitionTimingFunc) throws Exception {
        String[] detailsPropertiesList = new String[]{"elementId", "text-input-target", "componentName", "TextInput"};
        String[] propsPropertiesList = new String[]{"id", underlineElementType, "labelText", "First Name", "inputState", inputState, "fancy", "true", "placeholder", "First Name", "changeHandler", "function () {}", "infoMessage", "This is an info message", "errorMessage", "This is an error message'"};
        setConfigAndLaunch(detailsPropertiesList, propsPropertiesList, "mobile");

        backgroundColor = commonUtils.getCSSValue(underlineElement, "background-color", "mobile");
        isBackgroundColor = commonUtils.assertCSSProperties("background-color", backgroundColor, expUnderlineBackgroundColor);
        if (!isBackgroundColor) {
            log.info("background-color for " + underlineElementType + " is not as per the spec, actual: " + backgroundColor);
        }
        display = commonUtils.getCSSValue(underlineElement, "display", "mobile");
        isDisplay = commonUtils.assertValue(display, expDisplay, "underline color for '" + underlineElementType + "' is not as per the spec");
        height = commonUtils.getCSSValue(underlineElement, "height", "mobile");
        isHeight = commonUtils.assertCSSProperties("height", height, expUnderlineHeight);
        if (!isHeight) {
            log.info("height for " + underlineElementType + " is not as per the spec, actual: " + height);
        }
        transitionDelay = commonUtils.getCSSValue(underlineElement, "transition-delay", "mobile");
        isTransitionDelay = commonUtils.assertCSSProperties("transitionDelay", transitionDelay, expUnderlineTrasitionDelay);
        if (!isTransitionDelay) {
            log.info("transitionDelay for " + underlineElementType + " is not as per the spec, actual: " + transitionDelay);
        }
        transitionDuration = commonUtils.getCSSValue(underlineElement, "transition-duration", "mobile");
        isTransitionDuration = commonUtils.assertCSSProperties("transitionDuration", transitionDuration, expUnderlineTrasitionDuration);
        if (!isTransitionDuration) {
            log.info("transitionDuration for " + underlineElementType + " is not as per the spec, actual: " + transitionDuration);
        }
        transitionProp = commonUtils.getCSSValue(underlineElement, "transition-property", "mobile");
        isTransitionProp = commonUtils.assertValue(transitionProp, expUnderlineTransitionProp, "'" + underlineElementType + "' :for Single Line Input - Focus state is not as per the spec");
        trainsitionTimingFunc = commonUtils.getCSSValue(underlineElement, "transition-timing-function", "mobile");
        isTransitionTimingFunc = commonUtils.assertValue(trainsitionTimingFunc, expUnderlineTransitionTimingFunc, "'" + underlineElementType + "' :for Single Line Input - Focus state is not as per the spec");
        Assert.assertTrue(isBackgroundColor && isDisplay && isHeight && isTransitionDelay && isTransitionDuration && isTransitionProp && isTransitionTimingFunc);
    }

    //Basic - Text Input
    @Test(testName = "Mobile: Basic - Verify Single Line Text Input - Active/Error/Disabled", dataProvider = "Basic - Single Line Text Input - Active/Error/Disabled Test Data", groups = "mobile-regression")
    private void basicSingleLineTextInputMobileTest(String type, String inputState, String id, By element, String[] expBgColor, String expMarginTop, String expPaddingRight, String expPaddingLeft, String[] expHeight, String expBorderRad, String[] expFontSize, String expLineHt) throws Exception {
        String[] detailsPropertiesList = new String[]{"elementId", "text-input-target", "componentName", "TextInput"};
        String[] propsPropertiesList = new String[]{"id", id, "labelText", "Last Name", "inputState", inputState, "fancy", "false", "placeholder", "Last Name", "changeHandler", "function () {}", "infoMessage", "This is an info message", "errorMessage", "This is an error message'"};
        setConfigAndLaunch(detailsPropertiesList, propsPropertiesList, "mobile");

        bgColor = commonUtils.getCSSValue(element, "background-color", "mobile");
        marginTop = commonUtils.getCSSValue(element, "margin-top", "mobile");
        paddingRight = commonUtils.getCSSValue(element, "padding-right", "mobile");
        paddingLeft = commonUtils.getCSSValue(element, "padding-left", "mobile");
        height = commonUtils.getCSSValue(element, "height", "mobile");
        fontSize = commonUtils.getCSSValue(element, "font-size", "mobile");
        lineHeight = commonUtils.getCSSValue(element, "line-height", "mobile");
        for (String cssProperty : borderRadii) {
            borderRadius = commonUtils.getCSSValue(element, cssProperty, "mobile");
            isBorderRadius = commonUtils.assertValue(borderRadius, expBorderRad, "Border radius " + cssProperty + " of Input-Basic Single Line (" + type + ") field is not as per spec ");
            Assert.assertTrue(isBorderRadius);
        }

        isBackgroundColor = commonUtils.assertCSSProperties("background-color", bgColor, expBgColor);
        if (!isBackgroundColor) {
            log.info("Background color of Input-Basic Single Line (" + type + ") field is not as per spec exp, actual " + bgColor);
        }
        isMarginTop = commonUtils.assertValue(marginTop, expMarginTop, "margin-top of Input-Basic Single Line (Active) field is not as per spec");
        isPaddingRight = commonUtils.assertValue(paddingRight, expPaddingRight, "Padding right of Input-Basic Single Line (Active) field is not as per spec");
        isPaddingLeft = commonUtils.assertValue(paddingLeft, expPaddingLeft, "Padding left of Input-Basic Single Line (Active) field is not as per spec");
        isHeight = commonUtils.assertCSSProperties("height", height, expHeight);
        if (!isHeight) {
            log.info("Box height of Input-Basic Single Line (" + type + ") field is not as per spec, actual " + height);
        }
        isFontSize = commonUtils.assertCSSProperties("font-size", fontSize, expFontSize);
        if (!isFontSize) {
            log.info("Font Size of Input-Basic Single Line (" + type + ") field is not as per spec, actual " + fontSize);
        }
        isLineHeight = commonUtils.assertValue(lineHeight, expLineHt, "Line height of Input-Basic Single Line (" + type + ") field is not as per spec");

        Assert.assertTrue(isBackgroundColor && isMarginTop && isPaddingRight && isPaddingLeft && isHeight && isFontSize && isLineHeight);
    }

    @Test(testName = "Mobile: Basic - Verify Single Line Text Input - Active/Error/Disabled Borders", dataProvider = "Basic - Single Line Text Input - Active/Error/Disabled - Borders Test Data", groups = "mobile-regression")
    private void basicSingleLineTextInputBordersMobileTest(String type, String inputState, String id, By element, String expBorderWidth, String expBorderStyle, String[] expBorderColor) throws Exception {
        String[] detailsPropertiesList = new String[]{"elementId", "text-input-target", "componentName", "TextInput"};
        String[] propsPropertiesList = new String[]{"id", id, "labelText", "Last Name", "inputState", inputState, "fancy", "false", "placeholder", "Last Name", "changeHandler", "function () {}", "infoMessage", "This is an info message", "errorMessage", "This is an error message'"};
        setConfigAndLaunch(detailsPropertiesList, propsPropertiesList, "mobile");

        for (String cssProperty : borderWidths) {
            borderWidth = commonUtils.getCSSValue(element, cssProperty, "mobile");
            isBorderWidth = commonUtils.assertValue(borderWidth, expBorderWidth, "Border width " + cssProperty + " of Input-Basic Single Line (" + type + ") field is not as per spec");
            Assert.assertTrue(isBorderWidth);
        }
        for (String cssProperty : borderStyles) {
            borderStyle = commonUtils.getCSSValue(element, cssProperty, "mobile");
            isBorderStyle = commonUtils.assertValue(borderStyle, expBorderStyle, "Border style " + cssProperty + " of Input-Basic Single Line (" + type + ") field is not as per spec");
            Assert.assertTrue(isBorderStyle);
        }
        for (String cssProperty : borderColors) {
            borderColor = commonUtils.getCSSValue(element, cssProperty, "mobile");
            isBorderColor = commonUtils.assertCSSProperties(cssProperty, borderColor, expBorderColor);
            if (!isBorderColor) {
                log.info("Border color " + cssProperty + " of Input-Basic Single Line (" + type + ") field is not as per spec, actual: " + borderColor);
            }
            Assert.assertTrue(isBorderColor);
        }
    }

    @Test(testName = "Mobile: Basic - Verify Single Line Text Input - Active/Error/Disabled Label", dataProvider = "Basic - Single Line Text Input - Active/Error/Disabled - Label Test Data", groups = "mobile-regression")
    private void basicSingleLineTextInputLabelMobileTest(String type, String inputState, String labelId, By elementForLabel, String id, By element, String[] expLabelColor, String expLabelFontSize) throws Exception {
        String[] detailsPropertiesList = new String[]{"elementId", "text-input-target", "componentName", "TextInput"};
        String[] propsPropertiesList = new String[]{"id", id, "labelText", "Last Name", "inputState", inputState, "fancy", "false", "placeholder", "Last Name", "changeHandler", "function () {}", "infoMessage", "This is an info message", "errorMessage", "This is an error message'"};
        setConfigAndLaunch(detailsPropertiesList, propsPropertiesList, "mobile");

        js = (JavascriptExecutor) appium;
        webElement = appium.findElement(elementForLabel);
        js.executeScript("arguments[0].setAttribute('id', '" + labelId + "')", webElement);

        color = commonUtils.getCSSValue(elementForLabel, "color", "mobile");
        fontSize = commonUtils.getCSSValue(elementForLabel, "font-size", "mobile");
        lineHeight = commonUtils.getCSSValue(elementForLabel, "line-height", "mobile");
        isFontSize = commonUtils.assertValue(fontSize, expLabelFontSize, "Label font size of Input-Basic Single Line (" + type + ") label is not as per spec");
        isColor = commonUtils.assertCSSProperties("color", color, expLabelColor);
        if (!isColor) {
            log.info("Label color of Input-Basic Single Line (" + type + ") is not as per spec, actual:" + color);
        }
        isLineHeight = commonUtils.assertValue(lineHeight, "16px", "Line-height of Input-Basic Single Line (" + type + ") label is not as per spec");
        isLabelFor = commonUtils.checkLabelForVal(elementForLabel, element, "mobile");
        Assert.assertTrue(isColor && isFontSize && isLabelFor);
    }

    @Test(testName = "Mobile: Verify Single Line Text Input Info/Error Message Test", dataProvider = "Single Line Text Input Info/Error Message Test Data", groups = "mobile-regression")
    private void singleLineTextInputErrorMessageMobileTest(String inputType, String inputState, String id, By element, String inputTypeValue, String[] expPaddingTop, String[] expColor, String[] expFontSize, String[] expLineHeight) throws Exception {
        String[] detailsPropertiesList = new String[]{"elementId", "text-input-target", "componentName", "TextInput"};
        String[] propsPropertiesList = new String[]{"id", id, "labelText", "Password", "inputState", inputState, "placeholder", "Password", "fancy", inputTypeValue, "changeHandler", "function () {}", "infoMessage", "This is an info message", "errorMessage", "This is an error message'"};
        setConfigAndLaunch(detailsPropertiesList, propsPropertiesList, "mobile");

        js = (JavascriptExecutor) appium;
        webElement = appium.findElement(element);
        js.executeScript("arguments[0].setAttribute('id', '" + id + "')", webElement);

        paddingTop = commonUtils.getCSSValue(element, "padding-top", "mobile");
        isPaddingTop = commonUtils.assertCSSProperties("padding-top", paddingTop, expPaddingTop);
        if (!isPaddingTop) {
            log.info("padding-top for " + inputType + " is not as per the spec, actual: " + paddingTop);
        }
        color = commonUtils.getCSSValue(element, "color", "mobile");
        isColor = commonUtils.assertCSSProperties("color", color, expColor);
        if (!isColor) {
            log.info("color for " + inputType + " is not as per the spec, actual: " + color);
        }
        fontSize = commonUtils.getCSSValue(element, "font-size", "mobile");
        isFontSize = commonUtils.assertCSSProperties("font-size", fontSize, expFontSize);
        if (!isFontSize) {
            log.info("font-size for " + inputType + " is not as per the spec, actual: " + fontSize);
        }
        lineHeight = commonUtils.getCSSValue(element, "line-height", "mobile");
        isLineHeight = commonUtils.assertCSSProperties("line-height", lineHeight, expLineHeight);
        if (!isLineHeight) {
            log.info("line-height for " + inputType + " is not as per the spec, actual: " + lineHeight);
        }
        Assert.assertTrue(isPaddingTop && isColor && isFontSize && isLineHeight);
    }

    //Fancy - Password Input
    @Test(testName = "Mobile: Fancy - Verify Password Input - Label,and Msg", dataProvider = "Fancy - Password Input - Label,and Msg Test Data", groups = "mobile-regression")
    private void fancyPasswordInputOtherFieldsShowMobileTest(String inputState, String type, String elemId, By elem, By labelClass, String labelId, By label, String infoMsgId, By infoMsg, String errorMsgId, By errorMsg, String expLabelFontSize, String[] expLabelFontColor) throws Exception {
        String[] detailsPropertiesList = new String[]{"elementId", "password-input-target", "componentName", "TextInput"};
        String[] propsPropertiesList = new String[]{"password", "true", "fancy", "true", "id", elemId, "labelText", "Password", "inputState", inputState, "placeholder", "Password", "changeHandler", "function () {}", "showText", "show", "hideText", "hide", "isNotVisibleMsg", "Password is hidden", "isVisibleMsg", "Password is visible", "infoMessage", "This is an info message", "errorMessage", "This is an error message'"};
        setConfigAndLaunch(detailsPropertiesList, propsPropertiesList, "mobile");

        js = (JavascriptExecutor) appium;
        webElement = appium.findElement(labelClass);
        js.executeScript("arguments[0].setAttribute('id','" + labelId + "')", webElement);

        webElement = appium.findElement(inputsPgObj.textInputInfoMessageClass);
        js.executeScript("arguments[0].setAttribute('id','" + infoMsgId + "')", webElement);

        labelFontSize = commonUtils.getCSSValue(label, "font-size", "mobile");
        labelColor = commonUtils.getCSSValue(label, "color", "mobile");
        isLabelFontSize = commonUtils.assertValue(labelFontSize, expLabelFontSize, "The font size of " + type + " Label is not as per spec");
        isLabelColor = commonUtils.assertCSSProperties("color", labelColor, expLabelFontColor);
        if (!isLabelColor) {
            log.info("label color of " + type + " is not as per spec,actual" + labelColor);
        }
        isLabelFor = commonUtils.checkLabelForVal(label, elem, "mobile");
        if (!isLabelFor) {
            log.info("the password label is not mapped correctly to the password field  of " + type);
        }

        fontSize = commonUtils.getCSSValue(infoMsg, "font-size", "mobile");
        isFontSize = commonUtils.assertValue(fontSize, "12px", "Info Msg font size of " + type + " is not as per spec");
        paddingTop = commonUtils.getCSSValue(infoMsg, "padding-top", "mobile");
        isPaddingTop = commonUtils.assertValue(paddingTop, "3px", "Info Msg padding top of " + type + " is not as per spec");
        color = commonUtils.getCSSValue(infoMsg, "color", "mobile");
        isColor = commonUtils.assertCSSProperties("color", color, new String[]{commonUtils.hex2Rgb("#6A7070"), commonUtils.hex2RgbWithoutTransparency("#6A7070")});
        if (!isColor) {
            log.info("Font Color of Info Msg of " + type + " is not as per spec, actual " + color);
        }
        Assert.assertTrue(isFontSize && isColor && isPaddingTop);

        if (inputState.equals("error")) {
            webElement = appium.findElement(inputsPgObj.textInputErrorMessageClass);
            js.executeScript("arguments[0].setAttribute('id','" + errorMsgId + "')", webElement);
            Thread.sleep(500);
            fontSize = commonUtils.getCSSValue(errorMsg, "font-size", "mobile");
            isFontSize = commonUtils.assertValue(fontSize, "12px", "error Msg font size of " + type + " is not as per spec");
            paddingTop = commonUtils.getCSSValue(infoMsg, "padding-top", "mobile");
            isPaddingTop = commonUtils.assertValue(paddingTop, "3px", "Error Msg padding top of " + type + " is not as per spec");
            color = commonUtils.getCSSValue(errorMsg, "color", "mobile");
            isColor = commonUtils.assertCSSProperties("color", color, new String[]{commonUtils.hex2Rgb("#DB0020"), commonUtils.hex2RgbWithoutTransparency("#DB0020")});
            if (!isColor) {
                log.info("Font Color of error Msg of " + type + " is not as per spec, actual " + color);
            }
            Assert.assertTrue(isFontSize && isColor && isPaddingTop);
        }
        Assert.assertTrue(isLabelFontSize && isLabelColor && isLabelFor);
    }

    @Test(testName = "Mobile: Fancy - Verify Password Input - Show Button Test", dataProvider = "Fancy - Password Input - Show Button Test data", groups = "mobile-regression")
    private void fancyPasswordInputShowBtnMobileTest(String inputState, String id, By showbutton, String[] expShowBtnColor, String expMarginTop) throws Exception {
        String[] detailsPropertiesList = new String[]{"elementId", "password-input-target", "componentName", "TextInput"};
        String[] propsPropertiesList = new String[]{"password", "true", "fancy", "true", "id", id, "labelText", "Password", "inputState", inputState, "placeholder", "Password", "changeHandler", "function () {}", "showText", "show", "hideText", "hide", "isNotVisibleMsg", "Password is hidden", "isVisibleMsg", "Password is visible", "infoMessage", "This is an info message", "errorMessage", "This is an error message'"};
        setConfigAndLaunch(detailsPropertiesList, propsPropertiesList, "mobile");

        Thread.sleep(500);
        showBtnColor = commonUtils.getCSSValue(showbutton, "color", "mobile");
        marginTop = commonUtils.getCSSValue(showbutton, "margin-top", "mobile");
        showBtnFloat = commonUtils.getCSSValue(showbutton, "float", "mobile");

        isShowBtnColor = commonUtils.assertCSSProperties("color", showBtnColor, expShowBtnColor);
        if (!isShowBtnColor) {
            log.info("Show Button color  of " + id + " is not as per spec,actual " + showBtnColor);
        }
        isMarginTop = commonUtils.assertValue(marginTop, expMarginTop, "The top margin value  of " + id + "  show btn is not as per specs");
        isShowBtnFloat = commonUtils.assertValue(showBtnFloat, "right", "The show btn  of " + id + " is not on aligned on the right side");
        for (String cssProperty : paddings) {
            String cssPropertyType = cssProperty;
            cssProperty = commonUtils.getCSSValue(showbutton, cssProperty, "mobile");
            isCSSProperty = commonUtils.assertValue(cssProperty, "2px", "'" + cssPropertyType + "' of " + id + " is not as per spec");
            Assert.assertTrue(isCSSProperty);
        }
        Assert.assertTrue(isShowBtnColor && isMarginTop && isShowBtnFloat);
    }

    @Test(testName = "Mobile: Fancy - Verify Password Input - Input Box Test", dataProvider = "Fancy - Password Input - Input Box Test Data", groups = "mobile-regression")
    private void fancyPasswordInputBoxMobileTest(String inputState, String id, By elem, String expPaddingBottom, String expPaddingTop, String expBorderBottom, String[] expBorderBtmColor, String expBorderBtmStyle) throws Exception {
        String[] detailsPropertiesList = new String[]{"elementId", "password-input-target", "componentName", "TextInput"};
        String[] propsPropertiesList = new String[]{"password", "true", "fancy", "true", "id", id, "labelText", "Password", "inputState", inputState, "placeholder", "Password", "changeHandler", "function () {}", "showText", "show", "hideText", "hide", "isNotVisibleMsg", "Password is hidden", "isVisibleMsg", "Password is visible", "infoMessage", "This is an info message", "errorMessage", "This is an error message'"};
        setConfigAndLaunch(detailsPropertiesList, propsPropertiesList, "mobile");

        paddingBottom = commonUtils.getCSSValue(elem, "padding-bottom", "mobile");
        paddingTop = commonUtils.getCSSValue(elem, "padding-top", "mobile");
        isPaddingBottom = commonUtils.assertValue(paddingBottom, expPaddingBottom, "The padding-bottom of " + id + " is not as per spec");
        isPaddingTop = commonUtils.assertValue(paddingTop, expPaddingTop, "The padding-top of " + id + " is not as per spec");

        borderBottom = commonUtils.getCSSValue(elem, "border-bottom-width", "mobile");
        borderBottomColor = commonUtils.getCSSValue(elem, "border-bottom-color", "mobile");
        borderBottomStyle = commonUtils.getCSSValue(elem, "border-bottom-style", "mobile");
        isBorderBottom = commonUtils.assertValue(borderBottom, expBorderBottom, "The bottom border width of " + id + "  is not as per spec");
        if (!id.equals("password-input-fancy-readOnly")) {
            isBorderBottomColor = commonUtils.assertCSSProperties("border-bottom-color", borderBottomColor, expBorderBtmColor);
            if (!isBorderBottomColor) {
                log.info("Bottom border color of " + id + " is not as per spec,actual " + borderBottomColor);
            }
        }
        isBorderBottomStyle = commonUtils.assertValue(borderBottomStyle, expBorderBtmStyle, "The bottom border style of " + id + " is not as per spec");

        Assert.assertTrue(isPaddingBottom && isPaddingTop && isBorderBottom && isBorderBottomColor && isBorderBottomStyle);
    }

    @Test(testName = "Mobile: Fancy - Verify Password Input - Underline", dataProvider = "Fancy - Password Input - Underline Test Data", groups = "mobile-regression")
    private void fancyPasswordInputUnderlineMobileTest(String inputState, String id, By elem, String underlineElemId, By underlineElementClass, By underlineElement, String expUnderlineHeight, String[] expUnderlineColor) throws Exception {
        String[] detailsPropertiesList = new String[]{"elementId", "password-input-target", "componentName", "TextInput"};
        String[] propsPropertiesList = new String[]{"password", "true", "fancy", "true", "id", id, "labelText", "Password", "inputState", inputState, "placeholder", "Password", "changeHandler", "function () {}", "showText", "show", "hideText", "hide", "isNotVisibleMsg", "Password is hidden", "isVisibleMsg", "Password is visible", "infoMessage", "This is an info message", "errorMessage", "This is an error message'"};
        setConfigAndLaunch(detailsPropertiesList, propsPropertiesList, "mobile");

        commonUtils.click(elem, "mobile");
        js = (JavascriptExecutor) appium;
        webElement = appium.findElement(underlineElementClass);
        js.executeScript("arguments[0].setAttribute('id','" + underlineElemId + "')", webElement);

        lineHeight = commonUtils.getCSSValue(underlineElement, "height", "mobile");
        color = commonUtils.getCSSValue(underlineElement, "background-color", "mobile");

        isHeight = commonUtils.assertValue(lineHeight, expUnderlineHeight, "The underline height of " + id + " is not as per spec");
        isColor = commonUtils.assertCSSProperties("background-color", color, expUnderlineColor);
        if (!isColor) {
            log.info("Underline color  of " + id + " is not as per spec,actual " + color + " at width " + width);
        }
        Assert.assertTrue(isHeight && isColor);
    }

    //Basic - Password Input
    @Test(testName = "Mobile: Basic - Verify Password Input - Label,and Msg", dataProvider = "Basic - Password Input - Label,and Msg Test Data", groups = "mobile-regression")
    private void basicPasswordInputOtherFieldsShowMobileTest(String inputState, String type, String elemId, By elem, By labelClass, String labelId, By label, String infoMsgId, By infoMsg, String errorMsgId, By errorMsg, String expLabelFontSize, String[] expLabelFontColor) throws Exception {
        String[] detailsPropertiesList = new String[]{"elementId", "password-input-target", "componentName", "TextInput"};
        String[] propsPropertiesList = new String[]{"password", "true", "fancy", "false", "id", elemId, "labelText", "Password", "inputState", inputState, "placeholder", "Password", "changeHandler", "function () {}", "showText", "show", "hideText", "hide", "infoMessage", "This is an info message", "errorMessage", "This is an error message", "isNotVisibleMsg", "Password is hidden", "isVisibleMsg", "Password is visible'"};
        setConfigAndLaunch(detailsPropertiesList, propsPropertiesList, "mobile");

        js = (JavascriptExecutor) appium;
        webElement = appium.findElement(labelClass);
        js.executeScript("arguments[0].setAttribute('id','" + labelId + "')", webElement);

        webElement = appium.findElement(inputsPgObj.textInputInfoMessageClass);
        js.executeScript("arguments[0].setAttribute('id','" + infoMsgId + "')", webElement);

        labelFontSize = commonUtils.getCSSValue(label, "font-size", "mobile");
        labelColor = commonUtils.getCSSValue(label, "color", "mobile");
        isLabelFontSize = commonUtils.assertValue(labelFontSize, expLabelFontSize, "The font size of " + type + " Label is not as per spec");
        isLabelColor = commonUtils.assertCSSProperties("color", labelColor, expLabelFontColor);
        if (!isLabelColor) {
            log.info("label color of " + type + " is not as per spec,actual" + labelColor);
        }
        isLabelFor = commonUtils.checkLabelForVal(label, elem, "mobile");
        if (!isLabelFor) {
            log.info("the password label is not mapped correctly to the password field  of " + type);
        }

        fontSize = commonUtils.getCSSValue(infoMsg, "font-size", "mobile");
        isFontSize = commonUtils.assertValue(fontSize, "12px", "Info Msg font size of " + type + " is not as per spec");
        color = commonUtils.getCSSValue(infoMsg, "color", "mobile");
        isColor = commonUtils.assertCSSProperties("color", color, new String[]{commonUtils.hex2Rgb("#6A7070"), commonUtils.hex2RgbWithoutTransparency("#6A7070")});
        if (!isColor) {
            log.info("Font Color of Info Msg of " + type + " is not as per spec, actual " + color);
        }
        Assert.assertTrue(isFontSize && isColor);

        if (inputState.equals("error")) {
            webElement = appium.findElement(inputsPgObj.textInputErrorMessageClass);
            js.executeScript("arguments[0].setAttribute('id','" + errorMsgId + "')", webElement);
            Thread.sleep(500);
            fontSize = commonUtils.getCSSValue(errorMsg, "font-size", "mobile");
            isFontSize = commonUtils.assertValue(fontSize, "12px", "error Msg font size of " + type + " is not as per spec");
            color = commonUtils.getCSSValue(errorMsg, "color", "mobile");
            isColor = commonUtils.assertCSSProperties("color", color, new String[]{commonUtils.hex2Rgb("#DB0020"), commonUtils.hex2RgbWithoutTransparency("#DB0020")});
            if (!isColor) {
                log.info("Font Color of error Msg of " + type + " is not as per spec, actual " + color);
            }
            Assert.assertTrue(isFontSize && isColor);
        }
        Assert.assertTrue(isLabelFontSize && isLabelColor && isLabelFor);
    }

    @Test(testName = "Mobile: Basic - Verify Password Input - Show Button", dataProvider = "Basic - Password Input - Show Button Test data", groups = "mobile-regression")
    private void basicPasswordInputShowBtnMobileTest(String inputState, String id, By showbutton, String[] expShowBtnColor, String expMarginTop, String expMarginRight) throws Exception {
        String[] detailsPropertiesList = new String[]{"elementId", "password-input-target", "componentName", "TextInput"};
        String[] propsPropertiesList = new String[]{"password", "true", "fancy", "false", "id", id, "labelText", "Password", "inputState", inputState, "placeholder", "Password", "changeHandler", "function () {}", "showText", "show", "hideText", "hide", "infoMessage", "This is an info message", "errorMessage", "This is an error message", "isNotVisibleMsg", "Password is hidden", "isVisibleMsg", "Password is visible'"};
        setConfigAndLaunch(detailsPropertiesList, propsPropertiesList, "mobile");

        Thread.sleep(500);
        showBtnColor = commonUtils.getCSSValue(showbutton, "color", "mobile");
        marginTop = commonUtils.getCSSValue(showbutton, "margin-top", "mobile");
        marginRight = commonUtils.getCSSValue(showbutton, "margin-right", "mobile");
        showBtnFloat = commonUtils.getCSSValue(showbutton, "float", "mobile");

        isShowBtnColor = commonUtils.assertCSSProperties("color", showBtnColor, expShowBtnColor);
        if (!isShowBtnColor) {
            log.info("Show Button color  of " + id + " is not as per spec,actual " + showBtnColor);
        }
        isMarginTop = commonUtils.assertValue(marginTop, expMarginTop, "The top margin value  of " + id + "  show btn is not as per specs");
        isMarginRight = commonUtils.assertValue(marginRight, expMarginRight, "The margin-right value  of " + id + "  show btn is not as per specs");
        isShowBtnFloat = commonUtils.assertValue(showBtnFloat, "right", "The show btn  of " + id + " is not on aligned on the right side");
        for (String cssProperty : paddings) {
            String cssPropertyType = cssProperty;
            cssProperty = commonUtils.getCSSValue(showbutton, cssProperty, "mobile");
            isCSSProperty = commonUtils.assertValue(cssProperty, "2px", "'" + cssPropertyType + "' of " + id + " is not as per spec");
            Assert.assertTrue(isCSSProperty);
        }
        Assert.assertTrue(isShowBtnColor && isMarginTop && isMarginRight && isShowBtnFloat);
    }

    @Test(testName = "Mobile: Basic - Verify Password Input - Input Box", dataProvider = "Basic - Password Input - Input Box Test Data", groups = "mobile-regression")
    private void basicPasswordInputBoxMobileTest(String inputState, String id, By elem, String expPaddingLeftRight, String[] expHeight, String expFontSize, String[] expBgColor, String expMarginTop) throws Exception {
        String[] detailsPropertiesList = new String[]{"elementId", "password-input-target", "componentName", "TextInput"};
        String[] propsPropertiesList = new String[]{"password", "true", "fancy", "false", "id", id, "labelText", "Password", "inputState", inputState, "placeholder", "Password", "changeHandler", "function () {}", "showText", "show", "hideText", "hide", "infoMessage", "This is an info message", "errorMessage", "This is an error message", "isNotVisibleMsg", "Password is hidden", "isVisibleMsg", "Password is visible'"};
        setConfigAndLaunch(detailsPropertiesList, propsPropertiesList, "mobile");

        paddingLeft = commonUtils.getCSSValue(elem, "padding-left", "mobile");
        paddingRight = commonUtils.getCSSValue(elem, "padding-right", "mobile");
        height = commonUtils.getCSSValue(elem, "height", "mobile");
        fontSize = commonUtils.getCSSValue(elem, "font-size", "mobile");
        bgColor = commonUtils.getCSSValue(elem, "background-color", "mobile");
        marginTop = commonUtils.getCSSValue(elem, "margin-top", "mobile");

        isPaddingLeft = commonUtils.assertValue(paddingLeft, expPaddingLeftRight, "The padding-left of " + id + " is not as per spec");
        isPaddingRight = commonUtils.assertValue(paddingRight, expPaddingLeftRight, "The padding-right of " + id + " is not as per spec");
        isHeight = commonUtils.assertCSSProperties("height", height, expHeight);
        if (!isHeight) {
            log.info("The height of " + id + " is not as per spec, actual " + height);
        }
        isFontSize = commonUtils.assertValue(fontSize, expFontSize, "The font-size of " + id + " is not as per spec");
        isBgColor = commonUtils.assertCSSProperties("color", bgColor, expBgColor);
        if (!isBgColor) {
            log.info("BackGround Color of " + id + " is not as per spec, actual " + bgColor);
        }
        isMarginTop = commonUtils.assertValue(marginTop, expMarginTop, "The margin-top of " + id + " is not as per spec");

        Assert.assertTrue(isPaddingLeft && isPaddingRight && isHeight && isFontSize && isBgColor && isMarginTop);
    }

    //Multiline Text
    @Test(testName = "Mobile: MultiLine Input - Active States Test", dataProvider = "MultiLine Input Active Test Data", groups = "mobile-regression")
    private void multiLineInputBoxMobileTest(By element, String[] expFontSize, String[] expLineHeight, String[] expMarginTop, String[] expColor, String[] expBackgroundColor, String[] expPadding, String[] expBorderWidth, String[] expBorderStyle, String[] expBorderColor) throws Exception {
        String[] detailsPropertiesList = new String[]{"elementId", "multilinetext-target", "componentName", "MultiLineText"};
        String[] propsPropertiesList = new String[]{"id", "multiLine-text-input", "labelText", "Multi-line label", "inputState", "default", "placeholder", "Multilinetext placeholder", "changeHandler", "function () {}", "infoMessage", "This is an info message", "errorMessage", "This is an error message'"};
        setConfigAndLaunch(detailsPropertiesList, propsPropertiesList, "mobile");

        fontSize = commonUtils.getCSSValue(element, "font-size", "mobile");
        lineHeight = commonUtils.getCSSValue(element, "line-height", "mobile");
        marginTop = commonUtils.getCSSValue(element, "margin-top", "mobile");
        color = commonUtils.getCSSValue(element, "color", "mobile");
        backgroundColor = commonUtils.getCSSValue(element, "background-color", "mobile");

        isFontSize = commonUtils.assertCSSProperties("font-size", fontSize, expFontSize);
        isLineHeight = commonUtils.assertCSSProperties("line-height", lineHeight, expLineHeight);
        isMarginTop = commonUtils.assertCSSProperties("margin-top", marginTop, expMarginTop);
        isColor = commonUtils.assertCSSProperties("color", color, expColor);
        isBackgroundColor = commonUtils.assertCSSProperties("bg-color", backgroundColor, expBackgroundColor);

        if (!isFontSize) {
            log.info("font size for MultiLine Input is not as per the spec, actual: " + fontSize);
        }
        if (!isLineHeight) {
            log.info("Line Height for MultiLine Input is not as per the spec, actual: " + lineHeight);
        }
        if (!isMarginTop) {
            log.info("margin-top for MultiLine Input is not as per the spec, actual: " + marginTop);
        }
        if (!isColor) {
            log.info("color for for MultiLine Input is not as per the spec, actual: " + color);
        }
        if (!isBackgroundColor) {
            log.info("background-color for for MultiLine Input is not as per the spec, actual: " + backgroundColor);
        }
        Assert.assertTrue(isFontSize && isLineHeight && isMarginTop && isColor && isBackgroundColor);

        for (int i = 0; i < paddings.length; i++) {
            padding = commonUtils.getCSSValue(element, paddings[i], "mobile");
            isPadding = commonUtils.assertValue(padding, expPadding[i], paddings[i] + " for MultiLine Text is not as per the spec");
            Assert.assertTrue(isPadding);
        }

        for (int i = 0; i < borderStyles.length; i++) {
            borderStyle = commonUtils.getCSSValue(element, borderStyles[i], "mobile");
            isBorderStyle = commonUtils.assertValue(borderStyle, expBorderStyle[i], borderStyles[i] + " for MultiLine Text is not as per the spec");
            Assert.assertTrue(isBorderStyle);
        }

        for (int i = 0; i < borderWidths.length; i++) {
            borderWidth = commonUtils.getCSSValue(element, borderWidths[i], "mobile");
            isBorderWidth = commonUtils.assertValue(borderWidth, expBorderWidth[i], borderWidths[i] + " for MultiLine Text is not as per the spec");
            Assert.assertTrue(isBorderWidth);
        }

        for (int i = 0; i < borderRadii.length; i++) {
            borderRadius = commonUtils.getCSSValue(element, borderRadii[i],"mobile");
            isBorderRadius = commonUtils.assertValue(borderRadius, "3px", borderRadii[i] + " for MultiLine Text is not as per the spec");
            Assert.assertTrue(isBorderRadius);
        }

        for (String cssProperty : borderColors) {
            borderColor = commonUtils.getCSSValue(element, cssProperty, "mobile");
            isBorderColor = commonUtils.assertCSSProperties(cssProperty, borderColor, expBorderColor);
            if (!isBorderColor) {
                log.info("Border color " + cssProperty + " for MultiLine Text is not as per spec, actual " + borderColor);
            }
            Assert.assertTrue(isBorderColor);
        }
    }

    @Test(testName = "Mobile: MultiLine Input - Error States Test", dataProvider = "MultiLine Input Error Test Data", groups = "mobile-regression")
    private void multiLineInputBoxErrorMobileTest(By element, String[] expBackgroundColor, String[] expBorderColor) throws Exception {
        String[] detailsPropertiesList = new String[]{"elementId", "multilinetext-target", "componentName", "MultiLineText"};
        String[] propsPropertiesList = new String[]{"id", "multiLine-text-input-error", "labelText", "Multi-line label", "inputState", "error", "placeholder", "Multilinetext placeholder", "changeHandler", "function () {}", "infoMessage", "This is an info message", "errorMessage", "This is an error message'"};
        setConfigAndLaunch(detailsPropertiesList, propsPropertiesList, "mobile");

        backgroundColor = commonUtils.getCSSValue(element, "background-color", "mobile");
        isBackgroundColor = commonUtils.assertCSSProperties("background-color", backgroundColor, expBackgroundColor);
        if (!isBackgroundColor) {
            log.info("background-color for for MultiLine Input Error is not as per the spec, actual: " + backgroundColor);
        }
        Assert.assertTrue(isBackgroundColor);

        for (String cssProperty : borderColors) {
            borderColor = commonUtils.getCSSValue(element, cssProperty, "mobile");
            isBorderColor = commonUtils.assertCSSProperties(cssProperty, borderColor, expBorderColor);
            if (!isBorderColor) {
                log.info("Border color " + cssProperty + " for MultiLine Input Error is not as per spec, actual " + borderColor);
            }
            Assert.assertTrue(isBorderColor);
        }
    }

    @Test(testName = "Mobile: MultiLine Input - Disabled States Test", dataProvider = "MultiLine Input Disabled Test Data", groups = "mobile-regression")
    private void multiLineInputBoxDisabledMobileTest(By element, String[] expColor, String[] expBackgroundColor, String[] expBorderColor, String[] expOutlineStyle) throws Exception {
        String[] detailsPropertiesList = new String[]{"elementId", "multilinetext-target", "componentName", "MultiLineText"};
        String[] propsPropertiesList = new String[]{"id", "multiLine-text-input-disabled", "labelText", "Multi-line label", "inputState", "disabled", "placeholder", "Multilinetext placeholder", "changeHandler", "function () {}", "infoMessage", "This is an info message", "errorMessage", "This is an error message'"};
        setConfigAndLaunch(detailsPropertiesList, propsPropertiesList, "mobile");

        color = commonUtils.getCSSValue(element, "color", "mobile");
        backgroundColor = commonUtils.getCSSValue(element, "background-color", "mobile");
        outlineStyle = commonUtils.getCSSValue(element, "outline-style", "mobile");

        isColor = commonUtils.assertCSSProperties("color", color, expColor);
        isBackgroundColor = commonUtils.assertCSSProperties("background-color", backgroundColor, expBackgroundColor);
        isOutlineStyle = commonUtils.assertCSSProperties("outline-style", outlineStyle, expOutlineStyle);
        if (!isColor) {
            log.info("color for MultiLine Input Error is not as per the spec, actual: " + color);
        }
        if (!isBackgroundColor) {
            log.info("background-color for MultiLine Input Error is not as per the spec, actual: " + backgroundColor);
        }
        if (!isOutlineStyle) {
            log.info("outline-style for MultiLine Input Error is not as per the spec, actual: " + outlineStyle);
        }
        Assert.assertTrue(isColor && isBackgroundColor && isOutlineStyle);

        for (String cssProperty : borderColors) {
            borderColor = commonUtils.getCSSValue(element, cssProperty, "mobile");
            isBorderColor = commonUtils.assertCSSProperties(cssProperty, borderColor, expBorderColor);
            if (!isBorderColor) {
                log.info("Border color " + cssProperty + " for MultiLine Input Disabled is not as per spec, actual " + borderColor);
            }
            Assert.assertTrue(isBorderColor);
        }
    }

    @Test(testName = "Mobile: MultiLine Input - ReadOnly States Test", dataProvider = "MultiLine Input ReadOnly Test Data", groups = "mobile-regression")
    private void multiLineInputBoxReadOnlyMobileTest(By element, String[] expBackgroundColor, String[] expBorderStyle) throws Exception {
        String[] detailsPropertiesList = new String[]{"elementId", "multilinetext-target", "componentName", "MultiLineText"};
        String[] propsPropertiesList = new String[]{"id", "multiLine-text-input-readOnly", "labelText", "Multi-line label", "inputState", "readOnly", "placeholder", "Multilinetext placeholder", "changeHandler", "function () {}", "infoMessage", "This is an info message", "errorMessage", "This is an error message'"};
        setConfigAndLaunch(detailsPropertiesList, propsPropertiesList, "mobile");

        backgroundColor = commonUtils.getCSSValue(element, "background-color", "mobile");
        isBackgroundColor = commonUtils.assertCSSProperties("background-color", backgroundColor, expBackgroundColor);
        if (!isBackgroundColor) {
            log.info("background-color for for MultiLine Input ReadOnly is not as per the spec, actual: " + backgroundColor);
        }
        Assert.assertTrue(isBackgroundColor);

        for (int i = 0; i < borderStyles.length; i++) {
            borderStyle = commonUtils.getCSSValue(element, borderStyles[i], "mobile");
            isBorderStyle = commonUtils.assertValue(borderStyle, expBorderStyle[i], borderStyles[i] + " for MultiLine Text ReadOnly is not as per the spec");
            Assert.assertTrue(isBorderStyle);
        }
    }

    @Test(testName = "Mobile: MultiLine Input Label Test", dataProvider = "MultiLine Input Label Test Data", groups = {"mobile-regression"})
    private void multiTextInputLabelMobileTest(String inputState, String labelTextType, By labelClass, By label, String expLabelFontSize, String expLabelLineHt, String[] expLabelFontColor) throws Exception {
        String[] detailsPropertiesList = new String[]{"elementId", "multilinetext-target", "componentName", "MultiLineText"};
        String[] propsPropertiesList = new String[]{"id", "multiLine-text-input", "labelText", labelTextType, "inputState", inputState, "placeholder", "Multilinetext placeholder", "changeHandler", "function () {}", "infoMessage", "This is an info message", "errorMessage", "This is an error message"};
        setConfigAndLaunch(detailsPropertiesList, propsPropertiesList, "mobile");

        js = (JavascriptExecutor) appium;
        webElement = appium.findElement(labelClass);
        js.executeScript("arguments[0].setAttribute('id', '" + labelTextType + "')", webElement);

        labelFontSize = commonUtils.getCSSValue(label, "font-size", "mobile");
        labelLineHeight = commonUtils.getCSSValue(label, "line-height", "mobile");
        labelColor = commonUtils.getCSSValue(label, "color", "mobile");

        isLabelFontSize = commonUtils.assertValue(labelFontSize, expLabelFontSize, "Font-size of " + labelTextType + " Label is not as per spec");
        islabelLineHeight = commonUtils.assertValue(labelLineHeight, expLabelLineHt, "Line-height of " + labelTextType + " Label is not as per spec");
        isLabelColor = commonUtils.assertCSSProperties("color", labelColor, expLabelFontColor);
        if (!isLabelColor) {
            log.info("Font-color of " + labelTextType + " Label is not as per spec, actual " + labelColor);
        }
        Assert.assertTrue(isLabelFontSize && islabelLineHeight && isLabelColor);
    }

    //Fancy - Select Input
    @Test(testName = "Mobile: Fancy Select Input Box All States Test", dataProvider = "Fancy Select Input All States Test Data", groups = "mobile-regression")
    private void fancySelectInputBoxAllStatesMobileTest(String type, String id, String className, String divElement, String state, By element, By elementContainer, String[] expHeight, String[] expFontSize, String[] expLineHeight, String[] expPadding, String[] expColor, String[] expBorderBottoms, String[] expBorderRad) throws Exception {
        String[] detailsPropertiesList = new String[]{"elementId", "select-target", "componentName", "Select"};
        String[] propsPropertiesList = new String[]{"id", id, "labelText", "Select Label", "options", "['ipad', 'mac','iphone']", "fancy", "true", "inputState", state, "changeHandler", "function () {}", "infoMessage", "This is an info message", "errorMessage", "This is an error message'"};

        setConfigAndLaunch(detailsPropertiesList, propsPropertiesList, "mobile");

        js = (JavascriptExecutor) appium;
        webElement = appium.findElement(By.className(className));
        js.executeScript("arguments[0].setAttribute('id','" + divElement + "')", webElement);

        height = commonUtils.getCSSValue(elementContainer, "height", "mobile");
        fontSize = commonUtils.getCSSValue(elementContainer, "font-size", "mobile");
        lineHeight = commonUtils.getCSSValue(elementContainer, "line-height", "mobile");
        color = commonUtils.getCSSValue(elementContainer, "color", "mobile");

        isHeight = commonUtils.assertCSSProperties("height", height, expHeight);
        isFontSize = commonUtils.assertCSSProperties("font-size", fontSize, expFontSize);
        isLineHeight = commonUtils.assertCSSProperties("line-height", lineHeight, expLineHeight);
        isColor = commonUtils.assertCSSProperties("color", color, expColor);
        if (!isHeight) {
            log.info("Height for " + type + " is not as per the spec, actual: " + height);
        }
        if (!isFontSize) {
            log.info("font size for " + type + " is not as per the spec, actual: " + fontSize);
        }
        if (!isLineHeight) {
            log.info("Line Height for " + type + " is not as per the spec, actual: " + lineHeight);
        }
        if (!isColor) {
            log.info("color for " + type + " is not as per the spec, actual: " + color);
        }
        Assert.assertTrue(isHeight && isFontSize && isLineHeight && isColor);

        for (int i = 0; i < paddings.length; i++) {
            padding = commonUtils.getCSSValue(element, paddings[i], "mobile");
            isPadding = commonUtils.assertValue(padding, expPadding[i], paddings[i] + " for " + type + " is not as per the spec");
            Assert.assertTrue(isPadding);
        }

        testBorders(type, borderBottom, borderBottoms, expBorderBottoms, element, "mobile");
        int i = 0;
        for (i = 0; i < borderRadii.length; i++) {
            borderRadius = commonUtils.getCSSValue(elementContainer, borderRadii[i], "mobile");
            isBorderRadius = commonUtils.assertValue(borderRadius, expBorderRad[i], "Border radius " + borderRadii[i] + " of " + type + " field is not as per spec");
            Assert.assertTrue(isBorderRadius);
        }
    }

    @Test(testName = "Mobile : Fancy Select Input Label Test", dataProvider = "Fancy Select Input Label Test Data", groups = {"mobile-regression"})
    private void fancySelectInputBoxLabelMobileTest(String type, String state, By elem, By label, String expLabelFontSize, String expLabelLineHt, String[] expLabelColor) throws Exception {
        String[] detailsPropertiesList = new String[]{"elementId", "select-target", "componentName", "Select"};
        String[] propsPropertiesList = new String[]{"id", type, "labelText", "Select Label", "options", "['ipad', 'mac','iphone']", "fancy", "true", "inputState", state, "changeHandler", "function () {}", "infoMessage", "This is an info message", "errorMessage", "This is an error message'"};
        setConfigAndLaunch(detailsPropertiesList, propsPropertiesList, "mobile");

        labelColor = commonUtils.getCSSValue(label, "color", "mobile");
        labelFontSize = commonUtils.getCSSValue(label, "font-size", "mobile");
        labelLineHeight = commonUtils.getCSSValue(label, "line-height", "mobile");

        isLabelColor = commonUtils.assertCSSProperties("color", labelColor, expLabelColor);
        if (!isLabelColor) {
            log.info("Label color of " + type + " is not as per spec, actual " + labelColor);
        }
        isLabelFontSize = commonUtils.assertValue(labelFontSize, expLabelFontSize, "font-size of " + type + " Label is not as per spec");
        islabelLineHeight = commonUtils.assertValue(labelLineHeight, expLabelLineHt, "Line-height of " + type + " Label is not as per spec");
        isLabelFor = commonUtils.checkLabelForVal(label, elem, "mobile");
        if (!isLabelFor) {
            log.info("Label for " + type + " is not tagged to the appropriate input");
        }
        Assert.assertTrue(isLabelColor && isLabelFontSize && islabelLineHeight && isLabelFor);
    }

    @Test(testName = "Mobile : Fancy Select Input Msg Test", dataProvider = "Fancy Select Input Msg Test Data", groups = {"mobile-regression"})
    private void fancySelectInputBoxMsgMobileTest(String type, String state, String msgType, String className, By msg, String expFontSize, String expPaddingTop, String[] expColor) throws Exception {
        String[] detailsPropertiesList = new String[]{"elementId", "select-target", "componentName", "Select"};
        String[] propsPropertiesList = new String[]{"id", type, "labelText", "Select Label", "options", "['ipad', 'mac','iphone']", "fancy", "true", "inputState", state, "changeHandler", "function () {}", "infoMessage", "This is an info message", "errorMessage", "This is an error message'"};
        setConfigAndLaunch(detailsPropertiesList, propsPropertiesList, "mobile");

        js = (JavascriptExecutor) appium;
        webElement = appium.findElement(By.className(className));
        js.executeScript("arguments[0].setAttribute('id','" + msgType + "')", webElement);
        fontSize = commonUtils.getCSSValue(msg, "font-size", "mobile");
        isFontSize = commonUtils.assertValue(fontSize, expFontSize, "font size of " + type + " is not as per spec");
        paddingTop = commonUtils.getCSSValue(msg, "padding-top", "mobile");
        isPaddingTop = commonUtils.assertValue(paddingTop, expPaddingTop, "padding top of " + type + " is not as per spec");
        color = commonUtils.getCSSValue(msg, "color", "mobile");
        isColor = commonUtils.assertCSSProperties("color", color, expColor);
        if (!isColor) {
            log.info("Font Color of " + type + " is not as per spec, actual " + color);
        }
        Assert.assertTrue(isFontSize && isColor && isPaddingTop);
    }

    //Basic - Select Input
    @Test(testName = "Mobile: Basic Select Input All States Test", dataProvider = "Basic Select Input All States Test Data", groups = "mobile-regression")
    private void basicSelectInputAllStatesMobileTest(String type, String id, String className, String divElement, String state, By element, By elementContainer, String[] expHeight, String[] expPaddingLeft, String[] expPaddingRight, String[] expMarginTop, String[] expFontSize, String[] expLineHeight, String[] expColor, String[] expBackgroundColor) throws Exception {
        String[] detailsPropertiesList = new String[]{"elementId", "select-target", "componentName", "Select"};
        String[] propsPropertiesList = new String[]{"id", id, "labelText", "Select Label", "options", "['ipad', 'mac','iphone']", "fancy", "false", "inputState", state, "changeHandler", "function () {}", "infoMessage", "This is an info message", "errorMessage", "This is an error message'"};
        setConfigAndLaunch(detailsPropertiesList, propsPropertiesList, "mobile");

        js = (JavascriptExecutor) appium;
        webElement = appium.findElement(By.className(className));
        js.executeScript("arguments[0].setAttribute('id','" + divElement + "')", webElement);

        height = commonUtils.getCSSValue(elementContainer, "height", "mobile");
        paddingLeft = commonUtils.getCSSValue(element, "padding-left", "mobile");
        paddingRight = commonUtils.getCSSValue(element, "padding-right", "mobile");
        marginTop = commonUtils.getCSSValue(elementContainer, "margin-top", "mobile");
        fontSize = commonUtils.getCSSValue(elementContainer, "font-size", "mobile");
        lineHeight = commonUtils.getCSSValue(elementContainer, "line-height", "mobile");
        color = commonUtils.getCSSValue(elementContainer, "color", "mobile");
        backgroundColor = commonUtils.getCSSValue(elementContainer, "background-color", "mobile");

        isHeight = commonUtils.assertCSSProperties("height", height, expHeight);
        isPaddingLeft = commonUtils.assertCSSProperties("padding-left", paddingLeft, expPaddingLeft);
        isPaddingRight = commonUtils.assertCSSProperties("padding-right", paddingRight, expPaddingRight);
        isMarginTop = commonUtils.assertCSSProperties("margin-top", marginTop, expMarginTop);

        isFontSize = commonUtils.assertCSSProperties("font-size", fontSize, expFontSize);
        isLineHeight = commonUtils.assertCSSProperties("line-height", lineHeight, expLineHeight);
        isColor = commonUtils.assertCSSProperties("color", color, expColor);
        isBackgroundColor = commonUtils.assertCSSProperties("background-color", backgroundColor, expBackgroundColor);

        if (!isHeight) {
            log.info("Height for " + type + " is not as per the spec, actual: " + height);
        }
        if (!isPaddingLeft) {
            log.info("padding-left for " + type + " is not as per the spec, actual: " + paddingLeft);
        }
        if (!isPaddingRight) {
            log.info("padding-right for " + type + " is not as per the spec, actual: " + paddingRight);
        }
        if (!isMarginTop) {
            log.info("margin-top for " + type + " is not as per the spec, actual: " + marginTop);
        }
        if (!isFontSize) {
            log.info("font size for " + type + " is not as per the spec, actual: " + fontSize);
        }
        if (!isLineHeight) {
            log.info("Line Height for " + type + " is not as per the spec, actual: " + lineHeight);
        }
        if (!isColor) {
            log.info("color for " + type + " is not as per the spec, actual: " + color);
        }
        if (!isBackgroundColor) {
            log.info("bg-color for " + type + " is not as per the spec, actual: " + backgroundColor);
        }
        Assert.assertTrue(isHeight && isPaddingLeft && isPaddingRight && isMarginTop && isFontSize && isLineHeight && isColor && isBackgroundColor);
    }

    @Test(testName = "Mobile : Basic Select Input Box- Border Test", dataProvider = "Basic Select Input Border Test Data", groups = {"mobile-regression"})
    private void basicSelectInputBoxBorderMobileTest(String type, String state, String className, String id, By elem, String expBorderRadius, String expBorderWidth, String expBorderStyle, String[] expBorderColor) throws Exception {
        String[] detailsPropertiesList = new String[]{"elementId", "select-target", "componentName", "Select"};
        String[] propsPropertiesList = new String[]{"id", type, "labelText", "Select Label", "options", "['ipad', 'mac','iphone']", "fancy", "false", "inputState", state, "changeHandler", "function () {}", "infoMessage", "This is an info message", "errorMessage", "This is an error message'"};
        setConfigAndLaunch(detailsPropertiesList, propsPropertiesList, "mobile");

        js = (JavascriptExecutor) appium;
        webElement = appium.findElement(By.className(className));
        js.executeScript("arguments[0].setAttribute('id','" + id + "')", webElement);
        for (String cssProperty : borderRadii) {
            borderRadius = commonUtils.getCSSValue(elem, cssProperty, "mobile");
            isBorderRadius = commonUtils.assertValue(borderRadius, expBorderRadius, "Border radius  " + cssProperty + " of " + type + " is not as per spec");
            Assert.assertTrue(isBorderRadius);
        }
        for (String cssProperty : borderWidths) {
            borderWidth = commonUtils.getCSSValue(elem, cssProperty, "mobile");
            isBorderWidth = commonUtils.assertValue(borderWidth, expBorderWidth, "Border width " + cssProperty + " of " + type + " is not as per spec");
            Assert.assertTrue(isBorderWidth);
        }
        for (String cssProperty : borderStyles) {
            borderStyle = commonUtils.getCSSValue(elem, cssProperty, "mobile");
            isBorderStyle = commonUtils.assertValue(borderStyle, expBorderStyle, "Border style " + cssProperty + " of " + type + " is not as per spec");
            Assert.assertTrue(isBorderStyle);
        }
        if (!type.equals("select-input-basic-readOnly")) {
            for (String cssProperty : borderColors) {
                borderColor = commonUtils.getCSSValue(elem, cssProperty, "mobile");
                isBorderColor = commonUtils.assertCSSProperties(cssProperty, borderColor, expBorderColor);
                if (!isBorderColor) {
                    log.info("Border color " + cssProperty + " of " + type + " is not as per spec, actual " + borderColor);
                }
                Assert.assertTrue(isBorderColor);
            }
        }
    }

    @Test(testName = "Mobile : Basic Select Input Label Test", dataProvider = "Basic Select Input Label and Icon Test Data", groups = {"mobile-regression"})
    private void basicSelectInputBoxLabelMobileTest(String type, String state, By elem, By label, By icon, String[] expLabelColor, String expLabelFontSize, String expLabelLineHt) throws Exception {
        String[] detailsPropertiesList = new String[]{"elementId", "select-target", "componentName", "Select"};
        String[] propsPropertiesList = new String[]{"id", type, "labelText", "Select Label", "options", "['ipad', 'mac','iphone']", "fancy", "false", "inputState", state, "changeHandler", "function () {}", "infoMessage", "This is an info message", "errorMessage", "This is an error message'"};
        setConfigAndLaunch(detailsPropertiesList, propsPropertiesList, "mobile");

        // Select Input Label
        labelColor = commonUtils.getCSSValue(label, "color", "mobile");
        labelFontSize = commonUtils.getCSSValue(label, "font-size", "mobile");
        labelLineHeight = commonUtils.getCSSValue(label, "line-height", "mobile");

        isLabelColor = commonUtils.assertCSSProperties("color", labelColor, expLabelColor);
        if (!isLabelColor) {
            log.info("Label color of " + type + " is not as per spec, actual " + labelColor);
        }
        isLabelFontSize = commonUtils.assertValue(labelFontSize, expLabelFontSize, "Font-size of " + type + " Label is not as per spec");
        islabelLineHeight = commonUtils.assertValue(labelLineHeight, expLabelLineHt, "Line-height of " + type + " Label is not as per spec");
        isLabelFor = commonUtils.checkLabelForVal(label, elem, "mobile");
        if (!isLabelFor) {
            log.info("Label for " + type + " is not tagged to the appropriate input");
        }
        // icon
        actIconClass = commonUtils.getAttributeValue(icon, "class", "mobile");
        isIconClass = commonUtils.assertValue(actIconClass, "pe-icon--dropdown-open-sm-24", "Dropdown icon does not comply to the \"pe-icon--dropdown-open-sm-24\"");
        Assert.assertTrue(isLabelColor && isLabelFontSize && islabelLineHeight && isLabelFor && isIconClass);
    }

    //Check Box
    @Test(testName = "Mobile: Verify Checkbox Input", dataProvider = "Check Box Input - Test Data", groups = "mobile-regression")
    private void checkboxMobileTest(By element, String[] expPosition, String[] expMinHeight, String[] expMarginBottom) throws Exception {
        String[] detailsPropertiesList = new String[]{"elementId", "checkbox-target", "componentName", "RadioCheckGroup"};
        String[] propsPropertiesList = new String[]{"id", "checkboxInput", "inputType", "checkbox", "legendText", "checkbox1", "options", "[{value: 0, label: 'coffee', checked: true}, {value: 1, label: 'tea'}, {value: 2, label: 'hot cocoa'}]", "changeHandler", "function () {}"};
        setConfigAndLaunch(detailsPropertiesList, propsPropertiesList, "mobile");

        js = (JavascriptExecutor) appium;
        webElement = appium.findElement(element);
        js.executeScript("arguments[0].setAttribute('id', 'checkboxInput')", webElement);

        position = commonUtils.getCSSValue(element, "position", "mobile");
        minHeight = commonUtils.getCSSValue(element, "min-height", "mobile");
        marginBottom = commonUtils.getCSSValue(element, "margin-bottom", "mobile");

        isPosition = commonUtils.assertCSSProperties("position", position, expPosition);
        isMinHeight = commonUtils.assertCSSProperties("min-height", minHeight, expMinHeight);
        isMarginBottom = commonUtils.assertCSSProperties("marginBottom", marginBottom, expMarginBottom);

        if (!isPosition) {
            log.info("position for checkbox input is not as per the spec, actual: " + position);
        }
        if (!isMinHeight) {
            log.info("min-height for checkbox input is not as per the spec, actual: " + minHeight);
        }
        if (!isMarginBottom) {
            log.info("margin-bottom for checkbox input is not as per the spec, actual: " + marginBottom);
        }
        Assert.assertTrue(isPosition && isMinHeight && isMarginBottom);
    }

    @Test(testName = "Mobile: Verify Checkbox - Normal State", dataProvider = "Check Box - Normal State Test Data", groups = "mobile-regression")
    private void checkboxNormalStateMobileTest(By element, String[] expPosition, String[] expTop, String[] expLeft, String[] expHeight, String[] expWidth, String expBorderWidth, String expBorderStyle, String[] expBorderColor, String expBorderRadius) throws Exception {
        String[] detailsPropertiesList = new String[]{"elementId", "checkbox-target", "componentName", "RadioCheckGroup"};
        String[] propsPropertiesList = new String[]{"id", "checkbox-state", "inputType", "checkbox", "legendText", "checkbox1", "options", "[{value: 0, label: 'coffee', checked: true}, {value: 1, label: 'tea'}, {value: 2, label: 'hot cocoa'}]", "changeHandler", "function () {}"};
        setConfigAndLaunch(detailsPropertiesList, propsPropertiesList, "mobile");

        js = (JavascriptExecutor) appium;
        webElement = appium.findElement(By.xpath("//span"));
        js.executeScript("arguments[0].setAttribute('id', 'checkbox-state')", webElement);

        position = commonUtils.getCSSValue(element, "position", "mobile");
        topProperty = commonUtils.getCSSValue(element, "top", "mobile");
        leftProperty = commonUtils.getCSSValue(element, "left", "mobile");
        height = commonUtils.getCSSValue(element, "height", "mobile");
        width = commonUtils.getCSSValue(element, "width", "mobile");

        isPosition = commonUtils.assertCSSProperties("position", position, expPosition);
        isTop = commonUtils.assertCSSProperties("top", topProperty, expTop);
        isLeft = commonUtils.assertCSSProperties("left", leftProperty, expLeft);
        isHeight = commonUtils.assertCSSProperties("height", height, expHeight);
        isWidth = commonUtils.assertCSSProperties("width", width, expWidth);

        if (!isPosition) {
            log.info("position for checkbox input is not as per the spec, actual" + position);
        }
        if (!isTop) {
            log.info("top for checkbox input is not as per the spec, actual" + topProperty);
        }
        if (!isLeft) {
            log.info("left for checkbox input is not as per the spec, actual" + leftProperty);
        }
        if (!isHeight) {
            log.info("height for checkbox input is not as per the spec, actual" + height);
        }
        if (!isWidth) {
            log.info("width for checkbox input is not as per the spec, actual" + width);
        }
        Assert.assertTrue(isPosition && isTop && isLeft && isHeight && isWidth);

        for (String cssProperty : borderWidths) {
            borderWidth = commonUtils.getCSSValue(element, cssProperty, "mobile");
            isBorderWidth = commonUtils.assertValue(borderWidth, expBorderWidth, "Border width '" + cssProperty + "' of check box is not as per spec");
            Assert.assertTrue(isBorderWidth);
        }
        for (String cssProperty : borderStyles) {
            borderStyle = commonUtils.getCSSValue(element, cssProperty, "mobile");
            isBorderStyle = commonUtils.assertValue(borderStyle, expBorderStyle, "Border style '" + cssProperty + "' of check box is not as per spec");
            Assert.assertTrue(isBorderStyle);
        }
        for (String cssProperty : borderColors) {
            borderColor = commonUtils.getCSSValue(element, cssProperty, "mobile");
            isBorderColor = commonUtils.assertCSSProperties(cssProperty, borderColor, expBorderColor);
            if (!isBorderColor) {
                log.info("Border color '" + cssProperty + "' of check box is not as per spec, actual: " + borderColor);
            }
            Assert.assertTrue(isBorderColor);
        }
        for (String cssProperty : borderRadii) {
            borderRadius = commonUtils.getCSSValue(element, cssProperty, "mobile");
            isBorderRadius = commonUtils.assertValue(borderRadius, expBorderRadius, "Border radius '" + cssProperty + "' of check box is not as per spec");
            Assert.assertTrue(isBorderRadius);
        }
    }

    @Test(testName = "Mobile: Verify Checkbox - All States Test", dataProvider = "Check Box - All States Test Data", groups = "mobile-regression")
    private void checkboxAllStatesMobileTest(String state, String id, By xpathElement, By element, String[] expBorderColor) throws Exception {
        if (state.equals("focus")) {
            throw new SkipException("dont have to do focus operation on mobile devices");
        }
        String[] detailsPropertiesList = new String[]{"elementId", "checkbox-target", "componentName", "RadioCheckGroup"};
        String[] propsPropertiesList = {};

        propsPropertiesList = new String[]{"id", id, "inputType", "checkbox", "legendText", "checkbox1", "options", "[{value: 0, label: 'coffee', checked: true, disabled: true}, {value: 1, label: 'tea'}, {value: 2, label: 'hot cocoa'}]", "changeHandler", "function () {}"};

        setConfigAndLaunch(detailsPropertiesList, propsPropertiesList, "mobile");

        js = (JavascriptExecutor) appium;
        webElement = appium.findElement(xpathElement);
        js.executeScript("arguments[0].setAttribute('id', '" + id + "')", webElement);

        backgroundColor = commonUtils.getCSSValue(element, "background-color", "mobile");
        isBackgroundColor = commonUtils.assertCSSProperties("bg-color", backgroundColor, new String[]{commonUtils.hex2Rgb("#E9E9E9"), commonUtils.hex2RgbWithoutTransparency("#E9E9E9")});
        if (!isBackgroundColor) {
            log.info("'bg-color of check box in " + state + " state is not as per spec, actual: " + backgroundColor);
        }
        Assert.assertTrue(isBackgroundColor);

        for (String cssProperty : borderColors) {
            borderColor = commonUtils.getCSSValue(element, cssProperty, "mobile");
            isBorderColor = commonUtils.assertCSSProperties(cssProperty, borderColor, expBorderColor);
            if (!isBorderColor) {
                log.info("Border color '" + cssProperty + "' of check box in " + state + " state is not as per spec");
            }
            Assert.assertTrue(isBorderColor);
        }
    }

    @Test(testName = "Mobile: Verify Check Box - Label", dataProvider = "Check Box - Label Test Data", groups = "mobile-regression")
    private void labelForCheckBoxMobileTest(String id, String checkedValue, String disabledValue, String loc, By element, String[] expPaddingLeft, String expDisplay) throws Exception {
        if (id.contains("focus")) {
            throw new SkipException("Focus operation tests not needed for mobile devices");
        }
        String[] detailsPropertiesList = new String[]{"elementId", "checkbox-target", "componentName", "RadioCheckGroup"};
        String[] propsPropertiesList = new String[]{"id", id, "inputType", "checkbox", "legendText", "checkbox1", "options", "[{value: 0, label: 'coffee', checked: " + checkedValue + ", disabled: " + disabledValue + "}, {value: 1, label: 'tea'}, {value: 2, label: 'hot cocoa'}]", "changeHandler", "function () {}"};
        setConfigAndLaunch(detailsPropertiesList, propsPropertiesList, "mobile");

        js = (JavascriptExecutor) appium;
        webElement = appium.findElement(By.xpath(loc));
        js.executeScript("arguments[0].setAttribute('id', '" + id + "')", webElement);

        paddingLeft = commonUtils.getCSSValue(element, "padding-left", "mobile");
        isPaddingLeft = commonUtils.assertCSSProperties("padding-left", paddingLeft, expPaddingLeft);
        if (!isPaddingLeft) {
            log.info("padding-left for checkbox label of " + id + " type is not as per the spec, actual: " + paddingLeft);
        }
        display = commonUtils.getCSSValue(element, "display", "mobile");
        isDisplay = commonUtils.assertValue(display, expDisplay, "'display' for checkbox label of '" + id + "' type is not as per the spec");

        Assert.assertTrue(isPaddingLeft && isDisplay);
    }

    //Radios
    @Test(testName = "Mobile: Radio Buttons Unselected Selected And Disabled States - SVG Test", dataProvider = "Radio Buttons Unselected Selected And Disabled States - SVG Test Data", groups = {"mobile-regression"})
    private void radioButtonsUnselectedSelectedDisabledSVGMobileTest(String elemType, String checkedValue, String[] expBorderColor, String[] expSvgColor, String spanXpath, String spanId, By span, String svgXpath, String svgId, By svg) throws Exception {
        String[] detailsPropertiesList = new String[]{"elementId", "radio-target", "componentName", "RadioCheckGroup"};
        String[] propsPropertiesList = new String[]{"id", elemType, "inputType", "radio", "legendText", "radio1", "options", "[{value: 0, label: 'coffee',checked: " + checkedValue + ",disabled: false},{value: 1, label: 'tea'}]", "changeHandler", "function () {}"};
        setConfigAndLaunch(detailsPropertiesList, propsPropertiesList, "mobile");

        js = (JavascriptExecutor) appium;
        webElement = appium.findElement(By.xpath(spanXpath));
        js.executeScript("arguments[0].setAttribute('id', '" + spanId + "')", webElement);

        webElement = appium.findElement(By.xpath(svgXpath));
        js.executeScript("arguments[0].setAttribute('id', '" + svgId + "')", webElement);

        height = commonUtils.getCSSValue(span, "height", "mobile");
        width = commonUtils.getCSSValue(span, "width", "mobile");
        color = commonUtils.getCSSValue(span, "color", "mobile");
        radioBtnSelectedColor = commonUtils.getCSSValue(svg, "color", "mobile");

        for (String cssProperty : borderColors) {
            borderColor = commonUtils.getCSSValue(span, cssProperty, "mobile");
            isBorderColor = commonUtils.assertCSSProperties(cssProperty, borderColor, expBorderColor);
            if (!isBorderColor) {
                log.info("Border-color of " + elemType + " is not as per spec, actual " + borderColor);
            }
            Assert.assertTrue(isBorderColor);
        }
        for (String cssProperty : borderStyles) {
            borderStyle = commonUtils.getCSSValue(span, cssProperty, "mobile");
            isBorderStyle = commonUtils.assertValue(borderStyle, "solid", "'" + cssProperty + "' of " + elemType + " is not as per spec");
            Assert.assertTrue(isBorderStyle);
        }
        for (String cssProperty : borderWidths) {
            borderWidth = commonUtils.getCSSValue(span, cssProperty, "mobile");
            isBorderWidth = commonUtils.assertValue(borderWidth, "1px", "'" + cssProperty + "' of " + elemType + " is not as per spec");
            Assert.assertTrue(isBorderWidth);
        }
        for (String cssProperty : borderRadii) {
            borderRadius = commonUtils.getCSSValue(span, cssProperty, "mobile");
            isBorderRadius = commonUtils.assertCSSProperties(cssProperty, borderRadius, new String[]{"50%", "8px"});
            if (!isBorderRadius) {
                log.info("'" + cssProperty + "' of " + elemType + " is not as per spec, actual " + borderRadius);
            }
            Assert.assertTrue(isBorderRadius);
        }
        for (String cssProperty : paddings) {
            radioBtnPadding = commonUtils.getCSSValue(span, cssProperty, "mobile");
            isRadioBtnPadding = commonUtils.assertCSSProperties(cssProperty, radioBtnPadding, new String[]{"3px", "6px"});
            if (!isRadioBtnPadding) {
                log.info("'" + cssProperty + "' of " + elemType + " is not as per spec, actual " + radioBtnPadding);
            }
            Assert.assertTrue(isRadioBtnPadding);
        }
        if (elemType.contains("disabled")) {
            bgColor = commonUtils.getCSSValue(span, "background-color", "mobile");
            isBgColor = commonUtils.assertCSSProperties("background-color", bgColor, new String[]{commonUtils.hex2Rgb("#E9E9E9"), commonUtils.hex2RgbWithoutTransparency("#E9E9E9")});
            Assert.assertTrue(isBgColor);
        }
        isRadioBtnSelectedColor = commonUtils.assertCSSProperties("color", radioBtnSelectedColor, expSvgColor);
        if (!isRadioBtnSelectedColor) {
            log.info("Svg-icon color when selected of " + elemType + " is not as per spec, actual " + radioBtnSelectedColor);
        }
        Assert.assertTrue(isRadioBtnSelectedColor);
    }

    @Test(testName = "Mobile: Radio Buttons Unselected Selected And Disabled States LabelTest", dataProvider = "Radio Buttons Unselected Selected And Disabled States - Label Test Data", groups = {"mobile-regression"})
    private void radioButtonsUnselectedSelectedDisabledLabelMobileTest(String elemType, String checkedValue, String disabledValue, String expPaddingLeft, String[] expLabelfontSize, String labelXpath, String labelId, By label) throws Exception {
        String[] detailsPropertiesList = new String[]{"elementId", "radio-target", "componentName", "RadioCheckGroup"};
        String[] propsPropertiesList = new String[]{"id", elemType, "inputType", "radio", "legendText", "radio1", "options", "[{value: 0, label: 'coffee',checked: " + checkedValue + ",disabled: " + disabledValue + "},{value: 1, label: 'tea'}]", "changeHandler", "function () {}"};
        setConfigAndLaunch(detailsPropertiesList, propsPropertiesList, "mobile");

        js = (JavascriptExecutor) appium;
        webElement = appium.findElement(By.xpath(labelXpath));
        js.executeScript("arguments[0].setAttribute('id', '" + labelId + "')", webElement);

        paddingLeft = commonUtils.getCSSValue(label, "padding-left", "mobile");
        labelFontSize = commonUtils.getCSSValue(label, "font-size", "mobile");
        labelLineHeight = commonUtils.getCSSValue(label, "line-height", "mobile");
        isPaddingLeft = commonUtils.assertValue(paddingLeft, expPaddingLeft, "Padding-left between " + elemType + " and its label is not as per spec");
        isLabelFontSize = commonUtils.assertCSSProperties("font-size", labelFontSize, expLabelfontSize);
        if (!isLabelFontSize) {
            log.info("Label font size of " + elemType + "is not as per spec, actual " + labelFontSize);
        }
        islabelLineHeight = commonUtils.assertValue(labelLineHeight, "18px", "Label line height of " + elemType + "is not as per spec");

        Assert.assertTrue(isPaddingLeft && isLabelFontSize && islabelLineHeight);
    }

    @Test(testName = "Mobile: Radio Buttons Unselected Selected And Disabled States - Div Test", dataProvider = "Radio Buttons Unselected Selected And Disabled States - Div Test Data", groups = {"mobile-regression"})
    private void radioButtonsUnselectedSelectedDisabledRadioDivMobileTest(String elemType, String checkedValue, String disabledValue, String expMarginBottom, String divXpath, String divId, By elem) throws Exception {
        String[] detailsPropertiesList = new String[]{"elementId", "radio-target", "componentName", "RadioCheckGroup"};
        String[] propsPropertiesList = new String[]{"id", elemType, "inputType", "radio", "legendText", "radio1", "options", "[{value: 0, label: 'coffee',checked: " + checkedValue + ",disabled: " + disabledValue + "},{value: 1, label: 'tea'}]", "changeHandler", "function () {}"};
        setConfigAndLaunch(detailsPropertiesList, propsPropertiesList, "mobile");

        js = (JavascriptExecutor) appium;
        webElement = appium.findElement(By.xpath(divXpath));
        js.executeScript("arguments[0].setAttribute('id', '" + divId + "')", webElement);

        marginBottom = commonUtils.getCSSValue(elem, "margin-bottom", "mobile");
        isMarginBottom = commonUtils.assertValue(marginBottom, expMarginBottom, "Margin-Bottom of " + elemType + " is not as per spec");
        Assert.assertTrue(isMarginBottom);
    }

    //Negative tests
    @DataProvider(name = "Negative Config Test Data")
    public Object[][] getNegativeConfigTestData() {
        return new Object[][]{
                {"empty-elementId-textinput", new String[]{"componentName", "TextInput"}, new String[]{"id", "sl-text-input", "labelText", "Last Name", "inputType", "default", "fancy", "true", "placeholder", "Last Name", "changeHandler", "function () {}", "infoMessage", "This is an info message", "errorMessage", "This is an error message'"}, incorrectElementIdErrorMsg},
                {"empty-elementId-passwordinput", new String[]{"componentName", "TextInput"}, new String[]{"password", "true", "fancy", "true", "id", "password-input-fancy", "labelText", "Password", "inputState", "default", "placeholder", "Password", "changeHandler", "function () {}", "showText", "show", "hideText", "hide", "infoMessage", "This is an info message", "errorMessage", "This is an error message'"}, incorrectElementIdErrorMsg},
                {"empty-elementId-selectinput", new String[]{"componentName", "Select"}, new String[]{"id", "select-input-fancy", "labelText", "Select Label", "options", "['ipad', 'mac','iphone']", "fancy", "true", "inputState", "default", "changeHandler", "function () {}", "infoMessage", "This is an info message", "errorMessage", "This is an error message'"}, incorrectElementIdErrorMsg},
                {"empty-elementId-multiline", new String[]{"componentName", "MultiLineText"}, new String[]{"id", "multiLine-text-input", "labelText", "Multi-line label", "inputState", "default", "placeholder", "Multilinetext placeholder", "changeHandler", "function () {}", "infoMessage", "This is an info message", "errorMessage", "This is an error message'"}, incorrectElementIdErrorMsg},
                {"empty-elementId-checkbox", new String[]{"componentName", "RadioCheckGroup"}, new String[]{"id", "unselected", "inputType", "checkbox", "legendText", "checkbox1", "options", "[{value: 0, label: 'coffee', checked: true}, {value: 1, label: 'tea'}, {value: 2, label: 'hot cocoa'}]", "changeHandler", "function () {}"}, incorrectElementIdErrorMsg},
                {"empty-elementId-radio", new String[]{"componentName", "RadioCheckGroup"}, new String[]{"id", "unselected", "inputType", "radio", "legendText", "radio1", "options", "[{value: 0, label: 'coffee',checked: true,disabled: false},{value: 1, label: 'tea'}]", "changeHandler", "function () {}"}, incorrectElementIdErrorMsg},

                {"incorrect-elementId-textinput", new String[]{"elementId", "text-input-target1", "componentName", "TextInput"}, new String[]{"id", "sl-text-input", "labelText", "Last Name", "inputType", "default", "fancy", "false", "placeholder", "Last Name", "changeHandler", "function () {}", "infoMessage", "This is an info message", "errorMessage", "This is an error message'"}, incorrectElementIdErrorMsg},
                {"incorrect-elementId-passwordinput", new String[]{"elementId", "password-input-target1", "componentName", "TextInput"}, new String[]{"password", "true", "fancy", "true", "id", "password-input-fancy", "labelText", "Password", "inputState", "default", "placeholder", "Password", "changeHandler", "function () {}", "showText", "show", "hideText", "hide", "infoMessage", "This is an info message", "errorMessage", "This is an error message'"}, incorrectElementIdErrorMsg},
                {"incorrect-elementId-selectinput", new String[]{"elementId", "select-target1", "componentName", "Select"}, new String[]{"id", "select-input-fancy", "labelText", "Select Label", "options", "['ipad', 'mac','iphone']", "fancy", "true", "inputState", "default", "changeHandler", "function () {}", "infoMessage", "This is an info message", "errorMessage", "This is an error message'"}, incorrectElementIdErrorMsg},
                {"incorrect-elementId-multiline", new String[]{"elementId", "multilinetext-target1", "componentName", "MultiLineText"}, new String[]{"id", "multiLine-text-input", "labelText", "Multi-line label", "inputState", "default", "placeholder", "Multilinetext placeholder", "changeHandler", "function () {}", "infoMessage", "This is an info message", "errorMessage", "This is an error message'"}, incorrectElementIdErrorMsg},
                {"incorrect-elementId-checkbox", new String[]{"elementId", "checkbox-target1", "componentName", "RadioCheckGroup"}, new String[]{"id", "unselected", "inputType", "checkbox", "legendText", "checkbox1", "options", "[{value: 0, label: 'coffee', checked: true}, {value: 1, label: 'tea'}, {value: 2, label: 'hot cocoa'}]", "changeHandler", "function () {}"}, incorrectElementIdErrorMsg},
                {"incorrect-elementId-radio", new String[]{"elementId", "radio-target1", "componentName", "RadioCheckGroup"}, new String[]{"id", "unselected", "inputType", "radio", "legendText", "radio1", "options", "[{value: 0, label: 'coffee',checked: true,disabled: false},{value: 1, label: 'tea'}]", "changeHandler", "function () {}"}, incorrectElementIdErrorMsg},

                {"empty-componentName-textinput", new String[]{"elementId", "text-input-target"}, new String[]{"id", "sl-text-input", "labelText", "Last Name", "inputType", "default", "fancy", "true", "placeholder", "Last Name", "changeHandler", "function () {}", "infoMessage", "This is an info message", "errorMessage", "This is an error message'"}, incorrectComponentNameErrorMsg},
                {"empty-componentName-passwordinput", new String[]{"elementId", "password-input-target"}, new String[]{"password", "true", "fancy", "true", "id", "password-input-fancy", "labelText", "Password", "inputState", "default", "placeholder", "Password", "changeHandler", "function () {}", "showText", "show", "hideText", "hide", "infoMessage", "This is an info message", "errorMessage", "This is an error message'"}, incorrectComponentNameErrorMsg},
                {"empty-componentName-selectinput", new String[]{"elementId", "select-target"}, new String[]{"id", "select-input-fancy", "labelText", "Select Label", "options", "['ipad', 'mac','iphone']", "fancy", "true", "inputState", "default", "changeHandler", "function () {}", "infoMessage", "This is an info message", "errorMessage", "This is an error message'"}, incorrectComponentNameErrorMsg},
                {"empty-componentName-multiline", new String[]{"elementId", "multilinetext-target"}, new String[]{"id", "multiLine-text-input", "labelText", "Multi-line label", "inputState", "default", "placeholder", "Multilinetext placeholder", "changeHandler", "function () {}", "infoMessage", "This is an info message", "errorMessage", "This is an error message'"}, incorrectComponentNameErrorMsg},
                {"empty-componentName-checkbox", new String[]{"elementId", "checkbox-target"}, new String[]{"id", "unselected", "inputType", "checkbox", "legendText", "checkbox1", "options", "[{value: 0, label: 'coffee', checked: true}, {value: 1, label: 'tea'}, {value: 2, label: 'hot cocoa'}]", "changeHandler", "function () {}"}, incorrectComponentNameErrorMsg},
                {"empty-componentName-radio", new String[]{"elementId", "radio-target"}, new String[]{"id", "unselected", "inputType", "radio", "legendText", "radio1", "options", "[{value: 0, label: 'coffee',checked: true,disabled: false},{value: 1, label: 'tea'}]", "changeHandler", "function () {}"}, incorrectComponentNameErrorMsg},

                {"incorrect-componentName-textinput", new String[]{"elementId", "text-input-target", "componentName", "TextInput1"}, new String[]{"id", "sl-text-input", "labelText", "Last Name", "inputType", "default", "fancy", "false", "placeholder", "Last Name", "changeHandler", "function () {}", "infoMessage", "This is an info message", "errorMessage", "This is an error message'"}, incorrectComponentNameErrorMsg},
                {"incorrect-componentName-passwordinput", new String[]{"elementId", "password-input-target", "componentName", "TextInput1"}, new String[]{"password", "true", "fancy", "true", "id", "password-input-fancy", "labelText", "Password", "inputState", "default", "placeholder", "Password", "changeHandler", "function () {}", "showText", "show", "hideText", "hide", "infoMessage", "This is an info message", "errorMessage", "This is an error message'"}, incorrectComponentNameErrorMsg},
                {"incorrect-componentName-selectinput", new String[]{"elementId", "select-target", "componentName", "Select1"}, new String[]{"id", "select-input-fancy", "labelText", "Select Label", "options", "['ipad', 'mac','iphone']", "fancy", "true", "inputState", "default", "changeHandler", "function () {}", "infoMessage", "This is an info message", "errorMessage", "This is an error message'"}, incorrectComponentNameErrorMsg},
                {"incorrect-componentName-multiline", new String[]{"elementId", "multilinetext-target", "componentName", "MultiLineText1"}, new String[]{"id", "multiLine-text-input", "labelText", "Multi-line label", "inputState", "default", "placeholder", "Multilinetext placeholder", "changeHandler", "function () {}", "infoMessage", "This is an info message", "errorMessage", "This is an error message'"}, incorrectComponentNameErrorMsg},
                {"incorrect-componentName-checkbox", new String[]{"elementId", "checkbox-target", "componentName", "RadioCheckGroup1"}, new String[]{"id", "unselected", "inputType", "checkbox", "legendText", "checkbox1", "options", "[{value: 0, label: 'coffee', checked: true}, {value: 1, label: 'tea'}, {value: 2, label: 'hot cocoa'}]", "changeHandler", "function () {}"}, incorrectComponentNameErrorMsg},
                {"incorrect-componentName-radio", new String[]{"elementId", "radio-target", "componentName", "RadioCheckGroup1"}, new String[]{"id", "unselected", "inputType", "radio", "legendText", "radio1", "options", "[{value: 0, label: 'coffee',checked: true,disabled: false},{value: 1, label: 'tea'}]", "changeHandler", "function () {}"}, incorrectComponentNameErrorMsg}
        };

    }

    @Test(testName = "Negative Config Test", dataProvider = "Negative Config Test Data", groups = {"desktop-regression"})
    private void negativeConfigValuesTest(String errorType, String[] detailsPropertiesList, String[] propsPropertiesList, String errorMessage) throws Exception {
        if (((browser.equals("firefox")) || (browser.equals("safari")) || (browser.equals("ie")) || browser.equals("edge") || (lBrowser.equals("firefox")))) {
            throw new SkipException("Focus operation not yet supported in firefox/safari/ie browser drivers");
        }
        setConfigAndLaunch(detailsPropertiesList, propsPropertiesList);

        browserLogs = commonUtils.browserLogs().toString();
        result = commonUtils.assertValue(browserLogs.contains(errorMessage), true, "right error msg is NOT seen as per SPEC");
        Assert.assertTrue(result);
    }

    /*****************
     * Common methods
     *****************/
    private String buildJSONObjectDetailConfig(String[] detailsPropertiesList, String[] propsPropertiesList) throws IOException {
        int i = 0;
        if (!((detailsPropertiesList.length % 2 == 0) && (propsPropertiesList.length % 2 == 0))) {
            log.info("Pass even set of parameters.");
            return null;
        } else {
            fileContentsInAString = commonUtils.readFileAsString(inputsJSFilePath);
            detailProperties = new LinkedHashMap<String, String>();
            for (i = 0; i < (detailsPropertiesList.length - 1); i = i + 2) {
                detailProperties.put(detailsPropertiesList[i], detailsPropertiesList[i + 1]);
            }
            propsProperties = new LinkedHashMap<String, String>();
            for (i = 0; i < (propsPropertiesList.length - 1); i = i + 2) {
                propsProperties.put(propsPropertiesList[i], propsPropertiesList[i + 1]);
            }

            //building the props properties like: id, label etc
            jsonPropsObject = new JsonObject();
            jsonPropsPropertiesObject = new JsonObject();
            for (Map.Entry<String, String> entry : propsProperties.entrySet()) {
                jsonPropsPropertiesObject.addProperty(entry.getKey(), entry.getValue());
            }

            //packaging props properties into "props" attribute
            propsConfigMap = new LinkedHashMap<String, JsonObject>();
            propsConfigMap.put("props", jsonPropsPropertiesObject);

            //building the detail properties like: elementId and componentName
            jsonDetailObject = new JsonObject();
            jsonDetailPropertiesObject = new JsonObject();
            for (Map.Entry<String, String> entry : detailProperties.entrySet()) {
                jsonDetailPropertiesObject.addProperty(entry.getKey(), entry.getValue());
            }
            for (Map.Entry<String, JsonObject> entry : propsConfigMap.entrySet()) {
                jsonDetailPropertiesObject.addProperty(entry.getKey(), String.valueOf(entry.getValue()));
            }

            //packaging all properties including props into "detail" attribute
            jsonDetailObject.add("detail", jsonDetailPropertiesObject);

            beforeFinalFormat = jsonDetailObject.toString().replaceAll("\\\\", "").replaceAll("\"\\{", "\\{").replaceAll("\\}\"", "\\}").replaceAll("\"", "").replaceAll(":", ":'").replaceAll(",", "',").replaceAll("'\\{", "\\{").replaceAll("'\\[", "\\['").replaceAll("\\]'", "'\\]").replaceAll("''", "'").replaceAll("' '", "'").replaceAll("\\]\\}", "\\]").replaceAll("\\}'", "\\}").replaceAll("'\\},", "\\},").replaceAll("'false'", "false").replaceAll("'true'", "true").replaceAll("'function", "function").replaceAll("'\\{", "\\{").replaceAll("' true", "true").replaceAll("\\},", "'\\},").replaceAll("true'", "true").replaceAll("' false'", "false").replaceAll("\\{'\\}", "\\{\\}");

            finalConfig = preConfigStr1 + "\n" + preConfigStr2 + beforeFinalFormat + postConfigStr1;
            return finalConfig;
        }
    }

    private void setConfigAndLaunch(String[] detailsPropertiesList, String[] propsPropertiesList) throws Exception {
        testConfig = buildJSONObjectDetailConfig(detailsPropertiesList, propsPropertiesList);
        commonUtils.changeConfig(inputsJSFilePath, testConfig);
        commonUtils.getUrl(inputsUrl);
    }

    private void setConfigAndLaunch(String[] detailsPropertiesList, String[] propsPropertiesList, String mobile) throws Exception {
        testConfig = buildJSONObjectDetailConfig(detailsPropertiesList, propsPropertiesList);
        commonUtils.changeConfig(inputsJSFilePath, testConfig);
        commonUtils.getUrl(inputsUrl, "mobile");
    }

    private String constructPath(String absolutePath) {
        String path = absolutePath.substring(0, absolutePath.lastIndexOf("elementsSDK/functional")) + "src/main/java/" + absolutePath.substring(absolutePath.indexOf("elementsSDK/functional"));
        return path;
    }

    public void testBorders(String inputType, String borderType, String[] border, String[] expBordersArray, By element) {
        int i = 0;
        boolean isBorderType = false;
        for (i = 0; i < border.length; i++) {
            borderType = commonUtils.getCSSValue(element, border[i]);
            if (i == 2) {
                isBorderType = commonUtils.assertCSSProperties("border", borderType, new String[]{commonUtils.hex2Rgb(expBordersArray[i]), commonUtils.hex2RgbWithoutTransparency(expBordersArray[i])});
            } else {
                isBorderType = commonUtils.assertValue(borderType, expBordersArray[i], testName + ": " + border[i] + " for input-type: " + inputType + ", is not as per the spec");
            }
            if (!isBorderType) {
                log.info(testName + ": " + border[i] + " for input-type:  " + inputType + ", is not as per the spec, actual: " + borderType);
            }
            Assert.assertTrue(isBorderType);
        }
    }

    public void testBorders(String inputType, String borderType, String[] border, String[] expBordersArray, By element, String mobile) {
        int i = 0;
        boolean isBorderType = false;
        for (i = 0; i < border.length; i++) {
            borderType = commonUtils.getCSSValue(element, border[i], "mobile");
            if (i == 2) {
                isBorderType = commonUtils.assertCSSProperties("border", borderType, new String[]{commonUtils.hex2Rgb(expBordersArray[i]), commonUtils.hex2RgbWithoutTransparency(expBordersArray[i])});
            } else {
                isBorderType = commonUtils.assertValue(borderType, expBordersArray[i], testName + ": " + border[i] + " for input-type: " + inputType + ", is not as per the spec");
            }
            if (!isBorderType) {
                log.info(testName + ": " + border[i] + " for input-type:  " + inputType + ", is not as per the spec, actual: " + borderType);
            }
            Assert.assertTrue(isBorderType);
        }
    }

    @BeforeMethod(alwaysRun = true)
    private void beforeMethod(Method method) throws Exception {
        System.out.println("Test Method----> " + this.getClass().getSimpleName() + "::" + method.getName());
        testName = this.getClass().getSimpleName() + "::" + method.getName();
        commonUtils.readInitialConfig(inputsJSFilePath, tempJSFilePath);
    }

    @AfterMethod(alwaysRun = true)
    private void afterMethod() throws IOException, InterruptedException {
        System.out.println("_________________________________________________");
        commonUtils.writeInitialConfig(tempJSFilePath, inputsJSFilePath);
    }
}
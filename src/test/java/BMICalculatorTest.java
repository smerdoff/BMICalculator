import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

public class BMICalculatorTest {

    @Test
    public void normalCategory() {

        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");

        WebDriver driver = new ChromeDriver();
        driver.get("https://healthunify.com/bmicalculator/");
        driver.findElement(By.name("wg")).sendKeys("82");
        driver.findElement(By.name("ht")).sendKeys("185");
        driver.findElement(By.name("cc")).click();
        String category = driver.findElement(By.name("desc")).getAttribute("value");
        Assert.assertEquals(category, "Your category is Normal", "Значения не совпадают");
        driver.quit();
    }

    @Test
    public void overweightCategory() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("https://healthunify.com/bmicalculator/");
        driver.findElement(By.name("wg")).sendKeys("100");
        driver.findElement(By.name("ht")).sendKeys("185");
        driver.findElement(By.name("cc")).click();
        String category = driver.findElement(By.name("desc")).getAttribute("value");
        Assert.assertEquals(category, "Your category is Overweight", "Значения не совпадают");
        driver.quit();
    }

    @Test
    public void underweightCategory() {

        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("https://healthunify.com/bmicalculator/");
        driver.findElement(By.name("wg")).sendKeys("60");
        driver.findElement(By.name("ht")).sendKeys("185");
        driver.findElement(By.name("cc")).click();
        String category = driver.findElement(By.name("desc")).getAttribute("value");
        Assert.assertEquals(category, "Your category is Underweight", "Значения не совпадают");
        driver.quit();
    }

    @Test
    public void starvationCategory() {

        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("https://healthunify.com/bmicalculator/");
        driver.findElement(By.name("wg")).sendKeys("50");
        driver.findElement(By.name("ht")).sendKeys("185");
        driver.findElement(By.name("cc")).click();
        String category = driver.findElement(By.name("desc")).getAttribute("value");
        Assert.assertEquals(category, "Your category is Starvation", "Значения не совпадают");
        driver.quit();
    }

    @Test
    public void obeseCategory() {

        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("https://healthunify.com/bmicalculator/");
        driver.findElement(By.name("wg")).sendKeys("120");
        driver.findElement(By.name("ht")).sendKeys("185");
        driver.findElement(By.name("cc")).click();
        String category = driver.findElement(By.name("desc")).getAttribute("value");
        Assert.assertEquals(category, "Your category is Obese", "Значения не совпадают");
        driver.quit();
    }

    @Test
    public void incorrectValueWeight() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("https://healthunify.com/bmicalculator/");
        driver.findElement(By.name("wg")).sendKeys("nnnn");
        driver.findElement(By.name("ht")).sendKeys("185");
        driver.findElement(By.name("cc")).click();
        String unit = driver.findElement(By.name("si")).getAttribute("value");
        Assert.assertEquals(unit, "NaN", "Значения не совпадают");
        driver.quit();
    }

    @Test
    public void incorrectValueHeight() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("https://healthunify.com/bmicalculator/");
        driver.findElement(By.name("wg")).sendKeys("70");
        driver.findElement(By.name("ht")).sendKeys("nnn");
        driver.findElement(By.name("cc")).click();
        String unit = driver.findElement(By.name("si")).getAttribute("value");
        Assert.assertEquals(unit, "NaN", "Значения не совпадают");
        driver.quit();
    }

    @Test
    public void toFoots() {
        double foot = 30.48;
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("https://healthunify.com/bmicalculator/");
        WebElement testDropDown = driver.findElement(By.name("opt2"));
        Select dropdown = new Select(testDropDown);
        for (int i = 6; i > 0; i--) {
            dropdown.selectByIndex(i);
            String footString = driver.findElement(By.name("ht")).getAttribute("value");
            int footValue = Integer.parseInt(footString);
            double currentFoot = foot * (i + 1);
            int expectedFoot = (int)Math.round(currentFoot);
            Assert.assertEquals(footValue, expectedFoot, "Значения не совпадают");
        }

        driver.quit();
    }

    @Test
    public void toInches() {
        double inch = 2.54;
        double foot = 30.48;
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("https://healthunify.com/bmicalculator/");
        WebElement testDropDown = driver.findElement(By.name("opt3"));
        Select dropdown = new Select(testDropDown);
        for (int i = 11; i > 0; i--) {
            dropdown.selectByIndex(i);
            String inchString = driver.findElement(By.name("ht")).getAttribute("value");
            int inchValue = Integer.parseInt(inchString);
            double currentInch = inch * i + foot;
            int expectedInch = (int)Math.round(currentInch);
            Assert.assertEquals(inchValue, expectedInch, "Значения не совпадают");
        }
        driver.quit();
    }

    @Test
    public void toPounds() {
        double pound = 2.2;
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("https://healthunify.com/bmicalculator/");
        double randomValue = 10 + Math.random()*300;
        String stringRandom = Double.toString(randomValue);
        driver.findElement(By.name("wg")).sendKeys(stringRandom);
        WebElement testDropDown = driver.findElement(By.name("opt1"));
        Select dropdown = new Select(testDropDown);
        dropdown.selectByIndex(0);
        String weightString = driver.findElement(By.name("wg")).getAttribute("value");
        double weightDouble = Double.parseDouble(weightString);
        double poundToKg = (randomValue * pound);
        poundToKg = Math.rint(100.0 * poundToKg) / 100.0;
        Assert.assertEquals(weightDouble, poundToKg, "Значения не совпадают");
        driver.quit();
    }

    @Test
    public void toKgs() {
        double pound = 2.2;
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("https://healthunify.com/bmicalculator/");
        double randomValue = 10 + Math.random()*300;
        WebElement testDropDown = driver.findElement(By.name("opt1"));
        Select dropdown = new Select(testDropDown);
        dropdown.selectByIndex(0);
        String stringRandom = Double.toString(randomValue);
        driver.findElement(By.name("wg")).clear();
        driver.findElement(By.name("wg")).sendKeys(stringRandom);
        dropdown.selectByIndex(1);
        String weightString = driver.findElement(By.name("wg")).getAttribute("value");
        int kgToPound = (int)Math.round (randomValue / pound);
        String expectedKg = Integer.toString(kgToPound); // преобразуем ожидаемое значение в стринг для последующего сравнения
        Assert.assertEquals(weightString, expectedKg, "Значения не совпадают");
        driver.quit();
    }

}

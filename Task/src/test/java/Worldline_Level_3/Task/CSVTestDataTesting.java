package Worldline_Level_3.Task;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CSVTestDataTesting {
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "C:\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        String loginUrl = "https://thinking-tester-contact-list.herokuapp.com/";
        String csvFilePath = "E:\\task 2\\testdata.csv";
        String username = "sklementz0@archive.org";
        String password = "abcd1234";

        try {
            driver.get(loginUrl);
            WebElement usernameField = driver.findElement(By.xpath("//input[@id='email']"));
            WebElement passwordField = driver.findElement(By.xpath("//input[@id='password']"));
            WebElement loginButton = driver.findElement(By.xpath("//button[@id='submit']"));

            usernameField.sendKeys(username);
            passwordField.sendKeys(password);
            loginButton.click();
            Thread.sleep(2000); 
            WebElement addContactButton = driver.findElement(By.xpath("//button[@id='add-contact']"));
            addContactButton.click();
            try (BufferedReader br = new BufferedReader(new FileReader(csvFilePath))) {
                String line;
                br.readLine();
                while ((line = br.readLine()) != null) {
                    String[] testData = line.split(",");

                    String firstName = testData[0];
                    String lastName = testData[1];
                    String dobString = testData[2];
                    String email = testData[3];
                    String phone = testData[4];
                    String address1 = testData[5];
                    String address2 = testData[6];
                    String city = testData[7];
                    String state = testData[8];
                    String postalCode = testData[9];
                    String country = testData[10];

                    SimpleDateFormat originalFormat = new SimpleDateFormat("MM/dd/yyyy");
                    SimpleDateFormat targetFormat = new SimpleDateFormat("yyyy-MM-dd");
                    Date dobDate = originalFormat.parse(dobString);
                    String dob = targetFormat.format(dobDate);

                    WebElement firstNameField = driver.findElement(By.xpath("//input[@id='firstName']"));
                    WebElement lastNameField = driver.findElement(By.xpath("//input[@id='lastName']"));
                    WebElement dobField = driver.findElement(By.xpath("//input[@id='birthdate']"));
                    WebElement emailField = driver.findElement(By.xpath("//input[@id='email']"));
                    WebElement phoneField = driver.findElement(By.xpath("//input[@id='phone']"));
                    WebElement address1Field = driver.findElement(By.xpath("//input[@id='street1']"));
                    WebElement address2Field = driver.findElement(By.xpath("//input[@id='street2']"));
                    WebElement cityField = driver.findElement(By.xpath("//input[@id='city']"));
                    WebElement stateField = driver.findElement(By.xpath("//input[@id='stateProvince']"));
                    WebElement postalCodeField = driver.findElement(By.xpath("//input[@id='postalCode']"));
                    WebElement countryField = driver.findElement(By.xpath("//input[@id='country']"));

                    firstNameField.sendKeys(firstName);
                    lastNameField.sendKeys(lastName);
                    dobField.sendKeys(dob);
                    emailField.sendKeys(email);
                    phoneField.sendKeys(phone);
                    address1Field.sendKeys(address1);
                    address2Field.sendKeys(address2);
                    cityField.sendKeys(city);
                    stateField.sendKeys(state);
                    postalCodeField.sendKeys(postalCode);
                    countryField.sendKeys(country);

                    WebElement submitButton = driver.findElement(By.xpath("//button[@id='submit']"));
                    submitButton.click();

                    Thread.sleep(2000); 

                    String actualUrl = driver.getCurrentUrl();
                    if (actualUrl.equals("https://thinking-tester-contact-list.herokuapp.com/contactList")) {
                        System.out.println("Contact added successfully for: " + firstName + " " + lastName);
                    } else {
                        System.out.println("Failed to add contact for: " + firstName + " " + lastName);
                    }
                    Thread.sleep(2000);
                    WebElement navigateBackButton = driver.findElement(By.xpath("//button[@id='add-contact']"));
                    navigateBackButton.click();
                }
            }
        } catch (IOException | InterruptedException | ParseException e) {
            e.printStackTrace();
        }
        driver.quit();
    }
}

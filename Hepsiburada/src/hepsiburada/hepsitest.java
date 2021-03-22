package hepsiburada;


import java.util.List;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;


import java.util.concurrent.TimeUnit;


public class hepsitest {
	
	public WebDriver driver;
	boolean hasReview=true;
	//String[] sortListName = {"Varsayýlan","En yeni deðerlendirme","En faydalý deðerlendirme","Puana göre azalan","Puana göre artan"};
			
	
	public void hbWebPageTestCases() {

       try {
    	   openChrome();
    	   case1();
    	   case2();
   		

        } catch (Exception exception) {
            exception.printStackTrace();
        } finally {
            /*
        	if (driver != null)
               driver.quit();
            driver.close();
            */
        }
        
    }
	
	
		public void  case1(){
			gotoHepsiBuradaMainPage();
			hbSearchProduct();
			selectProduct();
			gotoEvaluation();
			firstEvaluationClickYes();
		}
		
		public void case2()
		{
			gotoHepsiBuradaMainPage();
			hbSearchProduct();
			selectProduct();
			gotoEvaluation();
			checkOrderDropbox();
		}
		
		
	 private void openChrome() {
	       //setProperty for chrome browser
	        System.setProperty("webdriver.chrome.driver","C:/Users/Burak/test/chromedriver_win32/chromedriver.exe");
	        ChromeOptions options = new ChromeOptions();
	        options.addArguments("--disable-notifications");
	        
	        driver = new ChromeDriver();
	        
	        // Waits the elements for 20 seconds
	        driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
	        driver.manage().window().maximize();
	    }
	
	//Hepsiburada main page
	private void gotoHepsiBuradaMainPage() {
        driver.get("https://www.hepsiburada.com");
        if (driver.getCurrentUrl().contains("https://www.hepsiburada.com/")) {
            System.out.println("Hepsiburada sayfasina gidildi");
        } else {
            throw new WebDriverException("Hepsiburada sayfasý acilamadi");
        }
	}
	
	//Search Product for iphone key.
	 private void hbSearchProduct() {
	        driver.findElement(By.xpath("//*[@id=\"SearchBoxOld\"]/div/div/div[1]/div[2]/input")).sendKeys("iphone");
	        driver.findElement(By.cssSelector("div.SearchBoxOld-buttonContainer")).click();
	        System.out.println("Aramaya iphone kelimesi girildi");
	  
	    }
	 
	//Search control for product 
	 private void hbSearchProductControl() {
	        if (driver.getCurrentUrl().contains("ara") || driver.getCurrentUrl().contains("iphone")) {
	            System.out.println("Aramanýz bulundu");
	            System.out.println("-----------------------------");
	        } else {
	            System.out.println("Aramanýz basarisiz oldu");
	            System.out.println("-----------------------------");
	        }
	    }
	 
	
	 //Select first product
	 private void selectProduct() {
		 driver.findElement(By.xpath("(//li[contains(concat(' ', normalize-space(@class), ' '), 'search-item')])[1]//a")).click();
		// driver.findElement(By.xpath("/html/body/div[3]/main/div[2]/div/div/div/div/div[2]/section/div[1]/div[4]/div/div/div/div/ul/li[1]/div/a")).click();
		 System.out.println("Ürün seçildi");
	 }
	 
	 //Go to Evalation tab.
	 private void gotoEvaluation()
	 {
		 driver.findElement(By.id("productReviewsTab")).click();
		 System.out.println("Degerlendirmeye gidildi");
		 
		 /*
		  * True if the element exists on the page, false otherwise
		  * If there is no element for hasReview it is set to true, if there is an element it is set to false.
		  */
		 hasReview=!isElementPresent(By.xpath("//span[@class='hermes-ProductRate-module-QusM-']"));
		
		 //Check !hasReview
		 if(!hasReview)
		 {
			 System.out.println("Urunun degerlendirmesi yok");
		 }
	 }
	 
	 //First Evaluation click yes.
	 private void firstEvaluationClickYes()
	 {
		 //Is there any element on the page, check it.
		 if(hasReview)
		 {
			 driver.findElement(By.xpath("(//div[@class=\"hermes-ReviewCard-module-tAGUS\"])[1]")).click();
			 String evaluationMsg = driver.findElement(By.xpath("//*[@id=\"hermes-voltran-comments\"]/div[3]/div[3]/div/div[1]/div[2]/div[4]/div[2]/span[2]")).getText();
			 //Control keyword ("Teþekkür Ederiz.")
			 Assert.assertEquals("Teþekkür Ederiz.", evaluationMsg);
			 System.out.println("Ýlk deðerlendirmenin evet butonuna basýldý. Case1 tamamlandý.");
		 }else
		 {
			 System.out.println("Degerlendirme olmadýgý icin evete týklanýlmadý.");
		 }
		 
		 
	 }
	 
	 //check dropbox list
	 private void checkOrderDropbox()
	 {
		 if(hasReview)
		 {
			 driver.findElement(By.xpath("(//div[@class=\"hermes-Sort-module-IHhTs\"])[1]")).click();
			 List<WebElement> myElements = driver.findElements(By.xpath("//*[@id=\"hermes-voltran-comments\"]/div[3]/div[1]/div[2]/div[2]/div[2]/div/div[2]/div"));
			 
			 for(WebElement e : myElements)
			 {	
				 /*
				 int i =0;
				 String tmp =e.getText();
				 if(tmp==sortListName[i])
				 {
					 System.out.println(sortListName[i] +" mevcut.");
				 }else
				 {
					 System.out.println("Sýralamadaki metinler bulunamadý.");
				 }
				 //System.out.println(e.getText());
				 i++;*/
				 
				 System.out.println(e.getText());
			 }
		 }else{
			 System.out.println("Degerlendirme olmadýgý icin sýralama yapýlamadý.");
		 }
		
	 }
	 
	 //Check Element
	 public boolean isElementPresent(By locatorKey) {
		    try {
		        driver.findElement(locatorKey);
		        return true;
		    } catch (org.openqa.selenium.NoSuchElementException e) {
		        return false;
		    }
		}
	 

}


	
	

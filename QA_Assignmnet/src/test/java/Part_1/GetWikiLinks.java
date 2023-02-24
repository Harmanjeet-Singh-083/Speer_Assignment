package Part_1;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.opencsv.CSVWriter;

public class GetWikiLinks {
	
	// Method to check Wikilink URL
	
	
	
	public static boolean isWikiLink(String url) {
		    return url.startsWith("https://en.wikipedia.org/wiki/");
		}
	
	 @Test (priority =1)
	public static void getValues() throws IOException{
		 Scanner scanner = new Scanner(System.in);
		    System.out.println("Enter the URL ");
		    String url = scanner.nextLine();
		    System.out.println("Enter the number of cycle ");
		    int number = scanner.nextInt();
		    while (number < 1 || number > 20) {
		        System.out.print("Enter a number between 1 and 20: ");
		        number = scanner.nextInt();
		    }
		    System.out.println("The number you entered is: " + number);
		    
			  // Webdriver setup 
			   System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
		        WebDriver driver = new ChromeDriver();	   
			   driver.get(url);
			   
				if (isWikiLink(url))
					{
					
					System.out.println("The URL is a Wikipedia link");
					}
				else {
				System.out.println("its not wikipedia link");
				driver.quit();
				throw new AssertionError("The URL is not a Wikipedia link");
				}
				// Create an ArrayList to store the links
		        List<String> links = new ArrayList<String>();
		        links.add(url);
		        int Start =0;
		        System.out.println(links.size());
		        //ArrayList<String> links2 = scrapeLinks(driver, url);
		       //for (String lin : links2) {
		       //links.add(lin);
		      //}
		        //for (String input : links) {
		        for (int i=1 ; i<=number; i++) {
		        	int FinalLength=links.size();
		        	 
		        for (int j=Start ; j <=FinalLength-1;j++) {
		        	Start= FinalLength;
		        String Url2= links.get(j);
		        ArrayList<String> links3 = scrapeLinks(driver, Url2);
		        for (String lin : links3) {
		            links.add(lin);
		        }
		         
		        }
		        
		        }
		       //links3. clear();
		       // }*/
		        
		        /*/ Create array to add all links from the page 
	 List<WebElement> newlinks = driver.findElements(By.tagName("a"));
	  for( WebElement e : newlinks) {
		String href = e.getAttribute("href");
        if (href != null && href.startsWith("https://en.wikipedia.org/wiki/")) {
           links.add(href);}*/
        
		    
	 
	  
	  
	// Print all the links
      
      FileWriter outputfile = new FileWriter("data.csv)");
      CSVWriter writer = new CSVWriter(outputfile);
      
        

      
      System.out.println("Total number of links found: " + links.size());
      for (String link : links) { 
          System.out.println(link);
          writer.writeNext(new String[] {link});
      }
      System.out.println("Total number of links found: " + links.size());
   driver.close();
	 }
	 public static ArrayList<String> scrapeLinks(WebDriver driver, String url) {
	        ArrayList<String> links = new ArrayList<String>();
	        
	        try {
	        	driver.get(url);
	        }
	        catch (StaleElementReferenceException e){
	        	return links;
	        }
	        
	        for (WebElement link : driver.findElements(By.tagName("a"))) {
	           try { String href = link.getAttribute("href");
	            if (href != null && href.contains("wikipedia.org/wiki/")) {
	                links.add(href);
	                System.out.println(href);
	            }
	           }
	           catch(StaleElementReferenceException e)
	           
	           {continue;
	        	   /*String href = link.getAttribute("href");
		            if (href != null && href.contains("wikipedia.org/wiki/")) {
		                links.add(href);
		                System.out.println(href);
		            }*/}
	           }
	        
	        return links;
	    }
	}


		
		


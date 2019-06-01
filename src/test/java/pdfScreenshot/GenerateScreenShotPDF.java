package pdfScreenshot;

import java.io.FileOutputStream;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import com.itextpdf.text.Document;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import io.github.bonigarcia.wdm.WebDriverManager;

public class GenerateScreenShotPDF {
	
	public static void main(String[] args) throws Exception {
		
		WebDriverManager.chromedriver().setup();
		
		String path = System.getProperty("user.dir");
		System.out.println(path);
		
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.google.com");

		// Capture screenshot and store it in byte[] array format
		byte[] input = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
		Document document = new Document();
		String output = path + "/images/" + "Image" + ".pdf";
		FileOutputStream fos = new FileOutputStream(output);

		// Instantiate the PDF writer
		PdfWriter writer = PdfWriter.getInstance(document, fos);

		// open the pdf for writing
		writer.open();
		document.open();

		// process content into image
		Image im = Image.getInstance(input);

		// set the size of the image
		im.scaleToFit(PageSize.A4.getWidth() / 2, PageSize.A4.getHeight() / 2);

		// add the captured image to PDF
		document.add(im);
		document.add(new Paragraph(" "));

		// close the files and write to local system
		document.close();
		writer.close();
		driver.close();
		System.out.println("Done");
	}
}
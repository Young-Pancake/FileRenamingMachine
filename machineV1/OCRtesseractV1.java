package machine;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

import net.sourceforge.tess4j.Tesseract;

public class OCRtesseractV1 extends MainMachine {
	String[] pathOCR;
	File fileOCR;
	Tesseract tesseract;
	public synchronized void MainOCRtesseract(){
		tesseract = new Tesseract(); 
		//here is where you add the language you need
		tesseract.setLanguage("select a language as recommended by OCR tesseract");
		int y=0;
		try { 
			fileOCR= new File("jpgFiles directory" + "\\");
			pathOCR= fileOCR.list();
			tesseract.setDatapath("directory for the OCR Tesseract with the end path similar to: \\Tess4J-3.4.8-src\\Tess4J\\tessdata"); 

			/*the path of your tests data folder 
			  inside the extracted file*/
			for(String paths: pathOCR) {
				
					String text 
					= tesseract.doOCR(new File("jpgFiles directory" + "\\" + paths)); 
					// path of your image file 
					System.out.print(text); 
					Files.write(Paths.get("txtFiles directory" + "\\" + 
							paths.substring(0, pathOCR[y].length()-4) + ".txt"), text.getBytes());
					y++;
				 }
					y=0;
		}
		catch (Exception e) { 
			e.printStackTrace(); 
		}
			
	}
}



package machine;

import java.io.IOException;	

public class MainMachineV1{
	static  Detector de = new Detector();
	static  PdfToJpgTransformer ptjt = new PdfToJpgTransformer();
	static  OCRtesseract ocrt = new OCRtesseract();
	static  FileMover fm = new FileMover();
	static  TextMerger tm = new TextMerger();
	static  KeywordExtractor ke = new KeywordExtractor();
	static int x=1;
	public static void main(String[] args) throws InterruptedException, IOException {
		while(true) {
			de.MainDetector();
			if(de.paths.length>0) {
				ptjt.MainPdfToJpgTransformer();
				ocrt.MainOCRtesseract();
				fm.pdfMover();
				tm.MainTextMerger();
				ke.MainKeywordExtractor();
				fm.JpgRemover();
				fm.TxtRemover();
				fm.TextMergerRemover();
				System.out.println("---------------------------------");	
				System.out.println("End of " + x + " Cycles");
				System.out.println();
				x++;
			}
		}
	}
}

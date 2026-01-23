## FileRenamingMachine

 A Java program that takes pdf files and renames them based of customizable parameters.

# Motivation

 While working for a job that had too much manual logging of file names by physically 
reading them I thought, "What if you can automate it?" And so here it is!

# Goal

 To be able to automate a specific mundane task(which is manually renaming files), so 
 that you can do something more useful while it runs in the background.

# To get started
```bash
git clone https://github.com/Young-Pancake/FileRenamingMachine
cd FileRenamingMachine
```

# 🚀 Quick Start 
This is the heart of the program, with everything getting done in a infinite loop.
``` Java
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
```
# Usage

 The FileREnamingMachine can be used for any job requiring changing names of files, or even
 for personal use if you have a project that needs lots of files to be renamed.
 
 # Contributing

  Feel free to clone or fork this project. I would love to see any kind of criticism.
  Best regards, and have fun!


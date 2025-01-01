I will explain to you the parts of this program and how they function with each other.|

Bear in mind that this is very experimental, but hopefully in time there will be a better 
way to implement this program so that it is easy for everyone to use. 

There are seven parts as of the first version of this program and they consists of:
MainMachine, Detector, PdfToJpgTransformer, OCRtesseract, FileMover, TextMerger, KeywordExtractor.

1-MainMachine:
  The main hub for all that happpens. However there is not the much to it, it simply consists of
  infinite while loop and it contains the classes that will perform all the transactions.
  to have the program not go insane and loop immediatly we use the detector class.
  
2-Detector:
  This class is also rather simple, its job is to detect if there is a file inside of a 
  directory (check NOTES). It will not act until there is one or more files inside the 
folder and then the program can commense.
  
3-PdfToJpgTransformer:
  
4-OCRtesseract:
5-FileMover:
6-TextMerger:
7-KeywordExtractor:

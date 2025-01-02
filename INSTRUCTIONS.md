I will explain to you the parts of this program and how they function with each other.

Bear in mind that this is very experimental, but hopefully in time there will be a better 
way to implement this program so that it is easy for everyone to use. 

FOR STARTERS:
  This program will make transactions inside of file directories.
  the way that it is setup is as follows:
  there is a "home" file that you will put the files to be renamed in. 
  Afterwards it moves around to the other files until it reaches 
  one of two types or file directories. The first is the one that is
  considered the "base' directory and it's where the files that have 
  not been fully renamed go to. The file is called "correction" for 
  this reason. The second end directory is the one where after detecting
  its kind will it sort it. So let's say there's a file and its type is
  a reciept, its end directory will be "RecieptEndDirectory" or something
  like that.
  The files will be all in one place so it is easier to work with and they
  go like this:
  home, pdfFile, garbageCan, jpgFiles, txtFiles, textMerger, Correction,
  FinalSortedDirectory1, FinalSortedDirectory2, FinalSortedDirectory(and so on).

There are seven parts(classes) as of the first version of this program and they consists of:
MainMachine, Detector, PdfToJpgTransformer, OCRtesseract, FileMover, TextMerger, KeywordExtractor.

1-MainMachine:
  The main hub for all that happpens. However there is not the much to it, it simply consists of
  infinite while loop and it contains the classes that will perform all the transactions.
  to have the program not go insane and loop immediatly we use the detector class.
  
2-Detector:
  This class is also rather simple, its job is to detect if there is a file inside of a 
  directory. It will not act until there is one or more files inside the 
folder and then the program can commense.
  
3-PdfToJpgTransformer:
  
4-OCRtesseract:

5-FileMover:

6-TextMerger:

7-KeywordExtractor:

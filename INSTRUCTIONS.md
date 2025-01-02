I will explain to you the parts of this program and how they function with each other.

Bear in mind that this is very experimental, but hopefully in time there will be a better 
way to implement this program so that it is easy for everyone to use. 

FOR STARTERS:
  We will be using dependancies so when you open a new project make
  sure that it is a maven project.
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

  The files that you will manually create will be all in one place so it 
is easier to work with and they go like this:
  home(where you put the starting files), pdfFile, garbageCan, jpgFiles, 
txtFiles, textMerger, Correction, FinalSortedDirectory1, FinalSortedDirectory2,
FinalSortedDirectory(and so on).

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
  We will be use apach.pdfbox for this step. 
  This is the link: https://pdfbox.apache.org/
  the code is simple, all you need to do is modify the directories. 
  What it does it take the pdf file and section it to its pages, and 
  then it converts it to jpg files and it will end up in the JpgFiles 
  directory.
  
4-OCRtesseract:
  This is with class that will be doing all the image to text conversion.
  we will be using tesseract for java. 
  here is the link: https://sourceforge.net/projects/tess4j/
  there will be a source directory that is responsible for the directory
  that contains the files for tesseract, and there will be another 
  directory that connects to the files(notes in the code).

5-FileMover:
  This part will be responsible for moving files from one place to 
  another. At the end of a cycle all the unneeded files will go to the
  garbage disposal file and must be manually deleted(this will hopefully not 
  be the case in the coming versions where it would automatically delete all 
  of the unneeded files).

6-TextMerger:
  This class is also simple, as the name implies it will merge all of the 
  text files that have been created bythe PdfToJpgTransformer and put them in
  the textMerger directory.
  
7-KeywordExtractor:
  This part will house all of the logical statements that we will need to make 
  so that the program can take the text file that contains all of the information
  and get all of what we need from it. This will be done using regular expressions.
  Bear in mind that this could become very tricky for some and may take some to get
  the hang of, that being said it is doable. the first thing it does is to determine
  the pattern number (let's say that you have many types of files and the way you 
  would distinguish them is by a certain word or a combination of words. If a program 
  is to find the word then will will know what type of data it will remane the original 
  file as. Maybe you have a reciept so you know that the output format will be 
  "date, type, sender", or you may have a letter so you know that the output will be 
  "date, sender, reciever"). Afterwards it will go to the next methods.
  there are three methods that can be used:
  The first is the date keyword extractor, which gets you the date and outputs it as 
  YYMMDD. The next is "FindWordAndSetToSomethingSpecific" where you will find a word 
  and output something that is not that word(let's say you found a book so you output 
  library). The last one is "FindWordAndSetAsIs" where as it suggests it finds the word 
  and outputs it. (Hopefully in the upcoming versions there will be a more simple method
   to take in statements from a user and it will change the code itself, a sort of
   "setup wizard").




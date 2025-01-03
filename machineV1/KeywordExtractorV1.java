package machine;
import java.util.regex.*;
import java.util.stream.Collectors;
import java.io.*;

public class KeywordExtractorV1 extends MainMachine{
	FileMoverV1 fm;
	//these will be the parts that build up the text the will rename the PDF file later on
	//it is not needed for them to be this many but these are just an example of how it could
	//be done
	static String dateValue = "null";
	static String type1 = "null";
	static String type2 = "null";
	//and so on
	
	static String parameter;
	public void MainKeywordExtractor() throws IOException {
		fm= new FileMoverV1();
		
		//This is an example of a set of if-else statements that are in levels, each level can be
		//determined by a number. It could also have a nested-if it needs to be more specified 
		if(determinePatternNumber()==1) {
			DatePatternExtractor();System.out.print("_");FindWordAndSetToSomethingSpecific();System.out.print("_");FindWordAndSetAsIs();
			if(dateValue!="null" & type1!="null" & type2!="null") {
				parameter = dateValue+"_"+type1+"_"+type2;
				fm.fileRenamer(parameter);
				fm.Mover1();
				}else {System.out.print("Error in pattern One!");}
			}
		else if(determinePatternNumber()==2) {
			DatePatternExtractor();System.out.print("_");System.out.print("_");FindWordAndSetAsIs();
		if(type2!="null") {
			parameter = type2;
			fm.fileRenamer(parameter);
			fm.Mover2();
			}else {System.out.print("Error in pattern Two!");}
		}
	}
	
	public int determinePatternNumber() throws IOException {
		String output = "textMerger directory" + "\\output.txt";

		// Patterns 
		Pattern patternOne = Pattern.compile("KeywordOne", Pattern.CASE_INSENSITIVE);
		Pattern patternTwo = Pattern.compile("KeywordTwo", Pattern.CASE_INSENSITIVE);
	

		String content = readFileContent(output);

		if (patternOne.matcher(content).find()) {
			return 1;
		} else if (patternTwo.matcher(content).find()) {
			return 2;
			// could add extra such as: else if (patternTwo.matcher(content).find()) {
			//return 3; 

		} else {
			return -1; // No matching pattern 
		}
	}
	private String readFileContent(String filePath) throws IOException {
		try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
			return reader.lines().collect(Collectors.joining("\n"));
		}
	}
	public void DatePatternExtractor() {
		String output = "textMerger directory" + "\\output.txt";
		
		//This is a regular expression that can "Generally" help out in finding date 
		//format in most documents this could of course not be the case for some
		//circumstances so I highly recommend to see what fits you best 
		String regexDate = "\\d{2}(\\.|\\,|\\-)(\\d{2}|Jan|JAN|Feb|FEB|Mar|MAR|Apr|APR|May|MAY|Jun|JUN|Jul|JUL|Aug|AUG|Sep"
				+ "|SEP|Oct|OCT|Nov|NOV|Dec|DEC)(\\.|\\,)(\\d{4}|\\d{2})";

	//we use regular expressions here to try to find the word that can mean date such as "RecieptDate" or
	//it could just be written as "Date" and then try to find the format for said keyword which could
	//be DD/MM/YY or DD/MM/YYYY. So in short it finds a word and then goes searching after the place in which 
	//the point was discovered at and then it goes and searches for a format that fits the requirement
		Pattern RecieptDatePattern = Pattern.compile("(?i)(^\\s*RecieptDate:?\\s*$|(?<=\\s)RecieptDate:?\\s*(" + regexDate + "))");
		Pattern DatePattern = Pattern.compile("(?i)(^\\s*Date:?\\s*$|(?<=\\s)Date:?\\s*(" + regexDate + "))");

		try (BufferedReader reader = new BufferedReader(new FileReader(output))) {
			StringBuilder content = new StringBuilder();
			String line;
			while ((line = reader.readLine()) != null) {
				content.append(line).append("\n");
			}
			String contentStr = content.toString();
			String extractedDate = null;

			
			Matcher RecieptDateMatcher = RecieptDatePattern.matcher(contentStr);
			Matcher DateMatcher = DatePattern.matcher(contentStr);

			if (RecieptDateMatcher.find()) {
				
				if (RecieptDateMatcher.group(2) != null) {
					extractedDate = RecieptDateMatcher.group(2);
				} else if (RecieptDateMatcher.group(3) != null) {
					extractedDate = RecieptDateMatcher.group(3);
				} else if (RecieptDateMatcher.group(4) != null) {
					extractedDate = RecieptDateMatcher.group(4);
				}
			}else if (DateMatcher.find()) {
				// Extract date from group 2, 3, or 4
				if (DateMatcher.group(2) != null) {
					extractedDate = DateMatcher.group(2);
				} else if (DateMatcher.group(3) != null) {
					extractedDate = DateMatcher.group(3);
				} else if (DateMatcher.group(4) != null) {
					extractedDate = DateMatcher.group(4);
				}
			}
			
			//this is for the formatting that will make the end date be set to the
			// format of YYMMDD. It could be modified if needed depending on preference
			if (extractedDate != null && extractedDate.length() >= 8) {
				String formattedDate = formatDateString(extractedDate);
				if (formattedDate != null) {
					System.out.print(formattedDate);
					dateValue = formattedDate;
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	private String formatDateString(String date) {
		// Assuming the date is in dd.MM.yy or dd.MM.yyyy format
		String day = date.substring(0, 2);
		String month = date.substring(3, 6);
		String year = null;

		// Convert month abbreviation to numeric format
		switch (month) {
		case "Jan":
		case "JAN": month = "01"; break;
		case "Feb":
		case "FEB": month = "02"; break;
		case "Mar":
		case "MAR": month = "03"; break;
		case "Apr":
		case "APR": month = "04"; break;
		case "May":
		case "MAY": month = "05"; break;
		case "Jun":
		case "JUN": month = "06"; break;
		case "Jul":
		case "JUL": month = "07"; break;
		case "Aug":
		case "AUG": month = "08"; break;
		case "Sep":
		case "SEP": month = "09"; break;
		case "Oct":
		case "OCT": month = "10"; break;
		case "Nov":
		case "NOV": month = "11"; break;
		case "Dec":
		case "DEC": month = "12"; break;
		default: month = date.substring(3, 5); break; 
		}

		if (date.length() == 11) {
			year = date.substring(7, 11); 
		} else if (date.length() == 10) {
			// Format: dd.MM.yyyy
			year = date.substring(6, 10); 
		} else if (date.length() == 9) {
			year = "20" + date.substring(7, 9); 
		} else if (date.length() == 8) {
			// Format: dd.MM.yy
			year = "20" + date.substring(6, 8); 
		}

		year = year.substring(2, 4);

		return year + month + day;
	}
	
	//This is a helpful type of method in which you may want to find a certain word but 
	//don't want it to save it as that keyword. Like for example you find the word
	// "lake" and you want it then to save the word "Fish"
	public void FindWordAndSetToSomethingSpecific() {
		String output = "textMerger directory" + "\\output.txt";


		Pattern PatternOne = Pattern.compile("KeywordOne",Pattern.CASE_INSENSITIVE);
		Pattern PatternTwo = Pattern.compile("KeywordTwo",Pattern.CASE_INSENSITIVE);

		try (BufferedReader reader = new BufferedReader(new FileReader(output))) {
			StringBuilder content = new StringBuilder();
			String line;
			while ((line = reader.readLine()) != null) {
				content.append(line).append("\n");
			}
			String contentStr = content.toString();

			// Determine the presence of keywords
			Matcher MatcherOne = PatternOne.matcher(contentStr);
			Matcher MatcherTwo = PatternTwo.matcher(contentStr);
			//and so on
			boolean hasPatterOne = MatcherOne.find();
			boolean hasPatterTwo = MatcherTwo.find();
			//and so on

			//example of if-else statements where the more specific the earlier
			if (hasPatterOne & hasPatterTwo) {System.out.print("SchlussRNG");
			type1= type2 ="TypeOneAndTypeTwo";}
			else if (hasPatterOne) {System.out.print("AbschlagRNG");
			type1="TypeOne";} 
			else if (hasPatterTwo) {System.out.print("JahresRNG");
			type2="TypeTwo";}
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	//this method is much simpler. Find the keyword and save that keyword as is. 
	//there is also some cutting in between to do depending on how you'll use the
	//regular expressions which may save the words with them but generally it 
	//should be find as there is a part in the code that removes most of the 
	//useless parts leaving you with a clean word
	public void FindWordAndSetAsIs() {
		String output = "textMerger directory" + "\\output.txt";
		
		String[] keywords = {"\\WordOne\\s", "\\WordTwo\\s", "\\WordThree\\s"}; //and so on
		for (String keyword : keywords) {
			if (keyword.isEmpty()) continue; // Skip empty keywords
			Pattern pattern = Pattern.compile(keyword, Pattern.CASE_INSENSITIVE);
			try (BufferedReader reader = new BufferedReader(new FileReader(output))) {
				StringBuilder content = new StringBuilder();
				String line;
				while ((line = reader.readLine()) != null) {
					content.append(line).append("\n");
				}

				String contentStr = content.toString();
				Matcher matcher = pattern.matcher(contentStr);
				boolean hasPattern = matcher.find();
				if (hasPattern && determinePatternNumber()!=1) {
					String word= keyword.replaceAll("[\\?\\(\\\\)\\*\\[\\]\\_\\=]|(?<=\\\\)s|(?<=\\\\)b","").replaceAll("[\\.(\\\\)]","-");
					System.out.print(word);
					type2=word;
					break;
				} 
			} 
			catch (IOException e) {
				e.printStackTrace();
				}
			}
		}
	}




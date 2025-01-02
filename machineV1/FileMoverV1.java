

package machine;

import java.io.*;


public class FileMoverV1 extends MainMachine{
	static String[] TxtPaths;
	static String[] TextMergerPaths;
	static String[] JpgPaths;
	static String[] pdfPaths;
	static String[] correctionPaths;
	static String[] EndDestinationPaths1;
	static String[] fileEndDestinationPaths2;
	static File filepdf;
	static File fileTxt;
	static File fileTextMerger;
	static File fileJpg;
	static File fileKorrektur;
	static File fileEndDestination1;
	static File fileEndDestination2;
// you could of course add more end destinations and they will be sorted later on in the KeywordExtractor
	
	public void pdfMover() throws IOException{
		int y=0;
		filepdf = new File("home directory" + "\\");
		pdfPaths = filepdf.list();
		if(pdfPaths.length>0) {

			filemovetoanotherfolder("home directory" + "\\","pdfFile directory" + "\\",pdfPaths[y]);
			y++;
		}	else {	y=0;}
	}
	public void JpgRemover() throws IOException {
		fileJpg = new File("jpgFiles directory" + "\\");
		JpgPaths = fileJpg.list();
		if(JpgPaths.length>0) {
			for(String pathJpg: JpgPaths) {

				filemovetoanotherfolder("jpgFiles directory" + "\\","garbageCan directory" + "\\",pathJpg);
			}
		}
	}
	public void TxtRemover() throws IOException {
		fileTxt = new File("txtFiles directory" + "\\");
		TxtPaths = fileTxt.list();
		if(TxtPaths.length>0) {
			for(String pathtxt: TxtPaths) {

				filemovetoanotherfolder("txtFiles directory" + "\\","garbageCan directory" + "\\",pathtxt);
			}
		}
	}
	public void TextMergerRemover() throws IOException {
		fileTextMerger = new File("textMerger directory" + "\\");
		TextMergerPaths = fileTextMerger.list();
		if(TextMergerPaths.length>0) {
			for(String pathtextmerger: TextMergerPaths) {

				filemovetoanotherfolder("textMerger directory" + "\\","garbageCan directory" + "\\",pathtextmerger);
			}
		}
	}
	//this will be the base path that will be used in the case of an incomplete file
	public void CorrectionMover() throws IOException {
		fileKorrektur = new File("C:\\Users\\saleh\\Downloads\\java\\pdfFile\\");
		correctionPaths = fileKorrektur.list();
		if(correctionPaths.length>0) {
			for(String path: correctionPaths) {

				filemovetoanotherfolder("pdfFile directory" + "\\","Correction directory" + "\\",path);
			}
		}
	}
	public void Mover1() throws IOException {
		fileEndDestination1 = new File("pdfFile directory" +"\\");
		EndDestinationPaths1 = fileEndDestination1.list();
		if(EndDestinationPaths1.length>0) {
			for(String path: EndDestinationPaths1) {

				filemovetoanotherfolder("pdfFile directory" +"\\","Mover1 directory" + "\\",path);
			}
		}
	}
	public void Mover2() throws IOException {
		fileEndDestination2 = new File("pdfFile directory" +"\\");
		fileEndDestinationPaths2 = fileEndDestination2.list();
		if(fileEndDestinationPaths2.length>0) {
			for(String path: fileEndDestinationPaths2) {

				filemovetoanotherfolder("pdfFile directory" +"\\","Mover2 directory" + "\\",path);
			}
		}
	}

	boolean filemovetoanotherfolder(String sourcefolder, String destinationfolder, String filename) throws IOException {
		boolean ismove = false;
		InputStream inStream = null;
		OutputStream outStream = null;
		try {

			File afile = new File(sourcefolder + filename);
			File bfile = new File(destinationfolder + filename);

			inStream = new FileInputStream(afile);
			outStream = new FileOutputStream(bfile);

			byte[] buffer = new byte[1024 * 4];

			int length;
			// copy the file content in bytes
			while ((length = inStream.read(buffer)) > 0) {

				outStream.write(buffer, 0, length);

			}
			inStream.close();
			outStream.close();
			// delete the original file
			if(afile.exists()) {
				afile.delete();
				ismove = true;}

		} catch (IOException e) {
			e.printStackTrace();
		}
		return ismove;
	}
	
	//this will be mainly used in the KeywordExtractor class
	public void fileRenamer(String parameter) {
		File file = new File("pdfFile directory" +"\\"); 
		pdfPaths = file.list();
		if(pdfPaths.length>0) {
			for(String pathPdf: pdfPaths) {
				File PdfFile = new File("pdfFile directory" +"\\"+pathPdf); 


				File rename = new File("pdfFile directory" +"\\"+parameter+".pdf"); 


				boolean flag = PdfFile.renameTo(rename); 

				if (flag == true) { 
					System.out.println("File Successfully Renamed"); 
				} 
				else { 
					System.out.println("File Rename Failed"); 
				} 
			}
		}
	}
}



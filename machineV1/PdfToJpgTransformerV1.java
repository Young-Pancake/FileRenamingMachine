package machine;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.List;
import javax.imageio.ImageIO;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;

public class PdfToJpgTransformerV1 extends MainMachine {
    String[] paths;
    File file;
    String sourceDir;
    String destinationDir;

    public synchronized void MainPdfToJpgTransformer() {
        int y = 0;
        PDDocument document = null;
        try {
            file = new File("home directory" + "\\");
            paths = file.list();
            if (paths.length > 0) {
                sourceDir = "home directory" + "\\" + paths[y]; 
                destinationDir = "jpgFiles directory" + "\\"; 
                
                File sourceFile = new File(sourceDir);
                File destinationFile = new File(destinationDir);
                if (!destinationFile.exists()) {
                    destinationFile.mkdir();
                    System.out.println("Folder Created -> " + destinationFile.getAbsolutePath());
                }
                if (sourceFile.exists()) {
                    System.out.println("Images copied to Folder: " + destinationFile.getName());

                    document = PDDocument.load(sourceFile);
                    @SuppressWarnings("unchecked")
					List<PDPage> list = document.getDocumentCatalog().getAllPages();
                    System.out.println("Total files to be converted -> " + list.size());

                    String fileName = sourceFile.getName().replace(".pdf", "");
                    int pageNumber = 1;
                    for (PDPage page : list) {
                        BufferedImage image = page.convertToImage();
                        File outputfile = new File(destinationDir + fileName + "_" + pageNumber + ".jpg");
                        System.out.println("Image Created -> " + outputfile.getName());
                        ImageIO.write(image, "jpg", outputfile);
                        pageNumber++;
                    }

                    System.out.println("Converted Images are saved at -> " + destinationFile.getAbsolutePath());
                } else {
                    System.err.println(sourceFile.getName() + " File not exists");
                }
                y++;
            } else {
                y = 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (document != null) {
                try {
                    document.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
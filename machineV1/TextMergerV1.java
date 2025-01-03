package machine;

import java.io.*;
import java.util.Arrays;

public class TextMergerV1 extends MainMachine {
    File dir;
    String[] fileNames;

    public void MainTextMerger() throws IOException {
        File sourceDir = new File("txtFiles directory" + "\\");
        fileNames = sourceDir.list();

        File mergedFile = new File("textMerger directory" + "\\output.txt");
        if (!mergedFile.exists()) {
            mergedFile.createNewFile();
        }

        try (PrintWriter pw = new PrintWriter(new FileWriter(mergedFile))) {
            String[] files = sourceDir.list();
            System.out.println("List of files -> " + Arrays.asList(files));

            for (String fileName : files) {
                File file = new File(sourceDir, fileName);

                // Using try-with-resources to ensure BufferedReader is closed properly
                try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                    String line;
                    while ((line = br.readLine()) != null) {
                        System.out.println(line);
                        pw.println(line);
                    }
                } catch (IOException e) {
                    System.err.println("Failed to read file: " + file.getAbsolutePath());
                    e.printStackTrace(); // Log the exception for debugging
                }
            }
        } catch (IOException e) {
            System.err.println("Failed to write to file: " + mergedFile.getAbsolutePath());
            e.printStackTrace(); // Log the exception for debugging
        }
    }
}
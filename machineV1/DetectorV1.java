package machine;
import java.io.File;
public class DetectorV1 extends MainMachine {
	String[] paths;
	File file;
	int x=1;
	public void MainDetector(){

		try {      
			file = new File("home Directory");
			paths = file.list();
			
			for(String path:paths) {
				System.out.println(path);
			}


		} catch (Exception e) {
			e.printStackTrace();
		}
	} 
	String getname(int index) {
		return paths[index];
	}
}
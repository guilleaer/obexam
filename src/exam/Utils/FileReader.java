package exam.Utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import exam.ImageProcessor.ImageProcessor;
import exam.ImageProcessor.TextFileImageProcessor;
import exam.color.Color;

public class FileReader {
String _rootFolder;
String _imagesFolder;

private static FileReader instance = null;

protected FileReader() throws IOException {
	this.set_rootFolder(new java.io.File( "." ).getCanonicalPath());
	Properties prop = new Properties();
	FileInputStream input = new FileInputStream("./config/obexam.properties");;
	prop.load(input);
	// set the properties value
	this.set_imagesFolder(this.get_rootFolder() + "/" + prop.getProperty("imagesUrl"));
}

public static FileReader getInstance() throws IOException {
   if(instance == null) {
      instance = new FileReader();
   }
   return instance;
}

public int getNumberOfImages() throws IOException {
	return new File(this.get_imagesFolder()).listFiles().length;
}

public List<Map<Color, Integer>> readImages() throws FileNotFoundException, IOException {
	List<Map<Color, Integer>> lstResult = new ArrayList<Map<Color, Integer>>();
	File dir = new File(this.get_imagesFolder());
	  File[] directoryListing = dir.listFiles();
	  if (directoryListing != null) {
	    for (File child : directoryListing) {
	    	try {
	    	  ImageProcessor ip = this.readFile(child);
	    	  lstResult.add(ip.getImageHistogram());
	    	} catch (Exception ex) {
	    		System.out.println("Error happened reading file " + child.getName());
	    	}
	    }
	  }
	return lstResult;
}

private ImageProcessor readFile(File fl) throws FileNotFoundException, IOException {
	ImageProcessor ip = null;
	String line;

    InputStream fis = new FileInputStream(fl);
    InputStreamReader isr = new InputStreamReader(fis, Charset.forName("UTF-8"));
    BufferedReader br = new BufferedReader(isr);

    int counter = 0;
    String type = "";
    String code = "";
    List<String> lines = new ArrayList<String>();
	while ((line = br.readLine()) != null) {
        switch (counter) {
		case 0:
			type = line;
			break;
		case 1:
			code = line;
			break;
		case 2:
			break;
		case 3:
			break;
		default:
			lines.add(line);
		}
        ip = new TextFileImageProcessor(type, code, lines);
        counter += 1;
    }
	br.close();
    return ip;
}

public String get_rootFolder() {
	return _rootFolder;
}
public void set_rootFolder(String _rootFolder) {
	this._rootFolder = _rootFolder;
}
public String get_imagesFolder() {
	return _imagesFolder;
}
public void set_imagesFolder(String _imagesFolder) {
	this._imagesFolder = _imagesFolder;
}
}

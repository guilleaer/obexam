package exam;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import exam.Utils.FileReader;
import exam.color.Color;

public class Main {

	public static void main(String[] args) {
		FileReader fr = null;
		List <Map<Color, Integer>> lstHistograms;
		try {
			 fr = FileReader.getInstance();
			 int imagesToExplore = fr.getNumberOfImages();
			 System.out.println(Integer.toString(imagesToExplore) + " images will be readed");
			 lstHistograms = fr.readImages();
			 for (int i = 0; i < lstHistograms.size(); i++) {
				 System.out.println("Reading image " + (i+1));
				 HashMap<Color, Integer> curHistogram = (HashMap<Color, Integer>)lstHistograms.get(i);
				 Iterator<Map.Entry<Color, Integer>> it = curHistogram.entrySet().iterator();
				 while (it.hasNext()) {
					 Map.Entry<Color, Integer> entry = it.next();
					 System.out.println("Color " + entry.getKey().getNormalizedColor() + " appears " + entry.getValue() + " times");
				 }
				 System.out.println("--------------------------------------------");
				 System.out.println("--------------------------------------------");
				 System.out.println("--------------------------------------------");
		     }
		} catch (Exception e) {
			System.out.println("Error! Please configure your images folder: " + e.getMessage());
			System.exit(0);
		}
	}
}

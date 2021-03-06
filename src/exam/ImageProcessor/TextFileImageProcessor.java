package exam.ImageProcessor;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import exam.Utils.ObExamConstants;
import exam.color.CMYKColor;
import exam.color.Color;
import exam.color.RGBColor;

public class TextFileImageProcessor implements ImageProcessor {

	private String _type;
	public String get_type() {
		return _type;
	}

	public void set_type(String _type) {
		this._type = _type;
	}

	public String get_colorCode() {
		return _colorCode;
	}

	public void set_colorCode(String _colorCode) {
		this._colorCode = _colorCode;
	}

	public List<String> get_lines() {
		return _lines;
	}

	public void set_lines(List<String> _lines) {
		this._lines = _lines;
	}
	
	private Boolean isCMYK() {
		return this.get_type().equals(ObExamConstants.CMYKTYPE);
	}
	
	

	private String _colorCode;
	private List<String> _lines;
	
	public TextFileImageProcessor (String type, String colorCode, List<String> lines) {
		this.set_colorCode(colorCode);
		this.set_lines(lines);
		this.set_type(type);
	}
	
	@Override
	public Map<Color, Integer> getImageHistogram() {
		Map<Color, Integer> lstToReturn = new HashMap<Color, Integer>();
		String[] arrOfColors = null;
		String[] currentColor = null;
		Color color;
		for (String lineStr : _lines) {
			arrOfColors = lineStr.split(";");
			for (int i = 0; i < arrOfColors.length; i++) {
			  currentColor = arrOfColors[i].split(",");
			  if (isCMYK()) {
				  color = new CMYKColor(currentColor, this.get_colorCode());
			  } else {
				  color = new RGBColor(currentColor, this.get_colorCode());
			  }
			  if (!this.findColor(lstToReturn, color)) {
				  lstToReturn.put(color, 1);  
			  }
			}
		}
		return lstToReturn;
	}
	
	private boolean findColor(Map<Color, Integer> lstToFind, Color color) {
		 Iterator<Map.Entry<Color, Integer>> it = lstToFind.entrySet().iterator();
		 while (it.hasNext()) {
			 Map.Entry<Color, Integer> entry = it.next();
			 if (entry.getKey().getNormalizedColor().equals(color.getNormalizedColor())) {
				 entry.setValue(entry.getValue()+1);
				 return true;
			 }
		 }
		return false;
	}
}

package exam.color;

import exam.Utils.ObExamConstants;

public class RGBColor implements Color {

private String _normCode;
public String get_normCode() {
	return _normCode;
}

public void set_normCode(String _normCode) {
	this._normCode = _normCode;
}

private String _codeType;
private String[] _origColor;

public String[] get_origColor() {
	return _origColor;
}

public void set_origColor(String[] _origColor) {
	this._origColor = _origColor;
}

public String get_strColor() {
	return _strColor;
}

public void set_strColor(String _strColor) {
	this._strColor = _strColor;
}

private String _strColor;
	
	public String get_codeType() {
	return _codeType;
}

public void set_codeType(String _codeType) {
	this._codeType = _codeType;
}

  @Override
  public boolean isValidColor() {
	  if (this.get_origColor().length == 3) {
		  return true;
	  } else {
		  return false;  
	  }
  }
    
  @Override
  public String getNormalizedColor() {
		String finalString = "#";
	  if (this.get_normCode() != null) {
		  return this.get_normCode();
	  } else {
			int scale;
			if (_codeType.equals(ObExamConstants.DATA_01)) {
				scale = 2;
			} else {
				scale = 10;
			}
			for (String pieceOfColor : this.get_origColor()) {
				int decimal = Integer.parseInt(pieceOfColor, scale);
				String hexStr = Integer.toString(decimal,16);
				finalString += hexStr;
			}
		this.set_normCode(finalString.toUpperCase());
		return this.get_normCode();
	  }
	}
	
	public RGBColor (String[] color, String codeType) {
		String strColor = "";
		this.set_codeType(codeType);
		this._origColor = color;
		if (this.isValidColor()) {
			for (String colorItem : this._origColor) {
				strColor += colorItem;
			}
			this.set_strColor(strColor);
			this.getNormalizedColor();
		}
	}

}

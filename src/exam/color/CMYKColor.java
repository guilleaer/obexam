package exam.color;

import exam.Utils.ObExamConstants;

public class CMYKColor implements Color {

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
	  if (this.get_origColor().length == 4) {
		  return true;
	  } else {
		  return false;  
	  }
  }
    
  @Override
  public String getNormalizedColor() {
		String finalString = "";
	  if (this.get_normCode() != null) {
		  return this.get_normCode();
	  } else {
			int scale;
			if (_codeType.equals(ObExamConstants.DATA_01)) {
				scale = 2;
			} else {
				scale = 10;
			}
			finalString = this.transformToRGB(this.get_origColor(), scale);
		this.set_normCode(finalString.toUpperCase());
		return this.get_normCode();
	  }
	}
  
    private String transformToRGB (String[] colorToTransform, int scale) {
		double decC = 0;
		double decM = 0;
		double decY = 0;
		double decK = 0;
		int R, G, B;
		String hexR, hexG, hexB;
    	for (int i = 0; i < this.get_origColor().length; i++) {
			int decimal = Integer.parseInt(this.get_origColor()[i], scale);
			switch (i) {
			case 0:
				decC = decimal;
				break;
			case 1:
				decM = decimal;
				break;
			case 2:
				decY = decimal;
				break;
			case 3:
				decK = decimal;
				break;
			}
    	}
	    R = (int) (Math.floor(255 * (1-(decC/255)) * (1-(decK/255))));
	    G = (int) (Math.floor(255 * (1-(decM/255)) * (1-(decK/255))));
	    B = (int) (Math.floor(255 * (1-(decY/255)) * (1-(decK/255))));
			
		hexR = Integer.toString(R,16);
		hexG = Integer.toString(G,16);
		hexB = Integer.toString(B,16);
	    return "#" + hexR + hexG + hexB;
    }
	
	public CMYKColor (String[] color, String codeType) {
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

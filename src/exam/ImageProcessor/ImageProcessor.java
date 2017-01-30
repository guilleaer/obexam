package exam.ImageProcessor;

import java.util.Map;

import exam.color.Color;

public interface ImageProcessor {
	public Map<Color, Integer> getImageHistogram();
}

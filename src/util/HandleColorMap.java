package util;

import java.util.HashMap;
import java.util.Map;



public class HandleColorMap {

	public static final Map<String, String> colorsMap;
    static
    {
    	colorsMap = new HashMap<String, String>();
    	colorsMap.put("red", "red");
    	colorsMap.put("orange", "#FF8C00");
    	colorsMap.put("violet", "#AA00AA");
    	colorsMap.put("blue", "blue");
    	colorsMap.put("green", "green");
    	colorsMap.put("grey", "grey");
    	colorsMap.put("black", "black");
    }
}

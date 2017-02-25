package com.charlie.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class FormatUtil {
	
	public static String dateFormate(String regrex,Date date){
		SimpleDateFormat sdf = new SimpleDateFormat(regrex);
		return sdf.format(date);
	}
}

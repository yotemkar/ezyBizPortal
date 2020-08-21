/**
 * 
 */
package com.hssa.ezybiz.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.gson.Gson;


/**
 * @author ytemkar
 *
 */
public class EzyBizUtil {
	private static final Logger logger = LogManager.getLogger(EzyBizUtil.class);

	/**
	 * 
	 * @param date
	 * @return
	 */
	public static String convertDateToStringMMDDYYYY(Date date) {
		String result = "";
		try {
			if(null != date) {
				SimpleDateFormat simpleCalender = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
				result = simpleCalender.format(date);
			}
		} catch (Exception e) {
			logger.error("ERROR UTILS-CONVERT-DATE-TO-STRING-DATE-MM-DD-YYYY");
		}
		return result;
	}
	
	public static String convertDateToStringNoTimeStamp(Date date) {
		String result = "";
		try {
			if(null != date) {
				SimpleDateFormat simpleCalender = new SimpleDateFormat("MM/dd/yyyy");
				result = simpleCalender.format(date);
			}
		} catch (Exception e) {
			logger.error("ERROR UTILS-CONVERT-DATE-TO-STRING-NO-TIMESTAMP");
		}
		return result;
	}
	
	public static String getDateDifference(String strDate1, String strDate2) {

		String format = "";
		if (!strDate1.isEmpty() && !strDate2.isEmpty()) {

			DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
			Date date = convertStringtoDate(strDate1);
			Date enddate = convertStringtoDate(strDate2);
			long t = enddate.getTime() + (60000);
			String s = df.format(new Date(t));
			Date date1 = convertStringtoDate(s);
			long diff = date1.getTime() - date.getTime();
			long temp = diff / (1000 * 60);
			long hrs = temp / 60;
			long mins = temp % 60;
			long lefthrs = hrs % 24;
			long days = hrs / 24;
			if (hrs >= 5) {
				format = "greater";
			} else {
				format = "less";
			}

		}
		return format;
	}
	
	public static Date convertStringtoDate(String value) {
		DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
		Date obj = null;
		try {
			obj = (Date) df.parse(value);

		} catch (ParseException ex) {
			logger.error("ERROR UTILS-CONVERT-STRING-TO-DATE");
		}
		return obj;
	}
	
	public static String convertStringtoDate2(String value) {
		SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
		//SimpleDateFormat outputFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
		SimpleDateFormat outputFormat = new SimpleDateFormat("MM/dd/yyyy");
		Date obj=null;
		String formattedDate = null;
		try {
			if(value!=null) {
				Date date = inputFormat.parse(value);
				if (!value.equals(inputFormat.format(date))) {
			        date = null;
			    }
				formattedDate = outputFormat.format(date);
				logger.info(formattedDate);
			}
			//obj=(Date) outputFormat.parse(formattedDate);
		} catch (ParseException ex) {
			logger.error("ERROR UTILS-CONVERT-STRING-TO-DATE");
			formattedDate = value;
		}
		
		return formattedDate;
	}
	
	public static String getDateMMddYYYY(String date) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SimpleDateFormat sdf2 = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
		String str = "";
		try {
			if (!isEmpty(date)) {
				str = sdf2.format(format.parse(date));
			}
		} catch (ParseException e) {
			logger.error("ERROR UTILS-GET-DATE-MM-DD-YYYY");
		}
		return str;
	}
	
	public static boolean isEmpty(String value) {
		boolean emptyFlag = false;
		if (value == null || value.trim().equals("")) {
			emptyFlag = true;
		}
		return emptyFlag;
	}
	
	public static boolean isEmpty(Long value) {
		boolean emptyFlag = false;
		if (value == null || value.equals("") || value==0) {
			emptyFlag = true;
		}
		return emptyFlag;
	}
	
	public static boolean isEmpty(Integer value) {
		boolean emptyFlag = false;
		if (value == null || value.equals("") || value==0) {
			emptyFlag = true;
		}
		return emptyFlag;
	}
	
	public static String convertMapToJson(Map<String, Object> outputMap) {
		try {
			Gson gson = new Gson();
			return gson.toJson(outputMap);
		} catch (Exception ex) {
			logger.error("convertMapToJson : Error in preparing JSON",ex);
			return "Error in preparing JSON";
		}
	}
}

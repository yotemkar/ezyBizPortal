package com.hssa.ezybiz.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class DaoHelper {

	public static Object[] getSearchableTokens(List<Object> paramList)
	{
		Object[] paramArray = new Object[paramList.size()];
		for(int i=0;i<paramList.size();i++)
		{
			String param = "";
			if(paramList.get(i) instanceof String )
			{
				param = (String)paramList.get(i);
				if("".equals(param) || "null".equals(param)){
					param = "%";
				}else{ 
					param = "%"+param+"%";
				}
			}
			else if(paramList.get(i) instanceof Integer )
			{
				param = ((Integer)paramList.get(i)) + "";
				if("".equals(param) || "null".equals(param) || ((Integer)paramList.get(i)) <= 0){
					param = "%";
				}
			}
			else if(paramList.get(i) instanceof Long )
			{
				param = ((Long)paramList.get(i)) + "";
				if("".equals(param) || "null".equals(param) || ((Long)paramList.get(i)) <= 0){
					param = "%";
				}
			}
			else if(paramList.get(i) instanceof Double )
			{
				param = ((Double)paramList.get(i)) + "";
				if("".equals(param) || "null".equals(param) || ((Double)paramList.get(i)) <= 0){
					param = "%";
				}
			}
			else if(paramList.get(i) instanceof Date )
			{			
				Date dParam = (Date)paramList.get(i);
				if(dParam==null || dParam.equals(new Date(0))){
				//if("".equals(param) || "null".equals(param)){
					param = "%";
				}else{
					SimpleDateFormat dt1;
					dt1 = new SimpleDateFormat("yyyy-MM-dd");
					param = dt1.format((Date)paramList.get(i));
				}
			}else if(paramList.get(i) instanceof Boolean )
			{	
				if(((Boolean)paramList.get(i))==true)
				{
					param = "1";
				}
				else
				{
					param = "0";
				}
			} else{
				param = "%";
			}
			paramArray[i] = param;			
		}
		return paramArray;
	}

	public static String getPreparedQuery(String rawQuery, Object[] paramArray)
	{
		String preparedQuery=rawQuery;
		for(int i=0;i<paramArray.length;i++)
		{
			preparedQuery = preparedQuery.replaceFirst("\\?", (String)paramArray[i]);
		}
		return preparedQuery;
	}	
}

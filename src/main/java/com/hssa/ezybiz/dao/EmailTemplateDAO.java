package com.hssa.ezybiz.dao;
 
import com.hssa.ezybiz.dto.EmailTemplate;
 
public interface EmailTemplateDAO 
{

	/**
	 * Returns only single row based on TemplateName.  
	 * @param templateName
	 * @return
	 */
	EmailTemplate findEmailTemplateByTemplateName(String templateName);
 
}

package com.hssa.ezybiz.services;

import com.hssa.ezybiz.dto.EmailTemplate;

public interface EmailTemplateBO {
 
	/**
	 * Returns only single row based on TemplateName.  
	 * @param templateName
	 * @return
	 */
    public EmailTemplate findEmailTemplateByTemplateName(String templateName);
 
}

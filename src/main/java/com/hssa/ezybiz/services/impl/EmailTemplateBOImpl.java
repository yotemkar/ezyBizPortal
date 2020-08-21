package com.hssa.ezybiz.services.impl;
import org.springframework.stereotype.Service;

import com.hssa.ezybiz.dao.EmailTemplateDAO;
import com.hssa.ezybiz.dto.EmailTemplate;
import com.hssa.ezybiz.services.EmailTemplateBO;
import com.hssa.ezybiz.services.SequencesBO;
 
@Service
public class EmailTemplateBOImpl implements EmailTemplateBO{
 
	private static final String EmailTemplateSequence = "EmailTemplateSequence";
	
	private transient EmailTemplateDAO emailTemplateDAO;
 
	private transient SequencesBO sequencesBO;
	
	public EmailTemplateBOImpl(){
		super();
	}
 
	public SequencesBO getSequencesBO() {
		return sequencesBO;
	}
 
	public void setSequencesBO(SequencesBO sequencesBO) {
		this.sequencesBO = sequencesBO;
	}
		
 
	public EmailTemplateDAO getEmailTemplateDAO(){
		return emailTemplateDAO;
	}
 
	public void setEmailTemplateDAO(EmailTemplateDAO emailTemplateDAO){
		this.emailTemplateDAO = emailTemplateDAO;
	}

	@Override
	public EmailTemplate findEmailTemplateByTemplateName(String templateName) {
		return emailTemplateDAO.findEmailTemplateByTemplateName(templateName);
	}
  
}

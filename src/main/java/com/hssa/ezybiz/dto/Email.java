package com.hssa.ezybiz.dto;

import java.io.Serializable;
import java.util.List;

public class Email  implements Serializable{

	private static final long serialVersionUID = 1L;
	private String from;
	private String to;
    private String cc;
    private String bcc;
    private String subject;
    private String content;   
    private List<String> attachmentFileNames;


	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getCc() {
		return cc;
	}

	public void setCc(String cc) {
		this.cc = cc;
	}

	public String getBcc() {
		return bcc;
	}

	public void setBcc(String bcc) {
		this.bcc = bcc;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public List<String> getAttachmentFileNames() {
		return attachmentFileNames;
	}

	public void setAttachmentFileNames(List<String> attachmentFileNames) {
		this.attachmentFileNames = attachmentFileNames;
	}

	@Override
    public String toString() {
        return String.format("Email{to="+getTo()+", content="+getContent()+"}");
    }

}

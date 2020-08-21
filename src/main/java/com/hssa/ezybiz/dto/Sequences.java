package com.hssa.ezybiz.dto;

import java.io.Serializable;

public class Sequences implements Serializable {

	private static final long serialVersionUID = 1L;
	private String sequenceName;
	private String sequenceDescription;
	private int startwith;
	private int incrementBy;
	private int sequenceMinValue;
	private int sequenceMaxValue;
	private int currentValue;

	public String getSequenceName() {
		return sequenceName;
	}

	public void setSequenceName(String sequenceName) {
		this.sequenceName = (sequenceName);
	}

	public String getSequenceDescription() {
		return sequenceDescription;
	}

	public void setSequenceDescription(String sequenceDescription) {
		this.sequenceDescription = (sequenceDescription);
	}

	public int getStartwith() {
		return startwith;
	}

	public void setStartwith(int startwith) {
		this.startwith = (startwith);
	}

	public int getIncrementBy() {
		return incrementBy;
	}

	public void setIncrementBy(int incrementBy) {
		this.incrementBy = (incrementBy);
	}

	public int getSequenceMinValue() {
		return sequenceMinValue;
	}

	public void setSequenceMinValue(int sequenceMinValue) {
		this.sequenceMinValue = (sequenceMinValue);
	}

	public int getSequenceMaxValue() {
		return sequenceMaxValue;
	}

	public void setSequenceMaxValue(int sequenceMaxValue) {
		this.sequenceMaxValue = (sequenceMaxValue);
	}

	public int getCurrentValue() {
		return currentValue;
	}

	public void setCurrentValue(int currentValue) {
		this.currentValue = (currentValue);
	}

}

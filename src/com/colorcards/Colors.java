package com.colorcards;

public class Colors {

	private int id;
	private String colorCode;

	public Colors(int id, String colorCode) {
		super();
		this.id = id;
		this.colorCode = colorCode;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getColorCode() {
		return colorCode;
	}

	public void setColorCode(String colorCode) {
		this.colorCode = colorCode;
	}


}

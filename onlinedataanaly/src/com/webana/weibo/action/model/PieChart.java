package com.webana.weibo.action.model;

/**
 * @author andrew
 *
 */
public final class PieChart {

    private String name;
    
    private int y;

	private String color;
    
	public PieChart(String name, int value, String color) {
		this.name = name;
		this.y = value;
		this.color = color;
	}
	
    public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}


}

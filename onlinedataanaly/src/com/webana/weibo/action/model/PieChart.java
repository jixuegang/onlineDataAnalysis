package com.webana.weibo.action.model;

/**
 * @author andrew
 *
 */
public final class PieChart {

    private String name;
    
    private int y;
    
	public PieChart(String name, int value) {
		this.name = name;
		this.y = value;
	}
	
    public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}


}

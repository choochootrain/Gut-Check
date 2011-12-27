package com.choochootrain.GutCheck.Item;

import java.util.Date;

public class Item {
	
	private Date time;
	private int id;
	
	public Item(int n, Date t) {
		id = n;
		time = t;
	}

	public Date getTime() {
		return time;
	}
	
	public void setTime(Date time) {
		this.time = time;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
}

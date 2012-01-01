package com.choochootrain.GutCheck.Item;

import java.util.Date;

public class Item {
	
	private Date time;
	private long id;
	
	public Item(long n, Date t) {
		id = n;
		time = t;
	}

	public Date getTime() {
		return time;
	}
	
	public void setTime(Date time) {
		this.time = time;
	}
	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
}
package com.wt.demo.commend;

public abstract class Commend {

	private Barbecuer barbecuer;

	public Commend(Barbecuer barbecuer) {
		this.barbecuer = barbecuer;
	}

	public abstract void execute();

	public Barbecuer getBarbecuer() {
		return barbecuer;
	}

	public void setBarbecuer(Barbecuer barbecuer) {
		this.barbecuer = barbecuer;
	}
}

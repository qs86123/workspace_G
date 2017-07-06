package com.wt.demo.absfactory.two;

public class DataAccess {

	public IUrid createObj(String type, String name) {
		try {
			return (IUrid) Class.forName("com.wt.demo.absfactory.two." + type + name).newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}

}

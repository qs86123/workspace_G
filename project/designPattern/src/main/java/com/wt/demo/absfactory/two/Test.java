package com.wt.demo.absfactory.two;

public class Test {
	public static void main(String[] args) {
		DataAccess sqlfactory = new DataAccess();
		sqlfactory.createObj("Sql", "User").insert();
		sqlfactory.createObj("Access", "User").insert();
		sqlfactory.createObj("Sql", "Department").insert();
		sqlfactory.createObj("Access", "Department").insert();
	}
}

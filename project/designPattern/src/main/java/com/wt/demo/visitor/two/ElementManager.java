package com.wt.demo.visitor.two;

import java.util.ArrayList;
import java.util.List;

public class ElementManager {

	List<Element> list = new ArrayList<>();

	public void addElement(Element element) {
		this.list.add(element);
	}

	public void display(Visitor visitor) {
		for (Element element : list) {
			element.accept(visitor);
		}
	}

}

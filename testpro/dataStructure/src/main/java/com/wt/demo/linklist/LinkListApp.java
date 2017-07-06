package com.wt.demo.linklist;

import org.junit.Test;

public class LinkListApp {

	@Test
	public void testlinkList() {
		LinkList linkList = new LinkList();
		linkList.insertFirst(1, 1.1);
		linkList.insertFirst(2, 2.1);
		linkList.insertFirst(3, 3.1);
		linkList.insertFirst(4, 4.1);
		linkList.insertFirst(5, 5.1);

		linkList.displayList();
		System.out.println("删除头个元素后---------------------------------");
		linkList.deleteFirst();
		linkList.displayList();
		System.out.println("再删除头个元素后---------------------------------");
		linkList.deleteFirst();
		linkList.displayList();
	}

}

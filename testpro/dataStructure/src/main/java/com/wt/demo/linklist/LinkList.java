package com.wt.demo.linklist;

/**
 * 单向链表，每次插入都插入到链表的头部
 * 
 * @author wangtao
 * @date 2017年4月1日上午10:03:29
 */
public class LinkList {

	private Link first;

	public LinkList() {
		first = null;
	}

	public boolean isEmpty() {
		return first == null;
	}

	// 将新元素插入到链表头部
	public void insertFirst(int id, double dd) {
		Link newLink = new Link(id, dd);
		newLink.setNext(first);
		first = newLink;
	}

	// 删除链表头部的元素
	public Link deleteFirst() {
		Link temp = first;
		// 将first直接指向下一个元素即可，至于已经被删除的first元素java内存回收机制回收
		first = first.getNext();
		// 返回删除的元素的值
		return temp;
	}

	public void displayList() {
		System.out.println("List(first ---> last):");
		Link current = first;
		while (current != null) {
			current.displayLink();
			current = current.getNext();
		}
		System.out.println("");
	}
}

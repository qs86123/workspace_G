package com.wt.demo.a_lesson16;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * 自定义无序list集合，通过数组实现
 * 
 * @author wangtao
 * @date 2017年4月18日下午1:04:40
 */
public class MyList<T> implements List<T> {

	Object data[];
	int size = 0;

	public MyList() {
		data = new Object[11];
	}

	public boolean add(T t) {
		if (size < 10)
			data[size++] = t;
		else {
			Object[] newData = data;
			for (int i = 0; i < size / 10; i++) {
				newData = (Object[]) data[10];
				if (newData == null) {
					newData = new Object[11];
					data[10] = newData;
				}
			}
			newData[size % 10] = t;
			size++;
		}
		return true;
	}

	@SuppressWarnings("unchecked")
	public T get(int index) {
		if (index >= size)
			// throw new IndexOutOfBoundsException();
			throw new OutError("范围超出");
		Object[] getData = data;
		for (int i = 0; i < index / 10; i++) {
			getData = (Object[]) data[10];
		}
		return (T) getData[index % 10];
	}

	public int size() {
		return size;
	}

	public boolean isEmpty() {
		return size == 0;
	}

	public boolean contains(Object o) {
		// TODO Auto-generated method stub
		return false;
	}

	public Iterator<T> iterator() {
		// TODO Auto-generated method stub
		return null;
	}

	public Object[] toArray() {
		// TODO Auto-generated method stub
		return null;
	}

	@SuppressWarnings("hiding")
	public <T> T[] toArray(T[] a) {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean remove(Object o) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean containsAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean addAll(Collection<? extends T> c) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean addAll(int index, Collection<? extends T> c) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean removeAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean retainAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return false;
	}

	public void clear() {
		// TODO Auto-generated method stub

	}

	public T set(int index, T element) {
		// TODO Auto-generated method stub
		return null;
	}

	public void add(int index, T element) {
		// TODO Auto-generated method stub

	}

	public T remove(int index) {
		// TODO Auto-generated method stub
		return null;
	}

	public int indexOf(Object o) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int lastIndexOf(Object o) {
		// TODO Auto-generated method stub
		return 0;
	}

	public ListIterator<T> listIterator() {
		// TODO Auto-generated method stub
		return null;
	}

	public ListIterator<T> listIterator(int index) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<T> subList(int fromIndex, int toIndex) {
		// TODO Auto-generated method stub
		return null;
	}

}

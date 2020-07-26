package dataStructure.tree.impl.binary;

import dataStructure.tree.impl.Position;

public class ArrayNode<T> implements Position<T> {
	private int index;
	private T data;

	public ArrayNode(T data, int index) {
		this.setData(data);
		this.index = index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public void setData(T data) {
		this.data = data;
	}

	@Override
	public T element() {
		return data;
	}

	public int index() {
		return index;
	}
  
}
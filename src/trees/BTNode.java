package trees;

public class BTNode<T> {
	// Data members
	public T data;
	public BTNode<T> left;
	public BTNode<T> right;
	
	// Constructor
	public BTNode(T data) {
		this.data = data;
	}
}
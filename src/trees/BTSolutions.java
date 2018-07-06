package trees;
import java.util.Scanner;
import java.util.Queue;
import java.util.LinkedList;

public class BTSolutions {

	// Input Strategies
	public static BTNode<Integer> depthFirstInput(Scanner s){
		int data = s.nextInt();
		if(data == -1) {
			return null;
		}
		BTNode<Integer> root = new BTNode<Integer>(data);
		root.left = depthFirstInput(s);
		root.right = depthFirstInput(s);
		return root;
		
	}
	public static BTNode<Integer> levelOrderInput(Scanner s){
		int data = s.nextInt();
		Queue<BTNode<Integer>> queue = new LinkedList<BTNode<Integer>>();
		BTNode<Integer> root = new BTNode<Integer>(data);
		BTNode<Integer> travel = new BTNode<Integer>(data);
		queue.add(root);
		
		while(!queue.isEmpty()) {
			travel = queue.remove();
			
			data = s.nextInt();
			if(data != -1) {
				travel.left = new BTNode<Integer>(data);
				queue.add(travel.left);
			}
			
			data = s.nextInt();
			if(data !=-1) {
				travel.right = new BTNode<Integer>(data);
				queue.add(travel.right);
			}
		}
		
		return root;
	}
	
	// Print Strategies
	public static void depthFirstPrint(BTNode<Integer> root) {
		if(root == null) {
			return;
		}
		System.out.println(root.data);
		depthFirstPrint(root.left);
		depthFirstPrint(root.right);
	} 
	public static void levelOrderPrint(BTNode<Integer> root) {
		BTNode<Integer> travel = new BTNode<Integer>(root.data);
		Queue<BTNode<Integer>> queue = new LinkedList<BTNode<Integer>>();
		queue.add(root);
		
		while(!queue.isEmpty()) {
			travel = queue.remove();
			System.out.println(travel.data);
			
			if(travel.left != null) {
				queue.add(travel.left);
			}
			
			if(travel.right != null) {
				queue.add(travel.right);
			}
		}
	}
	
	// Solutions to common problems
	
	// #1 Find Node
	public static boolean findNode(BTNode<Integer> root,int x) {
		if(root == null){
            return false;
          }
      
          if(root.data==x){
            return true;
          }
          else{
            boolean left = findNode(root.left,x);
            boolean right= findNode(root.right,x);
            if(left||right){
              return true;
            }
            else{
              return false;
            }
          }
	}
	
	// #2 Height of Binary tree
	public static int getHeight(BTNode<Integer> root, int count) {
		if(root == null) {
			return count;
		}
		count+=1;
		int leftCount = getHeight(root.left,count);
		int rightCount = getHeight(root.right,count);
		if(leftCount>=rightCount) {
			return leftCount;
		}
		else {
			return rightCount;
		}
	}
	
	// #3 Mirror of Binary tree
	public static void mirror(BTNode<Integer> root) {
		if(root==null) {
			return;
		}
		BTNode<Integer> temp;
		temp = root.left;
		root.left = root.right;
		root.right = temp;
		mirror(root.left);
      	mirror(root.right);	
	}
	
	// #4 PreOrder
	public static void preOrder(BTNode<Integer> root) {
		
		if(root==null){
          return;
        }
      	System.out.print(root.data+" ");
      	preOrder(root.left);
      	preOrder(root.right);
	}
	
	// #5 PostOrder
	private static void postOrder(BTNode<Integer> root) {
		if(root==null) {
			return;
		}
		
      	postOrder(root.left);
      	postOrder(root.right);
      	System.out.print(root.data+" ");

	}
	
	// #8 InOrder
	public static void inOrder(BTNode<Integer> root) {
		if(root==null) {
			return;
		}
		
		inOrder(root.left);
		System.out.print(root.data + " ");
		inOrder(root.right);
	}
	
	// #6 Diameter of Binary Tree without pair
	public static int diameter(BTNode<Integer> root) {
		if(root == null) {
			return 0;
		}
		int option1 = getHeight(root.left,0) + getHeight(root.right, 0);
		int option2 = diameter(root.left);
		int option3 = diameter(root.right);
		
		return Math.max(option1, Math.max(option2, option3));
	}
	
	// #7 Diameter and height of Binary Tree using pair
	public static Pair<Integer,Integer> treeDimentions(BTNode<Integer> root){
		// First: Diameter
		// Second: Height
		if(root == null) {
			Pair<Integer,Integer> result = new Pair<>(0, 0);
			return result;
		}
		
		Pair<Integer, Integer> leftTree = treeDimentions(root.left);
		Pair<Integer, Integer> rightTree = treeDimentions(root.right);
		Pair<Integer, Integer> result = new Pair<Integer, Integer>(0, 0);
		
		// Height
		result.second = 1 + Math.max(leftTree.second, rightTree.second);
		
		// Diameter
		int D1 = leftTree.second + rightTree.second;
		int D2 = leftTree.first;
		int D3 = leftTree.second;
		result.first = Math.max(D1, Math.max(D2, D3));
		
		return result;
		
	}
	
	// #10 Construct Tree From InOrder and preOrder arrays
	private static BTNode<Integer> getTreeFromPreOrderAndInOrder(int inOrderArray[], int preOrderArray[], int inStart, int inEnd, int preStart, int preEnd) {
		if(preStart > preEnd) {
			return null;
		}
		BTNode<Integer> root = new BTNode<Integer>(preOrderArray[preStart]);
		int rootIndex=0;
		for(int i=inStart; i<=inEnd; i++) {
			if(inOrderArray[i]==root.data) {
				rootIndex=i;
				break;
			}
		}
		
		int leftTreeInStart = inStart;
		int leftTreeInEnd = rootIndex - 1;
		int leftTreePreStart = preStart + 1;
		int leftTreePreEnd = leftTreeInEnd - leftTreeInStart + leftTreePreStart;
		
		int rightTreeInStart = rootIndex + 1;
		int rightTreeInEnd = inEnd;
		int rightTreePreStart = leftTreePreEnd + 1;
		int rightTreePreEnd = preEnd;
		
		root.right = getTreeFromPreOrderAndInOrder(inOrderArray, preOrderArray, rightTreeInStart, rightTreeInEnd, rightTreePreStart, rightTreePreEnd);
		root.left = getTreeFromPreOrderAndInOrder(inOrderArray, preOrderArray, leftTreeInStart, leftTreeInEnd, leftTreePreStart, leftTreePreEnd);
		
		return root;
		
	}
	public static BTNode<Integer> getTreeFromPreOrderAndInOrder(int inOrderArray[], int preOrderArray[]) {
		return getTreeFromPreOrderAndInOrder(inOrderArray, preOrderArray, 0, inOrderArray.length - 1, 0, preOrderArray.length - 1);
	}
	
	// #11 Construct Tree From InOrder and postOrder arrays
	private static BTNode<Integer> getTreeFromPostOrderAndInOrder(int inOrderArray[], int postOrderArray[], int inStart, int inEnd, int postStart, int postEnd) {
		if(postStart > postEnd) {
			return null;
		}
		
		BTNode<Integer> root=new BTNode<Integer>(postOrderArray[postEnd]);
		int rootIndex=0;
		for(int i=inStart; i<=inEnd; i++) {
			if(inOrderArray[i]==root.data) {
				rootIndex=i;
				break;
			}
		}
		int leftTreeInStart = inStart;
		int leftTreeInEnd = rootIndex - 1;
		int leftTreePostStart = postStart;
		int leftTreePostEnd = postStart + leftTreeInEnd - leftTreeInStart;
		
		
		int rightTreeInStart = rootIndex + 1;
		int rightTreeInEnd = inEnd;
		int rightTreePostStart = leftTreePostEnd + 1;
		int rightTreePostEnd = postEnd-1;
		root.right = getTreeFromPostOrderAndInOrder(inOrderArray, postOrderArray, rightTreeInStart, rightTreeInEnd, rightTreePostStart, rightTreePostEnd);
		root.left = getTreeFromPostOrderAndInOrder(inOrderArray, postOrderArray, leftTreeInStart, leftTreeInEnd, leftTreePostStart, leftTreePostEnd);
		return root;
	}
	public static BTNode<Integer> getTreeFromPostOrderAndInOrder(int inOrderArray[], int postOrderArray[]) {
		return getTreeFromPostOrderAndInOrder(inOrderArray, postOrderArray, 0, inOrderArray.length - 1, 0, postOrderArray.length - 1);
	}
	
	// #12 Sum of all nodes
	public static int sum(BTNode<Integer> root) {
		if(root == null) {
			return 0;
		}
		return root.data + sum(root.left) + sum(root.right);
	}
	
	
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		BTNode<Integer> root = levelOrderInput(s);
//		int inOrderArray[] = {4,2,5,1,3};
//		int postOrderArray[] = {4,5,2,3,1};
//		BTNode<Integer> root = getTreeFromPostOrderAndInOrder(inOrderArray, postOrderArray);
//		preOrder(root);
		System.out.println(sum(root));
	}

}

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
	
	// #4 TODO
	// #5 TODO
	
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
	
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		BTNode<Integer> root = levelOrderInput(s);
		System.out.println(diameter(root));
	}

}

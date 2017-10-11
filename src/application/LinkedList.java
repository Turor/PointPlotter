package application;

/**
 * Homework 06: LinkedList Class
 * @author Matthew Meidt
 * @version Homework 6
 *
 */
public class LinkedList {

	/**The first node in the list*/
	private Node head;

	/**The length of the linked list*/
	private int length;

	/**
	 * Default Constructor
	 */
	public LinkedList(){
		head = new Node();
		length = 0;
	}

	/**
	 * Method to add new nodes to the end of the Linked List
	 * @param newnode The node to be added to the end of the list
	 */
	public void addToEnd(Node newnode){
		Node itr = head;
		while(itr.getNext()!=null) //Finds the end
			itr = itr.getNext();
		itr.setNext(newnode);
		length++;
	}

	/**
	 * Calculates the perimeter of all the points in the list
	 * @return The perimeter of the shape created by all the points in the list
	 */
	public double calculatePerimeter() {
		
		double perimeter = 0;
		Node itr = head.getNext();
		while(itr.getNext()!=null){
			perimeter = perimeter + getDistance(itr,itr.getNext());
			itr = itr.getNext();
		}
		perimeter = perimeter + getDistance(itr,head.getNext());
		return perimeter;
	}

	/**
	 * Method to find the distance between two TwoDShape nodes
	 * @param n1 The first node
	 * @param n2 The second node
	 * @return The distance between the two vertices
	 */
	private double getDistance(Node n1, Node n2){
		return java.lang.Math.sqrt(java.lang.Math.pow(n1.getData().getX()-n2.getData().getX(), 2)+
				java.lang.Math.pow(n1.getData().getY()-n2.getData().getY(),2));
	}

	/**
	 * toString Method for the LinkedList
	 */
	public String toString(){
		Node itr = head.getNext();
		String s = "Points: " + itr.toString();
		while(itr.getNext()!=null){
			itr = itr.getNext();
			s = s +", " + itr.toString();
		}

		s = s + "\nPerimeter: " + calculatePerimeter();
		return s;
	}

	/**
	 * Clears all of the LinkedList
	 */
	public void clear() throws Exception{
		if(head.getNext() == null)
			throw new Exception("The list is already cleared");
		else{
			Node itr = head.getNext();
			head.setNext(null);;
			while(itr.getNext()!=null){
				Node temp = itr.getNext();
				itr.setData(null);
				itr.setNext(null);
				itr = temp;
			}
			itr.setData(null);
			length = 0;
		}
	}

	/**
	 * Method to return the length of the linked list
	 * @return The length of the LinkedList
	 */
	public int getLength(){
		return length;
	}


}

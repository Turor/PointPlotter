package application;
/**
 * Homework 06: Node Class
 * @author Matthew Meidt
 * @version Homework 06
 *
 * @param <TwoDShape> The data type which this node can store
 */
public class Node {
	
	/**The TwoDShape contained within the node*/
	private TwoDShape data;
	
	/**Pointer to the next Node in this list*/
	private Node next;
	
	/**
	 * Default Constructor
	 */
	public Node(){
		setData(null);
		setNext(null);
	}
	
	
	/**
	 * Parameterized Constructor populates the nodes data upon creation
	 * @param newdata The data to be stored in the node
	 */
	public Node(int newx, int newy) throws Exception{
		setData(new TwoDShape(newx,newy));
	}
	
	/**
	 * Setter for the data of the node
	 * @param newdata The new data to be held in the node
	 */
	public void setData(TwoDShape newdata){
		data = newdata;
	}
	
	/**
	 * Sets which node follows this node
	 * @param newnext The next node in the linked list
	 */
	public void setNext(Node newnext){
		next = newnext;
	}
	
	/**
	 * Method to retrieve the data stored within the node
	 * @return The TwoDShape data stored within
	 */
	public TwoDShape getData(){
		return data;
	}
	
	/**
	 * Returns the next node in the linked list
	 * @return Returns the next node in the list
	 */
	public Node getNext(){
		return next;
	}
	
	/**
	 * toString Method for the node
	 */
	public String toString(){
		return data.toString();
	}
	
	

}

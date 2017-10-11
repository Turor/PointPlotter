package application;

/**
 * Homework 06: TwoDShape Class
 * @author Matthew Meidt
 * @version Homework06
 */
public class TwoDShape {
	
	/**The X-Coordinate of the point/vertex*/
	private int x;
	
	/**The Y-Coordinate of the point/vertex*/
	private int y;

	
	/**
	 * Point/Vertex Constructor
	 * @param newx The x coordinate of the point/vertex
	 * @param newy The y coordinate of the point/vertex
	 * @throws Exception Thrown if an invalid point is given.
	 */
	public TwoDShape(int newx, int newy) throws Exception{
		setX(newx);
		setY(newy);
	}
	
	/**
	 * Method to retrieve the x-coordinate of the given point/vertex
	 * @return The x-coordinate of the vertex
	 */
	public int getX(){
		return x;
	}
	
	/**
	 * Method to retrieve the y-coordinate of the given point/vertex
	 * @return The y-coordinate of the point/vertex
	 */
	public int getY(){
		return y;
	}
	
	/**
	 * Method to set the X-coordinate of the point/vertex
	 * @param newx The new X-Coordinate to set the point/vertex to
	 * @throws Exception Thrown if an invalid y-value is provided (Unnecessary)
	 */
	public void setX(int newx) throws Exception{
		x = newx;
	}
	
	/**
	 * Method to set the Y-Coordinate of the point/vertex
	 * @param newy The new Y-Coordinate to set the point/vertex to
	 * @throws Exception Thrown if an invalid y-value is provided (Unnecessary)
	 */
	public void setY(int newy) throws Exception{
		y = newy;
	}
	
	/**
	 * toString Method returns a string of the form [x, y]
	 */
	public String toString(){
		String s = "";
		s = s + "[" + x + ", " + y + "]";
		return s;
	}	
}

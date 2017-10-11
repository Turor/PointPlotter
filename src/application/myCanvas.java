package application;

import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.paint.Color;

public class myCanvas extends Canvas implements EventHandler<ActionEvent> {

	/**Button which determines when a point will be added*/
	private Button submitB;

	/**The Button which determines when the list, text area, and graph will be cleared*/
	private Button clearB;

	/**The Button which determines when the perimeter will be calculated*/
	private Button calculatePerimeterB;

	/**The x-pixel indicating the x-component of the origin of the plane*/
	private double originx;

	/**The y-pixel indicating the y-component of the origin of the plane*/
	private double originy;

	/**The x component of the previous point*/
	private ArrayList<Double> prevX;

	/**The y component of the previous point*/
	private ArrayList<Double> prevY;

	/**This contains the information which says how much pixels are in a unit*/
	private double unit;

	/**Pointer to the grid pane class which contains most of the useful data*/
	private LinkedListGridPane gp;


	/**
	 * Constructor for the canvas
	 * @param width The width that the canvas will be
	 * @param height The height that the canvas will be
	 * @param myTA The text area which writes all the points to it
	 * @param submit
	 * @param clear
	 * @param calculate
	 */
	public myCanvas(double width, double height, LinkedListGridPane newgp){

		//Instantiate the array lists containing the list of x and y coordinates
		prevX = new ArrayList<Double>();
		prevY = new ArrayList<Double>();

		//Instantiate the pointers to the proper buttons
		submitB = newgp.getSubmitButton();
		clearB = newgp.getClearButton();
		calculatePerimeterB = newgp.getCalculatePerimeterButton();
		gp = newgp;


		//Set the width and height of the canvas
		setWidth(width);
		setHeight(height);

		//Determine the origin in terms of the width and height of the canvas
		originx = width/2;
		originy = height/2;

		//Method which draws the graph. Useful for when the graph is cleared.
		//An alternative would be to create a second canvas object which goes underneath the
		//canvas containing the points and shapes and lines. Performance wise this would be better
		//However, the graph is created so fast anyways, it matters not I think. We'll see
		drawGraph();

		submitB.setOnAction(this);

		clearB.setOnAction(this);

		calculatePerimeterB.setOnAction(this);


	}

	/**
	 * Method which draws the graph lines and stuff onto the picture. Basically the setup
	 */
	private void drawGraph(){

		//Grab the graphics context of the canvas
		GraphicsContext gc = getGraphicsContext2D();

		//Get the height and width of the canvas. I could probably use a method call each time
		//but I have the memory to spare for creating a couple of instance variables. I also copied
		//this over from the constructor after deciding it would be best to have the graph creation
		//be its own method.
		double height = getHeight();
		double width = getWidth();

		//Compute the minor axes distance\
		int yaxes = 11;
		double minorDist = height/yaxes;
		unit = minorDist;

		//Set the minor axes appearance
		gc.setStroke(Color.gray(.5));
		gc.setLineWidth(1);

		//Draw the minor x-axes
		for(int i = -yaxes/2 ; i <=yaxes/2;i++){
			gc.strokeLine(0, originy+i*minorDist, width, originy+i*minorDist);
		}

		//Figure out how many minor axes are in the x
		int xaxes = (int)(width/minorDist);

		//Draw the minor y-axes
		for(int i = -xaxes/2;i<=xaxes;i++){
			gc.strokeLine(originx+i*minorDist, 0, originx+i*minorDist, height);
		}

		//Set the attributes for the x and y axes
		gc.setStroke(Color.BLACK);
		gc.setLineWidth(2);

		//Draw the x and y axes
		gc.strokeLine(0, originy, width, originy);
		gc.strokeLine(originx, 0, originx, height);

	}

	/**Method to plot a point given an x or y
	 * @param x The x coordinate of the point
	 * @param y The y coordinate of the point
	 */
	public void plotPoint(int x, int y){
		GraphicsContext gc = getGraphicsContext2D();

		gc.setStroke(Color.BLACK);
		gc.setLineWidth(2);
		gc.setFill(Color.MAGENTA);


		//Figure out the location in pixels using the proper origin
		double piX = originx+x*unit;
		double piY = originy-y*unit;

		//Plot a point at the location
		gc.strokeRoundRect(piX-1, piY-1, 2, 2, 2, 2);

		//Adds the plotted points to array lists so lines can be drawn when the perimeter is
		//computed.
		prevX.add(piX);
		prevY.add(piY);	

	}


	public void handle(ActionEvent event) {

		if(event.getSource().equals(submitB)){
			try{
				//Grabs the input for the x and y position
				int x = Integer.parseInt(gp.getXField().getText());
				int y = Integer.parseInt(gp.getYField().getText());

				//Plots the point
				plotPoint(x,y);

				//Adds a node to linked list and updates the text area
				gp.addNode();
			}catch(Exception e){
				//Informs the user that the point was not added successfully
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Error: Point not added successfully");
				alert.setContentText("Please enter only integer values.");
				alert.showAndWait();
			}
		}else if(event.getSource().equals(calculatePerimeterB)){
			try{
				gp.calculatePerimeter();
				
				GraphicsContext gc = this.getGraphicsContext2D();
				gc.setStroke(Color.BLACK);
				gc.setLineWidth(2);
				double[] xarray = new double[prevX.size()];
				double[] yarray = new double[prevY.size()];				
				
				//Converts the array lists that contain the x and y data to arrays
				for(int i = 0; i < prevX.size();i++){
					xarray[i]= prevX.get(i);
					yarray[i] = prevY.get(i);
				}
				
				gc.strokeLine(xarray[0],yarray[0],xarray[xarray.length-1],
							yarray[yarray.length-1]);
				gc.fillPolygon(xarray, yarray, xarray.length);
				gc.strokePolygon(xarray, yarray, xarray.length);
				
			}catch(Exception e){
				Alert alert = new Alert(Alert.AlertType.ERROR);
				alert.setContentText(e.getMessage());
				alert.setTitle("Perimeter not calculated");
				alert.showAndWait();
			}
		}else if(event.getSource().equals(clearB)){
			try{
				
				//Clearing the x and y text fields
				gp.getXField().clear();
				gp.getYField().clear();		
				
				//Clears the linked list
				gp.getLinkedList().clear();
				
				//Clears the text area
				gp.getTextArea().setText("");
				
				//Clears out the array lists containing the lists of previous points
				prevX.clear();
				prevY.clear();
				
				GraphicsContext gc = this.getGraphicsContext2D();
				gc.clearRect(0, 0, this.getWidth(), this.getHeight());
				
				this.drawGraph();
				
			}catch(Exception e){
				//Could be generated if there were no points yet, but it would mostly be
				//inconvenient for the user to know this.
				
			}
		}


	}

}

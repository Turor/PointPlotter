package application;
	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

import javafx.scene.control.TextArea;

import javafx.scene.paint.Color;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			Group root = new Group();

			//Create the background
			Canvas canvas1 = new Canvas(600,600);
			root.getChildren().add(canvas1);
			
			GraphicsContext gc1 = canvas1.getGraphicsContext2D();
			gc1.setFill(Color.gray(.90));
			gc1.fillRect(0, 0, 600, 600);
			
			//Create the text area which handles the text output of the program
			TextArea ta = new TextArea();
			ta.setEditable(false);
			ta.setPrefHeight(100);
			ta.setPrefWidth(400);
			ta.setLayoutY(350);
			ta.setLayoutX(100);
			
			root.getChildren().add(ta);
			
			
			
			//Create the linked list grid pane which handles the user input
			LinkedListGridPane gp = new LinkedListGridPane(ta);
			gp.setLayoutY(450);
			gp.setLayoutX(100);
			gp.setPrefHeight(100);
			gp.setPrefWidth(400);
			
			root.getChildren().add(gp);
			
			//Create the canvas on which to render the graph of the points
			myCanvas canvas2 = new myCanvas(600,330,gp);
			root.getChildren().add(canvas2);
			
			GraphicsContext gc = canvas2.getGraphicsContext2D();
			gc.setStroke(Color.BLACK);
			gc.setLineWidth(2);
			//gc.strokeRect(2, 2, 596, 324);
					
			
			
			
			
			
			Scene scene = new Scene(root,600,600);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}

package mines;

import java.io.IOException;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class MinesFX extends Application {
	HBox hbox;
	GridPane grd;
	Boolean lost=false,won=false;
	Stage mStage;

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) {//start scene
		MyController controller;
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("MineSweeperGame.fxml"));
			hbox = loader.load();
			controller = loader.getController();
		} catch (IOException e) {
			e.printStackTrace();
			return;
		}
		controller.setMain(this);
		hbox.setSpacing(50);
		hbox.setMaxSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);
		Scene scene = new Scene(hbox);
		stage.setScene(scene);
		stage.show();
		stage.setTitle("The Amazing Mines Sweeper");
		mStage=stage;
	}

	Mines gameboard;
	ArrayList<SubButton> bList = new ArrayList<>();// An array of our SubButtons

	public GridPane createGrid(int row, int column, int mines) {
		gameboard = new Mines(row, column, mines);
		bList.clear();
		GridPane gridpane = new GridPane();
		gridpane.setGridLinesVisible(false);
		for (int i = 0; i < row; i++) {// making buttons;
			for (int j = 0; j < column; j++) {
				SubButton tmp = new SubButton(i, j);// adding new button to grid
				bList.add(tmp);// Creating an ListArray of our button list
				tmp.setOnMouseClicked(new ButtonHandler());// left/right click action
				tmp.setPrefWidth(60);// give them a bit of bulk
				tmp.setPrefHeight(60);// High as well
				tmp.setFont(new Font(25));// looks better
				tmp.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
				gridpane.add(tmp, j, i);

			}
		}
		gridpane.setPadding(new Insets(15));
		gridpane.setAlignment(Pos.CENTER);
		return gridpane;

	}

	class ButtonHandler implements EventHandler<MouseEvent> {

		
		@Override
		public void handle(MouseEvent event) {
			if(!lost && !won)
			{
			SubButton tmp = (SubButton) event.getSource();// Casting the event source into a sub button
//			Place holder = gameboard.getPlace(tmp.getX(), tmp.getY());// Instead of writing game board... 100 times we
			// will this
			if (event.getButton().equals(MouseButton.PRIMARY)) {// If it is left click action
				if (!gameboard.open(tmp.getX(), tmp.getY())) {// We hit a mine need to "end game" and show all mines
					lost=true;
					for (SubButton e : bList) {// iterate over our button to get X and Y to "print" all of our mines
						if (gameboard.getPlace(e.getX(), e.getY()).isMine())// if it is a mine change the button to "X"
							gameboard.getPlace(e.getX(), e.getY()).setOpen();// setting it to open

					}
					
				}
				if (gameboard.isDone() && lost==false)// Checking to see if user "won" the game
				{	won=true;
				//create new POP up box with a GIF and a message 
				Label label=new Label("You WON!");
				Image image=new Image(getClass().getResourceAsStream("youdidit.gif"));//getting resource for our picture
				ImageView view=new ImageView(image);
				label.setFont(new Font("Cambria",50));
				VBox root=new VBox();
				root.getChildren().addAll(view,label);
				Scene scene=new Scene(root);
				Stage stage=new Stage();
				stage.setTitle("WINNER!!!");
				stage.setScene(scene);
				stage.show();
	            gameboard.setShowAll(true);// show all the board
				}
				
				for (SubButton e : bList)// Print button "look"
					e.setText(gameboard.get(e.getX(), e.getY()));// setting the look of our "buttons"
				if(lost)
				{	//create new POP up box with a GIF and a message 
					Label label=new Label("HAHA!what a LOSER!");					
					Image image=new Image(getClass().getResourceAsStream("loser.gif"));
					ImageView view=new ImageView(image);
					label.setFont(new Font("Cambria",50));
					VBox root=new VBox();
					root.getChildren().addAll(view,label);
					Scene scene=new Scene(root);
					Stage stage=new Stage();
					stage.setTitle("LOSER");
					stage.setScene(scene);
					stage.show();
		            gameboard.setShowAll(true);// show all the board
					
				}

			} else {// Right mouse click detected, user is setting a flag or removing it!
				gameboard.getPlace(tmp.getX(), tmp.getY()).setFlag();
				tmp.setText(gameboard.get(tmp.getX(), tmp.getY()));
			}

		}
		}

	}

}

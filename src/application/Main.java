package application;
/**
 * Program to provide GUI for Guessing Game,
 * Lotto Cure and Prize window.
 * @author Ivan 
 * @version 5
 */
	
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;


public class Main extends Application {
	
	
	private HashMap<String, String> prizeMap;
	private NewLotto nl;
	private ArrayList<Integer> lottoEntries ;
	private ArrayList<Integer> lottoNumbers;
	private Validation val;
	private GameLogic gl;
	private Label heading;
	private Label instructions;
	private Label playerFeedback;
	private FileMap fileMap;
	private int matches;
	private String playerNums;
	private VBox prizeBox;
	private Image img;
	private ImageView imgView;
	
	
	@Override
	public void start(Stage primaryStage) {
		try {
			
			//instantiate objects to work with methods in controller classes
			nl = new NewLotto();
			val = new Validation();
			gl = new GameLogic();
			fileMap = new FileMap();
			lottoEntries = new ArrayList<Integer>();
			//generate lotto numbers
			lottoNumbers = nl.generateNumbers();
			//print numbers for testing
			System.out.println("Lotto Numbers: " + lottoNumbers);
			System.out.println("Guessing Game number: " + gl.getNumber());
			
			//root pane
			BorderPane root = new BorderPane();
			
			//VBox for menu | heading | game play information
			VBox top = new VBox();
			
			//menu bar
			HBox menu = new HBox(8);
			menu.setPadding(new Insets(5,15,10,15));
			
			//footer
			HBox footer = new HBox(300);
			footer.setPadding(new Insets(0,15,15,10));
			//footer buttons
			Button reset = new Button("Reset");
			reset.getStyleClass().add("footer");
			Button quit = new Button("Quit");
			quit.getStyleClass().add("footer");
			footer.getChildren().addAll(reset,quit);
			
			//menu buttons
			Button guessGame = new Button("Guessing Game");
			guessGame.setTooltip(new Tooltip("Pick a number between 1 and 100.\nPrize button is activated when correct number guesssed"));
			guessGame.setMinSize(130,40);
			guessGame.getStyleClass().add("menu");
			Button lottoGame = new Button("Lotto Cure");
			lottoGame.setTooltip(new Tooltip("Pick a 6 numbers from the number pad.\nEnter button activated after numbers chosen.\n"
								+ "Match 4 or more numbers to win a prize and\nactivate Prize button"));
			lottoGame.setMinSize(130,40);
			lottoGame.getStyleClass().add("menu");
			Button prize = new Button("Prizes");
			prize.setMinSize(130,40);
			prize.getStyleClass().add("menu");
			
			//disable prize window until prize won
			prize.setDisable(true);
			//add menu buttons
			menu.getChildren().addAll(guessGame,lottoGame,prize);
			
			HBox head = new HBox();
			//head.setMinWidth(500);
			head.setAlignment(Pos.BOTTOM_CENTER);
			heading = new Label("Guessing Game");
			heading.setFont(new Font("Arial", 30));
			heading.setTextFill(Color.BROWN);
			head.getChildren().addAll(heading);
			
			//instructions for player
			instructions = new Label();
			instructions.setText("Enter number between 1-100\nYou have 6 attempts\nPress Submit to enter number");
			instructions.getStyleClass().add("instructions");
			instructions.setPadding(new Insets(20,0,20,60));
			
			//label to respond to game play
			playerFeedback = new Label();
			playerFeedback.setPadding(new Insets(10,0,10,60));
			playerFeedback.getStyleClass().add("playerFeedback");
			
			//area to hold icons for correct/incorrect guesses
			StackPane sp = new StackPane();
			img = new Image("Image/if_Check_27837 (1).png");
	        imgView = new ImageView(img);
	        //add image to icon pane 
	        sp.getChildren().add(imgView);
	        sp.setPadding(new Insets(0,350,0,0));
	        //set to invisible initially | icons visible on guess submit
	        imgView.setVisible(false);
			
			//add menu to top
			top.getChildren().addAll(menu,head,instructions,sp,playerFeedback);
			
			//game play screen
			VBox guessingGame = new VBox();
			guessingGame.setSpacing(10);
			guessingGame.setPadding(new Insets(20,0,20,60));
			
			//text field for player to enter numbers
			TextField guess = new TextField();
			guess.setMaxSize(200,60);
			guess.setPrefSize(5,60);
			guess.requestFocus();
			guess.getStyleClass().add("guess-text");
			
			// button to submit player entries
			Button submit = new Button("Submit");
			submit.getStyleClass().add("submit");
			submit.setDefaultButton(true);

			//event handler submit button
			submit.setOnAction(e -> {
				// VALIDATION
				// first ensure entry is not empty
				if (val.nonEmpty(guess.getText())) {
					// second ensure entry is of integer type
					if (val.isInteger(guess.getText())) {
						// if in here user entered non-empty valid integers
						// check numbers are in range
						if (val.numberRange(1, 100, guess.getText())) {
							int entry = Integer.parseInt(guess.getText());
							String message = gl.guessResult(entry);
							playerFeedback.setText(message);
							//return 4 for winning game otherwise 0
							matches = gl.getStars();
							if (matches >= 4) {
								//enable prize pane
								prize.setDisable(false);
								//set prize area with appropriate prize level
								prizeBox = setPrizeBox(matches);
								//set icon for correct guess
								imgView.setVisible(false);
								img = new Image("Image/if_Check_27837 (1).png");
								imgView = new ImageView(img);
								sp.getChildren().add(imgView);
								imgView.setVisible(true);
							} else {
								//set icon for incorrect guess
								imgView.setVisible(false);
					        	img = new Image("Image/if_Delete_27842 (1).png");
					        	imgView = new ImageView(img);
					        	sp.getChildren().add(imgView);
							}
							//clear text field
							guess.clear();
							// auto return focus to text field
							guess.requestFocus();

						} else {
							//inform player of valid number range
							playerFeedback.setText("Number must be between 1 - 100");
						}
					} else {
						// inform player entry must be integer only
						playerFeedback.setText("You must enter integers only");
					}
				} else {
					// warning for empty entry
					playerFeedback.setText("You must enter guess in text box");
				}
			});
			
			//add to main game area
			guessingGame.getChildren().addAll(guess,submit);
			
			//----------LOTTO CURE--------
			HBox enterBox = new HBox();
			Button enter = new Button("Enter");
			enter.setDisable(true);
			enterBox.getChildren().add(enter);
			enterBox.setPadding(new Insets(10,0,10,10));
			enter.getStyleClass().add("enter");
			
			//pane for number buttons
			FlowPane lottoPane = new FlowPane();
			
			//create buttons | event handler in loop
			for(int x=1;x<=47;x++) {
				//new button object
				Button b = new Button();
				//set text(value) of button
				b.setText(String.valueOf(x));
				b.setMinHeight(40);
				b.setMinWidth(40);
				//add buttons to FlowPane
				lottoPane.getChildren().add(b);
				//set space between buttons
				lottoPane.setHgap(2);
				lottoPane.setVgap(1);
				
				//event handler | parse text to Integer add to ArrayList
				b.setOnAction(e -> {
					//get button text | parse to integer
					int a = Integer.parseInt(b.getText());
					//only six numbers allowed
					if (lottoEntries.size() < 6) {
						if(lottoEntries.contains(a)){
							playerFeedback.setText("Duplicate number - Pick again");
						} else {
						//add to ArrayList
						lottoEntries.add(a);
						//show player number entered | list of numbers
						playerNums = "Numbers entered " + lottoEntries;
						//regex to remove commas and square brackets from ArrayList toString output
						playerNums = playerNums.replaceAll("\\W", " ");
						playerFeedback.setText(playerNums);
						}
					}
					else {
						//inform player list is full
						playerFeedback.setText("No more numbers allowed - Press ENTER\n" +  playerNums);
					}
					//enable enter button after six numbers chosen
					if(lottoEntries.size() == 6){
						playerFeedback.setText(playerNums + "\nMaximum numbers chosen. Press ENTER to submit | Reset to retry. ");
						enter.setDisable(false);
					}
				});
			}
			
			//add enter button
			lottoPane.getChildren().add(enterBox);
			
			//event handler for enter button
			enter.setOnAction(e -> {
				//get number of matching numbers
				matches  = nl.compareNumbers(lottoNumbers, lottoEntries);
				//get matching numbers for printing
				String matchedNumbers = nl.matchedNumbers(lottoNumbers, lottoEntries);
				//get results for player
				String resultMessage = nl.playerMessage(lottoNumbers, lottoEntries, matchedNumbers, matches);
				//display message to player
				playerFeedback.setText(resultMessage);
				//enable prize button for winning numbers 
				if (matches >= 4) {
					//set prize area with appropriate prize level
					prizeBox = setPrizeBox(matches);
					prize.setDisable(false);
					
				}
				//disable enter button
				enter.setDisable(true);
				
			});
			
			
			//event handlers for menu and footer buttons
			guessGame.setOnAction(e -> {
				//ensure prize window disabled when moving between games
				prize.setDisable(true);
				heading.setText("Guessing Game");
				submit.setDefaultButton(true);
				guess.requestFocus();
				instructions.setText("Enter number between 1-100\nYou have 6 attempts\nPress ENTER to submit number");
				root.setCenter(guessingGame);
				

			});
			
			lottoGame.setOnAction(e -> {
				//ensure prize window disabled when moving between games
				prize.setDisable(true);
				//hide icon
				imgView.setVisible(false);
				heading.setText("Lotto Cure");
				enter.setDefaultButton(true);
				enter.setDisable(true);
				instructions.setText("Choose 6 numbers and press Enter");
				root.setCenter(lottoPane);
			});
			
			
			prize.setOnAction(e -> {
				heading.setText("Prizes");
				//hide icon
				imgView.setVisible(false);
				playerFeedback.setText(" ");
				instructions.setText("Choose prize from drop down and press submit");
				root.setCenter(prizeBox);
				//get appropriate icon for prize level
				switch(matches) {
					case 4:
						img = new Image("Image/stars_4.png");
						break;
					case 5:
						img = new Image("Image/stars_5.png");
						break;
					case 6:
						img = new Image("Image/stars_6.png");
						break;
					default:
						imgView.setVisible(false);
				}
					//add image to view
		        	imgView = new ImageView(img);
		        	imgView.setVisible(true);
		        	sp.setPadding(new Insets(0,0,0,10));
		        	sp.getChildren().add(imgView);
				
			});
			
			reset.setOnAction(e -> {
				//clear feedback label
				playerFeedback.setText(""); 
				//reset game data for Guessing Game
				gl = new GameLogic();
				imgView.setVisible(false);
				//reset data for Lotto Cure
				enter.setDisable(true);
				lottoEntries.clear();
				matches=0;
				nl = new NewLotto();
			});
			
			//exit program
			quit.setOnAction(e -> primaryStage.close());
			
			//set initial layout
			root.setTop(top);
			root.setCenter(guessingGame);
			root.setBottom(footer);
			
			Scene scene = new Scene(root,500,520);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			//restrict resizing of window
			primaryStage.setResizable(false);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Method to create prize screen with appropriate prize level loaded
	 * @param matches	number of numbers matched (4/5/6 in Lotto Cure |automatically 4 in Guessing Game)
	 * @return VBox with set prizes
	 */
	public VBox setPrizeBox(int matches) {
		
		//HashMap with prize pairs from text file
		prizeMap = fileMap.generateHashMap(matches);
		
		//VBox to contain prize area
		VBox prizeDisplay = new VBox();
		
		//choice button
		Button prizeSubmit = new Button("Submit");
		prizeSubmit.getStyleClass().add("enter");
		
		// close button for alert
		ButtonType close = new ButtonType("Close");

		// choice box to hold HashMap key values
		ChoiceBox<String> cbox = new ChoiceBox<String>();

		// keySet returns list of keys | use Iterator to run through list
		Iterator<String> myKeys = prizeMap.keySet().iterator();
		// loop through keys add to HashMap
		while (myKeys.hasNext()) {
			cbox.getItems().add(myKeys.next());
		}
		
		if(matches >= 4 && matches <= 6) {
		//set default value to first item
		cbox.setValue(cbox.getItems().get(0));
		}
		//add CSS class
		cbox.getStyleClass().add("c-box");
		
		//alert to inform player of prize won
		Alert a = new Alert(AlertType.INFORMATION);
		
		// event handler for submit button
		prizeSubmit.setOnAction(f -> {
			// gets value associated with key chosen in ChoiceBox
			a.setContentText("Prize: " + prizeMap.get(cbox.getValue()));
			//
			a.setTitle("Prize Information");
			a.setHeaderText(null);
			//need to create a DialogPane instance to enable style sheet access to Alert
			DialogPane dialogPane = a.getDialogPane();
			//get Alert DialogPane and add style sheet
			dialogPane.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			//add class to DialogPane
			dialogPane.getStyleClass().add("my-alert");
			
			// add close button to alert
			a.getButtonTypes().setAll(close);
			// show the alert
			a.show();
			//disable submit after prize chosen
			prizeSubmit.setDisable(true);
		});
		
		// padding for VBox
		prizeDisplay.setPadding(new Insets(20, 60, 110, 90));
		// add labels,choice box,button
		prizeDisplay.getChildren().addAll(cbox, prizeSubmit);
			
		//return VBox with prizes
		return prizeDisplay;
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}

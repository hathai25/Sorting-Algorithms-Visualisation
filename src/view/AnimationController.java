package view;

import cnode.CNode;
import util.RandomCNodes;
import sortingalgorithms.*;

import javafx.animation.SequentialTransition;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.control.TextField;
import javafx.scene.Node;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.util.StringConverter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AnimationController extends BorderPane {
  public static ArrayList<Text> textArrayList;
  public static int countSwap = 0;

  public static final int WINDOW_WIDTH = 1000;
  public static final int WINDOW_HEIGHT = 500;
  public static final int XGAP = 10;
  public static final int BUTTONROW_BOUNDARY = 100;

  public static int NO_OF_CNODES = 20;

  private static AbstractSort abstractSort ;

  private Pane display;// Contains the Cnodes
  private VBox vBox;
  public static VBox history;
  
  private HBox showarr;
  private HBox buttonRow;
  
  private TextField textField;
  private Label label;  
  
  private ScrollPane scrollHistory;
  
  //components in bottonRow
  private Button sortButton;
  private Button randomButton;
  private ChoiceBox<AbstractSort> choiceBox;
  private ChoiceBox<Object> speedBox;
  private Button pauseButton;
  private Button playButton;
  private Button playStart;

  private CNode[] cnodes;
  
  
  public void showArr() {   
	  String arr  = "" ;
	    for(int i = 0; i< NO_OF_CNODES; i++) {
	    	if(i == NO_OF_CNODES -1) arr += String.valueOf(cnodes[i].getValue());
	    	else arr += String.valueOf(cnodes[i].getValue()) + ", ";
	    }  
	    this.textField.setText(arr);
  }

  public AnimationController() {
	  
	  
	  
	AnimationController.textArrayList = new ArrayList<>();
	this.vBox = new VBox();
    this.buttonRow = new HBox();
    
  
    this.display = new Pane();
	this.cnodes = RandomCNodes.randomCNodes(NO_OF_CNODES);
	display.getChildren().addAll(Arrays.asList(cnodes));
	  
	
	//Display the history of sort
	AnimationController.history = new VBox();
    AnimationController.history.setAlignment(Pos.TOP_CENTER);
    AnimationController.history.setMinWidth(200);
    AnimationController.history.setMaxWidth(200);
    this.scrollHistory = new ScrollPane();  
    this.scrollHistory.setContent(history);
    this.scrollHistory.setMinWidth(200);
    this.scrollHistory.setVbarPolicy(ScrollBarPolicy.ALWAYS);
    this.scrollHistory.setHbarPolicy(ScrollBarPolicy.NEVER);
	this.scrollHistory.setStyle("-fx-background: #000000; -fx-border-color: #000000; -fx-padding: 10 0 0 -5");
	  	  
    
    //Display the initial array : showarr(HBox) contains the textField and label
    this.textField = new TextField();
    this.textField.setMaxWidth(WINDOW_WIDTH);
    this.textField.setMinWidth(750);
    this.textField.setEditable(false);
    this.textField.setFocusTraversable(false);   
    showArr();
    this.label = new Label("Initial Array:");
    this.label.setTextFill(Color.color(1,1,1));       
    this.showarr = new HBox(label,textField);
    this.showarr.setSpacing(20);
    this.showarr.setAlignment(Pos.CENTER);
            
    //set layout of borderpane
    this.setCenter(display);
    this.setBottom(vBox);
    this.setRight(scrollHistory);
    
    //vBox contains showarr and buttonRow
    vBox.getChildren().addAll(showarr,buttonRow);
    for (Node b : vBox.getChildren()) {
        VBox.setMargin(b, new Insets(5, 5, 20, 5));
      }
    
    //add button sort, random
    this.sortButton = new Button("Sort");
    this.randomButton = new Button("Random");
    
    //add choicebox
    this.choiceBox = new ChoiceBox<>();    
    List<AbstractSort> abstractSortList = new ArrayList<>(); 
    abstractSortList.add(new BubbleSort());
    abstractSortList.add(new SelectionSort());
    abstractSortList.add(new MergeSort());
    choiceBox.setItems(FXCollections.observableArrayList(    
    	      abstractSortList
    	    ));
    choiceBox.getSelectionModel().select(2);
    choiceBox.setConverter(new StringConverter<AbstractSort>() { 
	      @Override
	      public String toString(AbstractSort abstractSort) {
	        if(abstractSort == null) {
	          return "";
	        } else {
	          return abstractSort.getClass().getSimpleName();
	        }
	      }

	      @Override
	      public AbstractSort fromString(String s) {
	        return null;
	      }
	    });
    
    //Add speedBox
    this.speedBox = new ChoiceBox<>();
    speedBox.getItems().addAll("0 %", "25 %","50 %","100 %","200 %","300 %");
    speedBox.getSelectionModel().select(3);
   
    //Add pauseButton
    this.pauseButton = new Button("Pause");
    this.playButton = new Button("Play");
    this.playStart = new Button("Play from start");
    
    //Add buttons and choiceboxs to buttonRow
    buttonRow.getChildren().add(speedBox);        
    buttonRow.getChildren().add(sortButton);
    buttonRow.getChildren().add(randomButton);
    buttonRow.getChildren().add(choiceBox);
    buttonRow.getChildren().add(pauseButton);
    buttonRow.getChildren().add(playButton);
    buttonRow.getChildren().add(playStart);    
    buttonRow.setAlignment(Pos.CENTER);
    for (Node b : buttonRow.getChildren()) {
      HBox.setMargin(b, new Insets(5, 5, 20, 5));
    }
 
    //Action for sortButton
    sortButton.setOnAction(event -> {
      textArrayList.clear();
      countSwap = 0;
      history.getChildren().clear();
      sortButton.setDisable(true);
      randomButton.setDisable(true);
      choiceBox.setDisable(true);

      abstractSort = choiceBox.getSelectionModel().getSelectedItem();

      SequentialTransition sq = new SequentialTransition();
      pauseButton.setOnAction(e ->{ 
    	  String value = (String) speedBox.getSelectionModel().getSelectedItem();
      	String[] words = value.split(" %");
      	double speed = Double.parseDouble(words[0]);
      	sq.setRate(speed/100);
  	  sortButton.setDisable(true);
        randomButton.setDisable(true);
        choiceBox.setDisable(true);
  	  sq.pause();
    });
      playButton.setOnAction(e ->{ 
    	  String value = (String) speedBox.getSelectionModel().getSelectedItem();
      	String[] words = value.split(" %");
      	double speed = Double.parseDouble(words[0]);
      	sq.setRate(speed/100);
  	  sortButton.setDisable(true);
        randomButton.setDisable(true);
        choiceBox.setDisable(true);
  	  sq.play();
    });
      playStart.setOnAction(e ->{ 
    	  String value = (String) speedBox.getSelectionModel().getSelectedItem();
        	String[] words = value.split(" %");
        	double speed = Double.parseDouble(words[0]);
        	sq.setRate(speed/100);
    	  sortButton.setDisable(true);
          randomButton.setDisable(true);
          choiceBox.setDisable(true);
    	  sq.playFromStart();
      });
      
      
      	String value = (String) speedBox.getSelectionModel().getSelectedItem();
      	String[] words = value.split(" %");
      	double speed = Double.parseDouble(words[0]);
      	sq.setRate(speed/100);
      

      sq.getChildren().addAll(abstractSort.startSort(cnodes));

      sq.setOnFinished(e -> {
        sortButton.setDisable(false);
        randomButton.setDisable(false);
        speedBox.setDisable(false);
        choiceBox.setDisable(false);
      });

      sq.play();

    });
    
    //Action for randomButton
    randomButton.setOnAction(event -> {
      sortButton.setDisable(false);
      display.getChildren().clear();
      cnodes = RandomCNodes.randomCNodes(NO_OF_CNODES);
      
      // display the initial array
      showArr();

      display.getChildren().addAll(Arrays.asList(cnodes));
    });    
  }

} 
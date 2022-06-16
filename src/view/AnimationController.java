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
import javafx.scene.control.TextField;
import javafx.scene.Node;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.util.StringConverter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AnimationController extends BorderPane {

  public static final int WINDOW_WIDTH = 1000;
  public static final int WINDOW_HEIGHT = 500;
  public static final int XGAP = 10;
  public static final int BUTTONROW_BOUNDARY = 100;

  public static int NO_OF_CNODES = 40;

  private static AbstractSort abstractSort;

  private Pane display;
  private VBox vBox;
  
  private HBox showarr;
  private HBox buttonRow;
  
  private TextField textField;
  private Label label;

  private Button sortButton;
  private Button randomButton;
  private ChoiceBox<AbstractSort> choiceBox;

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
    this.display = new Pane();
    this.vBox = new VBox();
    
    this.buttonRow = new HBox();
    
    this.textField = new TextField();
    this.textField.setMaxWidth(WINDOW_WIDTH);
    this.textField.setMinWidth(750);
    this.textField.setEditable(false);
    this.textField.setFocusTraversable(false);
    
    this.label = new Label("Initial Array:");
    this.label.setTextFill(Color.color(1,1,1));
    
    this.showarr = new HBox(label,textField);
    this.showarr.setSpacing(20);
    this.showarr.setAlignment(Pos.CENTER);
    		

    this.setCenter(display);
    this.setBottom(vBox);
    

    this.sortButton = new Button("Sort");
    this.randomButton = new Button("Random");
    this.choiceBox = new ChoiceBox<>();

    this.cnodes = RandomCNodes.randomCNodes(NO_OF_CNODES);
    
    showArr();
    
    vBox.getChildren().addAll(showarr,buttonRow);
    for (Node b : vBox.getChildren()) {
        VBox.setMargin(b, new Insets(5, 5, 20, 5));
      }
    

    buttonRow.getChildren().add(sortButton);
    buttonRow.getChildren().add(randomButton);
    buttonRow.getChildren().add(choiceBox);

    buttonRow.setAlignment(Pos.CENTER);

    for (Node b : buttonRow.getChildren()) {
      HBox.setMargin(b, new Insets(5, 5, 20, 5));
    }


    List<AbstractSort> abstractSortList = new ArrayList<>(); // chua hieu
    abstractSortList.add(new BubbleSort());
    abstractSortList.add(new SelectionSort());
    abstractSortList.add(new MergeSort());

    display.getChildren().addAll(Arrays.asList(cnodes));

    sortButton.setOnAction(event -> {                           // chua hieu
      sortButton.setDisable(true);
      randomButton.setDisable(true);

      abstractSort = choiceBox.getSelectionModel().getSelectedItem();

      SequentialTransition sq = new SequentialTransition();

      sq.getChildren().addAll(abstractSort.startSort(cnodes));

      sq.setOnFinished(e -> {
        randomButton.setDisable(false);
      });

      sq.play();

    });

    randomButton.setOnAction(event -> {
      sortButton.setDisable(false);
      display.getChildren().clear();

      cnodes = RandomCNodes.randomCNodes(NO_OF_CNODES);
      
      // hien day thay doi theo random()
      showArr();

      display.getChildren().addAll(Arrays.asList(cnodes));
    });

    choiceBox.setItems(FXCollections.observableArrayList(     // chua hieu
      abstractSortList
    ));

    choiceBox.getSelectionModel().select(5);

    choiceBox.setConverter(new StringConverter<AbstractSort>() {  // chua hieu
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

  }

} 
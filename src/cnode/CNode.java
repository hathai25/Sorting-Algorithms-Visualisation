package cnode;

import javafx.animation.TranslateTransition;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class CNode extends VBox {
  
  public Rectangle rectangle;
  private Text number;
  
  private int value;

  public CNode(int n) {
	this.rectangle = new Rectangle();
	this.number = new Text(String.valueOf(n));
	number.setStroke(Color.WHITE);
	this.getChildren().addAll(rectangle,number);  
    this.value = n;
  }

  public int getValue() {
    return this.value;
  }
  
  public void setValue(int value) {
	    this.value = value;
	  }

  public TranslateTransition moveX(int x) {
    TranslateTransition t = new TranslateTransition();
    t.setNode(this);
    t.setDuration(Duration.millis(200));
    t.setByX(x);

    return t; 
  }  
  
}

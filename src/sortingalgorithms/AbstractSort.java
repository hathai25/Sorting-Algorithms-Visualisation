package sortingalgorithms;

import cnode.CNode;
import view.AnimationController;

import java.util.ArrayList;
import java.util.List;

import javafx.animation.FillTransition;
import javafx.animation.ParallelTransition;
import javafx.animation.Transition;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.util.Duration;

public abstract class AbstractSort {

  final Color START_COLOR = Color.CHARTREUSE;
  final Color SELECT_COLOR = Color.BEIGE;
  final Color SORTED_COLOR = Color.ROYALBLUE;

  static int DX;
  static {
    DX = AnimationController.WINDOW_WIDTH / AnimationController.NO_OF_CNODES;
  }

  ParallelTransition colorCNode(CNode[] arr, Color color, int...a) {
    ParallelTransition pt = new ParallelTransition();
    
    for (int i = 0; i < a.length; i++) {
      FillTransition ft = new FillTransition();
      ft.setShape(arr[a[i]].rectangle);
      ft.setToValue(color);
      ft.setDuration(Duration.millis(200));
      pt.getChildren().add(ft);
    }
    return pt;
  }

  ParallelTransition colorCNode(List<CNode> list, Color color) {
    ParallelTransition pt = new ParallelTransition();
    
    for (CNode c : list) {
      FillTransition ft = new FillTransition();
      ft.setShape(c.rectangle);
      ft.setToValue(color);
      ft.setDuration(Duration.millis(100));
      pt.getChildren().add(ft);
    }

    return pt;
  }

  ParallelTransition swap(CNode[] arr, int i, int j) {
    ParallelTransition pt = new ParallelTransition();
  
    int dxFactor = j - i;
    String message = "Swap " + arr[i].getValue() + " and " + arr[j].getValue();
    Text textHistory = new Text(message);
    textHistory.setStroke(Color.WHITE);
    AnimationController.history.getChildren().add(textHistory);
    pt.getChildren().addAll(arr[i].moveX(DX * dxFactor), arr[j].moveX(-DX * dxFactor));

    CNode tmp = arr[i];
    arr[i] = arr[j];
    arr[j] = tmp;

    return pt;
  }

  public abstract ArrayList<Transition> startSort(CNode[] arr);
}

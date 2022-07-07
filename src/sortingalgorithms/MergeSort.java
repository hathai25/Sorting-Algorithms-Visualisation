package sortingalgorithms;

import cnode.CNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javafx.animation.FadeTransition;
import javafx.animation.ParallelTransition;
import javafx.animation.Transition;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.util.Duration;
import view.AnimationController;

public class MergeSort extends AbstractSort {

  private CNode[] tmp;

  private ArrayList<Transition> merge(CNode[] arr, int p, int q, int r) {
    ArrayList<Transition> transitions = new ArrayList<>();       
    ArrayList<FadeTransition> fadeTransitions = new ArrayList<>();
    int countFade = 0;
    
    String message = "Subarray with index from " + p + " to " + r;
    Text textHistory = new Text(message);
    textHistory.setStroke(Color.WHITE);
    AnimationController.textArrayList.add(textHistory);
    FadeTransition ft1 = new FadeTransition();
    ft1.setNode(AnimationController.textArrayList.get(AnimationController.countSwap));
    ft1.setFromValue(0);
    ft1.setToValue(1);
    ft1.setCycleCount(1);
    ft1.setDuration(Duration.millis(500));
    AnimationController.history.getChildren().add(AnimationController.textArrayList.get(AnimationController.countSwap));
    AnimationController.countSwap ++;
    fadeTransitions.add(ft1);
    
    List<CNode> tmpList = new ArrayList<>();

    for (int i = p; i <= r; i++) {
      tmp[i] = arr[i];
      tmpList.add(tmp[i]);
    }

    int i = p;
    int j = q + 1;
    int k = p;

    while (i <= q && j <= r) {
      if (tmp[i].getValue() <= tmp[j].getValue()) {
    	message = "		Add "+ tmp[i].getValue()+" to subarray ";
    	textHistory = new Text(message);
    	textHistory.setStroke(Color.WHITE);
    	AnimationController.textArrayList.add(textHistory);
        FadeTransition ft = new FadeTransition();
        ft.setNode(AnimationController.textArrayList.get(AnimationController.countSwap));
        ft.setFromValue(0);
        ft.setToValue(1);
        ft.setCycleCount(1);
        ft.setDuration(Duration.millis(500));
        AnimationController.history.getChildren().add(AnimationController.textArrayList.get(AnimationController.countSwap));
        AnimationController.countSwap ++;
        fadeTransitions.add(ft);
        arr[k++] = tmp[i++];
      } else {
    	message ="		Add "+ tmp[j].getValue()+" to subarray ";
      	textHistory = new Text(message);
      	textHistory.setStroke(Color.WHITE);
      	AnimationController.textArrayList.add(textHistory);
        FadeTransition ft = new FadeTransition();
        ft.setNode(AnimationController.textArrayList.get(AnimationController.countSwap));
        ft.setFromValue(0);
        ft.setToValue(1);
        ft.setCycleCount(1);
        ft.setDuration(Duration.millis(500));
        AnimationController.history.getChildren().add(AnimationController.textArrayList.get(AnimationController.countSwap));
        AnimationController.countSwap ++;
        fadeTransitions.add(ft);
        arr[k++] = tmp[j++];
      }
    }

    while (i <= q) {
      message = "		Add "+ tmp[i].getValue()+" to subarray ";
      textHistory = new Text(message);
      textHistory.setStroke(Color.WHITE);
      AnimationController.textArrayList.add(textHistory);
      FadeTransition ft = new FadeTransition();
      ft.setNode(AnimationController.textArrayList.get(AnimationController.countSwap));
      ft.setFromValue(0);
      ft.setToValue(1);
      ft.setCycleCount(1);
      ft.setDuration(Duration.millis(500));
      AnimationController.history.getChildren().add(AnimationController.textArrayList.get(AnimationController.countSwap));
      AnimationController.countSwap ++;
      fadeTransitions.add(ft);
      arr[k++] = tmp[i++];
    }

    while (j <= r) {
      message = "		Add "+ tmp[j].getValue()+" to subarray ";
      textHistory = new Text(message);
      textHistory.setStroke(Color.WHITE);
      AnimationController.textArrayList.add(textHistory);
      FadeTransition ft = new FadeTransition();
      ft.setNode(AnimationController.textArrayList.get(AnimationController.countSwap));
      ft.setFromValue(0);
      ft.setToValue(1);
      ft.setCycleCount(1);
      ft.setDuration(Duration.millis(500));
      AnimationController.history.getChildren().add(AnimationController.textArrayList.get(AnimationController.countSwap));
      AnimationController.countSwap ++;
      fadeTransitions.add(ft);
      arr[k++] = tmp[j++];
    }

    transitions.add(colorCNode(tmpList, SELECT_COLOR));

    ParallelTransition pt = new ParallelTransition(); 

    pt.getChildren().add(fadeTransitions.get(countFade++));
    for (int x = p; x <= r; x++) {
      for (int y = p; y <= r; y++) {
        if (tmp[x].equals(arr[y])) {

          pt.getChildren().addAll(tmp[x].moveX(DX * (y - x)),fadeTransitions.get(countFade++));
        }
      }
  
    }

    transitions.add(pt);
    transitions.add(colorCNode(tmpList, SELECT_COLOR));

    return transitions;
  }

  private ArrayList<Transition> mergeSort(CNode[] arr, int p, int r) {
    ArrayList<Transition> transitions = new ArrayList<>();

    if (p < r) {
      int q = (p + r) / 2;
      transitions.addAll(mergeSort(arr, p, q));
      transitions.addAll(mergeSort(arr, q + 1, r));
      transitions.addAll(merge(arr, p, q, r));
    }

    return transitions;
  }

  @Override
  public ArrayList<Transition> startSort(CNode[] arr) {
    ArrayList<Transition> transitions = new ArrayList<>();

    this.tmp = new CNode[arr.length];

    transitions.addAll(mergeSort(arr, 0, arr.length - 1));

    transitions.add(colorCNode(Arrays.asList(arr), SORTED_COLOR));

    return transitions;
  }

}

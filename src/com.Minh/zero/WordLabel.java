package com.zero;

import javafx.scene.control.Label;

public class WordLabel extends Label {
    private Controller controller;
    public WordLabel(String text,Controller controller) {
        super(text);
        this.controller = controller;
//        this.setOnAction(event -> {
//            this.OnClicked();
//        });
    }
    public void OnClicked() {
        controller.onClickCellListView(this.getText());
    }

    @Override
    public String toString() {
        this.OnClicked();
        return super.toString();
    }
}

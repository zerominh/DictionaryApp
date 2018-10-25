package com.zero;

import javafx.fxml.FXML;
import java.io.IOException;

import com.darkprograms.speech.translator.GoogleTranslate;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class ControllerTranslate {
    @FXML
    public TextField en;
    @FXML
    public TextArea vi;
    public void onClickedTranslate() {
        String enText = en.getText();
        try {


            String viText;


            String lang = GoogleTranslate.detectLanguage(enText);
            if(lang.startsWith("vi")) {
                viText = GoogleTranslate.translate("en-US",
                        enText);
            } else {
                viText = GoogleTranslate.translate("vi",
                        enText);
            }

            vi.setText(viText);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

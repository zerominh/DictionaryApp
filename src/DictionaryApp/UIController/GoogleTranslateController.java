package DictionaryApp.UIController;

import DictionaryApp.*;
import com.darkprograms.speech.translator.GoogleTranslate;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GoogleTranslateController implements Initializable {


    @FXML
    private ImageView speakerOutput;

    @FXML
    private JFXDrawer navDrawer;

    @FXML
    private ChoiceBox<?> outputLanguageChoice;

    @FXML
    private TextArea outputWord;

    @FXML
    private TextField inputWord;

    @FXML
    private ImageView speakerInput;

    @FXML
    private AnchorPane topBar;

    @FXML
    private ChoiceBox<?> inputLanguageChoice;

    @FXML
    private JFXButton translate;

    @FXML
    private JFXHamburger hamburger;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            VBox box = FXMLLoader.load(getClass().getResource("../../UIUX/NavDrawer.fxml"));
            navDrawer.setSidePane(box);

            /*Change the Menu button: Menu <-> Arrow*/
            HamburgerBackArrowBasicTransition transition = new HamburgerBackArrowBasicTransition(hamburger);
            transition.setRate(-1);
            hamburger.addEventHandler(MouseEvent.MOUSE_PRESSED, (e) -> {
                transition.setRate(transition.getRate() * -1);
                transition.play();

                if (navDrawer.isOpened()) {
                    navDrawer.close();
                }
                else {
                    navDrawer.open();
                }
            });
        }

        catch (IOException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void onClickedTranslate() {
        String enText = inputWord.getText();
        try {
            String viText = GoogleTranslate.translate("vi", "en-US", enText);
            outputWord.setText(viText);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void onClickSpeakerButtonInput() {
        GTTS gtts = new GTTS();
        if(Utils.netIsAvailable()) {
            gtts.speak(inputWord.getText());
        } else {
            Utils.showAlertWithHeaderText("Please connect internet!!!");
        }
    }

    public void onClickSpeakerButtonOutput() {
        GTTS gtts = new GTTS();
        if(Utils.netIsAvailable()) {
            gtts.speak(outputWord.getText());
        } else {
            Utils.showAlertWithHeaderText("Please connect internet!!!");
        }
    }


}

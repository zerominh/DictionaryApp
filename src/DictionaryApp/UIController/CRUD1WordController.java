package DictionaryApp.UIController;

import DictionaryApp.*;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CRUD1WordController implements Initializable {
    @FXML
    private JFXDrawer navDrawer;

    @FXML
    private Button ButtonSaveModify;

    @FXML
    private TextArea showWordTarget;

    @FXML
    private ImageView speaker;

    @FXML
    private Button ButtonDelete;

    @FXML
    private TextArea showWordExplain;

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

    public void onButtonAddFuncClicked() {
        String enWord = showWordTarget.getText();
        String viWord = showWordExplain.getText();
        if (enWord.length() == 0) {
            showWordTarget.setText("English word is empty");
            return;
        }
        if (viWord.length() == 0) {
            showWordExplain.setText("Vietnamese word is empty");
            return;
        }
        Dictionary dictionary = DictionaryApplication.dictionaryManagement.getDictionary();
        dictionary.addWord(new Word(enWord, viWord));
        showWordTarget.setVisible(true);
        showWordExplain.setVisible(true);
    }

    public void onButtonDeleteFuncClicked() {
        String enW = showWordTarget.getText();
        Dictionary dictionary = DictionaryApplication.dictionaryManagement.getDictionary();
        if (dictionary.removeWord(enW)) {
            showWordExplain.setText("Tu ban muon xoa khong ton tai");
        } else {
            showWordExplain.setText("Ban da xoa thanh cong");
//            refreshListView();
        }
    }

    public JFXDrawer getNavDrawer() {
        return navDrawer;
    }

    public void setNavDrawer(JFXDrawer navDrawer) {
        this.navDrawer = navDrawer;
    }

    public Button getButtonSaveModify() {
        return ButtonSaveModify;
    }

    public void setButtonSaveModify(Button buttonSaveModify) {
        ButtonSaveModify = buttonSaveModify;
    }

    public TextArea getShowWordTarget() {
        return showWordTarget;
    }

    public void setShowWordTarget(TextArea showWordTarget) {
        this.showWordTarget = showWordTarget;
    }

    public ImageView getSpeaker() {
        return speaker;
    }

    public void setSpeaker(ImageView speaker) {
        this.speaker = speaker;
    }

    public Button getButtonDelete() {
        return ButtonDelete;
    }

    public void setButtonDelete(Button buttonDelete) {
        ButtonDelete = buttonDelete;
    }

    public TextArea getShowWordExplain() {
        return showWordExplain;
    }

    public void setShowWordExplain(TextArea showWordExplain) {
        this.showWordExplain = showWordExplain;
    }

    public JFXHamburger getHamburger() {
        return hamburger;
    }

    public void setHamburger(JFXHamburger hamburger) {
        this.hamburger = hamburger;
    }
}

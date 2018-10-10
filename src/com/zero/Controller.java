package com.zero;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.ArrayList;
import java.util.List;

public class Controller {
    public static final int M = 100;
    private int curI =0;
    private ArrayList<Word> words;
    private DictionaryManagement dictionaryManagement;
    private GTTS gtts;
    private Mode curMode = Mode.Search;
    private StringBuilder curEnWord;
    public static  boolean isSearch = true;
    @FXML
    private Label wordLabel;
    @FXML
    private TextField textSearch;
    @FXML
    private TextArea explainTextArea;
    @FXML
    private ListView wordsLV;
    @FXML
    private TextField enText;
    @FXML
    private TextField viText;
    @FXML
    private Button btnSearch;


    @FXML
    public void initialize() {
        gtts = new GTTS();
        enText.setVisible(false);
        viText.setVisible(false);
        dictionaryManagement = new DictionaryManagement();
        dictionaryManagement.insertFromFile();
        curEnWord = new StringBuilder();
        textSearch.setOnKeyReleased(event -> {
            switch (event.getCode()) {
                case ENTER:
                    SwitchFunc();
                    curEnWord = new StringBuilder(textSearch.getText().trim().toLowerCase().replace(" +", " ").replace("\n", ""));

                    break;
                default:

                    if(isSearch) {
                        System.out.println("search...");
                        isSearch = false;
                        try {
                            Platform.runLater(
                                    () -> {
                                        curEnWord = new StringBuilder(textSearch.getText().trim().toLowerCase().replace(" +", " ").replace("\n", ""));
                                        searchFirstSubWordAndUpdateListView();
                                        isSearch = true;
                                        // Update UI here.
                                    }
                            );

                            //Start the Thread

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    break;
            }
        });
        wordsLV.setOnMouseClicked(event -> {
            wordsLV.getSelectionModel().getSelectedItems().toString();

        });
        wordsLV.setOnScroll(event -> {

                if((int) event.getDeltaY() == -40) {
                }

        });
    }


    public void onButtonSearchClicked() {

        System.out.println("buttton clicked");

        SwitchFunc();


//        wordsLV.getItems().addAll("xin chao");

    }

    private void SwitchFunc() {
        switch (curMode) {
            case Search: {
                searchWordInTextField();
                break;
            }
            case Delete: {
                deleteWord();
                break;
            }
            case Edit: {
                editWord();
                break;
            }
            case Add: {
                addWord();
                break;
            }
        }
    }

    private void searchWordInTextField() {
        String searchEnW = textSearch.getText().trim().toLowerCase().replace(" +", " ").replace("\n", "");
        String viW = dictionaryManagement.searchDictionary(searchEnW);
        if (viW != null) {
            wordLabel.setText(searchEnW);
            explainTextArea.setText(viW);
        } else {
            explainTextArea.setText("Not Found!!!");
        }
    }

    private void searchFirstSubWordAndUpdateListView() {
        String searchEnW = curEnWord.toString().trim().toLowerCase().replace(" +", " ").replace("\n", "");
        System.out.println("searchEnW: ." + searchEnW + ".");
        if (searchEnW.length() >= 1) {
            List<String> enWords = dictionaryManagement.searchFirstSubWord(searchEnW);
            if (enWords != null) {
                wordsLV.getItems().clear();
                System.out.println("list length: " + enWords.size());
                for (String s : enWords) {
                    wordsLV.getItems().add(new WordLabel(s, this));
                }
            }
        } else {
            refreshListView();
        }

    }

    private void refreshListView() {
        words = dictionaryManagement.getDictionary().getWords();
        wordsLV.getItems().clear();
        for(int i = curI/M; i < M && i < words.size(); ++i) {
            wordsLV.getItems().add(new WordLabel(words.get(i).getWordTarget(), this));
        }
        if(curI < words.size()) {
            curI += M;
        } else {
            curI = 0;
        }

    }

    public void onClickSpeakerButton() {
        gtts.speak(textSearch.getText());
    }

    public void onClickCellListView(String text) {
        curEnWord = new StringBuilder(text);
        textSearch.setText(text);
        String viW = dictionaryManagement.searchDictionary(text);
        if (viW != null) {
            wordLabel.setText(text);
            explainTextArea.setText(viW);
        } else {
            explainTextArea.setText("Not Found!!!");
        }
    }

    public void onButtonSearchFuncClicked() {
        curMode = Mode.Search;
        btnSearch.setText("Search");
        enText.setVisible(false);
        viText.setVisible(false);
    }

    public void onButtonDeleteFuncClicked() {
        curMode = Mode.Delete;
        btnSearch.setText("Del");
        enText.setVisible(false);
        viText.setVisible(false);
    }

    public void onButtonAddFuncClicked() {
        curMode = Mode.Add;
        btnSearch.setText("Add");
        textSearch.setPromptText("English Word");
        enText.setVisible(false);
        viText.setVisible(true);
    }

    public void onButtonEditFuncClicked() {
        curMode = Mode.Edit;
        btnSearch.setText("Edit");
        enText.setVisible(true);
        viText.setVisible(true);
    }

    /**
     * function
     */
    public void deleteWord() {
        String enW = textSearch.getText().trim().toLowerCase().replace(" +", " ").replace("\n", "");
        Dictionary dictionary = dictionaryManagement.getDictionary();
        boolean retValue = dictionary.deleteWord(new Word(enW, " "));
        if (!retValue) {
            explainTextArea.setText("Tu ban muon xoa khong ton tai");
        } else {
            explainTextArea.setText("Ban da xoa thanh cong");
            refreshListView();
        }
    }

    public void editWord() {
        String editWord = Utils.formatString(textSearch.getText());
        String enWord = Utils.formatString(enText.getText());
        String viWord = Utils.formatString(viText.getText());
        if (enWord.length() == 0) {
            explainTextArea.setText("English word is empty");
            return;
        }
        if (viWord.length() == 0) {
            explainTextArea.setText("Vietnamese word is empty");
            return;
        }
        Dictionary dictionary = dictionaryManagement.getDictionary();
        boolean retValue = dictionary.modifyWord(new Word(editWord, dictionary.findMeaningOfWord(editWord)),
                new Word(enWord, viWord));
        if (!retValue) {
            explainTextArea.setText("Not exiting this word");
        } else {
            explainTextArea.setText("Completely edit word");
            enText.setText("");
            viText.setText("");
        }


    }
    public void addWord() {
        String enWord = Utils.formatString(textSearch.getText());
        String viWord = Utils.formatString(viText.getText());
        if (enWord.length() == 0) {
            explainTextArea.setText("English word is empty");
            return;
        }
        if (viWord.length() == 0) {
            explainTextArea.setText("Vietnamese word is empty");
            return;
        }
        Dictionary dictionary = dictionaryManagement.getDictionary();
        dictionary.addWord(new Word(enWord, viWord));
        explainTextArea.setText("Completely add word");
    }
    public void onClickedTranslate()
    {
        Tranlsate tranlsate = new Tranlsate();
        tranlsate.runApplication(null);
    }

}

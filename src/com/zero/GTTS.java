package com.zero;

import java.io.IOException;
import java.io.InputStream;
import com.darkprograms.speech.synthesiser.SynthesiserV2;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;




/**
 * This is where all begins .
 *
 * @author GOXR3PLUS
 *
 */
public class GTTS {

    //Create a Synthesizer instance
    private SynthesiserV2 synthesizer = new SynthesiserV2("AIzaSyBOti4mM-6x9WDnZIjIeyEU21OpBXqWBgw");
    private String lang = "en-US";
    public  byte[] data = null;
    public  InputStream is = null;
    /**
     * Constructor
     */
    public GTTS() {
        synthesizer.setLanguage(lang);
    }
    public GTTS(String language) {
        lang = language;
        synthesizer.setLanguage(lang);
    }

    /**
     * @return the is
     */
    public InputStream getIs() {
        return is;
    }
    public void speak(String text) {

        //Create a new Thread because JLayer is running on the current Thread and will make the application to lag
        Thread thread = new Thread(() -> {
            try {

                is  = synthesizer.getMP3Data(text);
                Player player = new Player(is);
                player.play();
                System.out.println("Successfully got back synthesizer data");
                //
            } catch (IOException e) {

                e.printStackTrace(); //Print the exception ( we want to know , not hide below our finger , like many developers do...)

            }
            catch(JavaLayerException e) {
                e.printStackTrace();
            }
        });

        //We don't want the application to terminate before this Thread terminates
        thread.setDaemon(false);

        //Start the Thread
        thread.start();
    }
    // public static void main(String[] args) {
    // 	String text = "how are you";
    // 	GTTS gtts = new GTTS();
    // 	gtts.speak(text);
    // 	try {
    // 	String s = GoogleTranslate.translate("vi", "en-US", viText);
    // 	System.out.println(s);
    // 	} catch(Exception e) {
    // 		e.printStackTrace();
    // 	}


    // 	// playMp3(data);
    // }

}

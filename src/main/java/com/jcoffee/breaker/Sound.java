package com.jcoffee.breaker;

import javax.sound.sampled.*;
import java.io.IOException;
import java.net.URL;

/**
 * The class <code>Sound</code> represents a playable sound file.
 *
 * @author Adel Khial
 */

public class Sound {

    private Clip clip;

    public Sound(String ref) {
        URL url = this.getClass().getClassLoader().getResource(ref);

        if(url == null) {
            err("Can't find ref: " + ref);
        }

        try {
            clip = AudioSystem.getClip();
            AudioInputStream ais = AudioSystem.getAudioInputStream(url);
            clip.open(ais);


        } catch(LineUnavailableException | IOException | UnsupportedAudioFileException e) {
            err("Error opening file: " + ref);
        }
    }

    public void play() {
        clip.setFramePosition(0);
        clip.start();
    }

    private void err(String message) {
        System.err.println(message);
        System.exit(0);
    }
}

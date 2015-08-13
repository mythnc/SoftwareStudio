// Programmer: Wu, En-Hsin 100062273 Lab7
// Date: 2012/12/01 21:14:29   
// Revised this code which TA wrote for Lab7 use.
// Use ActionListener instead of ChangeListener to
// embed draw to processing.

import processing.core.PApplet;
import processing.core.PFont;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import cue.lang.Counter;
import cue.lang.WordIterator;

import wordcram.*;

public class LeftDraw extends PApplet implements ActionListener {
    public RightMenu sp;
    private WordCram wc;
    private int sortNum = 30;
    private Counter<String> words;
    private PFont font;
    private boolean barIsDraw;

    @Override
    public void setup() {
        size(790,600);
        background(220);
        smooth();
        font = createFont("MingLiU", 12, true);
        textFont(font);
    }

    @Override
    public void draw() {
        if (wc != null && wc.hasMore())
            wc.drawNext();
        else if (wc != null && !wc.hasMore() && !barIsDraw) {
            drawBar();
            barIsDraw = true;
        }
    }

    // Initialize words
    public void actionPerformed(ActionEvent arg0) {
        // Count Words times
        words = new Counter<String>();
        String s = sp.content.getText();
        for (final String word: new WordIterator(s))
            words.note(word);

        // If there is no word.
        if (words.getTotalItemCount() == 0)
            return;

        // Wipe out everything in sketch
        noStroke();
        fill(220);
        rect(0, 0, width, height);
        barIsDraw = false;

        // Initialize wc
        wc = new WordCram(this)
          .fromTextString(s)
          .withColorer(Colorers.twoHuesRandomSatsOnWhite(this))
          .sizedByWeight(8, 100)
          .withFont("MingLiU")
          .withPlacer(Placers.centerClump())
          .withAngler(Anglers.heaped())
          //.maxNumberOfWordsToDraw(20);
          .maxAttemptsToPlaceWord(500);
    }

    // drawBar: Find word counts and reprsent them by bar
    public void drawBar() {
        final int w = 3;
        final int h = 10;
        final int widthSpace = 2;
        final int heightSpace = 2;

        Word[] word = wc.getWords();
        if (word.length < sortNum)
            sortNum = word.length;
        for (int i = 0; i < sortNum; i++) {
            String s = word[i].word;
            //println(s + " " + words.getCount(s));
            pushMatrix();
            translate(0, (h + heightSpace) * i);
            noStroke();
            fill(30);
            rect(0, 2, w * words.getCount(s), h);
            text(s + " " + words.getCount(s),
                       w * words.getCount(s) + widthSpace, h / 2f + 6);
            popMatrix();
        }
    }

    @Override
    public void mouseClicked() {
        // Fool-Proof (If no word in sketch)
        if (wc == null || words.getTotalItemCount() == 0)
            return;

        noStroke();
        fill(220);
        rect(0, height - 20, 100, height);
        Word clickWord = wc.getWordAt(mouseX, mouseY);
        if (clickWord != null) {
            fill(255, 0, 0);
            String s = clickWord.word;
            text(s + " " + words.getCount(s), 0, height - 8);
            //println(s + " " + words.getCount(s));
        }
    }
}


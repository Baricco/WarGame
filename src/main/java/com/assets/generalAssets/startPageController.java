package com.assets.generalAssets;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.io.IOException;
import java.nio.IntBuffer;
import java.util.ArrayList;

import com.assets.SVGAssets.BufferedImageTranscoder;
import com.assets.SVGAssets.SVGParser;

import javafx.fxml.FXML;
import javafx.scene.image.*;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.shape.SVGPath;
import javafx.util.Pair;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import org.apache.batik.transcoder.TranscoderException;
import org.apache.batik.transcoder.TranscoderInput;
import org.apache.batik.transcoder.TranscoderOutput;




public class startPageController implements Initializable{

    @FXML
    private ImageView imgView;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        BufferedImageTranscoder trans = new BufferedImageTranscoder();

        // In my case I have added a file "svglogo.svg" in my project source folder within the default package.
        // Use any SVG file you like! I had success with http://en.wikipedia.org/wiki/File:SVG_logo.svg
        try (InputStream file = getClass().getResourceAsStream("../resources/com/worldLow.svg")) {
            TranscoderInput transIn = new TranscoderInput(file);
            TranscoderOutput transOut = new TranscoderOutput();
            try {
                trans.transcode(transIn, transOut);
                WritableImage img = getImage(transOut.getBufferedImage());


                // visto che transcode restituisce il risultato nella variabile
                // transOut, bisogna trovare un modo per ottenere la BufferedImage da transOut
                // ancora meglio se riusciamo a ricavare direttamente la WritableImage

                imgView.setImage(img);
            } catch (TranscoderException ex) {
                Logger.getLogger(startPageController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        catch (IOException io) {
            Logger.getLogger(startPageController.class.getName()).log(Level.SEVERE, null, io);
        }
    }
    private WritableImage getImage(BufferedImage img){
        //converting to a good type, read about types here: https://openjfx.io/javadoc/13/javafx.graphics/javafx/scene/image/PixelBuffer.html
        BufferedImage newImg = new BufferedImage(img.getWidth(), img.getHeight(), BufferedImage.TYPE_INT_ARGB_PRE);
        newImg.createGraphics().drawImage(img, 0, 0, img.getWidth(), img.getHeight(), null);

        //converting the BufferedImage to an IntBuffer
        int[] type_int_agrb = ((DataBufferInt) newImg.getRaster().getDataBuffer()).getData();
        IntBuffer buffer = IntBuffer.wrap(type_int_agrb);

        //converting the IntBuffer to an Image, read more about it here: https://openjfx.io/javadoc/13/javafx.graphics/javafx/scene/image/PixelBuffer.html
        PixelFormat<IntBuffer> pixelFormat = PixelFormat.getIntArgbPreInstance();
        PixelBuffer<IntBuffer> pixelBuffer = new PixelBuffer<>(newImg.getWidth(), newImg.getHeight(), buffer, pixelFormat);
        return new WritableImage(pixelBuffer);
    }

    @FXML
    private Pane mapContainer;
    
    @FXML
    private ImageView mapImageView;
    
  
    @FXML
    private void initialize() {

        /*SVGParser svgParser = new SVGParser("src\\main\\resources\\com\\worldLow.svg");
        ArrayList<String> svgPaths = svgParser.parseFile();

        // Loop sui path dell'SVG
       for (String svgPathData : svgPaths) {
            try {
                Region region = svgParser.createRegionFromSvg(svgPathData, mapContainer);
                mapContainer.getChildren().add(region); // Aggiungi la regione al contenitore
            } catch (Exception e) { System.out.println("\nError with: " + svgPathData + "\n\n"); }


            //System.out.println(region.getShape());

            */
            // qui potresti voler considerare la dimensione della regione appena aggiunta per calcolare l'offset successivo
            
       }
        
    //}


    @FXML
    private void switchToSecondary() throws IOException {
        System.out.println("Hai premuto il pulsantozzo");
    }

}

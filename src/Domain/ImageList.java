/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Domain;

import File.ImageGridFile;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 *
 * @author Gabriela
 */
public class ImageList {
    
    public static ArrayList<BufferedImage> images = new ArrayList<>();
    public static ArrayList<String> imagesInformation = new ArrayList<>();
    public static ArrayList<ImageGrid> imagesGridList = new ArrayList<>();
    
    public static ArrayList<BufferedImage> getAllImages (){
        return images;
    }
    public static ArrayList<String> getInfo(){
        return imagesInformation;
    }
    public static void insertImage(BufferedImage image, String information){
        images.add(image);
        imagesInformation.add(information);
    }
}

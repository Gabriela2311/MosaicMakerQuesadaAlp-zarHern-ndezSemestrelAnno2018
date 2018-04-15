/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Domain;

/**
 *
 * @author Gabriela
 */
public class Images {

    //Atributos 
    private String nameImage;
    private int x;
    private int y;
    private String pathImage;
    private int n;

    public Images() {
    }

    public Images(String nameImage, int x, int y, String pathImage, int n) {
        this.nameImage = nameImage;
        this.x = x;
        this.y = y;
        this.pathImage = pathImage;
        this.n = n;
    }

    public String getNameImage() {
        return nameImage;
    }

    public void setNameImage(String nameImage) {
        this.nameImage = nameImage;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public String getPathImage() {
        return pathImage;
    }

    public void setPathImage(String pathImage) {
        this.pathImage = pathImage;
    }

    public int getN() {
        return n;
    }

    public void setN(int n) {
        this.n = n;
    }

    @Override
    public String toString() {
        return "Images{" + "nameImage=" + nameImage + ", x=" + x + ", y=" + y + ", pathImage=" + pathImage + ", n=" + n + '}';
    }
    public int sizeInBytes(){
        return (this.getNameImage().length()*2)+4+4+(this.getPathImage().length()*2)+4;
    }
}

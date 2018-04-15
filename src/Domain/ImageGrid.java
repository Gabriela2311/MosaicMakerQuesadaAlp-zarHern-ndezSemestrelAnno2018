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
public class ImageGrid {
    
    // Atributos
    private String name;
    private int x;
    private int y;
    private int heigth;
    private int witdh;
    private String path;
    private int contador;

    public ImageGrid() {
    }

    public ImageGrid(String name, int x, int y, int heigth, int witdh, String path, int contador) {
        this.name = name;
        this.x = x;
        this.y = y;
        this.heigth = heigth;
        this.witdh = witdh;
        this.path = path;
        this.contador = contador;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public int getHeigth() {
        return heigth;
    }

    public void setHeigth(int heigth) {
        this.heigth = heigth;
    }

    public int getWitdh() {
        return witdh;
    }

    public void setWitdh(int witdh) {
        this.witdh = witdh;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public int getContador() {
        return contador;
    }

    public void setContador(int contador) {
        this.contador = contador;
    }

    @Override
    public String toString() {
        return "ImageGrid{" + "name=" + name + ", x=" + x + ", y=" + y + ", heigth=" + heigth + ", witdh=" + witdh + ", path=" + path + ", contador=" + contador + '}';
    }
    
    public int sizeInBytes(){
        return (this.getName().length()*2)+ 4 + 4 + 4 +4 +(this.getPath().length()*2);
    }
}

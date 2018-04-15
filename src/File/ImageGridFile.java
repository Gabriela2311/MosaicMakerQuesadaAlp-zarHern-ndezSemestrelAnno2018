/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package File;

import Domain.ImageGrid;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Gabriela
 */
public class ImageGridFile {

    private RandomAccessFile RAFgrid;
    private int regsQuantity;
    private int regsSize;
    private String path;

    public ImageGridFile() {
        try {
            this.path = "imagesGrid.dat";
            File file = new File(path);
            start(file);
        } catch (IOException IOE) {
            System.err.println("Error 104: Error en la creacion del archivo");
        }//end try-catch
    }//end method imageGridFile

    public void start(File file) throws IOException {
        this.path = file.getPath();
        this.regsSize = 500;

        if (file.exists() && file.isFile()) {
            throw new IOException(file.getName() + " is an invalid file");
        } else {
            RAFgrid = new RandomAccessFile(file, "rw");
            this.regsQuantity = (int) Math.ceil((double) RAFgrid.length() / (double) this.regsSize);
        }//end if-else
    }//end method start

    public boolean insertValue(int position, ImageGrid imageToInsert) throws IOException {

        if (position >= 0 && position <= regsQuantity) {
            if (imageToInsert.sizeInBytes() > regsSize) {
                System.err.println("Error 105: record size is too large");
                return false;
            } else {
                RAFgrid.seek(position * this.regsSize);
                RAFgrid.writeUTF(imageToInsert.getName());
                RAFgrid.writeInt(imageToInsert.getX());
                RAFgrid.writeInt(imageToInsert.getY());
                RAFgrid.writeInt(imageToInsert.getHeigth());
                RAFgrid.writeInt(imageToInsert.getWitdh());
                RAFgrid.writeUTF(imageToInsert.getPath());
                RAFgrid.writeInt(imageToInsert.getContador());
                return true;
            }//end if-else
        } else {
            System.err.println("Error 106: position is out of bounds");
        }//end if-else
        return true;
    }//end method insertValue

    public boolean addEndRecord(ImageGrid imageGrid) throws IOException {
        boolean success = insertValue(regsQuantity, imageGrid);

        if (success) {
            ++regsQuantity;
        } else {
            return success;
        }//end if-else
        return success;
    }//end method addEndRecord

    public ImageGrid getImage(int position) {
        if (position >= 0 && position <= regsQuantity) {
            try {
                RAFgrid.seek(position * this.regsSize);
                ImageGrid imageGrid = new ImageGrid();

                imageGrid.setName(RAFgrid.readUTF());
                imageGrid.setX(RAFgrid.readInt());
                imageGrid.setY(RAFgrid.readInt());
                imageGrid.setHeigth(RAFgrid.readInt());
                imageGrid.setWitdh(RAFgrid.readInt());
                imageGrid.setPath(RAFgrid.readUTF());
                imageGrid.setContador(RAFgrid.readInt());

                if (imageGrid.getX() == -1) {
                    return null;
                } else {
                    return imageGrid;
                }
            } catch (IOException IOE) {
                System.err.println("Error 103: Error en getImageByPosition");

            }//end try-catch
        }//end if-else
        return null;
    }//end method getImage()
    
     public List<ImageGrid> getAllGrids(){
         List<ImageGrid> imageObjects = new ArrayList<>();
         
         for (int i = 0; i < this.regsQuantity; i++) {
             ImageGrid grid = this.getImage(i);
             if(grid != null){
                 imageObjects.add(grid);
             }//end if
         }//end for
         return imageObjects;
     }//getAllGrids()
    public void close() throws IOException {
        RAFgrid.close();
    }//end method close()
    
    public boolean deleteImage(int x, int y) throws IOException {
        ImageGrid grid;
        // busqueda del registro a eliminar
        for (int i = 0; i < regsQuantity; i++) {
            // obtener la persona en la posicion i
            grid = this.getImage(i);
            if (grid != null) {
                if (grid.getX() == x) {
                    if (grid.getY() == y) {
                        grid.setX(-1);
                        grid.setY(-1);
                        return this.insertValue(i, grid);
                    }//end if
                }//end if
            } // endif
        } // end for
        return false;
    } // end method deleteImage
}

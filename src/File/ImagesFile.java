/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package File;

import Domain.Images;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Gabriela
 */
public class ImagesFile {

    private RandomAccessFile RAFImages;
    private int regsQuantity;
    private int regsSize;
    private String filePath;

    //method to create the file
    public ImagesFile() {
        try {
            this.filePath = "Images.dat";
            File file = new File(filePath);
            start(file);
        } catch (IOException IOE) {
            System.err.println("Error 101: Error en la creación del archivo");
        }//end try-catch

    }//end method ImageFile()

    private void start(File file) throws IOException {
        this.filePath = file.getPath();
        this.regsSize = 500;

        //validation 
        if (file.exists() && file.isFile()) {
            throw new IOException(file.getName() + " is an invalid file");
        } else {
            RAFImages = new RandomAccessFile(file, "rw");
            this.regsQuantity = (int) Math.ceil((double) RAFImages.length() / (double) this.regsSize);
        }//end if-else
    }//end method start()

    public boolean insertImages(int position, Images imageToInsert) throws IOException {

        //validate the position
        if (position >= 0 && position <= this.regsQuantity) {

            //verify the size
            if (imageToInsert.sizeInBytes() > this.regsQuantity) {
                System.err.println("Error 102: record size is too large");
                return false;
            } else {
                RAFImages.seek(position * this.regsSize);
                RAFImages.writeUTF(imageToInsert.getNameImage());
                RAFImages.writeInt(imageToInsert.getX());
                RAFImages.writeInt(imageToInsert.getY());
                RAFImages.writeUTF(imageToInsert.getPathImage());
                RAFImages.writeInt(imageToInsert.getN());
                return true;
            }
        } else {
            System.err.println("Error 103: position is out of bounds");
        }
        return true;
    }//end method insertImages()

    public boolean addEndImage(Images image) throws IOException {

        boolean success = insertImages(this.regsQuantity, image);

        if (success) {
            ++this.regsQuantity;
        }//end if
        return success;
    }//end method addEndImage

    public Images getImageByPosition(int position) {

        if (position >= 0 && position <= this.regsQuantity) {
            try {
                RAFImages.seek(position * this.regsSize);
                Images imageTemp = new Images();
                imageTemp.setNameImage(RAFImages.readUTF());
                imageTemp.setX(RAFImages.readInt());
                imageTemp.setY(RAFImages.readInt());
                imageTemp.setPathImage(RAFImages.readUTF());
                imageTemp.setN(RAFImages.readInt());

            } catch (IOException IOE) {
                System.err.println("Error 103: Error en getImageByPosition");
            }//end try-catch
        }//end if
        return null;
    }//end method getImageByPosition

    public List<Images> getAllImages() {

        List<Images> imagesList = new ArrayList<>();

        for (int i = 0; i < this.regsQuantity; i++) {
            Images imageTemp = this.getImageByPosition(i);

            if (imageTemp != null) {
                imagesList.add(imageTemp);
            }//end if
        }//end for
        return imagesList;
    }//end method getAllImages

    public void close() throws IOException {
        RAFImages.close();
    }//end method close()

    public boolean deleteImage(int x, int y) throws IOException {
        Images image;

        for (int i = 0; i < this.regsQuantity; i++) {
            image = this.getImageByPosition(i);

            if (image != null) {
                if (image.getX() == x) {
                    if (image.getY() == y) {
                        image.setX(-1);
                        image.setY(-1);
                        return this.insertImages(i, image);
                    }//end if
                }//end if
            }//end if
        }//end for
        return false;
    }//end method deleteImage()
}

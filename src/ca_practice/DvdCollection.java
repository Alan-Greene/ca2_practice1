package ca_practice;

import java.io.*;

public class DvdCollection {
    Dvd[] dvds = new Dvd[4];
    int i = 0;

    public void addDvd(String title, String category, Integer year, Integer quantityInStock, Integer quantityCheckedOut){
        Dvd dvd = new Dvd(title, category, year, quantityInStock, quantityCheckedOut);
        dvds[i] = dvd;
        i++;
    }

    // new stuff
    public void checkOutDvd(Integer dvd_id) throws OutOfStockException{
        try{
            dvds[dvd_id - 1].checkOut();
        } catch (OutOfStockException e){
            throw new OutOfStockException(e.getMessage());
        }
    }

    // new stuff
    public boolean returnDvd(Integer dvd_id) {
            return dvds[dvd_id - 1].returnDvd();
    }

    // new stuff
    public void rateDvd(Integer dvd_id, Double rating){
        dvds[dvd_id].rateDvd(rating);
    }

    // new stuff
    public Dvd mostPopular(){
        int counter=0;
        double highest_rating = dvds[0].getRatingTotal();
        Dvd highest_dvd = dvds[0];
        for (Dvd dvd: dvds){
            if (dvd.getRatingTotal() > highest_rating){
                highest_dvd = dvds[counter];
            }
            counter++;
        }

        return highest_dvd;
    }

    public void displayDvd(){
        System.out.printf("%s%n", "DVDs");
        for (int i = 0; i < dvds.length; i++){
            System.out.printf("DVD number: %d Title: %-25s Number Available: %d%n", i+1, dvds[i].getTitle(), dvds[i].getQuantityInStock());
        }
    }

    public void writeToFile(){
        try (PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(new File("files", "movies_out.txt"))))){
            writer.printf("%s %10s %10s %10s %10s %10s %10s%n", "Title", "Category", "Year", "Quantity", "Checked out", "Rating sum", "Rating count");
            for (Dvd dvd: dvds){
                writer.println(dvd);
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}

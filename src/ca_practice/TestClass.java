package ca_practice;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class TestClass {

    // Question 1
    public static void createMoviesFile(String path){
        try (PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(new File(path))))){
            writer.println("The Great Wall,Thriller,2017,3,2");
            writer.println("John Wick Chapter 2,Action,2017,5,0");
            writer.println("Logan,Action,2017,0,5");
            writer.println("Loving,Drama,2017,4,1");
        } catch (IOException e) {
            System.err.println(e.getMessage());
            System.exit(-1);
        }
    }

    public static ArrayList<Dvd> getDvdsFromFile(String path){
        ArrayList<Dvd> dvds = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(path))){
            for (String line; (line = reader.readLine()) != null;) {
                String[] values = line.split(",");
                dvds.add(new Dvd(values[0], values[1], Integer.parseInt(values[2]), Integer.parseInt(values[3]), Integer.parseInt(values[4])));
            }
        } catch (IOException e){
            System.err.println(e.getMessage());
            System.exit(-2);
        }

        return dvds;
    }

    public static void menu(){
        System.out.println("Please press 1 to rent a DVD");
        System.out.println("Please press 2 if you want to return a DVD");
        System.out.println("Please press 3 to save details to the file");
        System.out.println("Please press 4 to quit");
    }

    public static int menuInput(){
        Scanner in = new Scanner(System.in);
        return in.nextInt();
    }

    public static void main(String[] args) {
        createMoviesFile("files/movies.txt");

        ArrayList<Dvd> dvds = getDvdsFromFile("files/movies.txt");

        DvdCollection dvd_col = new DvdCollection();

        for (Dvd dvd: dvds){
            dvd_col.addDvd(dvd.getTitle(), dvd.getCategory(), dvd.getYear(), dvd.getQuantityInStock(), dvd.getQuantityCheckedOut());
        }

        while (true){
            menu();
            int user_choice = menuInput();

            switch (user_choice){
                case 1:{
                    dvd_col.displayDvd();
                    System.out.println("Which DVD would you like to rent?");
                    int dvd_choice = menuInput();
                    try {
                        dvd_col.checkOutDvd(dvd_choice);
                    } catch (OutOfStockException e){
                        System.err.println(e.getMessage());
                    }
                    break;
                }
                case 2:{
                    dvd_col.displayDvd();
                    System.out.println("Enter the number of the DVD you wish to return");
                    int returned_dvd = menuInput();
                    dvd_col.returnDvd(returned_dvd);
                    break;
                }
                case 3:{
                    dvd_col.writeToFile();
                    break;
                }
                case 4:{
                    System.exit(0);
                    break;
                }
                default:{
                    System.out.println("Your input must be between 1 and 4.");
                    break;
                }
            }
        }

    }
}

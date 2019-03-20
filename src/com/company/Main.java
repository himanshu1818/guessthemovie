package com.company;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
public class Main {
    public static void main(String[] args){
        //ArrayList for movie names
        ArrayList<String>movieList=new ArrayList<>();
        //file for movies text file
        File file=new File("movies.txt");
        Scanner scanner=null;
        //try and catch conditions for exceptions
        try{
           scanner=new Scanner(file);
        }
        catch (FileNotFoundException fileNotFoundException) {
            System.out.println("File not found!");
            System.exit(0);
            }
        while(scanner.hasNextLine()){
            String line = scanner.nextLine();
            movieList.add(line);
        }
        //string to store the randomMovie
        String randomMovie;
        //length of arrayList movieList
        int movieLength=movieList.size();
        //choosing random number between 0 and and movieLength
        int randomNumber=(int)(Math.random()*movieLength);
        // selecting a random movieName from the movie list
        randomMovie = movieList.get(randomNumber).toLowerCase();
        //character array of hiddenMovie
        char[] hiddenMovie;
        //scanner for instantiating the user guess
        Scanner keyboard = new Scanner(System.in);
        //string storing the user guess
        String userGuess;
        //minimum number of chances missed
        int missChance = 0;
        //string to store the letter already guessed
        String letterAlreadyGuessed="";
        //character arrayList for storing the missed characters and correctLetters
        ArrayList<String> missedLetters = new ArrayList<>();
        //boolean returning 'true', if letter found and 'false' if not found and initialising solved as 'false'
        boolean letterFound, solved = false;
        //assigning the value to hidden movie array of characters of the same length of movie name
        hiddenMovie = new char[randomMovie.length()];
        //loop for printing  underscores in place of characters in movie name
        for (int i = 0; i < randomMovie.length(); i++) {
            hiddenMovie[i] = '_';
        }
        System.out.println("Welcome to the guess movie game\n");
        // while chances are less than 10 the loop will continue
        while (missChance < 10 && !solved) {
            System.out.println("You have " + (10 - missChance) + " turns left.");
            System.out.print("movie name:\t");
            for (int i = 0; i < randomMovie.length(); i++) {
                System.out.print(hiddenMovie[i] + " ");
            }
            System.out.print("\nMissed character: \n" +missChance);
            //loop for missed characters
            for (int i = 0; i < missedLetters.size(); i++) {
                System.out.print(missedLetters.get(i));
            }
            System.out.print("\nGuess: ");
            while(true) {//loop for checking the user inputs
                userGuess = keyboard.nextLine().toLowerCase();
                if(userGuess.isEmpty()) {//if userGuess is empty then print below message
                    System.out.println("nothing was  entered ..enter a letter ");
                    System.out.println("try again");
                } else if (userGuess.length()!=1) {//if the length is more than 1 then prints below message
                    System.out.println("enter only one character ");
                } else if(userGuess.matches(" ^[a-z+']")||Character.isDigit(userGuess.charAt(0))) {//if it's not a character or if it's a number
                    System.out.println("That is not a letter.");
                }
                else if(missedLetters.contains(userGuess)) {//if arrayList contains that letter already
                    System.out.println("You already guessed that letter.");
                    letterAlreadyGuessed+=userGuess;
                }
                else {
                    System.out.println("the letter guessed is :"+userGuess);
                    break;
                }
            }
            boolean foundAlready=missedLetters.contains(userGuess);//boolean for giving true and false for letter already found
            letterFound = false;
            //iterating through loop for storing the letters if user guess matched the movie character and replacing with underscores with that letter
            for (int i = 0; i < randomMovie.length(); i++) {
                if (userGuess.charAt(0) == randomMovie.charAt(i)) {
                    hiddenMovie[i] = randomMovie.charAt(i);
                    letterFound = true;
                }
            }
            if (!letterFound) {
                if(foundAlready){//if the letter is already guessed then just printing that it was already guessed
               System.out.println(" already guessed "+letterAlreadyGuessed);
                }
                else{
               missedLetters.add(userGuess);//else adding it to the arrayList of missed letters and increasing the misses
                missChance++;
                }
            }
            int counter= 0;
            for (int i = 0; i < randomMovie.length(); i++) {
                if ('_' == hiddenMovie[i])
                    counter++;
            }
            if (counter > 0)
                solved = false;
            else
                solved = true;
        }
            System.out.println("You lost");
            System.out.print("movie:\t");
            for (int i = 0; i < randomMovie.length(); i++) {
                System.out.print(hiddenMovie[i] + " ");
            }
                System.out.print("\nMisses: ");
            // loop for printing the miss characters by printing the letters stored in array of missed characters
                for (int i = 0; i < missedLetters.size(); i++) {
                System.out.print(missedLetters.get(i));
            }
        System.out.println();
            if (solved) {//printing below message if guessed the movie
                System.out.println("You won!");
            } else {
                System.out.println("\nThe movie name was..." + randomMovie);
            }
    }
}



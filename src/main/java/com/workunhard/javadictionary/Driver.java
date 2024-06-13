package com.workunhard.javadictionary;

import java.util.Scanner;

public class Driver {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Welcome to my quick ol' Java dictionary");

        DictionaryApiService apiService = new DictionaryApiService();

        while (true) {
            System.out.println("\nEnter a word (type 'exit' to quit): ");
            String word = sc.nextLine().trim();

            if (word.equalsIgnoreCase("exit")) {
                System.out.println("Exiting the application. Goodbye!");
                break;
            }

            DictionaryEntry[] entries = apiService.getDefinitions(word);
            apiService.printResults(entries);
        }
        sc.close();
    }
}

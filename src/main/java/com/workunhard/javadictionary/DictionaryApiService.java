package com.workunhard.javadictionary;
import com.google.gson.Gson;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * Class container for FreeDictionary API call, parsing, and printing
 */
public class DictionaryApiService {

    private static final Logger logger = Logger.getLogger(DictionaryApiService.class.getName());

    /**
     * Calls the FreeDictionary API with a user-provided word
     *
     * @param word the word to search
     * @return DictionaryEntry[] of parsed details
     */
    public DictionaryEntry[] getDefinitions(String word) {
        if (word == null || word.trim().isEmpty()) {
            logger.log(Level.WARNING, "Word is null or empty");
            return new DictionaryEntry[0]; // Return empty array for null or empty word
        }
        try {
            // Construct URI with the user-inputted word
            URI uri = new URI("https://api.dictionaryapi.dev/api/v2/entries/en/" + word);

            // Create HttpClient instance
            HttpClient client = HttpClient.newHttpClient();

            // Create HttpRequest with URI and headers
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(uri)
                    .header("Accept", "application/json")
                    .build();

            // Send HTTP request and get response
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            // Check response status
            if (response.statusCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : " + response.statusCode());
            }

            // Parse the JSON response
            Gson gson = new Gson();
            return gson.fromJson(response.body(), DictionaryEntry[].class);

        } catch (Exception e) {
            logger.log(Level.SEVERE, "An error occurred while fetching definitions for '" + word + "'", e);
            return new DictionaryEntry[0]; // Return empty array or handle error case appropriately
        }
    }

    /**
     * Prints the results of the getDefinitions method.
     * Assumed one definition for simplicity
     *
     * @param entries the results of the getDefinitions method
     */
    public void printResults(DictionaryEntry[] entries) {
        if (entries.length == 0) {
            System.out.println("No definitions found.");
        } else {
            for (DictionaryEntry entry : entries) {
                System.out.println("Word: " + entry.word);
                System.out.println("Phonetic: " + entry.phonetic);
                System.out.println("Part of Speech: " + entry.meanings.getFirst().partOfSpeech);
                System.out.println("Definition: " + entry.meanings.getFirst().definitions.getFirst().definition);
                System.out.println();
            }
        }
    }
}

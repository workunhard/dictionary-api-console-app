import com.workunhard.javadictionary.DictionaryApiService;
import com.workunhard.javadictionary.DictionaryEntry;
import com.google.gson.Gson;
import org.junit.Test;

import static org.junit.Assert.*;

public class dictionaryTest {

    @Test
    public void testGetDefinitionsValidWord() {
        DictionaryApiService apiService = new DictionaryApiService();
        DictionaryEntry[] entries = apiService.getDefinitions("adorable");

        assertNotNull(entries);
        assertTrue(entries.length > 0);
        assertEquals("adorable", entries[0].word);
    }

    @Test
    public void testGetDefinitionsInvalidWord() {
        DictionaryApiService apiService = new DictionaryApiService();
        DictionaryEntry[] entries = apiService.getDefinitions("invalidword12345");

        assertNotNull(entries);
        assertEquals(0, entries.length);
    }

    @Test
    public void testGetDefinitionsNullWord() {
        DictionaryApiService apiService = new DictionaryApiService();
        DictionaryEntry[] entries = apiService.getDefinitions(null);
        // Check if the entries array is empty
        assertEquals(0, entries.length);
    }

    @Test
    public void testDictionaryEntryDataMapping() {
        String json = "[{\"word\":\"adorable\",\"phonetic\":\"/əˈdɔːɹəbəl/\",\"meanings\":[{\"partOfSpeech\":\"adjective\",\"definitions\":[{\"definition\":\"Befitting of being adored; cute or loveable.\",\"synonyms\":[],\"antonyms\":[]}]}]}]";
        Gson gson = new Gson();
        DictionaryEntry[] entries = gson.fromJson(json, DictionaryEntry[].class);

        assertNotNull(entries);
        assertEquals(1, entries.length);
        assertEquals("adorable", entries[0].word);
        assertEquals("/əˈdɔːɹəbəl/", entries[0].phonetic);
        assertEquals("adjective", entries[0].meanings.get(0).partOfSpeech);
        assertEquals("Befitting of being adored; cute or loveable.", entries[0].meanings.getFirst().definitions.getFirst().definition);
    }





}

package com.workunhard.javadictionary;

import java.util.List;

public class DictionaryEntry {

    public String word;
    public String phonetic;
    public List<Meaning> meanings;

    public static class Meaning {
        public String partOfSpeech;
        public List<Definition> definitions;

        public static class Definition {
            public String definition;
        }
    }
}

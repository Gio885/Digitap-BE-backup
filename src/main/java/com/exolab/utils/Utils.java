package com.exolab.utils;

import org.bson.Document;

public class Utils {

    public Document createDocument(String... args) {
        Document document = new Document();
        for (String s : args) {
            document.append(s, "$" + s);
        }
        return document;
    }

}

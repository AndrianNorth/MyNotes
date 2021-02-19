package ru.geekbrains.mynotes;

import java.io.Serializable;

public class SimpleNotes implements Serializable {
    private final String TITLE;
    private final String DESCRIPTION;
    private final String DATE;

    public SimpleNotes(String TITLE, String DESCRIPTION, String DATE) {
        this.TITLE = TITLE;
        this.DESCRIPTION = DESCRIPTION;
        this.DATE = DATE;
    }

    public String getTITLE() {
        return TITLE;
    }

    public String getDESCRIPTION() {
        return DESCRIPTION;
    }

    public String getDATE() {
        return DATE;
    }
}

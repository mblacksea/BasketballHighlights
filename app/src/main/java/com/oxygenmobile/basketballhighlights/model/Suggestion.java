package com.oxygenmobile.basketballhighlights.model;

public class Suggestion {
    private String id;
    private int star;
    private String comment;

    public Suggestion(String id, int star, String comment) {
        this.id = id;
        this.star = star;
        this.comment = comment;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getStar() {
        return star;
    }

    public void setStar(int star) {
        this.star = star;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}

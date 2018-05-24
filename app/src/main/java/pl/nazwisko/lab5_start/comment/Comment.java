package pl.nazwisko.lab5_start.comment;

/**
 * Model opisujący komentarz pobrany z Rest API.
 *
 * Created by Michał Ciołek.
 */
public class Comment {
    // TODO
    int id;
    String author;
    String comment;

    public Comment(String author, String comment) {
        this.author = author;
        this.comment = comment;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", author='" + author + '\'' +
                ", comment='" + comment + '\'' +
                '}';
    }
}


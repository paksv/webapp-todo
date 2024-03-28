package dev.asjordi.models;

import java.time.LocalDate;

public class Todo {

    private Integer id;
    private String title;
    private String description;
    private LocalDate date;
    private boolean isDone;
    private User user;

    public Todo() {}

    public Todo(String title, String description, LocalDate date, boolean isDone, User user) {
        this.title = title;
        this.description = description;
        this.date = date;
        this.isDone = isDone;
        this.user = user;
    }

    public Todo(Integer id, String title, String description, LocalDate date, boolean isDone, User user) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.date = date;
        this.isDone = isDone;
        this.user = user;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public boolean getIsDone() {
        return isDone;
    }

    public void setIsDone(boolean done) {
        isDone = done;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Todo{" + "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", date=" + date +
                ", isDone=" + isDone +
                ", user=" + user +
                '}';
    }
}

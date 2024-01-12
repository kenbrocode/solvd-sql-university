package solvd.laba.domain;

import java.util.List;

public class Classroom {
    private int id;
    private int size;

    private List<Class> classes;

    public Classroom() {
    }

    public Classroom(int id, int size, List<Class> classes) {
        this.id = id;
        this.size = size;
        this.classes = classes;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public List<Class> getClasses() {
        return classes;
    }

    public void setClasses(List<Class> classes) {
        this.classes = classes;
    }

    @Override
    public String toString() {
        return "Classroom{" +
                "id=" + id +
                ", size=" + size +
                ", classes=" + classes +
                '}';
    }
}
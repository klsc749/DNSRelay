package org.example.dns.model;

public class Question extends Section{
    @Override
    public String toString() {
        return "Question{" +
                "name='" + name + '\'' +
                ", type=" + type +
                ", classCode=" + classCode +
                '}';
    }
}

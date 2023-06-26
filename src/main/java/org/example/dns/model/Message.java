package org.example.dns.model;

import java.util.List;

public class Message {
    private Header header;
    private List<Section> sections;

    public Message(Header header, List<Section> sections) {
        this.header = header;
        this.sections = sections;
    }

    public Header getHeader() {
        return header;
    }

    public List<Section> getSections() {
        return sections;
    }

    @Override
    public String toString() {
        return "Message{" +
                "header=" + header +
                ", records=" + sections +
                '}';
    }
}
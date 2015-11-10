package demo;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "VideoGenerationResponse")
public class Video {
    @XmlElement
    private int id;

    public int getId() {
        return id;
    }
}

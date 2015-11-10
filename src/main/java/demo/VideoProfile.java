package demo;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "VideoGenerationRequest")
public class VideoProfile {
    @XmlElement
    private int id;

    public VideoProfile(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public VideoProfile() {
    }
}

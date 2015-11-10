package demo;

import com.github.dreamhead.moco.HttpServer;
import com.github.dreamhead.moco.Runnable;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import java.io.IOException;

import static com.github.dreamhead.moco.Moco.*;
import static com.github.dreamhead.moco.Runner.running;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
        loader = AnnotationConfigContextLoader.class,
        classes = {
                VideoConfig.class
        }
)
public class VideoGeneratorShould {

    @Autowired
    private VideoGenerator subject;

    @Test
    public void sendVideoProfileToVendor() throws Exception {

        HttpServer server = httpServer(12306);

        // @formatter:off
        server.post(and(
                by(uri("/api/video")),
                xml(text("<VideoGenerationRequest>" +
                            "<id>1</id>" +
                        "</VideoGenerationRequest>"))
        )).response(header("Content-Type", "application/xml"),
                with("<VideoGenerationResponse>" +
                        "<id>1</id>" +
                    "</VideoGenerationResponse>"));
        // @formatter:on

        running(server, new Runnable() {
            @Override
            public void run() throws IOException {

                Video video = subject.generateWith(new VideoProfile(1));

                assertThat(video.getId(), is(1));
            }
        });

    }
}

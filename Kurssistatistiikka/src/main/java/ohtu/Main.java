package ohtu;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.IOException;
import org.apache.http.client.fluent.Request;

public class Main {

    public static void main(String[] args) throws IOException {
        // ÄLÄ laita githubiin omaa opiskelijanumeroasi
        String studentNr = "012345678";
        if (args.length > 0) {
            studentNr = args[0];
        }

        String submissionUrl = "https://studies.cs.helsinki.fi/courses/students/" + studentNr + "/submissions";
        String courseUrl = "https://studies.cs.helsinki.fi/courses/courseinfo";

        String submissionBodyText = Request.Get(submissionUrl).execute().returnContent().asString();
        String courseBodyText = Request.Get(courseUrl).execute().returnContent().asString();

        Gson mapper = new Gson();
        JsonParser parser = new JsonParser();
        Submission[] subs = mapper.fromJson(submissionBodyText, Submission[].class);
        Course[] courses = mapper.fromJson(courseBodyText, Course[].class);

        for (Course c : courses) {
            for (Submission s : subs) {
                if (s.getCourse().equals(c.getName())) {
                    c.addSubmission(s);
                }
            }
            String statiscticsUrl = "https://studies.cs.helsinki.fi/courses/" + c.getName() + "/stats";
            String statisticsBodyText = Request.Get(statiscticsUrl).execute().returnContent().asString();
            JsonObject stats = parser.parse(statisticsBodyText).getAsJsonObject();
            c.setStats(stats);
        }

        System.out.println("opiskelijanumero " + studentNr + "\n");

        for (Course c : courses) {
            System.out.println(c);
        }

    }
}

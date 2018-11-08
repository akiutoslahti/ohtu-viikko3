package ohtu;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.IntStream;

public class Course {

    String name;
    String fullName;
    String term;
    int year;
    int[] exercises;
    List<Submission> subs;
    JsonObject stats;

    public Course() {
        subs = new ArrayList<>();
    }

    public void addSubmission(Submission sub) {
        subs.add(sub);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setExercises(int[] exercises) {
        this.exercises = exercises;
    }

    public JsonObject getStats() {
        return stats;
    }

    public void setStats(JsonObject stats) {
        this.stats = stats;
    }

    @Override
    public String toString() {
        String ret = "";
        ret += fullName + " " + term + " " + year + "\n\n";

        int doneExercisesTotal = 0;
        int doneHoursTotal = 0;
        int exercisesTotal = IntStream.of(exercises).sum();

        for (Submission s : subs) {
            String doneExercises = Arrays.toString(s.getExercises());
            doneExercises = doneExercises.substring(1, doneExercises.length() - 1);
            ret += "viikko " + s.getWeek() + ":\n tehtyjä tehtäviä "
                    + s.getExercises().length + "/" + exercises[s.getWeek()]
                    + " aikaa kului " + s.getHours() + " tehdyt tehtävät: "
                    + doneExercises + "\n";

            doneExercisesTotal += s.getExercises().length;
            doneHoursTotal += s.getHours();
        }

        ret += "\nyhteensä: " + doneExercisesTotal + "/" + exercisesTotal
                + " tehtävää " + doneHoursTotal + " tuntia\n";

        int courseTotalSubs = 0;
        int courseTotalEx = 0;
        int courseTotalHours = 0;

        for (Entry<String, JsonElement> e : stats.entrySet()) {
            courseTotalSubs += e.getValue().getAsJsonObject().get("students").getAsInt();
            courseTotalEx += e.getValue().getAsJsonObject().get("exercise_total").getAsInt();
            courseTotalHours += e.getValue().getAsJsonObject().get("hour_total").getAsInt();
        }

        ret += "\nkurssilla yhteensä " + courseTotalSubs 
                + " palautusta, palautettuja tehtäviä yhteensä " 
                + courseTotalEx + " kpl, aikaa käytetty yhteensä "
                + courseTotalHours + " tuntia\n";

        return ret;
    }
}

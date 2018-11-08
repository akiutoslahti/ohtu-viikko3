package ohtu;

public class Submission {

    private int week;
    private int hours;
    private int[] exercises;
    private String course;

    public void setWeek(int week) {
        this.week = week;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public void setExercises(int[] exercises) {
        this.exercises = exercises;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public int getHours() {
        return hours;
    }

    public int[] getExercises() {
        return exercises;
    }

    public String getCourse() {
        return course;
    }

    public int getWeek() {
        return week;
    }

}

import java.util.ArrayList;
import java.util.List;

public class Student {
    private int id;
    private String name;
    private String university;

    private List<Double> grades = new ArrayList<>();
    private static final int MAX_NAME_LENGTH = 30;
    private static final int MAX_UNIVERSITY_LENGTH = 10;

    public Student(int id, String name, String university) {
        this.id = id;
        setName(name);
        setUniversity(university);
        this.grades = grades;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getUniversity() {
        return university;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        if (name.length() > MAX_NAME_LENGTH) {
            throw new IllegalArgumentException("Name exceeds maximum length of " + MAX_NAME_LENGTH + " characters.");
        }
        this.name = name;
    }

    public void setUniversity(String university) {
        if (university.length() > MAX_UNIVERSITY_LENGTH) {
            throw new IllegalArgumentException("University name exceeds maximum length of " + MAX_UNIVERSITY_LENGTH + " characters.");
        }
        this.university = university;
    }

    public List<Double> getGrades() {
        return grades;
    }

    public void setGrades(List<Double> grades) {
        this.grades = grades;
    }

    public double getGpa() {
        double sum = 0;
        for (double grade : grades) {
            sum += grade;
        }
        return Math.floor(sum / grades.size() * 10) / 10;
    }


    @Override
    public String toString() {
        return "id. " + id +
                " name " + name +
                " from university " + university + " note :  " + grades.toString();
    }
}

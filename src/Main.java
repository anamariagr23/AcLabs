import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        try {
//
            // read input from file
            Scanner scanner = new Scanner(new File("C:\\\\Users\\\\Anamaria\\\\IdeaProjects\\\\TakeOff\\\\input.txt"));
            int n = scanner.nextInt();
            Map<Integer, Student> students = new HashMap<>();
            for (int i = 0; i < n; i++) {
                int id = scanner.nextInt();
                String name = scanner.next();
                String university = scanner.next();
                students.put(id, new Student(id, name, university));
            }

            int m = scanner.nextInt();
            for (int i = 0; i < m; i++) {
                double grade = scanner.nextDouble();
                int id = scanner.nextInt();
                students.get(id).getGrades().add(grade);
            }

            //Subtask 1
            subtask1(students);
            System.out.println();
            subtask2(students);

        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

    }
    private static List<String> sortStudentNames(List<Student> students) {
        return students.stream()
                .map(Student::getName)
                .sorted()
                .collect(Collectors.toList());
    }
    

    private static void subtask1(Map<Integer, Student> students) {
        Map<String, List<Student>> studentsByUniversity = getStudentsByUniversity(students);

        Map<String, Double> averageGpaByUniversity = getAverageGpaByUniversity(studentsByUniversity);

        List<String> universitiesByGpa = sortUniversitiesByGpa(averageGpaByUniversity);

        for (String university : universitiesByGpa) {
            List<Student> universityStudents = studentsByUniversity.get(university);
            List<String> sortedNames = sortStudentNames(universityStudents);
            System.out.print(university + ":");
            for (String name : sortedNames) {
                System.out.print(" "+name);
            }
            System.out.println();
        }
    }

    private static void subtask2(Map<Integer,Student> students){
        Map<String, List<Student>> studentsByUniversity = getStudentsByUniversity(students);
        Map<String, Double> averageGpaByUniversity = getAverageGpaByUniversity(studentsByUniversity);
        List<String> universitiesByGpa = sortUniversitiesByGpa(averageGpaByUniversity);
        
        for (String university : universitiesByGpa) {
            List<Student> universityStudents = studentsByUniversity.get(university);
            List<String> topStudents = universityStudents.stream()
                    .sorted(Comparator.comparing(Student::getGpa).reversed().thenComparing(Student::getName))
                    .limit(2)
                    .map(Student::getName)
                    .collect(Collectors.toList());
            System.out.println(university + ": " + String.join(", ", topStudents));
        }
    }

    private static List<String> sortUniversitiesByGpa(Map<String, Double> averageGpaByUniversity) {
        List<String> universitiesByGpa = averageGpaByUniversity.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
        return universitiesByGpa;
    }

    private static Map<String, Double> getAverageGpaByUniversity(Map<String, List<Student>> studentsByUniversity) {
        Map<String, Double> averageGpaByUniversity = new HashMap<>();

        for (Map.Entry<String, List<Student>> entry : studentsByUniversity.entrySet()) {
            List<Double> gpas = entry.getValue()
                    .stream()
                    .map(Student::getGpa)
                    .collect(Collectors.toList());

            double sum = gpas.stream().mapToDouble(Double::doubleValue).sum();
            double averageGpa = Math.floor((sum / gpas.size()) * 10) / 10;
            averageGpaByUniversity.put(entry.getKey(), averageGpa);
        }

        return averageGpaByUniversity;
    }


    private static Map<String, List<Student>> getStudentsByUniversity(Map<Integer, Student> students) {
        Map<String,List<Student>> studentsByUniversity = students.values().stream().collect(Collectors.groupingBy(Student::getUniversity));
        return studentsByUniversity;
    }
    
}
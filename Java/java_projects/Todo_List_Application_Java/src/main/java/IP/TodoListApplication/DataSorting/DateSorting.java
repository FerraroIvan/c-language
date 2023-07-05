package IP.TodoListApplication.DataSorting;

import IP.TodoListApplication.Features.Actions;

import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import IP.TodoListApplication.App.*;

public class DateSorting extends Actions {
    @Override
    public void showActionsInformation(){
        throw new UnsupportedOperationException("The requested operation is not supported.");
    }

    @Override
    public String readUserInput(){
        throw new UnsupportedOperationException("The requested operation is not supported.");
    }

    @Override
    public void executeAction(String command){
        List<Map.Entry<String, Task>> entries = new ArrayList<>(TodoList.tasks.entrySet());
        Collections.sort(entries, new Comparator<Map.Entry<String, Task>>() {
            @Override
            public int compare(Map.Entry<String, Task> task1, Map.Entry<String, Task> task2) {
                LocalDate dueDateFirstTask = task1.getValue().getDueDate();
                LocalDate dueDateSecondTask = task2.getValue().getDueDate();
                int result = dueDateFirstTask.compare(dueDateSecondTask);
                return result;
            }
        });

        TodoList.tasks.clear();
        entries.forEaach((entry) -> {
            TodoList.tasks.put(entry.getKey(), entry.getValue());
        });

        System.out.println("Tasks succesfully Sorted!");
    }

    public static boolean isDateValid(String format, String value){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        String result = null;
        try {
            result = date.format(formatter);
        } catch (DateTimeParseException e) {

        }
        return result;
    }

    public static LocalDate parseDate(String format, String value){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        LocalDate localDate = LocalDate.parse(value, formatter);
        return localDate;
    }
}

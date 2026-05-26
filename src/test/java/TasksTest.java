import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TasksTest {

    @Test
    public void simpleTaskMatches() {
        SimpleTask task = new SimpleTask(1, "Купить хлеб");
        Assertions.assertTrue(task.matches("хлеб"));
        Assertions.assertFalse(task.matches("молоко"));
    }

    @Test
    public void epicMatches() {
        Epic epic = new Epic(1, new String[]{"Молоко", "Яйца"});
        Assertions.assertTrue(epic.matches("Яйца"));
        Assertions.assertFalse(epic.matches("Хлеб"));
    }

    @Test
    public void meetingMatches() {
        Meeting meeting = new Meeting(1, "Тема", "Проект", "Время");
        Assertions.assertTrue(meeting.matches("Тема"));
        Assertions.assertTrue(meeting.matches("Проект"));
        Assertions.assertFalse(meeting.matches("Другое"));
    }
}
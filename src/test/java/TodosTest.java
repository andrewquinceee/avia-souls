import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TodosTest {

    @Test
    public void shouldAddThreeTasksOfDifferentType() {
        // Создаём задачи разных типов
        SimpleTask simpleTask = new SimpleTask(5, "Позвонить родителям");

        String[] subtasks = {"Молоко", "Яйца", "Хлеб"};
        Epic epic = new Epic(55, subtasks);

        Meeting meeting = new Meeting(
                555,
                "Выкатка 3й версии приложения",
                "Приложение НетоБанка",
                "Во вторник после обеда"
        );

        // Добавляем в менеджер
        Todos todos = new Todos();
        todos.add(simpleTask);
        todos.add(epic);
        todos.add(meeting);

        // Ожидаемый и фактический результат
        Task[] expected = {simpleTask, epic, meeting};
        Task[] actual = todos.findAll();

        // ✅ Сравниваем массивы через assertArrayEquals
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldSearchByQueryInSimpleTask() {
        SimpleTask task1 = new SimpleTask(1, "Купить молоко");
        SimpleTask task2 = new SimpleTask(2, "Позвонить маме");

        Todos todos = new Todos();
        todos.add(task1);
        todos.add(task2);

        Task[] result = todos.search("молоко");

        // Проверяем, что нашлась одна задача
        Assertions.assertEquals(1, result.length);
        // Проверяем, что это нужная задача (сравниваем по id)
        Assertions.assertEquals(1, result[0].getId());
    }

    @Test
    public void shouldSearchByQueryInEpic() {
        Epic epic1 = new Epic(1, new String[]{"Хлеб", "Масло"});
        Epic epic2 = new Epic(2, new String[]{"Яйца", "Сыр"});

        Todos todos = new Todos();
        todos.add(epic1);
        todos.add(epic2);

        Task[] result = todos.search("Масло");

        Assertions.assertEquals(1, result.length);
        Assertions.assertEquals(1, result[0].getId());
    }

    @Test
    public void shouldSearchByQueryInMeeting() {
        Meeting meeting1 = new Meeting(1, "План", "Проект А", "Завтра");
        Meeting meeting2 = new Meeting(2, "Отчёт", "Проект Б", "Послезавтра");

        Todos todos = new Todos();
        todos.add(meeting1);
        todos.add(meeting2);

        Task[] result = todos.search("Проект А");

        Assertions.assertEquals(1, result.length);
        Assertions.assertEquals(1, result[0].getId());
    }

    @Test
    public void shouldReturnEmptyArrayWhenNoMatches() {
        SimpleTask task = new SimpleTask(1, "Купить хлеб");

        Todos todos = new Todos();
        todos.add(task);

        Task[] result = todos.search("молоко");

        // Проверяем, что массив пустой
        Assertions.assertEquals(0, result.length);
    }
}
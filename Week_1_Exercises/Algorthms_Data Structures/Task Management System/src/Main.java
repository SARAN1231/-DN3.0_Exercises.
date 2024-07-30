public class Main {
    public static void main(String[] args) {
        TaskLinkedList taskList = new TaskLinkedList();

        Task t1 = new Task("T001", "Design UI", "In Progress");
        Task t2 = new Task("T002", "Develop Backend", "Not Started");
        Task t3 = new Task("T003", "Test Application", "Not Started");

        // Add tasks
        taskList.addTask(t1);
        taskList.addTask(t2);
        taskList.addTask(t3);

        // Traverse and display all tasks
        System.out.println("All Tasks:");
        taskList.traverseTasks();

        // Search for a task by ID
        System.out.println("\nSearch Task T002:");
        System.out.println(taskList.searchTask("T002"));

        // Delete a task by ID
        taskList.deleteTask("T002");
        System.out.println("\nAfter Deleting Task T002:");
        taskList.traverseTasks();
    }
}

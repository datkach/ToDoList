package code.Controller;


public class ApproveDataController extends viewAllTasksController {

    private String query = "SELECT t FROM Task t INNER JOIN Done d ON t = d.task ";

    @Override
    public void loadTasks() {
        super.displayTasks(query);
    }

}

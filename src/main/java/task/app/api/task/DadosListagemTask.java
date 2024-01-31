package task.app.api.task;

public record DadosListagemTask (int id, String title, String description_task, int is_done, int priority) {

    public DadosListagemTask(Task task){
        this(task.getId(), task.getTitle(), task.getDescription_task(), task.getIs_done(), task.getPriority());
    }
    
}

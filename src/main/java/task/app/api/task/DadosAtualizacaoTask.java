package task.app.api.task;

import jakarta.validation.constraints.NotNull;

public record DadosAtualizacaoTask(
    @NotNull 
    int id, 
    String title, 
    String description_task, 
    int is_done,
    @NotNull
    int priority) {
}

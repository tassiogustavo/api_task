package task.app.api.task;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosCadastroTask(
    @NotBlank
    String title,
    @NotBlank 
    String description_task, 
    @NotNull
    int is_done, 
    @NotNull
    int priority) {
} 

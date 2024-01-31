package task.app.api.task;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "tasks")
@Entity(name = "Task")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Task {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String title;
    private String description_task;
    private int is_done;
    private int priority;

    public Task(DadosCadastroTask dados){
        this.title = dados.title();
        this.description_task = dados.description_task();
        this.is_done = dados.is_done();
        this.priority = dados.priority();
    }
    
    public void atualizarInformacoes(DadosAtualizacaoTask dados){
        if(dados.title() != null){
            this.title = dados.title();
        }
        if(dados.description_task() != null){
            this.description_task = dados.description_task();
        }
        this.priority = dados.priority();
    }

    public void atualizaStatusTask(){
        if(this.is_done == 0){
            this.is_done = 1;
        }else{
            this.is_done = 0;
        }
    }
}

package task.app.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import jakarta.validation.Valid;
import task.app.api.task.DadosAtualizacaoTask;
import task.app.api.task.DadosCadastroTask;
import task.app.api.task.DadosDetalhamentoTask;
import task.app.api.task.DadosListagemTask;
import task.app.api.task.Task;
import task.app.api.task.TaskRepository;

@RestController
@RequestMapping("/task")
public class TaskController {

    @Autowired
    private TaskRepository repository;
    
    @PostMapping
    @Transactional
    public ResponseEntity<DadosDetalhamentoTask> cadastrar(@RequestBody @Valid DadosCadastroTask dados, UriComponentsBuilder uBuilder){
        var task = new Task(dados);
        repository.save(task);

        var url = uBuilder.path("/task/{id}").buildAndExpand(task.getId()).toUri();

        return ResponseEntity.created(url).body(new DadosDetalhamentoTask(task));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DadosDetalhamentoTask> detalhar(@PathVariable int id){
        var task = repository.getReferenceById(id);

        return ResponseEntity.ok(new DadosDetalhamentoTask(task));
    }

    // localhost:8080/task?size=1&page=0
    // localhost:8080/task?sort=title,desc
    // @GetMapping
    // public Page<DadosListagemTask> listar(@PageableDefault(size = 10, sort = {"title"}) Pageable paginacao){
    //     return repository.findAll(paginacao).map(DadosListagemTask::new);
    // }

    // @GetMapping
    // public List<Task> listar(){
    //     return repository.findAll();
    // }

    @GetMapping
    public ResponseEntity<List<DadosListagemTask>> listar(){
        var lista = repository.findAll().stream().map(DadosListagemTask::new).toList();
        return ResponseEntity.ok(lista);
    }

    @PutMapping
    @Transactional
    public ResponseEntity<DadosDetalhamentoTask> atualizar(@RequestBody @Valid DadosAtualizacaoTask dados){
        var task = repository.getReferenceById(dados.id());
        task.atualizarInformacoes(dados);

        return ResponseEntity.ok(new DadosDetalhamentoTask(task));
    }
    
    @PutMapping("/status/{id}")
    @Transactional
    public ResponseEntity<String> statusTask(@PathVariable int id){
        var task = repository.getReferenceById(id);
        task.atualizaStatusTask();

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<String> excluir(@PathVariable int id){
        repository.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}

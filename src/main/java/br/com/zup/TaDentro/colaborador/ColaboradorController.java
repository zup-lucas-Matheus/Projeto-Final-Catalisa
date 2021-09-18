package br.com.zup.TaDentro.colaborador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping ("colaborador")
public class ColaboradorController {

    @Autowired
    private ColaboradorService colaboradorService;

    @PostMapping
    @ResponseStatus (HttpStatus.CREATED)
    public Colaborador salvarColaborador (@RequestBody @Valid Colaborador colaborador) {
        // Converter Model em DTO
        return colaboradorService.salvarColaborador(colaborador);
    }

    @GetMapping
    public List <Colaborador> exibirTodosOsColaboradores () {
        // Converter Model para DTO
        return colaboradorService.exibirTodosOsColaboradores();
    }


    @DeleteMapping ("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deltarPeloID (@PathVariable int id) {
        colaboradorService.deletarPorID(id);
    }


/* @PutMapping
   public Colaborador atualizarColaborador () {

        // Não soube fazer
    }*/



}

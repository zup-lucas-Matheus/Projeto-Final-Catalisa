package br.com.zup.TaDentro.Usuario;

import br.com.zup.TaDentro.Usuario.dto.UsuarioDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService service;
    @Autowired
    private ModelMapper modelMapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Usuario cadastroUsuario(@RequestBody @Valid UsuarioDto usuario){

        return service.salvarUsuario(usuario);
    }


    @GetMapping
    public List<Usuario> exibirUsuario(){
        return service.exibirUsuario();
    }


}

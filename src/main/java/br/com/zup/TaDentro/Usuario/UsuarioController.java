package br.com.zup.TaDentro.Usuario;

import br.com.zup.TaDentro.Usuario.dto.UsuarioDto;
import br.com.zup.TaDentro.indicacao.Indicacao;
import br.com.zup.TaDentro.indicacao.dtos.IndicacaoPUTDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private ModelMapper modelMapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UsuarioDto cadastroUsuario(@RequestBody @Valid Usuario usuario) throws Exception {
        Usuario usuarioModel =  usuarioService.salvarUsuario(usuario);
        return modelMapper.map(usuarioModel, UsuarioDto.class);
    }

    @GetMapping
    public List<UsuarioDto> exibirUsuario() {

        List<UsuarioDto> dtos = usuarioService.exibirUsuario()
                .stream()
                .map(user -> modelMapper.map(user, UsuarioDto.class))
                .collect(Collectors.toList());
        return dtos;

    }
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deletarUsuario(@PathVariable int id){
        usuarioService.deletarUsuario(id);
    }

    @PutMapping
    public void atualizarUsuario(UsuarioDto usuario){
        Usuario usuarioModel = modelMapper.map(usuario, Usuario.class);
        usuarioService.atualizarUsuario(usuarioModel);

    }



}

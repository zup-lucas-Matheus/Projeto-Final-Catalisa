package br.com.zup.TaDentro.Usuario;

import br.com.zup.TaDentro.Usuario.dto.UsuarioDto;
import br.com.zup.TaDentro.colaborador.dtos.ColaboradorResumidoDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.lang.reflect.Type;
import java.util.List;

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
        Usuario usuarioModel = usuarioService.salvarUsuario(usuario);
        return modelMapper.map(usuarioModel, UsuarioDto.class);
    }

    @GetMapping
    public List<UsuarioDto> exibirUsuario() {

        return modelMapper.map(usuarioService.exibirUsuario(), (Type) UsuarioDto.class);

    }

}

package br.com.zup.TaDentro.colaborador;

import br.com.zup.TaDentro.Usuario.Usuario;
import br.com.zup.TaDentro.Usuario.UsuarioService;
import br.com.zup.TaDentro.Usuario.exceptionUsuario.MensagemErroUsuario;
import br.com.zup.TaDentro.colaborador.dtos.ColaboradorResumidoDTO;
import br.com.zup.TaDentro.colaborador.exceptionColaborador.MensagemErroColaborador;
import br.com.zup.TaDentro.indicacao.Indicacao;
import br.com.zup.TaDentro.indicacao.IndicacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ColaboradorService {

    @Autowired
    private ColaboradorRepository colaboradorRepository;
    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private IndicacaoRepository indicacaoRepository;


    /**
     *
     * vinculo do Usuário ao Colaborador
     * Para que ele tenha acesso ao sistema
     */
    public Colaborador salvarColaborador(String email,Colaborador colaborador) {
        Usuario usuario = usuarioService.encontrarUsuarioPorEmail(email);
        colaboradorDuplicado(colaborador.getCpf());
        colaborador.setLoginUsuario(usuario);
        return colaboradorRepository.save(colaborador);
    }

    public List<Colaborador> exibirTodosOsColaboradores() {
        return (List<Colaborador>) colaboradorRepository.findAll();
    }


    public Colaborador procurarSeColaboradorJaExiste(int id) {
        Optional<Colaborador> colaboradorOptional = colaboradorRepository.findById(id);

        if (colaboradorOptional.isPresent()) {
            return colaboradorOptional.get();
        }
        else {
            throw new MensagemErroColaborador("Colaborador já existe!");
        }

    }

    public Colaborador buscarColaboradorPorId(int id){
        Optional<Colaborador> colaboradorOptional = colaboradorRepository.findById(id);

        if (colaboradorOptional.isEmpty()) {
            throw new MensagemErroColaborador("Colaborador não encontrado");
        }
        return colaboradorOptional.get();

    }

    public Colaborador buscarColaboradorPorCpf(String cpf){
        Optional<Colaborador> colaboradorOptional = colaboradorRepository.findByCpf(cpf);

        if (colaboradorOptional.isEmpty()) {
            throw new MensagemErroColaborador("Colaborador não encontrado");
        }
        return colaboradorOptional.get();

    }

    //Metódo para buscar colaborador por Usuario
    public Colaborador buscarColaboradorPorUsuario(Usuario usuario){
        Optional<Colaborador> colaboradorOptional = colaboradorRepository.findByLoginUsuario(usuario);

        if (colaboradorOptional.isEmpty()) {
            throw new MensagemErroColaborador("Colaborador não encontrado");
        }
        return colaboradorOptional.get();

    }


    public void atualizarColaborador (Colaborador colaborador) {
        Colaborador objetoColaborador = buscarColaboradorPorCpf(colaborador.getCpf());

        colaborador.setNome(colaborador.getNome());
        colaborador.setId(colaborador.getId());
        colaborador.setCpf(colaborador.getCpf());
        colaborador.setLoginUsuario(colaborador.getLoginUsuario());
        colaborador.setDataContratacao(colaborador.getDataContratacao());

        colaboradorRepository.save(colaborador);

    }

    public void deletarColaborador(int id){
        colaboradorRepository.delete(buscarColaboradorPorId(id));
    }


    public Optional<Colaborador> colaboradorDuplicado(String cpf){
            Optional<Colaborador> colaborador = colaboradorRepository.findByCpf(cpf);

        if (colaborador.isPresent()) {
            throw new MensagemErroColaborador("Colaborador já cadastrado");
        }
        return colaborador;

    }




}















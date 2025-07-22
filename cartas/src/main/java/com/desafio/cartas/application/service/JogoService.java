package com.desafio.cartas.application.service;

import com.desafio.cartas.application.usecases.BaralhoUseCases;
import com.desafio.cartas.application.usecases.JogoUseCases;
import com.desafio.cartas.domain.Jogador;
import com.desafio.cartas.domain.Jogo;
import com.desafio.cartas.domain.JogoRepository;
import com.desafio.cartas.domain.Mao;
import com.desafio.cartas.infrastructure.adapters.in.controller.JogoResponseDto;
import com.desafio.cartas.infrastructure.exceptions.BaralhoClientException;
import com.desafio.cartas.infrastructure.exceptions.ParametrosDeJogoInvalidosException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class JogoService implements JogoUseCases {

    private final JogoRepository jogoRepository;

    private final BaralhoUseCases baralhoUseCases;

    public JogoService(JogoRepository jogoRepository, BaralhoUseCases baralhoUseCases) {
        this.jogoRepository = jogoRepository;
        this.baralhoUseCases = baralhoUseCases;
    }

    @Transactional
    public JogoResponseDto jogar(int qtdJogadores, int qtdCartasPorMao) throws BaralhoClientException, ParametrosDeJogoInvalidosException {
        validarParametrosDoJogo(qtdJogadores, qtdCartasPorMao);
        Jogo jogo = new Jogo(qtdJogadores, qtdCartasPorMao);
        jogo.setMaos(baralhoUseCases.recuperarMaos(qtdJogadores, qtdCartasPorMao));
        apurarVencedores(jogo);
        jogoRepository.salvar(jogo);
        return new JogoResponseDto(jogo.getVencedores().stream().map(Jogador::nome).toList().toString());
    }

    private void apurarVencedores(Jogo jogo) {
        List<Jogador> jogadoresVencedores = new ArrayList<>();

        Optional<Integer> maiorValorOp = jogo.getMaos().stream()
                .map(Mao::getValor)
                .max(Comparator.naturalOrder());

        if (maiorValorOp.isPresent()) {
            jogadoresVencedores = jogo.getMaos().stream()
                    .filter(mao -> mao.getValor() == maiorValorOp.get())
                    .map(Mao::jogador)
                    .toList();
        }
        jogo.setVencedores(jogadoresVencedores);
    }

    void validarParametrosDoJogo(int qtdJogadores, int qtdCartasPorMao) throws ParametrosDeJogoInvalidosException {
        Map<String, String> errors = new HashMap<>();
        if (qtdJogadores < 2 || qtdJogadores > 52)
            errors.put("qtdJogadores", "Quantidade de jogadores (" + qtdJogadores + ") inválida!.");

        if (qtdCartasPorMao < 1 || qtdCartasPorMao > 26)
            errors.put("qtdCartasPorMao", "Quantidade de cartas por jogador (" + qtdCartasPorMao + ") inválida!.");

        if(qtdJogadores*qtdCartasPorMao > 52)
            errors.put("qtdCartasPorMao", "Quantidade de jogadores (" + qtdJogadores + ") e de cartas por mão (" + qtdCartasPorMao + ") ultrapassou a quantidade de cartas do baralho.");

        if(!errors.isEmpty())
            throw new ParametrosDeJogoInvalidosException("Erro de validação nos parâmetros do jogo.", errors);
    }
}

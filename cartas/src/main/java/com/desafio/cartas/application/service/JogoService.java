package com.desafio.cartas.application.service;

import com.desafio.cartas.application.usecases.BaralhoUseCases;
import com.desafio.cartas.application.usecases.JogoUseCases;
import com.desafio.cartas.domain.Jogador;
import com.desafio.cartas.domain.Jogo;
import com.desafio.cartas.domain.JogoRepository;
import com.desafio.cartas.domain.Mao;
import com.desafio.cartas.infrastructure.adapters.in.controller.JogoResponseDto;
import com.desafio.cartas.infrastructure.adapters.out.repository.JogoRepositoryImpl;
import com.desafio.cartas.infrastructure.exceptions.BaralhoClientException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class JogoService implements JogoUseCases {

    private final JogoRepository jogoRepository;

    private final BaralhoUseCases baralhoUseCases;

    public JogoService(JogoRepositoryImpl jogoRepositoryImpl, BaralhoService baralhoService) {
        this.jogoRepository = jogoRepositoryImpl;
        this.baralhoUseCases = baralhoService;
    }

    public JogoResponseDto jogar(int qtdJogadores, int qtdCartasPorMao) throws BaralhoClientException{
        Jogo jogo = new Jogo(qtdJogadores, qtdCartasPorMao);
        recuperarMaosPorJogador(jogo);
        apurarVencedores(jogo);
        this.salvar(jogo);

        return new JogoResponseDto(jogo.getVencedores().stream().map(Jogador::getNome).toList().toString());
    }

    private void apurarVencedores(Jogo jogo) {
        List<Jogador> jogadoresVencedores = new ArrayList<>();

        Optional<Integer> maiorValorOp = jogo.getMaos().stream()
                .map(Mao::getValor)
                .max(Comparator.naturalOrder());

        if (maiorValorOp.isPresent()) {
            jogadoresVencedores = jogo.getMaos().stream()
                    .filter(obj -> obj.getValor() == maiorValorOp.get())
                    .map(Mao::getJogador)
                    .toList();
        }

        jogo.setVencedores(jogadoresVencedores);
    }

    private void recuperarMaosPorJogador(Jogo jogo) throws BaralhoClientException {
        jogo.setMaos(baralhoUseCases.recuperarMaos(jogo));
    }

    void salvar(Jogo jogo) {
        this.jogoRepository.salvar(jogo);
    }

}

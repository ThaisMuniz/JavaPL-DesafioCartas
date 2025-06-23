package com.desafio.cartas.application.service;

import com.desafio.cartas.application.usecases.JogoUseCases;
import com.desafio.cartas.domain.jogo.JogoResponseDTO;
import com.desafio.cartas.model.entity.Carta;
import com.desafio.cartas.model.entity.Jogador;
import com.desafio.cartas.model.entity.Jogo;
import com.desafio.cartas.model.entity.Mao;
import com.desafio.cartas.model.repository.JogoRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class JogoService implements JogoUseCases {

    private final JogoRepository jogoRepository;

    public JogoService(JogoRepository jogoRepository) {
        this.jogoRepository = jogoRepository;
    }

    public JogoResponseDTO jogar(int qtdJogadores, int qtdCartasPorMao) {
        Jogo jogo = new Jogo(qtdJogadores, qtdCartasPorMao);
        jogo.setMaos(recuperarMaosPorJogador(jogo));
        jogo.setVencedores(apurarVencedores(jogo.getMaos()));
        jogo = this.save(jogo);

        return new JogoResponseDTO(jogo.getVencedores());
    }

    private List<Jogador> apurarVencedores(List<Mao> maos) {
        List<Jogador> vencedores = new ArrayList<>();

        //TODO apuração dos vencedores
        vencedores.add(maos.get(1).getJogador());

        return vencedores;
    }

    private List<Mao> recuperarMaosPorJogador(Jogo jogo){
        List<Mao> maos = new ArrayList<>();
        //TODO acessar serviço de baralho

        Mao mao1 = new Mao();
        Jogador jogador1 = new Jogador();
        jogador1.setNome("Maria");
        mao1.setJogador(jogador1);
        mao1.setJogo(jogo);
        Carta carta1 = new Carta();
        carta1.setNaipe("copas");
        carta1.setValor("1");
        Carta carta2 = new Carta();
        carta2.setNaipe("espadas");
        carta2.setValor("A");
        List<Carta> cartas = new ArrayList<>();
        cartas.add(carta1);
        cartas.add(carta2);
        mao1.setCartas(cartas);

        Mao mao2 = new Mao();
        Jogador jogador2 = new Jogador();
        jogador2.setNome("João");
        mao2.setJogador(jogador2);
        mao2.setJogo(jogo);
        Carta carta3 = new Carta();
        carta1.setNaipe("ouros");
        carta1.setValor("8");
        Carta carta4 = new Carta();
        carta2.setNaipe("espadas");
        carta2.setValor("K");
        cartas = new ArrayList<>();
        cartas.add(carta3);
        cartas.add(carta4);
        mao2.setCartas(cartas);

        maos.add(mao1);
        maos.add(mao2);

        return maos;
    }

    Jogo save(Jogo jogo) {
        return this.jogoRepository.save(jogo);
    }

}

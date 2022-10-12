package com.samuel.estudojpa.service;

import com.samuel.estudojpa.model.Funcionario;
import com.samuel.estudojpa.repository.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

@Service
public class RelatoriosService {

    private boolean system = true;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    public void menuInicial(Scanner scanner) {
        while (system) {
            System.out.println("Qual ação de cargo deseja executar?");
            System.out.println("0 - Sair.");
            System.out.println("1 - Pesquisar funcionario pelo nome.");
            System.out.println("2 - Pesquisar funcionario por filtros.");
            System.out.println("3 - Pesquisar funcionario pela data de contratacao.");
            System.out.println("4 - Pesquisar funcionario por parte do nome.");
            System.out.println("5 - Pesquisar funcionario por parte final do nome.");
            System.out.println("6 - Pesquisar funcionario por parte inicial do nome.");
            System.out.println("7 - Pesquisar funcionarios com nome nulo.");
            System.out.println("8 - Pesquisar funcionarios com nome Não nulo.");
            System.out.println("9 - Pesquisar funcionarios por nome e ordenar por data de contratacao.");

            int acao = scanner.nextInt();

            switch (acao) {
                case 1:
                    buscarFuncionarioPeloNome(scanner);
                    break;
                case 2:
                    buscarFuncionarioPorFiltros(scanner);
                    break;
                case 3:
                    buscarFuncionarioPelaDataDeContratacao(scanner);
                    break;
                case 4:
                    buscarFuncionarioPorParteDoNome(scanner);
                    break;
                case 5:
                    buscarFuncionarioQueTerminaCom(scanner);
                    break;
                case 6:
                    buscarFuncionarioQueComecaCom(scanner);
                    break;
                case 7:
                    buscarFuncionarioComNomeNulo();
                    break;
                case 8:
                    buscarFuncionarioComNomeNaoNulo();
                    break;
                case 9:
                    buscarFuncionarioPorNomeEOrdenarPorDataDeContratacao(scanner);
                    break;
                default:
                    system = false;
            }
        }
    }

    public void buscarFuncionarioPeloNome(Scanner scanner) {
        System.out.println("Digite o nome que deseja pesquisar: ");
        String nome = scanner.next();
        List<Funcionario> funcionarios = funcionarioRepository.findByNome(nome);
        funcionarios.forEach(System.out::println);
    }

    public void buscarFuncionarioPorParteDoNome(Scanner scanner){
        System.out.println("Digite parte do nome que deseja pesquisar: ");
        String nome = "%"+scanner.next()+"%";
        List<Funcionario> funcionarios = funcionarioRepository.findByNomeLike(nome);
        funcionarios.forEach(System.out::println);
    }

    public void buscarFuncionarioQueTerminaCom(Scanner scanner){
        System.out.println("Digite parte final do nome que deseja pesquisar: ");
        String nome = scanner.next();
        List<Funcionario> funcionarios = funcionarioRepository.findByNomeEndingWith(nome);
        funcionarios.forEach(System.out::println);
    }

    public void buscarFuncionarioQueComecaCom(Scanner scanner){
        System.out.println("Digite parte inicial do nome que deseja pesquisar: ");
        String nome = scanner.next();
        List<Funcionario> funcionarios = funcionarioRepository.findByNomeStartingWith(nome);
        funcionarios.forEach(System.out::println);
    }

    public void buscarFuncionarioComNomeNulo(){
        List<Funcionario> funcionarios = funcionarioRepository.findByNomeIsNull();
        funcionarios.forEach(System.out::println);
    }

    public void buscarFuncionarioComNomeNaoNulo(){
        List<Funcionario> funcionarios = funcionarioRepository.findByNomeIsNotNull();
        funcionarios.forEach(System.out::println);
    }

    public void buscarFuncionarioPorNomeEOrdenarPorDataDeContratacao(Scanner scanner){
        System.out.println("Digite o nome que deseja pesquisar: ");
        String nome = scanner.next();
        List<Funcionario> funcionarios = funcionarioRepository.findByNomeOrderByDataContratacaoAsc(nome);
        funcionarios.forEach(System.out::println);
    }


    public void buscarFuncionarioPorFiltros(Scanner scanner) {
        System.out.println("Qual o nome do funcionário que deseja pesquisar: ");
        String nome = scanner.next();
        System.out.println("Qual salário deseja pesquisar: ");
        Double salario = scanner.nextDouble();
        System.out.println("Qual a data de contratacao: ");
        String data = scanner.next();
        LocalDate localDate = LocalDate.parse(data, formatter);

        List<Funcionario> funcionarios = funcionarioRepository.findByNomeDataContratacaoSalario(nome, salario, localDate);
        funcionarios.forEach(System.out::println);
    }

    public void buscarFuncionarioPelaDataDeContratacao(Scanner scanner) {
        System.out.println("Qual a data de contratacao: ");
        String data = scanner.next();
        LocalDate localDate = LocalDate.parse(data, formatter);

        List<Funcionario> funcionarios = funcionarioRepository.findDataContratacao(localDate);
        funcionarios.forEach(System.out::println);
    }

}

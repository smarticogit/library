package com.domvs.library.service;

import com.domvs.library.model.Book;
import com.domvs.library.model.User;

import java.util.UUID;

/*
 * Classe responsavel pelo cadastro de livros e usuarios
 * Emprestimo e devolucao de livros
 */
public interface ILibrary {


    /*
     * Cria e retorna um novo livro
     * Nao pode haver duplicacao de titulos, mas uma biblioteca pode ter varios exemplares do mesmo livro
     */

    Book saveBook(Book book);



    /*
     * Cria e retorna um novo usuario
     * Nao pode haver usuarios duplicados
     */
    User saveUser(User user);


    /*
     * Realiza um emprestimo de um livro
     * Nao pode emprestar mais de um exemplar do mesmo livro para um usuario
     * Um usuario nao pode ter mais que 5 livros emprestados
     * Um usuario nao pode emprestar um novo livro se estiver com a data de entrega de um livro atrasada.
     * Deve lancar excessao customizada caso algum pre requisito nao seja atendido
     */
    void toLoan(Long userId, Long bookId);

    /*
     * Realiza a devolucao de um livro
     * Um usuario so pode devolver um livro que ele emprestou
     */
    void giveBack(Long userId, Long bookId);
}
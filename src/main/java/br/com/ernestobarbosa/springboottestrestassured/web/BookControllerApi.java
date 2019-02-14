package br.com.ernestobarbosa.springboottestrestassured.web;

import java.util.List;

import br.com.ernestobarbosa.springboottestrestassured.entity.Book;
import br.com.ernestobarbosa.springboottestrestassured.model.Availability;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

public interface BookControllerApi {

    @ApiOperation(value = "List all books.",
            notes = "Operação para listar todos os livros cadastrados")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Busca realizada com sucesso."),
            @ApiResponse(code = 400, message = "Parâmetro(s) inválido(s).")
    })
    List<Book> listBooks();

    @ApiOperation(value = "Get specific book.",
            notes = "Operação para buscar um único livro")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Busca realizada com sucesso."),
            @ApiResponse(code = 400, message = "Parâmetro(s) inválido(s)."),
            @ApiResponse(code = 404, message = "Livro não encontrado.")
    })
    Book getOneBook(@ApiParam(value = "Book ID for search.", required = true) Long bookId);

    @ApiOperation(value = "Create book.",
            notes = "Operação para criar um livro")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Livro criado."),
            @ApiResponse(code = 400, message = "Parâmetro(s) inválido(s)."),
            @ApiResponse(code = 409, message = "Livro já cadastrado.")
    })
    Book newBook(@ApiParam(value = "Book with name and price.", required = true) Book book);

    @ApiOperation(value = "Update specific book.",
            notes = "Operação para editar os dados de um livro")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Dados atualizados."),
            @ApiResponse(code = 400, message = "Parâmetro(s) inválido(s)."),
            @ApiResponse(code = 404, message = "Livro não encontrado.")
    })
    void updateBook(@ApiParam(value = "Book with id, name and price.", required = true) Book book);

    @ApiOperation(value = "Delete specific book.",
            notes = "Operação para remover um livro")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Operação realizada com sucesso."),
            @ApiResponse(code = 400, message = "Parâmetro(s) inválido(s)."),
            @ApiResponse(code = 404, message = "Livro não encontrado.")
    })
    void deleteOne(@ApiParam(value = "Book ID for delete.", required = true) Long bookId);

    @ApiOperation(value = "Update availability of a book.",
            notes = "Operação para atualizar a disponibilidade de um livro")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Operação realizada com sucesso."),
            @ApiResponse(code = 400, message = "Parâmetro(s) inválido(s)."),
            @ApiResponse(code = 404, message = "Livro não encontrado.")
    })
    void bookAvailability(@ApiParam(value = "Availability with bookId and quantity stock.", required = true) Availability availability);

    @ApiOperation(value = "Get availability of a book.",
            notes = "Operação para verificar a disponibilidade de um livro")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Busca realizada com sucesso."),
            @ApiResponse(code = 400, message = "Parâmetro(s) inválido(s)."),
            @ApiResponse(code = 404, message = "Livro não encontrado.")
    })
    Availability bookAvailability(@ApiParam(value = "Book ID for search.", required = true) Long bookId);

    @ApiOperation(value = "Loan book.",
            notes = "Realiza o empréstimo de um livro")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Operação realizada com sucesso."),
            @ApiResponse(code = 400, message = "Parâmetro(s) inválido(s)."),
            @ApiResponse(code = 401, message = "Livro sem estoque."),
            @ApiResponse(code = 404, message = "Livro não encontrado.")
    })
    void loanBook(@ApiParam(value = "Book ID for loan.", required = true) Long bookId);

    @ApiOperation(value = "Devolution book.",
            notes = "Devolve um livro")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Operação realizada com sucesso."),
            @ApiResponse(code = 400, message = "Parâmetro(s) inválido(s)."),
            @ApiResponse(code = 404, message = "Livro não encontrado.")
    })
    void devolutionBook(@ApiParam(value = "Book ID for devolution.", required = true) Long bookId);

}

package test.dao;

import dao.DAO;
import model.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import java.time.LocalDate;

import static org.junit.Assert.*;

public class LoanDAOTest {
    private BookLocation location;
    private Book book;
    private Loan loan1;
    private LocalDate dateLoan;
    private LocalDate dateDevolution;

    @BeforeEach
    public void setUp() {
        // Configurando objetos para teste
        location = new BookLocation("Estante", "Corredor", "Seção");
        book = new Book("ISBN123", "Título do Livro", "Autor do Livro","Editora", 2023, "Categoria", location, 1);
        dateLoan = LocalDate.now();
        dateDevolution = dateLoan.plusDays(10);
        loan1 = new Loan(7, 23, book, dateLoan, dateDevolution);
    }

    @Test
    public void testAddLoan() {
        LocalDate dateLoan = LocalDate.now(); //diz a data do dia atual ou seja, a data do emprestimo
        LocalDate dateDevolution = dateLoan.plusDays(10); // Calcule a data de devolução (10 dias a partir da data de empréstimo)
        Loan loan = new Loan(9, 23, book, dateLoan, dateDevolution);

        DAO.getLoanDAO().creat(loan);
        assertFalse(DAO.getLoanDAO().findAll().isEmpty()); // verifica se a lista está vazia
    }

    @Test
    public void testFindById() {
        assertNotNull(DAO.getLoanDAO().findById(9)); // verifica se é encontrado um livro pelo isbn
    }

    @Test
    public void testUpdateLoan() {
        // Salvando um empréstimo no DAO
        DAO.getLoanDAO().creat(loan1);

        // Atualizando o empréstimo cujo número de identificação é igual a 7
        Loan loan2 = new Loan(7, 23, book, dateLoan, dateDevolution);
        DAO.getLoanDAO().update(loan2);

        // Pegando o retorno que a busca por ID retorna para fins de comparação
        Loan loanTest = DAO.getLoanDAO().findById(7);

        assertNotEquals(loan1, loanTest); // asserta que o conteúdo dos objetos são diferentes
    }

    @Test
    public void testDeleteLoan() {
        // Salvando um empréstimo no DAO
        DAO.getLoanDAO().creat(loan1);

        // Deletando empréstimo
        DAO.getLoanDAO().delete(loan1);

        for (Loan obj : DAO.getLoanDAO().findAll()) {
            assertNotSame(obj, loan1);
        }
    }

    @Test
    public void testDeleteAll() {
        // Salvando um empréstimo no DAO
        DAO.getLoanDAO().creat(loan1);
        // Deletando toda a lista de empréstimos
        DAO.getLoanDAO().deleteAll();

        assertTrue(DAO.getLoanDAO().findAll().isEmpty());
    }
}
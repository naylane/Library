package test;

import dao.DAO;
import exceptions.BookException;
import exceptions.LoanException;
import model.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.Assert.*;

import java.time.LocalDate;

public class LibrarianTest {
    private Residence address;
    private Librarian librarian;
    private Reader reader;
    private BookLocation location;
    private Book book;

    @BeforeEach
    public void setUp() {
        // Configurando objetos para teste
        address = new Residence("Estado", "Cidade", "Bairro", "Rua", 62, 40000000);
        librarian = new Librarian(1, "Nome do Bibliotecário", "1234", "123-456-7890", address);
        reader = new Reader(2, "Nome do Leitor", "5678", "75 98765-3210", address);
        location = new BookLocation("Estante", "Corredor", "Seção");
        book = new Book("ISBN123", "Título do Livro", "Autor do Livro","Editora", 2023, "Categoria", location, 1);
    }

    @Test
    public void testGenerateId() {

    }

    @Test
    public void testRegisterBook() {
        librarian.registerBook("9788595081512","O Pequeno Príncipe", "Antoine de Saint-Exupéry",
                "HarperCollins", 2018, "Romance", location, 4);

        assertNotNull(DAO.getBookDAO().findById("9788595081512")); // verifica se o pesquisar por id retorna o livro cadastrado
    }

    @Test
    public void testRegisterDuplicateBook() {
        librarian.registerBook("9788595081512","O Pequeno Príncipe", "Antoine de Saint-Exupéry",
                "HarperCollins", 2018, "Romance", location, 1);

        // Registrando livro igual com quantidade diferente
        librarian.registerBook("9788595081512","O Pequeno Príncipe", "Antoine de Saint-Exupéry",
                "HarperCollins", 2018, "Romance", location, 5);

        assertSame(6, DAO.getBookDAO().findById("9788595081512").getQuantityAvailable());
    }

    @Test
    public void testRegisterLoan() throws BookException, LoanException {
        // Garantindo que o livro está disponível
        book.setQuantityAvailable(1);
        // Garantindo de que o leitor não está bloqueado
        reader.block = false;
        // Bibliotecário registra o empréstimo
        librarian.registerLoan(reader, book);

        assertEquals(0, book.getQuantityAvailable()); // verificando que o livro não está mais disponível
    }

    
}

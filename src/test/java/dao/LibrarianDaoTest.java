package dao;

import org.example.dao.DAO;
import org.example.dao.librarian.LibrarianDAO;
import org.example.model.Librarian;
import org.example.model.Residence;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LibrarianDaoTest {
    private LibrarianDAO librarianDAO = DAO.getLibrarianDAO();
    private Librarian lib1;
    private Librarian lib2;
    private Librarian lib3;
    Residence address;

    public LibrarianDaoTest() throws Exception {
    }

    @BeforeEach
    public void setUp() throws Exception {
        address = new Residence("Estado", "Cidade", "Bairro", "Rua", 62, "40000000");
        lib1 = new Librarian("Nome do Bibliotecario 1", "senha123","xx xxxxx-xxxx", address);
        lib2 = new Librarian("Nome do Bibliotecario 2", "senha456","xx xxxxx-xxxx", address);
        lib3 = new Librarian("Nome do Bibliotecario 3", "senha789","xx xxxxx-xxxx", address);}

    @AfterEach
    public void cleanDAO() {
        librarianDAO.deleteAll();
    }

    @Test
    public void testAddLibrarian() throws IOException {
        librarianDAO.create(lib1);
        Librarian libExpected = librarianDAO.findById(lib1.getId());
        assertEquals(lib1, libExpected); // verifica se os objetos são iguais
    }

    @Test
    public void testFindLibrarian() throws IOException {
        librarianDAO.create(lib1);
        assertSame(lib1, librarianDAO.findById(0));
    }

    @Test
    public void testFindAll() throws IOException {
        librarianDAO.create(lib1); // Adicionando bibliotecarios
        librarianDAO.create(lib2);
        librarianDAO.create(lib3);

        assertEquals(3, librarianDAO.findAll().size()); //se add 3 elementos, logo tem que ter 3 elementos na lista
    }

    @Test
    public void testUpdate() throws Exception {
        // Adicionando um adm
        librarianDAO.create(lib1);
        // Criando bibliotecario com informações diferentes ao lib1
        Librarian editedLibrarian = new Librarian("Nome Alterado", "Senha alterada", "xx xxxxx-xxxx", address);
        // Editando
        librarianDAO.update(editedLibrarian);

        assertEquals(lib1, librarianDAO.findById(lib1.getId())); //verifica se realmente as alerações foram feitas
    }

    @Test
    public void testDelete() throws IOException {
        // Adicionando dois bibliotecarios na lista
        librarianDAO.create(lib1);
        librarianDAO.create(lib2);
        int qntBefore = librarianDAO.findAll().size();
        // Removendo um bibliotecario
        librarianDAO.delete(lib1);
        //pegando a quantidade de elementos na lista
        int qntAfter = librarianDAO.findAll().size();

        assertTrue(qntAfter < qntBefore);
    }

    @Test
    public void testDeleteAll() {
        librarianDAO.deleteAll();
        //verificando se a lista está vazia
        assertTrue(librarianDAO.findAll().isEmpty());
    }

}

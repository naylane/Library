package model;

import dao.adm.AdmDAO;
import dao.DAO;
import dao.book.BookDaoImpl;
import dao.librarian.LibrarianDAO;
import dao.reader.ReaderDAO;
import exceptions.UsersException;

//é repsonsavel pela criação dos usuarios e block dos usuarios
public class Adm extends Librarian { //o adm é responsavel pela criação dos user
    private long idReader = 0; // cada tipo de usuario tem um id diferente, e essas variveis são responsaveis por gerar od id automaticamente
    public long idLibrarian = 0;
    public long idAdm = 0;
    BookDaoImpl books = new BookDaoImpl();
    public Adm(long id, String name, String pin, String phone, Residence address) {
        super(id, name, pin, phone, address);
    }

    public long generateId(long id){ // gera automaticamente o id
        return id += 1;
    }

    //CRIAÇÃO DE USERS
    public Reader creatReader(String name, String pin, String phone, Residence address){
        long id = generateId(idReader);
        Reader reader = new Reader(id, name, pin, phone, address);
        //adicionar o reader ao banco de dados - falta fazer o dao reader
        ReaderDAO readerDao = DAO.getReaderDAO();
        readerDao.creat(reader); //criou o book no banco de dados e armazenou no map tendo o seu id como chave
        return reader;
    }

    public Librarian creatLibrariam(String name, String pin, String phone, Residence address){ //bibliotecario não tem id
        long id = generateId(idLibrarian);
        Librarian librarian = new Librarian(id, name, pin, phone, address);
        //adicionar o reader ao banco de dados
        LibrarianDAO librarianDao = DAO.getLibrarianDAO();
        librarianDao.creat(librarian); //criou o book no banco de dados e armazenou no map tendo o seu id como chave
        return librarian;
    }

    public Adm creatAdm(String name, String pin, String phone, Residence address){
        long id = generateId(idAdm);
        Adm adm = new Adm(id, name, pin, phone, address);

        AdmDAO admDao = DAO.getAdmDAO();
        admDao.creat(adm); //criou o book no banco de dados e armazenou no map tendo o seu id como chave

        return adm;
    }

    //OPERAÇÕES DE USERS
    public void blockReader(Reader reader) throws UsersException{
        if(reader.getBlock()){throw new UsersException(UsersException.AlreadyUserBlock);}
        else{reader.blockReader(reader);}}
    public void unlockReader(Reader reader) throws UsersException {
        if(!reader.getBlock()){throw new UsersException(UsersException.AlreadyUserUnlock);}
        else{reader.unlockReader(reader);}}
    public void blockLibrarian(Librarian librarian) throws UsersException {
        if(librarian.getBlock()){throw new UsersException(UsersException.AlreadyUserBlock);}
        else{librarian.blockLibrarian(librarian);}}
    public void unlockLibrarian(Librarian librarian) throws UsersException{
        if(!librarian.getBlock()){throw new UsersException(UsersException.AlreadyUserUnlock);}
        else{librarian.unlockLibrarian(librarian);}}

    //GERENCIAMENTO DO ACERVO - a adição de livros o adm herda do bibliotecario
    public void removeBook(Book book){books.delete(book);}
    public void updateBook(Book book){books.update(book);}
    public void quantityBooks(){books.QuantityBooks();}
}

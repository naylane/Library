package dao.book;

import model.Book;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BookDaoImpl implements BookDAO {
    private final Map<Long, Book> bookMap = new HashMap<>(); //map para gaurdar os livros numa estrutura Isbn:livro
    
    @Override
    public Book creat(Book obj){ //criando um livro e colocando no map
        long id = obj.getISBN(); //o id do livro vai ser o proprio isbn
        bookMap.put(id, obj);
        return obj;
    }

    @Override
    public List<Book> findAll() { //retorna a lista de todos livros em bookmap
        return new ArrayList<>(bookMap.values());}

    @Override
    public Book findById(long id) {  //retorna um livro pelo Id (lembrando q o id é o isbn)
        return bookMap.get(id);}

    @Override
    public Book update(Book obj) {
        bookMap.put(obj.getISBN(), obj);
        return null;}

    @Override
    public void delete(Book obj) {
        long id = obj.getISBN();
        bookMap.remove(id);}

    public void deleteAll(){
        bookMap.clear(); //a função clear vai apagar tudo no bookmap
    }
    //as funções de pesquisas abaixo vão iterar pelo map e criar uma lista de livro de acordo com oq se pesquisa.

    public List<Book> findByTitle(String title) {
        List<Book> result = new ArrayList<>();
        for (Book book : bookMap.values()) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                result.add(book);
            }
        }
        return result;
    }

    public List<Book> findByAuthor(String author) {
        List<Book> result = new ArrayList<>();
        for (Book book : bookMap.values()) {
            if (book.getAuthor().equalsIgnoreCase(author)) {
                result.add(book);
            }
        }
        return result;
    }

    public List<Book> findByCategory(String category) {
        List<Book> result = new ArrayList<>();
        for (Book book : bookMap.values()) {
            if (book.getCategory().equalsIgnoreCase(category)) {
                result.add(book);
            }
        }
        return result;
    }
}
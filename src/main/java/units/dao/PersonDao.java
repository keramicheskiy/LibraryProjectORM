package units.dao;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import units.models.Book;
import units.models.Person;

import java.util.List;

@Component
public class PersonDao {
    private final SessionFactory sessionFactory;

    @Autowired
    public PersonDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Transactional(readOnly = true)
    public List<Person> index() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("select p from Person p", Person.class).getResultList();
    }

    @Transactional(readOnly = true)
    public Person get(int id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Person.class, id);
    }

    @Transactional(readOnly = true)
    public List<Book> getBooks(int id) {
        Session session = sessionFactory.getCurrentSession();
        Person person = session.load(Person.class, id);
        return person.getBooks();
    }

    @Transactional
    public void save(Person person) {
        Session session = sessionFactory.getCurrentSession();
        session.save(person);
    }

    @Transactional
    public void update(int id, Person person) {
        person.setId(id);
        Session session = sessionFactory.getCurrentSession();
        session.update(person);
    }

    @Transactional
    public void delete(int id) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(session.load(Person.class, id));
    }

    @Transactional
    public void addBook(int personId, Book book) {
        Session session = sessionFactory.getCurrentSession();
        Person person = session.load(Person.class, personId);
        List<Book> books = person.getBooks();
        books.add(book);
        person.setBooks(books);
    }



}








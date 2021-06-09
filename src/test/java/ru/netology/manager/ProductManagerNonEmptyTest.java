package ru.netology.manager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.domain.Book;
import ru.netology.domain.Product;
import ru.netology.domain.Smartphone;
import ru.netology.repository.ProductRepository;

import static org.junit.jupiter.api.Assertions.*;

class ProductManagerNonEmptyTest {
    private ProductRepository repository = new ProductRepository();
    private ProductManager manager = new ProductManager(repository);
    private Book first = new Book(1, "Harry Potter", "Joanne Rowling");
    private Book second = new Book(2, "Foundations of Software Testing", "Cem Kaner");
    private Smartphone third = new Smartphone(3, "IPhone 10", "Apple");
    private Smartphone fourth = new Smartphone(4, "Galaxy M31", "Samsung");

    @BeforeEach
    public void setUp() {
        repository.save(first);
        repository.save(second);
        repository.save(third);
        repository.save(fourth);
    }

    @Test
    void shouldGetAll() {
        Product[] actual = repository.findAll();
        Product[] expected = new Product[]{first, second, third, fourth};
        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldSearchNameInBooks() {
        Product[] actual = manager.searchBy("foundations of software testing");
        Product[] expected = new Product[]{second};
        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldSearchAuthorInBooks() {
        Product[] actual = manager.searchBy("Joanne Rowling");
        Product[] expected = new Product[]{first};
        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldSearchNameInSmartphones() {
        Product[] actual = manager.searchBy("ipHONE 10");
        Product[] expected = new Product[]{third};
        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldSearchManufacturerInSmartphones() {
        Product[] actual = manager.searchBy("Samsung");
        Product[] expected = new Product[]{fourth};
        assertArrayEquals(expected, actual);
    }


}
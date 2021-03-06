package com.github.joschi.jadconfig.repositories;

import com.github.joschi.jadconfig.RepositoryException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.fail;

/**
 * Unit tests for {@link InMemoryRepository}
 *
 * @author jschalanda
 */public class InMemoryRepositoryTest {

    private InMemoryRepository repository;

    @Before
    public void setUp() {

        repository = new InMemoryRepository();
    }

    @Test
    public void testOpen() throws RepositoryException {

        repository.open();

        Assert.assertEquals(0, repository.size());
    }

    @Test(expected = IllegalStateException.class)
    public void testCloseWithoutOpen() throws RepositoryException {

        repository.close();
    }

    @Test
    public void testSave() throws RepositoryException {

        repository.save();
    }

    @Test
    public void testClose() throws RepositoryException {

        repository.open();
        Assert.assertEquals(0, repository.size());
        repository.close();

        try {
            repository.size();
            fail("repository.size() should throw an IllegalStateException");
        } catch (IllegalStateException ex) {
            // OK
        }
    }

    @Test(expected = IllegalStateException.class)
    public void testReadWithoutOpen() {

        repository.read("Test");
    }

    @Test
    public void testRead() throws RepositoryException {

        repository.open();

        Assert.assertNull(repository.read("Test"));

        repository.write("Test", "Value");
        Assert.assertEquals("Value", repository.read("Test"));
    }

    @Test(expected = IllegalStateException.class)
    public void testWriteWithoutOpen() throws RepositoryException {

        repository.write("Test", "Value");
    }

    @Test
    public void testWrite() throws RepositoryException {

        repository.open();

        Assert.assertEquals(0, repository.size());
        repository.write("Test", "Value");

        Assert.assertEquals(1, repository.size());
        Assert.assertEquals("Value", repository.read("Test"));
    }

    @Test(expected = IllegalStateException.class)
    public void testSizeWithoutOpen() {

        repository.size();
    }

    @Test
    public void testSize() throws RepositoryException {

        repository.open();

        Assert.assertEquals(0, repository.size());
        repository.write("Test", "Value");

        Assert.assertEquals(1, repository.size());
    }

    @Test
    public void testConstructor() {

        Map<String, String> map = new HashMap<String, String>();
        map.put("one", "one");
        map.put("two", "two");
        map.put("three", "three");

        InMemoryRepository inMemoryRepository = new InMemoryRepository(map);

        Assert.assertEquals("one", inMemoryRepository.read("one"));
        Assert.assertEquals("two", inMemoryRepository.read("two"));
        Assert.assertEquals("three", inMemoryRepository.read("three"));
    }
}
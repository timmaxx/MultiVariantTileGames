package timmax.basepersistent.web.inmemory.user;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import timmax.basepersistent.repository.inmemory.InMemoryUserRepository;
import timmax.basepersistent.util.exception.NotFoundException;
import timmax.basepersistent.web.user.AdminRestController;

import static timmax.basepersistent.UserTestData.NOT_FOUND;
import static timmax.basepersistent.UserTestData.USER_ID;

@ContextConfiguration("classpath:applicationContext.xml")
@RunWith(SpringRunner.class)
public class InMemoryAdminRestControllerSpringTest {

    @Autowired
    private AdminRestController controller;

    @Autowired
    private InMemoryUserRepository repository;

    @Before
    public void setUp() {
        repository.init();
    }

    @Test
    public void delete() {
        controller.delete(USER_ID);
        Assert.assertNull(repository.get(USER_ID));
    }

    @Test
    public void deleteNotFound() {
        Assert.assertThrows(NotFoundException.class, () -> controller.delete(NOT_FOUND));
    }
}
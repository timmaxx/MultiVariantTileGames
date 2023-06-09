package timmax.basepersistent.repository.inmemory;

import org.springframework.stereotype.Repository;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import timmax.basepersistent.model.User;
import timmax.basepersistent.repository.UserRepository;

import static timmax.basepersistent.UserTestData.*;

@Repository
public class InMemoryUserRepository extends InMemoryBaseRepository<User> implements UserRepository {

    public void init() {
        map.clear();
        put(user);
        put(admin);
        put(guest);
        counter.getAndSet(GUEST_ID + 1);
    }

    @Override
    public List<User> getAll() {
        return getCollection().stream()
                .sorted(Comparator.comparing(User::getName).thenComparing(User::getEmail))
                .collect(Collectors.toList());
    }

    @Override
    public User getByEmail(String email) {
        return getCollection().stream()
                .filter(u -> email.equals(u.getEmail()))
                .findFirst()
                .orElse(null);
    }
}
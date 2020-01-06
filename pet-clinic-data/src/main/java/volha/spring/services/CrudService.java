package volha.spring.services;

import java.util.Set;

public interface CrudService<T, ID> {
    Set<T> finfAll();

    T findById(ID id);

    T save(T object);

    void delete(T object);

    void deleteById(ID id);
}

package productmanager.setvice;

import java.util.List;

public interface Service<E> {
    E findById(Long id);
    List<E> findAll();
    void deleteById(Long id);
    void insert(E element);
}

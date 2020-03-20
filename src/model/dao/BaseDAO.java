package model.dao;

import java.util.List;

public interface BaseDAO<R> {

    List<R> getAll();

    R getById(Integer id);

    boolean create(R r);

    R update(Integer id, R r);

    boolean delete(Integer id);
}

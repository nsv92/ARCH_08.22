package ru.geekbrains.orm;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Optional;

public class UserRepository {

    private final Connection conn;

    private final UserMapper mapper;

    private final UnitOfWork unitOfWork;

    private final PreparedStatement startTransaction;

    private final PreparedStatement commitTransaction;

    private final PreparedStatement rollbackTransaction;

    public UserRepository(Connection conn) {
        this.conn = conn;
        this.mapper = new UserMapper(conn);
        this.unitOfWork = new UnitOfWork(mapper);
        try {
            this.startTransaction = conn.prepareStatement("start transaction;");
            this.commitTransaction = conn.prepareStatement("commit;");
            this.rollbackTransaction = conn.prepareStatement("rollback;");
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    public Optional<User> findById(long id) {
        return mapper.findById(id);
    }

    public void beginTransaction() {
        try {
            startTransaction.executeQuery();
            unitOfWork.commit();
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    public void insert(User user) {
        unitOfWork.registerNew(user);
    }

    public void update(User user) {
        unitOfWork.registerUpdate(user);
    }

    public void delete(User user) {
        unitOfWork.registerDelete(user);
    }

    public void commitTransaction() {
        try {
            commitTransaction.executeQuery();
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    public void rollbackTransaction() {
        try {
            rollbackTransaction.executeQuery();
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }
}

package ru.geekbrains.orm;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class UnitOfWork {

    private final UserMapper userMapper;

    private final List<User> newUsers = new ArrayList<>();
    private final List<User> updateUsers = new ArrayList<>();
    private final List<User> deleteUsers = new ArrayList<>();

    public UnitOfWork(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    public void registerNew(User user) {
        this.newUsers.add(user);
    }

    public void registerUpdate(User user) {
        this.updateUsers.add(user);
    }

    public void registerDelete(User user) {
        this.deleteUsers.add(user);
    }

    public void commit() {
        if (!newUsers.isEmpty()) {
            Iterator<User> it = newUsers.iterator();
            while (it.hasNext()) {
                userMapper.insert(it.next());
                it.remove();
            }
        }
        if (!updateUsers.isEmpty()) {
            Iterator<User> it = updateUsers.iterator();
            while (it.hasNext()) {
                userMapper.update(it.next());
                it.remove();
            }
        }
        if (!deleteUsers.isEmpty()) {
            Iterator<User> it = deleteUsers.iterator();
            while (it.hasNext()) {
                userMapper.delete(it.next());
                it.remove();
            }
        }
    }

}


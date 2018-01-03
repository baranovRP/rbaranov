package ru.job4j.generic;

/**
 * @author Roman Baranov (baranov.rp@gmail.com)
 * @version 1
 */
public class RoleStore extends AbstractStore<Role> implements Store<Role> {

    public RoleStore(int size) {
        super(size);
    }

    @Override
    public void add(Role role) {
        super.add(role);
    }

    @Override
    public boolean replace(String id, Role role) {
        return super.replace(id, role);
    }

    @Override
    public boolean delete(String id) {
        return super.delete(id);
    }

    @Override
    public Role findById(String id) {
        return super.findById(id);
    }
}

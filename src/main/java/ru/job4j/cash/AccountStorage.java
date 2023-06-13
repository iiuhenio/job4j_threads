package ru.job4j.cash;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.HashMap;
import java.util.Optional;

@ThreadSafe
public class AccountStorage {
    @GuardedBy("this")
    private final HashMap<Integer, Account> accounts = new HashMap<>();

    public synchronized boolean add(Account account) {
        accounts.put(account.id(), account);
        return true;
    }

    public synchronized boolean update(Account account) {
        accounts.replace(account.id(), account);
        return true;
    }

    public synchronized boolean delete(int id) {
        accounts.remove(id);
        return true;
    }

    public synchronized Optional<Account> getById(int id) {
        return Optional.ofNullable(accounts.get(id));
    }

    public synchronized boolean transfer(int fromId, int toId, int amount) {

        int newValueFrom = accounts.get(fromId).amount() - amount;
        int newValueTo = accounts.get(toId).amount() + amount;
        Account accountFrom = new Account(fromId, newValueFrom);
        Account accountTo = new Account(toId, newValueTo);
        accounts.replace(fromId, accountFrom);
        accounts.replace(toId, accountTo);

        return true;
    }
}
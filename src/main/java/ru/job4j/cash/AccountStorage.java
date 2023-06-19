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
        return accounts.putIfAbsent(account.id(), account) == null;
    }

    public synchronized boolean update(Account account) {
       return accounts.replace(account.id(), account) != null;
    }

    public synchronized boolean delete(int id) {
       return accounts.remove(id) != null;
    }

    public synchronized Optional<Account> getById(int id) {
        return Optional.ofNullable(accounts.get(id));
    }

    public synchronized boolean transfer(int fromId, int toId, int amount) {
        boolean rsl = false;
        Optional<Account> from = getById(fromId);
        Optional<Account> to = getById(toId);
        if (from.isPresent() && to.isPresent() && accounts.get(fromId).amount() >= amount) {
            int newValueFrom = accounts.get(fromId).amount() - amount;
            int newValueTo = accounts.get(toId).amount() + amount;
            Account accountFrom = new Account(fromId, newValueFrom);
            Account accountTo = new Account(toId, newValueTo);
            accounts.replace(fromId, accountFrom);
            accounts.replace(toId, accountTo);
            rsl = true;
        }
        return rsl;
    }
}
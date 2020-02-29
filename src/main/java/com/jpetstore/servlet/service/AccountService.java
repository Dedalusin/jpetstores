package com.jpetstore.servlet.service;

import com.jpetstore.servlet.domain.Account;
import com.jpetstore.servlet.persistance.impl.AccountDAOimpl;


public class AccountService {


  private AccountDAOimpl accountDAOimpl;

  public Account getAccount(String username) {
    return accountDAOimpl.getAccountByUsername(username);
  }
  public boolean usernameIsExist(String username){accountDAOimpl=new AccountDAOimpl();return accountDAOimpl.getAccountByUsername(username)!=null;}
  public Account getAccount(String username, String password) {
    accountDAOimpl=new AccountDAOimpl();
    Account account = new Account();
    account.setUsername(username);
    account.setPassword(password);
    return accountDAOimpl.getAccountByUsernameAndPassword(account);
  }


  public void insertAccount(Account account) {
    accountDAOimpl=new AccountDAOimpl();
    accountDAOimpl.insertAccount(account);
    accountDAOimpl.insertProfile(account);
    accountDAOimpl.insertSignon(account);
  }


  public void updateAccount(Account account) {
    AccountDAOimpl accountDAOimpl = new AccountDAOimpl();
    accountDAOimpl.updateAccount(account);
    accountDAOimpl.updateProfile(account);

    if (account.getPassword() != null && account.getPassword().length() > 0) {
      accountDAOimpl.updateSignon(account);
    }
  }

}

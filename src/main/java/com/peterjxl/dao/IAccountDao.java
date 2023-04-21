package com.peterjxl.dao;

import com.peterjxl.domain.Account;
import com.peterjxl.domain.AccountUser;

import java.util.List;

public interface IAccountDao {

    /**
     * 查询所有账户
     * @return
     */
    List<Account> findAll();

    /**
     * 查询所有账户，并带有用户名称和地址信息
     */
    List<AccountUser> findAccountUser();
}

package com.example.aopdemo.dao;

import com.example.aopdemo.Account;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class AccountDAOImpl implements AccountDAO{
    String name;
    String serviceCode;



    @Override
    public void addAccount(Account theAccount,boolean VipFlag) {
        System.out.println(getClass()+" : Doing my DB work: Adding an account");
    }

    @Override
    public boolean doWork() {
        System.out.println(getClass()+" : dowork()");
        return false;
    }
    public String getName() {
        System.out.println(getClass()+" : IngetName()");
        return name;
    }

    public void setName(String name) {
        System.out.println(getClass()+" : InsetName");
        this.name = name;
    }

    public String getServiceCode() {
        System.out.println(getClass()+" : IngetServiceCode()");
        return serviceCode;
    }

    public void setServiceCode(String serviceCode) {
        System.out.println(getClass()+" : InSetServiceCode()");
        this.serviceCode = serviceCode;
    }

    @Override
    public List<Account> findAccounts(boolean flag) {

        if(flag == true){
            throw new RuntimeException("No soup for you");
        }
        List<Account>list = new ArrayList<>();
        //create Account object
        Account temp1 = new Account("John","silver");
        Account temp2 = new Account("Madhu","Platinum");
        Account temp3 = new Account("Luca","Gold");

        //Add this to account list
        list.add(temp1);
        list.add(temp2);
        list.add(temp3);
        return list;
    }

    @Override
    public List<Account> findAccounts() {
       return findAccounts(false);
    }

}

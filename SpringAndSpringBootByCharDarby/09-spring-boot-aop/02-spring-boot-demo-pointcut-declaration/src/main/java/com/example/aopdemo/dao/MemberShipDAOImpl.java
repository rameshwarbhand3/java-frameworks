package com.example.aopdemo.dao;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
public class MemberShipDAOImpl implements MemberShipDAO{
    @Override
    public boolean addSillyMember() {
        System.out.println(getClass()+" : Doing my DB work: Adding an MemberShip account");
        return true;
    }



    @Override
    public void goToSleep() {
        System.out.println(getClass()+" : I am going to sleep");
    }
}

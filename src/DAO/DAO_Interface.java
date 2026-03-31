package DAO;

import java.util.List;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Mahasiswa
 */
public interface DAO_Interface<A> {
    public void insert(A Object);
    public void update(A Object);
    public void delete(String key);
    public List<A> getAll();
    public List<A> getCari(String key);
}

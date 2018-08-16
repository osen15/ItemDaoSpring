package com.service;


import com.dao.ItemDAO;
import com.entity.Item;
import org.springframework.beans.factory.annotation.Autowired;

public class ItemService {
    @Autowired
    ItemDAO itemDAO;

    public Item findItem(Long id) {
        return itemDAO.findById(id);
    }

    public void saveItem(Item item) {
        itemDAO.save(item);
    }

    public void deleteItem(Long id) throws Exception {
        itemDAO.delete(id);
    }

    public void updateItem(Item item) throws Exception {
        itemDAO.update(item);
    }


}



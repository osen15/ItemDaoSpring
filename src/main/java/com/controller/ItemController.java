package com.controller;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.entity.Item;

import org.springframework.beans.factory.annotation.Autowired;
import com.service.ItemService;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.persistence.NoResultException;
import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Date;


@Controller
public class ItemController {
    @Autowired
    Item item;
    @Autowired
    ItemService itemService;

    @RequestMapping(method = RequestMethod.GET, value = "/Item")
    @ResponseBody
    String getItem(@RequestParam("id") String ID) {

        try {

            return (itemService.findItem(Long.parseLong(ID)).toString());
        } catch (NoResultException e) {
            e.printStackTrace();
            return ("Item with id: " + ID + " not found");

        } catch (NumberFormatException e) {
            e.printStackTrace();
            return "Bad request";
        }


    }

    @RequestMapping(method = RequestMethod.POST, value = "/saveItem", produces = "text/plain")
    @ResponseBody
    String doPost(HttpServletRequest req) {


        try {
            item = jsonToEntity(req);
            itemService.saveItem(item);
            return ("Item with id: " + item.getId() + " is saved");
        } catch (Exception e) {
            e.printStackTrace();
            return ("Error saving " + e.getMessage());

        }

    }


    @RequestMapping(method = RequestMethod.PUT, value = "/updateItem", produces = "text/plain")
    @ResponseBody
    String doPut(HttpServletRequest req) {

        try {
            item = jsonToEntity(req);
            item.setLastUpdatedDate(new Date());
            itemService.updateItem(jsonToEntity(req));
            return ("Item with id: " + item.getId() + " is updated");

        } catch (Exception e) {
            e.printStackTrace();
            return ("Error updating " + e.getMessage());

        }
    }


    @RequestMapping(method = RequestMethod.DELETE, value = "/deleteItem", produces = "text/plain")
    @ResponseBody
    String doDelete(@RequestParam("id") String ID) throws Exception {

        try {

            itemService.deleteItem(Long.parseLong(ID));
            return ("item with id: " + ID + " deleted");
        } catch (NoResultException e) {
            e.printStackTrace();
            return ("Item with id: " + ID + " not found");

        } catch (NumberFormatException e) {
            e.printStackTrace();
            return "Bad request";
        }


    }

    private Item jsonToEntity(HttpServletRequest req) {

        StringBuilder stb = new StringBuilder();
        Gson gson = new GsonBuilder().setDateFormat("dd-MM-yy").create();
        String line;
        try (BufferedReader reader = req.getReader()) {


            while ((line = reader.readLine()) != null)
                stb.append(line);


        } catch (IOException e) {
            e.printStackTrace();
        }

        return gson.fromJson(stb.toString(), Item.class);
    }
}
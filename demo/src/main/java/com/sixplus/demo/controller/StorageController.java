package com.sixplus.demo.controller;

import com.sixplus.demo.entity.Storage;
import com.sixplus.demo.repository.StorageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = {"*","null"})
@RestController
public class StorageController {
    @Autowired
    private StorageRepository storageRepository;

    @GetMapping(value = "/storages")
    public List<Storage> getStorages(){
        return storageRepository.findAll();
    }

    @GetMapping(value = "/storages/{id}")
    public Storage getStoragesById(@PathVariable(value = "id") Integer id)
    {
        return storageRepository.getOne(id);
    }

    @PutMapping(value = "add/storages/{id}")
    public Storage editStorages(@PathVariable(value = "id") Integer id,
                             @RequestParam(value = "url") String url,
                             @RequestParam(value = "name") String name,
                             @RequestParam(value = "ISBN") String isbn,
                             @RequestParam(value = "price") Double price,
                             @RequestParam(value = "stores") Integer stores,
                             @RequestParam(value = "descript") String descript,
                             @RequestParam(value = "subject") String subject)
    {
        Storage book;
        if(storageRepository.existsById(id)){
            book = storageRepository.getOne(id);
        }
        else{
            book = new Storage();
        }
        book.setName(name);
        book.setUrl(url);
        book.setISBN(isbn);
        book.setPrice(price);
        book.setStores(stores);
        book.setDescript(descript);
        book.setSubject(subject);
        return storageRepository.save(book);
    }

//    @PutMapping(value = "/storages/{id}")
//    public Storage modifyStores(@PathVariable(value = "id") Integer id,
//                                  @RequestParam(value = "stores") Integer stores)
//    {
//        Storage book = storageRepository.getOne(id);
//        book.setStores(stores);
//        return storageRepository.save(book);
//    }

    @DeleteMapping(value = "delete/storages/{id}")
    public void deleteStorages(@PathVariable(value = "id") Integer id)
    {
        storageRepository.deleteById(id);
    }
}

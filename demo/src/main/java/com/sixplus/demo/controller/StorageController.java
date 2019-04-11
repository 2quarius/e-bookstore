package com.sixplus.demo.controller;

import com.sixplus.demo.bean.WebResponse;
import com.sixplus.demo.entity.Storage;
import com.sixplus.demo.repository.StorageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = {"http://localhost:8081","null"},allowCredentials = "true",methods = {RequestMethod.GET, RequestMethod.PUT,RequestMethod.DELETE})
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

    @PutMapping(value = "add/storages/")
    public Storage editStorages()
    {
        Storage book = new Storage();
        book.setName("书籍名称");
        book.setISBN("ISBN编号");
        book.setDescript("商品描述");
        return storageRepository.save(book);
    }

    @PutMapping(value = "set/storages/{id}")
    public WebResponse modifyStores(@PathVariable(value = "id") Integer id,
                                    @RequestParam(value = "name") String name,
                                    @RequestParam(value = "descript") String descript,
                                    @RequestParam(value = "price") Double price,
                                    @RequestParam(value = "stores") Integer stores,
                                    @RequestParam(value = "url",required = false) String url)
    {
        Storage book = storageRepository.getOne(id);
        book.setName(name);
        book.setDescript(descript);
        book.setPrice(price);
        book.setStores(stores);
        storageRepository.save(book);
        return WebResponse.success("修改成功");
    }

    @DeleteMapping(value = "delete/storages/{id}")
    public WebResponse deleteStorages(@PathVariable(value = "id") Integer id)
    {
        storageRepository.deleteById(id);
        return WebResponse.success("删除成功");
    }
}

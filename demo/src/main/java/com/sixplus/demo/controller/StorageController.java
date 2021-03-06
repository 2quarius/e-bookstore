package com.sixplus.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.sixplus.demo.bean.WebResponse;
import com.sixplus.demo.entity.Storage;
import com.sixplus.demo.repository.StorageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = {"http://localhost:8081","null"},allowCredentials = "true",methods = {RequestMethod.GET, RequestMethod.POST,RequestMethod.PUT,RequestMethod.DELETE})
@RestController
public class StorageController {
    @Autowired
    private StorageRepository storageRepository;

    @GetMapping(value = "/storages")
    public List<Storage> getStorages(){
        return storageRepository.findAll();
    }
    @PostMapping(value = "/storages/{number}")
    public WebResponse getSpecificStorage(@PathVariable(value = "number") Integer number,@RequestParam Integer index){
        List<Storage> candidates = storageRepository.findAll();
        List<Storage> specific = new ArrayList<>();
        for (int i = index; i< index+number; i++){
            if (candidates.size()>i){
                specific.add(candidates.get(i));
            }
            else break;
        }
        JSONObject result = new JSONObject();
        result.put("data",specific);
        result.put("entries",candidates.size());
        return WebResponse.success(result);
    }

    @GetMapping(value = "/storages/{id}")
    public Storage getStoragesById(@PathVariable(value = "id") Integer id)
    {
        return storageRepository.getOne(id);
    }

    @PostMapping(value = "add/storages/")
    public Storage editStorages(@RequestBody JSONObject info)
    {
        Storage book = new Storage();
        book.setUrl("good0.jpg");
        book.setName(info.getString("name"));
        book.setISBN(info.getString("isbn"));
        book.setPrice(info.getDouble("price"));
        book.setStores(info.getInteger("stores"));
        book.setSubject(info.getString("subject"));
        book.setDescript(info.getString("descript"));
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

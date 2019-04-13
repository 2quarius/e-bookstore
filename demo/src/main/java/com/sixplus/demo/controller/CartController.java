package com.sixplus.demo.controller;

import com.sixplus.demo.bean.CartResponse;
import com.sixplus.demo.bean.WebResponse;
import com.sixplus.demo.entity.Cart;
import com.sixplus.demo.entity.Storage;
import com.sixplus.demo.repository.StorageRepository;
import com.sixplus.demo.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@CrossOrigin(origins = {"http://localhost:8081","null"},allowCredentials = "true", methods = {RequestMethod.GET, RequestMethod.POST,RequestMethod.DELETE})
@RestController
public class CartController {
    @Autowired
    private RedisService redisService;

    @Autowired
    private StorageRepository storageRepository;

    @GetMapping(value = "/cart/{username}")//获取购物车
    public List<CartResponse> getCart(@PathVariable(value = "username") String username)
    {
        if (redisService.exists(username)) {
            List<Cart> carts = redisService.get(username);
            return getListCartResponse(carts);
        }
        else return null;
    }

    private List<CartResponse> getListCartResponse(List<Cart> carts) {
        List<CartResponse> responses = new ArrayList<>();
        for (Cart c:carts)
        {
            CartResponse response = new CartResponse();
            Storage storage = storageRepository.getOne(c.getGoodid());
            response.setId(storage.getId());
            response.setUrl(storage.getUrl());
            response.setName(storage.getName());
            response.setPrice(storage.getPrice());
            response.setNumber(c.getNumber());
            responses.add(response);
        }
        return responses;
    }

    @PostMapping(value = "/cart/{username}")//加入购物车
    public List<CartResponse> saveCart(@PathVariable(value = "username") String username,
                                             @RequestBody Cart cart)
    {
        if (redisService.exists(username))
        {
            List<Cart> carts = redisService.get(username);
            carts.add(cart);
            redisService.getAndSet(username,carts);
            return getListCartResponse(carts);
        }
        List<Cart> carts= new ArrayList<>();
        carts.add(cart);
        redisService.set(username,carts);
        return getListCartResponse(carts);
    }

    @DeleteMapping(value = "/cart/{username}")//删除购物车中某一类物品
    public WebResponse deleteCart(@PathVariable(value = "username") String username,
                                  @RequestParam(value = "goodid") String goodid)
    {
        if(redisService.exists(username))
        {
            List<Cart> carts = redisService.get(username);
            for (Cart c:carts)
            {
                if (c.getGoodid()==Integer.valueOf(goodid))
                {
                    carts.remove(c);
                }
            }
            redisService.getAndSet(username,carts);
            return WebResponse.success("成功删除"+goodid+"号商品");
        }
        return WebResponse.success("该用户不存在购物车");
    }
}

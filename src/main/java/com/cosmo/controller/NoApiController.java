package com.cosmo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.cosmo.handler.UdpServerHandler;
import com.cosmo.model.No;
import com.cosmo.repository.redis.RedisRepository;

@RestController
public class NoApiController {

    private static final Logger LOGGER = LoggerFactory.getLogger(NoApiController.class);

    @Autowired
    private RedisRepository redisRepository;

    @GetMapping(value = "/")
    public ModelAndView page() {
        return new ModelAndView("page");
    }

    @GetMapping(value = "no")
    public No get() {
        if (UdpServerHandler.LIST_NO.isEmpty()) {
            return null;
        }
        return UdpServerHandler.LIST_NO.poll();
    }

}

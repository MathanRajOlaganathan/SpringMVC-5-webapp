package com.test.hplus.controller;

import com.test.hplus.beans.Product;
import com.test.hplus.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.AsyncTaskExecutor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

@Controller
public class SearchController {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private AsyncTaskExecutor taskExecutor;

    @GetMapping("/search")
    public DeferredResult<String> search(@RequestParam("search") String search, Model model, HttpServletRequest request) {
        DeferredResult<String> deferredResult = new DeferredResult<>();
        System.out.println("Inside Search Controller");
        System.out.println("Search Criteria: " + search);
        System.out.println("Async Supported in application: " + request.isAsyncSupported());
        System.out.println("Thread from the servlet container" + Thread.currentThread().getName());

        taskExecutor.execute(() -> {

            try {
                Thread.sleep(6000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Thread from AsyncTaskExecutor: " + Thread.currentThread().getName());
            List<Product> productList = productRepository.searchByName(search);
            model.addAttribute("products", productList);
            deferredResult.setResult("search");
        });
    return deferredResult;
    }

//    @GetMapping("/search")
//    public Callable<String> search(@RequestParam("search") String search, Model model, HttpServletRequest request)
//    {
//        System.out.println("Inside Search Controller");
//        System.out.println("Search Criteria: "+search);
//        System.out.println("Async Supported in application: "+request.isAsyncSupported());
//        System.out.println("Thread from the servlet container"+Thread.currentThread().getName());
//
//        return ()->{
//
//            Thread.sleep(5000);
//            System.out.println("Thread from AsyncTaskExecutor: "+Thread.currentThread().getName());
//            List<Product> productList = productRepository.searchByName(search);
//            model.addAttribute("products",productList);
//            return "search";
//        };
//
//    }
}

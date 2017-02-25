package com.charlie.business;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.charlie.dao.CustomerDao;
import com.charlie.entity.Customer;


@Controller
public class TestController {
	@Autowired
	CustomerDao cust;
    @RequestMapping("/test")
    public String greeting(@RequestParam(value="name", required=false, defaultValue="World") String name, Model model) {
        model.addAttribute("name", name);
         List<Customer>list  =  cust.findByLastName("Bauer");
         if (!list.isEmpty()) {
        	 System.out.println(list.get(0));
		}
         
        return "tt";
    }
    @RequestMapping("/save")
    public String save(@RequestParam(value="name", required=false, defaultValue="World") String name, Model model) {
        model.addAttribute("name", name);
        Customer cus = new Customer("first","last");
        cust.save(cus );
        List<Customer>list  =  cust.findByLastName("last");
        if (!list.isEmpty()) {
       	 System.out.println(list.get(0));
		}
        cust.delete(cus);
       
        List<Customer>list2  =  cust.findByLastName("last");
        if (!list2.isEmpty()) {
       	 System.out.println(list.get(0));
		}
        else {
			System.err.println("deleted");
		}
        return "tt";
    }

   
}

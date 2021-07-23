package com.unt.controller;

import com.unt.model.CheckIn;
import com.unt.service.CheckInService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/checkin")
@CrossOrigin()
public class CheckInController {

    @Autowired
    private CheckInService checkInService;

    // request book, add check in date + 15 to the check out date
    @RequestMapping(value = "/request", method = RequestMethod.POST)
    public String checkIn(@RequestBody Map<String, List<Integer>> booksList) {
        System.out.println(booksList);
        System.out.println(booksList.get("bookslist"));
        System.out.println(booksList.get("userId").get(0));
        checkInService.saveRequest(booksList.get("userId").get(0), booksList.get("bookslist"));
        return "Books Checked In";
    }

    //request approval get
    @RequestMapping(value = "/requestApproval", method = RequestMethod.GET)
    public List<CheckIn> getRequestApproval() {
        return checkInService.getRequestApproval();
    }

    //request approval post
    @RequestMapping(value = "/requestApproval", method = RequestMethod.POST)
    public void approve(@RequestBody Map<String, List<Integer>> booksList) {
        checkInService.approve(booksList.get("requestApprovalList"));
    }


    //all
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public List<CheckIn> getAll() {
        return checkInService.getAll();
    }

    @RequestMapping(value = "/all/{userId}", method = RequestMethod.GET)
    public List<CheckIn> getAllUser(@PathVariable("userId") Integer userId) {
        return checkInService.getAllUser(userId);
    }

    @RequestMapping(value = "/return", method = RequestMethod.POST)
    public String returnBooks(@RequestBody Map<String, List<Integer>> returnbooks) {
        System.out.println(returnbooks);
        System.out.println(returnbooks.get("returnbooksList"));
        checkInService.returnBooks(returnbooks.get("returnbooksList"));
        return "Books Returned";
    }


}

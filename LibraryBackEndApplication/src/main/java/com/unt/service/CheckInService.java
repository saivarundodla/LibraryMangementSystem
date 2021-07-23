package com.unt.service;

import com.unt.model.Book;
import com.unt.model.CheckIn;
import com.unt.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.unt.repository.CheckInRepository;

import java.util.*;

@Service
public class CheckInService {

    @Autowired
	private CheckInRepository checkInRepository;
    @Autowired
    private BookService bookService;
    @Autowired
    private UserService userService;

    public void saveRequest(Integer userid, List<Integer> booksList) {
        Calendar calendar = Calendar.getInstance();
        Date check_in_date = calendar.getTime();
        calendar.setTime(check_in_date);
        calendar.add(Calendar.DATE, 15);
        Date check_out_date =calendar.getTime();
        for (int bookId: booksList) {
            System.out.println(bookId);
            Book book = bookService.getBook(bookId);
            User user = userService.getUser(userid);
            CheckIn checkin = new CheckIn();
            checkin.setBook(book);
            checkin.setUser(user);
            checkin.setCheckInDate(check_in_date);
            checkin.setCheckOutDate(check_out_date);
            checkin.setReturnedDate(null);
            checkin.setRequestApproval(false);
            checkin.setPenalty(0F);
            System.out.println(checkin);
            checkInRepository.save(checkin);
        }
    }

    public void returnBooks(List<Integer> returnbooksList) {
        Calendar calendar = Calendar.getInstance();
        Date returnDate = calendar.getTime();
        for (int checkId: returnbooksList) {
            CheckIn checkin = getCheckInId(checkId);
            checkin.setReturnedDate(returnDate);
            System.out.println("Check in "+checkin);
            checkInRepository.save(checkin);
        }
    }

    public List<CheckIn> getRequestApproval() {
        List<CheckIn> allApprovals = new ArrayList<CheckIn>();
        checkInRepository.getRequestApproval().forEach(allApprovals::add);
        return allApprovals;
    }

    public List<CheckIn> getAll() {
        List<CheckIn> allcheckIn = new ArrayList<CheckIn>();
        checkInRepository.findAll().forEach(allcheckIn::add);
        return allcheckIn;
    }

    public List<CheckIn> getAllUser(Integer userId) {
        List<CheckIn> allcheckIn = new ArrayList<CheckIn>();
        checkInRepository.getAllUser(userId).forEach(allcheckIn::add);
        return allcheckIn;
    }

    public CheckIn getCheckInId(Integer checkId) {
        return checkInRepository.getCheckInId(checkId);
    }

    public CheckIn getCheckInWithBookId(Integer bookId) {
        return checkInRepository.getCheckInWithBookId(bookId);
    }

    public void approve(List<Integer> booksList) {
        for(int checkinid:booksList){
            System.out.println(checkinid);
            CheckIn checkin = getCheckInId(checkinid);
            System.out.println("Checkin "+checkin);
            checkin.setRequestApproval(Boolean.TRUE);
            checkInRepository.save(checkin);
        }
    }
}

package com.abc.springsession.web.controller;

import com.abc.springsession.domain.Employee;
import com.abc.springsession.exception.MyError;
import com.abc.springsession.exception.LoginError;
import com.abc.springsession.exception.Message;
import com.abc.springsession.exception.ResourceNotFoundError;
import com.abc.springsession.login.EmpLogin;
import com.abc.springsession.web.service.MailService;
import com.abc.springsession.web.repository.EmpRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import static com.abc.springsession.utils.EmployeeUtil.getDateTime;

@RestController
@RequestMapping(path = "/api/v1")
public class EmployeeController {
    private final EmpRepository repository;
    private final MailService mailService;

    @Autowired
    public EmployeeController(EmpRepository repository, MailService mailService) {
        this.repository = repository;
        this.mailService = mailService;
    }

    @PostMapping()
    public Employee saveEmployee(@Valid @RequestBody Employee employee, HttpServletRequest request) {
        String admin = (String) request.getSession().getAttribute(Message.SESSION_KEY.getValue());

        if (admin != null) {
            return repository.save(employee);
        }
        throw LoginError.builder()
                .message(Message.UNAUTHORIZED_ERROR.getValue()).build();
    }

    @GetMapping(path = "/{id}")
    public Employee getEmployee(@PathVariable Integer id, HttpServletRequest request) {
        String admin = (String) request.getSession().getAttribute(Message.SESSION_KEY.getValue());
        if (admin != null) {
            return repository.findById(id)
                    .orElseThrow(
                            ResourceNotFoundError.builder()
                                    .message(Message.RESOURCE_NOT_FOUND.getValue())
                                    .build()
                    );
        }
        throw LoginError.builder()
                .message(Message.UNAUTHORIZED_ERROR.getValue()).build();
    }

    @GetMapping()
    public Iterable<Employee> getEmployee(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String admin = (String) session.getAttribute(Message.SESSION_KEY.getValue());

        if (admin != null) {
            return repository.findAll();
        }
        throw LoginError.builder()
                .message(Message.UNAUTHORIZED_ERROR.getValue())
                .build();
    }

    @PostMapping(path = "/logout")
    public String logout(HttpServletRequest request) {
        request.getSession().invalidate();
        return Message.LOGOUT.getValue();
    }

    @PostMapping(path = "/login")
    public String login(HttpServletRequest request, @RequestBody EmpLogin empLogin) {
        Employee employee = repository.findByEmailAndPassword(empLogin.getEmail(), empLogin.getPassword());
        if (employee != null) {
            request.getSession().setAttribute(Message.SESSION_KEY.getValue(), employee.getFirstName());
            try {
                mailService.sendEmail(empLogin.getEmail(), false);
                return Message.SUCCESS.getValue();
            } catch (Exception e) {
                e.printStackTrace();
            }

            throw MyError.builder()
                    .message(Message.EMAIL_NOT_SENT.getValue())
                    .timestamp(getDateTime()).build();
        }
        throw LoginError.builder()
                .message(Message.INVALID_LOGIN_CREDS.getValue())
                .build();
    }

    @PostMapping(path = "/mail")
    public String sendMail() {
        try {
            mailService.sendEmail("alhagiebcham.ee@gmail.com", true);
            return Message.EMAIL_SENT.getValue();
        } catch (Exception e) {
            e.printStackTrace();
        }
        throw MyError.builder()
                .message(Message.EMAIL_NOT_SENT.getValue())
                .timestamp(getDateTime()).build();
    }
}

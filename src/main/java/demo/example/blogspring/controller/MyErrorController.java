package demo.example.blogspring.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

@Controller
public class MyErrorController implements ErrorController {

  @RequestMapping("/error")
  public String handleError(HttpServletRequest request, Model model) {
    Object status=request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
    if(status!=null){
      Integer statusCode=Integer.valueOf(status.toString());
      if(statusCode == HttpStatus.NOT_FOUND.value()){
        model.addAttribute("statusCode",statusCode);
        model.addAttribute("messages","Your Page Not Found.");
      }
      if(statusCode==500){
        model.addAttribute("statusCode",statusCode);
        model.addAttribute("messages","Server Error.");
      }
    }
    return "error";
  }

  @Override
  public String getErrorPath() {
    return "/error";
  }
}

package com.libraryPro.config;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class LoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {


    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws ServletException, IOException {


        System.out.println(authentication.getPrincipal());

        String redirectURL = request.getContextPath();

        UsersUserDetails usersDetails = (UsersUserDetails) authentication.getPrincipal();
        System.out.println("url" + usersDetails);


//        if(usersDetails.hasRole("ROLE_STUDENT")){
//        redirectURL = "/books/list";
//
//        response.sendRedirect(redirectURL);}


        if(usersDetails.hasRole("ROLE_ADMIN")){
            redirectURL = "/admin/books/list";
           System.out.println("Admin");
            response.sendRedirect(redirectURL);}

   else{
            int UserId = usersDetails.getUsers().getId();
            System.out.println("userId is: "+ UserId);
            String paramAppend = "?param1="+Integer.toString(UserId);
            redirectURL = "/books/list"+paramAppend;
            System.out.println("redirectURL is: "+ redirectURL);
            response.sendRedirect(redirectURL);}

}

    /*public String redirectStudentPage(HttpServletRequest request, int userId) {
        request.setAttribute("userId", userId);
        return "/books/list";
    }*/
}



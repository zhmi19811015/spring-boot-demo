/**
 * @Project spring-boot-demo
 * @Package com.ming.controller
 * @Description: TODO
 * @author zhangming
 * @date 2019/2/28 15:38
 * @version V1.0
 */
package com.ming.springbootfastdfs.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@ControllerAdvice
public class GlobalExceptionHandler {
    //https://jira.spring.io/browse/SPR-14651
    //4.3.5 supports RedirectAttributes redirectAttributes
    @ExceptionHandler(MultipartException.class)
    public String handleError1(MultipartException e, RedirectAttributes redirectAttributes) {

        redirectAttributes.addFlashAttribute("message", e.getCause().getMessage());
        return "redirect:/uploadStatus";

    }
}

package com.ming.springbootmail.service;

/**
 * 类作用描述
 *
 * @author zhangming
 * @version 1.0
 * @date 2019/5/31 2:12 PM
 */
public interface MailService {
     void sendSimpleMail(String to,String subject,String content);

     void sendHtmlMail(String to,String subject,String content);

     /**
      * 发送带附件的邮件
      *
      * @param to 1
      * @param subject 2
      * @param content 3
      * @param filePath 4
      * @return  void
      * @author  zhangming
      * @date  2019/5/31 2:27 PM
      */
     void sendAttachmentsMail(String to, String subject, String content, String filePath);

     /**
      * 发送带静态资源的邮件
      *
      * @param to 1
      * @param subject 2
      * @param content 3
      * @param rscPath 4
      * @param rscId 5
      * @return  void
      * @author  zhangming
      * @date  2019/5/31 2:14 PM
      */
     void sendInlineResourceMail(String to, String subject, String content, String rscPath, String rscId);
}

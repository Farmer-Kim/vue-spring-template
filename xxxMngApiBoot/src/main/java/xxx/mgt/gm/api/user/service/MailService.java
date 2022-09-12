package xxx.mgt.gm.api.user.service;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;


@Service
public class MailService {
	
	@Autowired
	private JavaMailSender mailSender;
	
	/**
	 * 회원 아이디를 이메일로 보내는 메소드
	 * @param username
	 * @param nickname
	 * @param userEmail
	 */
	public void mailSendWithId(String username, String nickname, String userEmail) {
		
		MimeMessage mail = mailSender.createMimeMessage();
	      String htmlStr = "<h2>안녕하세요 IoMT 플랫폼 지원 포털입니다.</h2><br><br>" + "<span>"+nickname+"님의 아이디는 " + username + "입니다.</span>";
	    		  
	      try {
	         mail.setSubject("[아이디 찾기] 요청하신 회원님의 아이디입니다", "utf-8");
	         mail.setText(htmlStr, "utf-8", "html");
	         mail.addRecipient(RecipientType.TO, new InternetAddress(userEmail));
	         mailSender.send(mail);

	      } catch (MessagingException e) {
	         e.printStackTrace();
	      }
	}
	
	public void mailSendWithNewPw(String randomNum, String nickname, String userEmail) {
		
		MimeMessage mail = mailSender.createMimeMessage();
	      String htmlStr = "<h2>안녕하세요 IoMT 플랫폼 지원 포털입니다.</h2><br><br>" + "<span>"+nickname+"님의 임시비밀번호는 " + randomNum + "입니다.</span><br><br>"
	      		+ "<span>로그인 후에 반드시 비밀번호를 변경해주세요.</span>";
	    		  
	      try {
	         mail.setSubject("[비밀번호 찾기] 임시 비밀번호입니다.", "utf-8");
	         mail.setText(htmlStr, "utf-8", "html");
	         mail.addRecipient(RecipientType.TO, new InternetAddress(userEmail));
	         mailSender.send(mail);

	      } catch (MessagingException e) {
	         e.printStackTrace();
	      }
	}
}

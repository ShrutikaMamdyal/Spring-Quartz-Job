package springmvc.controller;



import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.logging.log4j.Logger;

import org.apache.logging.log4j.LogManager;

import javax.mail.MessagingException;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMultipart;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import springmvc.service.MailService;
import springmvc.vo.MailDataVO;
import com.google.gson.Gson;


@Controller
@RequestMapping("/mail")
public class MailController {
	
	
	
	@Autowired
	private MailService classservice;
	
	private static final Logger logger = LogManager.getLogger(MailController.class);
	
	 private Gson gson = new Gson();
	
	
	@RequestMapping("/home")
	public String home() throws MessagingException, IOException {
		System.out.println("Inside Home Controller");
		
		List<String> to=new ArrayList<String>();
		to.add("shrutika.mamdyal@coreintegra.com");
		to.add("abhay.bhave@coreintegra.com");
		
		List<String> cc=new ArrayList<String>();
		cc.add("salman.shaikh@coreintegra.com");
		cc.add("sandhya.kamurti@coreintegra.com");
		
		MimeMultipart mimeMultipart = new MimeMultipart();
		
		MimeBodyPart textMime = new MimeBodyPart();
		
		MimeBodyPart fileMime = new MimeBodyPart();
		
		//Text
		textMime.setText("Hi Team ,\n\tThis is demo mail. Please find Attchment. \nRegards,\nCtrlf-Demo");
		
		//File
		File file=new File("C:\\Users\\Shrutika.Mamdyal\\Documents\\Salman\\Ctrl-F File.xlsx");
		fileMime.attachFile(file);
		
		//Set to MimeMultiPart
		mimeMultipart.addBodyPart(textMime);
		mimeMultipart.addBodyPart(fileMime);
		System.out.println("Sending Mail.....");
		
		//classservice.sendMail(to, cc, "Audit Sprint Mail" , mimeMultipart);
		//classservice.sendMail("shrutikamamdyal456@gmail.com","shrutika.mamdyal@coreintegra.com","Demo Mail","Hello , This Msg Send Again");
		return "homepage";
	}
	
	@RequestMapping("/triggerMail")
	public String triggerMail(Model model) throws MessagingException {
		System.out.println("Inside Mail Controller");
		classservice.sendMail("shrutikamamdyal456@gmail.com","shrutika.mamdyal@coreintegra.com","Demo Mail","Hello , This Msg Send Again");
		return "homepage";
	}
	

	@RequestMapping(path = "/getTableData", method = RequestMethod.GET)
	public void getTableData(HttpServletRequest request, HttpServletResponse response,Model m) throws IOException {
		System.out.println("Inside getTableData()");
		MailDataVO obj= new MailDataVO();
		obj.setMessage("Mail Triggered");
		obj.setTriggeredOn(new Date());
		 List<String> userJsonString = new ArrayList<String>();
		 userJsonString.add( this.gson.toJson(obj));
		 obj.setMessage("Mail Triggered 2");
		 obj.setTriggeredOn(new Date());
		 userJsonString.add( this.gson.toJson(obj));
		 PrintWriter out = response.getWriter();
	     response.setContentType("application/json");
	     response.setCharacterEncoding("UTF-8");
	     out.print(userJsonString);
	     out.flush();
	}
}

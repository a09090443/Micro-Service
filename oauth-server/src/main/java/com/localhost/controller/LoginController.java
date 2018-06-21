package com.localhost.controller;

import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.security.Principal;
import java.util.Date;
import java.util.Map;

@Controller
public class LoginController {
	@RequestMapping("/login")
	public String login() {
		return "login";
	}

	// sso服务端退出登录
	@RequestMapping("/signout")
	public String signout(HttpServletRequest request) throws Exception {
		request.logout();
		return "tologin";
	}

	@RequestMapping("/")
	public String home() {
		return "index";
	}

//	@RequestMapping(value = "/images/imagecode")
//	public String imagecode(HttpServletRequest request, HttpServletResponse response) throws Exception {
//		OutputStream os = response.getOutputStream();
//		Map<String, Object> map = ImageCode.getImageCode(60, 20, os);
//
//		String simpleCaptcha = "simpleCaptcha";
//		request.getSession().setAttribute(simpleCaptcha, map.get("strEnsure").toString().toLowerCase());
//		request.getSession().setAttribute("codeTime", new Date().getTime());
//
//		try {
//			ImageIO.write((BufferedImage) map.get("image"), "JPEG", os);
//		} catch (IOException e) {
//			return "";
//		}
//		return null;
//	}

	@RequestMapping(value = "/checkcode")
	@ResponseBody
	public String checkcode(HttpServletRequest request, HttpSession session) throws Exception {
		String checkCode = request.getParameter("checkCode");
		Object simple = session.getAttribute("simpleCaptcha");
		if (simple == null) {
			request.setAttribute("errorMsg", "verify code is not available, please enter again!");
			return "verify code is not available, please enter again!";
		}

		String captcha = simple.toString();
		Date now = new Date();
		Long codeTime = Long.valueOf(session.getAttribute("codeTime") + "");
		if (StringUtils.isEmpty(checkCode) || captcha == null || !(checkCode.equalsIgnoreCase(captcha))) {
			request.setAttribute("errorMsg", "verify code is wrong！");
			return "verify code is wrong！";
		} else if ((now.getTime() - codeTime) / 1000 / 60 > 5) {// 验证码有效长度为5分钟
			request.setAttribute("errorMsg", "verify code is not available, please enter again!");
			return "verify code is not available, please enter again!";
		} else {
			session.removeAttribute("simpleCaptcha");
			return "1";
		}
	}
}
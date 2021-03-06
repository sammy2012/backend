package org.bupt.travel.controller;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import javax.jws.soap.SOAPBinding.Use;
import javax.servlet.http.HttpServletRequest;

import org.bupt.travel.common.BizMsg;
import org.bupt.travel.common.Const;
import org.bupt.travel.common.FunctionUtility;
import org.bupt.travel.model.User;
import org.bupt.travel.service.BlogService;
import org.bupt.travel.service.UserService;
import org.bupt.travel.vo.BlogDigestItem;
import org.bupt.travel.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.mchange.v2.log.FallbackMLog;

@Controller
@RequestMapping("/blog")
public class BlogController {
	@Autowired
	private BlogService blogService;

	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	@ResponseBody
	public Object getAllBlogList(HttpServletRequest request) {
		String UName = request.getParameter("uid");
		String offset = request.getParameter("offset");
		
		
		
		BizMsg<BlogDigestItem> msg = new BizMsg<BlogDigestItem>();
		try {
			if(FunctionUtility.checkString(UName) == false) {
				msg = blogService.getRecentBlogsAllUser(offset);
			}
			else {
				msg = blogService.getRecentBlogsByUserId(offset, UName);
			}
			
		} catch (Exception e) {
			msg.setCode(Const.STATUS_BIZ_ERROR);
			msg.setMsg("系统错误，请稍后重试");
		}
		
		return msg;
	}
	
	@RequestMapping(value = "/newblog", method = RequestMethod.POST)
	@ResponseBody
	public Object addNewBlogSummary(HttpServletRequest request, @RequestParam(value="surface", required=false) MultipartFile surface) {
	//public Object addNewBlogSummary(HttpServletRequest request) {
		String title = request.getParameter("title");
		String visitTime = request.getParameter("visitTime");
		String location = request.getParameter("location");
		String duration = request.getParameter("duration");
		String userid = request.getParameter("userid");
		String username = request.getParameter("username");
		String desc = request.getParameter("desc");

		BizMsg<String> msg = blogService.addNewBlogSummary(title, visitTime, location, duration, userid, username, desc, surface);
		
		
		return msg;
	}
	
	@RequestMapping(value = "/footprint", method = RequestMethod.POST)
	@ResponseBody
	public Object addNewFootPrint(HttpServletRequest request, @RequestParam(value="photo", required=false) MultipartFile photo) {
	//public Object addNewBlogSummary(HttpServletRequest request) {
		String userId = request.getParameter("userId");
		String bId = request.getParameter("bId");
		String desc = request.getParameter("desc");
		String location = request.getParameter("location");
		String lon = request.getParameter("lon");
		String lat = request.getParameter("lat");
		String visitTime = request.getParameter("visitTime");

		BizMsg<String> msg = blogService.addNewBlogFootPrint(userId, bId, desc, location, lon, lat, visitTime, photo);
		
		
		return msg;
	}
	
	/*@RequestMapping(value = "/login", method = RequestMethod.POST)
	@ResponseBody
	public Object userLogin(HttpServletRequest request) {
		String UName = request.getParameter("UName");
		String pwd = request.getParameter("pwd");
		
		BizMsg<UserVo> msg = new BizMsg<UserVo>();
		try {
			msg = userService.login(UName, pwd);
		} catch (Exception e) {
			msg.setCode(Const.STATUS_BIZ_ERROR);
			msg.setMsg("系统错误，请稍后重试");
		}
		
		return msg;
	}
	
	@RequestMapping(value = "/info", method = RequestMethod.GET)
	public @ResponseBody Object UploadUserInfor(HttpServletRequest request) {
		String uid = request.getParameter("uID");
		
		BizMsg<UserVo> msg = userService.getuserInfo(uid);
		
		return msg;
	}
	
	@RequestMapping(value = "/avatar", method = RequestMethod.POST)
	public @ResponseBody Object UploadUserAvatar(HttpServletRequest request, @RequestParam("file") MultipartFile avatar) {
		String uid = request.getParameter("uID");
		
		HashMap<String, String> retJsonHashMap = new HashMap<String, String>();
		
		if(!avatar.isEmpty()) {
			String filename = uid.concat(avatar.getOriginalFilename().substring(avatar.getOriginalFilename().lastIndexOf(".")));
			String avatarPath = Const.avatarPath.concat(filename);
			
			try {
				avatar.transferTo(new File(avatarPath));
			} catch (IllegalStateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			BizMsg<String> msg = userService.updateUserAvatarInfo(uid, filename);
				
			return msg;
		}
		else {
			BizMsg<String> msg = new BizMsg<String>();
			msg.setCode(Const.STATUS_BIZ_ERROR);
			msg.setMsg("缺少头像文件名，上传失败");
			return msg;
		}

	}*/
}

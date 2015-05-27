package com.artportal.controller;

import java.util.List;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.artportal.domain.ArtWork;
import com.artportal.domain.Comment;
import com.artportal.domain.User;
import com.artportal.domain.Voice;
import com.artportal.service.interfaces.IArtTypeService;
import com.artportal.service.interfaces.IArtWorkService;
import com.artportal.service.interfaces.ICompetitionService;
import com.artportal.service.interfaces.IUserService;

@Controller
public class WorkController {

	@Autowired
	@Qualifier("userService")
	private IUserService userService;
	@Autowired
	@Qualifier("artWorkService")
	private IArtWorkService artWorkService;
	@Autowired
	@Qualifier("artTypeService")
	private IArtTypeService artTypeService;
	@Autowired
	@Qualifier("competitionService")
	private ICompetitionService competitionService;
	
	@Autowired
	Comment comment;
	@Autowired
	Voice voice;
	@Autowired
	ArtWork work;
	
	
	private int pageSize = 6;
	private int pageNumber = 1;
	private Long pageCount;


	// loads modelAttribute object for artwork view
	@RequestMapping(value = "workInfo/{workName}", method = RequestMethod.GET)
	public String showWorkInfo(@PathVariable String workName,
			HttpSession session, Model model) {
		model.addAttribute("work", artWorkService.getWorkByName(workName));
		session.setAttribute("workName", workName);
		boolean voitedUser = isUserVoited(userService.findUserByLogin((String) session.getAttribute("login")),artWorkService.getWorkByName(workName));
		model.addAttribute("voitedUser", voitedUser);
		//need to check user is comment able
		if(session.getAttribute("login")!=null){
			User user = userService.findUserByLogin(session.getAttribute("login").toString());
			session.setAttribute("user", user);
		}
		return "artwork";
	}
	
	private boolean isUserVoited(User user, ArtWork work) {
		for(Voice voice : work.getVoices()){
			if(user!=null&&user.equals(voice.getUser())){
				return true;
			}
		}
		return false;
		
	}

	@RequestMapping(value = "workInfo/submitaddcomment", method = RequestMethod.POST)
	public String addComment(Model model, HttpSession session, 
			@RequestParam("commentText") String commentText) {
		ArtWork work = artWorkService.getWorkByName(session.getAttribute("workName").toString());
		User user = userService.findUserByLogin(session.getAttribute("login").toString());
		if(user.getCommentAble()){
			//Comment comment = new Comment(commentText,work,user);//
			comment.setCommentText(commentText);
			comment.setWork(work);
			comment.setUser(user);
			//check double sending
			if(work.getComments().size()==0||!comment.equals(work.getComments().get(work.getComments().size()-1))){
				artWorkService.addComment(comment);
				work.getComments().add(comment);
			}
		}
		model.addAttribute("work",work);
		boolean voitedUser = isUserVoited(user,work);
		model.addAttribute("voitedUser", voitedUser);
		return "artwork";
	}
	
	@RequestMapping(value = "addLike", method = RequestMethod.GET)
	public String addLike(Model model, HttpSession session) {
		ArtWork work = artWorkService.getWorkByName(session.getAttribute("workName").toString());
		User user = userService.findUserByLogin(session.getAttribute("login").toString());
		//Voice voice = new Voice(work,user);//
		voice.setWork(work);
		voice.setUser(user);
		artWorkService.addVoice(voice);
		work.getVoices().add(voice);
		model.addAttribute("work",work);
		boolean voitedUser = isUserVoited(user,work);
		model.addAttribute("voitedUser", voitedUser);
		return "redirect:/workInfo/"+work.getName();
	}

	@RequestMapping(value = { "/artworks" }, method = RequestMethod.GET)
	public String artWorks(Model model) {
		List<ArtWork> works = artWorkService.getApprovedWorksOnPage(pageNumber,
				pageSize);
		pageCount = artWorkService.getPagesCountForApproved(pageSize);
		model.addAttribute("artWorks", works);
		model.addAttribute("pageCount", pageCount);
		model.addAttribute("pageNumber", pageNumber);
		return "artworks";
	}

	@RequestMapping(value = { "/account/loadWork" }, method = RequestMethod.GET)
	public String loadWorkForm(Model model, HttpSession session) {
		//model.addAttribute("work", new ArtWork());//
		model.addAttribute("work", work);
		model.addAttribute("artTypes", artTypeService.getAllArtTypes());
		model.addAttribute("competitions", competitionService.getAllCompetitions());
		return "accountloadwork";
	}

	@RequestMapping(value = "account/submitLoadWork", method = RequestMethod.POST)
	public String loadWork(
			Model model,
			HttpServletRequest request,
			HttpSession session,
			@RequestParam(value = "workName", required = true) String workName,
			@RequestParam(value = "workTypeName", required = false) String workTypeName,
			@RequestParam(value = "competitionName", required = false) String competitionName,
			@RequestParam(value = "image", required = false) MultipartFile image) {
		model.addAttribute("artTypes", artTypeService.getAllArtTypes());
		model.addAttribute("competitions", competitionService.getAllCompetitions());
		String fileName = image.getOriginalFilename();
		//ArtWork work = new ArtWork();
		work.setPath(fileName);
		work.setName(workName);
		work.setType(artTypeService.getArtTypeByName(workTypeName));
		if (competitionName != null && !competitionName.isEmpty()) {
			work.setCompetition(competitionService
					.getCompetitionByName(competitionName));
		}
		model.addAttribute("work", work);
		if(!checkUnique(workName,model)){
			return "accountloadwork";
		}
		if (workName == null || workName.equals("") || Pattern.matches("[\\s]+",workName)) {
			model.addAttribute("errNameMsg", "Name can't be empty!");
			return "accountloadwork";
		}
		if (workName.length() > 30) {
			model.addAttribute("errNameMsg", "Not more than 30 character!");
			return "accountloadwork";
		}
		if (workTypeName == null || workTypeName.isEmpty()) {
			model.addAttribute("errTypeMsg", "Choose type of Art!");
			return "accountloadwork";
		}
		if (!image.getContentType().equals("image/jpeg") || image.isEmpty()) {
			model.addAttribute("loadMsg", "You need choose file in jpg format!");
			return "accountloadwork";
		}
		User user = (User) session.getAttribute("user");
		work.setUser(user);
		work.setApproved(true);
		// save work in file system an in db
		artWorkService.saveNewArtWork(work, fileName, image);
		// update user in model
		List<ArtWork> userWorks = artWorkService.getWorksByUser(user);
		user.setWorks(userWorks);
		//1 userService.updateUser(user);
		session.setAttribute("user", user);
		model.addAttribute("user", user);
		model.addAttribute("workLoadMsg", "Your work was loaded successfully!");
		session.setAttribute("workName", "");
		return "accountworks";
	}
	
	public boolean checkUnique(String artWorkName, Model model) {
		if (artWorkService.getWorkByName(artWorkName) == null) {
			return true;
		} else {
			model.addAttribute("errNameMsg", "This name is already used ");
		}
		return false;
	}
	
	//----- paging for works -----
	
		@RequestMapping(value = { "/artworks/next", "/next" }, method = RequestMethod.GET)
		public String artWorksNextPage(Model model) {
			pageNumber++;
			updatePage(model);
			return "redirect:/artworks";
		}

		@RequestMapping(value = { "/artworks/prev", "/prev" }, method = RequestMethod.GET)
		public String artWorksPrevPage(Model model) {
			pageNumber--;
			updatePage(model);
			return "redirect:/artworks";
		}

		@RequestMapping(value = { "/artworks/first", "/first" }, method = RequestMethod.GET)
		public String artWorksFirstPage(Model model) {
			pageNumber = 1;
			updatePage(model);
			return "redirect:/artworks";
		}

		@RequestMapping(value = { "/artworks/last", "/last" }, method = RequestMethod.GET)
		public String artWorksLastPage(Model model) {
			pageCount = artWorkService.getPagesCountForApproved(pageSize);
			pageNumber = pageCount.intValue();
			updatePage(model);
			return "redirect:/artworks";
		}
		
		public void updatePage(Model model){
			List<ArtWork> works = artWorkService.getApprovedWorksOnPage(pageNumber,
					pageSize);
			pageCount = artWorkService.getPagesCountForApproved(pageSize);
			model.addAttribute("artWorks", works);
			model.addAttribute("pageCount", pageCount);
			model.addAttribute("pageNumber", pageNumber);
		}
	
}

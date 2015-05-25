package com.artportal.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.artportal.domain.ArtWork;
import com.artportal.domain.Competition;
import com.artportal.service.interfaces.IArtWorkService;
import com.artportal.service.interfaces.ICompetitionService;

@Controller
public class CompetitionController {
	
	@Autowired
	@Qualifier("competitionService")
	private ICompetitionService competitionService;
	@Autowired
	@Qualifier("artWorkService")
	private IArtWorkService artWorkService;
	
	private int pageNumberCW = 1;
	private Long pageCountCW;
	private int pageSize = 6;
	
	 @RequestMapping(value = "/competitions", method = RequestMethod.GET)
	 public String competitions(Model model) {
		 List<Competition> competitions = competitionService.getAllCompetitions();
		 model.addAttribute("compList", competitions);
		 return "competitions";
	 }
	 
	 @RequestMapping(value = "/compworks", method = RequestMethod.POST)
	 public String competitionWorks(Model model, HttpSession session, @RequestParam("compId") Long compId) {
		Competition competition = competitionService.getCompetitionById(compId);
		session.setAttribute("comp", competition);
		model.addAttribute("comp", competition);
		List<ArtWork> works = artWorkService.getCompWorksOnPage(pageNumberCW, pageSize, competition);
		pageCountCW = artWorkService.getPagesCountForCompWorks(pageSize, competition);
		model.addAttribute("artWorks", works);
		model.addAttribute("pageCount", pageCountCW);
		model.addAttribute("pageNumber", pageNumberCW);
		session.setAttribute("compId", competition.getId());
		return "compworks"; 
	 }
	 
	 @RequestMapping(value = "/compworks", method = RequestMethod.GET)
	 public String competitionWorksGet(Model model, HttpSession session) {
		 Long compId = (Long) session.getAttribute("compId");
		 Competition competition = competitionService.getCompetitionById(compId);
		 session.setAttribute("comp", competition);
		 model.addAttribute("comp", competition);
		 List<ArtWork> works = artWorkService.getCompWorksOnPage(pageNumberCW, pageSize, competition);
		 pageCountCW = artWorkService.getPagesCountForCompWorks(pageSize, competition);
		 model.addAttribute("artWorks", works);
		 model.addAttribute("pageCount", pageCountCW);
		 model.addAttribute("pageNumber", pageNumberCW);
		 return "compworks"; 
	 }
	  
		//----- paging for competition works-----
		
		@RequestMapping(value = { "/compworks/nextcw", "/nextcw" }, method = RequestMethod.GET)
		public String artWorksCompNextPage(Model model, HttpSession session) {
			pageNumberCW++;
			List<ArtWork> works = artWorkService.getCompWorksOnPage(pageNumberCW, pageSize,
					(Competition) session.getAttribute("comp"));
			pageCountCW = artWorkService.getPagesCountForCompWorks(pageSize, (Competition) session.getAttribute("comp"));
			model.addAttribute("artWorks", works);
			model.addAttribute("pageCount", pageCountCW);
			model.addAttribute("pageNumber", pageNumberCW);
			 System.out.println("********** comp id= " +session.getAttribute("compId"));//**************
			return "redirect:/compworks";
		}

		@RequestMapping(value = { "/compworks/prevcw", "/prevcw" }, method = RequestMethod.GET)
		public String artWorksCompPrevPage(Model model, HttpSession session) {
			pageNumberCW--;
			List<ArtWork> works = artWorkService.getCompWorksOnPage(pageNumberCW, pageSize,
					(Competition) session.getAttribute("comp"));
			pageCountCW = artWorkService.getPagesCountForCompWorks(pageSize,(Competition) session.getAttribute("comp"));
			model.addAttribute("artWorks", works);
			model.addAttribute("pageCount", pageCountCW);
			model.addAttribute("pageNumber", pageNumberCW);
			return "redirect:/compworks";
		}

		@RequestMapping(value = { "/compworks/firstcw", "/firstcw" }, method = RequestMethod.GET)
		public String artWorksCompFirstPage(Model model, HttpSession session) {
			pageNumberCW = 1;
			List<ArtWork> works = artWorkService.getCompWorksOnPage(pageNumberCW, pageSize, 
					(Competition) session.getAttribute("comp"));
			pageCountCW = artWorkService.getPagesCountForCompWorks(pageSize,(Competition) session.getAttribute("comp"));
			model.addAttribute("artWorks", works);
			model.addAttribute("pageCount", pageCountCW);
			model.addAttribute("pageNumber", pageNumberCW);
			return "redirect:/compworks";
		}

		@RequestMapping(value = { "/compworks/lastcw", "/lastcw" }, method = RequestMethod.GET)
		public String artWorksCompLastPage(Model model, HttpSession session) {
			List<ArtWork> works = artWorkService.getCompWorksOnPage(pageNumberCW, pageSize,
					(Competition) session.getAttribute("comp"));
			pageCountCW = artWorkService.getPagesCountForCompWorks(pageSize,(Competition) session.getAttribute("comp"));
			pageNumberCW = pageCountCW.intValue();
			model.addAttribute("artWorks", works);
			model.addAttribute("pageCount", pageCountCW);
			model.addAttribute("pageNumber", pageNumberCW);
			return "redirect:/compworks";
		}
	 
}

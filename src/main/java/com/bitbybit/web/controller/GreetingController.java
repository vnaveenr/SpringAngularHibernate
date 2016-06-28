package com.bitbybit.web.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Date;
import org.apache.log4j.Logger;
import com.bitbybit.domain.Greeting;
import com.bitbybit.domain.Color;
import com.bitbybit.service.GreetingService;
import com.bitbybit.web.form.GreetingForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.context.SecurityContextHolder;

@Controller
@RequestMapping("/home")
public class GreetingController {

	protected static Logger logger = Logger.getLogger("GreetingController");

	private GreetingService greetingService;

	@Autowired
	public GreetingController(GreetingService greetingService) {
		this.greetingService = greetingService;
	}

	// note there is no actual greetings.html file!!
	@RequestMapping(value = "/addgreeting.html", method = RequestMethod.GET)
	public String showAddGreetingPage(
			@ModelAttribute("greetingform") GreetingForm greetingForm) {

		logger.info("entering showAddGreetingPage()");

		return "addgreeting";
	}

	@ModelAttribute("colorlist")
	public List<Color> populateColorList() {

		List<Color> colorList = new ArrayList<Color>();
		colorList.add(new Color("Indian Red", "F75D59"));
		colorList.add(new Color("Red", "FF0000"));
		colorList.add(new Color("Salmon", "F9966B"));
		colorList.add(new Color("Lemon Chiffon", "FFF8C6"));
		colorList.add(new Color("Olive Green", "BCE954"));
		colorList.add(new Color("Steel Blue", "C6DEFF"));
		colorList.add(new Color("Medium Purple", "9E7BFF"));
		return colorList;
	}

	@RequestMapping(value = "/greetings.html", method = RequestMethod.POST)
	public String addGreetingAndShowAll(
			@ModelAttribute("greetingform") GreetingForm greetingForm,
			Map<String, Object> model) {

		logger.info("entering addGreetingAndShowAll()");

		Greeting greeting = greetingForm.getGreeting();
		greeting.setGreetingDate(new Date());
		UserDetails userDetails = (UserDetails) SecurityContextHolder
				.getContext().getAuthentication().getPrincipal();
		greeting.setUsername(userDetails.getUsername());
		greetingService.addGreeting(greeting);

		List<Greeting> greetings = greetingService.getAllGreetings();
		model.put("greetinglist", greetings);

		String selectedColorCode = greetingForm.getColor().getColorCode();
		if (selectedColorCode.equals("")) // if no color selected, then make
											// default white
			selectedColorCode = "FFFFFF";
		model.put("colorcode", selectedColorCode);

		// This will resolve to /WEB-INF/jsp/greetings.jsp
		return "greetings";
	}

	@RequestMapping(value = "/greetings.html", method = RequestMethod.GET)
	public String showAllGreetings(Map<String, Object> model) {

		logger.info("entering showAllGreetings");

		List<Greeting> greetings = greetingService.getAllGreetings();
		model.put("greetinglist", greetings);
		model.put("colorcode", "FFFFFF");

		// This will resolve to /WEB-INF/jsp/greetings.jsp
		return "greetings";
	}

	@RequestMapping(value = "/restrictedArea.html", method = RequestMethod.GET)
	public String restrictedArea() {

		logger.info("inside  restrictedArea");
		return "restrictedarea";
	}
}
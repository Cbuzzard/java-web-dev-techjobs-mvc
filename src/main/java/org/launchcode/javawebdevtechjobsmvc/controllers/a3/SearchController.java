package org.launchcode.javawebdevtechjobsmvc.controllers.a3;

import org.launchcode.javawebdevtechjobsmvc.models.a3.JobData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * Created by LaunchCode
 */
@Controller
@RequestMapping("a3/search")
public class SearchController extends TechJobsController{

    @RequestMapping(value = "")
    public String search(Model model) {
        model.addAttribute("columns", columnChoices);
        return "a3/search";
    }

    // TODO #3 - Create a handler to process a search request and render the updated search view.
    @PostMapping("/results")
    String displaySearchResults(Model model, @RequestParam String searchType, @RequestParam String searchTerm) {
        model.addAttribute("columns", columnChoices);
        model.addAttribute("prevSearchType", searchType);
        if(searchType.equals("All") || searchTerm.equals("All")) {
            model.addAttribute("jobs", JobData.findByValue(searchTerm));
        } else {
            model.addAttribute("jobs", JobData.findByColumnAndValue(searchType, searchTerm));
        }
        return "a3/search";
    }

}

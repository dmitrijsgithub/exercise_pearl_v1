package project.mvc.Controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import project.database.DBException;
import project.database.FeedsDAO;
import project.domain.Feed;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
public class FeedItemsController {

         @Autowired
         @Qualifier("ORM_FeedsDAO")
         private FeedsDAO feedsDao;

         List<Feed> feedsList = new ArrayList<>();

    @RequestMapping(value = "/feedList", method = {RequestMethod.GET})
    public ModelAndView redirectToListPage(HttpServletRequest request) throws DBException {
        // Feeds List

        try {
            feedsList = feedsDao.getAll();
            request.setAttribute("feedsList", feedsList);
            return new ModelAndView("feedsList");
        } catch (DBException e) {
            return new ModelAndView("feedsList");
        }
    }
}

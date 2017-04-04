package project.mvc.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import project.database.DBException;
import project.database.FeedsDAO;
import project.database.ItemsDAO;
import project.domain.Feed;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class FeedListController {

        @Autowired
        @Qualifier("ORM_FeedsDAO")
        private FeedsDAO feedsDao;

        @Autowired
        @Qualifier("ORM_ItemsDAO")
        private ItemsDAO itemsDao;

    @RequestMapping(value = { "{feedItems}" }, method = RequestMethod.GET)
    public ModelAndView showFeedItems(@PathVariable int feedItems, HttpServletRequest request) throws DBException {

        //add items list related to specific feed
        //add specific feed name

        try {
            Map<String, Object> model = new HashMap<String, Object>();

            List<?> feedItemsList = itemsDao.getItemsListByFeedId(feedItems);

            model.put("feedItemsList", feedItemsList);

            Feed feedName = feedsDao.getFeedById(feedItems);
            model.put("feedName", feedName);

            return new ModelAndView("feedItemsList", "model", model);

        }catch (DBException e) {
            return new ModelAndView("feedItemsList", "model", "DB error");
        }

    }
}

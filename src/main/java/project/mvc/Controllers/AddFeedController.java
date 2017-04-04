package project.mvc.Controllers;


import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import project.database.DBException;
import project.database.FeedsDAO;
import project.database.ItemsDAO;
import project.domain.Feed;
import project.domain.Item;
import project.service.RSSFeedParser;
import project.service.RSSFeedParserImpl;
import project.service.UrlValidator;
import project.service.UrlValidatorImpl;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
public class AddFeedController {

    @Autowired
    @Qualifier("ORM_FeedsDAO")
    private FeedsDAO feedsDao;

    @Autowired
    @Qualifier("ORM_ItemsDAO")
    private ItemsDAO itemsDao;

    Feed feed = new Feed();
    List<Item> itemsList = new ArrayList<>();

    @RequestMapping(value = "add_feed", method = RequestMethod.GET)
    public ModelAndView urlAndFeedEnter(HttpServletRequest request) throws DBException {

        ModelAndView modelAddFeed = new ModelAndView("addFeed");
        UrlValidator urlValidator = new UrlValidatorImpl();


        // URL & Feed Name enter

        if ((urlValidator.validateUrl(request.getParameter("rss_feed_url")))) {

            RSSFeedParser rssParser = new RSSFeedParserImpl(request.getParameter("rss_feed_url"), request.getParameter("rss_feed_name"));

            try {
                //parsing feeds and items and adding to db

                feed = rssParser.readFeed();
                itemsList = rssParser.readItems();

                feed.setItemList(itemsList);

                for (Item item : itemsList) {
                    itemsDao.create(item);
                }
                feedsDao.create(feed);

                modelAddFeed.addObject("Feed_added_message", "Feed added : " + feed.getFeed_name());
                return modelAddFeed;


            } catch (ConstraintViolationException e) {
                modelAddFeed.addObject("Feed_added_message", "This Url already exists in Feed List");
            } catch (DBException e) {
                modelAddFeed.addObject("Feed_added_message", "DB error");
                return modelAddFeed;
            } catch (Exception e) {
                modelAddFeed.addObject("Feed_added_message", "Something went wrong");
            }

        } else {
            modelAddFeed.addObject("Feed_added_message", "Incorrect Url ");
            return modelAddFeed;
        }

        return modelAddFeed;
    }

}

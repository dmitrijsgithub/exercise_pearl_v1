package project.mvc.Controllers;



import org.hibernate.exception.ConstraintViolationException;
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
import project.domain.Item;
import project.service.*;

import javax.servlet.http.HttpServletRequest;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.*;


@Controller
@RequestMapping ("/")
public class AppController {

         @Autowired
         @Qualifier("ORM_FeedsDAO")
         private FeedsDAO feedsDao;

         @Autowired
         @Qualifier("ORM_ItemsDAO")
         private ItemsDAO itemsDao;

         List<Feed> feedsList = new ArrayList<>();

    @RequestMapping(value = "/", method = {RequestMethod.GET})
    public ModelAndView indexPageButton(HttpServletRequest request) {

        if (request.getParameter("RedirectToListPage") != null) {
            return new ModelAndView("addFeed", "", null);
        }

        return new ModelAndView("main", "model", null);

    }

}

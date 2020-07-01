package com.telusko.demo.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.apache.tomcat.util.json.JSONParser;
import org.dom4j.rule.Mode;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Optional;


import com.google.gson.*;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.telusko.demo.model.Article;
import com.telusko.demo.repository.ArticleRepository;
import com.telusko.demo.service.ArticleService;



@Controller

public class HomeResource implements WebMvcConfigurer{
	
	private static Logger log = LoggerFactory.getLogger(HomeResource.class);
	public static String uploadDirectory = System.getProperty("user.dir")+"/uploads";
	
    @Autowired
    ArticleRepository articleRepository;

    @Autowired
    ArticleService articleService;
    
    
    
    @RequestMapping("/")
	public String home()
	{
		return "home3.html";
	}
	
	@RequestMapping("/aboutus")
	public String aboutus()
	{
		return "Aboutus.html";
	}
	

	@RequestMapping("/contactus")
	public String contactus()
	{
		return "contactus.html";
	}
	
	@RequestMapping("/logout/l")
	public String logoutPagee()
	{
		return "home3.html";
	}
	
	@RequestMapping("/user/showAll")
	public ModelAndView getAlien11() throws JSONException
	{
		ModelAndView mv=new ModelAndView("cardlayouts1.html");
		List<Article> articles=articleService.findAll();
		mv.addObject("articles",articles);
		return mv;
	}
	
	@RequestMapping("/user/article/{article_id}")
	public ModelAndView getAlien111(@PathVariable("article_id")int article_id)
	{
		ModelAndView mv=new ModelAndView("backpage.html");
		Article article=articleRepository.getOne(article_id);
		mv.addObject("article",article);
		return mv;
	}
	
	
	
	// Admin page Code
	
	@RequestMapping("/admin")
    public ModelAndView newProductForm() {
    	ModelAndView mv=new ModelAndView("adminUpdate.html");
		List<Article> articles=articleService.findAll();
		mv.addObject("articles",articles);
		return mv;
    }
	
	@RequestMapping("/admin/articleAdd")
    public ModelAndView newProductForm11() {
        ModelAndView modelAndView = new ModelAndView("adminAdd.html");
        Article article = new Article();
        modelAndView.addObject("article", article);
        return modelAndView;
    }
	
 
    @RequestMapping("/admin/save")
    public ModelAndView save(@Valid @ModelAttribute Article article,BindingResult bindingResult){
    	if (bindingResult.hasErrors()) {
    		ModelAndView modelAndView = new ModelAndView("adminAdd.html");
            return modelAndView;
		}

        ModelAndView modelAndView = new ModelAndView("adminUpdate.html");
        articleService.save(article);
        List<Article> articles=articleService.findAll();
		modelAndView.addObject("articles",articles);
        return modelAndView;
    }
	
    @RequestMapping("/admin/article/{article_id}")
	public ModelAndView getAlien1114(@PathVariable("article_id")int article_id)
	{
		ModelAndView mv=new ModelAndView("backpage.html");
		Article article=articleRepository.getOne(article_id);
		mv.addObject("article",article);
		return mv;
	}
    
	@RequestMapping("/admin/articleUpdate/{article_id}")
	public ModelAndView getAlien1112(@PathVariable("article_id")int article_id)
	{
        articleService.delete(article_id);
		ModelAndView mv=new ModelAndView("adminAdd.html");
        Article article = new Article();
        article.setArticle_id(article_id);
        mv.addObject("article",article);
        return mv;
	}
	
	@RequestMapping("/admin/articleDelete/{article_id}")
	public ModelAndView getAlien1113(@PathVariable("article_id")int article_id)
	{
		ModelAndView mv=new ModelAndView("adminUpdate.html");
        articleService.delete(article_id);
        List<Article> articles=articleService.findAll();
        mv.addObject("articles",articles);
        return mv;
	}
	

    
    
    // Rest API's
    @GetMapping(path="/all")
    public @ResponseBody Iterable<Article> getAllUsers() {
      // This returns a JSON or XML with the users
      return articleRepository.findAll();
    }

	@RequestMapping("/article1/{article_id}")
	@ResponseBody
	public Optional<Article> getAlien(@PathVariable("article_id")int article_id)
	{
		return articleService.findById(article_id);
		
		
	}
    
	/*
    
    
    
	@RequestMapping("/add")
	public String addAlien(Article article)
	{
		articleRepository.save(article);
		return "home.jsp";
	}
	
	
    @RequestMapping("/user1")
	public String home11()
	{
		return "cardlayouts1.html";
	}
	
	@RequestMapping(value= "/admin/save11" , method = RequestMethod.POST)
	public String checkPersonInfo(@Valid @ModelAttribute Article article, BindingResult bindingResult)throws Exception {

		if (bindingResult.hasErrors()) {
			return "adminAdd.html";
		}
		articleService.save(article);

		return "home3.html";
	}
    
    
    
    @RequestMapping("/admin/save")
    public ModelAndView save(@Valid @ModelAttribute Article article) throws IOException {
        ModelAndView modelAndView = new ModelAndView("/home3.html");
        
        File file = new File(article.getImage_link());
        String fileName = file.getName();
        String filepath = Paths.get(uploadDirectory,fileName).toString();
        
        // Save the file locally
        BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(filepath)) );
        stream.write(article.getImage_link().getBytes());
        stream.close();
        System.out.println(fileName);
        System.out.println(filepath);
        
        articleService.save(article);
        return modelAndView;
    }

    
	@RequestMapping("/admin2")
	public String home33()
	{
	
		return "admin2.html";
	}
	
    @RequestMapping("/admin3")
	public String admin()
	{
		return "adminAdd.html";
	}
	
	
    @RequestMapping("/save1")
    public ModelAndView save1() {
        ModelAndView modelAndView = new ModelAndView("/");
        return modelAndView;
    }
    
    @GetMapping("/register")
    public String showForm(Model model) {
        Article article = new Article();
        model.addAttribute("article", article);
                  
        return "home.html";
    }
    
    
    @RequestMapping("/login")
	public String loginPage()
	{
		return "login.jsp";
	}
	
	
	
	
	@RequestMapping("/logout-success")
	public String logoutPage()
	{
		return "logout.jsp";
	}
	
    @RequestMapping("/admin/save1") // Map ONLY POST Requests
    public @ResponseBody String addNewUser ( @RequestParam String title,
        @RequestParam String Category,
        @RequestParam String Description,
    @RequestParam String image_link,
    @RequestParam String start_time,
    @RequestParam String end_time,
    @RequestParam String official_link) throws IOException {
      // @ResponseBody means the returned String is the response, not a view name
      // @RequestParam means it is a parameter from the GET or POST request
    	//System.out.println(start_time);
    	
    	// String fileName = image_link.getOriginalFilename();
         //String filepath = Paths.get(uploadDirectory,fileName).toString();
         

      Article a = new Article();
      a.setTitle(title);
      a.setCategory(Category);
      a.setDescription(Description);
      a.setEnd_time(end_time);
      a.setImage_link(image_link);
      a.setOfficial_link(official_link);
      a.setStart_time(start_time);
      // Save the file locally
      //BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(filepath)) );
      //stream.close();
      //System.out.println(filepath);
      articleService.save(a);
      return "Saved";
    }
	
	
	@RequestMapping("/getArticle")
	public ModelAndView getAlien1(@RequestParam int aid)
	{
		ModelAndView mv=new ModelAndView("showArticle.jsp");
		Article article=articleService.findById(aid).orElse(new Article());
		
		//System.out.println(repo.findByTech("Java"));
		//System.out.println(repo.findByAidGreaterThan(101));
		//System.out.println(repo.findByTechSorted("Java"));
		mv.addObject(article);
		return mv;
	}
	
	@RequestMapping("/userr/showAll")
	public ModelAndView getAlien111() throws JSONException
	{
		ModelAndView mv=new ModelAndView("cardlayouts1.html");
		List<Article> articles=articleService.findAll();
		//System.out.println(articles);
		//Gson gson = new GsonBuilder().setPrettyPrinting().create();
		//String jarticles = gson.toJson(articles);
		//JSONArray list = new JSONArray(jarticles);
		//System.out.println(list);
		//System.out.println(list.getClass().getName());
		//System.out.println(repo.findByTech("Java"));
		//System.out.println(repo.findByAidGreaterThan(101));
		//System.out.println(repo.findByTechSorted("Java"));
		mv.addObject("articles",articles);
		return mv;
	}
	
    @GetMapping(path="/all2")
    public @ResponseBody Iterable<Article> getAllUsers2() {
      // This returns a JSON or XML with the users
      return articleRepository.findAll();
    }
	
    
    @RequestMapping("/admin/save")
    public ModelAndView save(@Valid @ModelAttribute Article article, BindingResult bindingResult) {
        //if(bindingResult.hasErrors()) {
          //  ModelAndView modelAndView = new ModelAndView("admin/new-product");
           // return modelAndView;
       // }
        ModelAndView modelAndView = new ModelAndView("redirect:/admin/index");
        articleService.save(article);
        return modelAndView;
    }
    

    @RequestMapping("/admin/new")
    public ModelAndView newArticleForm() {
        ModelAndView modelAndView = new ModelAndView("admin/new-product");
        Article article = new Article();
        modelAndView.addObject("article", article);
        return modelAndView;
    }
    
    */
    
    
	
	
	

}

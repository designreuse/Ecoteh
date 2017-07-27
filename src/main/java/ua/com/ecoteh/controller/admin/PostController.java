package ua.com.ecoteh.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mapping.model.IllegalMappingException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import ua.com.ecoteh.entity.article.Article;
import ua.com.ecoteh.entity.file.File;
import ua.com.ecoteh.entity.file.FileBuilder;
import ua.com.ecoteh.entity.post.Post;
import ua.com.ecoteh.entity.post.PostBuilder;
import ua.com.ecoteh.exception.ExceptionMessage;
import ua.com.ecoteh.service.data.PostService;
import ua.com.ecoteh.service.fabrica.MainMVFabric;
import ua.com.ecoteh.util.cache.Cache;

/**
 * The class implements a set of methods for working with
 * objects of the {@link Post} class for admins.
 * Class methods create and return modelsAndView, depending on the request.
 *
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 */
@Controller
@RequestMapping(
        value = {
                "/admin/blog",
                "/admin/post",
                "/admin/posts"
        }
)
@ComponentScan(
        basePackages = {
                "ua.com.ecoteh.service.fabrica",
                "ua.com.ecoteh.service.data"
        }
)
@SuppressWarnings("SpringMVCViewInspection")
public final class PostController {

    /**
     * The implementation of the interface provides a set of standard methods
     * for creates and returns the main modelAndViews.
     */
    private final MainMVFabric fabric;

    /**
     * The implementation of the interface describes a set of methods
     * for working with objects of the {@link Article} class.
     */
    private final PostService postService;

    /**
     * Constructor.
     * Initializes a implementations of the interfaces.
     *
     * @param fabric          the implementation of the {@link MainMVFabric} interface.
     * @param postService  the implementation of the {@link PostService} interface.
     */
    @Autowired
    @SuppressWarnings("SpringJavaAutowiringInspection")
    public PostController(
            @Qualifier("cacheMVFabricImpl") final MainMVFabric fabric,
            final PostService postService
    ) {
        this.fabric = fabric;
        this.postService = postService;
    }

    /**
     * Returns a page to add a new post.
     * Request mapping: /admin/post/new
     * Method: GET
     *
     * @return The ready object of the ModelAndView class.
     */
    @RequestMapping(
            value = "/new",
            method = RequestMethod.GET
    )
    public ModelAndView getNewPostPage() {
        final ModelAndView modelAndView = this.fabric.getDefaultModelAndView();
        modelAndView.setViewName("post/add");
        return modelAndView;
    }

    /**
     * Adds a new post with the incoming parameters
     * and redirects by the "/post/{url}" URL,
     * where {url} is a URL of a saving article.
     * Request mapping: /admin/article/add
     * Method: POST
     *
     * @param title         the title of a new post.
     * @param text          the new text to a post.
     * @param description   the description of a new post.
     * @param keywords      the keywords of a new post.
     * @param multipartLogo thea file of photo to a new post.
     * @param validated     the validated of a new post.
     * @return The redirect string to the "/post/{url}" URL,
     * where {url} is a URL of a saving post.
     */
    @RequestMapping(
            value = "/add",
            method = RequestMethod.POST
    )
    public String addPost(
            @RequestParam(value = "title", defaultValue = "") final String title,
            @RequestParam(value = "text", defaultValue = "") final String text,
            @RequestParam(value = "description", defaultValue = "") final String description,
            @RequestParam(value = "keywords", defaultValue = "") final String keywords,
            @RequestParam(value = "logo") final MultipartFile multipartLogo,
            @RequestParam(value = "is_valid", defaultValue = "false") final boolean validated
    ) {
        final PostBuilder postBuilder = Post.getBuilder();
        postBuilder.addTitle(title).addKeywords(keywords).addValidated(validated)
                .addDescription(description).addText(text);

        final FileBuilder fileBuilder = File.getBuilder();
        fileBuilder.addTitle(title).addMultipartFile(multipartLogo).isValid();
        final File logo = fileBuilder.build();
        postBuilder.addLogo(logo);

        final Post post = postBuilder.build();
        final Post savingPost = this.postService.add(post);
        Cache.clear();
        return "redirect:/post/" + savingPost.getUrl();
    }

    /**
     * The method throws an exception in the case of reference to it.
     * The exception message:
     * "GET method in "/admin/post/add" is not supported!"
     * Request mapping: /admin/post/add
     * Method: GET
     *
     * @throws IllegalMappingException thrown when an error occurs reading
     *                                 the mapping between object and datastore.
     */
    @RequestMapping(
            value = "/add",
            method = RequestMethod.GET
    )
    public void addPost() throws IllegalMappingException {
        final String message = String.format(
                ExceptionMessage.GET_METHOD_NOT_SUPPORTED_MESSAGE,
                "/admin/post/add"
        );
        throw new IllegalMappingException(message);
    }

    /**
     * Returns a page to edit an post with the incoming URL.
     * Request mapping: /admin/post/edit/{url},
     * where {url} is a URL of an post to edit.
     * Method: GET
     *
     * @param url the URL of a post to edit.
     * @return The ready object of the ModelAndView class.
     */
    @RequestMapping(
            value = "/edit/{url}",
            method = RequestMethod.GET
    )
    public ModelAndView editPost(@PathVariable("url") final String url) {
        final ModelAndView modelAndView = this.fabric.getDefaultModelAndView();
        modelAndView.addObject("post", this.postService.getByUrl(url, false));
        modelAndView.setViewName("post/edit");
        return modelAndView;
    }

    /**
     * Updates and save an post with the incoming URL
     * and redirects by the "/post/{url}" URL,
     * where {url} is a URL of a saving post.
     * Request mapping: /admin/post/update
     * Method: POST
     *
     * @param url           the URL of a post to update.
     * @param title         the new title to a post.
     * @param text          the new text to a post.
     * @param description   the new description to a post.
     * @param keywords      the new keywords to a post.
     * @param multipartLogo the file of photo to a new category.
     * @param validated     the validated of a article.
     * @return The redirect string to the "/post/{url}" URL,
     * where {url} is a URL of a saving post.
     */
    @RequestMapping(
            value = "/update",
            method = RequestMethod.POST
    )
    public String updatePost(
            @RequestParam(value = "url", defaultValue = "") final String url,
            @RequestParam(value = "title", defaultValue = "") final String title,
            @RequestParam(value = "text", defaultValue = "") final String text,
            @RequestParam(value = "description", defaultValue = "") final String description,
            @RequestParam(value = "keywords", defaultValue = "") final String keywords,
            @RequestParam(value = "logo") final MultipartFile multipartLogo,
            @RequestParam(value = "is_valid", defaultValue = "false") final boolean validated
    ) {
        final PostBuilder postBuilder = Post.getBuilder();
        postBuilder.addUrl(url).addTitle(title).addKeywords(keywords).addValidated(validated)
                .addDescription(description).addText(text);

        final FileBuilder fileBuilder = File.getBuilder();
        fileBuilder.addTitle(title).addMultipartFile(multipartLogo).isValid();
        final File logo = fileBuilder.build();
        postBuilder.addLogo(logo);

        final Post post = postBuilder.build();
        final Post updatedPost = this.postService.update(post);
        Cache.clear();
        return "redirect:/post/" + updatedPost.getUrl();
    }

    /**
     * The method throws an exception in the case of reference to it.
     * The exception message:
     * "GET method in "/admin/post/update" is not supported!"
     * Request mapping: /admin/post/update
     * Method: GET
     *
     * @throws IllegalMappingException thrown when an error occurs reading
     *                                 the mapping between object and datastore.
     */
    @RequestMapping(
            value = "/update",
            method = RequestMethod.GET
    )
    public void updatePost() throws IllegalMappingException {
        final String message = String.format(
                ExceptionMessage.GET_METHOD_NOT_SUPPORTED_MESSAGE,
                "/admin/post/update"
        );
        throw new IllegalMappingException(message);
    }

    /**
     * Removes an article with the incoming URL
     * and redirects by the "/" URL.
     * Request mapping: /admin/article/delete/{url},
     * where {url} is a URL of an article to remove.
     * Method: GET
     *
     * @param url the URL of an article to remove.
     * @return The redirect string to the "/" URL.
     */
    @RequestMapping(
            value = "/delete/{url}",
            method = RequestMethod.GET
    )
    public String deletePostByUrl(@PathVariable("url") final String url) {
        final boolean result = this.postService.removeByUrl(url);
        if (result) {
            Cache.clear();
        }
        return "redirect:/";
    }

    /**
     * Removes an all articles and redirects by the "/" URL.
     * Request mapping: /admin/article/delete/all
     * Method: GET
     *
     * @return The redirect string to the "/" URL.
     */
    @RequestMapping(
            value = "/delete/all",
            method = RequestMethod.GET
    )
    public String deleteAllPosts() {
        this.postService.removeAll();
        Cache.clear();
        return "redirect:/";
    }
}

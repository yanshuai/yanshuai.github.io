package controller;

import bo.AppBo;
import model.App;
import model.Result;
import model.editor.AppPropertyEditor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class AppController {
	
    @Autowired
    private AppBo appBo;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(App.class, new AppPropertyEditor());
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public Result addApp(@RequestParam(value = "app") App app) {
        try {
            return new Result(appBo.addApp(app));
        } catch (Exception ex) {
            return new Result(true, String.valueOf(ex));
        }
    }

    @RequestMapping(value = "/del/{id}", method = RequestMethod.POST)
    @ResponseBody
    public Result delApp(@PathVariable(value = "id") int id) {
        try {
            appBo.delApp(id);
            return new Result();
        } catch (Exception ex) {
            return new Result(true, String.valueOf(ex));
        }
    }

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    @ResponseBody
    public Result getApps() {
        try {
            return new Result(appBo.getApps());
        } catch (Exception ex) {
            return new Result(true, String.valueOf(ex));
        }
    }
}

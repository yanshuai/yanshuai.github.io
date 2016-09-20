package bo;

import dao.AppDao;
import java.util.List;
import model.App;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class AppBoImpl implements AppBo {

    @Autowired
    private AppDao appDao;

    @Autowired
    private ApplicationContext appContext;

    public AppBoImpl() {
        System.out.println("**** In AppBo Impl ****");
        if (null != appContext) {
            System.out.println(appContext.getBeanDefinitionCount());
        }
    }

    public App addApp(App app) throws Exception {
        return appDao.addApp(app);
    }

    public void delApp(int id) throws Exception {
        appDao.delApp(id);
    }

    public List<App> getApps() throws Exception {
        return appDao.getApps();
    }
}

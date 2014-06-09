package bo;

import java.util.List;
import model.App;

public interface AppBo {

    public App addApp(App app) throws Exception;

    public void delApp(int id) throws Exception;

    public List<App> getApps() throws Exception;
}

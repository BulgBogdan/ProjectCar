package projectCar.dao.interfaces;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.search.FullTextSession;
import org.hibernate.search.Search;

public interface IFullTextManager {

    Logger logger = Logger.getLogger(IFullTextManager.class);

    static FullTextSession getFullTextSession(Session session) {
        FullTextSession fullTextSession = Search.getFullTextSession(session);
        try {
            fullTextSession.createIndexer().startAndWait();
        } catch (InterruptedException e) {
            logger.error("FullTextSession exception", e);
            e.printStackTrace();
        }
        return fullTextSession;
    }
}

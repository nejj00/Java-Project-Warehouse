package bg.tu_varna.sit.group19.warehouse_project.data.access;

import bg.tu_varna.sit.group19.warehouse_project.common.Constants;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.File;

public class Connection {

    private static final Logger log = Logger.getLogger(Connection.class);
    private static SessionFactory sessionFactory;
    private static Session session;

    static {
        try {
            sessionFactory = new Configuration().configure(new File(Constants.HibernateConfig.CFG_FILE)).buildSessionFactory();
        } catch (Throwable ex) {
            log.error("Initial SessionFactory created failed" + ex);
        }
    }

    public static Session openSession() {
        return session = sessionFactory.openSession();
    }

    public static void openSessionClose() {
        session.close();
    }
}

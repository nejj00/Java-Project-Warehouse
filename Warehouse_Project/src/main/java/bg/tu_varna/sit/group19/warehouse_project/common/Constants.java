package bg.tu_varna.sit.group19.warehouse_project.common;

public class Constants {
    public static class View{
        public static final String HELLO_VIEW = "/bg/tu_varna/sit/group19/warehouse_project/presentations.view/hello-view.fxml";
        public static final String LOGIN_VIEW = "/bg/tu_varna/sit/group19/warehouse_project/presentations.view/login.fxml";
        public static final String MAIN_WINDOW_VIEW = "/bg/tu_varna/sit/group19/warehouse_project/presentations.view/MainWindow.fxml";
        public static final String REGISTER_VIEW = "/bg/tu_varna/sit/group19/warehouse_project/presentations.view/register.fxml";
        public static final String ADMIN_VIEW = "/bg/tu_varna/sit/group19/warehouse_project/presentations.view/adminWindow.fxml";
        public static final String AGENT_VIEW = "/bg/tu_varna/sit/group19/warehouse_project/presentations.view/agentWindow.fxml";
        public static final String OWNER_VIEW = "/bg/tu_varna/sit/group19/warehouse_project/presentations.view/ownerWindow.fxml";
        public static final String ADMIN_OWNERS_LIST_VIEW = "/bg/tu_varna/sit/group19/warehouse_project/presentations.view/adminWindowOwners.fxml";
        public static final String WAREHOUSES_LIST_VIEW = "/bg/tu_varna/sit/group19/warehouse_project/presentations.view/ownerWindowWarehouseList.fxml";
        public static final String CREATE_WAREHOUSE_VIEW = "/bg/tu_varna/sit/group19/warehouse_project/presentations.view/ownerWindowCreateWarehouse.fxml";
        public static final String WAREHOUSE_ROOMS_VIEW = "/bg/tu_varna/sit/group19/warehouse_project/presentations.view/warehouseRoomsWindow.fxml";
        public static final String CONTRACT_VIEW = "/bg/tu_varna/sit/group19/warehouse_project/presentations.view/contract.fxml";
        public static final String AGENT_LIST_VIEW = "/bg/tu_varna/sit/group19/warehouse_project/presentations.view/agentWindowList.fxml";
    }

    public static class Configurations{
        public static  final String LOG4J_PROPERTIES = "/bg/tu_varna/sit/group19/warehouse_project/configuration/log4j.properties";
    }

    public static class Values{
        public static  final String Title = "Example App";
        public static  final String LOGIN_TITILE = "Login";
        public static  final String MAIN_TITILE = "Login";
    }

    public static class HibernateConfig{
        public static final String CFG_FILE = "C:\\Users\\Radi_boy112\\Documents\\GitHub\\Java-Project-Warehouse\\Warehouse_Project\\src\\main\\resources\\bg\\tu_varna\\sit\\group19\\warehouse_project\\configuration\\hibernate.cfg.xml";
    }

    public static class Owner {
        public static final int ShowContractsClicked = 1;
        public static final int ShowAvailableWarehousesClicked = 2;
        public static final int ShowAllWarehousesClicked = 3;
    }

    public static class Agent {
        public static final int ShowOwnersWarehousesClicked = 1;
        public static final int ShowAvailableWarehousesClicked = 2;
    }

}

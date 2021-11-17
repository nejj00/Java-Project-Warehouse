package bg.tu_varna.sit.group19.warehouse_project.data.repositories;

import bg.tu_varna.sit.group19.warehouse_project.data.entities.Admin;


public class AdminRepository extends BaseRepository<Admin> {

    public AdminRepository() {
        super(Admin.class);
    }

    public static AdminRepository getInstance()
    {
        return AdminRepository.AdminRepositoryHolder.INSTANCE;
    }

    private static class AdminRepositoryHolder {
        public static final AdminRepository INSTANCE = new AdminRepository();
    }
}

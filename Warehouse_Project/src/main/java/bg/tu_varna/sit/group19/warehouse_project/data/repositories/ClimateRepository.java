package bg.tu_varna.sit.group19.warehouse_project.data.repositories;

import bg.tu_varna.sit.group19.warehouse_project.data.entities.ClimateCondition;

public class ClimateRepository extends BaseRepository<ClimateCondition> {

    public ClimateRepository() {
        super(ClimateCondition.class);
    }

    public static ClimateRepository getInstance(){
        return ClimateRepository.ClimateRepositoryHolder.INSTANCE;
    }

    public static class ClimateRepositoryHolder {
        public static final ClimateRepository INSTANCE = new ClimateRepository();
    }
}

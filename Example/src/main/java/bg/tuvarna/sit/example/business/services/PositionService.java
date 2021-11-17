package bg.tuvarna.sit.example.business.services;

import bg.tuvarna.sit.example.data.enities.Positions;
import bg.tuvarna.sit.example.data.repositories.PositionRepository;
import bg.tuvarna.sit.example.presentation.models.PositionListViewModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;
import java.util.stream.Collectors;

public class PositionService {
    private final PositionRepository repository = PositionRepository.getInstance();

    public static PositionService getInstance(){
        return PositionServiceHolder.INSTANCE;
    }

    private static class PositionServiceHolder {
        public static final PositionService INSTANCE = new PositionService();
    }

    public ObservableList<PositionListViewModel> GetAll(){
        List<Positions> positions = repository.getAll();

        return FXCollections.observableList(
                positions.stream().map(
                        t-> new PositionListViewModel(
                                t.getPosition()
                        )
                ).collect(Collectors.toList()));
    }
}

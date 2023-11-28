package co.edu.unicauca.SIRENABackend.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.unicauca.SIRENABackend.models.BookingModel;
import co.edu.unicauca.SIRENABackend.models.ProgramModel;
import co.edu.unicauca.SIRENABackend.models.StatisticsModel;
import co.edu.unicauca.SIRENABackend.repositories.IBookingRepository;
import co.edu.unicauca.SIRENABackend.repositories.IProgramRepository;
import co.edu.unicauca.SIRENABackend.services.StatisticsService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StatisticsServiceImpl implements StatisticsService {

    @Autowired
    private final IBookingRepository bookingRepository;
    @Autowired
    private final IProgramRepository programRepository;

    @Override
    public List<StatisticsModel> getClassroomsStatistics() {
        return getStatisticsBase(1);
    }

    @Override
    public List<StatisticsModel> getBuildingsStatistics() {
        return getStatisticsBase(2);
    }

    @Override
    public List<StatisticsModel> getFacultiesStatistics() {
        return getStatisticsBase(3);
    }

    @Override
    public List<StatisticsModel> getProgramsStatistics() {
        return getStatisticsBase(4);
    }

    @Override
    public List<ProgramModel> getProgramsFacultie(Integer facultieId) {
        List<ProgramModel> programsList = new ArrayList<>();
        for (ProgramModel program : programRepository.findAll()) {
            if (program.getFaculty().getId() == facultieId) {
                programsList.add(program);
            }
        }
        return programsList;
    }

    private List<StatisticsModel> getStatisticsBase(int type) {
        // 1 para salon, 2 para facultad, 3 para edificio
        List<StatisticsModel> statisticsResult = new ArrayList<>();
        List<BookingModel> bookingList = bookingRepository.findAll();
        for (BookingModel booking : bookingList) {
            // Verificar si ya fue guardado
            Integer indexFinded = switch (type) {
                case 1 -> findIndex(statisticsResult, booking.getClassroom().getId());
                case 2 -> findIndex(statisticsResult, booking.getFaculty().getId());
                case 3 -> findIndex(statisticsResult, booking.getClassroom().getBuilding().getId());
                case 4 -> findIndex(statisticsResult, booking.getProgram().getId());
                default -> -1;
            };
            if (indexFinded >= 0) {
                statisticsResult.get(indexFinded).getBokings_ids().add(booking.getId());
            } else {
                StatisticsModel newStatistic = getStatisticsModel(type, booking);
                newStatistic.getBokings_ids().add(booking.getId());
                statisticsResult.add(newStatistic);
            }
        }
        return statisticsResult;
    }

    private static StatisticsModel getStatisticsModel(int type, BookingModel booking) {
        StatisticsModel newStatistic = new StatisticsModel();
        switch (type) {
            case 1 -> newStatistic.setEntity_id(booking.getClassroom().getId());
            case 2 -> newStatistic.setEntity_id(booking.getFaculty().getId());
            case 3 -> newStatistic.setEntity_id(booking.getClassroom().getBuilding().getId());
            case 4 -> newStatistic.setEntity_id(booking.getProgram().getId());
        }
        newStatistic.setBokings_ids(new ArrayList<>());
        return newStatistic;
    }

    private Integer findIndex(List<StatisticsModel> statistics, int id) {
        for (StatisticsModel statistic : statistics) {
            if (statistic.getEntity_id() == id)
                return statistics.indexOf(statistic);
        }
        return -1;
    }
}

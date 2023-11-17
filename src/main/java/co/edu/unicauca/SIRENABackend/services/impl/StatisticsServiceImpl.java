package co.edu.unicauca.SIRENABackend.services.impl;

import co.edu.unicauca.SIRENABackend.models.BookingModel;
import co.edu.unicauca.SIRENABackend.models.ClassroomModel;
import co.edu.unicauca.SIRENABackend.models.StatisticsModel;
import co.edu.unicauca.SIRENABackend.repositories.*;
import co.edu.unicauca.SIRENABackend.security.repositories.IUserRepository;
import co.edu.unicauca.SIRENABackend.services.StatisticsService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StatisticsServiceImpl implements StatisticsService {

    @Autowired
    private final IBookingRepository bookingRepository;

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

    private List<StatisticsModel> getStatisticsBase(int type){
        //1 para salon, 2 para facultad, 3 para edificio
        List<StatisticsModel> statisticsResult=new ArrayList<>();
        List<BookingModel> bookingList=bookingRepository.findAll();
        for (BookingModel booking:bookingList) {
            //Verificar si ya fue guardado
            Integer indexFinded=-1;
            switch (type){
                case 1:
                    indexFinded = findIndex(statisticsResult, booking.getClassroom().getId());
                    break;
                case 2:
                    indexFinded = findIndex(statisticsResult, booking.getFaculty().getId());
                    break;
                case 3:
                    indexFinded = findIndex(statisticsResult, booking.getClassroom().getBuilding().getId());
                    break;
            }
            if(indexFinded>=0){
                statisticsResult.get(indexFinded).getBokings_ids().add(booking.getId());
            }
            else
            {
                StatisticsModel newStatistic=new StatisticsModel();
                newStatistic.setEntity_id(booking.getClassroom().getId());
                newStatistic.setBokings_ids(new ArrayList<>());
                statisticsResult.add(newStatistic);
            }
        }
        return statisticsResult;
    }
    private Integer findIndex(List<StatisticsModel> statistics, int id){
        for(StatisticsModel statistic:statistics){
            if(statistic.getEntity_id()==id)
                return statistics.indexOf(statistic);
        }
        return -1;
    }
}

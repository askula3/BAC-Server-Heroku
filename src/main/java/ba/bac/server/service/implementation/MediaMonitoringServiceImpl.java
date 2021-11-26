package ba.bac.server.service.implementation;

import ba.bac.server.io.entity.NotableIndividualEntity;
import ba.bac.server.io.repository.MediaMonitoringRepository;
import ba.bac.server.io.repository.NotableIndividualRepository;
import ba.bac.server.service.MediaMonitoringService;
import ba.bac.server.service.NotableIndividualService;
import ba.bac.server.shared.dto.MediaMonitoringDto;
import ba.bac.server.shared.dto.NotableIndividualDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.Calendar;

@Service("mediaMonitoringService")
public class MediaMonitoringServiceImpl implements MediaMonitoringService {
    @Autowired
    MediaMonitoringRepository mediaMonitoringRepository;


    @Override
    public MediaMonitoringDto getMonitorings(String category) {
        return null;
    }

    @Override
    public MediaMonitoringDto createMonitoring(MediaMonitoringDto monitor) {
        return null;
    }

    @Override
    public MediaMonitoringDto updateMonitoring(MediaMonitoringDto monitor) {
        return null;
    }

    @Override
    public MediaMonitoringDto deleteMonitoring(long id) {
        return null;
    }
}
